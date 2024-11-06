<%@page import="kr.or.iei.search.word.vo.Word"%>
<%@page import="kr.or.iei.aside.model.vo.Product"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
ArrayList<Product> ProductList = (ArrayList<Product>)request.getAttribute("productList");
ArrayList<Word> wordList = (ArrayList<Word>)request.getAttribute("wordList");
%>
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