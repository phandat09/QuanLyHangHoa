<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<body>
	<div class="bg-page py-5">
		<div class="container">
			<!-- main-heading -->
			<p id ="message"></p>
			<h2 class="main-title-w3layouts mb-2 text-center text-white">
				Quản lý quyền của user</h2>
			<!--// main-heading -->
			<table class="table" id="tableContact">
				<thead>
					<tr class="bg-success text-white">
						<th scope="col">Tên</th>
						<th scope="col">Tên Đăng Nhập</th>
						<th scope="col">Địa chỉ email</th>
						<th scope="col">Quyền</th>
						<th scope="col">Kích hoạt</th>
						<th scope="col"></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="user" items="${users}">
						<tr id="row-${user.id }">
							<td>${user.name }</td>
							<td>${user.userName }</td>
							<td>${user.email }</td>
							
								<c:set var="roleId" value="0"></c:set>
								<c:set value="${user.roles}" var="rrole"></c:set>
							<c:forEach items="${rrole}" var="ro">
								<c:set var="roleId" value="${ro.id}"></c:set>
							</c:forEach>
							
							<td><select id="idRole" onclick="IV.changeOption()">
									<c:forEach items="${roles}" var="r">
										<c:if test="${r.id == roleId }">
												<option value="${r.id}" selected>${r.roleName}</option>
										</c:if>
										<c:if test="${r.id != roleId }">
												<option value="${r.id}" >${r.roleName}</option>
										</c:if>
									</c:forEach>
							</select></td>
							<td><c:choose>
									<c:when test="${user.activeFlag == 1}">
										<span id="col-status-${user.id}" class="badge badge-success">Có</span>
									</c:when>
									<c:otherwise>
										<span id="col-status-${user.id}" class="badge badge-danger">Không</span>
									</c:otherwise>
								</c:choose></td>
							<td>
								<button type="button"
									class="btn btn-success glyphicon glyphicon-ok"
									onclick="IV.editRole(${user.id});"></button>

							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

		</div>
	</div>


</body>

