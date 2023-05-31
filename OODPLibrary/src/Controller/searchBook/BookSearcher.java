package Controller.searchBook;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.table.DefaultTableModel;

public abstract class BookSearcher {
	
    public final void search(String query, DefaultTableModel model) {
        
    	model.setNumRows(0);
        try {
            // 파일에서 데이터 읽기
            String filePath = System.getProperty("user.dir") + "/src/Model/resources/Book_List.csv";
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line = reader.readLine();
            while ((line = reader.readLine()) != null) {
            	String[] data = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
            	if(data[1].charAt(0) == '\"')
            		data[1] = data[1].substring(3, data[1].length()-3); 
            	//일부 포함이어도 반환
            	searchType(data, query, model);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
    protected abstract void searchType(String[] data, String query, DefaultTableModel model);
}
