package BurstSort80;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Burstsort {
    private static final int BLOCK_SIZE = 1000;

    public static void main(String[] args) {
        try {

            generateInputFile("input.txt", 100000);

            burstsort("input.txt", "output.txt");

            System.out.println("Burstsort completed.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void burstsort(String inputFile, String outputFile) throws IOException {

        List<String> block = new ArrayList<>(BLOCK_SIZE);

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                block.add(line);

                if (block.size() == BLOCK_SIZE) {
                    Collections.sort(block);
                    writeBlock(block, outputFile);
                    block.clear();
                }
            }

            if (!block.isEmpty()) {
                Collections.sort(block);
                writeBlock(block, outputFile);
            }
        }

        mergeSortedBlocks(outputFile);
    }

    private static void writeBlock(List<String> block, String outputFile) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile, true))) {
            for (String line : block) {
                writer.write(line);
                writer.newLine();
            }
        }
    }

    private static void mergeSortedBlocks(String outputFile) throws IOException {
        List<BufferedReader> readers = new ArrayList<>();
        List<String> currentLines = new ArrayList<>();

        for (int i = 0; i < BLOCK_SIZE; i++) {
            BufferedReader reader = new BufferedReader(new FileReader("block_" + i + ".txt"));
            readers.add(reader);

            String line = reader.readLine();
            currentLines.add(line);
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            while (!currentLines.isEmpty()) {
                int minIndex = findMinIndex(currentLines);

                writer.write(currentLines.get(minIndex));
                writer.newLine();

                String nextLine = readers.get(minIndex).readLine();
                currentLines.set(minIndex, nextLine);

                if (nextLine == null) {
                    currentLines.remove(minIndex);
                    readers.get(minIndex).close();
                    readers.remove(minIndex);
                }
            }
        }

        for (int i = 0; i < BLOCK_SIZE; i++) {
            new File("block_" + i + ".txt").delete();
        }
    }

    private static int findMinIndex(List<String> currentLines) {
        int minIndex = 0;
        for (int i = 1; i < currentLines.size(); i++) {
            if (currentLines.get(i) != null && (currentLines.get(minIndex) == null
                    || currentLines.get(i).compareTo(currentLines.get(minIndex)) < 0)) {
                minIndex = i;
            }
        }
        return minIndex;
    }

    private static void generateInputFile(String filename, int lines) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (int i = 0; i < lines; i++) {
                writer.write(String.valueOf((int) (Math.random() * 1000000)));
                writer.newLine();
            }
        }
    }
}
