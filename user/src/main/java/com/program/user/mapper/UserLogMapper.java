package com.program.user.mapper;

import com.program.user.entity.UserLog;
import com.program.user.entity.UserLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserLogMapper {
    long countByExample(UserLogExample example);

    int deleteByPrimaryKey(Long id);

    int insertSelective(UserLog record);

    List<UserLog> selectByExample(UserLogExample example);

    UserLog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserLog record, @Param("example") UserLogExample example);

    int updateByExample(@Param("record") UserLog record, @Param("example") UserLogExample example);

    int updateByPrimaryKeySelective(UserLog record);

    int updateByPrimaryKey(UserLog record);
}