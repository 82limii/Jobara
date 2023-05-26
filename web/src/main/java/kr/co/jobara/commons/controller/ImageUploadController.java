package kr.co.jobara.commons.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ImageUploadController {

	@Inject
	private WebApplicationContext context;
	@Value("#{appInfo['board.image']}")
	private String folderURL;
	private File saveFolder;
	@PostConstruct
	public void init() throws IOException {
		Resource folderRes = context.getResource(folderURL);
		saveFolder = folderRes.getFile();
		log.info("게시판 이미지 저장 위치 : {}", saveFolder.getCanonicalPath());
	}
	
	@RequestMapping("/board/uploadImage.do")
	public String uploadImgae(
			@RequestPart(required=true) MultipartFile upload
			, Model model
	) throws IOException {
		if(!upload.isEmpty()) {
			String saveName = UUID.randomUUID().toString();
			File saveFile = new File(saveFolder, saveName);
			upload.transferTo(saveFile);
			model.addAttribute("uploaded", 1);
			model.addAttribute("fileName", upload.getOriginalFilename());
			model.addAttribute("url", context.getServletContext().getContextPath()+folderURL+"/"+saveName);
			
		}
		return "jsonView";
	}
}
