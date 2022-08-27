package com.ecommerce;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

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
		 try {
             SessionFactory factory = HibernateUtil.getSessionFactory();
             
             Session session = factory.openSession();
             
              
             List<EProduct> list = session.createQuery("from EProduct").list();
          // Eager - Have data for eproduct, color, os , screensizes, finance
          // Lazy - Have data for eproduct
             
              PrintWriter out = response.getWriter();
              out.println("<html><body>");
              out.println("<b>Product Listing</b><br>");
              for(EProduct p: list) {
                      out.println("ID: " + String.valueOf(p.getID()) + ", Name: " + p.getName() +
                                      ", Price: " + String.valueOf(p.getPrice()) + ", Date Added: " + p.getDateAdded().toString());
                      
                      //Association Fetching Code
                      List<Color> colors = p.getColors();// Lazy cascade is done and Colors data is fetched from the colors table.
                      out.println("Colors: ");
                      for(Color c: colors)
                      {
                    	  out.println(c.getName() + "&nbsp;");
                      }
                      
                      Collection<ScreenSizes> sizes= p.getScreenSizes();// Lazy cascade is done and Screensizes data is fetched from the Screensizes table.
                      out.println(", Screen Sizes: ");
                      for(ScreenSizes s: sizes) {
                              out.print(s.getSize() + "&nbsp;");
                      }
                      
                      Set<OS> os= p.getOs();// Lazy cascade is done and OS data is fetched from the OS table.
                      out.println(", OS : ");
                      for(OS o: os) {
                              out.print(o.getName() + "&nbsp;");
                      }
                      
				/*
				 * Map finances = p.getFinance();// Lazy cascade is done and Finance data is
				 * fetched from the Finance table. out.println(", Finance Options: "); if
				 * (finances.get("CREDITCARD") != null) { Finance f = (Finance)
				 * finances.get("CREDITCARD"); out.println(f.getName() + " &nbsp;"); } if
				 * (finances.get("BANK") != null) { Finance f = (Finance) finances.get("BANK");
				 * out.println(f.getName() + " &nbsp;"); }
				 */
                      
                      
                      out.println("<hr>");
              }
                     session.close();

          out.println("</body></html>");
              	
            }catch(Exception e)
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
