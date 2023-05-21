package searchBook;

import javax.swing.table.DefaultTableModel;

public class TitleSearch implements SearchStrategy {
	
	@Override
	public void search(String[] data, String query, DefaultTableModel model) {
		if(data[1].contains(query))
            model.addRow(data);
	}
}