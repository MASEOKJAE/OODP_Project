package Controller.system;

import javax.swing.*;

import Model.User;
//import Controller.system.Light;
import View.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class LibrarySystem extends JFrame {
    private JButton adminButton;        // "관리" 버튼
    private boolean isLoginSuccess;     // 로그인 성공 여부
    private JButton searchButton;       // "도서 검색" 버튼
    private JButton requestButton;      // "도서 요청" 버튼
    private JButton rentButton;         // "도서 빌림" 버튼
    private JButton reserveButton;      // "도서 예약" 버튼

    private JButton returnButton;       // "도서 반납" 버튼
    private JButton loginButton;        // "로그인" 버튼
    private JButton darkModeButton;        // "다크 모드" 버튼
    private Light light;                // 다크 모드 정보

    public LibrarySystem() {
        this.light = Light.getInstance();
        setDisplay();
        addListener();
    }
    private void setDisplay() {
        // 윈도우 설정
        setTitle("도서관 관리 시스템");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel northPanel = new JPanel(new BorderLayout());

        // 레이아웃 설정
        setLayout(new BorderLayout());

        // 도서관 이미지 추가
        ImageIcon libraryImage = new ImageIcon(getClass().getResource("../../Model/resources/LibLogo.png"));
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

        // 관리자 버튼 추가
        this.adminButton = new JButton("관리 페이지");
        JPanel adminPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        adminPanel.add(adminButton);
        northPanel.add(adminPanel, BorderLayout.CENTER);
        add(northPanel, BorderLayout.NORTH);

        // 다크 모드 버튼 추가
        this.darkModeButton = new JButton("다크 모드");

        JPanel darkModePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        darkModePanel.add(darkModeButton);
        add(darkModePanel, BorderLayout.SOUTH);

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
        this.returnButton = new JButton("도서 반납");
        returnButton.setPreferredSize(new Dimension(200, 50)); // JButton 크기 설정
        menuButtonPanel.add(searchButton);
        menuButtonPanel.add(requestButton);
        menuButtonPanel.add(rentButton);
        menuButtonPanel.add(reserveButton);
        menuButtonPanel.add(returnButton);
        add(menuButtonPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private void addListener() {
        // login dialog 연결
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(loginButton.getText().equals("로그인")) {
                    // LoginDialog 생성
                    LoginDialog loginDialog = new LoginDialog(LibrarySystem.this);
                    boolean check = loginDialog.getCheck();

                    setLoginSuccess(check);
                } else {
                    // 싱글톤 패턴 적용
                    User.getInstance().logout();
                    setLoginSuccess(User.auth);
                }
            }
        });

        adminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // adminPanel 생성
                AdminPage adminPanel = new AdminPage();
                // 기존 창의 컨텐트 팬을 adminPanel로 교체합니다.
                setContentPane(adminPanel);
                // 기존 창을 다시 그리도록 합니다.
                revalidate();
                repaint();
            }
        });

        // 책 검색 페이지 연결
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

        requestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // SearchBookPanel 생성
                RequestBook requestPanel = new RequestBook();
                // 기존 창의 컨텐트 팬을 SearchBookPanel로 교체합니다.
                setContentPane(requestPanel);
                // 기존 창을 다시 그리도록 합니다.
                revalidate();
                repaint();
            }
        });

        // 책 빌림 페이지 연결
        rentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // SearchBookPanel 생성
                RentBook rentPanel = new RentBook();
                // 기존 창의 컨텐트 팬을 SearchBookPanel로 교체합니다.
                setContentPane(rentPanel);
                // 기존 창을 다시 그리도록 합니다.
                revalidate();
                repaint();
            }
        });

        // 책 빌림 페이지 연결
        reserveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // SearchBookPanel 생성
                ReserveBook reservePanel = new ReserveBook();
                // 기존 창의 컨텐트 팬을 SearchBookPanel로 교체합니다.
                setContentPane(reservePanel);
                // 기존 창을 다시 그리도록 합니다.
                revalidate();
                repaint();
            }
        });

        // 책 반납 페이지 연결
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // ReturnBookPanel 생성
                ReturnBook returnPanel = new ReturnBook();
                // 기존 창의 컨텐트 팬을 ReturnBookPanel로 교체합니다.
                setContentPane(returnPanel);
                // 기존 창을 다시 그리도록 합니다.
                revalidate();
                repaint();
            }
        });

        // Dark Mode 실행
        darkModeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(darkModeButton.getText().equals("다크 모드")) {
                    light.on_button_pushed();

                    updateDarkModeButton();
                } else {
                    light.off_button_pushed();

                    updateDarkModeButton();
                }
            }
        });

        // 초기 권한값 설정
        setLoginSuccess(User.auth);

        // updateMenuButton 호출
        updateMenuButton();

        // updateDarkModeButton 호출
        updateDarkModeButton();
    }

    // isLoginSuccess setter
    public void setLoginSuccess(boolean success) {
        isLoginSuccess = success;
        updateMenuButton();
    }

    // 메뉴 버튼 활성화/비활성화 메서드
    public void updateMenuButton() {
        // "도서 요청", "도서 대여", "도서 예약" 버튼 비활성화

        if (isLoginSuccess && User.userCheck.equals("admin")) {
            adminButton.setEnabled(true);
        } else {
            adminButton.setEnabled(false);
        }
        requestButton.setEnabled(isLoginSuccess);
        rentButton.setEnabled(isLoginSuccess);
        reserveButton.setEnabled(isLoginSuccess);

        // 로그아웃 버튼 활성화
        loginButton.setText(isLoginSuccess ? "로그아웃" : "로그인");
    }

    // 다크 모드 버튼 업데이트 메서드
    public void updateDarkModeButton() {
        if (light.isDarkMode()) {
            darkModeButton.setText("라이트 모드");
            try {
                UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
                UIManager.put("Button.background", Color.DARK_GRAY);
                UIManager.put("Button.foreground", Color.WHITE);
                UIManager.put("Panel.background", Color.BLACK);

                SwingUtilities.updateComponentTreeUI(this);

                revalidate();
                repaint();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            darkModeButton.setText("다크 모드");
            try {
                System.out.println("여기 라이트 모드 등장이요 ~");
                UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
                UIManager.put("Button.background", Color.LIGHT_GRAY);
                UIManager.put("Button.foreground", Color.BLACK);
                UIManager.put("Panel.background", Color.WHITE);

                SwingUtilities.updateComponentTreeUI(this);

                revalidate();
                repaint();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}