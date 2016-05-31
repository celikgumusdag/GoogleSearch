package Base;

import org.imgscalr.Scalr;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import static org.imgscalr.Scalr.resize;

/**
 * Created by celik.gumusdag on 31.05.2016.
 */
public class Base {

    public static String imageToString(BufferedImage image) {
        String imageString = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        String base64HTMLStart="data:image/png;base64,";
        try {
            ImageIO.write(image, "png", bos);
            byte[] imageBytes = bos.toByteArray();

            BASE64Encoder encoder = new BASE64Encoder();
            imageString = encoder.encode(imageBytes);

            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return base64HTMLStart+imageString;
    }
    public static BufferedImage resizeImage(BufferedImage image) {
        BufferedImage newImage = resize(image, Scalr.Method.BALANCED, 800, 600);
        return newImage;
    }

}
