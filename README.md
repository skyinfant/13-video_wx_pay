# 13-video_wx_pay
微信登陆/支付demo，这是后端部分，需要搭配前端部分一起使用
前端代码地址：https://github.com/skyinfant/13-video_wx_pay_front
本项目共有两大主要功能：微信授权登陆和微信扫码支付


部署方式：
1、在mysql创建一个database，然后执行项目中的video_wx_pay.sql，生成相关表和数据
2、配置好application.properties的各个参数（本项目需要用到多个微信登陆和微信支付相关的参数，这些参数有些是需要企业资质才能申请的，具体参数含义请参阅微信平台官方文档）
    微信授权登陆（网站应用）官方文档：https://developers.weixin.qq.com/doc/oplatform/Website_App/WeChat_Login/Wechat_Login.html
    微信支付官方文档：https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=9_7&index=8
2、在idea启动本项目
3、用VSCODE打开前端代码，运行index.html即可打开项目主页


