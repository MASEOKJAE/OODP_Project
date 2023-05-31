package View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JTextField;
import Controller.LibrarySystem;
import Controller.searchBook.SearchType;

@SuppressWarnings("serial")
public class SearchBook extends JPanel {
    private JTextField searchField;
    private SearchType searcher;
    private JTable bookTable;
    private DefaultTableModel model;
    private JButton backButton;
    private JComboBox<String> comboBox;
    private JButton searchButton;
    private JScrollPane scrollPane;


    public SearchBook() {
        init();
        setDisplay();
        addListener();
    }

    private void init() {
        //검색을 위한 Context 클래스 생성
        searcher = new SearchType();

        // 테이블 생성
        bookTable = new JTable();
        model = new DefaultTableModel();
        model.addColumn("제어번호");
        model.addColumn("제목");
        model.addColumn("저자");
        model.addColumn("발행처");
        model.addColumn("발행년도");
        model.addColumn("대여");
        bookTable.setModel(model);


        //뒤로가기 버튼 생성
        backButton = new JButton("뒤로 가기");
        // 콤보 박스 생성
        comboBox = new JComboBox<>();
        comboBox.addItem("제어번호");
        comboBox.addItem("제목");
        comboBox.addItem("저자");
        comboBox.addItem("발행처");
        comboBox.addItem("발행년도");

        // 검색 버튼 생성
        searchButton = new JButton("검색");

        try {
            // 파일에서 데이터 읽기
            String filePath = System.getProperty("user.dir") + "/src/Model/resources/Book_List.csv";
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                if(data[1].charAt(0) == '\"')
                    data[1] = data[1].substring(3, data[1].length()-3);

                model.addRow(data);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void setDisplay() {
        JPanel panel = new JPanel(new BorderLayout());
        // 검색어 입력 필드, 콤보 박스, 검색 버튼을 담을 패널 생성
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        //검색어 영역 생성
        searchField = new JTextField();
        searchField.setColumns(10); // 10개의 컬럼으로 설정
        searchPanel.add(searchField);
        searchPanel.add(comboBox);
        searchPanel.add(searchButton);
        // JScrollPane 생성
        scrollPane = new JScrollPane(bookTable);
        // 뒤로가기 버튼 배치
        // SearchBook 패널에 BorderLayout으로 추가
        panel.add(searchPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(backButton, BorderLayout.SOUTH);
        add(panel);
    }

    private void addListener() {
        // 테이블 정렬 기능 추가
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        bookTable.setRowSorter(sorter);
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                // 뒤로가기 버튼을 선택했을 때
                if(backButton.equals(ae.getSource())){

                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(SearchBook.this);
                    frame.dispose();
                    frame.setContentPane(new LibrarySystem());
                    frame.revalidate();
                }
                //검색 버튼을 선택했을 때
                if(searchButton.equals(ae.getSource())){
                    String searchText = searchField.getText();
                    if (searchText.trim().length() == 0) {
                        sorter.setRowFilter(null);
                    } else {
                        // 검색 버튼 클릭 시 테이블 필터링 기능 추가
                        searcher.search(comboBox.getSelectedItem().toString(),searchText, model);
                    }
                }
            }
        };
        backButton.addActionListener(listener);
        searchButton.addActionListener(listener);
    }


}
