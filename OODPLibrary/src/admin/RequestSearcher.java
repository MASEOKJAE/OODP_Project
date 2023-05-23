package admin;

import java.io.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class RequestSearcher {
    // 요청된 책 정보 담는 변수, 0 ~ 4까지 순서대로 "제어번호" ~ "발행년도"
    private String[] bookInfo = new String[5];

    public void search(String type, String query, DefaultTableModel model) {
        boolean infoCheck = false;
        // 모달에서 일치하는 책 정보 가져오기
        for(int i=0; i<model.getRowCount(); i++) {
            Object value = null;
            for(int j=0; j<model.getColumnCount(); j++) {
                if(j == 0) {
                    value = model.getValueAt(i, j);
                    if(value.equals(query))
                        infoCheck = true;
                    else
                        break;
                }
                // 일치하는 책 정보가 있을 경우
                if(infoCheck) {
                    value = model.getValueAt(i, j);
                    bookInfo[j] = (String) value;
                }
            }
        }
        // 일치하는 책 정보가 없었을 경우
        if(!infoCheck)
            JOptionPane.showMessageDialog(null, "존재하지 않은 요청 정보입니다");
        // 선택한 드롭다운 박스 내옹에 따라 처리
        if(type.equals("등록") && infoCheck) {
            addBookLibrary();
        } else if(type.equals("요청 거절") && infoCheck) {
            JOptionPane.showMessageDialog(null, "해당 도서 요청이 거절되었습니다");
        }

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

    public void addBookLibrary() {
        String filePath = System.getProperty("user.dir") + "/src/resources/Book_List.csv";

        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(filePath, true)))) {
            // 맨 마지막 인덱스 가져오기
            int newIndex = getLastRowNumber(filePath);

            // 데이터 추가
            writer.print(newIndex);
            writer.print(",");
            writer.print(bookInfo[1]);
            writer.print(",");
            writer.print(bookInfo[2]);
            writer.print(",");
            writer.print(bookInfo[3]);
            writer.print(",");
            writer.print(bookInfo[4]);
            writer.print(",");
            writer.print("대여 가능");
            writer.close();

            JOptionPane.showMessageDialog(null, "도서명: " + bookInfo[1] + "\n\n" + "도서관에 등록 되었습니다");
            System.out.println("Data added successfully to the Request_List.csv file.");
        } catch (IOException e) {
            System.out.println("Error occurred while adding data to the Request_List.csv file: " + e.getMessage());
        }
    }
}
