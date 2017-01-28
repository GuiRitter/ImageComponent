package io.github.guiritter.imagecomponent;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JComponent;

/**
 * Simple class to enable images in Swing applications.
 * The size of the component will follow the image's size,
 * unless different sizes are specified by the user.
 * This class is not synchronized.
 * Usage example:<blockquote><pre> JFrame frame = new JFrame();
 * frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 * frame.getContentPane().add(new ImageComponent(
 *  ImageIO.read(new File("path/to/image.png"))));
 * frame.pack();
 * frame.setLocationRelativeTo(null);
 * frame.setVisible(true);</pre></blockquote>
 * @author Guilherme Alan Ritter
 */
public class ImageComponent extends JComponent {

    private BufferedImage image = null;

    protected final Dimension size = new Dimension(0, 0);

    /**
     * Whether the size follows the image size.
     */
    protected boolean sizeAuto = true;

    protected final Dimension sizeMaximum = new Dimension(0, 0);

    /**
     * Whether the maximum size follows the image size.
     */
    protected boolean sizeMaximumAuto = true;

    protected final Dimension sizeMinimum = new Dimension(0, 0);

    /**
     * Whether the minimum size follows the image size.
     */
    protected boolean sizeMinimumAuto = true;

    protected final Dimension sizePreferred = new Dimension(0, 0);

    /**
     * Whether the preferred size follows the image size.
     */
    protected boolean sizePreferredAuto = true;

    @Override
    public int getHeight() {
        return size.height;
    }

    @Override
    public Dimension getMaximumSize() {
        return new Dimension(sizeMaximum);
    }

    /**
     * Returns whether the minimum size of this component is following
     * the size of the image. It will do so when the object is created,
     * will stop to do so when the user changes the size, and will do so again
     * when the user decides to reset the sizes.
     * @return true when the minimum size of this component is the same
     * as the size of the image, false when the size has been fixed by the user
     */
    public boolean getMaximumSizeAuto() {
        return sizeMaximumAuto;
    }

    @Override
    public Dimension getMinimumSize() {
        return new Dimension(sizeMinimum);
    }

    /**
     * Returns whether the maximum size of this component is following
     * the size of the image. It will do so when the object is created,
     * will stop to do so when the user changes the size, and will do so again
     * when the user decides to reset the sizes.
     * @return true when the maximum size of this component is the same
     * as the size of the image, false when the size has been fixed by the user
     */
    public boolean getMinimumSizeAuto() {
        return sizeMinimumAuto;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(sizePreferred);
    }

    /**
     * Returns whether the preferred size of this component is following
     * the size of the image. It will do so when the object is created,
     * will stop to do so when the user changes the size, and will do so again
     * when the user decides to reset the sizes.
     * @return true when the preferred size of this component is the same
     * as the size of the image, false when the size has been fixed by the user
     */
    public boolean getPreferredSizeAuto() {
        return sizePreferredAuto;
    }

    @Override
    public Dimension getSize() {
        return new Dimension(size);
    }

    @Override
    public Dimension getSize(Dimension rv) {
        if (rv == null) {
            rv = new Dimension(size);
        } else {
            rv.setSize(size);
        }
        return rv;
    }

    /**
     * Returns whether the size of this component is following
     * the size of the image. It will do so when the object is created,
     * will stop to do so when the user changes the size, and will do so again
     * when the user decides to reset the sizes.
     * @return true when the size of this component is the same
     * as the size of the image, false when the size has been fixed by the user
     */
    public boolean getSizeAuto() {
        return sizeAuto;
    }

    @Override
    public int getWidth() {
        return size.width;
    }

    /**
     * Whether this component contains an image.
     * @return true if this component contains an image; false otherwise
     */
    public boolean isEmpty() {
        return image == null;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);
    }

    /**
     * Sets the size according to the size of the image.
     */
    protected void setAutoSize() {
        if (isEmpty()) {
            size.setSize(0, 0);
        } else {
            size.setSize(image.getWidth(), image.getHeight());
        }
    }

    /**
     * Sets the maximum size according to the size of the image.
     */
    protected void setAutoSizeMaximum() {
        if (isEmpty()) {
            sizeMaximum.setSize(0, 0);
        } else {
            sizeMaximum.setSize(image.getWidth(), image.getHeight());
        }
    }

    /**
     * Sets the minimum size according to the size of the image.
     */
    protected void setAutoSizeMinimum() {
        if (isEmpty()) {
            sizeMinimum.setSize(0, 0);
        } else {
            sizeMinimum.setSize(image.getWidth(), image.getHeight());
        }
    }

    /**
     * Sets the preferred size according to the size of the image.
     */
    protected void setAutoSizePreferred() {
        if (isEmpty()) {
            sizePreferred.setSize(0, 0);
        } else {
            sizePreferred.setSize(image.getWidth(), image.getHeight());
        }
    }

    /**
     * Sets the image that this component paints. This is the part
     * where the sizes of the component follow the size of the image,
     * if configured to do so.
     * @param image
     */
    public void setImage(BufferedImage image) {
        this.image = image;
        if (sizeAuto         ) setAutoSize();
        if (sizeMinimumAuto  ) setAutoSizeMinimum();
        if (sizePreferredAuto) setAutoSizePreferred();
        if (sizeMaximumAuto  ) setAutoSizeMaximum();
        /*if (!isEmpty())*/ repaint();
    }

    /**
     * To fix "replaceable method in constructor" warning.
     * @param image
     */
    private void setImageFix(BufferedImage image) {
        setImage(image);
    }

    @Override
    public final void setMaximumSize(Dimension maximumSize) {
        if (maximumSize == null) {
            setAutoSizeMaximum();
            sizeMaximumAuto = true;
        } else {
            sizeMaximum.setSize(maximumSize.width, maximumSize.height);
            sizeMaximumAuto = false;
        }
    }

    @Override
    public final void setMinimumSize(Dimension minimumSize) {
        if (minimumSize == null) {
            setAutoSizeMinimum();
            sizeMinimumAuto = true;
        } else {
            sizeMinimum.setSize(minimumSize.width, minimumSize.height);
            sizeMinimumAuto = false;
        }
    }

    @Override
    public final void setPreferredSize(Dimension preferredSize) {
        if (preferredSize == null) {
            setAutoSizePreferred();
            sizePreferredAuto = true;
        } else {
            sizePreferred.setSize(preferredSize.width, preferredSize.height);
            sizePreferredAuto = false;
        }
    }

    @Override
    public final void setSize(Dimension d) {
        // if d is null, will throw NPE, just like parent method
        setSize(d.width, d.height);
    }

    @Override
    public void setSize(int width, int height) {
        size.setSize(width, height);
        sizeAuto = false;
    }

    /**
     * Set the sizes of the component to follow the sizes of the image.
     * This is the default setup for this component.
     */
    public void setSizesAuto() {
        setAutoSize();
        setAutoSizeMinimum();
        setAutoSizePreferred();
        setAutoSizeMaximum();
        sizeAuto          = true;
        sizeMinimumAuto   = true;
        sizePreferredAuto = true;
        sizeMaximumAuto   = true;
    }

    /**
     * Constructs an empty component with size (0, 0).
     */
    public ImageComponent() {}

    /**
     * Constructs a component with an image, with the same size as the image.
     * @param image the image to be painted by the component
     */
    public ImageComponent(BufferedImage image) {
        setImageFix(image);
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
    public ImageComponent(Dimension size,
     Dimension minimumSize, Dimension preferredSize, Dimension maximumSize) {
        // java.awt.Component.setSize's default behaviour is to throw NPE
        // on null parameter, and I've overriden it maintaing that behaviour.
        // however, this behavior is not acceptable here, so I've treated it
        if (size != null) setSize(size);
        // the default behaviour of these, however is different.
        // executing these methods with null parameters is the same
        // as not executing them in this case
        setMinimumSize(minimumSize);
        setPreferredSize(preferredSize);
        setMaximumSize(maximumSize);
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
    public ImageComponent(BufferedImage image, Dimension size,
     Dimension minimumSize, Dimension preferredSize, Dimension maximumSize) {
        this(size, minimumSize, preferredSize, maximumSize);
        setImageFix(image);
    }
}
