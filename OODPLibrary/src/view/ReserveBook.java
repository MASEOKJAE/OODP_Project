package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

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
public class ReserveBook extends JPanel {
    private JTextField inputTextField;
    private JButton rentButton;
    private JTable bookTable;
    private DefaultTableModel model;
    private JScrollPane scrollPane;
    private User userinfo = User.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


    public ReserveBook() {
        setLayout(new BorderLayout());

        // 테이블 생성
        bookTable = new JTable();
        model = new DefaultTableModel();
        model.addColumn("제어번호");
        model.addColumn("제목");
        model.addColumn("저자");
        model.addColumn("발행처");
        model.addColumn("발행년도");
        model.addColumn("ID");
        model.addColumn("예약일자");
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
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(ReserveBook.this);
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
        JLabel controlNumberLabel = new JLabel("예약하실 책의 제어번호를 입력해주세요: ");
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

    public int getLastRowNumber(String filePath) {
        int lastRowNumber = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lastRowNumber++;
            }
        } catch (IOException e) {
            System.out.println("Error occurred while reading the CSV file: " + e.getMessage());
        }

        return lastRowNumber;
    }
    private void searchBook() {
        // 입력창에서 입력받은 책의 제어번호를 가져옵니다.
        String bookControlNumber = inputTextField.getText();
        System.out.println("입력값 확인 중 -> " + bookControlNumber);
        // ReserveBook_List에 담을 정보들
        String[] storeInfo = new String[9];
        String rentInfo = "";
        Calendar c = Calendar.getInstance();
        //대여일자와 반납일자 설정
        String today = sdf.format(c.getTime());
        c.add(Calendar.DATE, +7);
        String stDate = sdf.format(c.getTime());

        boolean reserved = true, bookCheck = false;         // 대여 가능한 책만 파일 수정, bookCheck가 true면 존재하는 책
        String temp = "";
        String temp2 = "";

        try {
            // 파일에서 데이터 읽기
            String reservePath = System.getProperty("user.dir") + "/src/resources/ReserveBook_List.csv";
            BufferedReader reader = new BufferedReader(new FileReader(reservePath));
            StringBuilder sb = new StringBuilder();
            String line = "";

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                if(data[1].charAt(0) == '\"')
                    data[1] = data[1].replaceAll("\"", "");


                // 입력된 제어번호와 일치한 책 정보 추출
                System.out.println("도서 정보 -> " + bookControlNumber + "\nReserve Book 현재 도서 정보 -> " + data[1].replaceAll("\"", ""));
                if(data[0].equals(bookControlNumber)) {
                    System.out.println("Reserve!! 에서 일치 항목 발생!!!");
                    bookCheck = true;
                    reserved = false;
                    JOptionPane.showMessageDialog(null, "해당 도서는 이미 예약 되었습니다");
                    break;
                    // 책이 예약 리스트에 존재 하지 않을 경우 처리!!
                }
            }
            reader.close();

            // 만약, 책이 ReserveBook_List에 없을 경우
            if(!bookCheck) {
                String rentPath = System.getProperty("user.dir") + "/src/resources/RentBook_List.csv";
                reader = new BufferedReader(new FileReader(rentPath));
                sb = new StringBuilder();
                line = "";

                while ((line = reader.readLine()) != null) {
                    String[] data = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                    if(data[1].charAt(0) == '\"')
                        data[1] = data[1].replaceAll("\"", "");

                    // 단어 결합자 제거
                    for (int i = 0; i < data[0].length(); i++) {
                        System.out.print("[" + data[0].charAt(i) + "]");
                    }

                    if(data[0].substring(1).equals(bookControlNumber)) {
                        bookCheck = true;
                        storeInfo = data;
                        System.out.println("!!Rent!! 에서 일치 항목 발생!!!");
                        // 책이 예약 리스트에 존재 하지 않을 경우 처리!!
                    }
                }
                reader.close();
            }

            // 수정된 내용으로 파일에 쓰기
            if (reserved && bookCheck) {
                try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(reservePath, true)))) {
                    writer.print(storeInfo[0]);
                    writer.print("," + storeInfo[1]);
                    writer.print("," + storeInfo[2]);
                    writer.print("," + storeInfo[3]);
                    writer.print("," + storeInfo[4]);
                    writer.print("," + userinfo.getID());

                    // 예약자 대여일, 예약자 반납일
                    // 대여일
                    String dateString = storeInfo[8];

                    // 스트링 값을 Date 객체로 변환
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date date;
                    try {
                        date = dateFormat.parse(dateString);
                    } catch (Exception e) {
                        e.printStackTrace();
                        return;
                    }

                    // Calendar 객체를 생성하고 날짜를 설정
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(date);

                    // 날짜에 사용자에 따른 대여 가능일만큼 더함
                    calendar.add(Calendar.DAY_OF_MONTH, userinfo.getLoanLimit());

                    // 변경된 날짜를 다시 Date 객체로 변환
                    Date newDate = calendar.getTime();

                    // 변환된 Date 값을 다시 스트링으로 변환 -> 반납일
                    String newDateString = dateFormat.format(newDate);

                    writer.print("," + dateString);
                    writer.println("," + newDateString);
                    writer.close();

                    JOptionPane.showMessageDialog(null, "\n도서명: " + storeInfo[1].replaceAll("\"", "") + "\n\n예약이 완료되었습니다");
                    refresh();
                    System.out.println("Data added successfully to the ReserveBook_List.csv file.");
                } catch (IOException e) {
                    System.out.println("Error occurred while adding data to the ReserveBook_List.csv file: " + e.getMessage());
                }
            } else if (!bookCheck) {
                JOptionPane.showMessageDialog(null, "예약 가능한 도서가 아닙니다");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void refresh() {

        model.setRowCount(0);
        try {
            // 파일에서 데이터 읽기
            String filePath = System.getProperty("user.dir") + "/src/resources/ReserveBook_List.csv";
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                if(data.length>0) {
                    if(data[1].charAt(0) == '\"')
                        data[1] = data[1].replaceAll("\"","");
                    if(data[5].equals(userinfo.getID()))
                        model.addRow(data);
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}