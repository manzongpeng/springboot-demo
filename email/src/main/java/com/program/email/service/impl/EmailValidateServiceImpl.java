package com.program.email.service.impl;

import com.program.email.entity.EmailValidate;
import com.program.email.entity.EmailValidateExample;
import com.program.email.entity.EmailValidateExample.Criteria;
import com.program.email.entity.User;
import com.program.email.mapper.EmailValidateMapper;
import com.program.email.service.EmailValidateService;
import com.program.email.utils.GenerateIdUtil;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service("EmailValidateServiceImpl")
public class EmailValidateServiceImpl implements EmailValidateService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private EmailValidateMapper validateMapper;

    /**
     * 发送邮件：@Async进行异步调用发送邮件接口
     */
    @Override
    @Async
    public void sendPasswordResetEmail(SimpleMailMessage email) {
        javaMailSender.send(email);
    }

    /**
     * 在email_validate表中插入一条validate记录，userid，email属性来自MUL_TENANT表，token由UUID生成
     */
    @Override
    public int insertNewResetRecord(User user, String token, String type) {
        EmailValidate emailValidate = new EmailValidate();
        emailValidate.setId(GenerateIdUtil.nextId());
        emailValidate.setUserId(user.getId());
        emailValidate.setEmail(user.getEmail());
        emailValidate.setResetToken(token);
        emailValidate.setType(type);
        int insertSelective = validateMapper.insertSelective(emailValidate);
        return insertSelective;

    }

    /**
     * email_validate表中，通过token查找重置申请记录
     */
    @Override
    public List<EmailValidate> findUserByResetToken(String token) {
        EmailValidateExample emailValidateExample = new EmailValidateExample();
        emailValidateExample.createCriteria().andResetTokenEqualTo(token);
        return validateMapper.selectByExample(emailValidateExample);
    }

    /**
     * 验证是否发送重置邮件：每个email的重置密码每日请求上限为requestPerDay次，与上一次的请求时间间隔为interval分钟。
     */
    @Override
    public boolean sendValidateLimitation(String email, long requestPerDay, long interval) {
        EmailValidateExample emailValidateEntityExample = new EmailValidateExample();
        Criteria criteria = emailValidateEntityExample.createCriteria();
        criteria.andEmailEqualTo(email);
        List<EmailValidate> validates =
            validateMapper.selectByExample(emailValidateEntityExample);
        // 若查无记录，意味着第一次申请，直接放行
        if (validates.isEmpty()) {
            return true;
        }
        // 有记录，则判定是否频繁申请以及是否达到日均请求上线
        long countTodayValidation = validates.stream()
            .filter(x -> DateUtils.isSameDay(x.getModifyTime(), new Date())).count();
        Optional validate = validates.stream().map(EmailValidate::getModifyTime)
            .max(Date::compareTo);
        Date dateOfLastRequest = new Date();
        if (validate.isPresent()) {
            dateOfLastRequest = (Date) validate.get();
        }
        long intervalForLastRequest = new Date().getTime() - dateOfLastRequest.getTime();

        return countTodayValidation <= requestPerDay
            && intervalForLastRequest >= interval * 60 * 1000;
    }

    /**
     * 验证连接是否失效：链接有两种情况失效 1.超时 2.最近请求的一次链接自动覆盖之前的链接（待看代码）
     */
    @Override
    public boolean validateLimitation(String email, long requestPerDay, long interval,
        String token) {
        EmailValidateExample emailValidateExample = new EmailValidateExample();
        EmailValidateExample.Criteria criteria = emailValidateExample.createCriteria();
        criteria.andEmailEqualTo(email);
        List<EmailValidate> validates = validateMapper.selectByExample(emailValidateExample);
        // 有记录才会调用该函数，只需判断是否超时
        Optional validate = validates.stream().map(EmailValidate::getModifyTime)
            .max(Date::compareTo);
        Date dateOfLastRequest = new Date();
        if (validate.isPresent()) {
            dateOfLastRequest = (Date) validate.get();
        }
        long intervalForLastRequest = new Date().getTime() - dateOfLastRequest.getTime();

        Optional lastRequestToken = validates.stream().filter(x -> x.getResetToken().equals(token))
            .map(EmailValidate::getModifyTime).findAny();
        Date dateOfLastRequestToken = new Date();
        if (lastRequestToken.isPresent()) {
            dateOfLastRequestToken = (Date) lastRequestToken.get();
        }
        return intervalForLastRequest <= interval * 60 * 1000
            && dateOfLastRequest == dateOfLastRequestToken;
    }
}