package searchBook;

import javax.swing.table.DefaultTableModel;

public class AuthorSearch implements SearchStrategy {
	
	@Override
	public void search(String[] data, String query, DefaultTableModel model) {
		if(data[2].contains(query))
            model.addRow(data);
	}
}