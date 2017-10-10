package indi.sword.spring._03annotation;

import org.springframework.stereotype.Service;

@Service
public class UserDao {
	
	public void save(){
		System.out.println("UserDao.addNew() ... 保存新用户");
	}
	
}
