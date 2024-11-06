<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="/resources/css/default.css"/>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

<header class="header">
        <div class="logo">
            <a href="/">
                <img src="" alt="로고">
            </a>
        </div>
        <div class="contnent-search">
            <input type="search" name="search" id="search" placeholder="게시판명 & 통합검색">
            <a href="#">
                <img src="resources/image/search_24dp_FFFFFF_FILL0_wght400_GRAD0_opsz24.svg" alt="검색">
            </a>
        </div>
        <nav class="nav">
            <ul>
            <%-- Listener에서 조회한, 메뉴 종류 --%>
				<c:forEach var="noticeType" items="${noticeTypeList}">
					<li>
						<a href="/notice/list?reqPage=1&boardId=${noticeType.boardId}&boardName=${noticeType.boardName}">${noticeType.boardName}</a>
					</li>
				</c:forEach>
				<li>
					<a href="#">API</a>
					<ul class="sub-menu">
						<li><a href="/api/naverMaps">지도</a></li>
						<li><a href="/api/emailSendFrm">이메일</a></li>
					</ul>
				</li>
                <li>
                    <a href="#">
                        <img src="resources/image/menu_24dp_E8EAED_FILL0_wght400_GRAD0_opsz24.svg" alt="메뉴">
                    </a>
                    <div class="sub-menu">
                        <ul>
                            <div class="username">
                                <a href="">닉네임</a> 
                                <div>포인트 : 3000p</div>
                            </div>
                            <div class="dropdown-bottom-line"></div>
                            <li><a href="">작성글</a></li>
                            <li><a href="">스크랩 목록</a></li>
                            <li><a href="">로그아웃</a></li>
                        </ul>
                    </div>
                </li>
            </ul>
        </nav>
    </header>