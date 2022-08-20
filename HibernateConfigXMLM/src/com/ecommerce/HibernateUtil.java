package com.ecommerce;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
	
	private static final SessionFactory sessionFactory;// Immutable - Encapsulation
	
	static
	{
		try
		{	
			// All the 3 below classes uses BUILDER Pattern.
			StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build(); // Properties storage
			
			Metadata metaData = new MetadataSources(standardRegistry).getMetadataBuilder().build(); // Heart of Hibernate
			
			sessionFactory = metaData.getSessionFactoryBuilder().build();
		}catch(Throwable e)
		{
			throw new ExceptionInInitializerError(e);
		}
	}
	
	public static SessionFactory getSessionFactory()
	{
		return sessionFactory;
	}
	

}
