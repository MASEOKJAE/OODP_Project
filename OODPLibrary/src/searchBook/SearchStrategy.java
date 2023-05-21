package searchBook;

import javax.swing.table.DefaultTableModel;

public interface SearchStrategy {
	public abstract void search(String[] data, String query, DefaultTableModel model);
}
