package kr.or.iei.notice.model.vo;

import java.util.ArrayList;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class Notice {
	private String postId;
	private String boardId;
	private String boardTitle;
	private String boardWriter;
	private String boardContent;
	private String createdDate;
	private int likes;
	private int readCount;
	
	//종속 데이터 변수 추가
	private String boardName;
	private ArrayList<NoticeFile> fileList;
	private ArrayList<NoticeComment> commentList;
}
