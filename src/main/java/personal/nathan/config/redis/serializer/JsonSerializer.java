package personal.nathan.config.redis.serializer;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

/**
 * Description:
 * <p>
 * Created by nathan.z on 2017/12/24.
 */
public class JsonSerializer<T> implements RedisSerializer<T> {

    @Override
    public byte[] serialize(T t) throws SerializationException {


        return new byte[0];
    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        if (bytes == null) {
            return null;
        }
        return null;
    }
}
