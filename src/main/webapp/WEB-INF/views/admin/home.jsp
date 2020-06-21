<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="x-ua-compatible" content="ie=edge">
		<title>Admin Role</title>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		
		<%@ include file="/WEB-INF/views/admin/common/css.jsp" %>
	</head>

	<body>
		<div id="preloader"><div class="loader"></div></div>	
		
		<div class="page-container">
			<%@ include file="/WEB-INF/views/admin/common/nav.jsp" %>
			
			<div class="main-content">
				<%@ include file="/WEB-INF/views/admin/common/header.jsp" %>
				
				<div class="page-title-area">
					<div class="row align-items-center">
						<div class="col-sm-6">
							<div class="breadcrumbs-area clearfix">
								<h4 class="page-title pull-left">Dashboard</h4>
								<ul class="breadcrumbs pull-left">
									<li><a href="">Home</a></li>
									<li><span>Dashboard</span></li>
								</ul>
							</div>
						</div>
						
						<%@ include file="/WEB-INF/views/admin/common/user.jsp" %>
					</div>
				</div>
				
				<div class="main-content-inner">
					<div class="sales-report-area mt-5 mb-5">
						<div class="row">
						
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<%@ include file="/WEB-INF/views/admin/common/offset.jsp" %>
		
		<%@ include file="/WEB-INF/views/admin/common/js.jsp" %>
	</body>
</html>