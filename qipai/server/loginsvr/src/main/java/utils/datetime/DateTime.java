package utils.datetime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期时间
 *
 * @author Peng Chaowen
 * @version 1.0
 * @date 2018-05-31 12:25
 */
public class DateTime {
    /**
     * 获取当前时间字符串
     *
     * @return 当前时间字符串
     */
    public static String getNowStr() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    /**
     * 获取格式化时间字符串
     *
     * @param pattern 格式化字符串
     * @param date    时间
     * @return 格式化后时间字符串
     */
    public static String getDateStr(String pattern, Date date) {
        return new SimpleDateFormat(pattern).format(date);
    }

    /**
     * 获取间隔日期
     *
     * @param date        源日期
     * @param intervalDay 间隔天数
     * @return 间隔日期
     */
    public static Date getIntervalDate(Date date, int intervalDay) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, intervalDay);
        return calendar.getTime();
    }

    /**
     * 获取相差多少天
     *
     * @param date1 日期1
     * @param date2 日期2
     * @return 返回相差天数
     */
    public static long getDiffDays(Date date1, Date date2) {
        long nd = 1000 * 24 * 60 * 60;
        long diff = date1.getTime() - date2.getTime();
        return diff / nd;
    }

    /**
     * 获取相差多少小时
     *
     * @param date1 日期1
     * @param date2 日期2
     * @return 返回相差小时数
     */
    public static long getDiffHours(Date date1, Date date2) {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long diff = date1.getTime() - date2.getTime();
        return diff % nd / nh;
    }

    /**
     * 获取相差多少分钟
     *
     * @param date1 日期1
     * @param date2 日期2
     * @return 返回相差分钟数
     */
    public static long getDiffMinutes(Date date1, Date date2) {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        long diff = date1.getTime() - date2.getTime();
        return diff % nd % nh / nm;
    }

    /**
     * 判断是否合法的数据
     *
     * @param pattern  模式
     * @param checkVal 检查值
     * @return 非null表示合法数据
     */
    public static Date isValid(String pattern, String checkVal) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        format.setLenient(false);

        Date date = null;
        try {
            date = format.parse(checkVal);
        } catch (ParseException e) {
            e.printStackTrace();
            date = null;
        }

        return date;
    }
}
