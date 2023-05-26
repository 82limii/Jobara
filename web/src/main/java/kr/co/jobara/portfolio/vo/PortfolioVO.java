package kr.co.jobara.portfolio.vo;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import kr.co.jobara.commons.vo.AttatchVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="portSn")
public class PortfolioVO {
	private int rnum;
	
	//PORTFOLIO
	private String pmemId;
	private Integer portSn;
	private String attSn;
	
	// ATTATCH
	private Integer attNum;
	private String attNm;
	private String attSave;
	private String attRoute;
	private String attId;
	private String attDate;
	private String attMime;
	private String attSize;
	private String attFancy;
	private String attDown;
	
	// 첨부파일
	private MultipartFile[] portfolioFiles;

	private AttatchVO attatch;
}
