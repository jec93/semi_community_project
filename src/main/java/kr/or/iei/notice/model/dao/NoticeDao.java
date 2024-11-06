package kr.or.iei.notice.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kr.or.iei.common.JDBCTemplate;
import kr.or.iei.notice.model.vo.Notice;
import kr.or.iei.notice.model.vo.NoticeComment;
import kr.or.iei.notice.model.vo.NoticeFile;

public class NoticeDao {

	public ArrayList<Notice> selectNoticeList(Connection conn, String boardId,int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Notice> list = new ArrayList<Notice>();
		String query = "select * from (select rownum rnum,a.*from (select a.* from tbl_notice a where board_id = ? order by post_id desc) a)a where rnum between ? and ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, boardId);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Notice n = new Notice();
				n.setPostId(rset.getString("post_id"));
				n.setBoardId(rset.getString("board_id"));
				n.setBoardTitle(rset.getString("board_title"));
				n.setBoardContent(rset.getString("board_content"));
				n.setBoardWriter(rset.getString("user_no"));
				n.setCreatedDate(rset.getString("created_date"));
				n.setReadCount(rset.getInt("read_count"));
				n.setLikes(rset.getInt("likes"));
				list.add(n);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
				
		return list;
	}

	public int selectNoticeCount(Connection conn, String noticeCd) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int totCnt = 0;
		String query = "select count(*) cnt from tbl_notice where board_id=?";
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, noticeCd);
			rset=pstmt.executeQuery();
			if(rset.next()) {
				totCnt = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return totCnt;
	}

	public String selectPostId(Connection conn) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select to_char(sysdate, 'yymmdd')|| lpad(seq_notice.nextval,4,'0') as post_id from dual";
		String postId = "";
		
		try {
			pstmt = conn.prepareStatement(query);
			rset=pstmt.executeQuery();
			rset.next();
			postId = rset.getString("post_id");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return postId;
	}

	public int insertNotice(Connection conn, Notice notice) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into tbl_notice values(?,?,?,sysdate,default,default,?,?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, notice.getPostId());
			pstmt.setString(2, notice.getBoardTitle());
			pstmt.setString(3, notice.getBoardContent());
			pstmt.setString(4, notice.getBoardId());
			pstmt.setString(5, notice.getBoardWriter());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int insertNoticeFile(Connection conn, NoticeFile file) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		String query = "insert into notice_file values(to_char(sysdate, 'yymmdd')||lpad(seq_notice_file.nextval, 5,'0'),?,?,?)";
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, file.getFileName());
			pstmt.setString(2, file.getFilePath());
			pstmt.setString(3, file.getPostId());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public Notice selectOneNotice(Connection conn, String postId) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		Notice n = null;
		
		String query = "select a.*,c.board_name, b.nickname from tbl_notice a, tbl_board c, tbl_user b where a.post_id = ? and a.board_id = c.board_id and a.user_no = b.user_no";
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, postId);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				n = new Notice();
				n.setPostId(rset.getString("post_id"));
				n.setBoardId(rset.getString("board_id"));
				n.setBoardName(rset.getString("board_name"));
				n.setBoardTitle(rset.getString("board_title"));
				n.setBoardContent(rset.getString("board_content"));
				n.setBoardWriter(rset.getString("nickname"));
				n.setCreatedDate(rset.getString("created_date"));
				n.setLikes(rset.getInt("likes"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return n;
	}

	public int updateReadCount(Connection conn, String postId) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update tbl_notice set read_count = read_count +1 where post_id = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, postId);
			result = pstmt.executeUpdate();
			System.out.println("조회수 1증가");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public ArrayList<NoticeFile> selectNoticeFileList(Connection conn, String postId) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from notice_file where post_id = ?";
		ArrayList<NoticeFile> fileList = new ArrayList<NoticeFile>();
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, postId);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				NoticeFile file = new NoticeFile();
				file.setFileId(rset.getString("file_id"));
				file.setPostId(rset.getString("post_id"));
				file.setFileName(rset.getString("file_name"));
				file.setFilePath(rset.getString("file_path"));
				fileList.add(file);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return fileList;
	}

	public int updateNotice(Connection conn, Notice notice) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update tbl_notice set board_title = ?, board_content=? where post_id=?";
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, notice.getBoardTitle());
			pstmt.setString(2, notice.getBoardContent());
			pstmt.setString(3, notice.getPostId());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public int deleteNoticeFile(Connection conn, String fileNo) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt=null;
		int result = 0;
		String query = "delete from notice_file where file_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, fileNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int insertComment(Connection conn, NoticeComment comment) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "INSERT INTO tbl_comment VALUES (to_char(sysdate, 'yymmdd') || lpad(seq_comment.nextval, 5, '0'),?,?,sysdate,?,?)";
		
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, comment.getReplyTo());
			pstmt.setString(2, comment.getComments());
			pstmt.setString(3, comment.getCommentWriter());
			pstmt.setString(4, comment.getPostId());
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public ArrayList<NoticeComment> selectCommentList(Connection conn, String postId) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		String query = "select * from tbl_comment where post_id = ? order by comm_time desc";
		ResultSet rset = null;
		ArrayList<NoticeComment> list = new ArrayList<NoticeComment>();
		
		try {
			pstmt= conn.prepareStatement(query);
			pstmt.setString(1, postId);
			rset=pstmt.executeQuery();
			while(rset.next()) {
				NoticeComment c =new NoticeComment();
				c.setCommentId(rset.getString("comment_id"));
				c.setReplyTo(rset.getString("reply_to"));
				c.setCommentWriter(rset.getString("user_no"));
				c.setComments(rset.getString("comments"));
				c.setPostId(rset.getString("post_id"));
				c.setCommTime(rset.getString("comment_date"));
				list.add(c);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	public int deleteComment(Connection conn, String commentNo) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		
		int result = 0;
		String query = "delete from tbl_notice_comment where comment_no =?";
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, commentNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public int updateComment(Connection conn, NoticeComment comment) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		String query="update tbl_notice_comment set contents = ? where comment_id = ?";
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, comment.getComments());
			pstmt.setString(2, comment.getCommentId());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public ArrayList<Notice> selectIndexNoticeList(Connection conn) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt=null;
		ResultSet rset=null;
		//notice_cd별로 그룹지어 행번호 조회
		String query="select * from( select row_number() over (partition by board_id order by created_date desc) as rnum,a.* from tbl_notice a)where rnum<=5";
		ArrayList<Notice>list=new ArrayList<Notice>();
		try {
			pstmt=conn.prepareStatement(query);
			rset=pstmt.executeQuery();
			while(rset.next()) {
				Notice n=new Notice();
				n.setPostId(rset.getString("post_id"));
				n.setBoardId(rset.getString("board_id"));
				
				n.setBoardTitle(rset.getString("board_title"));
				n.setBoardContent(rset.getString("board_content"));
				n.setBoardWriter(rset.getString("user_no"));
				n.setCreatedDate(rset.getString("created_date"));
				n.setLikes(rset.getInt("likes"));
				list.add(n);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return list;
	}

	

	
}
