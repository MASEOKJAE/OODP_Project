package Controller.searchBook;

import javax.swing.table.DefaultTableModel;

public class AuthorSearch extends BookSearcher{
	
	@Override
	protected void searchType(String[] data, String query, DefaultTableModel model) {
		if(data[2].contains(query))
            model.addRow(data);
	}
}