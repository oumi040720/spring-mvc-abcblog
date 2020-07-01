<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="x-ua-compatible" content="ie=edge">
		<title>Admin | <s:message code="user.title" /> </title>
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
								<h4 class="page-title pull-left"><s:message code="user.title" /></h4>
								<ul class="breadcrumbs pull-left">
									<li><a href=""><s:message code="user.title" /></a></li>
									<li><span><s:message code="user.list" /></span></li>
								</ul>
							</div>
						</div>
						
						<%@ include file="/WEB-INF/views/admin/common/user.jsp" %>
					</div>
				</div>
				
				<div class="main-content-inner">
					<div class="row">
						<div class="col-12 mt-5">
							<div class="card">
								<div class="card-header">
									<h4><s:message code="user.list" /></h4>
								</div>
								<div class="card-body">
									<div class="row">
										<a href="<c:url value="/admin/user/add" />" class="btn btn-primary">
											Add
										</a>
									</div>
									<br>
									<c:if test="${not empty message}">
										<div class="row">
											<div class="alert-dismiss" style="width: 100%;">
												<div class="alert alert-${alert} alert-dismissible fade show" role="alert">
		                                            <strong>
		                                            	<c:set var="success" value="success" />
		                                            	<c:choose>
		                                            		<c:when test="${alert == success}">
		                                            			<i class="fa fa-check-circle"></i>
		                                            		</c:when>
		                                            		<c:otherwise>
		                                            			<i class="fa fa-times-circle"></i>
		                                            		</c:otherwise>
		                                            	</c:choose>
		                                            </strong> <s:message code="${message}" />
		                                            <button type="button" class="close" data-dismiss="alert"
		                                                aria-label="Close"><span class="fa fa-times"></span>
		                                            </button>
		                                        </div>
											</div>
										</div>
									</c:if>
									<div class="row">
										<form id="form-submit" action="<c:url value="/admin/user/list" />" method="get">
											<table class="table table-bordered datatable-dark">
												 <thead class="text-capitalize">
												 	<tr>
												 		<th style="width: 5%"><s:message code="user.avatar" /></th>
												 		<th><s:message code="user.username" /></th>
												 		<th><s:message code="user.fullname" /></th>
												 		<th><s:message code="user.gender" /></th>
												 		<th><s:message code="user.birthday" /></th>
												 		<th>Email</th>
												 		<th><s:message code="user.phone" /></th>
												 		<th>#</th>
												 	</tr>
												 </thead>
												 <tbody>
												 	<c:forEach var="user" items="${users}">
												 		<tr>
												 			<td>
												 				<img alt="${user.avatar}" src="${user.avatar}">
												 			</td>
												 			<td>${user.username}</td>
												 			<td>${user.fullname}</td>
												 			<td>
												 				<c:if test="${user.gender}">Male</c:if>
												 				<c:if test="${!user.gender}">Female</c:if>
												 			</td>
												 			<td>${user.birthday}</td>
												 			<td>${user.email}</td>
												 			<td>${user.phone}</td>
												 			<td>
												 				<span class="mr-3">
																	<c:url var="editURL" value="/admin/user/edit">
																		<c:param name="username" value="${user.username}" />
																	</c:url>
																	<a href="${editURL}" data-toggle="tooltip" data-placement="left" title="<s:message code="common.button.edit" />">
																		<i class="text-info fa fa-edit"></i>
																	</a>
																</span>
																<span class="mr-3">
																	<c:url var="deleteURL" value="/admin/user/delete" >
																		<c:param name="id" value="${user.id}" />
																	</c:url>
																	<a href="${deleteURL}" data-toggle="tooltip" data-placement="left" title="<s:message code="common.button.delete" />">
																		<i class="text-danger ti-trash"></i>
																	</a>
																</span>
												 			</td>
												 		</tr>
												 	</c:forEach>
												 </tbody>
											</table>
											<nav aria-label="Page navigation">
												<ul class="pagination" id="pagination"></ul>
												<input type="hidden" id="page" name="page" value=""> 
											</nav>
										</form>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<%@ include file="/WEB-INF/views/admin/common/offset.jsp" %>
		
		<%@ include file="/WEB-INF/views/admin/common/js.jsp" %>
		<script type="text/javascript">
			var totalPages = ${totalPages};
			var currentPage = ${page};
			var limit = 10;
		
			$(function() {
				window.pagObj = $('#pagination').twbsPagination({
					totalPages : totalPages,
					visiblePages : 5,
					startPage : currentPage,
					onPageClick : function(event, page) {
						if (currentPage != page) {
							$('#page').val(page);
							$('#form-submit').submit();
						}
					}
				});
			});
		</script>
	</body>
</html>