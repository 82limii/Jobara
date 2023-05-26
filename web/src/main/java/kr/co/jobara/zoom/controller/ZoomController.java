package kr.co.jobara.zoom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ZoomController {
	@RequestMapping("/zoom/zoomIndex.do")
	public String zoom() {
		return "zoom/index";
	}
	@RequestMapping("/zoom/meeting.do")
	public String meeting() {
		return "zoom/meeting";
	}
}
