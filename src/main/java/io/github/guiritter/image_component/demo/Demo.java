package io.github.guiritter.image_component.demo;

import java.io.IOException;

import javax.swing.JFrame;

public final class Demo {

    static {
        JFrame.setDefaultLookAndFeelDecorated(true);
    }

    public static void main(String args[]) throws IOException {
        Single.main(args);
        Multiple.main(args);
    }
}