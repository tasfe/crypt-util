package com.zouguoluguo.crypt.core.type;

import com.zouguoluguo.crypt.core.base.CryptService;
import com.zouguoluguo.crypt.support.CryptException;
import com.zouguoluguo.crypt.support.NumberUtil;
import com.zouguoluguo.crypt.support.PreconditionUtil;

/**
 * 实现描述：手机加解密算法
 *
 * @author zouguoluguo
 * @version v1.0.0
 * @see
 * @since 2017/8/24
 */
public class MobileCryptService extends CryptService{
    private static class ServiceHolder{
        private static final MobileCryptService mcs = new MobileCryptService();
    }

    private static final int mobile_count = 11;

    private MobileCryptService(){}

    public static MobileCryptService getInstance(){
        return ServiceHolder.mcs;
    }

    @Override
    protected long generateSeed(String m) {
        //获取手机号前三位和后四位，用于生产seed
        int preNum = Integer.valueOf(getPreStr(m));
        int tailNum = Integer.valueOf(getTailStr(m));

        return (preNum ^ tailNum) & mask;
    }

    private void baseCheck(String mobile){
        if (PreconditionUtil.isBlack(mobile)){
            throw new CryptException("mobile is empty");
        }

        if (mobile.length() != mobile_count){
            throw new CryptException("it is not mobile number");
        }
    }

    private void checkEncry(String mobile) {
        baseCheck(mobile);

        if (!NumberUtil.isNumber(mobile)){
            throw new CryptException("encry mobile str is invalid");
        }
    }

    private void checkDecry(String mobile){
        baseCheck(mobile);

        if (NumberUtil.isNumber(mobile)){
            throw new CryptException("decry mobile str is invalid");
        }
    }

    @Override
    protected String generateCryptPart(String s) {
        return  getEncryPart(s);
    }

    /**
     * 获取手机号前三位
     * @param mobile
     * @return
     */
    private String getPreStr(String mobile){
        return mobile.substring(0,3);
    }

    /**
     * 获取手机号中间四位，用于加密
     * @param mobile
     * @return
     */
    private String getEncryPart(String mobile){
        return mobile.substring(3,7);
    }

    /**
     * 获取手机号后四位
     * @param mobile
     * @return
     */
    private String getTailStr(String mobile){
        return mobile.substring(7,mobile.length());
    }

    /**
     * 加密手机号
     * <p>手机号为11位</p>
     * <p>加密中间4位，例如明文15288369873，加密后密文：152ABEX9873</p>
     * @param decryMobile
     * @return
     */
    public String encryptMobile(String decryMobile){
        if (isEncryMobile(decryMobile)){
            return decryMobile;
        }

        return getPreStr(decryMobile)+encrypNumStr(decryMobile)+getTailStr(decryMobile);
    }

    private boolean isEncryMobile(String encryMobile){
        checkEncry(encryMobile);

        return NumberUtil.isNumber(getPreStr(encryMobile)) && !NumberUtil.isNumber(getEncryPart(encryMobile))
                && NumberUtil.isNumber(getTailStr(encryMobile));
    }

    private boolean isDecryMobile(String decryMobile){
        checkDecry(decryMobile);

        return NumberUtil.isNumber(decryMobile);
    }

    /**
     * 解密手机号
     * <p>手机号为11位</p>
     * <p>解密中间4位，例如密文：152ABEX9873，解密后明文15288369873</p>
     * @param encryMobile
     * @return
     */
    public String decryptMobile(String encryMobile){
        if (isDecryMobile(encryMobile)){
            return encryMobile;
        }

        return getPreStr(encryMobile)+decryptNumStr(encryMobile)+getTailStr(encryMobile);
    }
}
