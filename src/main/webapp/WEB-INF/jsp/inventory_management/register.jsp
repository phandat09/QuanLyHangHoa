<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>Inventor Management</title>
    <!-- Meta Tags -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="utf-8">
    <meta name="keywords" content="" />
    <script>
        addEventListener("load", function () {
            setTimeout(hideURLbar, 0);
        }, false);

        function hideURLbar() {
            window.scrollTo(0, 1);
        }
    </script>
    <script src='/js/jquery-2.2.3.min.js'></script>
    <script src='/js/script.js'></script>
    <!-- //Meta Tags -->
	<script type="text/javascript">
		$(document).ready(function(){
			Inventory.init();
		})
	</script>
    <!-- Style-sheets -->
    <!-- Bootstrap Css -->
    <link href="/css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
    <!-- Bootstrap Css -->
    <!-- Common Css -->
    <link href="/css/style.css" rel="stylesheet" type="text/css" media="all" />
    <!--// Common Css -->
    <!-- Fontawesome Css -->
    <link href="/css/fontawesome-all.css" rel="stylesheet">
    <!--// Fontawesome Css -->
    <!--// Style-sheets -->

    <!--web-fonts-->
    <link href="//fonts.googleapis.com/css?family=Poiret+One" rel="stylesheet">
    <link href="//fonts.googleapis.com/css?family=Open+Sans:300,400,600,700" rel="stylesheet">
    <!--//web-fonts-->
</head>

<body>
    <div class="bg-page py-5">
        <div class="container">
            <!-- main-heading -->
            <p>${messages}</p>
            <h2 class="main-title-w3layouts mb-2 text-center text-white">Register</h2>
            <!--// main-heading -->
            <div class="form-body-w3-agile text-center w-lg-50 w-sm-75 w-100 mx-auto mt-5">
                <form:form action="/register" modelAttribute="register" enctype="multipart/form-data" method="post">
                    <div class="form-group">
                        <label>Username</label>
                        <form:input type="text" path="userName" class="form-control" placeholder="Enter username" required=""/>
                    </div>
                    <div class="form-group">
                        <label>Email address</label>
                        <form:input type="email" path="email" class="form-control" placeholder="Enter email" required=""/>
                    </div>
                    <div class="form-group">
                        <label>Name</label>
                        <form:input type="text" path="name" class="form-control" placeholder="Enter email" required=""/>
                    </div>
                    <div class="form-group">
                        <label>Password</label>
                        <form:input type="password" path="password" class="form-control" placeholder="Password" required="" id="password"/>
                    </div>
                    <div class="form-group">
                        <label>Confirm Password</label>
                        <input type="password" class="form-control" placeholder="Password" required="required" id="confirm">
                        <label id="message"></label>
                    </div>
                    <div class="form-check text-center">
                        <input type="checkbox" class="form-check-input" id="exampleCheck1">
                        <label class="form-check-label" for="exampleCheck1">Agree the terms and policy</label>
                    </div>
                    <button type="submit" id="btnSubmit" class="btn btn-primary error-w3l-btn mt-sm-5 mt-3 px-4">REGISTER</button>
                </form:form>
                <p class="paragraph-agileits-w3layouts mt-4">Already have account?
                    <a href="/">Login</a>
                </p>
                <h1 class="paragraph-agileits-w3layouts mt-2">
                    <a href="/">Back to Home</a>
                </h1>
            </div>

            <!-- Copyright -->
            <div class="copyright-w3layouts py-xl-3 py-2 mt-xl-5 mt-4 text-center">
                <p>© 2018 Modernize . All Rights Reserved | Design by
                </p>
            </div>
            <!--// Copyright -->
        </div>
    </div>


    <!-- Required common Js -->
    <!-- //Required common Js -->

    <!-- Js for bootstrap working-->
    <script src="/js/bootstrap.js"></script>
    <!-- //Js for bootstrap working -->

</body>

</html>