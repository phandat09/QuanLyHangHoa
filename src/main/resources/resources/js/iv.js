/**
 * 
 *//**
	 * moojt file javascript chung cho dự án
	 * 
	 */
var IV = {
		init: function() {
			// ::: for paging
			try {
				var currentPage = parseInt($( "#page" ).val());
				var totalPages = parseInt($( "#totalPages" ).val());
				console.log(totalPages)
				if(currentPage === 1) {
					$("#btnPrevious").attr("disabled", true);
				} else {
					$("#btnPrevious").attr("disabled", false);
				}
				if(currentPage === totalPages ) {
					$("#btnNext").attr("disabled", true);
				} else {
					$("#btnNext").attr("disabled", false);
				}
			} catch(e) {}
			
			/*
			 * // ::: for richtext editor $(document).ready(function() {
			 * $('#txtLongDescription').summernote(); });
			 */
			
		},
		changeOption: function() {
//			$('#idRole').find('option:selected').remove().end();
			console.log("ok")
			
		},
		
		deleteCategories: function(element) {
			var data ={}; // khai báo kiểu json
			data["categoryId"] = element
			$.ajax({
				url : "/api/deleteCategory",
				type  : "POST",
				contentType : "application/json",// dữ liệu gửi lên
													// web-service
				data : JSON.stringify(data),  // chuyển object json sang
												// string json
				dataType : "json", // dữ liệu từ web-service trả về là json
				success: function(jsonResult) {
					$('#col-status-'+element).removeClass('badge badge-success');
					$('#col-status-'+element).addClass('badge badge-danger');
					$('#col-status-'+element).html("InActive");
				}
			})
		},
		
		deleteproduct: function(element) {
			var data ={}; // khai báo kiểu json
			data["pruductID"] = element
			$.ajax({
				url : "/api/deleteProduct",
				type  : "POST",
				contentType : "application/json",// dữ liệu gửi lên
				// web-service
				data : JSON.stringify(data),  // chuyển object json sang
				// string json
				dataType : "json", // dữ liệu từ web-service trả về là json
				success: function(jsonResult) {
					$('#col-status-'+element).removeClass('badge badge-success');
					$('#col-status-'+element).addClass('badge badge-danger');
					$('#col-status-'+element).html("InActive");
				}
			})
		},
		deleteUser: function(element) {
			var data ={}; // khai báo kiểu json
			data["UserId"] = element
			$.ajax({
				url : "/api/deleteUser",
				type  : "POST",
				contentType : "application/json",// dữ liệu gửi lên
				// web-service
				data : JSON.stringify(data),  // chuyển object json sang
				// string json
				dataType : "json", // dữ liệu từ web-service trả về là json
				success: function(jsonResult) {
					$('#row-'+element).html("");
				}
			})
		},
		
		next: function(frmId) {
			var nextPage = parseInt($( "#page" ).val()) + 1;
			$("#page").val(nextPage);
			$("#"+frmId).submit(); // Submit the form
		},
		
		previous: function(frmId) {
			var nextPage = parseInt($( "#page" ).val()) - 1;
			$("#page").val(nextPage);
			$("#"+frmId).submit(); // Submit the form
		},
		/**
		 * Đăng kí nhận bản tin mới cho mail
		 */
		delete: function(methodFunction) {
			$('#blogModalDelete').modal('show');
			$("#btn_blogModalDelete").bind("click", function() { 
                eval(methodFunction); 
            }); 
		},
		
		getQualityProduct : function() {
			var value = $("#productInfors").val();
			var data ={}; // khai báo kiểu json
			data["productId"] = value
			$.ajax({
				url : "/api/getQualityProduct",
				type  : "POST",
				contentType : "application/json",// dữ liệu gửi lên
													// web-service
				data : JSON.stringify(data),  // chuyển object json sang
												// string json
				dataType : "json", // dữ liệu từ web-service trả về là json
				success: function(jsonResult) {
					var product = JSON.parse(jsonResult.data);
					$("#exampleCtrl").attr("placeholder",product.quality)
					$("#exampleAmount").val(product.price)
				}
			})
		},
		editRole : function(element) {
			var stateRole = $("#idRole").val();
			console.log(stateRole)
			var data ={}; // khai báo kiểu json
			data["userId"] = element;
			data["roleId"] =stateRole;
			
			$.ajax({
				url : "/api/getRole",
				type  : "POST",
				contentType : "application/json",// dữ liệu gửi lên
													// web-service
				data : JSON.stringify(data),  // chuyển object json sang
												// string json
				dataType : "json", // dữ liệu từ web-service trả về là json
				success: function(jsonResult) {
					var roles = JSON.parse(jsonResult.data);
					if(roles == ok){
						$("#message").html("Đã chỉnh sửa quyền thành công cho người dùng")
					}
				}
			})
		},
		
		onchangeBar : function() {
			var value = $("#yearValue").val();
			var data ={}; // khai báo kiểu json
			data["year"] = value
			$.ajax({
				url : "/api/onchangeBar",
				type  : "POST",
				contentType : "application/json",// dữ liệu gửi lên
													// web-service
				data : JSON.stringify(data),  // chuyển object json sang
												// string json
				dataType : "json", // dữ liệu từ web-service trả về là json
				success: function(jsonResult) {
					var product = JSON.parse(jsonResult.data);
					var dataImport =product['import'];
					var dataEmport =product['emport'];
					console.log(dataImport)
					console.log(dataEmport)
					var labelsIP = [];
					var labelIP = [];
					var labelEp =[];
					
					
					for(let i in dataImport){
						labelsIP.push("tháng"+i);
						labelIP.push(dataImport[i]);
					}
					for(let i in dataEmport){
						labelEp.push(dataEmport[i]);
					}
					
					console.log(labelsIP)
					console.log(labelIP)
					console.log(labelEp)
					Chart.defaults.global.defaultFontColor = '#000000';
					Chart.defaults.global.defaultFontFamily = 'Arial';
					var lineChart = document.getElementById('line-chart');
					var myChart = new Chart(lineChart, {
						type : 'line', // bar, horizontalBar, pie, line,
										// doughnut, radar,
										// polarArea
						data : {
							labels : labelsIP,
							datasets : [ {
								label : 'Nhập Kho',
								data : labelIP,
								backgroundColor : 'rgba(0, 128, 128, 0.3)',
								borderColor : 'rgba(0, 128, 128, 0.7)',
								borderWidth : 1
							}, {
								label : 'Xuất Kho',
								data : labelEp,
								backgroundColor : 'rgba(0, 128, 128, 0.7)',
								borderColor : 'rgba(0, 128, 128, 1)',
								borderWidth : 1
							} ]
						},
						options : {
							scales : {
								yAxes : [ {
									ticks : {
										beginAtZero : true
									}
								} ]
							},
						}
					});
				}
			})
		},
		
}

