package com.avoole.common.util;

import android.text.TextUtils;
import android.util.Base64;
import com.avoole.common.Applog;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Base64Util {
    private static final String TAG = "Base64Util";
    private static Set<String> encodedUids = new HashSet();
    private static Set<String> decodedUids = new HashSet();

    public static String encode(String key) {
        return encode(key, "UTF-8");
    }

    public static String encode(String key, String encode) {
        if (TextUtils.isEmpty(key)) {
            return null;
        }

        if (encodedUids.contains(key)) {
            return key;
        }

        byte[] bytes = null;
        try {
            bytes = key.toLowerCase().getBytes(encode);
        } catch (Exception e) {
            bytes = key.toLowerCase().getBytes();
        }

        byte[] encodeBytes = Base64.encode(bytes, 0);
        String enc = new String(encodeBytes).replaceAll("[\r|\n]", "");
        Applog.i("Base64Util", "String:" + key + "-----encoded string:" + enc);
        encodedUids.add(enc);
        return enc;
    }

    public static String decode(String encodeStr) {
        if (TextUtils.isEmpty(encodeStr)) {
            return encodeStr;
        }

        if (decodedUids.contains(encodeStr)) {
            return encodeStr;
        }

        if (!isLetter(encodeStr)) {
            StringWriter sw = new StringWriter();
            RuntimeException re = new RuntimeException("decode error:");
            re.printStackTrace(new PrintWriter(sw));
            String crashInfo = sw.toString();
            Applog.e("Base64Util", "decode cause" + crashInfo);
            return encodeStr;
        }

        byte[] result = null;
        try {
            result = Base64.decode(encodeStr, 0);
        } catch (Exception e) {
            result = encodeStr.getBytes();
        }
        String res = new String(result);
        decodedUids.add(res);
        Applog.i("Base64Util", "String:" + encodeStr + "-----decoded string:" + res);
        return res;
    }

    public static boolean isLetter(String kk) {
        Pattern p = Pattern.compile("^[A-Za-z0-9/+=]*$");
        Matcher matcher = p.matcher(kk);
        return matcher.find();
    }

    public static String fetchEncodeAccount(String mPrefix, String account) {
        String result = null;
        if ((mPrefix != null) && (mPrefix.startsWith("u")))
            result = encode(account);
        else {
            result = account;
        }

        return result;
    }

    public static String fetchDecodeAccount(String mPrefix, String account) {
        String result = null;
        if ((mPrefix != null) && (mPrefix.startsWith("u"))) {
            result = decode(account);
            Applog.i("Base64Util", "-----userId:" + result);
        } else {
            result = account;
        }

        return result;
    }

    public static String fetchDecodeAccount(String lid) {
        String result = null;
        if (TextUtils.isEmpty(lid)) {
            return result;
        }

        if (lid.length() <= 8) {
            return lid;
        }

        String prefix = lid.substring(0, 8);
        String account = lid.substring(8);
        return fetchDecodeAccount(prefix, account);
    }

    public static String fetchEcodeLongUserId(String lid) {
        String result = null;
        if (TextUtils.isEmpty(lid)) {
            return result;
        }

        if (!lid.startsWith("u")) {
            return lid;
        }

        if (lid.length() <= 8) {
            return lid;
        }

        String prefix = lid.substring(0, 8);
        String account = lid.substring(8);
        return prefix + fetchEncodeAccount(prefix, account);
    }

    public static String fetchDecodeLongUserId(String lid) {
        String result = null;
        if (TextUtils.isEmpty(lid)) {
            return result;
        }

        if (!lid.startsWith("u")) {
            return lid;
        }

        if (lid.length() <= 8) {
            return lid;
        }

        String prefix = lid.substring(0, 8);
        String account = lid.substring(8);
        return prefix + fetchDecodeAccount(prefix, account);
    }
}
