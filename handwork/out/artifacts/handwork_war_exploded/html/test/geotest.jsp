<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <script type="text/javascript"
            src="//code.jquery.com/jquery-1.11.0.min.js"></script>
    <script type="text/javascript"
            src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
</head>
<body>
    <form action="">
        <input type="button" value="주소검색" onclick="searchAddr()">
        <input type="text" name="flocation" id="flocation" required>
        <input type="text" name="location" id="location" required>
        <p class="view"></p>
        <input type="submit" value="확인">
    </form>
    <a href="/handwork/LocationTest" >rest</a>
    <script>
        $(window).load(function (){
            $("#location, #flocation").on('keydown paste focus mousedown', function(e){
                if(e.keyCode != 9) // ignore tab
                    e.preventDefault();
            });
            $("#location, #flocation").on('click', function(e){
                searchAddr();
            });
        })

        function searchAddr(){
            new daum.Postcode({
                oncomplete: function(data) {
                    $('#flocation').val(data.address);
                    $('#location').val(data.sido+' '+data.sigungu.split(" ")[0]);
                }
            }).open();
        }
    </script>
</body>
</html>