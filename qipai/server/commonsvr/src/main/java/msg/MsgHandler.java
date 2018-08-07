package msg;

import org.java_websocket.WebSocket;

/**
 * @author Peng Chaowen
 * @version 1.0
 * @date 2018-08-07 19:26
 */
public class MsgHandler {
    public static void innerLogicHandler(WebSocket conn, int id, byte[] content){

    }

    public static void gameLogicHandler(WebSocket conn, int id, byte[] content){
        switch (id){
            case MsgID.LOGIN:{

            }break;
        }
    }
}
