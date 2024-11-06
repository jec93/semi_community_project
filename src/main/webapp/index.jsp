<%@page import="kr.or.iei.news.model.vo.NewsItem"%>
<%@page import="kr.or.iei.search.word.vo.Word"%>
<%@page import="kr.or.iei.aside.model.vo.Product"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ArrayList<Product> ProductList = (ArrayList<Product>)request.getAttribute("productList");
	ArrayList<Word> wordList = (ArrayList<Word>)request.getAttribute("wordList");
	ArrayList<NewsItem> newsList = (ArrayList<NewsItem>)request.getAttribute("newsList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.newsTitle{
    font-size: 14px;
}
.newsDescription{
    font-size: 12px;
}
.newsContainer{
    width: 500px;
    height: 500px;
    margin-bottom: 20px;
    overflow: hidden;
}
</style>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
    <div class="body-content">
    	<aside>관리자용 영역 + 화면 리모콘</aside>
        <main>
            <h1>HTML5 aside 태그 예시</h1>
            <p>이 페이지는 <code>aside</code> 태그 사용법을 설명하는 예시입니다. <code>aside</code> 태그는 주 콘텐츠와 관련된 추가 정보를 제공할 때 유용합니다. dddddddddddddddddddddddddddddddd ddddddd</p>
            <p>예를 들어, 본문 내용이 기사라면, <code>aside</code>에는 관련 기사나 참고 자료, 광고 등이 올 수 있습니다.</p><br><br>
            <div class="newsContainer">
            뉴스 임시 배치<br>
			<%for(int i=0; i<newsList.size(); i++) {%>
				<div class="newsTitle"><a href="${newsList.get(i).getLink() }"><%=newsList.get(i).getTitle() %></a></div>
				<div class="newsDescription"><%=newsList.get(i).getDescription() %></div>
				<br>
			<%} %>
</div>
            <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
        </main>
        
          <aside>
          	<h2>인기검색어 현황</h2>
          	<div class="one" style="fixed; width: 250px; height: 40px; overflow: hidden;">
	        <div id="animated-box" style="width: 250px; height: 100px; margin-top: 0; position: relative; transition:0.5s ease; font-size:30px">
	        <%for(int i=0; i<wordList.size(); i++) {%>
				<%=wordList.get(i).getSrchRank() %>
				<%=wordList.get(i).getSrchWord() %>
				<br>
			<%} %>
	        </div>
	    </div>
          	
          	
          	
          	
            <h2>핫딜!!</h2>
			<div class="aside-container">
				<% for(int i = 0; i < ProductList.size(); i++) { %>
				    <div><a href="<%= ProductList.get(i).getShopLink() %>"><%= ProductList.get(i).getShopTitle() %></a></div>
				    <div class="aside-content">
				        <img src="<%= ProductList.get(i).getShopImg() %>" class="aside-image-box">
				        <div class="aside-tags">
				            <div class="aside-tag"><%= ProductList.get(i).getShopLowPrice() %>원!!</div>
				            <div class="aside-tag">판매처 : <%= ProductList.get(i).getShopName() %></div>
				            <div class="aside-tag">제품 카테고리 : <%= ProductList.get(i).getShopCategory1() %></div>           
				        </div>
				    </div>
				<% } %>
			</div>
            <a href="https://maplestory.nexon.com/Home/Main"><img src="resources/image/광고이미지예시.gif" class="god"></a>
          </aside>
    </div>
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
    </script>
</body>
</html>