package com.bean;

import sun.misc.BASE64Encoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Encoding {
    /**
     * @param oldString 待加密字符串
     * @return 已加密的新字符串
     */
    public static String MD5(String oldString) {
        byte[] oldBytes = oldString.getBytes();
        MessageDigest messageDigest;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            byte[] newBytes = messageDigest.digest(oldBytes);
            BASE64Encoder encoder = new BASE64Encoder();
            String newString = encoder.encode(newBytes);
            return newString;
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }
}
