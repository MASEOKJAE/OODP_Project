import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookCart {
    private Map<Integer, String> bookMap;
    private List<Integer> cartList;

    public BookCart() {
        bookMap = new HashMap<>();
        cartList = new ArrayList<>();
    }

    public void showList() {
        System.out.println("=== Book Cart List ===");
        for (int id : cartList) {
            System.out.println("ID: " + id + ", Title: " + bookMap.get(id));
        }
        System.out.println("======================");
    }

    public void loanList() {
        System.out.println("=== Loan Book List ===");
        for (int id : cartList) {
            System.out.println("Loaning book ID: " + id + ", Title: " + bookMap.get(id));
        }
        System.out.println("======================");
    }

    public void removeList() {
        System.out.println("=== Remove Book List ===");
        for (int id : cartList) {
            System.out.println("Removing book ID: " + id + ", Title: " + bookMap.get(id));
        }
        cartList.clear();
        System.out.println("========================");
    }

    public void addBookCart(int bookId) {
        if (bookMap.containsKey(bookId)) {
            cartList.add(bookId);
            System.out.println("Book ID: " + bookId + " has been added to the cart.");
        } else {
            System.out.println("Book ID: " + bookId + " does not exist in the library.");
        }
    }

    public void addBook(int bookId, String title) {
        bookMap.put(bookId, title);
    }
}
