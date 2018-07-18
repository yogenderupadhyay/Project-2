package com.project.middleware.restController;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.LetsTalk.dao.FriendDAO;
import com.project.LetsTalk.dao.UserDAO;
import com.project.LetsTalk.model.Friend;
import com.project.LetsTalk.model.User;

@RestController
public class FriendController {
	@Autowired
	FriendDAO friendDAO;
//*************************************send friend request****************************************
	@PostMapping(value = "/sendFriendRequest")
	public ResponseEntity<Friend> sendFriendRequest(@RequestBody Friend friend) {

		if (friendDAO.sendFriendRequest(friend)) {
			return new ResponseEntity<Friend>(friend, HttpStatus.OK);
		} else {
			return new ResponseEntity<Friend>(friend, HttpStatus.NOT_FOUND);
		}
	}
//*************************************show friend list	*****************************************
	@GetMapping(value="/showFriendList/{userName}")
	public ResponseEntity<List<Friend>> showFriendList(@PathVariable("userName") String userName)
	{	
		/*String userName=((User)session.getAttribute("user")).getEmailID();*/
		System.out.println(userName);
		List<Friend> listFriends=friendDAO.friendList(userName);
		//if User have no friends
		if(listFriends.size()==0) {
			
			return new ResponseEntity<List<Friend>>(listFriends,HttpStatus.FOUND);
		}
		if(listFriends.size()>0)
		{
			return new ResponseEntity<List<Friend>>(listFriends,HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<List<Friend>>(listFriends,HttpStatus.NOT_FOUND);
		}
	}
//*************************************show pending friend request List******************************
	@GetMapping(value="/showPendingFriendRequest/{userName}")
	public ResponseEntity<List<Friend>> showPendingFriendRequest(@PathVariable("userName") String userName)
	{
		/*String userName=((User)session.getAttribute("user")).getEmailID();*/
		List<Friend> pendingFriendRequest=friendDAO.pendingFriendRequestList(userName);
		if(pendingFriendRequest.size()==0) {	
			return new ResponseEntity<List<Friend>>(pendingFriendRequest,HttpStatus.FOUND);
		}
		if(pendingFriendRequest.size()>0)
		{
			return new ResponseEntity<List<Friend>>(pendingFriendRequest,HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<List<Friend>>(pendingFriendRequest,HttpStatus.NOT_FOUND);
		}
	}
//************************************show suggested friend List***************************************
	@GetMapping(value="/showSuggestedFriend/{userName}")
	public ResponseEntity<List<User>> showSuggestedFriend(@PathVariable("userName") String userName)
	{	
	    /*String user=((User)session.getAttribute("user")).getEmailID();*/
		List<User> showSuggestedFriend=friendDAO.suggestedPeopleList(userName);
		
		if(showSuggestedFriend.size()==0) {
			
			return new ResponseEntity<List<User>>(showSuggestedFriend,HttpStatus.FOUND);
		}
		
		if(showSuggestedFriend.size()>0)
		{
			return new ResponseEntity<List<User>>(showSuggestedFriend,HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<List<User>>(showSuggestedFriend,HttpStatus.NOT_FOUND);
		}
		
	}
//***********************************accept friend request****************************************
	@GetMapping(value="/acceptFriendRequest/{friendID}")
	public ResponseEntity<Friend> acceptFriendRequest(@PathVariable("friendID") int friendId)
	{
		Friend friend=friendDAO.getFriend(friendId);
		if(friendDAO.acceptFriendRequest(friendId))
		{
			return new ResponseEntity<Friend>(friend,HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<Friend>(friend,HttpStatus.NOT_FOUND);
		}
	}
//***********************************delete friend request****************************************
	@GetMapping(value="/deleteFriendRequest/{friendID}")
	public ResponseEntity<Friend> deleteFriendRequest(@PathVariable("friendID") int friendId)
	{
		Friend friend=friendDAO.getFriend(friendId);
		if(friendDAO.deleteFriendRequest(friendId))
		{
			return new ResponseEntity<Friend>(friend,HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<Friend>(friend,HttpStatus.NOT_FOUND);
		}
	}
}