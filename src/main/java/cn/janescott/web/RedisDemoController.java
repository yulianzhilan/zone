package cn.janescott.web;

import cn.janescott.common.LoggerManage;
import cn.janescott.domain.Person;
import cn.janescott.domain.dto.SidebarDTO;
import cn.janescott.repository.RedisRepository;
import cn.janescott.repository.mapper.SidebarMapper;
import cn.janescott.service.UserService;
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
@RequestMapping(value = "/redis")
public class RedisDemoController {
    @Resource
    private RedisRepository personDao;

    @Resource
    private UserService userService;

//    @Resource
//    private UserRepository userRepository;

    @Resource
    private SidebarMapper sidebarMapper;

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

//    @RequestMapping("/find")
//    @LoggerManage(description = "FIND")
//    public User find(){
//        return userRepository.findByUsername("SCOTT");
//    }
//
//    @RequestMapping("/update")
//    @LoggerManage(description = "UPDATE")
//    public User update(String password, String username){
//        if(StringUtils.isEmpty(password) || StringUtils.isEmpty(username)){
//            throw new RuntimeException("含有空值");
//        }
//        userRepository.setPasswordByUsername(password, username);
//        return userRepository.findByUsername(username);
//    }
//
//    @RequestMapping("/user/{msg}")
//    @LoggerManage(description = "get user")
//    public User getUser(@PathVariable("msg")String msg) throws Exception{
//        User user = userRepository.findByUsername(msg);
//        return user;
//    }

    @LoggerManage(description = "get sidebar")
    @RequestMapping("/sidebar/{id}")
    public SidebarDTO getSidebar(@PathVariable("id")Integer id) throws Exception{
        SidebarDTO dto = sidebarMapper.getSidebar(id);
        return dto;
    }
}
