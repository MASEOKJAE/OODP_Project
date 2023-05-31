package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.xml.crypto.Data;

import system.controller.LibrarySystem;
import system.controller.User;

@SuppressWarnings("serial")
public class RentBook extends JPanel {
    private JTextField inputTextField;
    private JButton rentButton;
    private JTable bookTable;
    private DefaultTableModel model;
    private JScrollPane scrollPane;
    private User userinfo = User.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


    public RentBook() {
        setLayout(new BorderLayout());

        // 테이블 생성
        bookTable = new JTable();
        model = new DefaultTableModel();
        model.addColumn("제어번호");
        model.addColumn("제목");
        model.addColumn("저자");
        model.addColumn("발행처");
        model.addColumn("발행년도");
        model.addColumn("대여");
        model.addColumn("ID");
        model.addColumn("대여일자");
        model.addColumn("반납일자");
        bookTable.setModel(model);

        // JScrollPane 생성
        scrollPane = new JScrollPane(bookTable);


        // 이전 버튼 생성
        JButton backButton = new JButton("뒤로 가기");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // LibrarySystem으로 돌아갑니다.
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(RentBook.this);
                frame.dispose();
                frame.setContentPane(new LibrarySystem());
                frame.revalidate();
            }
        });

        add(backButton, BorderLayout.NORTH);
        //빌린 목록
        refresh();

        // 입력창과 검색 버튼 생성
        JPanel searchPanel = new JPanel();
        JLabel controlNumberLabel = new JLabel("대여하실 책의 제어번호를 입력해주세요: ");
        inputTextField = new JTextField(10);
        rentButton = new JButton("빌리기");
        rentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchBook();
            }
        });
        searchPanel.add(controlNumberLabel);
        searchPanel.add(inputTextField);
        searchPanel.add(rentButton);
        add(searchPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);
    }

    private void searchBook() {
        // 입력창에서 입력받은 책의 제어번호를 가져옵니다.
        String bookControlNumber = inputTextField.getText();
        System.out.println("입력값 확인 중 -> " + bookControlNumber);
        int rentcol = 5;
        String rentInfo = "";
        Calendar c = Calendar.getInstance();
        //대여일자와 반납일자 설정
        String today = sdf.format(c.getTime());
        c.add(Calendar.DATE, userinfo.getLoanLimit());
        String stDate = sdf.format(c.getTime());

        boolean rented = true, bookCheck = false;         // 대여 가능한 책만 파일 수정, bookCheck가 true면 존재하는 책
        String temp = "";
        String temp2 = "";

        try {
            // 파일에서 데이터 읽기
            String filePath = System.getProperty("user.dir") + "/src/resources/Book_List.csv";
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            StringBuilder sb = new StringBuilder();
            String line = "";

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                if(data[1].charAt(0) == '\"')
                    data[1] = data[1].replaceAll("\"", "");


                // 입력된 제어번호와 일치한 책 정보 추출
                System.out.println("도서 정보 -> " + bookControlNumber + "\n현재 도서 정보 -> " + data[0].replaceAll("\"", ""));
                if(data[0].equals(bookControlNumber)) {
                    bookCheck = true;
                    // 책이 이미 대여 중..
                    if(data[rentcol].equals("대여 중")) {
                        JOptionPane.showMessageDialog(null, "해당 도서는 이미 대여 중입니다");
                        break;
                    } else {
                        rented = false;
                        data[rentcol] = "대여 중";
                        for(int i=0;i<data.length;i++) {
                            if(data[i].contains(","))
                                temp2+="\"\"\""+data[i]+"\"\"\",";
                            else
                                temp2+=data[i]+",";
                        }
                        rentInfo = data[1];
                    }
                    sb.append(line);
                }
                //, 포함한 데이터의 경우 입력하기 전처리
                for(int i=0;i<data.length;i++) {
                    if(data[i].contains(","))
                        temp+="\"\"\""+data[i]+"\"\"\",";
                    else
                        temp+=data[i]+",";
                }

                temp+="\n";
            }
            reader.close();

            // 수정된 내용으로 파일에 쓰기
            if(!rented) {
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath), StandardCharsets.UTF_8));
                writer.write(temp);
                writer.close();
                filePath = System.getProperty("user.dir") + "/src/resources/RentBook_List.csv";
                writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath,true), StandardCharsets.UTF_8));
                writer.write(temp2+userinfo.getID()+","+today+","+stDate+"\n");
                writer.close();
                JOptionPane.showMessageDialog(null, "\n도서명: " + rentInfo.replaceAll("\"", "") + "\n\n대여가 완료되었습니다");
                System.out.println("파일 수정이 완료되었습니다!!!");
                refresh();
            } else {
                if(!bookCheck)
                    JOptionPane.showMessageDialog(null, "존재하지 않는 도서입니다.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void refresh() {

        model.setRowCount(0);
        try {
            // 파일에서 데이터 읽기
            String filePath = System.getProperty("user.dir") + "/src/resources/RentBook_List.csv";
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                if(data.length>0) {
                    if(data[1].charAt(0) == '\"')
                        data[1] = data[1].replaceAll("\"","");
                    if(data[6].equals(userinfo.getID()))
                        model.addRow(data);
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}