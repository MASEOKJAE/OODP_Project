package View;

import Controller.admin.RequestAdmin;
import Controller.LibrarySystem;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminPage extends JPanel {
    private JButton requestAcceptButton; // "도서 요청 리스트" 버튼
    private JButton bookManageButton;	// "책 관리" 버튼
    private JButton backButton;         // "뒤로 가기" 버튼

    public AdminPage() {
        setDisplay();
        homeSet();
    }
    private void setDisplay() {
        JPanel northPanel = new JPanel(new BorderLayout());

        //뒤로가기 버튼 생성
        backButton = new JButton("뒤로 가기");

        JPanel backPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        backPanel.add(backButton);

        northPanel.add(backPanel, BorderLayout.WEST);
        add(northPanel, BorderLayout.WEST);

        // 관리자 버튼 추가
        this.requestAcceptButton = new JButton("도서 요청 목록");
        requestAcceptButton.setPreferredSize(new Dimension(200, 50)); // JButton 크기 설정
        JPanel adminPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        adminPanel.add(requestAcceptButton);
        northPanel.add(adminPanel, BorderLayout.CENTER);
        add(northPanel, BorderLayout.NORTH);
    }
    private void homeSet() {
        // 뒤로가기 버튼
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // LibrarySystem으로 돌아갑니다.
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(AdminPage.this);
                frame.dispose();
                frame.setContentPane(new LibrarySystem());
                frame.revalidate();
            }
        });
        // 책 요청 페이지
        requestAcceptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // requestPanel 생성
                RequestAdmin requestPanel = new RequestAdmin();
                // 현재 패널의 최상위 JFrame 컨테이너를 찾습니다.
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(AdminPage.this);
                // 기존 컨텐트 팬을 제거하고, requestPanel을 추가합니다.
                frame.setContentPane(requestPanel);
                // 기존 창을 다시 그리도록 합니다.
                frame.revalidate();
                frame.repaint();
            }
        });

    }
}
