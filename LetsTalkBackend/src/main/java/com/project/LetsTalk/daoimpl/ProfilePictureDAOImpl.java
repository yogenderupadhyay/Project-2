package com.project.LetsTalk.daoimpl;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.LetsTalk.dao.ProfilePictureDAO;
import com.project.LetsTalk.model.ProfilePicture;

@Repository("profilePicDAO")
@Transactional
public class ProfilePictureDAOImpl implements ProfilePictureDAO{
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public boolean save(ProfilePicture profilePicture) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(profilePicture);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public ProfilePicture getProfilePicture(String userName) {
		return (ProfilePicture) sessionFactory.getCurrentSession().get(ProfilePicture.class, userName);
	}

}
