package com.anlu.ld.basedemo.http;




public interface API {

    interface BaseApi {
       /* @GET("/v1/device")
        Observable<BaseResultWrapper<DeviceBean>> getDeviceId();


        @POST("/v1/sms/send")
        Observable<BaseResultWrapper> sendVerificationCode(@Body VerificationCodeParam param);

        @GET("/v1/page/index")
        Observable<BaseResultWrapper<H5PagerBean>> getH5Pagers();

        @GET("/v1/medical_info/index")
        Observable<BaseResultWrapper<IndexListBean>> getIndexInfo();

        @Multipart
        @POST("/v1/uploads")
        Observable<BaseResultWrapper<UploadBean>> upload(@Part MultipartBody.Part imgs);

        @GET("/v1/version/check_version")
        Observable<BaseResultWrapper<VersionUpdateBean>> checkUpdate(@Query("os_type") int os_type,
                                                                     @Query("ver_num") String ver_num,
                                                                     @Query("channel_id") int channel_id);*/

    }

    interface UserApi {
        /*@Headers("atype:login")
        @GET("/v1/wx/auth")
        Observable<BaseResultWrapper<WxLoginBean>> loginWx(@Query("code") String code);

        @GET("/v1/wx/bind")
        Observable<BaseResultWrapper<WxBindBean>> bindWX(@Query("code") String code);

        @Headers("atype:login")
        @POST("/v1/authenticate")
        Observable<BaseResultWrapper<LoginBean>> login(@Body LoginParam param);

        @GET("/v1/friend/invit")
        Observable<BaseResultWrapper<InviteFriendBean>> getInviteInfo();

        @GET("/v1/personal/info")
        Observable<BaseResultWrapper<PersonalInfo>> getPersionInfo();

        @POST("/v1/personal/gender")
        Observable<BaseResultWrapper<InterestedOfficeBean>> modifyUserSex(@Query("gender") int gender);

        @GET("/v1/information/collect")
        Observable<BaseResultWrapper<StatusBean>> setCollectionNews(@Query("article_id") int article_id);

        @GET("/v1/information/is_collect")
        Observable<BaseResultWrapper<StatusBean>> getNewIsCollection(@Query("article_id") int article_id);

        @GET("/v1/information/article_read_info")
        Observable<BaseResultWrapper<SingleMessageBean>> articleReadInfo(@Query("article_id") int article_id);

        @GET("/v1/myInfo")
        Observable<BaseResultWrapper<ShareBean>> getShareInfo();

        @GET("/v1/message/has_message")
        Observable<BaseResultWrapper<HasMessageBean>> hasMessage();

        @GET("/v1/feedback/unread")
        Observable<BaseResultWrapper<FeedBackBean>> hasFeedBackMessage();

        @POST("/v1/personal/modify")
        Observable<BaseResultWrapper<InterestedOfficeBean>> modifyUserNick(@Query("nickname") String nickname, @Query("avatar") String avatar);

        @GET("/v1/friend/index")
        Observable<BaseResultWrapper<MyFriendListBean>> getMyFriendInfo(@Query("page_index") int page_index, @Query("page_size") int page_size);*/
/*

        @Headers("atype:login")
        @GET("/v1/diagnosis/office")
        Observable<BaseResultWrapper<InquiryOfficeListBean>> getMyInquiryOffice();

        @POST("/v1/signOut")
        Observable<BaseResultWrapper<String>> signOut();
*/
    }
}
