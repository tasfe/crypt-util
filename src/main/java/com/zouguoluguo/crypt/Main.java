package com.zouguoluguo.crypt;

import com.zouguoluguo.crypt.core.type.MobileCryptService;

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
        MobileCryptService mcs = MobileCryptService.getInstance();

        System.out.println(mcs.decryptMobile(mcs.encryptMobile("15201328863")));
    }
}
