package Logger;

enum loglevel {
    LEVEL1,
    LEVEL2,
    LEVEL3
}

abstract class loghandler {
    public loghandler next;

    public void setNext(loghandler next) {
        this.next = next;
    }

    public abstract void logMessage(String Message, loglevel level);
}

class Level1Handler extends loghandler {
    @Override
    public void logMessage(String message, loglevel level) {
        if (level == loglevel.LEVEL1) {
            System.out.println("Levle 1 logger:" + message);
        } else {
            next.logMessage(message, level);
        }
    }
}

class Level2Handler extends loghandler {
    @Override
    public void logMessage(String message, loglevel level) {
        if (level == loglevel.LEVEL2) {
            System.out.println("Levle 2 logger:" + message);
        } else {
            next.logMessage(message, level);
        }
    }
}

class Level3Handler extends loghandler {
    @Override
    public void logMessage(String message, loglevel level) {
        if (level == loglevel.LEVEL3) {
            System.out.println("Levle 3 logger:" + message);
        } else {
            next.logMessage(message, level);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        loghandler log1 = new Level1Handler();
        loghandler log2 = new Level2Handler();
        loghandler log3 = new Level3Handler();

        log1.setNext(log2);
        log2.setNext(log3);

        log1.logMessage("This is Log 1", loglevel.LEVEL1);
        log1.logMessage("This is Log 2", loglevel.LEVEL2);
        log1.logMessage("This is Log 3", loglevel.LEVEL3);
    }
}