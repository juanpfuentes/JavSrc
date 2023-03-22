package com.trifulcas.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


import com.trifulcas.dao.Actor;
import com.trifulcas.dao.ActorDAO;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Controlador
 */
@WebServlet(urlPatterns = {"/actor","/nuevo","/insertar","/editar","/actualizar","/borrar"})
public class Controlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ActorDAO actorDao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Controlador() {
		super();
		actorDao = new ActorDAO();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		
		System.out.println(action);
		try {
			switch (action) {
			case "/nuevo":
				System.out.println("Nuevo");
				nuevoActor(request, response);
				break;
			case "/insertar":
				System.out.println("Insertar");
				insertarActor(request, response);
				break;
			case "/borrar":
				System.out.println("Borrar");
				borrarActor(request, response);

				break;
			case "/editar":
				System.out.println("Editar");
				editarActor(request, response);
				break;
			case "/actualizar":
				System.out.println("Actualizar");
				actualizarActor(request, response);

				break;
			default:
				System.out.println("Index");

				listaActores(request, response);
				break;
			}
		} catch (Exception ex) {
			throw new ServletException(ex);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private void listaActores(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		String search=request.getParameter("search");
		System.out.println(search);
		List<Actor> actores;
		if (search==null) {
			actores = actorDao.getActors();
		}else {
			actores = actorDao.getActors(search);
		}
		
		request.setAttribute("actores", actores);
		RequestDispatcher dispatcher = request.getRequestDispatcher("actores.jsp");
		dispatcher.forward(request, response);
	}

	private void nuevoActor(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("nuevo.jsp");
		dispatcher.forward(request, response);
	}

	private void insertarActor(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		String first_name = request.getParameter("first_name");
		String last_name = request.getParameter("last_name");
		Actor actor = new Actor(0, first_name, last_name, null);
		actorDao.addActor(actor);
		response.sendRedirect("actor");
	}

	private void editarActor(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Actor actor = actorDao.getActor(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("editar.jsp");
		request.setAttribute("actor", actor);
		dispatcher.forward(request, response);

	}

	private void actualizarActor(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int actor_id = Integer.parseInt(request.getParameter("actor_id"));
		String first_name = request.getParameter("first_name");
		String last_name = request.getParameter("last_name");
		Actor actor = new Actor(actor_id, first_name, last_name, null);
		actorDao.updateActor(actor);
		response.sendRedirect("./actor");
	}

	private void borrarActor(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		actorDao.deleteActor(id);
		response.sendRedirect("actor");

	}
}
