package com.zouguoluguo.crypt.support;

import java.util.List;
import java.util.Map;

/**
 * 实现描述：判断
 *
 * @author zuoguoluguo
 * @version v1.0.0
 * @see
 * @since 2017/8/24
 */
public class PreconditionUtil {
    /**
     * 判断字符串是否为null或空串
     * @param s
     * @return
     */
    public static boolean isBlack(String s){
        return s == null || s.length() == 0;
    }

    public static boolean isMapEmpty(Map m){
        return m == null || m.isEmpty();
    }

    public static boolean isListEmpty(List l){
        return l == null || l.isEmpty();
    }
}
