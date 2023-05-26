package kr.co.jobara.jobboard.dao;

import static org.junit.Assert.assertEquals;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import kr.co.jobara.jobboard.vo.JobBoardVO;

@RunWith(SpringRunner.class)
@ContextConfiguration("file:webapp/WEB-INF/spring/*-context.xml")
@WebAppConfiguration
public class JobBoardDAOTest {
	
	@Inject
	JobBoardDAO dao;

	@Test
	public void testInsertJobBoard() {
		JobBoardVO jobBoard = JobBoardVO.builder()
								.ememId("ejava")
								.jobTitle("JAVA 개발자 모집합니다")
								.jobEduCd("PE003")
								.jobCarCd("CA001")
								.jobEmpCd("EM001")
								.jobPay("3000만원")
								.jobLocCd("RE001")
								.jobDetail("모집인원 : 00명, 지원자격 : 결격사유 없는~~ 배아팡")
								.jobStartd("2022-02-10")
								.jobEndd("2022-03-01")
								.jobWay("잡아라 지원")
								.jobSkill("Java, Spring, SpringBoot")
								.build();
		int row = dao.insertJobBoard(jobBoard);
		assertEquals(1, row);
	}

}
