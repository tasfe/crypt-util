package com.zouguoluguo.crypt.support;

/**
 * 实现描述：
 *
 * @author zuoguoluguo
 * @version v1.0.0
 * @see
 * @since 2017/8/24
 */
public class NumberUtil {
    public static boolean isNumber(String s){
        if (PreconditionUtil.isBlack(s)){
            return false;
        }

        for (char c : s.toCharArray()){
            if (c<'0' || c>'9'){
                return false;
            }
        }

        return true;
    }

    public static int char2int(char c){
        switch (c){
            case '0':{
                return 0;
            }
            case '1':{
                return 1;
            }
            case '2':{
                return 2;
            }
            case '3':{
                return 3;
            }
            case '4':{
                return 4;
            }
            case '5':{
                return 5;
            }
            case '6':{
                return 6;
            }
            case '7':{
                return 7;
            }
            case '8':{
                return 8;
            }
            case '9':{
                return 9;
            }
            default:{
                return 0;
            }
        }
    }
}
