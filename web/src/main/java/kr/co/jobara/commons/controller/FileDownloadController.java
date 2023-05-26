package kr.co.jobara.commons.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.jobara.commons.dao.AttatchDAO;
import kr.co.jobara.commons.exception.PKNotFoundException;
import kr.co.jobara.commons.service.AttatchService;
import kr.co.jobara.commons.vo.AttatchVO;

@Controller
public class FileDownloadController {
   @Inject
   private AttatchService service;;
   
   @RequestMapping("/download.do")
   public String download(
      @RequestParam("what") String attSn,
      @RequestParam(value="num", required=false, defaultValue="1") int attNum,
      Model model
   ) {
      List<AttatchVO> fileList = service.retrieveAttatchList(attSn);
      
      if (fileList == null || fileList.size() == 0) {
         throw new PKNotFoundException("파일이 존재하지 않습니다");
      }
      
      AttatchVO file = fileList.get(attNum-1);
      
      if (file == null) {
         throw new PKNotFoundException("파일이 존재하지 않습니다");
      }
      
      model.addAttribute("attatch", file);
      
      return "downloadFile";
   }
}