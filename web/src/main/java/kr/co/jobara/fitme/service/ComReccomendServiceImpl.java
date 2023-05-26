package kr.co.jobara.fitme.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.co.jobara.enumpkg.ServiceResult;
import kr.co.jobara.fitme.dao.ComReccomendDAO;
import kr.co.jobara.fitme.vo.RecommendVO;
import kr.co.jobara.fitme.vo.SurveyVO;

@Service
public class ComReccomendServiceImpl implements ComReccomendService {
	@Inject
	private ComReccomendDAO comRecDao;

	@Override
	public ServiceResult createSurvey(SurveyVO survey) {
		return comRecDao.insertSurvey(survey) > 0 ? ServiceResult.OK : ServiceResult.FAIL;
	}

	@Override
	public SurveyVO retrieveTodaysSurvey(String pmemId) {
		SurveyVO survey = comRecDao.selectTodaySurvey(pmemId);
		if(survey == null) {
			survey = new SurveyVO();
			survey.setSurMsg("설문조사를 진행해주세요.");
		}
		return survey;
	}

	@Override
	public RecommendVO retrieveRecommend(String pmemId) {
		RecommendVO recommend = comRecDao.selectRecommend(pmemId);
		if(recommend == null) {
			recommend = new RecommendVO();
			recommend.setRecMsg("설문조사를 먼저 진행해주세요.");
		}
		return recommend;
	}

}
