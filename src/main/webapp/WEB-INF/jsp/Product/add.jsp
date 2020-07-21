<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!-- tag-libs -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!-- JSTL -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<body>
	<h1 class="my-4">
 <small>add Sản Phẩm</small>
 <c:if test="${not empty message }">
 	<p style="color: green;">${message}</p>
 </c:if>
 <c:if test="${not empty messageError }">
 	<p style="color: red">${messageError}</p>
 </c:if>
	</h1>
	<form:form action="/product-info/add" method="post" modelAttribute="pi"
		enctype="multipart/form-data">
		<div class="form-group">
			<label for="txtInput">Category</label>
			<form:select class="form-control" path="categorys">
				<form:options items="${categories}"  itemLabel="name"/>
			</form:select>
		</div>
		<div class="form-group">
			<label for="txtInput">Name</label>
			<form:input class="form-control" id="txtInput" path="name" />
		</div>
		<div class="form-group">
			<label for="txtShortDescription">Description</label>
			<form:textarea class="form-control" rows="5" id="txtShortDescription"
				path="description"></form:textarea>
		</div>
		<div class="form-group">
			<label for="txtLongDescription">Hình Ảnh</label> <input type="file"
				name="postImage" />
		</div>
		<button type="submit" class="btn btn-primary">ADD</button>
	</form:form>

	<h1 class="my-4"></h1>
</body>