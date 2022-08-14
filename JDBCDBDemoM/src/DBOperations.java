

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecommerce.DBConnection;

/**
 * Servlet implementation class DBOperations
 */
@WebServlet("/DBOperations")
public class DBOperations extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DBOperations() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{
			PrintWriter out = response.getWriter();
            out.println("<html><body>");
            
            //Load the DB Connection config parameters.
            InputStream in = getServletContext().getResourceAsStream("/WEB-INF/config.properties");
            Properties props = new Properties();
            props.load(in); // We have added url, user, password to the Props object.
            
            //Create the DBConnection object here.
            DBConnection conn = new DBConnection(props.getProperty("url"), props.getProperty("userid"), props.getProperty("password"));
            out.println("DB Connection Initialized Successfully!. <br>");
            
            Statement stmt = conn.getConnection().createStatement();
            stmt.executeUpdate("create database randomdb");
            out.println("Create database successful: randomdb<br>");
            
            stmt.executeUpdate("use randomdb");
            out.println("Select database successful: randomdb<br>");
            
            //stmt.executeUpdate("drop database randomdb");
            //out.println("Drop database successful: randomdb<br>");
            
            stmt.close();
            conn.closeConnection();
            out.println("DB Connection closed.<br>");
            
            out.println("</body></html>");
                       
		}catch(ClassNotFoundException | SQLException e) // Piping Exceptions - JDK 1.7
		{
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
