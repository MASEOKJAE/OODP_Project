package searchBook;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class AuthorSearch implements SearchStrategy {
	

	@Override
	public DefaultTableModel search(String query) {
		// TODO Auto-generated method stub
		JTable bookTable = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("제어번호");
        model.addColumn("제목");
        model.addColumn("저자");
        model.addColumn("발행처");
        model.addColumn("발행년도");
        model.addColumn("대여");
        bookTable.setModel(model);

        try {
            // 파일에서 데이터 읽기
            String filePath = System.getProperty("user.dir") + "/src/resources/Book_List.csv";
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line = reader.readLine();
            while ((line = reader.readLine()) != null) {
            	String[] data = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
            	if(data[1].charAt(0) == '\"')
            		data[1] = data[1].substring(1, data[1].length()-1); 
            	//일치시 반환
            	if(data[2].equals(query))
            		model.addRow(data);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return model;
	}

}
