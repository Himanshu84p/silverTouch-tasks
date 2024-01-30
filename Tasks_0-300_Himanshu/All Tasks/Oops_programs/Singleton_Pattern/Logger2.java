package Oops_programs.Singleton_Pattern;

public class Logger2 {
    private static Logger2 instance;

    private Logger2() {
    }

    public static Logger2 getInstance() {
        if (instance == null) {
            instance = new Logger2();
        }
        return instance;
    }

    public void log(String msg) {
        System.out.println("Logs : " + msg);
    }

    public static void main(String[] args) {
        Logger2 log1 = Logger2.getInstance();
        log1.log("Msg 1");
        log1.log("Msg 2");

        Logger2.getInstance().log("object log");
    }
}
