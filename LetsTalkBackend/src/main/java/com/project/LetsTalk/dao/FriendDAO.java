package com.project.LetsTalk.dao;

import java.util.List;

import com.project.LetsTalk.model.Friend;
import com.project.LetsTalk.model.User;

public interface FriendDAO {
	
	public List<Friend> friendList(String userName);
	public List<Friend> pendingFriendRequestList(String userName);
	public List<User> suggestedPeopleList(String userName);

	public boolean sendFriendRequest(Friend friend);
	public boolean acceptFriendRequest(int friendId);
	public boolean deleteFriendRequest(int friendId);
	public Friend getFriend(int friendId);
}
