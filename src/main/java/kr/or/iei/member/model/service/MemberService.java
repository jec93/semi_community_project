package kr.or.iei.member.model.service;

import java.sql.Connection
;
import java.util.ArrayList;
import java.util.StringTokenizer;

import kr.or.iei.common.JDBCTemplate;
import kr.or.iei.member.model.dao.MemberDao;

import kr.or.iei.member.model.vo.User;

public class MemberService {
	MemberDao dao;
	
	public MemberService() {
		dao=new MemberDao();
	}

	public int insertMember(User member) {
		// TODO Auto-generated method stub
		Connection conn = JDBCTemplate.getConnection();
		int result = 0;
		result = dao.insertMember(conn, member);
		if(result > 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		return result;
	}

	public int idDuplChk(String userId) {
		// TODO Auto-generated method stub
		Connection conn = JDBCTemplate.getConnection();
		int result= 0;
		result = dao.idDuplChk(conn,userId);
		JDBCTemplate.close(conn);
		
		return result;
	}
	
	public User userLogin(String loginId, String loginPw) {
		Connection conn = JDBCTemplate.getConnection();
		User member = dao.userLogin(conn,loginId,loginPw);
		JDBCTemplate.close(conn);
		return member;
	}

	public int updateMember(User updUser) {
		// TODO Auto-generated method stub
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.updateMember(conn, updUser);
		
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		return result;
	}

	public int deleteMember(String userNo) {
		// TODO Auto-generated method stub
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.deleteMember(conn, userNo);
		
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}
	
	public int updateMemberPw(String userNo, String newUserPw) {
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.updateMemberPw(conn, userNo, newUserPw);
		
		if(result > 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		
		return result;
	}
	
	public ArrayList<User> selectAllUser() {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<User> list = dao.selectAllUser(conn);
		JDBCTemplate.close(conn);
		return list;
		
	}

	public int updChgLevel(String memberNo, String memberLevel) {
		Connection conn = JDBCTemplate.getConnection();
		
		int result = dao.updChgLevel(conn,memberNo,memberLevel);
		
		if(result > 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		
		return result;
	}

	public int updChgAllLevel(String memberNoArrStr, String memberLevelArrStr) {
		//memberNoArrStr : 2410230054/2410230055/2410230056
		Connection conn = JDBCTemplate.getConnection();
		
		StringTokenizer st1 = new StringTokenizer(memberNoArrStr, "/");
		StringTokenizer st2 = new StringTokenizer(memberLevelArrStr, "/");
		
		
		
		boolean resultChk = true;
		while(st1.hasMoreTokens()) {
			String userNo = st1.nextToken();
			String userLevel = st2.nextToken();
			
			int result = dao.updChgLevel(conn, userNo, userLevel);
			
			if(result < 1) {
				resultChk = false;
				break;
			}	
		}
		if(resultChk) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		if(resultChk) {
			return 1;
		}else {
			return 0;			
		}
	}

	public String srchInfoId(String srchEmail) {
	Connection conn=JDBCTemplate.getConnection();
	
	String userId=dao.sechInfoId(conn,srchEmail);
	
	
	JDBCTemplate.close(conn);
	return userId;
			
	}

	
	

	
}
