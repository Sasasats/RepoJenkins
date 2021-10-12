package Utils;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;

import java.security.SecureRandom;

public class RandomUtils {
    private static String letters = "abcdefghijklmnopqrstuvwxyz";
    private static SecureRandom rnd = new SecureRandom();
    private static StringBuilder commonLetter = new StringBuilder(1).append(letters.charAt(rnd.nextInt(letters.length())));

    public static String getRandomString(int length) {
        StringBuilder randomString = new StringBuilder((length));

        randomString.append(commonLetter);
        for (int i = 0; i < (length - randomString.length()); ) {
            randomString.append(letters.charAt(rnd.nextInt(letters.length())));
        }

        return randomString.toString();
    }
}