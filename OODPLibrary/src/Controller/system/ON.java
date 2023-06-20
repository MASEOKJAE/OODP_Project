package Controller.system;

public class ON implements DarkState {
    private static ON on = new ON();
    private ON() { }

    public static ON getInstance() {
        return on;
    }
    public void on_button_pushed(Light light) {
        System.out.println("++++++++ 다크모드 ON 상태 ++++++++");
    }
    public void off_button_pushed(Light light) {
        System.out.println("++++++++ 다크모드 OFF 상태 ++++++++");
        light.setDarkState(OFF.getInstance());
    }
}
