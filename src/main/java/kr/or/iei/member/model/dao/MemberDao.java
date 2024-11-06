package kr.or.iei.member.model.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kr.or.iei.common.JDBCTemplate;

import kr.or.iei.member.model.vo.User;

public class MemberDao {

	public int insertMember(Connection conn, User member) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into tbl_user(user_no, user_id, user_pw,user_email, nickname, phone)  values (seq_user.nextval,?,?,?,?,?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getUserId());
			pstmt.setString(2, member.getUserPw());
			pstmt.setString(3, member.getUserEmail());
			pstmt.setString(4, member.getNickname());
			pstmt.setString(5, member.getPhone());
			
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int idDuplChk(Connection conn, String userId) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select count(*) as cnt from tbl_user where user_id = ?";
		int cnt = 0;
		

		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, userId);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				//select 결과를 int로 꺼내온다
				cnt = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}

		return cnt;
	}

	public User userLogin(Connection conn, String loginId, String loginPw) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		User u = null;
		String query = "select * from tbl_user where user_id =? and user_pw=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, loginId);
			pstmt.setString(2, loginPw);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				u = new User();
				u.setUserNo(rset.getString("user_no"));
				u.setUserId(rset.getString("user_id"));
				u.setUserPw(rset.getString("user_pw"));
				u.setNickname(rset.getString("nickname"));
				u.setUserEmail(rset.getString("user_email"));
				u.setPhone(rset.getString("phone"));
				u.setUserDate(rset.getString("user_date"));
				u.setUserGrade(rset.getInt("grade"));
				u.setUserPoint(rset.getInt("point"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return u;
	}

	public int updateMember(Connection conn, User updUser) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "update tbl_user set nickname=?, user_email=?,phone=? where user_no=?";
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, updUser.getNickname());
			pstmt.setString(2, updUser.getUserEmail());
			pstmt.setString(3, updUser.getPhone());
			pstmt.setString(4, updUser.getUserNo());
			
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int deleteMember(Connection conn, String memberNo) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete from tbl_user where user_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int updateMemberPw(Connection conn, String userNo, String newUserPw) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update tbl_user set user_pw =? where user_no =?";
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, newUserPw);
			pstmt.setString(2, userNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public ArrayList<User> selectAllUser(Connection conn) {
		PreparedStatement pstmt = null;
		ArrayList<User> list = new ArrayList<User>();
		ResultSet rset = null;
		String query = "select * from tbl_user where grade != '100' order by user_no";
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				User u = new User();
				u.setUserNo(rset.getString("user_no"));
				u.setUserId(rset.getString("user_id"));
				u.setNickname(rset.getString("nickname"));
				u.setPhone(rset.getString("phone"));
				u.setUserEmail(rset.getString("user_email"));
				u.setUserGrade(rset.getInt("grade"));
				u.setUserDate(rset.getString("user_date"));
				u.setUserPoint(rset.getInt("point"));
				
				list.add(u);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return list;
	}

	public int updChgLevel(Connection conn, String userNo, String userGrade) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update tbl_user set grade = ? where user_no = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userGrade);
			pstmt.setString(2, userNo);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} finally {
			JDBCTemplate.close(pstmt);
			
		}
		
		return result;
	}

	public String sechInfoId(Connection conn, String srchEmail) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt=null;
		ResultSet rset=null;
		String query="select user_id from tbl_user where user_email=?";
		String userId=null;
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1,srchEmail);
			rset=pstmt.executeQuery();
			if(rset.next()) {
				userId=rset.getString("user_id");
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return userId;
	}

	

	
}
