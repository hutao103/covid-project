package com.htht.pro.controller;


import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.htht.pro.Constants;
import com.htht.pro.PageSupport;
import com.htht.pro.bean.SysUserBean;
import com.htht.pro.service.SysUserService;



@Controller
public class SysUserController {
	
	@Autowired
	private SysUserService sysUserService;

	@RequestMapping(value="/login")
	public String login(Model m){
		m.addAttribute("userName", "");
		m.addAttribute("password", "");
		m.addAttribute("error", "");
		return "/login";
	}
	
	@RequestMapping(value="/user/login")
	public String userLogin(@RequestParam(value="userName",defaultValue="")String userName,@RequestParam(value="password",defaultValue="")String password,Model m,HttpSession session){
		if (userName == null || "".equals(userName)){
			m.addAttribute("userName", userName);
			m.addAttribute("password", password);
			m.addAttribute("error", "Username can not empty");
			return "/login";
		}
		
		if (password == null || "".equals(password)){
			m.addAttribute("userName", userName);
			m.addAttribute("password", password);
			m.addAttribute("error", "Password can not empty");
			return "/login";
		}
		
		if(session.getAttribute(Constants.USER_SESSION) != null){
			return "/main";
		}
		SysUserBean su = sysUserService.findUserByUserName(userName, password);
		if(su != null){//LOGIN
			session.setAttribute(Constants.USER_SESSION, su);
			return "/main";
		}
		m.addAttribute("userName", userName);
		m.addAttribute("password", password);
		m.addAttribute("error", "Username OR Password Error");
		return "/login";
	}
	
	@RequestMapping(value="/logout",method = RequestMethod.GET)
	public String logout(Model m,HttpSession session){
		session.setAttribute(Constants.USER_SESSION, null);
		m.addAttribute("userName", "");
		m.addAttribute("password", "");
		m.addAttribute("error", "");
		return "/login";
	}
	
	@RequestMapping(value = "/user/query")
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
		
		List<SysUserBean> userList = null;
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
		int totalCount = sysUserService.getPageListCount(queryUserName);
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
		
		userList = sysUserService.getPageList(queryUserName,currentPageNo, pageSize);
		request.setAttribute("userList", userList);
		request.setAttribute("queryUserName", queryUserName);
		request.setAttribute("totalPageCount", totalPageCount);
		request.setAttribute("totalCount", totalCount);
		request.setAttribute("currentPageNo", currentPageNo);
		return "/userList";
	}
	
	@RequestMapping("/user/addUserShow")
	public String addUserShow(Model m,HttpServletRequest request) {
		if(request.getSession().getAttribute(Constants.USER_SESSION) == null){
			m.addAttribute("userName", "");
			m.addAttribute("password", "");
			m.addAttribute("error", "");
			return "/login";
		}
		return "/useradd";
	}
	
	@RequestMapping("/user/add")
	@ResponseBody
	public String add(@RequestParam(value="userName",defaultValue="")String userName,@RequestParam(value="password",defaultValue="")String password,@RequestParam(value="realName",defaultValue="")String realName,@RequestParam(value="sex",defaultValue="")String sex,@RequestParam(value="mobile",defaultValue="")String mobile,HttpServletRequest request) {
		SysUserBean ub = (SysUserBean) request.getSession().getAttribute(Constants.USER_SESSION);
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		List<SysUserBean> clist = sysUserService.findListByUserName(userName);
		if(clist != null && clist.size() > 0){
			resultMap.put("success", false);
			resultMap.put("message", "UserName EXIST!");
			return JSONArray.toJSONString(resultMap);
		}
		SysUserBean b = new SysUserBean();
		b.setUserName(userName);
		b.setPassword(password);
		b.setRealName(realName);
		b.setSex(sex);
		b.setMobile(mobile);
		b.setCreateUserId(ub.getUserId());
		int c = sysUserService.add(b);
		resultMap.put("success", c>0);
		return JSONArray.toJSONString(resultMap);
	}
	
	@RequestMapping("/user/updateShow")
	public String updateShow(@RequestParam(value="userId",defaultValue="")String userId,Model m,HttpServletRequest request) {
		
		if(request.getSession().getAttribute(Constants.USER_SESSION) == null){
			m.addAttribute("userName", "");
			m.addAttribute("password", "");
			m.addAttribute("error", "");
			return "/login";
		}
		
		SysUserBean ab = sysUserService.findListByUserId(userId);
		m.addAttribute("ab", ab);
		return "/userUpdate";
	}
	
	@RequestMapping("/user/update")
	@ResponseBody
	public String update(@RequestParam(value="userId",defaultValue="")String userId,@RequestParam(value="userName",defaultValue="")String userName,@RequestParam(value="password",defaultValue="")String password,@RequestParam(value="realName",defaultValue="")String realName,@RequestParam(value="sex",defaultValue="")String sex,@RequestParam(value="mobile",defaultValue="")String mobile,HttpServletRequest request) {
		//SysUserBean ub = (SysUserBean) request.getSession().getAttribute(Constants.USER_SESSION);
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		List<SysUserBean> clist = sysUserService.findListByUserName(userName);
		if(clist != null && clist.size() > 0 && !userId.equals(clist.get(0).getUserId())){
			resultMap.put("success", false);
			resultMap.put("message", "UserName EXIST!");
			return JSONArray.toJSONString(resultMap);
		}
		SysUserBean b = new SysUserBean();
		b.setUserId(userId);
		b.setUserName(userName);
		b.setPassword(password);
		b.setRealName(realName);
		b.setSex(sex);
		b.setMobile(mobile);
		int c = sysUserService.update(b);
		resultMap.put("success", c>0);
		return JSONArray.toJSONString(resultMap);
	}
	
	@RequestMapping("/user/deluser/{id}")
	@ResponseBody
	public String delUser(@PathVariable String id) {
		
		HashMap<String, String> resultMap = new HashMap<String, String>();
		if (id == null || "".equals(id)) {
			resultMap.put("delResult", "notexist");
		} else {
			// UserService userService = new UserServiceImpl();
			if (sysUserService.deleteUserById(id) >0) {
				resultMap.put("delResult", "true");
			} else {
				resultMap.put("delResult", "false");
			}
		}
		return JSONArray.toJSONString(resultMap);
	}
}
