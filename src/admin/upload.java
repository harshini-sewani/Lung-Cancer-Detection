package admin;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import utils.GraphicsUtilities;
import Database.DatabaseConnection;

/**
 * Servlet implementation class upload
 */
@WebServlet("/upload")
public class upload extends HttpServlet {
	private static final long serialVersionUID = 1L;	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public upload() {
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

		ArrayList<String> textbox = new ArrayList<String>();
		ArrayList<String> textbox1 = new ArrayList<String>();
		path p=new path();

		try {
			List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
			for (FileItem item : items) {
				if (item.isFormField()) {
					
					String fieldValue = item.getString();
					textbox.add(fieldValue);
						
				} else {
										
					String name = new File(item.getName()).getName();
					textbox1.add(name);
					
					String pathhhhh = path.path + File.separator +textbox.get(0) +File.separator+ name;

					item.write(new File(path.path + File.separator +textbox.get(0) +File.separator+ name));
					try {
	                    BufferedImage bufferedImage = ImageIO.read(new File(p.path + File.separator +textbox.get(0) +File.separator+ name));
	                    if (bufferedImage.getHeight(null) != 256 || bufferedImage.getWidth(null) != 256) {
	                        bufferedImage = GraphicsUtilities.resizeImage(bufferedImage, 256,256);
	                    }
	                    ImageIO.write(bufferedImage, "png", new File(new File(p.path + File.separator +textbox.get(0) +File.separator+ name).toString()));
	                } catch (IOException ex) {
	                }
				}
			}
		} catch (FileUploadException e) {
			throw new ServletException("Cannot parse multipart request.", e);
		} catch (Exception e) {
			e.printStackTrace();
		}
						
			DatabaseConnection db;
			db = new DatabaseConnection();
			db.dbconnection();

			String query3="insert into tbl_image(Category,Name,Image,descr) values('"+textbox.get(0)+"','"+textbox.get(1)+"','"+textbox1.get(0)+"','"+textbox.get(2)+"')";
			int i=db.getUpdate(query3);
			System.out.println(query3);

			if(i==1)
			{
					System.out.println("Inserted Successfully");
					response.sendRedirect("Admin/Single.jsp");
			}else
			{
					System.out.println("Please Fillup all fields");
					response.sendRedirect("Admin/Single.jsp");
			}
	}

}
