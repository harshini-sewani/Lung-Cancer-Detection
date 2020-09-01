package admin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.io.FilenameUtils;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import Database.DatabaseConnection;

/**
 * Servlet implementation class multiupload
 */
@WebServlet("/multiupload")
public class multiupload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public multiupload() {
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
        
        ArrayList<String> textbox = new ArrayList<String>();
        ArrayList<String> textbox1 = new ArrayList<String>();
        path p=new path();
        
		try {
			List<FileItem> items = new ServletFileUpload(
					new DiskFileItemFactory()).parseRequest(request);
			for (FileItem item : items) {
				if (item.isFormField()) {

					String fieldValue = item.getString();
					textbox.add(fieldValue);
						
						
				} else {
					
					String name = new File(item.getName()).getName();
					textbox1.add(name);
					item.write(new File(p.path + File.separator +textbox.get(0) +File.separator+ name));
				}
			}
		} catch (FileUploadException e) {
			throw new ServletException("Cannot parse multipart request.", e);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
						
			DatabaseConnection db;
			db = new DatabaseConnection();
			db.dbconnection();
			int i1=0;
			int j=1;
			int k=6;
			int n=5;
		for(int i=0;i<5;i++)
		{
			int rank=0;
			String query3="insert into tbl_image(Category,Name,Image,descr)values('"+textbox.get(0)+"','"+textbox.get(j)+"','"+textbox1.get(i)+"','"+textbox.get(k)+"')";
			i1=db.getUpdate(query3);
			j++;
			k++;
			n++;
		}

			if(i1==1)
			{
					System.out.println("Inserted Successfully");
					response.sendRedirect("Admin/MultiUpload.jsp");
			}else
			{
					System.out.println("Please Fillup all fields");
					response.sendRedirect("Admin/MultiUpload.jsp");
			}

	}

}
