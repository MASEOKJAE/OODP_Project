package Controller.admin;

import java.io.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

// Subject (관찰 대상) 인터페이스
interface RequestSearcherSubject {
    void attach(RequestSearcherObserver observer);
    void detach(RequestSearcherObserver observer);
    void notifyObservers(String[] bookInfo);
}

// Observer (관찰자) 인터페이스
interface RequestSearcherObserver {
    void update(String[] bookInfo);
}

// RequestSearcher 클래스가 Subject 클래스 역할을 수행
public class RequestSearcher implements RequestSearcherSubject {
    private List<RequestSearcherObserver> observers = new ArrayList<>();
    private String[] bookInfo = new String[5];

    public void search(String type, String query, DefaultTableModel model) {
        boolean infoCheck = false;

        for (int i = 0; i < model.getRowCount(); i++) {
            Object value = null;
            for (int j = 0; j < model.getColumnCount(); j++) {
                if (j == 0) {
                    value = model.getValueAt(i, j);
                    if (value.equals(query))
                        infoCheck = true;
                    else
                        break;
                }
                if (infoCheck) {
                    value = model.getValueAt(i, j);
                    bookInfo[j] = (String) value;
                }
            }
        }

        if (!infoCheck) {
            JOptionPane.showMessageDialog(null, "존재하지 않은 요청 정보입니다");
        } else {
            notifyObservers(bookInfo); // Observer에게 변경 사항 알림
        }

        if (type.equals("등록") && infoCheck) {
            addBookLibrary();
        } else if (type.equals("요청 거절") && infoCheck) {
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
        String filePath = System.getProperty("user.dir") + "/src/Model/resources/Book_List.csv";

        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(filePath, true)))) {
            int newIndex = getLastRowNumber(filePath);
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
    // Subject 인터페이스의 메소드 구현
    @Override
    public void attach(RequestSearcherObserver observer) {
        observers.add(observer);
    }

    // Subject 인터페이스의 메소드 구현
    @Override
    public void detach(RequestSearcherObserver observer) {
        observers.remove(observer);
    }

    // Subject 인터페이스의 메소드 구현
    @Override
    public void notifyObservers(String[] bookInfo) {
        for (RequestSearcherObserver observer : observers) {
            observer.update(bookInfo);
        }
    }
}