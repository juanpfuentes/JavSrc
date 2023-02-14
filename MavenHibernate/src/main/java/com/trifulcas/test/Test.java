package com.trifulcas.test;

import java.util.List;

import com.trifulcas.model.Alumno;
import com.trifulcas.model.AlumnoDAO;

public class Test {

	public static void main(String[] args) {
		AlumnoDAO AlumnoDao = new AlumnoDAO();
		Alumno Alumno = new Alumno(null, "Juan Perez", "ana@pi.com", 7);
		AlumnoDao.saveAlumno(Alumno);
		Alumno foo=AlumnoDao.getAlumno(1);
		foo.setMail("pepe@pepito.com");
		AlumnoDao.saveAlumno(foo);
		System.out.println(foo);
		List<Alumno> Alumnos = AlumnoDao.getAlumnos();
		Alumnos.forEach(s -> {
			System.out.println(s.getNombre());
		});
	}

}
