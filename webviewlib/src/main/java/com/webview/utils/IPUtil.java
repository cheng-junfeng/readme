package com.webview.utils;

import android.text.TextUtils;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IPUtil {

    private static final String REGEX_ADDRESS = "(https?|ftp|file)://[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|]";

    public static boolean isValidAddress(String address) {
        if (TextUtils.isEmpty(address)){
            return false;
        }
        Pattern p = Pattern.compile(REGEX_ADDRESS);
        Matcher m = p.matcher(address);
        return m.matches();
    }
}
