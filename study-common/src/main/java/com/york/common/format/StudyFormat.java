package com.york.common.format;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

/**
 * @description: 学习格式化
 * @author: york
 * @date: 2019-8-7 15:48
 * @version: <1.0>
 */
public class StudyFormat {


    public static String currencyFormat(double mount){
        NumberFormat instance = NumberFormat.getCurrencyInstance();
        instance.setCurrency(Currency.getInstance(Locale.SIMPLIFIED_CHINESE));
        return instance.format(mount);
    }


    public static void main(String[] args) {
        System.out.println(StudyFormat.currencyFormat(300d));
    }

}
