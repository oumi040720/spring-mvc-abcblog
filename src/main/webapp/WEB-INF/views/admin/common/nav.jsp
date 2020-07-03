<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<div class="sidebar-menu">
	<div class="sidebar-header">
		<div class="logo">
			<a href=""></a>
		</div>
	</div>
	
	<div class="main-menu">
		<div class="menu-inner">
			<nav>
				<ul class="metismenu" id="menu">
					<li>
						<a href="<c:url value="/admin/home" />">
							<i class="ti-dashboard"></i><span><s:message code="menu.dashboard" /></span>
						</a>
					</li>
					<li>
						<a href="<c:url value="/admin/role/list" />">
							<i class="ti-desktop"></i><span><s:message code="menu.role" /> </span>
						</a>
					</li>
					<li>
						<a href="<c:url value="/admin/user/list?page=1" />">
							<i class="ti-user"></i><span><s:message code="menu.user" /> </span>
						</a>
					</li>
					<li>
						<a href="<c:url value="/admin/category/list?page=1" />">
							<i class="ti-view-list"></i><span><s:message code="menu.category" /> </span>
						</a>
					</li>
					<li>
						<a href="<c:url value="/admin/post/list?page=1" />">
							<i class="ti-layers-alt"></i><span><s:message code="menu.post" /> </span>
						</a>
					</li>
					<li>
						<a href="">
							<i class="ti-tag"></i><span><s:message code="menu.tag" /> </span>
						</a>
					</li>
					<li>
						<a href="">
							<i class="ti-comment-alt"></i><span><s:message code="menu.comment" /> </span>
						</a>
					</li>
					<li>
						<a href="javascript:void(0)" aria-expanded="true">
							<i class="ti-trash"></i><span><s:message code="menu.bin" /> </span>
						</a>
						<ul class="collapse">
							<li>
								<a href=""><s:message code="menu.user" /> </a>
							</li>
							<li>
								<a href=""><s:message code="menu.post" /> </a>
							</li>
							<li>
								<a href=""><s:message code="menu.comment" /> </a>
							</li>
						</ul>
					</li>
				</ul>
			</nav>
		</div>
	</div>
</div>