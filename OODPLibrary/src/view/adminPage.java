package view;

import system.controller.LibrarySystem;

import javax.swing.*;
import javax.swing.text.View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class adminPage extends JPanel {
    private JButton requestAceptButton; // "도서 요청 리스트" 버튼
    private JButton bookManageButton;	// "책 관리" 버튼
    private JButton backButton;         // "뒤로 가기" 버튼

    public adminPage() {
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
        this.requestAceptButton = new JButton("도서 요청 목록");
        requestAceptButton.setPreferredSize(new Dimension(200, 50)); // JButton 크기 설정
        JPanel adminPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        adminPanel.add(requestAceptButton);
        northPanel.add(adminPanel, BorderLayout.CENTER);
        add(northPanel, BorderLayout.NORTH);
    }
    private void homeSet() {
        // 뒤로가기 버튼
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // LibrarySystem으로 돌아갑니다.
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(adminPage.this);
                frame.dispose();
                frame.setContentPane(new LibrarySystem());
                frame.revalidate();
            }
        });
        // 책 요청 페이지
        requestAceptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // LibrarySystem으로 돌아갑니다.
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(adminPage.this);
                frame.dispose();
                frame.setContentPane(new LibrarySystem());
                frame.revalidate();
            }
        });
    }
}
