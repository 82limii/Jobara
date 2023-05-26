package kr.co.jobara.resume.vo;

import java.util.List;

import javax.validation.constraints.NotBlank;

import org.springframework.web.multipart.MultipartFile;

import kr.co.jobara.commons.vo.AttatchVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="eduSn")
public class EducationVO {
	
	private Integer eduSn;
	private Integer reSn;
	@NotBlank
	private String ediSn;
	@NotBlank
	private String eduName;
	private String eduMajor;
	@NotBlank
	private String eduSdate;
	@NotBlank
	private String eduEdate;
	@NotBlank
	private String eduState;
	private String eduScore;
	private String attSn;
	@NotBlank
	private String eduEnter;
	
	// 공통코드
	private String ediNm;
	private String stateNm;
	private String enterNm;
	
	// 첨부파일
	private MultipartFile[] eduFiles;

	private List<AttatchVO> attatchList;
	
	// 검증
	public boolean validate() {
		if(this.ediSn == null) {
			return false;
		}
		if(this.eduName == null) {
			return false;
		}
		if(this.eduSdate == null) {
			return false;
		}
		if(this.eduEdate == null) {
			return false;
		}
		
		return true; 
	}
	
}
