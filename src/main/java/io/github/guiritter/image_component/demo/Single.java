package io.github.guiritter.image_component.demo;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.IOException;

import javax.swing.JFrame;

import io.github.guiritter.image_component.ImageComponent;

public final class Single {

    static {
        JFrame.setDefaultLookAndFeelDecorated(true);
    }

    public static void main(String args[]) throws IOException {
        BufferedImage image
         = new BufferedImage(256 , 256, BufferedImage.TYPE_INT_RGB);
        WritableRaster raster = image.getRaster();
        int x;
        int y;
        int color[] = {0, 0, 0};
        for (y = 0; y < image.getHeight(); y++) {
            for (x = 0; x < image.getWidth(); x++) {
                color[0] = x;
                color[1] = (x / 2) + (y / 2);
                color[2] = y;
                raster.setPixel(x, y, color);
            }
        }
        JFrame frame = new JFrame("ImageComponent Demo Single");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new ImageComponent(image));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
