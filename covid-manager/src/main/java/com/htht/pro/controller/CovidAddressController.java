package com.htht.pro.controller;


import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.htht.pro.Constants;
import com.htht.pro.PageSupport;
import com.htht.pro.bean.CovidAddressBean;
import com.htht.pro.bean.SysUserBean;
import com.htht.pro.service.CovidAddressService;



@Controller
@RequestMapping("/address")
public class CovidAddressController {
	
	@Autowired
	private CovidAddressService covidAddressService;
	
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
		
		List<CovidAddressBean> addressList = null;
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
		int totalCount = covidAddressService.getPageListCount(queryUserName);
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
		
		addressList = covidAddressService.getPageList(queryUserName,currentPageNo, pageSize);
		request.setAttribute("addressList", addressList);
		request.setAttribute("queryUserName", queryUserName);
		request.setAttribute("totalPageCount", totalPageCount);
		request.setAttribute("totalCount", totalCount);
		request.setAttribute("currentPageNo", currentPageNo);
		return "/addressList";
	}
	
	@RequestMapping("/addShow")
	public String addShow(Model m,HttpServletRequest request) {
		if(request.getSession().getAttribute(Constants.USER_SESSION) == null){
			m.addAttribute("userName", "");
			m.addAttribute("password", "");
			m.addAttribute("error", "");
			return "/login";
		}
		return "/addressAdd";
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public String add(@RequestParam(value="name",defaultValue="")String name,@RequestParam(value="lnglat",defaultValue="")String lnglat,HttpServletRequest request) {
		SysUserBean ub = (SysUserBean) request.getSession().getAttribute(Constants.USER_SESSION);
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		List<CovidAddressBean> clist = covidAddressService.findListByName(name);
		if(clist != null && clist.size() > 0){
			resultMap.put("success", false);
			resultMap.put("message", "NAME EXIST!");
			return JSONArray.toJSONString(resultMap);
		}
		CovidAddressBean b = new CovidAddressBean();
		b.setName(name);
		b.setLnglat(lnglat);
		b.setCreateUserId(ub.getUserId());
		b.setUpdateUserId(ub.getUserId());
		int c = covidAddressService.add(b);
		resultMap.put("success", c>0);
		return JSONArray.toJSONString(resultMap);
	}
	
	@RequestMapping("/updateShow")
	public String updateShow(@RequestParam(value="addressId",defaultValue="")String addressId,Model m) {
		CovidAddressBean ab = covidAddressService.findListByAddressId(addressId);
		m.addAttribute("ab", ab);
		return "/addressUpdate";
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public String update(@RequestParam(value="addressId",defaultValue="")String addressId,@RequestParam(value="name",defaultValue="")String name,@RequestParam(value="lnglat",defaultValue="")String lnglat,HttpServletRequest request) {
		SysUserBean ub = (SysUserBean) request.getSession().getAttribute(Constants.USER_SESSION);
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		CovidAddressBean b = new CovidAddressBean();
		b.setAddressId(addressId);
		b.setName(name);
		b.setLnglat(lnglat);
		b.setUpdateUserId(ub.getUserId());
		int c = covidAddressService.update(b);
		resultMap.put("success", c>0);
		return JSONArray.toJSONString(resultMap);
	}
	
	@RequestMapping("/delete/{id}")
	@ResponseBody
	public String delUser(@PathVariable String id) {
		
		HashMap<String, String> resultMap = new HashMap<String, String>();
		if (id == null || "".equals(id)) {
			resultMap.put("delResult", "notexist");
		} else {
			if (covidAddressService.deleteByAddressId(id) >0) {
				resultMap.put("delResult", "true");
			} else {
				resultMap.put("delResult", "false");
			}
		}
		return JSONArray.toJSONString(resultMap);
	}
}
