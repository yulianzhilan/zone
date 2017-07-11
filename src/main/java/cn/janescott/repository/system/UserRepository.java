package cn.janescott.repository.system;

import cn.janescott.domain.system.User;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

/**
 * Created by scott on 2017/6/13.
 */
public interface UserRepository extends JpaRepository<User, Long>{
    /**
     * Cacheable注解缓存方法（或类的所有方法）返回值，缓存Key是方法本身和参数的组合签名
     * 通俗说就是同样的参数调用两次，不会重复执行方法，也是缓存最朴素的动机
     */
    @Cacheable(cacheNames = "user", key = "'findByUsername:username@' + #username")
    User findByUsername(String username);

    @CacheEvict(cacheNames = "user", key = "'findByUsername:username@' + #username")
    // @Modifying标志为修改查询
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update User set password=:password where username=:username")
    int setPasswordByUsername(@Param("password")String password, @Param("username")String username);

}
