package com.wjw.blog.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {

    public static String code(String str) {
        try {

            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte[] byteDigest = md.digest();
            int i;
            StringBuilder builder = new StringBuilder();
            for(int offset = 0; offset < byteDigest.length; offset++) {
                i = byteDigest[offset];
                if(i < 0)
                    i += 256;
                if(i < 16)
                    builder.append("0");
                builder.append(Integer.toHexString(i));
            }
            return builder.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

}
