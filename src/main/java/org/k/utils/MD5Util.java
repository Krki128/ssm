package org.k.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
    public static String getMD5(String string){
        try{
            MessageDigest messageDigest=MessageDigest.getInstance("SHA");
            messageDigest.update(string.getBytes());
            byte[] bytes=messageDigest.digest();
            StringBuilder hash= new StringBuilder();
            int temp;
            for (byte aByte : bytes) {
                temp = aByte < 0 ? 256 + aByte : aByte;
                if (temp < 16)
                    hash.append("0");
                hash.append(Integer.toString(temp, 16));
            }
            return hash.toString();
        }catch(NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return null;
    }

}
