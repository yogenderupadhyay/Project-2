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
		System.out.println("inside sent friend request restcontroller "+friend);
		if(friendDAO.isFriendRequestAlreadySent(friend)==true) {
			return new ResponseEntity<Friend>(friend, HttpStatus.FOUND);
		}
		if(friendDAO.alreadyHaveFriendRequest(friend)==true) {
			return new ResponseEntity<Friend>(friend, HttpStatus.BAD_REQUEST);
		}
		if (friendDAO.sendFriendRequest(friend)) {
			return new ResponseEntity<Friend>(friend, HttpStatus.OK);
		} else {
			return new ResponseEntity<Friend>(friend, HttpStatus.NOT_FOUND);
		}
	}
//*************************************show friend list	*****************************************
	@PostMapping(value="/showFriendList")
	public ResponseEntity<List<User>> showFriendList(@RequestBody User user)
	{	
		/*String userName=((User)session.getAttribute("user")).getEmailID();*/
		System.out.println(user);
		List<User> listFriends=friendDAO.friendList(user);
		//if User have no friends
		if(listFriends.size()==0) {
			
			return new ResponseEntity<List<User>>(listFriends,HttpStatus.FOUND);
		}
		if(listFriends.size()>0)
		{
			return new ResponseEntity<List<User>>(listFriends,HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<List<User>>(listFriends,HttpStatus.NOT_FOUND);
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
	//*************************************show sent friend request List******************************
		@GetMapping(value="/showSentFriendRequest/{userName}")
		public ResponseEntity<List<Friend>> showSentFriendRequest(@PathVariable("userName") String userName)
		{
			/*String userName=((User)session.getAttribute("user")).getEmailID();*/
			List<Friend> sentFriendRequest=friendDAO.sentFriendRequestList(userName);
			if(sentFriendRequest.size()==0) {	
				return new ResponseEntity<List<Friend>>(sentFriendRequest,HttpStatus.FOUND);
			}
			if(sentFriendRequest.size()>0)
			{
				return new ResponseEntity<List<Friend>>(sentFriendRequest,HttpStatus.OK);
			}
			else
			{
				return new ResponseEntity<List<Friend>>(sentFriendRequest,HttpStatus.NOT_FOUND);
			}
		}
//************************************show suggested friend List***************************************
	@PostMapping(value="/showSuggestedFriend")
	public ResponseEntity<List<User>> showSuggestedFriend(@RequestBody User user)
	{	
	    /*String user=((User)session.getAttribute("user")).getEmailID();*/
		System.out.println("inside friend controller");
		List<User> showSuggestedFriend=friendDAO.suggestedPeopleList(user);
		
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
	@GetMapping(value="/deleteFriend/{friendID}")
	public ResponseEntity<Friend> deleteFriend(@PathVariable("friendID") int friendId)
	{
		Friend friend=friendDAO.getFriend(friendId);
		if(friendDAO.deleteFriend(friendId))
		{
			return new ResponseEntity<Friend>(friend,HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<Friend>(friend,HttpStatus.NOT_FOUND);
		}
	}
}
