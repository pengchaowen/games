package ws;

import org.java_websocket.WebSocket;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Peng Chaowen
 * @version 1.0
 * @date 2018-07-30 21:34
 */
public class WSMgr {
    private static class InnerObj {
        WebSocket ws;
        Integer id;
        Object userObj;

        public InnerObj(WebSocket ws, Integer id, Object userObj) {
            this.ws = ws;
            this.id = id;
            this.userObj = userObj;
        }
    }

    private static final HashMap<WebSocket, InnerObj> wsMgr = new HashMap();
    private static final HashMap<Integer, InnerObj> idMgr = new HashMap();
    private static final ReentrantLock lock = new ReentrantLock();
    private static Integer idCreator = 0;

    /**
     * 增加
     *
     * @param ws      websocket
     * @param userObj user object
     * @return id
     */
    public static Integer add(WebSocket ws, Object userObj) {
        lock.lock();
        ++idCreator;
        try {
            InnerObj innerObj = new InnerObj(ws, idCreator, userObj);
            wsMgr.put(ws, innerObj);
            idMgr.put(idCreator, innerObj);
        } finally {
            lock.unlock();
        }
        return idCreator;
    }

    /**
     * 根据id获取对应的websocket
     *
     * @param id id
     * @return websocket
     */
    public static WebSocket getWS(Integer id) {
        WebSocket ret = null;
        lock.lock();
        try {
            if (idMgr.containsKey(id)) {
                ret = idMgr.get(id).ws;
            }
        } finally {
            lock.unlock();
        }
        return ret;
    }

    /**
     * 根据websocket获取对饮的id
     *
     * @param ws websocket
     * @return id
     */
    public static Integer getID(WebSocket ws) {
        Integer ret = null;
        lock.lock();
        try {
            if (wsMgr.containsKey(ws)) {
                ret = wsMgr.get(ws).id;
            }
        } finally {
            lock.unlock();
        }
        return ret;
    }

    /**
     * 根据id获取对应的用户对象
     *
     * @param id id
     * @return user object
     */
    public static Object getUserObj(Integer id) {
        Object ret = null;
        lock.lock();
        try {
            if (idMgr.containsKey(id)) {
                ret = idMgr.get(id).userObj;
            }
        } finally {
            lock.unlock();
        }
        return ret;
    }

    /**
     * 根据websocket获取对应的用户对象
     *
     * @param ws websocket
     * @return 用户对象
     */
    public static Object getUserObj(WebSocket ws) {
        Object ret = null;
        lock.lock();
        try {
            if (wsMgr.containsKey(ws)) {
                ret = wsMgr.get(ws).userObj;
            }
        } finally {
            lock.unlock();
        }
        return ret;
    }

    /**
     * 根据websocket移除
     *
     * @param ws websocket
     */
    public static void remove(WebSocket ws) {
        lock.lock();
        try {
            if (wsMgr.containsKey(ws)) {
                InnerObj innerObj = wsMgr.get(ws);
                innerObj.ws.close();
                idMgr.remove(innerObj.id);
                wsMgr.remove(ws);
            }
        } finally {
            lock.unlock();
        }
    }

    /**
     * 根据id移除
     *
     * @param id id
     */
    public static void remove(Integer id) {
        lock.lock();
        try {
            if (idMgr.containsKey(id)) {
                InnerObj innerObj = idMgr.get(id);
                innerObj.ws.close();
                wsMgr.remove(innerObj.ws);
                idMgr.remove(id);
            }
        } finally {
            lock.unlock();
        }
    }

    /**
     * 根据websocket发送String数据
     *
     * @param ws   websocket
     * @param data 数据
     */
    public static void sendMsg(WebSocket ws, String data) {
        lock.lock();
        try {
            if (wsMgr.containsKey(ws)) {
                ws.send(data);
            }
        } finally {
            lock.unlock();
        }
    }

    /**
     * 根据websocket发送byte[]数据
     *
     * @param ws   websocket
     * @param data 数据
     */
    public static void sendMsg(WebSocket ws, byte[] data) {
        lock.lock();
        try {
            if (wsMgr.containsKey(ws)) {
                ws.send(data);
            }
        } finally {
            lock.unlock();
        }
    }

    /**
     * 根据websocket发送ByteBuffer数据
     *
     * @param ws   websocket
     * @param data 数据
     */
    public static void sendMsg(WebSocket ws, ByteBuffer data) {
        lock.lock();
        try {
            if (wsMgr.containsKey(ws)) {
                ws.send(data);
            }
        } finally {
            lock.unlock();
        }
    }

    /**
     * 根据id发送字符串数据
     *
     * @param id   id
     * @param data 数据
     */
    public static void sendMsg(Integer id, String data) {
        lock.lock();
        try {
            if (idMgr.containsKey(id)) {
                idMgr.get(id).ws.send(data);
            }
        } finally {
            lock.unlock();
        }
    }

    /**
     * 根据id发送字符串数据
     *
     * @param id   id
     * @param data 数据
     */
    public static void sendMsg(Integer id, byte[] data) {
        lock.lock();
        try {
            if (idMgr.containsKey(id)) {
                idMgr.get(id).ws.send(data);
            }
        } finally {
            lock.unlock();
        }
    }

    /**
     * 根据id发送字符串数据
     *
     * @param id   id
     * @param data 数据
     */
    public static void sendMsg(Integer id, ByteBuffer data) {
        lock.lock();
        try {
            if (idMgr.containsKey(id)) {
                idMgr.get(id).ws.send(data);
            }
        } finally {
            lock.unlock();
        }
    }

    /**
     * 判断是否存在ws
     *
     * @param ws websocket
     * @return true存在；false不存在
     */
    public boolean isContain(WebSocket ws) {
        boolean ret = false;
        lock.lock();
        try {
            return wsMgr.containsKey(ws);
        } finally {
            lock.unlock();
        }
    }

    /**
     * 判断是否存在id
     *
     * @param id id
     * @return true存在；false不存在
     */
    public boolean isContain(Integer id) {
        boolean ret = false;
        lock.lock();
        try {
            return idMgr.containsKey(id);
        } finally {
            lock.unlock();
        }
    }
}
