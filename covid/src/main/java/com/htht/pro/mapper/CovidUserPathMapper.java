package com.htht.pro.mapper;

import com.htht.pro.bean.CovidUserPathBean;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CovidUserPathMapper {

    List<CovidUserPathBean> getPageList(@Param("userName")String userName,@Param("addressName")String addressName,@Param("isCarryVirus")String isCarryVirus,@Param("type")String type,@Param("currentPageNo")int currentPageNo, @Param("pageSize")int pageSize);
    
    int getPageListCount(@Param("userName")String userName,@Param("addressName")String addressName,@Param("isCarryVirus")String isCarryVirus,@Param("type")String type);
    
    List<CovidUserPathBean> getAppPageList(@Param("userId")String userId,@Param("currentPageNo")int currentPageNo, @Param("pageSize")int pageSize);
    
    int getAppPageListCount(@Param("userId")String userId);
    
    
    int add(@Param("covidUserPath")CovidUserPathBean bean);
}
