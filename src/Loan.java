public class Loan {
    private Book book;
    private Student borrower;
    private String loan_date;
    private String due_date;

    public Loan(Book book, Student borrower, String loan_date, String due_date) {
        this.book = book;
        this.borrower = borrower;
        this.loan_date = loan_date;
        this.due_date = due_date;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Student getBorrower() {
        return borrower;
    }

    public void setBorrower(Student borrower) {
        this.borrower = borrower;
    }

    public String getLoanDate() {
        return loan_date;
    }

    public void setLoanDate(String loan_date) {
        this.loan_date = loan_date;
    }

    public String getDueDate() {
        return due_date;
    }

    public void setDueDate(String due_date) {
        this.due_date = due_date;
    }
}
