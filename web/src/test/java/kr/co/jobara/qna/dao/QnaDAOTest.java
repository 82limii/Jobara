package kr.co.jobara.qna.dao;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import kr.co.jobara.qna.vo.QnaVO;

@RunWith(SpringRunner.class)
@ContextConfiguration("file:webapp/WEB-INF/spring/*-context.xml")
@WebAppConfiguration
public class QnaDAOTest {

	@Inject
	private QnaDAO dao;
	
	@Test
	public void testInsertQna() {
		QnaVO qna = new QnaVO();
		qna.setQnaTitle("테스트qna");
		qna.setQnaContents("아아아아아아아앙");
		qna.setPmemId("nana01");
		int cnt = dao.insertQna(qna);
	}

}
