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

public class View extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		Unit u = null;
		Unit superUnit = null;
		List<Unit> childUnits = null;
		try {
			Dao dao = new Dao();
			long id = Long.parseLong(request.getParameter("id"));
			//superUnit = new Dao().findSuperUnit(id);
			u = dao.findUnit(id);
			childUnits = dao.findAllChildUnits(id);
			
			//if (u != null)
			superUnit = dao.findSuperUnit(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (u != null) {
			request.setAttribute("uName", u.getName());
			request.setAttribute("uKood", u.getCode());
			
			if (superUnit != null) {
				request.setAttribute("superUnit", superUnit);
			}
			
			if (childUnits != null)
				request.setAttribute("subUnits", childUnits);
		}
		
		request.getRequestDispatcher("jsp/view.jsp").forward(request, response);
	}
	
	@Override	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		response.sendRedirect("Search");
	}
}

