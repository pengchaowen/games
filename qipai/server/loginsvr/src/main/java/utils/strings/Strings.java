package utils.strings;

import java.util.regex.Pattern;

/**
 * @author Peng Chaowen
 * @version 1.0
 * @date 2018-06-08 17:54
 */
public class Strings {
    /**
     * 判断是否为16进制数
     *
     * @param c 字符
     * @return 是否为16进制数
     */
    public static boolean isHex(char c) {
        if (((c >= '0') && (c <= '9')) ||
                ((c >= 'a') && (c <= 'f')) ||
                ((c >= 'A') && (c <= 'F')))
            return true;
        else
            return false;
    }

    /**
     * 判断是否中文
     *
     * @param c 字符
     * @return 是否中文
     */
    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
            return true;
        }
        return false;
    }


    /**
     * 字符串是否包含中文
     *
     * @param str 测试的字符串
     * @return 是否包含中文
     */
    public static boolean isContainChinese(String str) {
        char[] c = str.toCharArray();
        for (int i = 0; i < str.length(); i++) {
            if (isChinese(c[i])) {
                return true;
            }
        }
        return false;
    }


    /**
     * 过滤中文
     *
     * @param str 过滤字符串
     * @return 过滤中文后的字符串
     */
    public static String filterChinese(String str) {
        StringBuffer sb = new StringBuffer();
        char[] c = str.toCharArray();
        for (int i = 0; i < str.length(); i++) {
            if (!isChinese(c[i])) {
                sb.append(c[i]);
            }
        }
        return sb.toString();
    }


    /**
     * 保留中文
     *
     * @param str 过滤字符串
     * @return 过滤后只保留中文的字符串
     */
    public static String retainChinese(String str) {
        StringBuffer sb = new StringBuffer();
        char[] c = str.toCharArray();
        for (int i = 0; i < str.length(); i++) {
            if (isChinese(c[i])) {
                sb.append(c[i]);
            }
        }
        return sb.toString();
    }


    /**
     * 转换到有效的url（包括对于源url带有(%english)和对(%中文)的转换
     *
     * @param url 源url
     * @return 有效字符串
     */
    public static String toValidURL(String url) {
        StringBuilder sb = new StringBuilder(url);

        for (int i = 0; i < sb.length(); i++) {
            char c = sb.charAt(i);
            //判断是否为转码符号%
            if (c == '%') {
                if (((i + 1) < sb.length() - 1) && ((i + 2) < sb.length() - 1)) {
                    char first = sb.charAt(i + 1);
                    char second = sb.charAt(i + 2);
                    //如只是普通的%则转为%25
                    if (!(isHex(first) && isHex(second)))
                        sb.insert(i + 1, "25");
                } else {//如只是普通的%则转为%25
                    sb.insert(i + 1, "25");
                }

            }
        }

        return sb.toString();
    }

    /**
     * 判断是否有效的ipv4地址格式
     *
     * @param ip 测试的ip地址
     * @return 是否ipv4格式
     */
    public static boolean isIPV4(String ip) {
        String pattern = "^((25[0-5]|2[0-4]\\d|[01]?\\d\\d?)\\.){3}(25[0-5]|2[0-4]\\d|[01]?\\d\\d?)$";
        return Pattern.matches(pattern, ip);
    }

    /**
     * 判断是否有效的ipv6地址格式
     *
     * @param ip 测试的ip地址
     * @return 是否ipv6格式
     */
    public static boolean isIPV6(String ip) {
        String pattern = "^([\\da-fA-F]{1,4}:){7}[\\da-fA-F]{1,4}$";
        return Pattern.matches(pattern, ip);
    }
}
