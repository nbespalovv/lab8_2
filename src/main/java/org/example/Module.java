package org.example;

import java.util.List;

public interface Module {

    List<String> getSupportedFileTypes();

    String getDescription();

    void invoke(String fileName);

}
