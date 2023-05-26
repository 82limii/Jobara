package kr.co.jobara.ftp.controller;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Lazy;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.jobara.ftp.service.FTPFileService;
import kr.co.jobara.ftp.vo.FTPFileWrapper;

/**
 * 장인슬
 * @author PC-17
 * 2022-03-15
 */
@Controller
@RequestMapping("/ftp/fileBrowser.do")
@Lazy
public class FTPBrowsingController {
	
	@Inject
	@Named("ftpPoolService")
	private FTPFileService service;
	
	@GetMapping
	// 문서함 뷰 
	public String view(){
		return "ftp/fileForm";
	}
	
	
	//APPLICATION_JSON_UTF8_VALUE==application/json;charset=UTF-8
	//파일목록 
	@GetMapping(produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<FTPFileWrapper> view(@RequestParam(required=false, defaultValue="/") String path) throws Exception{
		List<FTPFileWrapper> listFiles = service.traversing(path);
		return listFiles;
	}
}


