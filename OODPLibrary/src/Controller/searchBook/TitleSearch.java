package Controller.searchBook;

import javax.swing.table.DefaultTableModel;

public class TitleSearch extends BookSearcher{
	
	@Override
	protected void searchType(String[] data, String query, DefaultTableModel model) {
		if(data[1].contains(query))
            model.addRow(data);
	}
}