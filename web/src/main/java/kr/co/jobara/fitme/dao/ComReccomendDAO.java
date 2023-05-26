package kr.co.jobara.fitme.dao;

import org.apache.ibatis.annotations.Mapper;

import kr.co.jobara.fitme.vo.RecommendVO;
import kr.co.jobara.fitme.vo.SurveyVO;

/**
 * @author 이상림
 * 최초작성일 2022.03.23
 */
@Mapper
public interface ComReccomendDAO {

	/**
	 * 설문조사 내용 삽입
	 * @param survey
	 * @return
	 */
	public int insertSurvey(SurveyVO survey);
	
	/**
	 * 현재날짜의 설문조사 선택
	 * @param pmemId
	 * @return
	 */
	public SurveyVO selectTodaySurvey(String pmemId);
	
	/**
	 * 회원의 추천 리스트
	 * @param pmemId
	 * @return
	 */
	public RecommendVO selectRecommend(String pmemId);
}
