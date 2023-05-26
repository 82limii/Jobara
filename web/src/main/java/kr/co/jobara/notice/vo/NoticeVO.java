package kr.co.jobara.notice.vo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode(of="noticeSn")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class NoticeVO implements Serializable {
	
	private int rnum;
	
	private Integer noticeSn;
	private String adminId;
	@NotBlank
	private String noticeTitle;
	@NotBlank
	private String noticeContents;
	private String noticeDate;
	private String noticeEdit;
}
