# ImageComponent

It's just that I don't like to use `JLabel`s when I want images in Swing GUIs, so I Googled for what would be the minimum necessary way to do that, and I wrote a class that does that containing just the essential.

The size of the image component will follow the image's size, unless different sizes are specified by the user. Usage example:
```java
JFrame frame = new JFrame();
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame.getContentPane().add(new ImageComponent(
 ImageIO.read(new File("path/to/image.png"))));
frame.pack();
frame.setLocationRelativeTo(null);
frame.setVisible(true);
```
I also wrote a class for a component that hosts a stack of images, simply because it required very few changes. Depending on the opacity of each image, they will be blended according to their order in the stack (first image in the bottom). The top left corner of all images are located at the same position. Usage example:
```java
JFrame frame = new JFrame();
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
ImageComponentMultiple multiple = new ImageComponentMultiple();
multiple.images.add(ImageIO.read(new File("path/to/image/0.png")));
multiple.images.add(ImageIO.read(new File("path/to/image/1.png")));
multiple.setSize(256, 256);
multiple.setMinimumSize(multiple.getSize());
multiple.setPreferredSize(multiple.getSize());
multiple.setMaximumSize(multiple.getSize());
multiple.update();
frame.getContentPane().add(multiple);
frame.pack();
frame.setLocationRelativeTo(null);
frame.setVisible(true);
```
I wish I could've implemented the List interface to handle the stack, but there's a conflict with the `remove` and `size` methods, whose names both belong to the JComponent class and the List interface. I also intended to implement automatic stretching with original aspect ratio control, control over the position of each image inside the component, and maybe other features, but I think it's good enough as it is and I want to focus on getting more of my projects here.

I've written a demo class for each class. Just run it and you'll see it in action.

[A few words about Maven.](https://gist.github.com/GuiRitter/1834bd024756e08ab422026a7cd24605)
