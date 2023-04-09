package org.example;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

@Component
public class FileRowCountModule implements Module {
    @Override
    public List<String> getSupportedFileTypes() {
        ArrayList<String> list = new ArrayList<>();
        list.add("txt");
        return list;
    }

    @Override
    public String getDescription() {
        return "Считает строки в файле";
    }

    @Override
    public void invoke(String fileName) {

        try {
            Scanner scanner = new Scanner(new File(fileName));

            int lines = 0;
            while (scanner.hasNextLine() && (scanner.nextLine() != null)) {
                lines++;
            }

            System.out.printf("File has lines %d", lines);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
