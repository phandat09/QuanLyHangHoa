<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!-- tag-libs -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!-- JSTL -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<div class="container-fluid content-top-gap">

	<nav aria-label="breadcrumb">
		<ol class="breadcrumb my-breadcrumb">
			<li class="breadcrumb-item"><a href="index.html">Home</a></li>
			<li class="breadcrumb-item active" aria-current="page">Dashboard</li>
		</ol>
	</nav>
	<div class="welcome-msg pt-3 pb-4">
		<h1>
			Hi <span class="text-primary">${users}</span>, Welcome back
		</h1>
	</div>

	<!-- statistics data -->
	<div class="statistics">
		<div class="row">
			<div class="col-xl-6 pr-xl-2">
				<div class="row">
					<div class="col-sm-6 pr-sm-2 statistics-grid">
						<div class="card card_border border-primary-top p-4">
							<i class="lnr lnr-users"> </i>
							<h3 class="text-primary number">${totaluses }</h3>
							<p class="stat-text">Total Users</p>
						</div>
					</div>
					<div class="col-sm-6 pl-sm-2 statistics-grid">
						<div class="card card_border border-primary-top p-4">
							<i class="lnr lnr-eye"> </i>
							<h3 class="text-secondary number">${totalProduct }</h3>
							<p class="stat-text">Tổng Sản Phầm</p>
						</div>
					</div>
				</div>
			</div>
			<div class="col-xl-6 pl-xl-2">
				<div class="row">
					<div class="col-sm-6 pr-sm-2 statistics-grid">
						<div class="card card_border border-primary-top p-4">
							<i class="lnr lnr-cloud-download"> </i>
							<h3 class="text-success number">${totalCategories }</h3>
							<p class="stat-text">Danh Mục Sản Phẩm</p>
						</div>
					</div>
					<div class="col-sm-6 pl-sm-2 statistics-grid">
						<div class="card card_border border-primary-top p-4">
							<i class="lnr lnr-cart"> </i>
							<h3 class="text-danger number">${totalExport }</h3>
							<p class="stat-text">Tổng số lần xuất kho</p>
						</div>
					</div>
				</div>
			</div>
		</div>
		<br />
		<div class="row">
		<c:set value="${totalImport}" var="h"></c:set>
		<input type="hidden" value="${h}" id ="totalImport">
			<input type="hidden" value="${totalEmport}" id ="totalEmport">
			<div class="col-sm-2"></div>
			<div class="col-sm-8">
				<div class="chart">
					<div class="row">
						<div class="col-lg-12 pr-lg-2 chart-grid">
							<div class="card text-center card_border">
								<div class="card-header chart-grid__header">
									Bảng Xuất Nhập Kho Theo Năm <label for="sel1"></label> 
									<select onselect="" onchange="IV.onchangeBar()" id="yearValue"
										class="form-control mb-2 mr-sm-2" name="status">
										<jsp:useBean id="now" class="java.util.Date" />
										<fmt:formatDate var="year" value="${now}" pattern="yyyy" />
										<c:forEach begin="2000" end="${year}" step="1" var="nam">
											<option <c:if test="${nam == year }">selected</c:if> value="${nam}">${nam}</option>
										</c:forEach>
									</select>
								</div>
								<div class="card-body">
									<!-- bar chart -->
									<div id="container">
										<canvas id="line-chart"></canvas>
									</div>
									<!-- //bar chart -->
								</div>
								<div class="card-footer text-muted chart-grid__footer">
									<!-- Updated 2 hours ago --></div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<%-- <div class="col-sm-5">
				<div class="chart">
					<div class="row">
						<div class="col-lg-12 pr-lg-2 chart-grid">
							<div class="card text-center card_border">
								<div class="card-header chart-grid__header">
									Bảng Xuất Nhập Kho Theo Tháng  <label for="sel1"></label> 
									<select 
										class="form-control mb-2 mr-sm-2" name="status">
										<jsp:useBean id="now1" class="java.util.Date" />
										<c:forEach begin="1" end="12" step="1" var="thang">
											<option <c:if test="${thang == month }">selected</c:if> value="${thang}">${thang} --${year}</option>
										</c:forEach>
									</select>
								</div>
								<div class="card-body">
									<!-- bar chart -->
									<div id="container">
										<canvas id="bar-chart"></canvas>
									</div>
									<!-- //bar chart -->
								</div>
								<div class="card-footer text-muted chart-grid__footer">
									<!-- Updated 2 hours ago --></div>
							</div>
						</div>
					</div>
				</div>
			</div> --%>
			<div class="col-sm-2"></div>
		</div>
	</div>
</div>
