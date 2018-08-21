# 结合eSDK 客户端源码理解SSL协议

一. 背景

- HTTPS(HTTP secure)的目的是为了确保HTTP的安全。
- 考虑一下三个network threats:
  1. C/S的流量被窃听 (报文机密性：非对称加密，使用CA来解决公钥分发的安全性)
  2. C/S的流量被篡改 （报文完整性：校验码，不易改动内容，MD5，SHA-1哈希算法）
  3. 冒充web服务器 （数字签名，MAC）
     - 其中1属于Encryption的问题，2,3属于Identification的问题
- HTTPS is an add-on
  - Means HTTP over SSL/TLS
  - SSL (安全套接字层) 先于TLS(Transport Layer Security),TLS是
  - TLS，注意到是Transport layer，和HTTP不直接相关，上层完全可以是SMTP等其他应用层协议（用来加密邮件）
  - motivated  by secure web commerce
        HTTP
        -------
        SSL/TLS   <- Insert
        -------
        TCP       <- 并不知道info被加密了
        -------
        IP
- SSL认证
  - client通过public key authentication来认证unknown Server

二. 登录IVS过程

    	@Override
        public Integer login(String userName, String pwd)
        {
            LOGGER.info("begin to execute getPublicKey method");
            SDKResponse<String> key = keyServiceEx.getPublicKey("RSA2048");
            if (null == key || 0 != key.getResultCode())
            {
                LOGGER.info("failed to get publicKey");
                return PlatformNativeConstant.FAILED_TO_GET_PUTLIC_KEY;
            }
            LOGGER.info("execute getPublicKey method completed");
            
            Integer setSecretKeyResultCode = setSecretKey();
            if (null == setSecretKeyResultCode || 0 != setSecretKeyResultCode)
            {
                return PlatformNativeConstant.FAILED_TO_SET_SECRET_KEY;
            }
            
            LOGGER.info("begin to execute login method");
            
            if (StringUtils.isNullOrEmpty(pwd))
            {
                LOGGER.info("execute login method completed");
                return NativeConstant.SDK_PARAM_NOT_COMPLETE_ERRORCODE;
            }
            
            String encodePwd = null;
            try
            {
                // 加密方式向下兼容
                encodePwd = Encrypt.getEncryptPwd(pwd);
                
                if (StringUtils.isNullOrEmpty(encodePwd))
                {
                    return PlatformNativeConstant.SDK_TP_PASSWORD_ENCODE_ERRORCODE;
                }
            }
            catch (Exception e1)
            {
                LOGGER.debug("encode password error");
                return PlatformNativeConstant.SDK_TP_PASSWORD_ENCODE_ERRORCODE;
            }
            
            try
            {
                Integer errorCode = ivsProfessionalCommon.login(userName, encodePwd);
                
                LOGGER.info("execute login method completed");
                
                return errorCode;
            }
            catch (Exception e)
            {
                LOGGER.error("login method exception happened", e);
                return ExceptionUtils.processSoapException(e);
            }
            
        }

假设我们的eSDK 客户端想要安全地将用户的密码告诉eSDK Server，他的做法过程如下：

1. 验证eSDK Server是真实的eSDK Server
   - 客户端向server发送一个hello报文
   - Server通过证书进行响应，证书中包含了Server的公钥
   - 代码中，这个过程是通过调用PlatformKeyServiceEx接口的getPublicKey方法来实现的
          SDKResponse<String> key = keyServiceEx.getPublicKey("RSA2048");
2. 利用Server的公钥加密这个密码，然后发送给Server：
       encodePwd = Encrypt.getEncryptPwd(pwd);
   远程方法调用：
       Integer errorCode = ivsProfessionalCommon.login(userName, encodePwd);
3. 之后，SDK Server只要使用它自己的私钥来解码这个密文就可以获得对应的密码


