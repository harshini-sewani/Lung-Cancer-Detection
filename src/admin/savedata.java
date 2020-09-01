package admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Database.DatabaseConnection;

/**
 * Servlet implementation class savedata
 */
@WebServlet("/savedata")
public class savedata extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public savedata() {
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

		String category=request.getParameter("category");
		String imagename=request.getParameter("txtimage");
		String file=request.getParameter("file");
		
		DatabaseConnection db;
		db = new DatabaseConnection();
		db.dbconnection();
		
		
		String query3="insert into tbl_image(Category,Name,Image)values('"+category+"','"+imagename+"','"+file+"')";
		int i=db.getUpdate(query3);
		if(i==1)
		{
				System.out.println("Inserted Successfully");
				response.sendRedirect("Admin/Home.jsp");
		}else
		{
				System.out.println("Please Fillup all fields");
				response.sendRedirect("Admin/Home.jsp");
		}
	}

}
