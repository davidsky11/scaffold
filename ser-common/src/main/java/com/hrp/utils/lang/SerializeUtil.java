package com.hrp.utils.lang;

import net.sf.json.JSONObject;
import org.apache.log4j.Logger;

import java.io.*;

/**
 * SerializeUtil
 * Java原生版的 Serialize
 * @author KVLT
 * @date 2017-03-28.
 */
public class SerializeUtil {

    private static Logger logger = Logger.getLogger(SerializeUtil.class);
    static final Class<?> CLAZZ = SerializeUtil.class;

    public static byte[] serialize(Object value) {
        if (value == null) {
            throw new NullPointerException("Can't serialize null");
        }
        byte[] rv = null;
        ByteArrayOutputStream bos = null;
        ObjectOutputStream os = null;
        try {
            bos = new ByteArrayOutputStream();
            os = new ObjectOutputStream(bos);
            os.writeObject(value);
            os.close();
            bos.close();
            rv = bos.toByteArray();
        } catch (Exception e) {
            logger.error("serialize error : " + JSONObject.fromObject(value));
        } finally {
            close(os);
            close(bos);
        }
        return rv;
    }


    public static Object deserialize(byte[] in) {
        return deserialize(in, Object.class);
    }

    public static <T> T deserialize(byte[] in, Class<T>...requiredType) {
        Object rv = null;
        ByteArrayInputStream bis = null;
        ObjectInputStream is = null;
        try {
            if (in != null) {
                bis = new ByteArrayInputStream(in);
                is = new ObjectInputStream(bis);
                rv = is.readObject();
            }
        } catch (Exception e) {
            logger.error("serialize error : " + in);
        } finally {
            close(is);
            close(bis);
        }
        return (T) rv;
    }

    private static void close(Closeable closeable) {
        if (closeable != null)
            try {
                closeable.close();
            } catch (IOException e) {
                logger.error("close stream error");
            }
    }

}
