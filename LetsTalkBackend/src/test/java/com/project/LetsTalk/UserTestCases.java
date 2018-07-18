package com.project.LetsTalk;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.project.LetsTalk.dao.UserDAO;
import com.project.LetsTalk.model.User;

public class UserTestCases {
	@Autowired
	private static AnnotationConfigApplicationContext context;
	
	@Autowired
	private static User user;

	@Autowired
	private static UserDAO userDAO;

	@BeforeClass
	public static void init()
	{
		context = new AnnotationConfigApplicationContext();
		context.scan("com.project");
		context.refresh();
		userDAO = (UserDAO)context.getBean("userDAO");
		user = (User)context.getBean("user");
	}

	@Ignore
	public void addUserTestCase()
	{
		user.setEmailID("yatin");
		user.setFirstName("yatin");
		user.setLastName("Upadhyay");
		user.setGender("M");
		user.setD_o_b("02/24/1994");
		user.setMobile("9716836799");
		user.setPassword("yatin");
		boolean actual = userDAO.save(user);
		assertEquals("add user test case", true, actual);

	}
	@Ignore
	public void updateUserTestCase()
	{
		user=userDAO.get("yogender@LT.COM");
		user.setRole("USER");
		boolean actual=userDAO.update(user);
		assertEquals("update user test case", true,actual);
	}
	@Ignore
	public void deleteUserTestcase()
	{
		boolean actual=userDAO.delete("y@LT.COM");
		assertEquals("delete user test case", true, actual);
	}
	@Test
	public void getUserTestCase()
	{
		user=userDAO.get("yatin@LT.COM");
		assertNotNull("get user test case ", user);
	}
	@Ignore
	public void getAllUsersTestCase()
	{
	List<User>	users = userDAO.list();
	assertEquals("get all users " , 3, users.size() );
	}
	@Test
	public void validateUserTestCase()
	{
		assertNotNull("validate user test case", userDAO.validate("yatin@LT.COM","yatin"));
	}
}
