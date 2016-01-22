<%@ page language="java" contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8"%>
     <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%
 String path = request.getContextPath();
 String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
 %>
 <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
 <html>
   <head>
     <base href="<%=basePath%>">    
     <title></title>        
   <meta http-equiv="pragma" content="no-cache">
   <meta http-equiv="cache-control" content="no-cache">
   <meta http-equiv="expires" content="0">    
   <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
   <meta http-equiv="description" content="This is my page">
  </head>
  <body>
  	<table >
  		<thead>
  			<tr>Name</tr>
  		</thead>
  		<tbody>
  			<c:forEach items="${students}" var="stuent">
  				<tr>${stuent.name}</tr>
  			</c:forEach>
  		</tbody>
  	</table>
  	<button>Manual</button><br>
  	<button>Auto</button>
  </body>
 </html>