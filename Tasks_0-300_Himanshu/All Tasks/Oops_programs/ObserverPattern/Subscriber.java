public class Subscriber {
    private String name;
    private Channel channel = new Channel();

    public Subscriber(String name) {
        this.name = name;
    }

    public void update() {
        System.out.println(name + " you have notification New Video uploaded!!!");
    }

    public void subscribeChannel(Channel ch) {
        channel = ch;
    }
}
