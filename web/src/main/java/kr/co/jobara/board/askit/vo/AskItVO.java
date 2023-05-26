package kr.co.jobara.board.askit.vo;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import kr.co.jobara.commons.vo.AttatchVO;
import kr.co.jobara.validate.hints.UpdateGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="boardSn")
@Builder
public class AskItVO implements Serializable {

	private int rnum;
	@NotNull(groups=UpdateGroup.class)
	private Integer boardSn;
	private String pmemId;
	private String pmemNm;
	@NotBlank
	private String boardTitle;
	private String boardComb;
	@NotBlank
	private String boardContents;
	private String boardDate;
	private String attSn;
	private String boardEdit;
	//첨부파일
	private MultipartFile[] askItFiles;
	
	private List<AttatchVO> attatchList;
}




