package com.anlu.ld.basedemo.data.bean;

import android.os.Build;

import com.anlu.ld.basedemo.BuildConfig;
import com.anlu.ld.basedemo.cons.Cons;


/**
 * Created by maoqi on 2019/2/13.
 */
public class PublicParamBean {
    //设备1=>Android,2=>iOS
    private String k_os;
    //当前软件版本
    private String k_appversion;
    // string 手机型号
    private String k_model;

    private int k_lat;//纬度0 /
    private int k_lon;//经度0 /
    private String k_network;//wifi/4G/3G/2G/E等 /
    private String k_mac;//手机mac地址，能获取则传递，不能获取则发送空值 /
    private String k_release;//手机系统版本,例如miui7.0/iOS的11.0.3 /
    private String k_imei;//手机imei（(安卓必填)）/
    private String k_idfa;//(安卓不填，iOS必填)
    private String k_brand;//品牌(iOS可传 Apple)
    private String k_unixtime;//手机系统时间戳 /



    public PublicParamBean() {
        this.k_os = String.valueOf(Cons.OS_ANDROID);
        this.k_appversion = BuildConfig.VERSION_NAME;
        this.k_model = Build.MODEL;
        this.k_release=Build.VERSION.RELEASE;
        this.k_brand= Build.BRAND;
    }


    public int getK_lat() {
        return k_lat;
    }

    public void setK_lat(int k_lat) {
        this.k_lat = k_lat;
    }

    public int getK_lon() {
        return k_lon;
    }

    public void setK_lon(int k_lon) {
        this.k_lon = k_lon;
    }

    public String getK_network() {
        return k_network;
    }

    public void setK_network(String k_network) {
        this.k_network = k_network;
    }

    public String getK_mac() {
        return k_mac;
    }

    public void setK_mac(String k_mac) {
        this.k_mac = k_mac;
    }

    public String getK_release() {
        return k_release;
    }

    public void setK_release(String k_release) {
        this.k_release = k_release;
    }

    public String getK_imei() {
        return k_imei;
    }

    public void setK_imei(String k_imei) {
        this.k_imei = k_imei;
    }

    public String getK_idfa() {
        return k_idfa;
    }

    public void setK_idfa(String k_idfa) {
        this.k_idfa = k_idfa;
    }

    public String getK_brand() {
        return k_brand;
    }

    public void setK_brand(String k_brand) {
        this.k_brand = k_brand;
    }

    public String getK_unixtime() {
        return k_unixtime;
    }

    public void setK_unixtime(String k_unixtime) {
        this.k_unixtime = k_unixtime;
    }

    public String getK_os() {
        return k_os;
    }

    public void setK_os(String k_os) {
        this.k_os = k_os;
    }

    public String getK_appversion() {
        return k_appversion;
    }

    public void setK_appversion(String k_appversion) {
        this.k_appversion = k_appversion;
    }

    public String getK_model() {
        return k_model;
    }

    public void setK_model(String k_model) {
        this.k_model = k_model;
    }

    @Override
    public String toString() {
        return "PublicParamBean{" +
                "k_os='" + k_os + '\'' +
                ", k_appversion='" + k_appversion + '\'' +
                ", k_model='" + k_model + '\'' +
                ", k_lat='" + k_lat + '\'' +
                ", k_lon='" + k_lon + '\'' +
                ", k_network='" + k_network + '\'' +
                ", k_mac='" + k_mac + '\'' +
                ", k_release='" + k_release + '\'' +
                ", k_imei='" + k_imei + '\'' +
                ", k_idfa='" + k_idfa + '\'' +
                ", k_brand='" + k_brand + '\'' +
                ", k_unixtime='" + k_unixtime + '\'' +
                '}';
    }
}
