package kr.co.jobara.board.shareit.vo;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import kr.co.jobara.commons.vo.AttatchVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of="rebSn")
public class ShareItVO {
	private int rnum;
	private String rebEdit;
	private Integer rebSn;
	private Integer rebSn2;
	private String rebComb;
	private String rebTitle;
	private String rebContents;
	private String rebDate;
	private String pmemId;
	private String pmemNm;
}
