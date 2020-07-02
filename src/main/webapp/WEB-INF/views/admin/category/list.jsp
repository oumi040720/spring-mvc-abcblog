<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="x-ua-compatible" content="ie=edge">
		<title>Admin | <s:message code="category.title" /></title>
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
								<h4 class="page-title pull-left"><s:message code="category.title" /></h4>
								<ul class="breadcrumbs pull-left">
									<li><a href=""><s:message code="category.title" /></a></li>
									<li><span><s:message code="category.list" /></span></li>
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
									<h4><s:message code="category.list" /></h4>
								</div>
								<div class="card-body">
									<div class="row">
										<a href="<c:url value="/admin/category/add" />" class="btn btn-primary">
											<s:message code="category.add" />
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
										<form id="form-submit" action="<c:url value="/admin/category/list" />" method="get" style="width: 100%">
											<table class="table table-bordered datatable-dark">
												 <thead class="text-capitalize">
												 	<tr>
												 		<th><s:message code="category.name" /></th>
												 		<th><s:message code="category.code" /></th>
												 		<th><s:message code="category.create" /></th>
												 		<th><s:message code="category.modified" /></th>
												 		<th>#</th>
												 	</tr>
												 </thead>
												 <tbody>
														<c:forEach items="${categories}" var="category">
															<tr>
																<td>${category.categoryName}</td>
																<td>${category.categoryCode}</td>
																<td>
																	<p>${category.createBy} - ${category.createDate}</p>
																</td>
																<td>
																	<p>${category.modifiedBy} - ${category.modifiedDate}</p>
																</td>
																<td>
																	<span class="mr-3">
																		<c:url var="editURL" value="/admin/category/edit">
																			<c:param name="category_code" value="${category.categoryCode}" />
																		</c:url>
																		<a href="${editURL}" data-toggle="tooltip" data-placement="left" title="<s:message code="common.button.edit" />">
																			<i class="text-info fa fa-edit"></i>
																		</a>
																	</span>
																	<span class="mr-3">
																		<c:url var="deleteURL" value="/admin/category/delete" >
																			<c:param name="id" value="${category.id}" />
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
			var limit = ${limit};
		
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