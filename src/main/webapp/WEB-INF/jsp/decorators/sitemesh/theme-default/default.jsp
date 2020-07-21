<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="utf-8">
<meta name="keywords"
	content="Modernize Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template,
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, Sony Ericsson, Motorola web design" />

<title>Inventory Manegement</title>

    <!-- Style-sheets -->
    <!-- Bootstrap Css -->
    <link href="/css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
    <link href="/css/bootstrap.min.css" rel="stylesheet" type="text/css" media="all" />
    <link rel="stylesheet" href="/glyphicons/css/bootstrap-glyphicons.css">
    <!-- Bootstrap Css -->
    <!-- Common Css -->
    <link href="/css/style.css" rel="stylesheet" type="text/css" media="all" />
    <!--// Common Css -->
    <!-- Nav Css -->
    <link rel="stylesheet" href="/css/style4.css">
    <!--// Nav Css -->
    <!-- Fontawesome Css -->
    <link href="/css/fontawesome-all.css" rel="stylesheet">
    <!--// Fontawesome Css -->
    <!--// Style-sheets -->

    <!--web-fonts-->
    <link href="//fonts.googleapis.com/css?family=Poiret+One" rel="stylesheet">
    <link href="//fonts.googleapis.com/css?family=Open+Sans:300,400,600,700" rel="stylesheet">
    <!--//web-fonts-->
    <script src='/js/jquery-2.2.3.min.js'></script>
    <script src="/js/bootstrap.min.js"></script>
    <script src="/js/jquery.stellar.min.js"></script>
    <script src='/js/iv.js'></script>
    <script src='/js/Chart.min.js'></script>
    <script src='/js/bar.js'></script>
    <script type="text/javascript">
    $(document).ready(function(){
    	IV.init();
    	LineChart.init();
    	LineChart.bar();
    })
    </script>
</head>
<body>
	<!-- Page Content -->
    <div class="wrapper">
			<%@ include
				file="/WEB-INF/jsp/decorators/sitemesh/theme-default/widgets.jsp"%>
			<div id="content">
				<%@ include
				file="/WEB-INF/jsp/decorators/sitemesh/theme-default/menu.jsp"%>
					
					
					<sitemesh:write property='body' />
		
			<%@ include
				file="/WEB-INF/jsp/decorators/sitemesh/theme-default/footer.jsp"%>
			</div>
	</div>
			<%@ include
				file="/WEB-INF/jsp/decorators/sitemesh/theme-default/modals.jsp"%>

</body>
<script>
	$(document).ready(function() {
		$('#sidebarCollapse').on('click', function() {
			$('#sidebar').toggleClass('active');
		});
	});
</script>
<script>
        $(document).ready(function () {
            $(".dropdown").hover(
                function () {
                    $('.dropdown-menu', this).stop(true, true).slideDown("fast");
                    $(this).toggleClass('open');
                },
                function () {
                    $('.dropdown-menu', this).stop(true, true).slideUp("fast");
                    $(this).toggleClass('open');
                }
            );
        });
    </script>
</html>