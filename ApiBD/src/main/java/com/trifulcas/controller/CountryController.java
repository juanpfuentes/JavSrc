package com.trifulcas.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONObject;

import com.trifulcas.dao.CountryDAO;
import com.trifulcas.models.Country;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Country
 */
@WebServlet("/country/*")
public class CountryController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public CountryController() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String parametro = request.getPathInfo();
			CountryDAO paises = new CountryDAO();

			if (parametro == null) {

				List<Country> todos = paises.paises();
				JSONArray resultado = new JSONArray(todos);
				JSONObject res = new JSONObject();
				res.put("error", false);
				res.put("Data", resultado.toString());
				response.getWriter().append(res.toString());
			} else {
				int id = Integer.parseInt(parametro.substring(1));
				Country pais = paises.paisPorId(id);
				JSONObject resultado = new JSONObject(pais);
				response.getWriter().append(resultado.toString());
			}
		} catch (Exception ex) {
			JSONObject error = new JSONObject();
			error.put("Error", "GET");
			error.put("Mensaje", ex.getMessage());
			response.getWriter().append(error.toString());
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Leer los datos que nos mandan
		JSONObject error = new JSONObject();
		try {

			String data = request.getReader().lines().collect(Collectors.joining());
			JSONObject pais = new JSONObject(data);
			CountryDAO daoPais = new CountryDAO();

			daoPais.crearPais(0, pais.get("country").toString());
			error.put("Error", false);
			response.getWriter().append(error.toString());
		} catch (Exception ex) {
			
			error.put("Error", true);
			error.put("Mensaje", ex.getMessage());
			response.getWriter().append(error.toString());
		}
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		JSONObject error = new JSONObject();
			// Modificar el elemento
		try {
			// Obtener el JSON que me pasan
			String data = request.getReader().lines().collect(Collectors.joining());
			JSONObject pais = new JSONObject(data);
			// Obtengo el id que tengo que cambiat
			String parametro = request.getPathInfo();
			int id = Integer.parseInt(parametro.substring(1));
			
			// Utilizo el DAO para hacer el cambio
			CountryDAO daoPais = new CountryDAO();
			daoPais.cambiarPais(id, pais.getString("country"));
			error.put("Error", false);
			response.getWriter().append(error.toString());
		} catch (Exception ex) {
			error.put("Error", true);
			error.put("Mensaje", ex.getMessage());
			response.getWriter().append(error.toString());
		}
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		JSONObject error = new JSONObject();
		try {
			String parametro = request.getPathInfo();
			int id = Integer.parseInt(parametro.substring(1));
			CountryDAO daoPais = new CountryDAO();
			daoPais.borrarPais(id);
			error.put("Error", false);
			response.getWriter().append(error.toString());
		
		}catch (Exception ex) {
			error.put("Error", true);
			error.put("Mensaje", ex.getMessage());
			response.getWriter().append(error.toString());
		}
	}

}
