package com.handwork.free.controller;

import com.handwork.free.entity.Free;
import com.handwork.free.service.FreeService;
import com.handwork.request.entity.Request;
import com.handwork.request.service.RequestService;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Enumeration;

@WebServlet("/free/update")
public class FreeUpdateController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		System.out.println(id);
		
		FreeService service = new FreeService();
		
		Free free_ = service.getFree(id);
		//free_.setIsUpdate(true);
		
		request.setAttribute("r", free_);
		service.disconnect();
		request.getRequestDispatcher("/free/write").forward(request, response);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String fullFileName = null;
		ArrayList<String> imgUpdateList = new ArrayList<String>();

		int sizeLimit = 15 * 1024 * 1024;

		String realPath = request.getServletContext().getRealPath("upload/freeBoard");
		System.out.println(realPath);

		File dir = new File(realPath);
		if (!dir.exists()) dir.mkdirs();
				
		MultipartRequest multi = null;
		multi = new MultipartRequest(request, realPath, 
				sizeLimit, "utf-8",new DefaultFileRenamePolicy());

		int id = Integer.parseInt(multi.getParameter("id"));
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String writer = (String) session.getAttribute("name");
		String title = multi.getParameter("title");
		String content = multi.getParameter("content");

		Enumeration files = multi.getFileNames();
		ArrayList<String> fileList = new ArrayList<String>();		
		while(files.hasMoreElements()) {
			String paramName = (String) files.nextElement();
			if(paramName.contains("img_update")) {
				String imgUpdateVal = multi.getFilesystemName(paramName);
				if(imgUpdateVal == null || imgUpdateVal.equals("")|| imgUpdateVal.equals("null")) {
					
				} else {
					imgUpdateList.add(imgUpdateVal); //imgUpdateList = ���� �� �̹���(sql �߰��ʿ�)
				}
			}
			if(paramName.contains("img_path")){
				String filenames = multi.getFilesystemName(paramName);
				fileList.add(filenames);
			}
		}

		String[] del_filename = multi.getParameterValues("del_filename");
		String[] edit_filename = multi.getParameterValues("edit_filename");

		RequestService service = new RequestService();
		Request request_ = service.getRequest(id);
		if(request_.getFilename() != null && !request_.getFilename().equals("")) {
			fullFileName = request_.getFilename();//Request�� ����� �̹��� �� ���
			if(del_filename != null && !del_filename.equals("")) {
				for(int i=0; i<del_filename.length; i++) {
					String delFile = del_filename[i].replace("*", " ");;
					fullFileName = fullFileName.replace(delFile, "");
				}
			}
			if(edit_filename != null && !edit_filename.equals("")) {
				for(int i=0; i<edit_filename.length; i++) {
					String newFileName = imgUpdateList.get(i);
					if(newFileName == null || newFileName.equals("")|| newFileName.equals("null")) {
						
					} else {
						String editFile = edit_filename[i].replace("*", " ");
						String changeFileName = imgUpdateList.get(i);
						fullFileName = fullFileName.replace(editFile,changeFileName+"/");
					}
				}
			}
		}

		for(int i=0;i<fileList.size(); i++) {
			String newFileName = fileList.get(i);
			if(newFileName == null || newFileName.equals("")|| newFileName.equals("null")) {
			} else if(fullFileName==null){
				fullFileName = "";
				fullFileName = fullFileName.concat(newFileName+"/");
			} else {
				fullFileName = fullFileName.concat(newFileName+"/");
			}
		}

		try {
		System.out.println("������Ʈ�� �ʱ� ����");
		
		String sql = "update free set title=?, content=?, filename=? where id=?";
		
		Class.forName("com.mysql.cj.jdbc.Driver");
			String dbURL = "jdbc:mysql://nfox.site:3306/handwork?serverTimezone=UTC&useSSL=FALSE";
	    String dbID = "handwork";
	    String dbPassword = "handwork";
		
		Connection conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, title);
		pstmt.setString(2, content);
		if(fullFileName.replace("/","").equals("")) {
	         fullFileName = null;
	    }
		if(fullFileName== null || fullFileName.equals("")) {
			pstmt.setNull(3, java.sql.Types.VARCHAR);
		} else {
			pstmt.setString(3, fullFileName);
		}
		pstmt.setInt(4, id);
		pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath()+"/free");
	}
}

	

