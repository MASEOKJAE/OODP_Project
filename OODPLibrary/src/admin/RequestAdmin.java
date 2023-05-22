package admin;

import view.adminPage;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class RequestAdmin extends JPanel {
    private JTextField searchField;
    private RequestSearcher searcher;
    private JTable bookTable;
    private DefaultTableModel model;
    private JButton backButton;
    private JComboBox<String> comboBox;
    private JButton searchButton;
    private JScrollPane scrollPane;


    public RequestAdmin() {
        init();
        setDisplay();
        addListener();
    }

    private void init() {
        searcher = new RequestSearcher(); // searcher 객체를 생성
        // 테이블 생성
        bookTable = new JTable();
        model = new DefaultTableModel();
        model.addColumn("제어번호");
        model.addColumn("제목");
        model.addColumn("저자");
        model.addColumn("발행처");
        model.addColumn("발행년도");
        bookTable.setModel(model);

        //뒤로가기 버튼 생성
        backButton = new JButton("뒤로 가기");
        // 콤보 박스 생성
        comboBox = new JComboBox<>();
        comboBox.addItem("등록");
        comboBox.addItem("요청 거절");

        // 검색 버튼 생성
        searchButton = new JButton("처리");

        try {
            // 파일에서 데이터 읽기
            String filePath = System.getProperty("user.dir") + "/src/resources/Request_List.csv";
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                if(data[0].charAt(0) == '\"')
                    data[0] = data[0].substring(1, data[0].length()-1);

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
        searchField.setColumns(5); // 10개의 컬럼으로 설정
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

        for(int i=0; i<model.getRowCount(); i++) {
            for(int j=0; j<model.getColumnCount(); j++) {
                Object value = model.getValueAt(i, j);
                System.out.println("Value at (" + i + ", " + j + "): " + value);
            }
        }

        bookTable.setRowSorter(sorter);
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                // 뒤로가기 버튼을 선택했을 때
                if(backButton.equals(ae.getSource())){
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(RequestAdmin.this);
                    frame.getContentPane().removeAll(); // 기존 컨텐트 팬 제거
                    frame.getContentPane().add(new adminPage()); // 새로운 컨텐트 팬 추가
                    frame.revalidate();
                    frame.repaint();
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
