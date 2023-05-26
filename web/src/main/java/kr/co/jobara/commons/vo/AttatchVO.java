package kr.co.jobara.commons.vo;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttatchVO {
	private MultipartFile oriFile;
	
	public AttatchVO(MultipartFile oriFile) {
		super();
		this.oriFile = oriFile;
		this.attNm = oriFile.getOriginalFilename();
		this.attMime = oriFile.getContentType();
		this.attSize = oriFile.getSize();
		this.attFancy = FileUtils.byteCountToDisplaySize(attSize);
		this.attSave = UUID.randomUUID().toString();
	}
	
	private String attSn;
	private String attNum;
	private String attNm;
	private String attSave;
	private String attRoute;
	private String attId;
	private String attDate;
	private String attMime;
	private Long attSize;
	private String attFancy;
	private String attDown;
	
	public void saveTo(File saveFolder) throws IOException {
		oriFile.transferTo(new File(saveFolder, attSave));
	}
}
