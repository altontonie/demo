package backyard.programmer.demo.util;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Random;

@Component
public class Utils {

    private Random random = new SecureRandom();
    private String Alphabet = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890";

    public String GenerateUserId(int length){
        return generateId(length);
    }

    private String generateId(int length) {
        StringBuilder returnString = new StringBuilder(length);

        for(int i=0;i<length;i++){
            returnString.append(Alphabet.charAt(random.nextInt(Alphabet.length())));
        }

        return new String(returnString);
    }
}
