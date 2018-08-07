package global;

/**
 * @author Peng Chaowen
 * @version 1.0
 * @date 2018-08-07 19:57
 */
public class GlobalDef {
    public static final int SVR_TYPE_LOGIN = 1; //登录服

    public static final int MSG_ID_SIZE = Short.SIZE / 8; //消息ID大小
    public static final int MSG_LEN_SIZE = Short.SIZE / 8; //消息长度大小
    public static final int MSG_HEAD_SIZE = MSG_ID_SIZE + MSG_LEN_SIZE; //包头长度大小
    public static final int MSG_LEN_MAX = Short.MAX_VALUE; //消息最大长度
}
