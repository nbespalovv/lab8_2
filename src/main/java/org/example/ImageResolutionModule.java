package org.example;

import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Component
public class ImageResolutionModule implements Module {
        @Override
        public List<String> getSupportedFileTypes() {
            ArrayList<String> list = new ArrayList<>();
            list.add("png");
            list.add("jpg");
            return list;
        }

        @Override
        public String getDescription() {
            return "Считает разрешение изображения";
        }

        @Override
        public void invoke(String fileName) {
            try {
                BufferedImage image = ImageIO.read(new File(fileName));
                System.out.printf("Ширина изображения= %d и высота= %d", image.getWidth(), image.getHeight());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

