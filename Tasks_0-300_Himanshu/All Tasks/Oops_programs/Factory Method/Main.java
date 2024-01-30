interface Document {
    public void print();
}

class PDFDocument implements Document {

    @Override
    public void print() {
        System.out.println("PDF Document");
    }

}

class WordDocument implements Document {

    @Override
    public void print() {
        System.out.println("Word Document");
    }

}

public class Main {

    public static void main(String[] args) {
        PDFDocument p1 = new PDFDocument();
        WordDocument w1 = new WordDocument();

        p1.print();
        w1.print();
    }

}