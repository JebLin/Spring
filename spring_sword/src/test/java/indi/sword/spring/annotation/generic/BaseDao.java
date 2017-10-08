package indi.sword.spring.annotation.generic;

import org.springframework.stereotype.Repository;

/**
 * @Description
 * @Author rd_jianbin_lin
 * @Date 13:23 2017/10/8
 */
@Repository
public class BaseDao<T> {

    public void save(T t){
        System.out.println("BaseDao.save() ... " );
    }
}
