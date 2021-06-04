package com.handwork.mail;


import com.handwork.dao.DAO;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Properties;

public class RequestMailService {
    Connection conn;
    DAO dao;

    public RequestMailService(){
        dao = new DAO();
        conn = dao.getConnection();
    }

    public void sendRequestMail(HttpServletRequest request, HttpServletResponse response){


        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=UTF-8");

        JSONObject json = new JSONObject();

        try{
            DAO dao = new DAO();
            conn = dao.getConnection();

            String subject = "(핸드워크) 문의하신 정보입니다.";
            String fromId = (String) request.getSession().getAttribute("id");
            String from = "ambirion0302@gmail.com";
            String toId = request.getParameter("commentIndex");
            String to = getTo(toId);


            int board_num = Integer.parseInt(request.getParameter("boardNum"));

            String url = String.valueOf(request.getRequestURL());
            String url_ = request.getRequestURI();

            String result = url.replace(url_,"");
            System.out.println("url : "+result);

            String resultURL = result+"/handwork/request/detail?id="+board_num;
            Properties p = new Properties(); // 정보를 담을 객체

            p.put("mail.smtp.host","smtp.gmail.com");
            p.put("mail.smtp.port", "465");
            p.put("mail.smtp.starttls.enable", "true");
            p.put("mail.smtp.auth", "true");
            p.put("mail.smtp.debug", "true");
            p.put("mail.smtp.socketFactory.port", "465");
            p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            p.put("mail.smtp.socketFactory.fallback", "false");



            Authenticator auth = new MailAuth();
            Session ses = Session.getInstance(p, auth);

            ses.setDebug(true);
            MimeMessage msg = new MimeMessage(ses); // 메일의 내용을 담을 객체

            msg.setSubject(subject); //  제목

            StringBuffer buffer = new StringBuffer();
                        buffer.append("<div style=\"margin : 40px; background: white; box-shadow: 0 0 5px gray; border-radius: 2px; padding: 20px 60px 40px 60px; width: 500px; font-size: 17px;\">");
                        buffer.append("<div><h2 style=\"padding-top: 20px; padding-bottom: 20px; margin: 20px 0px; color: white; background-color: tomato; width: 500px; text-align: center;\">HANDWORK</h2></div><br>");
                        buffer.append("<div><h2 style=\"padding-top: 20px; padding-bottom: 20px; margin: 20px 0px; color: white; background-color: tomato; width: 500px; text-align: center;\">체결이 완료되었습니다.</h2></div><br>");
//                        buffer.append("<div style=\"display: flex; width: 100%;\"><p style=\"font-weight: 600; font-size: 30px; line-height: 22px;\">"+title+"</p></div> <br>");
//                        buffer.append("<div style=\"display: flex; width: 100%;\"><p style=\"font-weight: 600; font-size: 20px; line-height: 22px;\">"+content+"</p></div> <br>");
//                        buffer.append("<div style=\"display: flex; align-items: center;  padding 20px 0px; color: tomato; font-family: 'Do Hyeon', sans-serif;\"><p style=\"font-weight: 600; font-size: 25px; line-height: 22px;\">"+price+"원</p></div> <br>");
//                        buffer.append("<div style=\"display: flex; align-items: center;  padding 20px 0px;\"><p style=\"font-weight: 600; font-size: 20px; line-height: 15px;\">제작자 이름 : "+name+"</p></div> <br>");
//                        buffer.append("<div style=\"display: flex; align-items: center;  padding 20px 0px;\"><p style=\"font-weight: 600; font-size: 20px; line-height: 15px;\">제작자 연락처 : "+phone+"</p></div> <br>");
//                        buffer.append("<div style=\"display: flex; align-items: center;  padding 20px 0px;\"><p style=\"font-weight: 600; font-size: 20px; line-height: 15px;\">제작자 이메일 : "+email+"</p></div> <br>");
//                        buffer.append("<div style=\"display: flex; align-items: center;  padding 20px 0px;\"><p style=\"font-weight: 600; font-size: 20px; line-height: 15px;\">제작자 이메일 : "+email+"</p></div> <br>");
                        buffer.append("<div style=\"display: flex; align-items: center; margin: 10px; display: flex; justify-content: center; align-items: center; justify-self: flex-end; color: white; background: tomato; font-size: 19px; padding: 10px; border-radius: 2px; transition: 0.3s all; \"><a href=\""+resultURL+"\"style=\" font-weight: 600; font-size: 20px; line-height: 15px; text-decoration: none; color: white;\">게시글로 이동</a></div>");

            buffer.append("</div>");




            Address fromAddr = new InternetAddress(from);
            msg.setFrom(fromAddr);

            System.out.println(to);
            Address toAddr = new InternetAddress(to);
            msg.addRecipient(Message.RecipientType.TO, toAddr); // 받는 사람

            msg.setContent(buffer.toString(), "text/html;charset=UTF-8"); // 내용
            Transport.send(msg); // 전송

            conn.close();
            dao = null;


            json.put("state","success");

        } catch(Exception e){
            e.printStackTrace();
            json.put("state","error");
        }

    }

    private String getEmail(String writer_id){
        System.out.println("getEmail 메서드 실행");
        String email = null;
        try {

            String sql = "SELECT email from member where id=?";
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
            String sql = "SELECT phone from member where id=?";
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

    private String getFrom(String id){
        String fromEmail = null;
        DAO dao = new DAO();
        conn = dao.getConnection();
        try {
            String sql = "SELECT email from member where id=?";
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, id);

            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                fromEmail = rs.getString("email");
            }
        } catch (Exception e) {

        }
        return fromEmail;
    }

    private String getTo(String id){
        String user_id = null;
        String toEmail = null;
        DAO dao = new DAO();
        conn = dao.getConnection();
        try {
            String sql = "SELECT user_id from comment where idx=?";
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, id);

            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                user_id = rs.getString("user_id");
            }


        } catch (Exception e) {

        }
        toEmail = getFrom(user_id);
        return toEmail;
    }


}
