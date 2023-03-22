package com.trifulcas.model;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.trifulcas.HibernateMaven.HibernateUtil;

public class DAO<T> {
	private Class<T> entityClass;

	public DAO(Class<T> clase) {
		this.entityClass = clase;
	}

	public void save(T Objeto) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			session.saveOrUpdate(Objeto);
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public List<T> getAll() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("from " + entityClass.getSimpleName(), entityClass).list();
		}
	}

	public T get(int id) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.get(entityClass, id);
		}
	}

	public boolean delete(int id) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			T objeto = session.get(entityClass, id);
			session.remove(objeto);
			return true;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return false;
		}
	}
}
