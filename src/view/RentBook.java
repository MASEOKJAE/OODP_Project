package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
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
        JLabel controlNumberLabel = new JLabel("대여하실 책의 제어번호를 입력해주세요.");
        inputTextField = new JTextField(10);
        rentButton = new JButton("검색");
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

    }
}
