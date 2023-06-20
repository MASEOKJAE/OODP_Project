package View;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import Controller.system.LibrarySystem;
import Model.User;

@SuppressWarnings("serial")
public class ReturnBook extends JPanel {
    private JTextField inputTextField;
    private JButton returnButton;
    private DefaultTableModel model;
    private User userinfo = User.getInstance();


    public ReturnBook() {
        setLayout(new BorderLayout());



        // 이전 버튼 생성
        JButton backButton = new JButton("뒤로 가기");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // LibrarySystem으로 돌아갑니다.
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(ReturnBook.this);
                frame.dispose();
                frame.setContentPane(new LibrarySystem());
                frame.revalidate();
            }
        });

        add(backButton, BorderLayout.NORTH);

        // 입력창과 검색 버튼 생성
        JPanel searchPanel = new JPanel();
        JLabel controlNumberLabel = new JLabel("반납하실 책의 제어번호를 입력해주세요: ");
        inputTextField = new JTextField(10);
        returnButton = new JButton("반납");
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchBook();
            }
        });
        searchPanel.add(controlNumberLabel);
        searchPanel.add(inputTextField);
        searchPanel.add(returnButton);
        add(searchPanel, BorderLayout.CENTER);
    }

    private void searchBook() {
        // 입력창에서 입력받은 책의 제어번호를 가져옵니다.
        String bookControlNumber = inputTextField.getText();
        System.out.println("입력값 확인 중 -> " + bookControlNumber);
        int rentcol = 5;
        String rentInfo = "";

        boolean rented = false, bookCheck = false;         // 대여 가능한 책만 파일 수정, bookCheck가 true면 존재하는 책
        String temp = "";

        try {
            // 파일에서 데이터 읽기
            String filePath = System.getProperty("user.dir") + "/src/Model/resources/Book_List.csv";
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line = "";

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                if(data[1].charAt(0) == '\"')
                    data[1] = data[1].replaceAll("\"", "");


                // 입력된 제어번호와 일치한 책 정보 추출
                if(data[0].equals(bookControlNumber)) {
                    bookCheck = true;
                    // 책이 이미 대여 중이 아닐 때
                    if(data[rentcol].equals("대여 가능")) {
                        JOptionPane.showMessageDialog(null, "해당 도서는 '대여 중' 상태가 아닙니다");
                        break;
                    } else {
                        rented = true;
                        data[rentcol] = "대여 가능";
                        rentInfo = data[1];
                    }
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
            if(rented) {
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath), StandardCharsets.UTF_8));
                writer.write(temp);
                writer.close();
                temp="";

                filePath = System.getProperty("user.dir") + "/src/Model/resources/RentBook_List.csv";
                reader = new BufferedReader(new FileReader(filePath));

                while ((line = reader.readLine()) != null) {
                    String[] data = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                    if(data.length>0) {
                        if(data[1].charAt(0) == '\"')
                            data[1] = data[1].replaceAll("\"", "");

                        if(data[0].equals(bookControlNumber)) {

                        }else {
                            for(int i=0;i<data.length;i++) {
                                if(data[i].contains(","))
                                    temp+="\"\"\""+data[i]+"\"\"\",";
                                else
                                    temp+=data[i]+",";
                            }

                            temp+="\n";
                        }
                    }
                }

                reader.close();

                writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath), StandardCharsets.UTF_8));
                writer.write(temp);
                writer.close();
                JOptionPane.showMessageDialog(null, "\n도서명: " + rentInfo.replaceAll("\"", "") + "\n\n반납이 완료되었습니다");
                System.out.println("파일 수정이 완료되었습니다!!!");
            } else {
                if(!bookCheck)
                    JOptionPane.showMessageDialog(null, "존재하지 않는 도서입니다.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}