package view;

import javax.swing.*;

import system.controller.LibrarySystem;
import system.controller.User;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@SuppressWarnings("serial")
public class LoginDialog extends JDialog implements ActionListener {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JRadioButton userCheck[] = new JRadioButton[3];
    private String[] user = {"student", "professor", "admin"}; // 0 = 학생, 1 = 교수, 2 = 도서관 관리
    private LibrarySystem librarySystem;
    private boolean isLoginSuccess = false;


    public LoginDialog(JFrame parent) {
        super(parent, "로그인", true);
        setLayout(new BorderLayout());
        
        // 로그인 폼 생성
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        ButtonGroup group = new ButtonGroup();
        JLabel usernameLabel = new JLabel("사용자 이름:");
        JLabel passwordLabel = new JLabel("비밀번호:");
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        for(int i=0; i<3; i++) {
            userCheck[i] = new JRadioButton(user[i]);
            group.add(userCheck[i]);
            formPanel.add(userCheck[i]);
            userCheck[i].addActionListener(this);
        }
        formPanel.add(usernameLabel);
        formPanel.add(usernameField);
        formPanel.add(passwordLabel);
        formPanel.add(passwordField);
        add(formPanel, BorderLayout.CENTER);

        // 로그인, 취소 버튼 생성
        JButton loginButton = new JButton("로그인");
        JButton cancelButton = new JButton("취소");
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(loginButton);
        buttonPanel.add(cancelButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // 이벤트 리스너 등록
        loginButton.addActionListener(this);
        cancelButton.addActionListener(this);

        // 다이얼로그 크기, 위치 설정 및 보이기
        setSize(300, 150);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    public boolean getCheck() {
    	return isLoginSuccess;
    }

    @Override
    public void actionPerformed(ActionEvent e) {	
    	// 선택된 라디오 버튼을 찾아서 해당하는 사용자 타입을 설정
        String userType = "";
        for (int i = 0; i < 3; i++) {
            if (userCheck[i].isSelected()) {
                userType = user[i];
                break;
            }
        }
        
        
        // 로그인 버튼 클릭 시 처리
        if (e.getActionCommand().equals("로그인")) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            String csvFile = null;
	        
	        if(userType.equals("student"))
	        	csvFile = System.getProperty("user.dir") + "/src/resources/Std_info.csv";
	        else if(userType.equals("professor"))
	        	csvFile = System.getProperty("user.dir") + "/src/resources/Prof_info.csv";
	        else if(userType.equals("admin"))
	        	csvFile = System.getProperty("user.dir") + "/src/resources/Admin_info.csv";
            String line = "";
            String csvSplitBy = ",";
            
            
            try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
                while ((line = br.readLine()) != null) {
                    String[] userInfo = line.split(csvSplitBy);
//                    System.out.println("check!! 아이디 -> " + userInfo[0] + " 비밀번호 -> " + userInfo[1]);
                    if (userInfo.length >= 2 && userInfo[0].equals(username) && userInfo[1].equals(password)) {
                        isLoginSuccess = true;
                        break;
                    }
                }

                if (isLoginSuccess) {
                    // 로그인 성공 시 처리할 내용 추가
                    JOptionPane.showMessageDialog(this, "로그인 성공");
                    // 처리가 완료되면 다이얼로그 닫기
                    User.getInstance(username, password, userType);
                    dispose();
                } else {
                    // 로그인 실패 시 처리할 내용 추가
                    JOptionPane.showMessageDialog(this, "아이디 또는 비밀번호가 일치하지 않습니다");
                }

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else if (e.getActionCommand().equals("취소")) {
            // 취소 버튼 클릭 시 처리
            setVisible(false);
            dispose();
        }
    }
}
