package com.trifulcas.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONObject;

import com.trifulcas.dao.Actor;
import com.trifulcas.dao.ActorDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ApiRest
 */
@WebServlet("/apirest/*")
public class ApiRest extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ActorDAO actorDAO;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ApiRest() {
		super();
		actorDAO = new ActorDAO();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Devolver los valores. Si no me pasan el id todos los actores
		// Si me pasan el id, el actor que tenga ese id
		// ¿En qué formato? JSON
		try {
			String id = request.getPathInfo();
			System.out.println(id);
			if (id == null) {
				List<Actor> actores = actorDAO.getActors();
				JSONArray actoresJSON = new JSONArray(actores);
				response.getWriter().append(actoresJSON.toString());
			} else {
				int idActor = Integer.parseInt(id.substring(1));
				Actor actor = actorDAO.getActor(idActor);
				JSONObject actorJSON = new JSONObject(actor);
				response.getWriter().append(actorJSON.toString());
			}
		} catch (Exception ex) {
			JSONObject mensaje = new JSONObject();
			mensaje.put("Mensaje", "Error en la petici�n");
			mensaje.put("Error", ex);
			response.getWriter().append(mensaje.toString());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Me mandarán los valores del actor en formato JSON
		// Tendré que insertarlo en la BD
		JSONObject mensaje = new JSONObject();
		try {
			String data = request.getReader().lines().collect(Collectors.joining());
			JSONObject actorJSON = new JSONObject(data);

			Actor actor = new Actor(0, actorJSON.getString("first_name"), actorJSON.getString("last_name"), null);

			if (actorDAO.addActor(actor)) {
				mensaje.put("Mensaje", "Actor insertado con �xito");
			} else {
				mensaje.put("Mensaje", "Error en la inserci�n");
			}
			response.getWriter().append(mensaje.toString());
		} catch (Exception ex) {
			mensaje.put("Mensaje", "Error en la petici�n");
			mensaje.put("Error", ex);
			response.getWriter().append(mensaje.toString());
		}
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Me mandarán los valores del actor incluyendo el id
		// Modificaré el actor que tenga ese id
		JSONObject mensaje = new JSONObject();
		try {
			String id = request.getPathInfo();
			String data = request.getReader().lines().collect(Collectors.joining());
			int idActor = Integer.parseInt(id.substring(1));
			JSONObject actorJSON = new JSONObject(data);
			Actor actor = new Actor(idActor, actorJSON.getString("first_name"), actorJSON.getString("last_name"), null);
			if (actorDAO.updateActor(actor)) {
				mensaje.put("Mensaje", "Actor modificado con �xito");
			} else {
				mensaje.put("Mensaje", "Error en la modificaci�n");
			}
		} catch (Exception ex) {
			mensaje.put("Mensaje", "Error en la petici�n");
			mensaje.put("Error", ex);
		} finally {
			response.getWriter().append(mensaje.toString());
		}
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Me mandarán un id y yo eliminaré ese actor
		JSONObject mensaje = new JSONObject();
		try {
			String id = request.getPathInfo();
			int idActor = Integer.parseInt(id.substring(1));
			if (actorDAO.deleteActor(idActor)) {
				mensaje.put("Mensaje", "Actor eliminado con �xito");
			} else {
				mensaje.put("Mensaje", "Error al eliminar");
			}
		} catch (Exception ex) {
			mensaje.put("Mensaje", "Error en la petici�n");
			mensaje.put("Error", ex);

		} finally {
			response.getWriter().append(mensaje.toString());
		}
	}

}
