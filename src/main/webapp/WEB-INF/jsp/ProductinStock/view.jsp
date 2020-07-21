<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!-- tag-libs -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!-- JSTL -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<body>
<h1 class="my-4">
		<small>Sản Phầm Trong kho .</small>
	</h1>
	<form class="form-inline" action="/product-in-stock/list" method="post"
		id="frmCate">
		<label for="email" class="mr-sm-2">Name:</label> <input
			class="form-control mb-2 mr-sm-2" name="name" value="${name}">

	 <input type="hidden" name="page" id="page" value="${page }">
		<input type="hidden" name="numberOfRecords" id="numberOfRecords"
			value="${numberOfRecords }"> <input type="hidden"
			name="totalPages" id="totalPages" value="${totalPages }">

		<button type="submit" class="btn btn-primary mb-2">Search</button>
	</form>
	<table class="table table-striped ">
		<thead>
			<tr style="color: white">
				<th scope="col">STT</th>
				<th scope="col">Hãng</th>
				<th scope="col">Tên</th>
				<th scope="col">Hình Ảnh</th>
				<th scope="col">Ngày Nhập Kho</th>
				<th scope="col">Số Lượng</th>
			</tr>
		</thead>
		<tbody>
			<c:set var="stt" value="0"></c:set>
			<c:forEach items="${products}" var="product">
				<c:set var="stt" value="${stt +1  }"></c:set>
				<tr>
					<th scope="row">${stt}</th>
					<td>${product.productInfors.categorys.name}</td>
					<td>${product.productInfors.name }</td>
					<c:set var="imgurl"
						value="${fn:split(product.productInfors.imgUrl, '/')}" />
					<c:set var="imgURL" value="${imgurl[fn:length(imgurl) -1]}" />
					<td><img alt="hình ảnh" width="100px" src="/files/${imgURL}"></td>
					<%-- <fmt:parseDate  value="${product.createDateTime}"  type="date" pattern="dd-MM-yyyy" var="parsedDate" />
					<td><fmt:formatDate value="${parsedDate}" pattern="dd-MM-yyyy"/></td> --%>
					<td>${product.createDateTime} </td>
					<td>${product.quality }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<ul class="pagination justify-content-center" style="margin:20px 0">
		<li class="page-item">
			<button class="page-link" onclick="IV.previous('frmCate');" id="btnPrevious">Previous</button>
		</li>
		<li class="page-item">
			<button class="page-link" onclick="IV.next('frmCate');" id="btnNext">Next</button>
		</li>
	</ul>
	<div id="pagination"></div>
</body>