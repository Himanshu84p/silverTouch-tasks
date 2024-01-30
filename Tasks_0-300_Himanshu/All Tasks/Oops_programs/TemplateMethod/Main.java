abstract class Game {

    public final void play() {
        initialize();
        startPlay();
        endPlay();
    }

    abstract void initialize();

    abstract void startPlay();

    abstract void endPlay();
}

class Football extends Game {
    @Override
    void initialize() {
        System.out.println("Football game initialized. Get ready!");
    }

    @Override
    void startPlay() {
        System.out.println("Football game started. Kick off!");
    }

    @Override
    void endPlay() {
        System.out.println("Football game ended. Final whistle!");
    }
}

class Basketball extends Game {
    @Override
    void initialize() {
        System.out.println("Basketball game initialized. Warm up!");
    }

    @Override
    void startPlay() {
        System.out.println("Basketball game started. Tip-off!");
    }

    @Override
    void endPlay() {
        System.out.println("Basketball game ended. Buzzer beater!");
    }
}

public class Main {
    public static void main(String[] args) {

        Game footballGame = new Football();
        footballGame.play();

        System.out.println("---------------------");

        Game basketballGame = new Basketball();
        basketballGame.play();
    }
}
