package kr.co.jobara.qna.vo;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotBlank;

import org.springframework.web.multipart.MultipartFile;

import kr.co.jobara.commons.vo.AttatchVO;
import kr.co.jobara.validate.hints.InsertGroup;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode(of="qnaSn")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QnaVO implements Serializable {
	
	private int rnum;
	
	private Integer qnaSn;
	private Integer qnaSn2;
	@NotBlank
	private String qnaTitle;
	@NotBlank
	private String qnaContents;
	private String qnaDate;
	private String pmemId;
	private String ememId;
	private String attSn;
	private String qnaEdit;
	
	private MultipartFile[] qnaFiles;
	
	private List<AttatchVO> attatchList;
}
