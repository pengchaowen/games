package msg;

/**
 * @author Peng Chaowen
 * @version 1.0
 * @date 2018-08-07 19:49
 */
public class MsgID {
    public static final int USER_DEF_MIN = 257; //用户自定义消息最小值

    public static final int LOGIN_HANDLER_BEGIN = USER_DEF_MIN;
    public static final int LOGIN = LOGIN_HANDLER_BEGIN + 1;
    public static final int LOGIN_HANDLER_END = LOGIN_HANDLER_BEGIN + 50;
}
