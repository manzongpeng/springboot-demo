package com.program.sms.mapper;

import com.program.sms.entity.SmsSendRecord;
import com.program.sms.entity.SmsSendRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SmsSendRecordMapper {
    long countByExample(SmsSendRecordExample example);

    int deleteByPrimaryKey(Long id);

    int insertSelective(SmsSendRecord record);

    List<SmsSendRecord> selectByExample(SmsSendRecordExample example);

    SmsSendRecord selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SmsSendRecord record, @Param("example") SmsSendRecordExample example);

    int updateByExample(@Param("record") SmsSendRecord record, @Param("example") SmsSendRecordExample example);

    int updateByPrimaryKeySelective(SmsSendRecord record);

    int updateByPrimaryKey(SmsSendRecord record);
}