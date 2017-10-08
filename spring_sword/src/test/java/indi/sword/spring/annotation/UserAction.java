package indi.sword.spring.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserAction {
	
	@Autowired
	private UserService userService;
	
	public void execute(){
		System.out.println("UserAction.execute()... 接受请求");
		userService.addNew();
	}
	
}
