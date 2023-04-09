package org.example;

import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

@Component
public class FileSizeModule implements Module {
    @Override
    public List<String> getSupportedFileTypes() {
        ArrayList<String> list = new ArrayList<>();
        list.add("txt");
        return list;
    }

    @Override
    public String getDescription() {
        return "Считает размер файла в байтах";
    }

    @Override
    public void invoke(String fileName) {
        File file = new File(fileName);
        System.out.println(file.length());
    }
}
