package com.anlu.ld.basedemo.cons;

/**
 * 常量类
 * Created by maoqi on 2018/12/24.
 */
public class Cons {
    public static final int OS_ANDROID = 1;

   /* 全局通用错误码定义：（web端、app端必须要全局处理）
            200  // 请求成功
            400  // 客户端错误
            500  // 服务端错误
            401  // 未登录、登录失效、token过期且不能自动续期（动作：跳转到登录页面或者退出登录状态）
            402  // 缺少设备号（动作：重新获得一个设备号并存储）
            403  // 无访问权限*/
    public static final int REQUEST_SUCCESS = 200;
    public static final int REQUEST_TOKEN_TIMEOUT = 401;
    public static final int REQUEST_LOCAL = -1;

    //Intent request code
    public static final int INTENT_LOGIN_REQUEST = 100;
    public static final int INTENT_BIND_PHONE_REQUEST = 101;
    public static final int INTENT_INPUT_INVITE_CODE_REQUEST = 102;
    public static final int INTENT_ORDER = 103;

    //短信类型
    public static final int SMS_TYPE_SIMPLE = 1;
    public static final int SMS_TYPE_WITHDRAW = 2;

    //倒计时时间
    public static final int COUNT_DOWN_TIME = 60 * 1000;

    //Intent result code
    public static final int INTENT_RESULT_OK = 10086;

    public static final int INT_ZERO = 0;
}
