package kr.or.iei.search.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.iei.search.word.service.SearchService;
import kr.or.iei.search.word.vo.Word;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/search")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//2.값추출
		String srchInput = request.getParameter("srchInput");
		
		//3.비즈니스 로직 - 검색
		SearchService service = new SearchService();
		ArrayList<Word> words = new ArrayList<Word>();
		//문장의 명사들을 뽑는 노가다 작업(?) 외부 라이브러리 실패, 부조사들은 추후 추가하거나 라이브러리 연결
		String[] wordsToRemove = {"이 ", "가 ", "을 ", "를 ", "의 ", "에 ", "에서 ", "로 ", "으로 ", "더러 ", "까지 ", "부터 ", "와 ", "과 ","랑 ", "이랑 ", "도 ", "만 ","뿐 ", "마저 ", "까지 ", "조차 ","나마 ", "마다 ","하고 ","게 "};
		for(String ch: wordsToRemove) {
			srchInput = srchInput.replaceAll(ch, " "); //부조사 삭제 작업
		}
		
		Boolean result = service.searchString(srchInput);
		
		//4.결과 처리
		RequestDispatcher view = null;
		if(result) {
			response.sendRedirect("/allMember");
		}else {
			view = request.getRequestDispatcher("/WEB_INF/views/search/InsertFail.jsp");
			view.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
