package com.fc.mydemo.utils;

/**
 * Created by fc on 2015/9/17.
 * 用于定义各种常量
 */
public class Constants {

    /*-------服务器请求地址--------*/
    //host
    public final static String SERVER_HOST = "101.200.180.185";
    //port
    public final static String SERVER_PORT = "8080";
    //
    public final static String SERVER_PATH = "http://" + SERVER_HOST;
    //服务器URL
    public final static String SERVER_URL = SERVER_PATH + ":" + SERVER_PORT;

    //PC首页
    public final static String PC_MAIN_URL = "http://101.200.180.185/travel_web/link_main_frame.html#/personal-index/";

    //普通登录URL
    public final static String NORMAL_LOGIN_PATH = SERVER_URL + "/luoyou/login/dologin.do";
    //请求动态密码URL
    //忘记密码第一步，获取验证码
    public final static String DYN_PWD_PATH = SERVER_URL + "/luoyou/login/getverifycode.do";
    //动态登录URL，获取动态密码
    //忘记密码第二步，校验验证码
    public final static String DYN_LOGIN_PATH = SERVER_URL + "/luoyou/login/mobilelogin.do";
    //注册URL
    public final static String REGISTER_PATH = SERVER_URL + "/luoyou/login/mobileRegister.do";
    //忘记密码第三步，修改密码URL
    public final static String FORGET_PWD_CHANGE_PWD_PATH = SERVER_URL + "/luoyou/login/forgetPwd.do";

    //酒店列表请求URL
    public final static String HOTEL_LIST_PATH = SERVER_URL + "/luoyou/hotelProductCol/searchHotels.do";
    //修改个人信息URL
    public final static String MY_INFO_MODIFY = SERVER_URL + "/luoyou/usermanage/updatepersoninfo.do";


    /*--------全局常量----------*/
    //response返回msg成功
    public final static String SUCCESS = "success";
    //response返回msg失败
    public final static String FAIL = "fail";
    //response返回的json字符串code关键字
    public final static String CODE = "code";
    //response返回的json字符串data关键字
    public final static String DATA = "data";
    //response返回的json字符串msg关键字
    public final static String MSG = "msg";

    /*--------共享参数---------*/
    //共享参数 文件名称
    public static final String PREFERENCES_NAME = "lingke.xml";

}
