package Text;

interface text {
    String display();
}

class normalText implements text {
    @Override
    public String display() {
        return "Normal Text";
    }
}

class textDecorator implements text {

    protected text txt;

    textDecorator(text txt) {
        this.txt = txt;
    }

    @Override
    public String display() {
        String str = txt.display();
        return str;
    }

}

class italicDecorator extends textDecorator {
    italicDecorator(text txt) {
        super(txt);
    }

    @Override
    public String display() {
        System.out.println(txt.display());
        return "Italic Text";
    }
}

class BoldDecorator extends textDecorator {
    BoldDecorator(text txt) {
        super(txt);
    }

    @Override
    public String display() {
        System.out.println(txt.display());
        return "Bold Text";
    }
}

public class Main {

    public static void main(String[] args) {
        text txtDisplay = new normalText();

        italicDecorator itdeco = new italicDecorator(txtDisplay);

        BoldDecorator boldanditDeco = new BoldDecorator(itdeco);

        System.out.println("Normal text: " + txtDisplay.display());
        System.out.println(" ");
        System.out.println("Italic Text: " + itdeco.display());
        System.out.println(" ");
        System.out.println("Bold and Italic text: " + boldanditDeco.display());
    }
}
