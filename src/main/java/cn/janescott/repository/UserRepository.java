package cn.janescott.repository;

import cn.janescott.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

/**
 * Created by scott on 2017/6/13.
 */
public interface UserRepository extends JpaRepository<User, Long>{
    User findByAccount(String account);

    User findByEmail(String email);

    User findById(Long id);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update User set password=:password where email=:email")
    int setPassword(@Param("password")String password, @Param("email")String email);

}
