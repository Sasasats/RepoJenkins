package Utils;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

public class UploadUtils {
    public void uploadAvatar() {

        Robot rb = null;

        try {
            rb = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }

        rb.delay(2000);

        URL res = getClass().getClassLoader().getResource("Avatar.png");

        File file = null;
        try {
            file = Paths.get(res.toURI()).toFile();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        StringSelection aPath = new StringSelection(file.getAbsolutePath());

        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(aPath, null);

        rb.keyPress(KeyEvent.VK_CONTROL);
        rb.keyPress(KeyEvent.VK_V);

        rb.keyRelease(KeyEvent.VK_CONTROL);
        rb.keyRelease(KeyEvent.VK_V);

        rb.keyPress(KeyEvent.VK_ENTER);
        rb.keyRelease(KeyEvent.VK_ENTER);

        rb.delay(2000);
    }
}