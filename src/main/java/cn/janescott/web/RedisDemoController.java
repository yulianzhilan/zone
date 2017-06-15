package cn.janescott.web;

import cn.janescott.common.LoggerManage;
import cn.janescott.domain.Person;
import cn.janescott.domain.system.User;
import cn.janescott.repository.PersonDao;
import cn.janescott.repository.system.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by scott on 2017/6/6.
 * 测试redis缓存
 */
@RestController
public class RedisDemoController {
    @Autowired
    private PersonDao personDao;

    @RequestMapping("/setPerson")
    public void setPerson(){
        Person person = new Person();
        person.setAge(23);
        person.setName("scott");
        person.setId("120305109");
        personDao.save(person);
    }

    @RequestMapping("/getPerson/{id}")
    public Person getPerson(@PathVariable("id") String id){
        return personDao.get(id);
    }

    @RequestMapping("/set/{name}")
    public void set(@PathVariable("name")String name){
        personDao.stringSet(name);
    }

    @RequestMapping("/get")
    public String get(){
        return personDao.getString();
    }


    @Resource
    private
    UserRepository userRepository;

    @RequestMapping("/find")
    @LoggerManage(description = "JPA")
    public User find(){
        return userRepository.findByAccount("SCOTT");
    }
}
