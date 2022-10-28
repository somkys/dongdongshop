package com.dongdongshop.config;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {

//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2016103100780474";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCUOmEc2NVMe/7NKZiqRlZNzxA6pZMu0GYK2TGls8lNct7GDnuA0QJoKGL/3H2cPcOf2jQ0V/PnthNus/rFhLFktr99QjAOSODVhSUtuZDdjkCpv+B1RIXcEJtPTLYsqwkr4CquCffvAQnN4MHOs3PzNA0Rx2B1TXwQgsZsLjG9cdfqoPUUFediPlypYZR8tIyYjKw6nNnuBWZOBdH5n0Tvl+e0rylkBk2QQ1Jor+dUpoUoatExP52HDY41GCC61uQcx7kZTymZLr+7K8XefnsM2PCY2tLK4h+3MdZhibXgtNOuS85wv3HmVV+SN0eR8S4cGpH157JWmv115S4SfW6nAgMBAAECggEBAIdhnqrrPD2EDp+xuzbh0Rd15Fu8OGaMfnawEtFGGLi9Ds0UEyb/PtSE9o68Apm+K7sHX6DjJ+jc+nikt4jJff//jXvJBP03AOrjlaZikvZYY6TmlApnEF+DZ2aRKN45RMInFrQodKN9bkf0XO7+Jgvd4YX4dJgOsh6eYr4Rnupn76y/ztF5o1e90MrVl1QmdvSURRAkRPFUDRT9TizPrNB6+gThDU6JnBl+RwDb754R33/v4bCkHMhsbN254RHMncs+v7361HRFFzxjGlnCRnP5P62XiJpaRx7ACeYIDjirSYBfC605nF0A9IXPitia9ItdCzTASaj5zWIol4YuDjkCgYEA9XBwzfBT9Sc/lI0Fu44Wh9ajyag2OSkVStpBFvse7Nj0MHq/JyQObUO/eNHK6TsC0e/R0NKyqTmlB7yhiVqZCWWnAU90WlylgcrxrQdyM/H+TwmXebRzYsq32szwNOj+uQjsTkSi1NY+GjJ/8YqfVxCfftgwVaTyWIwMqQ9atTMCgYEAmpsjpRm9Pyk2HQiaMduQklns6lY+1NrHpXmQ9VaseW4forcR0QrF5flNhwYpWyxTxY1x1M6qQNWhMltebYy0h8iJXTs+okPN70jujTxAvbfgPVLTa/dXHoALaiH5x3J592f3w+ZGEQ2NqSiG+7wiIqH47oHNHMii6EqRK4qTuL0CgYAXn67743nxSsnP3pMCrnZ0tLAnJM2zSsUF42QIdjtPyMIYOHhVqB7LXY38bxT8xfVLh63GT8FqkGXWDM3vjozUXrhoY+FrISe9Cm+gm+reHRjBOscdq8S/aYuwv7EPJq392WIFVo3ltolbtCikcxdLJgp0BxL/61R4OWKukw/ECQKBgDSM7vy7VvwVAfO0bNTtkKJuNn0ge5LWGm5Ad+ksYMuVhuND3qZzRvj4WEXA7mWzF3ZasYpoBK6s7nGzZNFPtj1BCI/MB2oYKfNfktYm2MqjqXCuqnZUSCRx2W9lxQTqAFL739eSedZqCPCqedcvB1tGOtq2/AUAnX7feaCXJcbxAoGBAKdIH24ehkMiJBzrwUECae/2vMLH4YmhxrCnoXQSFvefbici12twrYdE4Q65HWTn5uMRrSKRpjEXouehkoQ4kIqJ6yZtbEKe52XpgJZGyUjYsAoZ780eP/hW+cdUOzI+OaRuMSyOMwSR+QFCj4NSLaaL5ElOYF33XvMIr3+p/XNq";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjewhkuipcoKtSMpu03VukojURD5+UzPTxhdLrGpFWEc68N94VW/9hfXuBL52D+oxNrWSCedeUZjOdksimtnDHyiRKHln0LFkSnupMgSTdYdB6x1OzGH+3VkgUuLVI6JD0L55ns9stTLbPgWDgUbk4GVL4pXyl+6sVX2H2X67wkmK0S0+XBNXFoK8NaRKMTyePAhSLUIQGPLxZhwnMt+pdaEkPgh94kJG/4JB8O3kQmfbsi9edr43mlJ+6w4pZ4KKDlwC5EPBQPDgk2/GCezApv//vPwxqd538oimT9OmNWNHWO1Ei0ew6ns6MXll5c0+GbsgE7Z/AhzEVFP3V+og6wIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = " http://qm2z7p.natappfree.cc/notifyUrl";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = " http://qm2z7p.natappfree.cc/returnUrl";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

