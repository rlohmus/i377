package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.Dao;
import database.models.Unit;

public class Add extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<Unit> units = null;
		try {
			units = new Dao().findAllUnits();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("units", units);
		request.getRequestDispatcher("jsp/add.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			new Dao().addUnit(request.getParameter("name"),
					request.getParameter("code"), request.getParameter("superUnitCode"));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		response.sendRedirect("Search");
	}
}