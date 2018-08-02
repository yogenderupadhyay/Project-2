package com.project.LetsTalk.dao;

import java.util.List;

import com.project.LetsTalk.model.Friend;
import com.project.LetsTalk.model.User;

public interface FriendDAO {
	
	public List<User> friendList(User user);
	public List<Friend> pendingFriendRequestList(String userName);
	public List<Friend> sentFriendRequestList(String userName);
	public List<User> suggestedPeopleList(User user);

	public boolean sendFriendRequest(Friend friend);
	public boolean acceptFriendRequest(int friendId);
	public boolean deleteFriend(int friendId);
	public Friend getFriend(int friendId);
	
	public boolean isFriendRequestAlreadySent(Friend friend);
	public boolean alreadyHaveFriendRequest(Friend friend);
	
}
