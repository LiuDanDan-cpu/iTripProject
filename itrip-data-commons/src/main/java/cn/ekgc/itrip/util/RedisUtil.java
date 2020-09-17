package cn.ekgc.itrip.util;

import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * <b>redis工具类</b>
 */
@Component//该注解的意思为 组件 （把普通pojo实例化到spring容器中，相当于配置文件中的）
public class RedisUtil {
    @Autowired
    private StringRedisTemplate template;

    /**
     * <b>将对象存入redis 中</b>
     * @param token
     * @param value
     * @param expireTime
     * @throws Exception
     */
    public void saveToken(String token,Object value,Long expireTime)throws Exception{
        //需要将value变为字符串 变为字符串需要使用到下边的对象 所以要提前new 出来
        JsonMapper jsonMapper=new JsonMapper();
        //将传过来的对象变为字符串形式的值
        String strValue=jsonMapper.writeValueAsString(value);
        //所传过来的对象放入redis 中
        template.opsForValue().set(token,strValue);
        //设置存储时间
        template.expire(token,expireTime, TimeUnit.MINUTES);
    }

    /**
     * <b>获取所需对象</b>
     * @param key
     * @param type
     * @return
     * @throws Exception
     */
    public Object getFromRedis(String key,Class type)throws Exception{
        //先从redis 中查是否有该对象
        String value=template.opsForValue().get(key);
        if (value!=null){
            //使用jsonMapper 将字符串变为所需要的对象
            JsonMapper jsonMapper=new JsonMapper();
//            将字符串变为自己所需要的对象
            Object isValue=jsonMapper.readValue(value,type);
//            返回该对象
            return isValue;
        }
        return null;
    }
}
