package com.program.email.mapper;

import com.program.email.entity.EmailValidate;
import com.program.email.entity.EmailValidateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EmailValidateMapper {
    long countByExample(EmailValidateExample example);

    int deleteByPrimaryKey(Long id);

    int insertSelective(EmailValidate record);

    List<EmailValidate> selectByExample(EmailValidateExample example);

    EmailValidate selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") EmailValidate record, @Param("example") EmailValidateExample example);

    int updateByExample(@Param("record") EmailValidate record, @Param("example") EmailValidateExample example);

    int updateByPrimaryKeySelective(EmailValidate record);

    int updateByPrimaryKey(EmailValidate record);
}