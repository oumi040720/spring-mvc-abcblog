<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="x-ua-compatible" content="ie=edge">
		<title>ADMIN | <s:message code="comment.title" /></title>
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
								<h4 class="page-title pull-left"><s:message code="comment.title" /></h4>
								<ul class="breadcrumbs pull-left">
									<li><a href=""><s:message code="comment.title" /></a></li>
									<li><span><s:message code="comment.edit" /></span></li>
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
									<c:choose>
										<c:when test="${not empty comment.id}">
											<h4><s:message code="comment.update" /></h4>
										</c:when>
										<c:otherwise>
											<h4><s:message code="comment.add" /></h4>
										</c:otherwise>
									</c:choose>
								</div>
								<div class="card-body">
									<div class="row">
										<c:url var="action" value="/admin/comment/save" />
										<form:form action="${action}" modelAttribute="comment" cssStyle="width: 100%;">
											<div class="form-group">
												<label><s:message code="comment.post" />:</label>
												<form:input path="postID" cssClass="form-control"/>
											</div>
											<div class="form-group">
												<label><s:message code="comment.content" />:</label>
												<form:textarea path="content" cssClass="form-control" />
												<script src="<c:url value="/template/ckeditor/ckeditor.js" />"></script>
												<script type="text/javascript">
													CKEDITOR.replace('content');
												</script>
											</div>
											<div class="form-group">
												<c:choose>
													<c:when test="${not empty comment.id}">
														<button type="submit" class="btn btn-success">
															<s:message code="common.button.update" />
														</button>
														<form:hidden path="id"/>
														<form:hidden path="createBy"/>
														<form:hidden path="createDate"/>
													</c:when>
													<c:otherwise>
														<button type="submit" class="btn btn-success">
															<s:message code="common.button.add" />
														</button>
													</c:otherwise>
												</c:choose>
												<button type="reset" class="btn btn-outline-warning">
													<s:message code="common.button.reset" />
												</button>
											</div>
										</form:form>
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
	</body>
</html>