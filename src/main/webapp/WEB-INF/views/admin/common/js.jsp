<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<script src="<c:url value="/template/admin/js/vendor/jquery-2.2.4.min.js" />"></script>

<script src="<c:url value="/template/admin/js/popper.min.js" />"></script>
<script src="<c:url value="/template/admin/js/bootstrap.min.js" />"></script>
<script src="<c:url value="/template/admin/js/owl.carousel.min.js" />"></script>
<script src="<c:url value="/template/admin/js/metisMenu.min.js" />"></script>
<script src="<c:url value="/template/admin/js/jquery.slimscroll.min.js" />"></script>
<script src="<c:url value="/template/admin/js/jquery.slicknav.min.js" />"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.2/Chart.min.js"></script>
<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://cdn.zingchart.com/zingchart.min.js"></script>

<script src="<c:url value="/template/admin/js/plugins.js" />"></script>
<script src="<c:url value="/template/admin/js/scripts.js" />"></script>

<script>
	$(document).ready(function(){
	  	$('[data-toggle="tooltip"]').tooltip();
	});
</script>


<!-- Language -->
<script>
	$(function(){
		$("a[data-lang]").click(function(){
			var lang = $(this).attr("data-lang");
			var url = $(location).attr('pathname');
			$.get(url + "?language="+ lang, function(){
				location.reload();
			});
			
			return false;
		});
	});
</script>
