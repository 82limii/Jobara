package kr.co.jobara.board.askit.service;

import java.util.List;

import kr.co.jobara.board.askit.vo.CommentsVO;
import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.enumpkg.ServiceResult;

public interface CommentsService {
	public ServiceResult createReply(CommentsVO reply);
	public List<CommentsVO> readReplyList(PagingVO<CommentsVO> pagingVO);
	public ServiceResult modifyReply(CommentsVO reply);
	public ServiceResult removeReply(int commSn);
}
