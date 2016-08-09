package fordream.binary.builder.scanner;

import fordream.binary.builder.hash.HashCalculator;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class DirectoryScanner {
    interface GetValueInf {
        void doValue(String filePath, HashCalculator calculator);
    }

    private File root;
    private HashCalculator calculator;

    public DirectoryScanner(File root, HashCalculator calculator) {
        this(root, calculator, false);
    }

    public DirectoryScanner(File root, HashCalculator calculator, boolean autoScan) {
        this.root = root;
        this.calculator = calculator;
        if (autoScan) {
            this.runWithHex();
        }
    }

    public Map<String, String> runWithHex() {
        Map<String, String> map = new HashMap();
        this.realRun((filePath, calculator1) -> map.put(filePath, calculator1.getHexValue()));
        return map;
    }

    public Map<String, byte[]> runWithBytes() {
        Map<String, byte[]> map = new HashMap();
        this.realRun(((filePath, calculator1) -> map.put(filePath, calculator1.getValue())));
        return map;
    }

    private Map<String, String> realRun(GetValueInf inf) {
        Queue<File> dirList = new LinkedList();
        Map<String, String> scanResult = new HashMap();

        int baseOffset = root.getAbsolutePath().length();
        dirList.add(root);
        while (!dirList.isEmpty()) {
            File dir = dirList.poll();
            File[] files = dir.listFiles();

            for (File file : files) {
                if (file.isDirectory()) {
                    dirList.add(file);
                    continue;
                }

                String filePath = file.getAbsolutePath().substring(baseOffset + 1);
                try {
                    calculator.reset();
                    calculator.update(file);
                    //byte[] hash = calculator.getValue();
                    //scanResult.put(filePath, calculator.getHexValue());
                    inf.doValue(filePath, calculator);
                } catch (IOException e) {
                    System.err.printf("IOException with %s -> %s%n", file.getAbsolutePath(), e.getMessage());
                }
            }
        }
        return scanResult;
    }
}
