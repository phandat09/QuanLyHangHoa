package com.inventory_manegement.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class HttpUtils {

	public static String NAME_OF_CART = "NAME_OF_CART";
	
	/**
	 * Ngăn khởi tạo class.
	 */
	private HttpUtils() {
	}

	/**
	 * hàm này sẽ trả về dữ liệu chứa trong session theo tên.
	 * 
	 * @param <Bat_ki_kieu_j_cung_duoc>
	 * @param name
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <Bat_ki_kieu_j_cung_duoc> Bat_ki_kieu_j_cung_duoc getSessionValue(final String name,
			final HttpServletRequest request) {
		// lấy session từ HttpServletRequest.
		HttpSession session = request.getSession();
		// ép object trong session sang kiểu Bat_ki_kieu_j_cung_duoc.
		return (Bat_ki_kieu_j_cung_duoc) session.getAttribute(name);
	}

	/**
	 * Kiểm tra dữ liệu có trong session hay không ?
	 * 
	 * @param name
	 * @param request
	 * @return
	 */
	public static boolean isExistsSessionValue(final String name, final HttpServletRequest request) {
		// lấy session từ HttpServletRequest.
		HttpSession session = request.getSession();
		return (session.getAttribute(name) == null) ? false : true;
	}

	/**
	 * Hàm xử lí lưu dữ liệu vào trong session.
	 * 
	 * @param name
	 * @param request
	 */
	public static void setSessionValue(final String name, final Object value, final HttpServletRequest request) {
		// lấy session từ HttpServletRequest.
		HttpSession session = request.getSession();
		session.setAttribute(name, value);
	}

}
