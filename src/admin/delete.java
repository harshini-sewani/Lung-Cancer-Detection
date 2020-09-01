package admin;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Database.DatabaseConnection;

/**
 * Servlet implementation class delete
 */
@WebServlet("/delete")
public class delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public delete() {
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
		HttpSession session = request.getSession();
		String id=request.getParameter("id");
		String path=request.getParameter("path");
		String[] getpath=path.split("/", 2);
		
        String newpath="D:\\projects 2016-2017\\189 Crop Disease Prediction\\CropDiseasePrediction\\WebContent/"+getpath[1];
		
        DatabaseConnection db = new DatabaseConnection();
		db.dbconnection();
		
		String query1="DELETE FROM tbl_image WHERE id="+id+"";
		System.out.println(query1);
		int i = db.getUpdate(query1);
		if(i==1)
		{
			File f=new File(newpath);
			System.out.println(f);
			f.delete();
			response.sendRedirect("Admin/Download.jsp");
		}
	}

}
