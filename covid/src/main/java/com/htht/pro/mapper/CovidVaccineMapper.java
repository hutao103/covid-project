package com.htht.pro.mapper;

import com.htht.pro.bean.CovidVaccineBean;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CovidVaccineMapper {

    List<CovidVaccineBean> getPageList(@Param("userName")String userName,@Param("addressId")String addressId,@Param("currentPageNo")int currentPageNo, @Param("pageSize")int pageSize);
    
    int getPageListCount(@Param("userName")String userName,@Param("addressId")String addressId);
    
    List<CovidVaccineBean> getAppPageList(@Param("userId")String userId,@Param("currentPageNo")int currentPageNo, @Param("pageSize")int pageSize);
    
    int getAppPageListCount(@Param("userId")String userId);
    
    int add(@Param("covidVaccine")CovidVaccineBean bean);
}
