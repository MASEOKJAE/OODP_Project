package system.controller;

import javax.swing.*;

import view.LoginDialog;
import view.SearchBook;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LibrarySystem extends JFrame {
	private boolean isLoginSuccess = false;
	private JButton searchButton; 		// "도서 검색" 버튼
	private JButton requestButton; 		// "도서 요청" 버튼
	private JButton rentButton; 		// "도서 빌림" 버튼
	private JButton reserveButton;		// "도서 예약" 버튼
	private JButton loginButton; 		// "로그인" 버튼

    public LibrarySystem() {
        // 윈도우 설정
        setTitle("도서관 관리 시스템");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // 레이아웃 설정
        setLayout(new BorderLayout());

     // 도서관 이미지 추가
        JPanel northPanel = new JPanel(new BorderLayout());
        ImageIcon libraryImage = new ImageIcon(getClass().getResource("/resources/LibLogo.png"));
        Image scaledImage = libraryImage.getImage().getScaledInstance(200, -1, Image.SCALE_SMOOTH); // 이미지 크기 조정
        libraryImage = new ImageIcon(scaledImage);
        JLabel libraryImageLabel = new JLabel(libraryImage);
        JPanel logoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        logoPanel.add(libraryImageLabel);
        northPanel.add(logoPanel, BorderLayout.WEST);

        // 로그인 버튼 추가
        this.loginButton = new JButton("로그인");
        JPanel loginPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        loginPanel.add(loginButton);
        northPanel.add(loginPanel, BorderLayout.EAST);
        add(northPanel, BorderLayout.NORTH);
        
        // login dialog 연
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // LoginDialog 생성
                LoginDialog loginDialog = new LoginDialog(LibrarySystem.this);
                loginDialog.setVisible(true);
                boolean check = loginDialog.getCheck();
                
                setLoginSuccess(check);
            }
        });

        // 메뉴 버튼 추가
        JPanel menuButtonPanel = new JPanel(new GridLayout(1, 5));
        this.searchButton = new JButton("도서 검색");
        searchButton.setPreferredSize(new Dimension(200, 50)); // JButton 크기 설정
        this.requestButton = new JButton("도서 요청");
        requestButton.setPreferredSize(new Dimension(200, 50)); // JButton 크기 설정
        menuButtonPanel.add(requestButton);
        this.rentButton = new JButton("도서 대여");
        rentButton.setPreferredSize(new Dimension(200, 50)); // JButton 크기 설정
        this.reserveButton = new JButton("도서 예약");
        reserveButton.setPreferredSize(new Dimension(200, 50)); // JButton 크기 설정
        JButton returnButton = new JButton("도서 반납");
        returnButton.setPreferredSize(new Dimension(200, 50)); // JButton 크기 설정
        menuButtonPanel.add(searchButton);
        menuButtonPanel.add(requestButton);
        menuButtonPanel.add(rentButton);
        menuButtonPanel.add(reserveButton);
        menuButtonPanel.add(returnButton);
        add(menuButtonPanel, BorderLayout.CENTER);
        
        // 책 검색 페이지 연
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // SearchBookPanel 생성
                SearchBook searchPanel = new SearchBook();
                // 기존 창의 컨텐트 팬을 SearchBookPanel로 교체합니다.
                setContentPane(searchPanel);
                // 기존 창을 다시 그리도록 합니다.
                revalidate();
                repaint();
            }
        });

        
        // updateMenuButton 호출
        updateMenuButton();

        // 윈도우 표시
        setVisible(true);
    }
    public boolean isLoginSuccess() {
        return isLoginSuccess;
    }

    // isLoginSuccess setter
    public void setLoginSuccess(boolean success) {
        isLoginSuccess = success;
        updateMenuButton();
    }

    // 메뉴 버튼 활성화/비활성화 메서드
    public void updateMenuButton() {
    	 // "도서 요청", "도서 대여", "도서 예약" 버튼 비활성화
        requestButton.setEnabled(isLoginSuccess);
        rentButton.setEnabled(isLoginSuccess);
        reserveButton.setEnabled(isLoginSuccess);

        // 로그아웃 버튼 활성화
        loginButton.setText(isLoginSuccess ? "로그아웃" : "로그인");
    }
}