package com.htht.pro.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.htht.pro.Constants;
import com.htht.pro.PageSupport;
import com.htht.pro.bean.CovidMeetingInfectedBean;
import com.htht.pro.service.CovidMeetingInfectedService;



@Controller
@RequestMapping("/meetingInfected")
public class CovidMeetingInfectedController {
	
	@Autowired
	private CovidMeetingInfectedService covidMeetingInfectedService;
	
	@RequestMapping(value = "/query")
	public String query(
			@RequestParam(value = "queryname", required = false) String queryUserName,
			@RequestParam(value = "queryaddressName", defaultValue="",required = false) String queryaddressName,
			@RequestParam(value = "pageIndex", required = false) String pageIndex,
			HttpServletRequest request,Model m) {
		
		if(request.getSession().getAttribute(Constants.USER_SESSION) == null){
			m.addAttribute("userName", "");
			m.addAttribute("password", "");
			m.addAttribute("error", "");
			return "/login";
		}
		
		List<CovidMeetingInfectedBean> meetingInfectedList = null;
		int pageSize = Constants.pageSize;
		int currentPageNo = 1;
		if (queryUserName == null) {
			queryUserName = "";
		}

		if (pageIndex != null) {
			try {
				currentPageNo = Integer.valueOf(pageIndex);
			} catch (NumberFormatException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		int totalCount = covidMeetingInfectedService.getPageListCount(queryUserName,queryaddressName);
		PageSupport pages = new PageSupport();
		pages.setCurrentPageNo(currentPageNo);
		pages.setPageSize(pageSize);
		pages.setTotalCount(totalCount);
		int totalPageCount = pages.getTotalPageCount();

		if (currentPageNo < 1) {
			currentPageNo = 1;
		} else if (currentPageNo > totalPageCount) {
			currentPageNo = totalPageCount;
		}
		
		meetingInfectedList = covidMeetingInfectedService.getPageList(queryUserName,queryaddressName,currentPageNo, pageSize);
		request.setAttribute("meetingInfectedList", meetingInfectedList);
		request.setAttribute("queryUserName", queryUserName);
		request.setAttribute("queryaddressName", queryaddressName);
		request.setAttribute("totalPageCount", totalPageCount);
		request.setAttribute("totalCount", totalCount);
		request.setAttribute("currentPageNo", currentPageNo);
		return "/meetingInfectedList";
	}
}
