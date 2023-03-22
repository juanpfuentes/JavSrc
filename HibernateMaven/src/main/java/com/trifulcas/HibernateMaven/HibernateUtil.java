package com.trifulcas.HibernateMaven;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import com.trifulcas.model.Address;
import com.trifulcas.model.Alumno;
import com.trifulcas.model.City;
import com.trifulcas.model.Country;

public class HibernateUtil {
	private static SessionFactory sessionFactory;
	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			try {
				Configuration configuration = new Configuration();

				// Hibernate settings equivalent to hibernate.cfg.xml's properties
				Properties settings = new Properties();
				settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
				settings.put(Environment.URL, "jdbc:mysql://mysql-trifulcas.alwaysdata.net:3306/trifulcas_code?useSSL=false");
				settings.put(Environment.USER, "trifulcas_code");
				settings.put(Environment.PASS, "TrifulcasForever");
				settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");

				settings.put(Environment.SHOW_SQL, "true");

				settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");


				configuration.setProperties(settings);
				
				configuration.addAnnotatedClass(Alumno.class);
				configuration.addAnnotatedClass(Country.class);
				configuration.addAnnotatedClass(City.class);
				configuration.addAnnotatedClass(Address.class);

				ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
						.applySettings(configuration.getProperties()).build();
				
				sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return sessionFactory;
	}
}
