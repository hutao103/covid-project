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
import com.htht.pro.bean.CovidUserBean;
import com.htht.pro.service.CovidUserService;



@Controller
@RequestMapping("/covid/user")
public class CovidUserController {
	
	@Autowired
	private CovidUserService covidUserService;
	
	@RequestMapping(value = "/query")
	public String query(
			@RequestParam(value = "queryname", required = false) String queryUserName,
			@RequestParam(value = "pageIndex", required = false) String pageIndex,
			HttpServletRequest request,Model m) {
		if(request.getSession().getAttribute(Constants.USER_SESSION) == null){
			m.addAttribute("userName", "");
			m.addAttribute("password", "");
			m.addAttribute("error", "");
			return "/login";
		}
		List<CovidUserBean> userList = null;
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
		int totalCount = covidUserService.getPageListCount(queryUserName);
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
		
		userList = covidUserService.getPageList(queryUserName,currentPageNo, pageSize);
		request.setAttribute("userList", userList);
		request.setAttribute("queryUserName", queryUserName);
		request.setAttribute("totalPageCount", totalPageCount);
		request.setAttribute("totalCount", totalCount);
		request.setAttribute("currentPageNo", currentPageNo);
		return "/covidUserList";
	}
}
