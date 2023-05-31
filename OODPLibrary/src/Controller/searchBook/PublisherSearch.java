package Controller.searchBook;

import javax.swing.table.DefaultTableModel;

public class PublisherSearch extends BookSearcher{
	
	@Override
	protected void searchType(String[] data, String query, DefaultTableModel model) {
		if(data[3].contains(query))
            model.addRow(data);
	}
}