package kr.co.jobara.pms.project.vo;

import java.io.Serializable;

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
@EqualsAndHashCode(of="notiSn")
@Builder
public class ProNoticeVO implements Serializable {
	
	private int rnum;
	
	@NotNull(groups=UpdateGroup.class)
	private Integer notiSn;
	private Integer proSn;
	@NotBlank
	private String notiTitle;
	@NotBlank
	private String notiContents;
	private String notiDate;
	private String notiEdit;
	@NotBlank
	private String notiImportance;
	
	private String pmemId;
	private String pmemNm;
	
	private String attSn;
	
	//첨부파일
	private MultipartFile[] notiFiles;

	private AttatchVO attatch;
}
