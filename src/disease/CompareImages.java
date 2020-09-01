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
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import utils.GraphicsUtilities;
import admin.path;

/**
 * Servlet implementation class CompareImages
 */
@WebServlet("/CompareImages")
@MultipartConfig(fileSizeThreshold=1024*1024*10,    // 10 MB 
maxFileSize=1024*1024*50,          // 50 MB
maxRequestSize=1024*1024*100) 
public class CompareImages extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CompareImages() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String uploadFilePath = path.path1; 
		String name = "";
		ArrayList<String> textbox = new ArrayList<String>();
		ArrayList<String> textbox1 = new ArrayList<String>();
		  
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
		                } catch (IOException ex) {
		                }
					}
				}
			} catch (FileUploadException e) {
				throw new ServletException("Cannot parse multipart request.", e);
			} catch (Exception e) {
				e.printStackTrace();
			}
		 
		 List<String> list;
		 String imagepath=uploadFilePath + File.separator + name;
		   
		 String folderpath= path.path +"/"+ textbox.get(0);
		   
		 list = FeatureExtraction.main(imagepath, new File(folderpath));
		 
		 System.out.println(list);
		 
		 HttpSession session = request.getSession();
		 
		 session.setAttribute("allids", list);
		 response.sendRedirect("DisplayDiseaseWIthName.jsp");
	}
//	 private String getFileName(Part part) {
//	        String contentDisp = part.getHeader("content-disposition");
//	        System.out.println("content-disposition header= "+contentDisp);
//	        String[] tokens = contentDisp.split(";");
//	        for (String token : tokens) {
//	            if (token.trim().startsWith("filename")) {
//	                return token.substring(token.indexOf("=") + 2, token.length()-1);
//	            }
//	        }
//	        return "";
//	    }
}
