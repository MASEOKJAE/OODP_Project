package Model;

import java.time.LocalDate;

public class User {
    private static User instance;
    private String user_id;
    private String user_pw;
    private int due_date;
    public static boolean auth;
    private int loan_limit;
    public static String userCheck;


    private User(String id, String pw, String userCheck) {
        if(!auth)
            resetInfo();
        this.user_id = id;
        this.user_pw = pw;
        this.userCheck = userCheck;
        if(userCheck.equals("student"))
            loan_limit = 3;
        else if(userCheck.equals("professor"))
            loan_limit = 5;
        setIsLogin();
    }

    public static User getInstance(String id, String pw, String userCheck) {
        if(instance == null) {
            instance = new User(id, pw, userCheck);
        }
        return instance;
    }

    public static User getInstance() {

        return instance;
    }

    public String getID() {
        return user_id;
    }

    public void setIsLogin() {
        auth = true;
    }

    public boolean getIsLogin() {
        return auth;
    }

    public int getLoanLimit() {
        return loan_limit;
    }


    // overdueCounter() 메서드
    public int overdueCounter() {
        if (!auth) {  // 사용자가 로그인하지 않은 경우
            return 0;
        }

        int days_overdue = 0;
        if (this.due_date < LocalDate.now().getDayOfYear()) {  // 대출 기한이 지난 경우
            days_overdue = LocalDate.now().getDayOfYear() - this.due_date;
        }
        return days_overdue;
    }

    public void resetInfo() {
        user_id = "";
        user_pw = "";
        due_date = 0;
        loan_limit = 0;
        userCheck = "";
    }

    public void logout() {
        resetInfo();
        auth = false;
        instance = null;
    }

}