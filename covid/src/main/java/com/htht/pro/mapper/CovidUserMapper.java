package com.htht.pro.mapper;

import com.htht.pro.bean.CovidUserBean;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CovidUserMapper {

    List<CovidUserBean> getPageList(@Param("userName")String userName,@Param("currentPageNo")int currentPageNo, @Param("pageSize")int pageSize);
    
    int getPageListCount(@Param("userName")String userName);
    
    int add(@Param("covidUser")CovidUserBean bean);
    
    List<CovidUserBean> findListByNric(@Param("nric")String nric);
    
    CovidUserBean findUserByNric(@Param("nric")String nric,@Param("password")String password);
    
    int update(@Param("covidUser")CovidUserBean bean);
    
    CovidUserBean findByUserId(@Param("userId")String userId);
}
