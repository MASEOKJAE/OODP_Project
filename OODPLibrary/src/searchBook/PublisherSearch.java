package searchBook;

import javax.swing.table.DefaultTableModel;

public class PublisherSearch implements SearchStrategy {
	
	@Override
	public void search(String[] data, String query, DefaultTableModel model) {
		if(data[3].contains(query))
            model.addRow(data);
	}
}