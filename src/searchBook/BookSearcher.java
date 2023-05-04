package searchBook;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class BookSearcher {
	private SearchStrategy searchStrategy;
    
    public BookSearcher(SearchStrategy searchStrategy) {
        this.searchStrategy = searchStrategy;
    }
    
    public void setSearchStrategy(SearchStrategy searchStrategy) {
        this.searchStrategy = searchStrategy;
    }
    
    public DefaultTableModel search(String query) {
        // 현재 선택된 검색 알고리즘을 실행합니다.
        return searchStrategy.search(query);
    }

}
