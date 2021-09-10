package com.htht.pro.mapper;

import com.htht.pro.bean.CovidAddressBean;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CovidAddressMapper {

    List<CovidAddressBean> getPageList(@Param("name")String name,@Param("currentPageNo")int currentPageNo, @Param("pageSize")int pageSize);
    
    int getPageListCount(@Param("name")String name);
    
    List<CovidAddressBean> findList();
    
    List<CovidAddressBean> findListByName(@Param("name")String name);
    
    CovidAddressBean findListByAddressId(@Param("addressId")String addressId);
    
    int add(@Param("covidAddress")CovidAddressBean bean);
    
    int update(@Param("covidAddress")CovidAddressBean bean);
    
    int deleteByAddressId(@Param("addressId")String addressId);
}
