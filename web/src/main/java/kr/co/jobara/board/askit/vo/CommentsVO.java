package kr.co.jobara.board.askit.vo;

import javax.validation.constraints.NotNull;

import kr.co.jobara.validate.hints.UpdateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of="commSn")
public class CommentsVO {
	@NotNull(groups=UpdateGroup.class)
	private Integer commSn;
	@NotNull(groups=UpdateGroup.class)
	private Integer boardSn;

	private String pmemId;
	
	private String commDate;
	
	private String commContents;
}
