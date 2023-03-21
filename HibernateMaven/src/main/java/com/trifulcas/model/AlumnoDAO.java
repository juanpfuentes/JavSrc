package com.trifulcas.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.trifulcas.HibernateMaven.HibernateUtil;

public class AlumnoDAO {
	public void saveAlumno(Alumno Alumno) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the Alumno object
            session.saveOrUpdate(Alumno);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

	
    public List<Alumno> getAlumnos() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Alumno", Alumno.class).list();
        }
    }
    public Alumno getAlumno(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Alumno.class, id);
        }
    }
    public boolean deleteAlumno(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Alumno alumno= session.get(Alumno.class, id);
            session.remove(alumno);
            return true;
        }catch (Exception ex) {
        	System.out.println(ex.getMessage());
        	return false;
        }
    }
}
