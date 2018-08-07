package utils.log;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.*;

/**
 * 日志
 *
 * @author Peng Chaowen
 * @version 1.0
 * @date 2018-05-30 10:25
 */
public class Log {
    private final Logger logger;
    private static final Log log = new Log();
    private static final ReentrantLock saveFileLock = new ReentrantLock();
    private static FileOutputStream saveFile = null;
    private static final SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 格式化
     */
    private class LogFormatter extends Formatter {
        public String format(LogRecord record) {
            StringBuilder builder = new StringBuilder(1000);
            String fs = sf.format(new Date());
            builder.append("["+fs+"] ");
            builder.append(record.getLevel());
            builder.append(" ");
            builder.append(record.getMessage());
            builder.append("\n");
            String outStr = builder.toString();
            //将数据写入文件
            if (Log.saveFile != null) {
                saveFileLock.lock();
                try {
                    Log.saveFile.write(outStr.getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    saveFileLock.unlock();
                }
            }
            return outStr;
        }
    }

    /**
     * 私有构造函数
     */
    private Log() {
        logger = Logger.getLogger("Log");
        logger.setUseParentHandlers(false); //屏蔽使用父类的默认输出
        ConsoleHandler handler = new ConsoleHandler();
        handler.setFormatter(new LogFormatter());
        logger.setLevel(Level.ALL);
        logger.addHandler(handler);
    }


    //    SEVERE > WARNING > INFO > CONFIG > FINE > FINER > FINESET

    /**
     * 设置过滤等级
     *
     * @param level 过滤等级
     */
    public synchronized static void setLevel(Level level) {
        log.logger.setLevel(level);
    }

    /**
     * 设置保存文件路径
     *
     * @param fileNamePath 文件路径
     * @return 是否设置成功
     */
    public synchronized static boolean setSavePath(String fileNamePath) {
        saveFileLock.lock();
        try {
            saveFile = new FileOutputStream(fileNamePath, true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            saveFileLock.unlock();
        }

        return saveFile != null;
    }


    public synchronized static void error(String format, Object... args) {
        String f = String.format("ERROR %s", format);
        String msg = String.format(f, args);
        log.logger.severe(msg);
    }

    public synchronized static void severe(String format, Object... args) {
        String msg = String.format(format, args);
        log.logger.severe(msg);
    }

    public synchronized static void warning(String format, Object... args) {
        String msg = String.format(format, args);
        log.logger.warning(msg);
    }

    public synchronized static void info(String format, Object... args) {
        String msg = String.format(format, args);
        log.logger.info(msg);
    }

    public synchronized static void config(String format, Object... args) {
        String msg = String.format(format, args);
        log.logger.config(msg);
    }

    public synchronized static void fine(String format, Object... args) {
        String msg = String.format(format, args);
        log.logger.fine(msg);
    }

    public synchronized static void finer(String msg) {
        log.logger.finer(msg);
    }

    public synchronized static void finest(String msg) {
        log.logger.finest(msg);
    }
}
