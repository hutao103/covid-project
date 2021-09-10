package com.htht.pro.mapper;

import com.htht.pro.bean.CovidMeetingInfectedBean;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CovidMeetingInfectedMapper {

    List<CovidMeetingInfectedBean> getPageList(@Param("userName")String userName,@Param("addressName")String addressName,@Param("currentPageNo")int currentPageNo, @Param("pageSize")int pageSize);
    
    int getPageListCount(@Param("userName")String userName,@Param("addressName")String addressName);
    
    List<CovidMeetingInfectedBean> getAppPageList(@Param("userId")String userId,@Param("currentPageNo")int currentPageNo, @Param("pageSize")int pageSize);
    
    int getAppPageListCount(@Param("userId")String userId);
    
    
    int add(@Param("covidMeetingInfected")CovidMeetingInfectedBean bean);
}
