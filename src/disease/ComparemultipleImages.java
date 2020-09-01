package disease;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import utils.GraphicsUtilities;
import admin.path;

/**
 * Servlet implementation class ComparemultipleImages
 */
@WebServlet("/ComparemultipleImages")
@MultipartConfig(fileSizeThreshold=1024*1024*10,    // 10 MB 
maxFileSize=1024*1024*50,          // 50 MB
maxRequestSize=1024*1024*100) 
public class ComparemultipleImages extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ComparemultipleImages() {
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
	@SuppressWarnings("null")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<String> multiplelist =new ArrayList<String>();
		String uploadFilePath = path.path1; 
		String name = "";
		ArrayList<String> textbox = new ArrayList<String>();
		ArrayList<String> textbox1 = new ArrayList<String>();
		ArrayList<String> images = new ArrayList<String>();
		  try {
				List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
				for (FileItem item : items) {
					if (item.isFormField()) {
						
						String fieldValue = item.getString();
						textbox.add(fieldValue);
							
					} else {
											
						name = new File(item.getName()).getName();
						textbox1.add(name);
						item.write(new File(uploadFilePath + File.separator + name));
						try {
		                    BufferedImage bufferedImage = ImageIO.read(new File(uploadFilePath + File.separator + name));
		                    if (bufferedImage.getHeight(null) != 256 || bufferedImage.getWidth(null) != 256) {
		                        bufferedImage = GraphicsUtilities.resizeImage(bufferedImage, 256,256);
		                    }
		                    ImageIO.write(bufferedImage, "png", new File(new File(uploadFilePath + File.separator + name).toString()));
		                
		                    images.add(uploadFilePath + File.separator + name);
						} catch (IOException ex) {
		                }
					}
				}
			} catch (FileUploadException e) {
				throw new ServletException("Cannot parse multipart request.", e);
			} catch (Exception e) {
				e.printStackTrace();
			}
		 
		 
		 
		 for (String image : images){
		 
		 List<String> list = new ArrayList<String>();
		 String imagepath = image;

		 String folderpath= path.path +"/"+ textbox.get(0);
		   
		 list = FeatureExtraction.main(imagepath, new File(folderpath));
		 
		 multiplelist.add(list.get(0));
		 }
		 
		 HttpSession session = request.getSession();
		 
		 session.setAttribute("allids", multiplelist);
		 response.sendRedirect("MDiseaseWIthName.jsp");
	}

}
