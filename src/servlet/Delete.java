package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.Dao;

public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			if (request.getParameter("id").equalsIgnoreCase("All")) {
				new Dao().deleteAll();
			} else {
				int id = Integer.parseInt(request.getParameter("id"));
				new Dao().deleteUnit(id);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		response.sendRedirect("Search");
	}
}