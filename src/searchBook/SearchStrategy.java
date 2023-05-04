package searchBook;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public interface SearchStrategy {
	public DefaultTableModel search(String query);
}
