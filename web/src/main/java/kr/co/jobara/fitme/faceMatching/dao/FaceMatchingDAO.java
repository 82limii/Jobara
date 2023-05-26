package kr.co.jobara.fitme.faceMatching.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.jobara.commons.vo.PagingVO;
import kr.co.jobara.fitme.faceMatching.vo.FaceMatchingVO;

/**
 * 작성일 2022.03.22
 * @author 김승현
 * 기업추천 (CRD)용 dao
 */
@Mapper
public interface FaceMatchingDAO {
	
	/**
	 * 기업추천 등록
	 * @param faceMatching
	 * @return
	 */
	public int insertFaceMatching(FaceMatchingVO faceMatching);
	
	/**
	 * 조건에 맞는 기업추천 갯수
	 * @param faceSn
	 * @return
	 */
	public int selectTotalRecord(PagingVO<FaceMatchingVO> pagingVO);
	
	/**
	 * 기업추천 리스트 조회
	 * @param pagingVO
	 * @return
	 */
	public List<FaceMatchingVO> selectFaceMatchingList(PagingVO<FaceMatchingVO> pagingVO);
	
	/**
	 * 기업추천  상세 조회
	 * @param pmemId
	 * @return
	 */
	public FaceMatchingVO selectFaceMatching(String pmemId);
	
	/**
	 * 기업추천 삭제
	 * @param faceSn
	 * @return
	 */
	public int deleteFaceMatching(int faceSn);
	
}
