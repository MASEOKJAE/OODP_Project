package searchBook;

import javax.swing.table.DefaultTableModel;

public class YearSearch implements SearchStrategy {
	
	@Override
	public void search(String[] data, String query, DefaultTableModel model) {
		if(data[4].equals(query))
            model.addRow(data);
	}
}