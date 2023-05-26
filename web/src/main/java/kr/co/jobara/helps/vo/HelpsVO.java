package kr.co.jobara.helps.vo;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotBlank;

import org.springframework.web.multipart.MultipartFile;

import kr.co.jobara.commons.vo.AttatchVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode(of="helpsSn")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class HelpsVO implements Serializable {
	
	private int rnum;
	
	private Integer helpsSn;
	private Integer helpsSn2;
	@NotBlank
	private String helpsId;
	@NotBlank
	private String helpsTitle;
	@NotBlank
	private String helpsKindCd;
	@NotBlank
	private String helpsContents;
	private String attSn;
	private Integer helcoSn;
	private String helpsAnswer;
	private String helpsDate;
	
	// 답변여부 확인
	private boolean ans;
	
	private MultipartFile[] helpsFiles;
	
	private List<AttatchVO> attatchList;
	
	// 공통코드 조인
	private String helpsKind;
	
}
