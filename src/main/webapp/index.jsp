<%@page import="kr.or.iei.news.model.vo.NewsItem"%>
<%@page import="kr.or.iei.search.word.vo.Word"%>
<%@page import="kr.or.iei.aside.model.vo.Product"%>
<%@page import="java.util.ArrayList"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	ArrayList<NewsItem> newsList = (ArrayList<NewsItem>)request.getAttribute("newsList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.section{
		margin-top: 20px;
	}
	.list-header{
		text-align: right;
		margin-bottom: 10px;
	}
	.list-header>a:hover{
		text-decoration: underline;
	}
</style>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
    <div class="body-content">
    	<aside>관리자용 영역 + 화면 리모콘</aside>
        <main class="content">
        	<c:forEach var="notice" items="${noticeTypeList }">
	
			<section class="section type${notice.boardId }">
				<div class="page-title" style="text-align:left;">${notice.boardName}
					<div class="list-header">
						<a href="/notice/list?reqPage=1&boardId=${notice.boardId }&boardName=${notice.boardName}">더보기</a>
					</div>
					<div class="list-content">
						<table class="tbl hover">
							<tr class="th">
								<th style="width:10%">번호</th>
								<th style="width:50%">제목</th>
								<th style="width:20%">작성자</th>
								<th style="width:20%">작성일</th>
							</tr>
							
						</table>
					</div>
					</div>
			</section>
		</c:forEach>
		<section class="section type${notice.noticeList }">
				<div class="page-title" style="text-align:left;">${notice.boardName }
					<div class="list-header">
						<a href="/notice/list?reqPage=1&boardId=${notice.boardId }&boardName=${notice.boardName}">더보기</a>
					</div>
					<div class="list-content">
						<table class="tbl hover">
							<tr class="th">
								<th style="width:10%">번호</th>
								<th style="width:50%">제목</th>
								<th style="width:20%">작성자</th>
								<th style="width:20%">작성일</th>
							</tr>
							
						</table>
					</div>
					</div>
			</section>
            <div class="newsContainer">
			<%for(int i=0; i<newsList.size(); i++) {%>
				<div class="newsTitle"><a href="${newsList.get(i).getLink() }"><%=newsList.get(i).getTitle() %></a></div>
				<div class="newsDescription"><%=newsList.get(i).getDescription() %></div>
				<br>
			<%} %>
			</div>
            <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
        </main>
	<jsp:include page="/WEB-INF/views/common/aside_right.jsp"/>
    </div>
    <jsp:include page="/WEB-INF/views/common/footer.jsp"/>
    <script>
    let marginTop = 0; // 초기 margin-top 값
    const maxMarginTop = -405; // 최대 margin-top 값
    const animatedBox = document.getElementById('animated-box');

	    setInterval(function() {
	        marginTop -= 45; // 
	        if (marginTop < maxMarginTop) { // 특정 위치에 도달하면
	            animatedBox.style.transition = 'none'; // 애니메이션 제거
	            marginTop = 0; // margin-top을 0으로 재설정
	            animatedBox.style.marginTop = marginTop + 'px';
	         // 약간의 지연 후 애니메이션 다시 활성화
	        setTimeout(() => {
	            animatedBox.style.transition = 'margin-top 0.5s ease';
	        }, 10);
	    } else {
	        animatedBox.style.marginTop = marginTop + 'px';
	    }
	}, 1000);
	    
	    function noticeList(){
			$.ajax({
				url: "/notice/index",
				type:"GET",
				dataType: "json",//서블릿에서 응답해주는 데이터의 형식
				success:function(res){
					console.log(res);
					$(res).each(function(index,item){
						let html='';
						html+="<tr>";
						html+="<td>"+item.boardId+"</td>";
						html+="<td><a href='/notice/view?postId="+item.postId+"'>'"+item.boardTitle+"</a></td>";
						html+="<td>"+item.boardWriter+"</td>";
						html+="<td>"+item.createdDate+"</td>";
						html+="</tr>";
						
						$('.section.type'+item.boardId).find('.tbl').append(html)
					});
				},
				error:function(){
					console.log("실패");
				}
			});
			
			}
	
	
	setInterval(function(){
		noticeList();
		},1000*60*10);//10분에 1번씩
	
$(function(){
	noticeList();
	});
    </script>
</body>
</html>