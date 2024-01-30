import java.util.ArrayList;
import java.util.List;

class FileNode {
    String name;
    List<FileNode> children;

    public FileNode(String name) {
        this.name = name;
        this.children = new ArrayList<>();
    }
}

public class FileSystemSimulation {

    public static void displayFileSystem(FileNode root, int depth) {
        StringBuilder indentation = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            indentation.append("  ");
        }

        System.out.println(indentation + root.name);
        for (FileNode child : root.children) {
            displayFileSystem(child, depth + 1);
        }
    }

    public static void main(String[] args) {
        FileNode root = new FileNode("root");

        FileNode documents = new FileNode("Documents");
        FileNode pictures = new FileNode("Pictures");

        FileNode resume = new FileNode("Resume.txt");
        FileNode vacation = new FileNode("Vacation");

        FileNode beach = new FileNode("Beach.jpg");
        FileNode mountains = new FileNode("Mountains.jpg");

        root.children.add(documents);
        root.children.add(pictures);

        documents.children.add(resume);
        pictures.children.add(vacation);

        vacation.children.add(beach);
        vacation.children.add(mountains);

        System.out.println("File System Structure:");
        displayFileSystem(root, 0);
    }
}
