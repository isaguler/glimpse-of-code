package com.isaguler.jasypt;

import jakarta.annotation.PostConstruct;
import org.jasypt.util.text.AES256TextEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PropertyService {

    @Value("${app.secret}")
    String secret;

    @Value("${app.open}")
    String open;

    @PostConstruct
    void readFromProperties() {
        System.out.println("open : " + open);
        System.out.println("secret : " + secret);

        AES256TextEncryptor textEncryptor = new AES256TextEncryptor();
        textEncryptor.setPassword("vf_cloud");

        List<String> stringList = List.of("123456", "CONFIG", "BILLACCOUNT", "CDR", "vibe:vibe!");

        for (String s : stringList) {
            System.out.println("-----");

            String myEncryptedText = textEncryptor.encrypt(s);
            System.out.println("myEncryptedText : " + myEncryptedText);

            String plainText = textEncryptor.decrypt(myEncryptedText);
            System.out.println("plainText : " + plainText);

            System.out.println("-----");
        }

        /*System.out.println("-----");
        String decrypted = textEncryptor.decrypt("biYRKw2lm7UiTTkVucr2nbLIHRlPqLAL7uyW+nejlCPnhRZrTj8kvZKiRpu7ovIq");
        System.out.println("decrypted : " + decrypted);*/

    }
}
