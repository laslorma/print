package io.catwizard.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Victor on 07-Feb-17.
 */
public class License {

    private String key;

    public String generateLicence(String domain) throws NoSuchAlgorithmException
    {
        StringBuffer sb = null;
        String word = null;

        if (!domain.isEmpty())
        {
            word = key + domain;
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(word.getBytes());

            byte byteData[] = md.digest();

            //convert the byte to hex format method 1
            sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }

        }

        return sb.toString();
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
