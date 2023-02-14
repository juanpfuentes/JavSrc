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
 * Servlet implementation class Api
 */
@WebServlet("/api/*")
public class Api extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ActorDAO actorDao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Api() {
		super();
		actorDao = new ActorDAO();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getPathInfo();
		if (id == null) {
			List<Actor> actores = actorDao.getActors();
			JSONArray jsArray = new JSONArray(actores);
			response.getWriter().append(jsArray.toString());
		} else {
			id = id.substring(1);
			Actor actor = actorDao.getActor(Integer.parseInt(id));
			JSONObject jsonObj = new JSONObject(actor);
			response.getWriter().append(jsonObj.toString());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String data = request.getReader().lines().collect(Collectors.joining());
		JSONObject jsonObj = new JSONObject(data);
		Actor actor = new Actor(0, jsonObj.getString("first_name"), jsonObj.getString("last_name"), null);
		boolean res = actorDao.addActor(actor);
		JSONObject resJSON = new JSONObject();
		if (res) {
			resJSON.put("ok", true);
			resJSON.put("message", "Actor introducido con �xito");
		} else {
			resJSON.put("ok", false);
			resJSON.put("message", "Ha ocurrido un error");

		}
		response.getWriter().append(resJSON.toString());

	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("PUT: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("DELETE: ").append(request.getContextPath());

	}

}
