package com.dongdongshop.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.dysmsapi20170525.models.SendSmsResponseBody;
import com.aliyun.tea.TeaException;
import com.aliyun.teautil.models.RuntimeOptions;
import com.dongdongshop.service.RegisterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RegisterServiceImpl implements RegisterService {

    @Override
    public String getMessageCode(String phone) {
        Client client = null;
        try {
            client = createClient("LTAI4GCbdwnjCGwQj9Z3tr3N", "27ih4pc1W1tqey9X4mmCy1JUotAX7L");
        } catch (Exception e) {
            e.printStackTrace();
        }
        String code = RandomUtil.randomNumbers(4);
        SendSmsRequest sendSmsRequest = new com.aliyun.dysmsapi20170525.models.SendSmsRequest()
                .setSignName("东科创想")
                .setTemplateCode("SMS_162522027")
                .setPhoneNumbers(phone)
                .setTemplateParam("{\"sms_code\":\""+code+"\"}");
        RuntimeOptions runtime = new com.aliyun.teautil.models.RuntimeOptions();
        try {
            // 复制代码运行请自行打印 API 的返回值
            SendSmsResponse sendSmsResponse = client.sendSmsWithOptions(sendSmsRequest, runtime);
            SendSmsResponseBody body = sendSmsResponse.getBody();
//            log.info("------短信发送体内容:{}",body);
//            log.info("验证码发送成功:{}",code);
        } catch (TeaException error) {
            // 如有需要，请打印 error
            com.aliyun.teautil.Common.assertAsString(error.message);
        } catch (Exception _error) {
            TeaException error = new TeaException(_error.getMessage(), _error);
            // 如有需要，请打印 error
            com.aliyun.teautil.Common.assertAsString(error.message);
        }
        return code;
    }


    public com.aliyun.dysmsapi20170525.Client createClient(String accessKeyId, String accessKeySecret) throws Exception {
        com.aliyun.teaopenapi.models.Config config = new com.aliyun.teaopenapi.models.Config()
                // 您的 AccessKey ID
                .setAccessKeyId(accessKeyId)
                // 您的 AccessKey Secret
                .setAccessKeySecret(accessKeySecret);
        // 访问的域名
        config.endpoint = "dysmsapi.aliyuncs.com";
        return new com.aliyun.dysmsapi20170525.Client(config);
    }
}
