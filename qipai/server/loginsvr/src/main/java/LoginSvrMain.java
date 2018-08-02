import org.java_websocket.WebSocketImpl;
import utils.log.Log;
import ws.WSSvr;

/**
 * @author Peng Chaowen
 * @version 1.0
 * @date 2018-07-30 20:57
 */
public class LoginSvrMain {
    public static void main(String args[]) {
        WebSocketImpl.DEBUG = false;
        int port = 8887; // 端口
        WSSvr s = new WSSvr(port);
        s.start();
    }
}
