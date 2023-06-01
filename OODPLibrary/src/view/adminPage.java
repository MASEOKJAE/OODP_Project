package View;

import Controller.LibrarySystem;
import Controller.admin.RequestAdmin;
import Controller.LibrarySystem;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// ModeState 인터페이스
interface ModeState {
    void applyMode(AdminPage adminPage);
}

// LightModeState 클래스 (라이트 모드)
class LightModeState implements ModeState {
    @Override
    public void applyMode(AdminPage adminPage) {
        adminPage.setPanelBackground(Color.WHITE);
    }
}

// DarkModeState 클래스 (다크 모드)
class DarkModeState implements ModeState {
    @Override
    public void applyMode(AdminPage adminPage) {
        adminPage.setPanelBackground(Color.BLACK);
    }
}

// DarkModeButton 클래스
class DarkModeButton extends JButton {
    private AdminPage adminPage;
    private ModeState lightModeState;
    private ModeState darkModeState;
    private ModeState currentState;

    public DarkModeButton(AdminPage adminPage) {
        this.adminPage = adminPage;
        lightModeState = new LightModeState();
        darkModeState = new DarkModeState();
        currentState = lightModeState;

        setText("다크 모드");
        addActionListener(e -> toggleMode()); // ActionListener 추가
    }

    private void toggleMode() {
        currentState.applyMode(adminPage);
        currentState = (currentState == lightModeState) ? darkModeState : lightModeState;
        setText((currentState == lightModeState) ? "다크 모드" : "라이트 모드");
    }
}

public class AdminPage extends JPanel {
    private JButton backButton;             // "뒤로 가기" 버튼
    private JButton requestAcceptButton;     // "도서 요청 리스트" 버튼
    private JButton darkModeButton;          // "다크 모드" 버튼

    public AdminPage() {
        setDisplay();
        homeSet();
    }

    private void setDisplay() {
        JPanel northPanel = new JPanel(new BorderLayout());
        setLayout(new BorderLayout());

        // 뒤로가기 버튼 생성
        backButton = new JButton("뒤로 가기");

        JPanel backPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        backPanel.add(backButton);

        add(backPanel, BorderLayout.NORTH);

        northPanel.add(backPanel, BorderLayout.WEST);
        add(northPanel, BorderLayout.WEST);

        // 관리자 버튼 추가
        this.requestAcceptButton = new JButton("도서 요청 목록");
        requestAcceptButton.setPreferredSize(new Dimension(200, 50)); // JButton 크기 설정
        JPanel adminPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        adminPanel.add(requestAcceptButton);
        northPanel.add(adminPanel, BorderLayout.CENTER);
        add(northPanel, BorderLayout.NORTH);


        darkModeButton = new DarkModeButton(this);
        adminPanel.add(darkModeButton);

        add(adminPanel, BorderLayout.CENTER);
    }

    private void homeSet() {
        // 뒤로가기 버튼
        backButton.addActionListener(e -> {
            // LibrarySystem으로 돌아갑니다.
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(AdminPage.this);
            frame.dispose();
            frame.setContentPane(new LibrarySystem());
            frame.revalidate();
        });

        // 책 요청 페이지
        requestAcceptButton.addActionListener(e -> {
            // requestPanel 생성
            RequestAdmin requestPanel = new RequestAdmin();
            // 현재 패널의 최상위 JFrame 컨테이너를 찾습니다.
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(AdminPage.this);
            // 기존 컨텐트 팬을 제거하고, requestPanel을 추가합니다.
            frame.setContentPane(requestPanel);
            // 기존 창을 다시 그리도록 합니다.
            frame.revalidate();
            frame.repaint();
        });
    }

    public void setPanelBackground(Color color) {
        setBackground(color);
        setComponentsBackground(this, color);
    }

    private void setComponentsBackground(Container container, Color color) {
        for (Component component : container.getComponents()) {
            if (component instanceof Container) {
                setComponentsBackground((Container) component, color);
            }
            component.setBackground(color);
        }
    }
}