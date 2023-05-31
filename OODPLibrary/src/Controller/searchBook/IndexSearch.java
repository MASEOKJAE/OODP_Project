package Controller.searchBook;

import javax.swing.table.DefaultTableModel;

public class IndexSearch extends BookSearcher{
	
	@Override
	protected void searchType(String[] data, String query, DefaultTableModel model) {
		if(data[0].equals(query))
            model.addRow(data);
	}
}