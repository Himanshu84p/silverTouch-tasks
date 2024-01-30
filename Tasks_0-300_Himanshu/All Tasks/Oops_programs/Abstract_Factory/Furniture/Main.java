package Furniture;

interface furniture {
    public void victorian();

    public void modern();
}

class chair implements furniture {
    @Override
    public void victorian() {
        System.out.println("Victorian chair created!!");
    }

    @Override
    public void modern() {
        System.out.println("Modern chair Created!!");
    }
}

class table implements furniture {
    @Override
    public void victorian() {
        System.out.println("Victorian table created!!");
    }

    @Override
    public void modern() {
        System.out.println("Modern table created!1");
    }
}

interface furnitureFactory {
    furniture createFurniture();
}

class chairFactory implements furnitureFactory {
    @Override
    public furniture createFurniture() {
        return new chair();
    }
}

class tableFactory implements furnitureFactory {
    @Override
    public furniture createFurniture() {
        return new table();
    }
}

public class Main {
    public static void main(String[] args) {
        furnitureFactory chairFactory = new chairFactory();
        furniture chair = chairFactory.createFurniture();
        chair.victorian();
        chair.modern();
    }

}
