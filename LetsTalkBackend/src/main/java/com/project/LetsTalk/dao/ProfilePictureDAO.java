package com.project.LetsTalk.dao;

import com.project.LetsTalk.model.ProfilePicture;

public interface ProfilePictureDAO {

	public boolean save(ProfilePicture profilePicture);
	public ProfilePicture getProfilePicture(String userName);
}
