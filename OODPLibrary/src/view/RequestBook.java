package view;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import system.controller.LibrarySystem;
import searchBook.*;

@SuppressWarnings("serial")
public class RequestBook extends JPanel {
    private JLabel pageTitle;
    private JLabel titleLabel;
    private JLabel authorLabel;
    private JLabel publishLabel;
    private JLabel yearLabel;

    private JTextField titleField;
    private JTextField authorField;
    private JTextField publishField;
    private JTextField yearField;
    private JButton requestButton;
    private JButton backButton;
    private JTable requestTable;
    private DefaultTableModel model;

    public RequestBook() {
        init();
        setDisplay();
        addListener();
    }

    private void init() {

        //뒤로가기 버튼 생성
        backButton = new JButton("뒤로 가기");

        // 검색 버튼 생성
        requestButton = new JButton("요청");

        // 입력창 초기화
        titleField = new JTextField();
        authorField = new JTextField();
        publishField = new JTextField();
        yearField = new JTextField();

    }
    private void setDisplay() {
        JPanel panel = new JPanel(new BorderLayout());
        // 검색어 입력 필드, 콤보 박스, 검색 버튼을 담을 패널 생성
        JPanel inputPanel = new JPanel(new GridLayout(0, 2)); // 0행 2열의 그리드 레이아웃 사용
        pageTitle = new JLabel("[ 요청하실 책 정보를 입력해주세요 ]");
        titleLabel = new JLabel("책 제목: ");
        authorLabel = new JLabel("저자명: ");
        publishLabel = new JLabel("출판사: ");
        yearLabel = new JLabel("발행년도: ");
        JTextField titleField = new JTextField(10);
        JTextField authorField = new JTextField(10);
        JTextField publishField = new JTextField(10);
        JTextField yearField = new JTextField(10);

        inputPanel.add(pageTitle);
        inputPanel.add(new JPanel()); // 빈 패널을 추가하여 한 칸을 차지하도록 함
        inputPanel.add(titleLabel);
        inputPanel.add(titleField);
        inputPanel.add(authorLabel);
        inputPanel.add(authorField);
        inputPanel.add(publishLabel);
        inputPanel.add(publishField);
        inputPanel.add(yearLabel);
        inputPanel.add(yearField);
        inputPanel.add(requestButton);
        inputPanel.add(backButton);

        // 확인 버튼 배치
        panel.add(inputPanel, BorderLayout.CENTER);

        add(panel);
    }

    private void addListener() {
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                // 확인 버튼을 선택했을 때
                if(requestButton.equals(ae.getSource())){
                    addToRequestList();
                }
                // 뒤로가기 버튼을 선택했을 때
                if(backButton.equals(ae.getSource())){

                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(RequestBook.this);
                    frame.dispose();
                    frame.setContentPane(new LibrarySystem());
                    frame.revalidate();
                }
            }
        };
        backButton.addActionListener(listener);
        requestButton.addActionListener(listener);
    }

    private void addToRequestList() {
        String filePath = System.getProperty("user.dir") + "/src/resources/Request_List.csv";

        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(filePath, true)))) {
            // 맨 마지막 인덱스 가져오기
            int lastIndex = model.getRowCount() > 0 ? Integer.parseInt(model.getValueAt(model.getRowCount() - 1, 0).toString()) : 0;
            int newIndex = lastIndex + 1;

            // 데이터 추가
            writer.print(newIndex);
            writer.print(",");
            writer.print(titleField.getText());
            writer.print(",");
            writer.print(authorField.getText());
            writer.print(",");
            writer.print(publishField.getText());
            writer.print(",");
            writer.println(yearField.getText());

            // 모델에도 추가
            model.addRow(new Object[] { newIndex, titleField.getText(), authorField.getText(), publishField.getText(), yearField.getText() });

            // 입력 필드 초기화
            titleField.setText("");
            authorField.setText("");
            publishField.setText("");
            yearField.setText("");

            System.out.println("Data added successfully to the Request_List.csv file.");
        } catch (IOException e) {
            System.out.println("Error occurred while adding data to the Request_List.csv file: " + e.getMessage());
        }
    }
}