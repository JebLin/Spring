package indi.sword.spring._03annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	public void addNew(){
		System.out.println("UserService.addNew() ... 添加新用户");
		userDao.save();
	}
	
}
