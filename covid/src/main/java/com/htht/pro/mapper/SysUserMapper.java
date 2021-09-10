package com.htht.pro.mapper;

import com.htht.pro.bean.SysUserBean;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SysUserMapper {

    SysUserBean findUserByUserName(@Param("userName")String userName,@Param("password")String password);
    
    List<SysUserBean> getPageList(@Param("userName")String userName,@Param("currentPageNo")int currentPageNo, @Param("pageSize")int pageSize);
    
    int getPageListCount(@Param("userName")String userName);
    
    int add(@Param("sysUser")SysUserBean user);
    
    int deleteUserById(@Param("userId")String userId);
    
    List<SysUserBean> findListByUserName(@Param("userName")String userName);
    
    SysUserBean findListByUserId(@Param("userId")String userId);
    
    int update(@Param("sysUser")SysUserBean bean);
    
}
