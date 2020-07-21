<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<body>
	<h1 class="my-4">
		<small>Quản Lý Users</small>
	</h1>

	<table class="table" id="tableContact">
		<thead>
			<tr class="bg-success text-white">
				<th scope="col">Tên </th>
				<th scope="col">Địa chỉ email</th>
				<th scope="col">Quyền</th>
				<th scope="col">Kích hoạt</th>
				<th scope="col"></th>
			</tr>
		</thead>
		<tbody >
			<c:forEach var="user" items="${listUsers}" >
				<tr id="row-${user.id }">
					<td>${user.name }</td>
					<td>${user.email }</td>
					<c:set value="${user.roles }" var="role"></c:set>
					<c:forEach items="${ role}" var="r">
					<td>${r.roleName }</td>
					</c:forEach>
					<td>
					
						<c:choose>
							<c:when test="${user.activeFlag == 1}">
					            <span id="col-status-${user.id}" class="badge badge-success">Có</span>
					         </c:when>
							<c:otherwise>
					            <span id="col-status-${user.id}" class="badge badge-danger">Không</span>
					         </c:otherwise>
						</c:choose>
						
					</td>
					<td>
						<a type="button" class="btn btn-primary glyphicon glyphicon-edit" href="${pageContext.request.contextPath}/editUser/${user.id}">
						
					    </a>
					    <button type="button" class="btn btn-danger glyphicon glyphicon-remove" onclick="IV.delete('IV.deleteUser(${user.id})');" >
					    </button>
					    <a type="button" class="btn btn-success glyphicon glyphicon-plus" href="${pageContext.request.contextPath}/register" > 
					    </a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	
	<h1 class="my-4"></h1>
</body>