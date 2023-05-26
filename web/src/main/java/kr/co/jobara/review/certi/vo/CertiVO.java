package kr.co.jobara.review.certi.vo;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import kr.co.jobara.commons.vo.AttatchVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 작성일 2022.03.04
 * @author 김승현
 * 리뷰인증(CR)용 VO
 */

@Data
@EqualsAndHashCode(of="certiSn")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CertiVO {

	private Integer certiSn;
	private String pmemId;
	private String ememId;
	private String certiState;
	private String certiNm;
	private String certiDiviCd;
	private String certiFile;
	private String certiSpac;
	private String langTest;
	private String langScore;
	private String actNm;
	private String eduName;
	private String eduMajor;
	
	private MultipartFile[] certiFiles;
	private List<AttatchVO> attatchList;
}
