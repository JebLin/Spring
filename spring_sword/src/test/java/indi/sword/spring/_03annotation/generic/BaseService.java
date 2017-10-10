package indi.sword.spring._03annotation.generic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author rd_jianbin_lin
 * @Date 13:27 2017/10/8
 */

//若注解没有指定 bean 的 id, 则类名第一个字母小写即为 bean 的 id
@Service
public class BaseService<T> {

    @Autowired
    public BaseDao<T> baseDao;

    public void addNew(T t){
        baseDao.save(t);
    }
}
