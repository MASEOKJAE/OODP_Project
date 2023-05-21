package searchBook;

import javax.swing.table.DefaultTableModel;

public class IndexSearch implements SearchStrategy {
	
	@Override
	public void search(String[] data, String query, DefaultTableModel model) {
		if(data[0].equals(query))
            model.addRow(data);
	}
}