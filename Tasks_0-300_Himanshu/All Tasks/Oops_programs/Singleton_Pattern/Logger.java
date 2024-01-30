package Oops_programs.Singleton_Pattern;

public class Logger {
    private static Logger instance;

    private Logger() {
    }

    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public void log(String msg) {
        System.out.println("Logs : " + msg);
    }

    public static void main(String[] args) {

    }
}
