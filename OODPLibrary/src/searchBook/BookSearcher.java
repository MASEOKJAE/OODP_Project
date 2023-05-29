package searchBook;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.table.DefaultTableModel;

public class BookSearcher {
	private SearchStrategy searchStrategy;
    
    public void setSearchStrategy(SearchStrategy searchStrategy) {
        this.searchStrategy = searchStrategy;
    }





	// 수정!!!!
    public void search(String type, String query, DefaultTableModel model) {
        // 현재 선택된 검색 알고리즘을 실행합니다. 	-> 	수정, 다시 스트레티지 패턴 적용할 것
    	switch (type) {
	        case "제어번호":
	        	this.setSearchStrategy(new IndexSearch());                                
	            break;
	        case "제목":
	        	this.setSearchStrategy(new TitleSearch());                            	
	            break;
	        case "저자":
	        	this.setSearchStrategy(new AuthorSearch());                            	
	            break;
	        case "발행처":
	        	this.setSearchStrategy(new PublisherSearch());                            	
	            break;
	        case "발행년도":
	        	this.setSearchStrategy(new YearSearch());                            	
	            break;
	        default:
	            break;
    	}
		// 수정 !!!!







    	model.setNumRows(0);
        try {
            // 파일에서 데이터 읽기
            String filePath = System.getProperty("user.dir") + "/src/resources/Book_List.csv";
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line = reader.readLine();
            while ((line = reader.readLine()) != null) {
            	String[] data = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
            	if(data[1].charAt(0) == '\"')
            		data[1] = data[1].substring(3, data[1].length()-3); 
            	//일부 포함이어도 반환
            	searchStrategy.search(data, query, model);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
}
