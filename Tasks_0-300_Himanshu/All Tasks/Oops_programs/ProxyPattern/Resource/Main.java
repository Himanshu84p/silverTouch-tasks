package Resource;

interface Resource {
    public void access();
}

class RealResource implements Resource {

    private String AdminName = "Himanshu";
    private String AdminPassword = "Himanshu@123";

    private String Name;
    private String Password;

    RealResource(String Name, String Password) {
        this.Name = Name;
        this.Password = Password;
    }

    @Override
    public void access() {
        if (Name == AdminName && Password == AdminPassword) {
            System.out.println("You can have access to Real Resource");
        } else {
            System.out.println("You are not authorized to access Resource.");
        }
    }
}

class ProxyResource implements Resource {

    private RealResource realResource;
    public String Name;
    public String Password;

    ProxyResource(String Name, String Password) {
        this.Name = Name;
        this.Password = Password;
    }

    @Override
    public void access() {

        RealResource realResource = new RealResource(this.Name, this.Password);
        realResource.access();
    }
}

public class Main {

    public static void main(String[] args) {
        ProxyResource proxyResource = new ProxyResource("Himanshu", "Himanshu@123");
        proxyResource.access();
    }
}