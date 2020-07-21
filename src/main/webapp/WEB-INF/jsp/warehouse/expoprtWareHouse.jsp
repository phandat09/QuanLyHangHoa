<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!-- tag-libs -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!-- JSTL -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="col-md-10 offset-md-1">
	<span class="anchor" id="formComplex"></span>
	<hr class="my-5">
	<h3>Kê Khai Xuất Kho Cho Sản Phẩm</h3>
	<c:if test="${not empty message }">
		<p style="color: green;">${message}</p>
	</c:if>
	<c:if test="${not empty messageError }">
		<p style="color: red">${messageError}</p>
	</c:if>
	<!-- form complex example -->
	<form:form action="/goods-issue/list" method="post"
		modelAttribute="invoke" enctype="multipart/form-data">

		<form:hidden path="type" value="2" />
		<div class="form-row mt-4">
			<div class="col-sm-5 pb-3">
				<label for="txtInput">Sản Phẩm </label>
				<form:select class="form-control" path="productInfors" onchange="IV.getQualityProduct()">
					<form:options items="${product}" itemLabel="name" />
				</form:select>
			</div>
			<div class="col-sm-3 pb-3">
				<label for="exampleCtrl">Số Lượng</label>
				<form:input class="form-control" id="exampleCtrl" path="quality" />
			</div>
			<div class="col-sm-4 pb-3">
				<label for="exampleAmount">Giá Tiền</label>
				<div class="input-group">
					<div class="input-group-prepend">
						<span class="input-group-text">$.000.000</span>
					</div>
					<form:input class="form-control" id="exampleAmount" path="price" />
				</div>
			</div>
			<div class="col-sm-6 pb-3">
				<label for="exampleFirst">Họ Đệm Người Nhập </label> <input
					type="text" class="form-control" id="exampleFirst" name="firstName">
			</div>
			<div class="col-sm-6 pb-3">
				<label for="exampleLast">Tên Người Nhập</label> <input type="text"
					class="form-control" id="exampleLast" name="lastName">
			</div>
			<div class="col-sm-12 pb-3">
				<label for="exampleCity">Địa Chỉ</label> <input type="text"
					class="form-control" id="exampleCity" name="address">
			</div>
			<div class="col-sm-5 pb-3"></div>
			<div class="col-sm-2 pb-3">
				<button type="submit" class="btn btn-primary center">Xác
					Nhận</button>
			</div>
			<div class="col-sm-5 pb-3"></div>

		</div>
	</form:form>
</div>