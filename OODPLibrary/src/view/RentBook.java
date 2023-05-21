package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import system.controller.LibrarySystem;

public class RentBook extends JPanel {
    private JTextField inputTextField;
    private JButton rentButton;

    public RentBook() {
        setLayout(new BorderLayout());

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
    }

    private void searchBook() {
    	 // 입력창에서 입력받은 책의 제어번호를 가져옵니다.
        String bookControlNumber = inputTextField.getText();
        System.out.println("입력값 확인 중 -> " + bookControlNumber);
        int rentcol = 5;
        String rentInfo = "";
        boolean rented = true, bookCheck = false;			// 대여 가능한 책만 파일 수정, bookCheck가 true면 존재하는 책
        
        try {
            // 파일에서 데이터 읽기
            String filePath = System.getProperty("user.dir") + "/src/resources/Book_List.csv";
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            StringBuilder sb = new StringBuilder();          
            String line = reader.readLine();
            
            while ((line = reader.readLine()) != null) {
            	String[] data = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
            	if(data[1].charAt(0) == '\"')
            		data[1] = data[1].substring(1, data[1].length()-1);
            	
            	// 입력된 제어번호와 일치한 책 정보 추출
            	System.out.println("도서 정보 -> " + bookControlNumber + "\n현재 도서 정보 -> " + data[0]);
            	if(data[0].equals(bookControlNumber)) {
            		bookCheck = true;
            		// 책이 이미 대여 중..
            		if(data[rentcol].equals("대여 중")) {
            			JOptionPane.showMessageDialog(null, "해당 도서는 이미 대여 중입니다");
            			break;
            		} else {
            			rented = false;
            			data[rentcol] = "대여 중";
            			line = String.join(",", data);
            			rentInfo = data[1];
            		}
            		sb.append(line).append("\n");
            	}
            }
            reader.close();
            
            // 수정된 내용으로 파일에 쓰기
            if(!rented) {
	        	 BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
	             writer.write(sb.toString());
	             writer.close();
	             JOptionPane.showMessageDialog(null, "\n도서명: " + rentInfo + "\n\n대여가 완료되었습니다");
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
