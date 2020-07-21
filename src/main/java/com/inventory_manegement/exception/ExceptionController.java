package com.inventory_manegement.exception;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.inventory_manegement.contronller.BaseController;
/**
 * hiểu là 1 servlet
 * @author PHAN DAT
 *
 */
@Controller
public class ExceptionController extends BaseController implements ErrorController {

	@Override
	public String getErrorPath() {
		// TODO Auto-generated method stub
		return "/error";
	}
	Logger logger = LoggerFactory.getLogger(ExceptionController.class);
	@RequestMapping("/error")
	public String handleError(ModelMap model,final HttpServletRequest request, final HttpServletResponse response) {
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		//Exception exception = (Exception) request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
		//model.addAttribute("message",exception);
		if (status != null) {
			Integer statusCode = Integer.valueOf(status.toString());

			if (statusCode == HttpStatus.NOT_FOUND.value()) {
				return "/exceptions/error-404";

			} else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
				return "/exceptions/error-500";
			}

		}
		return "exceptions/error";
	}
	@Override
	protected String name() {
		return "";
	}


}
