package DocumentStruc;

interface DocElement {
    public void accept(DocVisitor visitor);
}

interface DocVisitor {
    void visitPara(Paragraph paragraph);

    void visitImage(Image image);
}

class Paragraph implements DocElement {
    @Override
    public void accept(DocVisitor visitor) {
        visitor.visitPara(this);
    }

    public void render() {
        System.out.println("Rendering Paragraph!!");
    }
}

class Image implements DocElement {
    @Override
    public void accept(DocVisitor visitor) {
        visitor.visitImage(this);
    }

    public void Display() {
        System.out.println("Displaying Image!!");
    }
}

class DocOperation implements DocVisitor {
    @Override
    public void visitPara(Paragraph paragraph) {
        System.out.println("Performing operation to Paragraph");
        paragraph.render();
    }

    @Override
    public void visitImage(Image image) {
        System.out.println("Performing operation to Image");
        image.Display();
    }
}

public class Main {
    public static void main(String[] args) {
        DocElement para = new Paragraph();
        DocElement image = new Image();

        DocOperation operation = new DocOperation();
        para.accept(operation);
        image.accept(operation);
    }
}