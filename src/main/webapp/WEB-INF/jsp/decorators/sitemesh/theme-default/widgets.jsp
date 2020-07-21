<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Sidebar Holder -->
        <nav id="sidebar">
            <div class="sidebar-header">
                <h1>
                    <a href="/home">Inventory Manegement</a>
                </h1>
                <span>I</span>
            </div>
            <div class="profile-bg"></div>
            <ul class="list-unstyled components">
            	<c:forEach items="${menu}" var ="itemMenu" varStatus="ac">
            	<c:if test="${ac.index == 0}">
                <li class="active">
            	</c:if>
            	<c:if test="${ac.index != 0}">
                <li>
            	</c:if>
                    <a href="#menu${ac.index}" data-toggle="collapse" aria-expanded="false">
                        <i class="${itemMenu.icons}"></i>
                        ${itemMenu.name}
                         <i class="fas fa-angle-down fa-pull-right"></i>
                    </a>
                    <ul class="collapse list-unstyled" id="menu${ac.index}">
                    	<c:set var="childrenMenu" value="${itemMenu.childrenMenu }"></c:set>
                    	<c:forEach items="${childrenMenu}" var="item">
                    		<c:if test="${item.icons != '-1' }">
                        <li>
                            <a href="${item.url}">${item.name}</a>
                        </li>
                    		</c:if>
                    	</c:forEach>
                    </ul>
                </li>
            	</c:forEach>
            </ul>
        </nav>