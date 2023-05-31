package Controller.searchBook;

import javax.swing.table.DefaultTableModel;

public class SearchType {
	
	private BookSearcher searcher;
	
	public void search(String type, String query, DefaultTableModel model) {
		// 현재 선택된 검색 알고리즘을 실행합니다.
    	switch (type) {
	        case "제어번호":
	        	searcher = new IndexSearch();
	            break;
	        case "제목":
	        	searcher = new TitleSearch();                            	
	            break;
	        case "저자":
	        	searcher = new AuthorSearch();                            	
	            break;
	        case "발행처":
	        	searcher = new PublisherSearch();                            	
	            break;
	        case "발행년도":
	        	searcher = new YearSearch();                            	
	            break;
	        default:
	            break;
    	}
    	searcher.search(query, model);
	}
}
