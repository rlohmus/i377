package ylesanne2;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
/**
 * Servlet implementation class SessioonideArv
 */
public class SessionCount extends HttpServlet implements HttpSessionListener{
	private static final long serialVersionUID = 1L;
     
	private static int sessionCounter;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().println("count is " + GetSessionCounter()); 
	}

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		sessionCounter++;
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		sessionCounter--;		
	}

	public static int GetSessionCounter() {
		return sessionCounter;
	}

}
