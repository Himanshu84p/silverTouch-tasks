package FileDirec;

import java.util.ArrayList;
import java.util.List;

interface FileSystem {
    void Display();
}

class File implements FileSystem {
    private String FileName;

    public File(String Fname) {
        this.FileName = Fname;
    }

    @Override
    public void Display() {
        System.out.println("File: " + FileName);
    }
}

class Directory implements FileSystem {
    List<FileSystem> fileSystem = new ArrayList<>();
    private String DirectoryName;

    public Directory(String Name) {
        this.DirectoryName = Name;
    }

    @Override
    public void Display() {
        System.out.println("Directory: " + DirectoryName);
        for (FileSystem fileSystem : fileSystem) {
            fileSystem.Display();
        }
    }

    public void add(FileSystem fileSystem) {
        this.fileSystem.add(fileSystem);
    }

    public void remove(FileSystem fileSystem) {
        this.fileSystem.remove(fileSystem);
    }

}

public class Main {
    public static void main(String[] args) {
        FileSystem file1 = new File("File1.txt");
        FileSystem file2 = new File("File2.txt");

        Directory directory = new Directory("Directory 1");
        directory.add(file1);
        directory.add(file2);

        Directory directory2 = new Directory("Directory 2");
        directory2.add(directory);
        directory2.add(new File("File3.txt"));

        directory.Display();
        System.out.println("--------------------------------------------");
        directory2.Display();
    }
}
