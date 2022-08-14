

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecommerce.DBConnection;


/**
 * Servlet implementation class ProductDetails
 */
@WebServlet("/ProductDetails")
public class ProductDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductDetails() {
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

//            CREATE PROCEDURE ecommerce.add_productsp(IN pname varchar(100), IN pprice decimal(10,2))
//            INSERT INTO ecommerce.eproduct (name, price) VALUES (pname, pprice)
            
            //Load the DB Connection config parameters.
            InputStream in = getServletContext().getResourceAsStream("/WEB-INF/config.properties");
            Properties props = new Properties();
            props.load(in); // We have added url, user, password to the Props object.
            
            //Create the DBConnection object here.
            DBConnection conn = new DBConnection(props.getProperty("url"), props.getProperty("userid"), props.getProperty("password"));
            out.println("DB Connection Initialized Successfully!. <br>");
            
            CallableStatement stmt = conn.getConnection().prepareCall("{call add_productsp(?,?)}");
            stmt.setString(1, "zzz product");
            stmt.setBigDecimal(2, new BigDecimal(135415.50));
            stmt.executeUpdate();
            
            out.println("Stored Procedure has been executed successfully.");
            
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
