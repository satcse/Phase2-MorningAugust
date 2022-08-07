

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;


@WebServlet("/InterfaceDemo")
public class InterfaceDemo implements Servlet {

	ServletConfig config = null;
	
	@Override
	public void destroy() {
		System.out.println("In destroy() method...");
		
	}

	@Override
	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return config;
	}

	@Override
	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		this.config = config;
		System.out.println("Initialization Complete...");
		
	}

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		
		
		res.setContentType("text/html");
		PrintWriter out= res.getWriter();
		out.println("<html><body>");
		out.println("In the service() method<br>");
		out.println(config.getServletName());
		out.println("</body></html>");
		
	}
	

}
