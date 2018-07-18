package com.project.LetsTalk.dao;

import java.util.List;

import com.project.LetsTalk.model.User;

public interface UserDAO {
	public boolean save(User user);
	public boolean update(User user);
	public boolean delete(String user);
	public User   get(String emailId);
	public  List<User>     list();
	public User validate(String emailID,  String password);
}