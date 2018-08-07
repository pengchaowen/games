package utils.file;


import utils.convert.Convert;

import java.io.File;

/**
 * 文件路径命名
 *
 * @author Peng Chaowen
 * @version 1.0
 * @date 2018-05-30 11:14
 */
public class FilePathName {

    /**
     * 获取当前应用路径
     *
     * @return 应用路径
     */
    public static String getCurAppPath() {
        String curAppPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        return Convert.toCharset(curAppPath, "UTF-8");
    }
}
