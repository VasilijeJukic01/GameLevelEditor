package editor.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class Utils {

    private static volatile Utils instance = null;

    private Utils() {}

    public static Utils getInstance() {
        if (instance == null) {
            synchronized (Utils.class) {
                if (instance == null) {
                    instance = new Utils();
                }
            }
        }
        return instance;
    }

    // Importing image: [(w, h) = (-1, -1) Original Size]
    public BufferedImage importImage(String name, int w, int h) {
        try {
            BufferedImage image = ImageIO.read(Objects.requireNonNull(getClass().getResource(name)));
            if (w == -1 && h == -1) return image;
            Image temp = image.getScaledInstance(w, h, Image.SCALE_SMOOTH);
            BufferedImage resized = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = resized.createGraphics();
            g2.drawImage(temp, 0, 0, null);
            g2.dispose();
            return resized;
        }
        catch (Exception e) {
            System.out.println("Image load failed: "+name);
        }
        return null;
    }

}
