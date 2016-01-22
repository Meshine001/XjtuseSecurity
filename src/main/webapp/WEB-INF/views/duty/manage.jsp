<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
<title>信通院排班系统 - Dashboard</title>
</head>
<body>
	<!-- Page Content -->
	<div id="page-wrapper" style="min-height: 1178px;">
		<div class="container-fluid">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">Dashboard</h1>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<!-- /.row -->
			<div class="row">
					<c:forEach items="${students}" var="student">
						<div class="col-sm-6 col-md-3">
							<div class="thumbnail" style="width: 60px;height: 60px;border-radius:50%">
								<img src="/security/resources/image/simple.jpg"
									alt="通用的占位符缩略图" >
							</div>
							<div class="caption">
								<h3>${student.name}</h3>
								<p>1387455555</p>
								<p>
									<a href="#" class="btn btn-primary" role="button"> 按钮 </a> <a
										href="#" class="btn btn-default" role="button"> 按钮 </a>
								</p>
							</div>
						</div>

					</c:forEach>

					<button>Manual</button>
					<br>
					<button>Auto</button>
			</div>
		</div>
		<!-- /.container-fluid -->
	</div>
	<!-- /#page-wrapper -->







</body>

</html>
