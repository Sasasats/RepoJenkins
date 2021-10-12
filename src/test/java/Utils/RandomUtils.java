package Utils;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;

import java.security.SecureRandom;

public class RandomUtils {

    private ISettingsFile textData = new JsonSettingsFile("TextData.json");
    private SecureRandom rnd = new SecureRandom();
    private StringBuilder commonLetter = new StringBuilder(1).append(textData.getValue("/EnglishCommonLetters").toString().charAt(rnd.nextInt(textData.getValue("/EnglishCommonLetters").toString().length())));

    public String getRandomString(int length) {
        StringBuilder randomString = new StringBuilder((length));

        randomString.append(textData.getValue("/Numbers").toString().charAt(rnd.nextInt(textData.getValue("/Numbers").toString().length())));
        randomString.append(textData.getValue("/EnglishCapitalLetters").toString().charAt(rnd.nextInt(textData.getValue("/EnglishCapitalLetters").toString().length())));
        randomString.append(textData.getValue("/RussianLetters").toString().charAt(rnd.nextInt(textData.getValue("/RussianLetters").toString().length())));

        randomString.append(commonLetter);

        for (int i = 0; i < (length - randomString.length()); ) {
            randomString.append(textData.getValue("/AllSimbols").toString().charAt(rnd.nextInt(textData.getValue("/AllSimbols").toString().length())));
        }

        return randomString.toString();
    }

    public String getRandomStringEmailDomain(int length) {
        StringBuilder randomString = new StringBuilder((length));

        randomString.append(commonLetter);
        for (int i = 0; i < (length - randomString.length()); ) {
            randomString.append(textData.getValue("/EnglishCommonLetters").toString().charAt(rnd.nextInt(textData.getValue("/EnglishCommonLetters").toString().length())));
        }

        return randomString.toString();
    }
}