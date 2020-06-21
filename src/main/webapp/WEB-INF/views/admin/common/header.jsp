<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<div class="header-area">
	<div class="row align-items-center">
		<div class="col-md-6 col-sm-8 clearfix">
			<div class="nav-btn pull-left">
				<span></span>
				<span></span>
				<span></span>
			</div>
		</div>
		
		<div class="col-md-6 col-sm-4 clearfix">
			<ul class="notification-area pull-right">
				<li id="full-view"><i class="ti-fullscreen"></i></li>
				<li class="dropdown">
					<i class="ti-bell dropdown-toggle" data-toggle="dropdown">
						<span>2</span>
					</i>
					<div class="dropdown-menu bell-notify-box notify-box">
						<span class="notify-title">
							You have 3 new notifications 
							<a href="#">view all</a>
						</span>
					</div>
				</li>
				<li class="dropdown">
					 <i class="fa fa-envelope-o dropdown-toggle" data-toggle="dropdown">
					 	<span>3</span>
					 </i>
					 <div class="dropdown-menu notify-box nt-enveloper-box">
					 	 <span class="notify-title">
					 	 	You have 3 new notifications 
					 	 	<a href="#">view all</a>
					 	 </span>
					 	 <div class="nofity-list">
					 	 	<a href="#" class="notify-item"> </a>
					 	 </div>
					 </div>
				</li>
				<li class="settings-btn">
					<i class="ti-settings"></i>
				</li>
			</ul>
		</div>
	</div>
</div>