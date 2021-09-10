package com.htht.pro.mapper;

import com.htht.pro.bean.CovidMonitorBean;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CovidMonitorMapper {

    List<CovidMonitorBean> getPageList(@Param("userName")String userName,@Param("addressId")String addressId,@Param("currentPageNo")int currentPageNo, @Param("pageSize")int pageSize);
    
    int getPageListCount(@Param("userName")String userName,@Param("addressId")String addressId);
    
    List<CovidMonitorBean> getAppPageList(@Param("userId")String userId,@Param("currentPageNo")int currentPageNo, @Param("pageSize")int pageSize);
    
    int getAppPageListCount(@Param("userId")String userId);
    
    
    int add(@Param("covidMonitor")CovidMonitorBean bean);
    
    int deleteByUserId(@Param("userId")String userId);
}
