package ru.kpfu.khismatova.lab6;

import java.io.File;

public class Task2 {
    public static void main(String[] args) {
        String directoryPath = "src/main/resources/example";
        StringBuilder folderNames = new StringBuilder();
        int folderCount = countFolders(new File(directoryPath), folderNames);

        System.out.println("Количество папок в директории: " + folderCount);
        System.out.println("Названия папок:\n" + folderNames);
    }

    private static int countFolders(File directory, StringBuilder folderNames) {
        if (!directory.isDirectory()) return 0;

        int count = 0;
        for (File file : directory.listFiles()) {
            if (file.isDirectory()) {
                count++;
                folderNames.append(file.getName()).append("\n");
                count += countFolders(file, folderNames);
            }
        }
        return count;
    }
}