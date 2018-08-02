package ws;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import utils.log.Log;

import java.net.InetSocketAddress;

/**
 * @author Peng Chaowen
 * @version 1.0
 * @date 2018-07-30 22:54
 */
public class WSSvr extends WebSocketServer {
    public WSSvr(int port) {
        super(new InetSocketAddress(port));
    }

    public WSSvr(InetSocketAddress address) {
        super(address);
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        Log.info("New socket connect");
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        //断开连接时候触发代码
        Log.info(String.format("onClose %d %s %b", code, reason, remote));

        WSMgr.remove(conn);
    }

    @Override
    public void onMessage(WebSocket conn, String msg) {
        Log.info(msg);
        /*
        if (null != message && message.startsWith("online")) {
            String userName = message.replaceFirst("online", message);//用户名
            userJoin(conn, userName);//用户加入
        } else if (null != message && message.startsWith("offline")) {
            userLeave(conn);
        }*/
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        Log.info("onError");
        ex.printStackTrace();
    }

    @Override
    public void onStart() {
        Log.info("onStart");
    }
}
