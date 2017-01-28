package io.github.guiritter.imagecomponent.demo;

import io.github.guiritter.imagecomponent.ImageComponentMultiple;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

public final class Multiple {

    static {
        JFrame.setDefaultLookAndFeelDecorated(true);
    }

    public static void main(String args[]) {
        final BufferedImage image0
         = new BufferedImage(256 , 128, BufferedImage.TYPE_INT_ARGB);
        WritableRaster raster0 = image0.getRaster();
        int x;
        int y;
        int color[] = {0, 0, 0, 0};
        for (y = 0; y < image0.getHeight(); y++) {
            for (x = 0; x < image0.getWidth(); x++) {
                color[0] = x;
                color[1] = 0;
                color[2] = 0;
                color[3] = 255 - x;
                raster0.setPixel(x, y, color);
            }
        }
        final BufferedImage image1
         = new BufferedImage(128 , 256, BufferedImage.TYPE_INT_ARGB);
        WritableRaster raster1 = image1.getRaster();
        for (y = 0; y < image1.getHeight(); y++) {
            for (x = 0; x < image1.getWidth(); x++) {
                color[0] = 0;
                color[1] = 0;
                color[2] = y;
                color[3] = 255 - y;
                raster1.setPixel(x, y, color);
            }
        }
        final ImageComponentMultiple multiple = new ImageComponentMultiple();
        multiple.images.add(image0);
        multiple.images.add(image1);
        multiple.setSize(256, 256);
        multiple.setMinimumSize(multiple.getSize());
        multiple.setPreferredSize(multiple.getSize());
        multiple.setMaximumSize(multiple.getSize());
        multiple.update();
        JFrame frame = new JFrame("ImageComponent Demo Single");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(
         new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.getContentPane().add(multiple);
        JButton button = new JButton("toggle 0");
        button.addActionListener((ActionEvent e) -> {
            if (multiple.images.contains(image0)) {
                multiple.images.remove(image0);
            } else {
                multiple.images.add(image0);
            }
            multiple.update();
        });
        frame.getContentPane().add(button);
        button = new JButton("toggle 1");
        button.addActionListener((ActionEvent e) -> {
            if (multiple.images.contains(image1)) {
                multiple.images.remove(image1);
            } else {
                multiple.images.add(image1);
            }
            multiple.update();
        });
        frame.getContentPane().add(button);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
