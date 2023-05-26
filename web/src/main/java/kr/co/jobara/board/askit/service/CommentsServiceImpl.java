package kr.co.jobara.board.askit.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.co.jobara.board.askit.dao.CommentsDAO;
import kr.co.jobara.board.askit.vo.CommentsVO;
import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.enumpkg.ServiceResult;

@Service
public class CommentsServiceImpl implements CommentsService {

	@Inject
	private CommentsDAO commentsDAO;
	
	@Override
	public ServiceResult createReply(CommentsVO reply) {
		int rowcnt = commentsDAO.insertReply(reply);
		return rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
	}

	@Override
	public List<CommentsVO> readReplyList(PagingVO<CommentsVO> pagingVO) {
		int totalRecord = commentsDAO.selectTotalRecord(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		List<CommentsVO> dataList = commentsDAO.selectReplyList(pagingVO);
		pagingVO.setDataList(dataList);
		return dataList;
	}

	@Override
	public ServiceResult modifyReply(CommentsVO reply) {
		int rowcnt = commentsDAO.updateReply(reply);
		return rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
	}

	@Override
	public ServiceResult removeReply(int commSn) {
		int rownum = commentsDAO.deleteReply(commSn);
		return rownum > 0 ? ServiceResult.OK : ServiceResult.FAIL;
	}

}
