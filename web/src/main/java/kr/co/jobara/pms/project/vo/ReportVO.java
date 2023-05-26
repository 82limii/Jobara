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
@Builder
@EqualsAndHashCode(of="repoSn")
public class ReportVO implements Serializable{
	@NotNull(groups=UpdateGroup.class)
	private Integer repoSn;
	@NotNull(groups=UpdateGroup.class)
	private Integer proSn;
	private String pmemId;
	private String pmemNm;
	@NotBlank
	private String repoTitle;
	@NotBlank
	private String repoContents;
	@NotBlank(groups=UpdateGroup.class)
	private String repoDate;
	private String repoEdit;
	
	private String attSn;
	
	private MultipartFile[] repoFiles;
	
	private AttatchVO attatch;
	
}
