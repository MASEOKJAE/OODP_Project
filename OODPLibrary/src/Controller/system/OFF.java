package Controller.system;

public class OFF implements DarkState {
    private static OFF off = new OFF();
    OFF() { }

    public static OFF getInstance() {
        return off;
    }
    public void on_button_pushed(Light light) {
        System.out.println("++++++++ 다크모드 ON 상태 ++++++++");
        light.setDarkState(ON.getInstance());
    }
    public void off_button_pushed(Light light) {
        System.out.println("++++++++ 다크모드 OFF 상태 ++++++++");
    }
}
