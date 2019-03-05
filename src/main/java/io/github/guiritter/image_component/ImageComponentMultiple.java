package io.github.guiritter.image_component;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Collection;
import java.util.LinkedList;

/**
 * Simple class to enable stacks of images in Swing applications.
 * The size of the component will follow the first image's size,
 * unless different sizes are specified by the user.
 * Depending on the opacity of each image, they will be blended
 * according to their order in the stack (first image in the bottom).
 * The top left corner of all images are located at the same position.
 * Usage example:<blockquote><pre> JFrame frame = new JFrame();
 * frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 * ImageComponentMultiple multiple = new ImageComponentMultiple();
 * multiple.images.add(ImageIO.read(new File("path/to/image/0.png")));
 * multiple.images.add(ImageIO.read(new File("path/to/image/1.png")));
 * multiple.setSize(256, 256);
 * multiple.setMinimumSize(multiple.getSize());
 * multiple.setPreferredSize(multiple.getSize());
 * multiple.setMaximumSize(multiple.getSize());
 * multiple.update();
 * frame.getContentPane().add(multiple);
 * frame.pack();
 * frame.setLocationRelativeTo(null);
 * frame.setVisible(true);</pre></blockquote>
 * @author Guilherme Alan Ritter
 */
public final class ImageComponentMultiple extends ImageComponent{
    /**
     * Image stack. The user can modify this list freely, but must run the
     * {@link #update()} method afterwards to ensure expected behaviour. Applying
     * specific changes to this list and not running the {@link #update()} method
     * leads to undefined behaviour.
     */
    public final LinkedList<BufferedImage> images = new LinkedList<>();

    private static final long serialVersionUID = -7231014528527525725L;

    @Override
    public boolean isEmpty() {
        return images.isEmpty();
    }

    @Override
    protected void paintComponent (Graphics g) {
        super.paintComponent(g);
        for (BufferedImage bI : images) {
            g.drawImage(bI, 0, 0, null);
        }
    }

    @Override
    protected void setAutoSize() {
        if (isEmpty()) {
            size.setSize(0, 0);
        } else {
            size.setSize(
             images.getFirst().getWidth(), images.getFirst().getHeight());
        }
    }

    @Override
    protected void setAutoSizeMaximum() {
        if (isEmpty()) {
            sizeMaximum.setSize(0, 0);
        } else {
            sizeMaximum.setSize(
             images.getFirst().getWidth(), images.getFirst().getHeight());
        }
    }

    @Override
    protected void setAutoSizeMinimum() {
        if (isEmpty()) {
            sizeMinimum.setSize(0, 0);
        } else {
            sizeMinimum.setSize(
             images.getFirst().getWidth(), images.getFirst().getHeight());
        }
    }

    @Override
    protected void setAutoSizePreferred() {
        if (isEmpty()) {
            sizePreferred.setSize(0, 0);
        } else {
            sizePreferred.setSize(
             images.getFirst().getWidth(), images.getFirst().getHeight());
        }
    }

    /**
     * @deprecated This method was only valid for single image components.
     * Calling this method throws an
     * {@link java.lang.UnsupportedOperationException}.
     */
    @Deprecated
    @Override
    public void setImage(BufferedImage image) {
        throw new UnsupportedOperationException(
         "This method was only valid for single image components.");
    }

    public void update() {
        if (sizeAuto         ) setAutoSize();
        if (sizeMinimumAuto  ) setAutoSizeMinimum();
        if (sizePreferredAuto) setAutoSizePreferred();
        if (sizeMaximumAuto  ) setAutoSizeMaximum();
        repaint();
    }

    /**
     * Constructs an empty component with size (0, 0).
     */
    public ImageComponentMultiple() {}

    /**
     * Constructs a component with an image, with the same size as the image.
     * @param image the image to be painted by the component
     */
    public ImageComponentMultiple(BufferedImage image) {
        images.add(image);
        update();
    }

    /**
     * Constructs a component with a list of images,
     * with the same size as the first image.
     * @param images the images to be painted by the component
     */
    public ImageComponentMultiple(Collection<BufferedImage> images) {
        this.images.addAll(images);
        update();
    }

    /**
     * Constructs an empty component with the specified sizes.
     * Sizes that are null will follow the size of the image,
     * which will be zero for now.
     * @param size the size of the component, or null for automatic sizing
     * @param minimumSize
     * the minimum size of the component, or null for automatic sizing
     * @param preferredSize
     * the preferred size of the component, or null for automatic sizing
     * @param maximumSize
     * the maximum size of the component, or null for automatic sizing
     */
    public ImageComponentMultiple(Dimension size,
     Dimension minimumSize, Dimension preferredSize, Dimension maximumSize) {
        super(size, minimumSize, preferredSize, maximumSize);
    }

    /**
     * Constructs a component with an image, with the specified sizes.
     * Sizes that are null will follow the size of the image.
     * @param image the image to be painted by the component
     * @param size the size of the component, or null for automatic sizing
     * @param minimumSize
     * the minimum size of the component, or null for automatic sizing
     * @param preferredSize
     * the preferred size of the component, or null for automatic sizing
     * @param maximumSize
     * the maximum size of the component, or null for automatic sizing
     */
    public ImageComponentMultiple(BufferedImage image, Dimension size,
     Dimension minimumSize, Dimension preferredSize, Dimension maximumSize) {
        this(size, minimumSize, preferredSize, maximumSize);
        images.add(image);
        update();
    }

    /**
     * Constructs a component with a list of images, with the specified sizes.
     * Sizes that are null will follow the size of the image.
     * @param images the images to be painted by the component
     * @param size the size of the component, or null for automatic sizing
     * @param minimumSize
     * the minimum size of the component, or null for automatic sizing
     * @param preferredSize
     * the preferred size of the component, or null for automatic sizing
     * @param maximumSize
     * the maximum size of the component, or null for automatic sizing
     */
    public ImageComponentMultiple(
     Collection<BufferedImage> images, Dimension size,
     Dimension minimumSize, Dimension preferredSize, Dimension maximumSize) {
        this(size, minimumSize, preferredSize, maximumSize);
        this.images.addAll(images);
        update();
    }
}
