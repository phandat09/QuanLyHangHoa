<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!-- tag-libs -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!-- JSTL -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  

<body>
	<h1 class="my-4">
		<small>Danh sách Categories .</small>
	</h1>

	<form class="form-inline" action="/product-info/list" method="post" id="frmCate">
		<label for="email" class="mr-sm-2">Name:</label>
		<input class="form-control mb-2 mr-sm-2" name="name" value="${name}">
		
		<label for="sel1">Status:</label>
		<select class="form-control mb-2 mr-sm-2" name="status">
			<option value="all">all</option>
			<option value="0">Inactive</option>
			<option value="1">Active</option>
		</select>
		
		<input type="hidden" name="page" id="page" value="${page }">
		<input type="hidden" name="numberOfRecords" id="numberOfRecords" value="${numberOfRecords }">
		<input type="hidden" name="totalPages" id="totalPages" value="${totalPages }">
		
		<button type="submit" class="btn btn-primary mb-2">Search</button>
		
		<a href="/product-info/add" type="button" class="btn btn-info mb-2">Thêm Sản Phẩm</a>
	</form>
	
	<table class="table" id="tableContact">
		<thead>
			<tr>
				<th scope="col">name</th>
				<th scope="col">miêu tả</th>
				<th scope="col">Hãng</th>
				<th scope="col">HÌnh Ảnh</th>
				<th scope="col">Status</th>
				<th scope="col">action</th>
			</tr>
		</thead>
		<tbody >
			<c:forEach var="pi" items="${ProductInforList}">
				<tr>
					<td>${pi.name }</td>
					<td>${pi.description }</td>
					<c:set value="${pi.categorys}" var="cate"/>
					<td> ${cate.name }</td>
					<c:set var="imgurl" value="${fn:split(pi.imgUrl, '/')}" />
					<c:set var="imgURL" value="${imgurl[fn:length(imgurl) -1]}" />
					<td> <img src="/files/${imgURL }" class="rounded" alt="Cinque Terre" style="width: 200px;height: 100px;"> </td>
					<td>
					
						<c:choose>
							<c:when test="${pi.activeFlag == 1}">
					            <span id="col-status-${pi.id}" class="badge badge-success">Active</span>
					         </c:when>
							<c:otherwise>
					            <span id="col-status-${pi.id}" class="badge badge-danger">InActive</span>
					         </c:otherwise>
						</c:choose>
						
					</td>
					<td>
						<a type="button" class="btn btn-primary" href="${pageContext.request.contextPath}/product-info/edit/${pi.id}">
					      <span class=" glyphicon glyphicon-edit"></span> 
					    </a>
					    <button type="button" class="btn btn-danger" onclick="IV.delete('IV.deleteproduct(${pi.id})');" >
					      <span class="glyphicon glyphicon-remove"></span>
					    </button>
					</td>
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
	
	<h1 class="my-4"></h1>
</body>