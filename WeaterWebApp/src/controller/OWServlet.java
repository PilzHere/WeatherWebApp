package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CookieBaker;
import model.WeatherBean;
import model.WeatherManager;

/**
 * Servlet implementation class Servlet1
 */
@WebServlet("/OWServlet")
public class OWServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OWServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {
		final String cityName = request.getParameter("cityName");
		final String countryName = request.getParameter("countryName");

		if (cityName.isEmpty() || countryName.isEmpty()) {
			response.sendRedirect("Index.jsp");
		} else {
			final WeatherBean weatherBean = new WeatherBean(cityName, countryName);

			WeatherManager.getWeather(weatherBean);

			request.setAttribute("weatherBean", weatherBean);
			response.addCookie(CookieBaker.bakeCookie(weatherBean));
			final RequestDispatcher reqDisp = request.getRequestDispatcher("DisplayWeather.jsp");
			reqDisp.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
