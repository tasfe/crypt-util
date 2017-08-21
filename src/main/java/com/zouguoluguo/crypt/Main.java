package com.zouguoluguo.crypt;

import java.util.Arrays;
import java.util.Random;

/**
 * 实现描述：
 *
 * @author zouguoluguo
 * @version v1.0.0
 * @see
 * @since 2017/8/19
 */
public class Main {
    public static void main(String[] args) {
        String s = "15201328863";
        System.out.println(s.hashCode()+Integer.MAX_VALUE);

        Random r = new Random(15201328863L);

        System.out.println(r.nextInt());

        System.out.println((1<<31)-1);
    }
}
