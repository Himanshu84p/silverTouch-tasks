public class Main {
    public static void main(String[] args) {
        Channel yrf = new Channel();
        Subscriber s1 = new Subscriber("Himanshu");
        Subscriber s2 = new Subscriber("Kevin");
        Subscriber s3 = new Subscriber("Rutul");

        yrf.subscribe(s3);
        yrf.subscribe(s1);
        yrf.subscribe(s2);

        s1.subscribeChannel(yrf);
        s2.subscribeChannel(yrf);
        s3.subscribeChannel(yrf);

        yrf.uploadVideo("Learning observer pattern");
    }
}