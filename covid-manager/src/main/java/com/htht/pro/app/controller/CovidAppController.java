package com.htht.pro.app.controller;


import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.htht.pro.Constants;
import com.htht.pro.Utils;
import com.htht.pro.bean.CovidAddressBean;
import com.htht.pro.bean.CovidMeetingInfectedBean;
import com.htht.pro.bean.CovidMonitorBean;
import com.htht.pro.bean.CovidUserBean;
import com.htht.pro.bean.CovidUserPathBean;
import com.htht.pro.bean.CovidVaccineBean;
import com.htht.pro.service.CovidAddressService;
import com.htht.pro.service.CovidMeetingInfectedService;
import com.htht.pro.service.CovidMonitorService;
import com.htht.pro.service.CovidUserPathService;
import com.htht.pro.service.CovidUserService;
import com.htht.pro.service.CovidVaccineService;



@Controller
@RequestMapping("/app")
public class CovidAppController {
	
	private static Logger logger = LoggerFactory.getLogger(CovidAppController.class);
	
	@Autowired
	private CovidUserService covidUserService;
	
	@Autowired
	private CovidAddressService covidAddressService;
	
	@Autowired
	private CovidMonitorService covidMonitorService;
	
	@Autowired
	private CovidVaccineService covidVaccineService;
	
	@Autowired
	private CovidMeetingInfectedService covidMeetingInfectedService;
	
	@Autowired
	private CovidUserPathService covidUserPathService;
	
	@RequestMapping(value="/user/login",method = RequestMethod.GET)
	@ResponseBody
	public String login(@RequestParam(value="nric")String nric,@RequestParam(value="password")String password){
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		CovidUserBean cu = covidUserService.findUserByNric(nric, password);
		
		if(cu != null){//LOGIN
			resultMap.put("codeid", 10000);
			resultMap.put("message", "SUCCESS");
			resultMap.put("retdata", cu);
			resultMap.put("token", cu.getUserId());
			resultMap.put("curtime", new Date().getTime());
			resultMap.put("userid", cu.getUserId());
			
			return JSONArray.toJSONString(resultMap);
		}
		resultMap.put("codeid", -1);
		resultMap.put("message", "Nric OR Password Error");
		resultMap.put("retdata", new CovidUserBean());
		resultMap.put("curtime", new Date().getTime());
		return JSONArray.toJSONString(resultMap);
	}
	
	@RequestMapping(value="/user/register",method = RequestMethod.GET)
	@ResponseBody
	public String register(@RequestParam(value="userName",defaultValue="")String userName,@RequestParam(value="password",defaultValue="")String password,@RequestParam(value="nric",defaultValue="")String nric){
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		List<CovidUserBean> ulist = covidUserService.findListByNric(nric);
		if(ulist != null && ulist.size() > 0){
			resultMap.put("codeid", -1);
			resultMap.put("message", "Nric EXISTS");
			resultMap.put("retdata", null);
			resultMap.put("curtime", new Date().getTime());
			return JSONArray.toJSONString(resultMap);
		}
		CovidUserBean b = new CovidUserBean();
		b.setUserName(userName);
		b.setPassword(password);
		b.setNric(nric);
		int c = covidUserService.add(b);
		if(c > 0){
			resultMap.put("codeid", 10000);
			resultMap.put("message", "SUCCESS");
			resultMap.put("retdata", new CovidUserBean());
			resultMap.put("curtime", new Date().getTime());
			return JSONArray.toJSONString(resultMap);
		}
		resultMap.put("codeid", -2);
		resultMap.put("message", "FAIL");
		resultMap.put("retdata", new CovidUserBean());
		resultMap.put("curtime", new Date().getTime());
		return JSONArray.toJSONString(resultMap);
	}
	
	@RequestMapping(value="/user/update",method = RequestMethod.GET)
	@ResponseBody
	public String update(@RequestParam(value="password",defaultValue="")String password,@RequestParam(value="nric",defaultValue="")String nric,HttpServletRequest request){
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		CovidUserBean ub = (CovidUserBean)request.getSession().getAttribute(Constants.APP_USER_SESSION);
		CovidUserBean b = covidUserService.findByUserId(ub.getUserId());
		b.setPassword(password);
		b.setNric(nric);
		int c = covidUserService.update(b);
		if(c > 0){
			resultMap.put("codeid", 10000);
			resultMap.put("message", "SUCCESS");
			resultMap.put("retdata", b);
			resultMap.put("curtime", new Date().getTime());
			return JSONArray.toJSONString(resultMap);
		}
		resultMap.put("codeid", -2);
		resultMap.put("message", "FAIL");
		resultMap.put("retdata", new CovidUserBean());
		resultMap.put("curtime", new Date().getTime());
		return JSONArray.toJSONString(resultMap);
	}
	
	@RequestMapping(value="/selectAddress",method = RequestMethod.POST)
	@ResponseBody
	public String selectAddress(){
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		List<CovidAddressBean> blist = covidAddressService.findList();
		resultMap.put("codeid", 10000);
		resultMap.put("message", "SUCCESS");
		resultMap.put("retdata", blist);
		resultMap.put("curtime", new Date().getTime());
		return JSONArray.toJSONString(resultMap);
	}
	
	@RequestMapping(value="/monitor/add",method = RequestMethod.GET)
	@ResponseBody
	public String monitorAdd(@RequestParam(value="addressId",defaultValue="")String addressId,@RequestParam(value="startTime",defaultValue="")String startTime,HttpServletRequest request){
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		CovidMonitorBean b = new CovidMonitorBean();
		String userId = getUserId(request);
		CovidUserBean ub = covidUserService.findByUserId(userId);
		b.setAddressId(addressId);
		b.setUserId(ub.getUserId());
		b.setStartTime(startTime);
		b.setCreateUserId(ub.getUserId());
		b.setUpdateUserId(ub.getUserId());
		int c = covidMonitorService.add(b);
		if(c > 0){
			resultMap.put("codeid", 10000);
			resultMap.put("message", "SUCCESS");
			resultMap.put("retdata", new CovidMonitorBean());
			resultMap.put("curtime", new Date().getTime());
			return JSONArray.toJSONString(resultMap);
		}
		resultMap.put("codeid", -2);
		resultMap.put("message", "FAIL");
		resultMap.put("retdata", new CovidMonitorBean());
		resultMap.put("curtime", new Date().getTime());
		return JSONArray.toJSONString(resultMap);
	}
	
	@RequestMapping(value="/vaccine/add",method = RequestMethod.GET)
	@ResponseBody
	public String vaccineAdd(@RequestParam(value="addressId",defaultValue="")String addressId,@RequestParam(value="startTime",defaultValue="")String startTime,HttpServletRequest request){
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		CovidVaccineBean b = new CovidVaccineBean();
		String userId = getUserId(request);
		CovidUserBean ub = covidUserService.findByUserId(userId);
		b.setAddressId(addressId);
		b.setStartTime(startTime);
		b.setUserId(ub.getUserId());
		b.setCreateUserId(ub.getUserId());
		b.setUpdateUserId(ub.getUserId());
		int c = covidVaccineService.add(b);
		if(c > 0){
			resultMap.put("codeid", 10000);
			resultMap.put("message", "SUCCESS");
			resultMap.put("retdata", new CovidVaccineBean());
			resultMap.put("curtime", new Date().getTime());
			return JSONArray.toJSONString(resultMap);
		}
		resultMap.put("codeid", -2);
		resultMap.put("message", "FAIL");
		resultMap.put("retdata", new CovidVaccineBean());
		resultMap.put("curtime", new Date().getTime());
		return JSONArray.toJSONString(resultMap);
	}
	
	@RequestMapping(value="/meetingInfected/add",method = RequestMethod.GET)
	@ResponseBody
	public String meetingInfectedAdd(@RequestParam(value="addressName",defaultValue="")String addressName,@RequestParam(value="startTime",defaultValue="")String startTime,HttpServletRequest request){
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		CovidMeetingInfectedBean b = new CovidMeetingInfectedBean();
		String userId = getUserId(request);
		CovidUserBean ub = covidUserService.findByUserId(userId);
		b.setAddressName(addressName);
		b.setUserId(ub.getUserId());
		b.setStartTime(startTime);
		b.setCreateUserId(ub.getUserId());
		b.setUpdateUserId(ub.getUserId());
		int c = covidMeetingInfectedService.add(b);
		if(c > 0){
			resultMap.put("codeid", 10000);
			resultMap.put("message", "SUCCESS");
			resultMap.put("retdata", new CovidMeetingInfectedBean());
			resultMap.put("curtime", new Date().getTime());
			return JSONArray.toJSONString(resultMap);
		}
		resultMap.put("codeid", -2);
		resultMap.put("message", "FAIL");
		resultMap.put("retdata", new CovidMeetingInfectedBean());
		resultMap.put("curtime", new Date().getTime());
		return JSONArray.toJSONString(resultMap);
	}
	
	@RequestMapping(value="/userPath/add",method = RequestMethod.GET)
	@ResponseBody
	public String userPathAdd(@RequestParam(value="addressName",defaultValue="")String addressName,@RequestParam(value="isCarryVirus",defaultValue="")String isCarryVirus,@RequestParam(value="type",defaultValue="")String type,@RequestParam(value="startTime",defaultValue="")String startTime,HttpServletRequest request){
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		CovidUserPathBean b = new CovidUserPathBean();
		String userId = getUserId(request);
		CovidUserBean ub = covidUserService.findByUserId(userId);
		b.setAddressName(addressName);
		b.setUserId(ub.getUserId());
		b.setIsCarryVirus(isCarryVirus);
		b.setType(type);
		b.setStartTime(startTime);
		b.setCreateUserId(ub.getUserId());
		b.setUpdateUserId(ub.getUserId());
		int c = covidUserPathService.add(b);
		if(c > 0){
			
			if("1".equals(isCarryVirus)){//carryVirus   YES
				//add meetingInfected
				CovidMeetingInfectedBean b2 = new CovidMeetingInfectedBean();
				b2.setUserId(ub.getUserId());
				b2.setAddressName(addressName);
				b2.setStartTime(startTime);
				b2.setCreateUserId(ub.getUserId());
				b2.setUpdateUserId(ub.getUserId());
				covidMeetingInfectedService.add(b2);
			}
			
			resultMap.put("codeid", 10000);
			resultMap.put("message", "SUCCESS");
			resultMap.put("retdata", new CovidUserPathBean());
			resultMap.put("curtime", new Date().getTime());
			resultMap.put("userid", ub.getUserId());
			return JSONArray.toJSONString(resultMap);
		}
		resultMap.put("codeid", -2);
		resultMap.put("message", "FAIL");
		resultMap.put("retdata", new CovidUserPathBean());
		resultMap.put("curtime", new Date().getTime());
		resultMap.put("userid", ub.getUserId());
		return JSONArray.toJSONString(resultMap);
	}
	
	@RequestMapping(value="/vaccine/getData",method = RequestMethod.GET)
	@ResponseBody
	public String vaccineGetData(HttpServletRequest request){
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		String userId = getUserId(request);
		CovidUserBean ub = covidUserService.findByUserId(userId);
		List<CovidVaccineBean> list = covidVaccineService.getAppPageList(ub.getUserId(), 1, 1);
		resultMap.put("codeid", 10000);
		resultMap.put("message", "SUCCESS");
		if(list != null && list.size() >0){
			resultMap.put("retdata", list.get(0));
		}else{
			resultMap.put("retdata", new CovidVaccineBean());
		}
		resultMap.put("curtime", new Date().getTime());
		return JSONArray.toJSONString(resultMap);
	}
	
	@RequestMapping(value="/meetingInfected/getData",method = RequestMethod.GET)
	@ResponseBody
	public String meetingInfectedGetData(HttpServletRequest request){
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		String userId = getUserId(request);
		CovidUserBean ub = covidUserService.findByUserId(userId);
		List<CovidMeetingInfectedBean> list = covidMeetingInfectedService.getAppPageList(ub.getUserId(), 1,1);
		resultMap.put("codeid", 10000);
		resultMap.put("message", "SUCCESS");
		if(list != null && list.size() >0){
			
			resultMap.put("retdata", list.get(0));
		}else{
			resultMap.put("retdata", new CovidMeetingInfectedBean());
		}
		resultMap.put("curtime", new Date().getTime());
		return JSONArray.toJSONString(resultMap);
	}
	
	
	@RequestMapping(value="/monitor/getData",method = RequestMethod.GET)
	@ResponseBody
	public String monitorGetData(HttpServletRequest request){
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		String userId = getUserId(request);
		CovidUserBean ub = covidUserService.findByUserId(userId);
		List<CovidMonitorBean> list = covidMonitorService.getAppPageList(ub.getUserId(), 1, 1);
		resultMap.put("codeid", 10000);
		resultMap.put("message", "SUCCESS");
		if(list != null && list.size() >0){
			
			//delete monitor data
			covidMonitorService.deleteByUserId(ub.getUserId());
			
			//update covidUser isInfect
			String isInfect = Utils.getRandomVirus();
			CovidUserBean cb = covidUserService.findByUserId(ub.getUserId());
			cb.setIsInfect(isInfect);
			covidUserService.update(cb);
			
			resultMap.put("retdata", isInfect);
		}else{
			resultMap.put("retdata", -1);
		}
		resultMap.put("curtime", new Date().getTime());
		return JSONArray.toJSONString(resultMap);
	}
	
	@RequestMapping(value="/userPath/pageList",method = RequestMethod.GET)
	@ResponseBody
	public String userPathPageList(@RequestParam(value="page",defaultValue="")String page,@RequestParam(value="rows",defaultValue="10")String rows,HttpServletRequest request){
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		String userId = getUserId(request);
		CovidUserBean ub = covidUserService.findByUserId(userId);
		List<CovidUserPathBean> list = covidUserPathService.getAppPageList(ub.getUserId(), Integer.parseInt(page), Integer.parseInt(rows));
		int c = covidUserPathService.getAppPageListCount(ub.getUserId());
		resultMap.put("codeid", 10000);
		resultMap.put("message", "SUCCESS");
		resultMap.put("retdata", list);
		resultMap.put("curtime", new Date().getTime());
		resultMap.put("totalCount", c);
		if(c == 0){
			resultMap.put("totalPage", 0);
		}else{
			if(c%Integer.parseInt(rows) == 0){
				resultMap.put("totalPage", c/Integer.parseInt(rows));
			}else{
				resultMap.put("totalPage", c/Integer.parseInt(rows)+1);
			}
		}
		return JSONArray.toJSONString(resultMap);
	}

	
	@RequestMapping(value="/monitor/getNewData",method = RequestMethod.GET)
	@ResponseBody
	public String monitorGetNewData(HttpServletRequest request){
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		String userId = getUserId(request);
		CovidUserBean ub = covidUserService.findByUserId(userId);
		List<CovidMonitorBean> list = covidMonitorService.getAppPageList(ub.getUserId(), 1, 1);
		resultMap.put("codeid", 10000);
		resultMap.put("message", "SUCCESS");
		if(list != null && list.size() >0){
			
			resultMap.put("retdata", list.get(0));
		}else{
			resultMap.put("retdata", new CovidMonitorBean());
		}
		resultMap.put("curtime", new Date().getTime());
		return JSONArray.toJSONString(resultMap);
	}
	
	private static String getUserId(HttpServletRequest request){
		Enumeration<String> headerNames = request.getHeaderNames();
		String userId = "";
		while (headerNames.hasMoreElements()) {
			String key = (String) headerNames.nextElement();
			if(Constants.AUTHORIZATION.equals(key)){
				userId = request.getHeader(key);
			}
			logger.info("KEY:"+key);
			logger.info("VALUE:"+request.getHeader(key));
		}

		return userId;
	}
}
