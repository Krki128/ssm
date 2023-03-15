package org.k.utils;

import org.junit.Test;
import static org.junit.Assert.*;

public class MD5UtilTest {

    @Test
    public void getMD5() {
        String string=MD5Util.getMD5("000000");
        System.out.println(string);
    }
}