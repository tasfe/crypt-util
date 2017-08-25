package com.zouguoluguo.crypt.core.base;

/**
 * 实现描述：加解密行为接口
 *
 * @author yanhuizhang
 * @version v1.0.0
 * @see
 * @since 2017/8/25
 */
public interface Crypt {
    /**
     * 加密
     * @param s
     * @return
     */
    String encry(String s);

    /**
     * 解密
     * @param s
     * @return
     */
    String decry(String s);

    /**
     * mask
     * @param s
     * @return
     */
    String mask(String s);

    /**
     * 是否加密过，不保证是正确的
     * @param s
     * @return
     */
    boolean isEncry(String s);
}
