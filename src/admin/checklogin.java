package admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Database.DatabaseConnection;

/**
 * Servlet implementation class checklogin
 */
@WebServlet("/checklogin")
public class checklogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public checklogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		String username=request.getParameter("username");
		String password=request.getParameter("password");
        
        DatabaseConnection db = new DatabaseConnection();
		db.dbconnection();
		
		String query1="SELECT * FROM admin WHERE uname='"+username+"' AND pwd='"+password+"'";
		ResultSet rs = db.getResultSet(query1);
				try {
							if(rs.next())
							{
								response.sendRedirect("Admin/Single.jsp");
							}
							else
							{
								out.println("<script type=\"text/javascript\">");
								out.println("alert('Wrong Username & Password');");
								out.println("location=\"Admin/index.jsp\";");
								out.println("</script>");
							}
							
							
					} catch (SQLException e) {
						e.printStackTrace();
					}
	}

}
