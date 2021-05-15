package com.handwork.market.controller;

import com.handwork.market.entity.Market;
import com.handwork.market.service.MarketService;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Enumeration;

@WebServlet("/market/update")
public class MarketUpdateController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int id = Integer.parseInt(request.getParameter("id"));
		System.out.println(id);
		
		MarketService service = new MarketService();
		
		Market market = service.getMarket(id);
		market.setIsUpdate(true);
		
		request.setAttribute("r", market);
		
		request.getRequestDispatcher("/market/write").forward(request, response);
	}

	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String fullFileName = null; //������ SQL filename �� ���
		ArrayList<String> imgUpdateList = new ArrayList<String>(); //imgUpdateList = ���� �� �̹���(sql �߰��ʿ�)

		int sizeLimit = 15 * 1024 * 1024;

		
		//	����θ� �����η� �����;� 
		String realPath = request.getServletContext().getRealPath("upload/marketBoard");
		System.out.println(realPath);

		//upload ������ ���� ��� ������ �����
		File dir = new File(realPath);
		if (!dir.exists()) dir.mkdirs();

		MultipartRequest multi = null;
		multi = new MultipartRequest(request, realPath,
				sizeLimit, "utf-8",new DefaultFileRenamePolicy());


		int id = Integer.parseInt(multi.getParameter("id"));
		HttpSession session = request.getSession();
		
		String writer = (String) session.getAttribute("name");
		System.out.println("writer = "+writer);
		String title = multi.getParameter("title");
		System.out.println("타이트트트트틀 : "+title);
		String kategorie = multi.getParameter("kategorie");
		String location = multi.getParameter("location");
		String flocation = multi.getParameter("flocation");
		int period = Integer.parseInt(multi.getParameter("period"));
		int price = Integer.parseInt(multi.getParameter("price"));
		String content = multi.getParameter("content");
		
		//���ε� �κ�
		Enumeration files = multi.getFileNames();
		ArrayList<String> fileList = new ArrayList<String>();		
		while(files.hasMoreElements()) {
			String paramName = (String) files.nextElement();
			System.out.println("paramName : "+paramName);
			if(paramName.contains("img_update")) {
				System.out.println("img_update ���Խ� paramName : "+paramName);
				// ���� ��ư ������ ���� ���ε� ������ ������ ���� �̸� ����Ʈ ����
				String imgUpdateVal = multi.getFilesystemName(paramName);
				if(imgUpdateVal == null || imgUpdateVal.equals("")|| imgUpdateVal.equals("null")) {
					
				} else {
					imgUpdateList.add(imgUpdateVal); //imgUpdateList = ���� �� �̹���(sql �߰��ʿ�)
				}
			}
			if(paramName.contains("img_path")){
				System.out.println("img_path ���Խ� paramName : "+paramName);
				// ���ε� ��ư ������ ���� ���ε� ������ ���� �̸� ����Ʈ ����
				String filenames = multi.getFilesystemName(paramName);
				fileList.add(filenames);
			}
			System.out.println("while ���� ������ : "+paramName);
		}
		System.out.println("����Ʈ �Ҵ� ����"+fullFileName);
		
		
		//����Ʈ üũüũüũ
		
//		for(int i=fileList.size(); i>0; i--) {
//			String newFileName = fileList.get(i-1);
//			System.out.println("���ε� �׸� for ���ϸ�: "+newFileName);
//		}
//		for(int j=imgUpdateList.size(); j>0; j--) {
//			String changeFileName = imgUpdateList.get(j-1);
//			System.out.println("���� �׸� for ���ϸ�: "+changeFileName);
//		}
//		
		
		/////
		
		
		
		
		//�����׸� �׽�Ʈ
		
		String how = multi.getParameter("how");
		int state = Integer.parseInt(multi.getParameter("state"));
		//�ϴ� �ΰ� �׸��� �Ķ���ͷ� �Ѿ���� ���� �� �� ����
		String[] del_filename = multi.getParameterValues("del_filename");
		String[] edit_filename = multi.getParameterValues("edit_filename"); //edit_filename = ���� �� �̹���(�����ؾ���)
		
		// ���񽺸� ȣ���Ͽ� Request ������� �ش� �Խñ� �̹��� �� ��� �޾ƿ� 		
		MarketService service = new MarketService();
		Market market = service.getMarket(id);
		if(market.getFilename() != null && !market.getFilename().equals("")) {
			System.out.println("������Ʈ ����Ұ� null ���� �ƴ�"+fullFileName);
			fullFileName = market.getFilename();//Request�� ����� �̹��� �� ���
			System.out.println("������Ʈ���� �����̸� ������"+fullFileName);
			//������ �̹������� ����
			System.out.println("del_filename : " + del_filename);
			if(del_filename != null && !del_filename.equals("")) {
				System.out.println("�ܼ� ����"+fullFileName);
				for(int i=0; i<del_filename.length; i++) {
					String delFile = del_filename[i].replace("*", " ");;
					fullFileName = fullFileName.replace(delFile, "");
				}
			}
			if(edit_filename != null && !edit_filename.equals("")) {
				System.out.println("���� ����"+fullFileName);
				for(int i=0; i<edit_filename.length; i++) {
					System.out.println("editFile ����: "+edit_filename.length);
					System.out.println("imgUpdateList ����: "+imgUpdateList.size());
					String newFileName = imgUpdateList.get(i);
					
					if(newFileName == null || newFileName.equals("")|| newFileName.equals("null")) {
						
					} else {
						String editFile = edit_filename[i].replace("*", " ");
						String changeFileName = imgUpdateList.get(i);
						fullFileName = fullFileName.replace(editFile,changeFileName+"/");
						System.out.println("���� ���� ELSE fullFileName : " + fullFileName);
						System.out.println("���� ���� ELSE editFile : " + editFile);
					}
					
					
				}
			}
		}
		System.out.println("�������� ��������"+fullFileName);
		
		//���ε�� �ø� ���� ����Ʈ�� �� ��ο� �߰�
		for(int i=0;i<fileList.size(); i++) {
			System.out.println("���ε�� �ø� �׸� ������ for��"+fullFileName); //����ȵ�
			String newFileName = fileList.get(i);
			if(newFileName == null || newFileName.equals("")|| newFileName.equals("null")) {
				System.out.println("���ε� �ø� �׸��� ������ null �϶�"+fullFileName);
			} else if(fullFileName==null){
				fullFileName = "";
				fullFileName = fullFileName.concat(newFileName+"/");
				System.out.println("���ε� �ø� �׸��� ������ "+fullFileName);
			} else {
				fullFileName = fullFileName.concat(newFileName+"/");
				System.out.println("���ε� �ø� �׸��� ������ "+fullFileName);
			}
		}
		System.out.println("���ε� �׸� for�� ����"+fullFileName);
		//�������� �ø� ���� ����Ʈ�� �� ��ο� �߰�
/*		for(int i=0; i<imgUpdateList.size(); i++) {
			System.out.println("�������� ���� �߰��� ���� ������"+fullFileName);
			String changeFileName = imgUpdateList.get(i);
			if(changeFileName == null || changeFileName.equals("")||changeFileName.equals("null")) {	
				System.out.println("�������� �ø� �׸��� ������ null �϶�"+fullFileName);
			} else {
				fullFileName = fullFileName.concat(changeFileName+"/");
				System.out.println("���ε� �ø� �׸��� ������ "+fullFileName);
			}
		}*/
		System.out.println("���� �׸� for�� ����"+fullFileName);
		
		try {
		System.out.println("������Ʈ�� �ʱ� ����");
		
		String sql = "update market set title=?, kategorie=?, location=?, flocation=?, period=?, price=?, content=?, filename=?, how=?, state=?, latlng=? where id=?";
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		String dbURL = "jdbc:mysql://61.83.168.88:3306/handwork?serverTimezone=UTC";
	    String dbID = "handwork";
	    String dbPassword = "handwork";
		
		Connection conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, title);
		pstmt.setString(2, kategorie);
		pstmt.setString(3, location);
		pstmt.setString(4, flocation);
		pstmt.setInt(5, period);
		pstmt.setInt(6, price);
		pstmt.setString(7, content);

		if(fullFileName.replace("/","").equals("")) {
	         fullFileName = null;
	    }
		
		System.out.println("��������� if��"+fullFileName);
		if(fullFileName== null || fullFileName.equals("")) {
			pstmt.setNull(8, java.sql.Types.VARCHAR);
		} else {
			pstmt.setString(8, fullFileName);
		}
		System.out.println("���� �����"+fullFileName);
		
		pstmt.setString(9, how);
		pstmt.setInt(10, state);
		pstmt.setString(11, getGeoCode(flocation));
		pstmt.setInt(12, id);

		pstmt.executeUpdate();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		response.sendRedirect(request.getContextPath()+"/market");
	}

	private String getGeoCode(String flocation) {
		StringBuffer result = new StringBuffer();
		JSONObject obj = null;
		try {
			String address= URLEncoder.encode(flocation, "UTF-8");
			String apiKey= URLEncoder.encode("AIzaSyAY--fPA18-UuvufmlYYCPygAJYmQNXZVo", "UTF-8");
			String urlStr = String.format("https://maps.googleapis.com/maps/api/geocode/json?address=%s&key=%s",address,apiKey);
			System.out.println(urlStr);
			URL url = new URL(urlStr);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setRequestMethod("GET");
			int rspCode = conn.getResponseCode();
			if (rspCode == 200) {
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

				String nextLine = br.readLine();
				while ((nextLine = br.readLine()) != null) {
					result.append(nextLine);
				}
			}
			conn.disconnect();

			String resultStr = result.toString();
			resultStr = resultStr.substring(resultStr.indexOf("\"location\" : {"));
			resultStr = resultStr.substring(resultStr.indexOf("{"),resultStr.indexOf("}")+1);

			JSONParser jsonParse = new JSONParser();
			obj =  (JSONObject)jsonParse.parse(resultStr);

		} catch (IOException e) {
			System.out.println("RestCall Fail : " + e.getMessage());
		} catch (ParseException e) {
			System.out.println("Json error");
		}

		return String.valueOf(obj);
	}
}

	

