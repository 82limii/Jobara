package kr.co.jobara.scrap.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScrapPostVO {

	private int rnum;
	
	// jobBoard 기업 컬럼
	private String ememNmJ;
	private String ememIdJ;
	
	// proBoard 기업 컬럼
	private String ememNmP;
	private String ememIdP;
	
	// searchBoard 컬럼
	private Integer searchSn;
	
	// 스크랩 vo
	private Integer scrapSn;
	private String pmemId;
	private String ememId;
	private String scrapDate;	
	
	// 프로젝트공고 vo
	private Integer probSn;
	private String probTitle;
	private String probStartd;	
	private String probEndd;
	private String probRendd;
	
	// 채용공고vo
	private Integer jobSn;
	private String jobTitle;
	private String jobEndd;
	
	// 기업 vo
	private String ememPic;
	private String ememAi;
	private String ememPass;
	private String ememNum;
	private String ememNm;
	private String ememCeo;
	private String ememZip;
	private String ememBadd;
	private String ememDadd;
	private String ememTel;
	private String ememFax;
	private String ememEmail;
	private String ememMan;
	private String ememMantel;
	
}
