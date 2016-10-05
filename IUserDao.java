/**
 * 
 */
package com.userdemo.dao;

import java.util.List;

import com.userdemo.model.User;

/**
 * @author shivraj-patil
 *
 */
public interface IUserDao {

	//Create
	public void save(User user);
	//Read
	public User getById(int id);
	//Update
	public void update(User user);
	//Delete
	public void deleteById(int id);
	//Get All
	public List<User> getAll();
}
