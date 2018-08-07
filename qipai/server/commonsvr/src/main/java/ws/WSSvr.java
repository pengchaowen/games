package ws;

import global.GlobalDef;
import msg.MsgHandler;
import msg.MsgID;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import utils.log.Log;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;

import static global.GlobalDef.MSG_HEAD_SIZE;

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
        Log.info("String msg : " + msg);
        /*
        if (null != message && message.startsWith("online")) {
            String userName = message.replaceFirst("online", message);//用户名
            userJoin(conn, userName);//用户加入
        } else if (null != message && message.startsWith("offline")) {
            userLeave(conn);
        }*/
    }

    @Override
    public void onMessage(WebSocket conn, ByteBuffer message) {
        short id = message.getShort();
        short len = message.getShort(1); //消息长度，不包含包头信息
        if (len > 0 && len <= GlobalDef.MSG_LEN_MAX) {
            byte[] content = new byte[len];
            message.get(content, MSG_HEAD_SIZE, len);

            //系统内部处理
            if (id < MsgID.USER_DEF_MIN) {
                MsgHandler.innerLogicHandler(conn, id, content);
            } else {
                //逻辑处理
                MsgHandler.gameLogicHandler(conn, id, content);
            }
        } else {
            //TODO
        }

        Log.info("ByteBuffer onMessage");
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
