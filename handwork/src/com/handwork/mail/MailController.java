package com.handwork.mail;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

@WebServlet("/market/mail")
public class MailController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=UTF-8");


        String subject = "(핸드워크) 문의하신 정보입니다.";
        String from = request.getParameter("from");
        String toId = request.getParameter("toId");
        String to = getEmail(toId);
        String title	= request.getParameter("title");
        String content = request.getParameter("content");
        String writer_id = request.getParameter("writer_id");
        String price = request.getParameter("price");
        String name = request.getParameter("name");
        String email = getEmail(writer_id);
        String phone=null;
        if(phone==null||phone.equals("")){
            phone="연락처를 등록하지 않았습니다.";
        }else{
            phone = getPhone(writer_id);
        }

        int board_num = Integer.parseInt(request.getParameter("board-num"));

        String url = String.valueOf(request.getRequestURL());
        String url_ = request.getRequestURI();

        String result = url.replace(url_,"");
        System.out.println("url : "+result);

        String resultURL = result+"/handwork/market/detail?id="+board_num;
        Properties p = new Properties(); // 정보를 담을 객체

        p.put("mail.smtp.host","smtp.gmail.com");
        p.put("mail.smtp.port", "465");
        p.put("mail.smtp.starttls.enable", "true");
        p.put("mail.smtp.auth", "true");
        p.put("mail.smtp.debug", "true");
        p.put("mail.smtp.socketFactory.port", "465");
        p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        p.put("mail.smtp.socketFactory.fallback", "false");


        try{
            Authenticator auth = new MailAuth();
            Session ses = Session.getInstance(p, auth);

            ses.setDebug(true);
            MimeMessage msg = new MimeMessage(ses); // 메일의 내용을 담을 객체

            msg.setSubject(subject); //  제목

            StringBuffer buffer = new StringBuffer();
            buffer.append("<div style=\"margin : 40px; background: white; box-shadow: 0 0 5px gray; border-radius: 2px; padding: 20px 60px 40px 60px; width: 500px; font-size: 17px;\">");
            buffer.append("<div><h2 style=\"padding-top: 20px; padding-bottom: 20px; margin: 20px 0px; color: white; background-color: tomato; width: 500px; text-align: center;\">HANDWORK</h2></div><br>");
            buffer.append("<div style=\"display: flex; width: 100%;\"><p style=\"font-weight: 600; font-size: 30px; line-height: 22px;\">"+title+"</p></div> <br>");
            buffer.append("<div style=\"display: flex; width: 100%;\"><p style=\"font-weight: 600; font-size: 20px; line-height: 22px;\">"+content+"</p></div> <br>");
            buffer.append("<div style=\"display: flex; align-items: center;  padding 20px 0px; color: tomato; font-family: 'Do Hyeon', sans-serif;\"><p style=\"font-weight: 600; font-size: 25px; line-height: 22px;\">"+price+"원</p></div> <br>");
            buffer.append("<div style=\"display: flex; align-items: center;  padding 20px 0px;\"><p style=\"font-weight: 600; font-size: 20px; line-height: 15px;\">제작자 이름 : "+name+"</p></div> <br>");
            buffer.append("<div style=\"display: flex; align-items: center;  padding 20px 0px;\"><p style=\"font-weight: 600; font-size: 20px; line-height: 15px;\">제작자 연락처 : "+phone+"</p></div> <br>");
            buffer.append("<div style=\"display: flex; align-items: center;  padding 20px 0px;\"><p style=\"font-weight: 600; font-size: 20px; line-height: 15px;\">제작자 이메일 : "+email+"</p></div> <br>");
            buffer.append("<div style=\"display: flex; align-items: center; margin: 10px; display: flex; justify-content: center; align-items: center; justify-self: flex-end; color: white; background: tomato; font-size: 19px; padding: 10px; border-radius: 2px; transition: 0.3s all; \"><a href=\""+resultURL+"\"style=\" font-weight: 600; font-size: 20px; line-height: 15px; text-decoration: none; color: white;\">게시글로 이동</a></div>");
            buffer.append("</div>");





            Address fromAddr = new InternetAddress(from);
            msg.setFrom(fromAddr);

            Address toAddr = new InternetAddress(to);
            msg.addRecipient(Message.RecipientType.TO, toAddr); // 받는 사람

            msg.setContent(buffer.toString(), "text/html;charset=UTF-8"); // 내용
            Transport.send(msg); // 전송

            PrintWriter out = response.getWriter();

            out.println("<script>alert('메일이 성공적으로 발송되었습니다.'); location.href='"+request.getContextPath()+"/market/detail?id="+board_num+"';</script>");

            out.flush();




        } catch(Exception e){
            e.printStackTrace();
            return;
        }
    }

    private String getEmail(String writer_id){
        System.out.println("getEmail 메서드 실행");
        String email = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String dbURL = "jdbc:mysql://61.83.168.88:3306/handwork?serverTimezone=UTC";
            String dbID = "handwork";
            String dbPassword = "handwork";
            String sql = "SELECT email from member where id=?";
            Connection conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, writer_id);

            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                email = rs.getString("email");
            }
        } catch (Exception e) {

        }
        return email;
    }
    private String getPhone(String writer_id){
        System.out.println("getEmail 메서드 실행");
        String phone = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String dbURL = "jdbc:mysql://61.83.168.88:3306/handwork?serverTimezone=UTC";
            String dbID = "handwork";
            String dbPassword = "handwork";
            String sql = "SELECT phone from member where id=?";
            Connection conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, writer_id);

            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                phone = rs.getString("phone");
            }
        } catch (Exception e) {

        }
        return phone;
    }
}
