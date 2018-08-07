package utils.convert;

import java.io.UnsupportedEncodingException;

/**
 * 数据转换
 *
 * @author Peng Chaowen
 * @version 1.0
 * @date 2018-05-30 19:57
 */
public class Convert {
    /**
     * 将源字符串转为toCharset编码字符串
     * 此函数一般用于从本进程中创建的字符串转为toCharset编码字符串
     *
     * @param src       源字符串
     * @param toCharset 目标字符集
     * @return 目标字符集字符串
     */
    public static String toCharset(String src, String toCharset) {
        try {
            return java.net.URLDecoder.decode(src, toCharset);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return "";
    }


    /**
     * 将源字符串从fromCharset字符集转为toCharset编码字符串
     * 此函数一般用于从非本进程创建字符串（比如文本文件中）转为toCharset编码字符串
     *
     * @param src         源字符串
     * @param fromCharset 源字符集
     * @param toCharset   目标字符集
     * @return 目标字符集字符串
     */
    public static String toCharset(String src, String fromCharset, String toCharset) {
        try {
            return new String(src.getBytes(fromCharset), toCharset);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return "";
    }


    /**
     * 字符串转换为整数，也可以用户判断某个字符串是否为有效数字
     * @param val 字符串值
     * @return 返回对应的Integer，非有效值则为null
     */
    public static Integer toInt(String val) {
        try {
            return Integer.valueOf(val);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
