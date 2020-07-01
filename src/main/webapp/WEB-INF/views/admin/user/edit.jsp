<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="x-ua-compatible" content="ie=edge">
		<title>Admin | <s:message code="user.title" /></title>
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
									<li><span><s:message code="user.edit" /></span></li>
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
										<c:when test="${not empty user.id}">
											<h4><s:message code="user.update" /></h4>
										</c:when>
										<c:otherwise>
											<h4><s:message code="user.add" /></h4>
										</c:otherwise>
									</c:choose>
								</div>
								<div class="card-body">
									<div class="row">
										<c:url var="action" value="/admin/user/save" />
										<form:form action="${action}" modelAttribute="user" cssStyle="width: 100%;">
											<div class="form-group">
												<label><s:message code="user.username" />:</label>
												<form:input path="username" cssClass="form-control"/>
											</div>
											<div class="form-group">
												<label><s:message code="user.password" />:</label>
												<form:password path="password" cssClass="form-control"/>
											</div>
											<div class="form-group">
												<label><s:message code="user.confirm_password" />:</label>
												<input id="confirmPassword" type="password" class="form-control">
											</div>
											<div class="form-group">
												<label><s:message code="user.fullname" />:</label>
												<form:input path="fullname" cssClass="form-control"/>
											</div>
											<br>
											<div class="form-row align-items-center">
												<div class="col-sm-2 my-1">
													<label><s:message code="user.gender" />:</label>
												</div>
												<div class="col-sm-2 my-1">
													<span class="custom-control custom-radio">
				                                    	<input type="radio" id="male" name="gender" class="custom-control-input">
				                                        <label class="custom-control-label" for="male"><s:message code="user.gender.male" /></label>
				                                    </span>
												</div>
												<div class="col-sm-2 my-1">
													<span class="custom-control custom-radio">
				                                    	<input type="radio" id="female" name="gender" class="custom-control-input">
				                                        <label class="custom-control-label" for="female"><s:message code="user.gender.female" /></label>
				                                    </span>
												</div>
												<form:hidden path="gender"/>
											</div>
											<br>
											<div class="form-group">
												<label><s:message code="user.birthday" />:</label>
												<form:input path="birthday" cssClass="form-control"/>
											</div>
											<div class="form-group">
												<label>Email:</label>
												<form:input path="email" cssClass="form-control"/>
											</div>
											<div class="form-group">
												<label><s:message code="user.address" />:</label>
												<form:input path="address" cssClass="form-control"/>
											</div>
											<div class="form-group">
												<label><s:message code="user.phone" />:</label>
												<form:input path="phone" cssClass="form-control"/>
											</div>
											<div class="form-group">
												<label><s:message code="user.avatar" />:</label>
												<form:input path="avatar" cssClass="form-control"/>
												<div class="input-group input-file" name="fichier">
													<span class="input-group-btn">
														<button class="btn btn-default btn-choose" type="button">
															Choose
														</button>
													</span>
													<input class="form-control" name="photo" placeholder='Choose a file...' />
													<span class="input-group-btn">
														<button class="btn btn-primary btn-reset" type="button">
															Reset
														</button>
													</span>
												</div>											
											</div>
											<div class="form-group">
												<label><s:message code="role.title" />:</label>
												<form:select cssClass="form-control" path="roleID">
													<form:option value="0"><s:message code="user.role.choose" /></form:option>
													<c:forEach items="${roles}" var="role">
														<form:option value="${role.id}">${role.roleName}</form:option>
													</c:forEach>
												</form:select>
											</div>
											<div class="form-group">
												<c:choose>
													<c:when test="${not empty user.id}">
														<button type="submit" class="btn btn-success">
															<s:message code="common.button.update" />
														</button>
														<form:hidden path="id"/>
														<form:hidden path="createBy"/>
														<form:hidden path="createDate"/>
													</c:when>
													<c:otherwise>
														<button id="submit" type="submit" class="btn btn-success">
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
		<script type="text/javascript">
			$(document).ready(function() {
				var gender = ${user.gender};
				
				if (gender) {
					$("#male").attr("checked", "true");
				} else {
					$("#female").attr("checked", "true");
				}	
				
				$('#submit').click(function() {
					$('#avatar').val($('input[name="photo"]').val());
					
					if ($('#male').is(':checked')) {
						$('#gender').val('true');
					} else {
						$('#gender').val('false');
					}
				})
			});
		</script>
		
		<script type="text/javascript">
			function bs_input_file() {
				$(".input-file").before(function() {
					if (!$(this).prev().hasClass('input-ghost')) {
						var element = $("<input type='file' class='input-ghost' accept='image/*' style='visibility:hidden; height:0'>");
						element.attr("name", $(this).attr("name"));
						element.change(function() {
							element.next(element).find('input').val((element.val()).split('\\').pop());
						});
										
						$(this).find("button.btn-choose").click(function() {
							element.click();
						});
										
						$(this).find("button.btn-reset").click(function() {
							element.val(null);
							$(this).parents(".input-file").find('input').val('');
						});
								
						$(this).find('input').css("cursor", "pointer");
								
						$(this).find('input').mousedown(function() {
							$(this).parents('.input-file').prev().click();
								return false;
						});
						return element;
					}
				});
			}
		
			$(function() {
				bs_input_file();
			});
		</script>
	</body>
</html>