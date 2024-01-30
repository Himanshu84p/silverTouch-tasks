interface Image {
    public void display();
}

class RealImage implements Image {

    @Override
    public void display(){
        System.out.println("This is a real image calling from Proxy Object.");
    }
}

class ProxyImage implements Image {
    @Override
    public void display(){
        RealImage realImage = new RealImage();
        realImage.display();
    }
}

public class Prac9 {
    public static void main(String[] args) {
        // Intializing Proxy image

        ProxyImage proxyImage = new ProxyImage();
        proxyImage.display();
    }
    
}
