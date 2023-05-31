package Controller.searchBook;

import javax.swing.table.DefaultTableModel;

public class YearSearch extends BookSearcher{
	
	@Override
	protected void searchType(String[] data, String query, DefaultTableModel model) {
		if(data[4].equals(query))
            model.addRow(data);
	}
}