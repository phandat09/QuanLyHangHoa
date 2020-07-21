package com.inventory_manegement.contronller;

import org.slf4j.LoggerFactory;

/**
 * Lớp chung cho tất cả các Controller
 * @author PHAN DAT
 *
 */
public abstract class BaseController {
	/**
	 * tên của  Controller là j
	 * @return
	 */
	protected abstract String  name();
	public void infor(final Class<?>clazz,final Object msg) {
		LoggerFactory.getLogger(clazz).info(msg.toString());
	}
	public void error(final Class<?>clazz,final Object msg) {
		LoggerFactory.getLogger(clazz).error(msg.toString());
	}
}
