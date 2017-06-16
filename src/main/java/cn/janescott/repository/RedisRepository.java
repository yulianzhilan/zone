package cn.janescott.repository;

import cn.janescott.domain.Person;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

/**
 * Created by scott on 2017/6/6.
 * 测试redis缓存
 */
@Repository
public class RedisRepository {

    @Resource(name = "redisTemplate")
    private RedisTemplate<Object, Object> redisTemplate;

    @Resource(name = "stringRedisTemplate")
    private StringRedisTemplate stringRedisTemplate;

//    @Resource(name = "stringRedisTemplate")
//    private ValueOperations<String,String> stringOps = stringRedisTemplate.opsForValue();
//
//    @Resource(name = "redisTemplate")
//    private ValueOperations<Object, Object> valOps = redisTemplate.opsForValue();
//
    public void stringSet(String name){
        stringRedisTemplate.opsForValue().set("name", name);
    }

    public String getString(){
        return stringRedisTemplate.opsForValue().get("name");
    }

    public void save(Person person){
        redisTemplate.opsForValue().set(person.getId(), person);
    }

    public Person get(Object key){
        Object obj = redisTemplate.opsForValue().get(key);
        return obj != null ? (Person)obj : null;
    }

}
