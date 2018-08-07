import com.google.gson.Gson;
import com.sun.org.apache.bcel.internal.generic.BREAKPOINT;
import global.GlobalDef;
import global.StartArgs;
import org.java_websocket.WebSocketImpl;
import utils.log.Log;
import ws.WSSvr;

/**
 * 通用服务器入口
 *
 * @author Peng Chaowen
 * @version 1.0
 * @date 2018-07-30 20:57
 */
public class SvrMain {
    public static void main(String args[]) {
        //传入的参数为json格式
        if (args.length < 1) {
            Log.info("App can not start, args is empty!");
            return;
        }

        Gson gson = new Gson();
        StartArgs startArgs = gson.fromJson(args[0], StartArgs.class);
        switch (startArgs.svrType) {
            case GlobalDef.SVR_TYPE_LOGIN: {
                Log.info("App login svr start!");
                WebSocketImpl.DEBUG = false;
                int port = 8887; // 端口
                WSSvr s = new WSSvr(port);
                s.start();
            }
            break;

            default: {
                Log.info("App start err : svr type %d !", startArgs.svrType);
            }
            break;
        }
    }
}
