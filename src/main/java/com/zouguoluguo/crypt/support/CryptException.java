package com.zouguoluguo.crypt.support;

/**
 * 实现描述：加密异常类
 *
 * @author zuoguoluguo
 * @version v1.0.0
 * @see
 * @since 2017/8/24
 */
public class CryptException extends RuntimeException{
    public CryptException() {
    }

    public CryptException(String message) {
        super(message);
    }

    public CryptException(String message, Throwable cause) {
        super(message, cause);
    }

    public CryptException(Throwable cause) {
        super(cause);
    }

    public CryptException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
