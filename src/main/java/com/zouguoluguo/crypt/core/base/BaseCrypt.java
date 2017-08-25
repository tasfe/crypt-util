package com.zouguoluguo.crypt.core.base;

import com.zouguoluguo.crypt.support.CryptException;
import com.zouguoluguo.crypt.support.NumberUtil;
import com.zouguoluguo.crypt.support.PreconditionUtil;

import java.util.*;

/**
 * 实现描述：加解密算法
 *
 * @author zuoguoluguo
 * @version v1.0.0
 * @see
 * @since 2017/8/21
 */
public abstract class BaseCrypt implements Crypt{
    protected static final long mask = 9989071L;

    //需要打乱顺序
    private static final char[] codes = {
            'A','B','C','D','E','F','G',
            'H','I','J','K','L','M','N',
            'O','P','Q','R','S','T',
            'U','V','W','X','Y','Z',
            'a','b','c','d','e','f','g',
            'h','i','j','k','l','m','n',
            'o','p','q','r','s','t',
            'u','v','w','x','y','z',
            '#','$','%','^','&','*','_',
            '+','-','=','<','>','[',']',
            '{','}','(',')'
    };

    //需要打乱顺序
    private static final char[] numbers = {
            '1','2','3','4','5',
            '6','7','8','9','0'
    };

    protected abstract long generateSeed(String s);

    protected abstract String generateCryptPart(String s);

    /**
     * 根据seed 产生加密对照map
     * @param seed
     * @return
     */
    private Map<Character,List<Character>> generateEncryCodeMap(long seed){
        //3.获取codes.lengh的不重复随机数
        CryptRandom cr = new CryptRandom(seed);
        List<Integer> noRepeatNums = cr.getNorepeatNumber(codes.length);

        //4.产生加密映射表
        if (PreconditionUtil.isListEmpty(noRepeatNums)){
            return new HashMap<>();
        }

        int mod = getMod();
        int count = codes.length;
        Map<Character,List<Character>> encryMap = new HashMap<>(count);
        int j = -1;

        for (int i = 0; i < count; i++){
            if (i%mod == 0){
                j++;

                encryMap.put(numbers[j],new ArrayList<>());
            }

            encryMap.get(numbers[j]).add(codes[noRepeatNums.get(i)]);
        }

        return encryMap;
    }

    private Map<Character,Character> generateDecryCodeMap(long seed){
        Map<Character,List<Character>> encryCodeMap = generateEncryCodeMap(seed);
        if (PreconditionUtil.isMapEmpty(encryCodeMap)){
            throw new CryptException("generateCodeDecryMap fail,codeDecryMap is empty");
        }

        Map<Character,Character> codeDecryMap = new HashMap<>();
        encryCodeMap.entrySet().forEach(e->{
            e.getValue().forEach(c->{
                codeDecryMap.put(c,e.getKey());
            });
        });

        return codeDecryMap;
    }

    /**
     * 获取每个待加密字符对应的可用密文个数
     * @return
     */
    private int getMod(){
        return codes.length / numbers.length;
    }

    /**
     * 对字符串进行加密
     * @param s
     * @return
     */
    private String encryptStr(String s,Map<Character,List<Character>> encryMap){

        int mod = getMod();

        if (PreconditionUtil.isBlack(s)){
            throw new CryptException("encry str is empty");
        }

        if (!NumberUtil.isNumber(s)){
            throw new CryptException("encry str has no-number char");
        }

        StringBuilder sb = new StringBuilder("");
        for (char c : s.toCharArray()){
            sb.append(encryMap.get(c).get(NumberUtil.char2int(c)/mod));
        }

        return sb.toString();
    }

    private String decryptStr(String s,Map<Character,Character> decryMap){
        if (PreconditionUtil.isBlack(s)){
            throw new CryptException("decry str is empty");
        }

        if (NumberUtil.isNumber(s)){
            throw new CryptException("decry str is all number");
        }

        StringBuilder sb = new StringBuilder("");
        for (char c : s.toCharArray()){
            sb.append(decryMap.get(c));
        }

        return sb.toString();
    }

    /**
     * 加密数字字符串
     * @param s
     * @return
     */
    protected String encrypNumStr(String s){
        //1.计算seed
        long seed = generateSeed(s);

        //2.产生映射表
        Map<Character,List<Character>> encryCodeMap = generateEncryCodeMap(seed);
        if (PreconditionUtil.isMapEmpty(encryCodeMap)){
            throw new CryptException("encryCodeMap is empty");
        }

        //3.替换字符,完成加密
        String encryPart = generateCryptPart(s);
        return encryptStr(encryPart,encryCodeMap);
    }

    /**
     * 解密字符串
     * @param s
     * @return
     */
    protected String decryptNumStr(String s){
        //1.计算seed
        long seed = generateSeed(s);

        //2.还原加密映射表
        Map<Character,Character> decryCodeMap = generateDecryCodeMap(seed);
        if (PreconditionUtil.isMapEmpty(decryCodeMap)){
            throw new CryptException("decryCodeMap is empty");
        }

        //3.解密
        String decryPart = generateCryptPart(s);
        return decryptStr(decryPart,decryCodeMap);
    }
}
