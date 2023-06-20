package Controller.system;

public class Light {
    private static Light instance;
    private DarkState darkState;
    private boolean isDarkMode;

    public Light() {
        darkState = new OFF();
    }

    public static Light getInstance() {
        if (instance == null) {
            instance = new Light();
        }
        return instance;
    }

    public void setDarkState(DarkState darkState) {
        this.darkState = darkState;
        this.isDarkMode = (darkState instanceof ON);
    }

    public boolean isDarkMode() {
        return isDarkMode;
    }

    public void on_button_pushed() {
        System.out.println("On 버튼 눌림!!");
        darkState.on_button_pushed(this);
    }

    public void off_button_pushed() {
        System.out.println("Off 버튼 눌림!!");
        darkState.off_button_pushed(this);
    }
}
