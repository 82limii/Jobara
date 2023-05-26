package kr.co.jobara.fitme.service;

import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.fitme.vo.RecommendVO;
import kr.co.jobara.fitme.vo.SurveyVO;

/**
 * @author 이상림
 * 최초작성일 2022.03.23
 */
public interface ComReccomendService {
	
	/**
	 * 설문조사 등록 프로세스
	 * @param survey
	 * @return
	 */
	public ServiceResult createSurvey(SurveyVO survey);
	
	/**
	 * 오늘 등록된 설문조사 조회
	 * @param pmemId
	 * @return
	 */
	public SurveyVO retrieveTodaysSurvey(String pmemId);
	
	/**
	 * 회원의 추천 목록 조회
	 * @param pmemId
	 * @return
	 */
	public RecommendVO retrieveRecommend(String pmemId);
}
