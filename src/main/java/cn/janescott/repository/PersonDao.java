package cn.janescott.repository;

import cn.janescott.domain.Person;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by scott on 2017/6/6.
 * 测试redis缓存
 */
@Repository
public class PersonDao {

    @Resource(name = "stringRedisTemplate")
    private ValueOperations<String,String> stringOps;

    @Resource(name = "redisTemplate")
    private ValueOperations<Object, Object> valOps;

    public void stringSet(String name){
        stringOps.set("name", name);
    }

    public String getString(){
        return stringOps.get("name");
    }

    public void save(Person person){
        valOps.set(person.getId(), person);
    }

    public Person get(Object key){
        Object obj = valOps.get(key);
        return obj != null ? (Person)obj : null;
    }

}
