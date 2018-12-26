package personal.nathan.config.redis.serializer;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

/**
 * Description:
 * <p>
 * Created by nathan.z on 2017/12/24.
 */
public class StringSerializer implements RedisSerializer<String> {

    @Override
    public byte[] serialize(String s) throws SerializationException {
        return s == null ? null : s.getBytes();
    }

    @Override
    public String deserialize(byte[] bytes) throws SerializationException {
        return bytes == null ? null : new String(bytes);
    }
}
