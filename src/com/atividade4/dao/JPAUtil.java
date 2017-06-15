package com.atividade4.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("atividade4jdbc");
	
	public static EntityManager getEntityManager(){
		return entityManagerFactory.createEntityManager();
	}
}
