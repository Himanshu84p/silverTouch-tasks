package UIComponents;

interface UI {
    public void notification();

    public void chatbox();
}

class mobile implements UI {
    @Override
    public void notification() {
        System.out.println("Mobile notification UI!!");
    }

    @Override
    public void chatbox() {
        System.out.println("Mobile chatbox UI");
    }
}

class desktop implements UI {
    @Override
    public void notification() {
        System.out.println("Desktop notification UI!!");
    }

    @Override
    public void chatbox() {
        System.out.println("Desktop chatbox UI");
    }
}

interface UIFactory {
    UI createComponent();
}

class mobileFactory implements UIFactory {
    @Override
    public UI createComponent() {
        return new mobile();
    }
}

class desktopFactory implements UIFactory {
    @Override
    public UI createComponent() {
        return new desktop();
    }
}

public class Main {
    public static void main(String[] args) {
        UIFactory mobileFactory = new mobileFactory();
        UI mobile = mobileFactory.createComponent();
        mobile.chatbox();
        mobile.notification();

        UIFactory deskFactory = new desktopFactory();
        UI desktop = deskFactory.createComponent();
        desktop.chatbox();
        desktop.notification();
    }
}
