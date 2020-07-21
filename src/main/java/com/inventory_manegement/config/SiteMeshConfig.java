package com.inventory_manegement.config;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;

/**
 * Cấu hình siteMesh
 * 
 * @author PHAN DAT
 *
 */
public class SiteMeshConfig extends ConfigurableSiteMeshFilter {

	@Override
	protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
		// Assigning default decorator if no path specific decorator found
		builder.addDecoratorPath("/*", "/WEB-INF/jsp/decorators/sitemesh/theme-default/default.jsp")
				// Exclude few paths from decoration.
				.addExcludedPath("/login")
				.addExcludedPath("/register"); // trang login không cần phải thực hiện layout.
	}
}
