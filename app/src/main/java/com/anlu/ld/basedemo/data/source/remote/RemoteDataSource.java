package com.anlu.ld.basedemo.data.source.remote;


import com.anlu.ld.basedemo.AndroidApplication;
import com.anlu.ld.basedemo.data.source.DataSource;
import com.anlu.ld.basedemo.http.config.URLConfig;
import com.anlu.ld.basedemo.http.converter.CustomGsonConverterFactory;
import com.anlu.ld.basedemo.http.intercept.HeaderParamIntercept;
import com.anlu.ld.basedemo.http.intercept.PublicParamIntercept;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * 远程数据
 */

public class RemoteDataSource implements DataSource {
    private static RemoteDataSource INSTANCE;
    private Retrofit retrofit;

    private RemoteDataSource() {
        if (retrofit == null) {
            //initOkHttpClient
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient.Builder builder = new OkHttpClient.Builder()
                    .addInterceptor(new PublicParamIntercept())
                    .addInterceptor(new HeaderParamIntercept());

            if (AndroidApplication.isDebug()) {
                builder.addInterceptor(loggingInterceptor)
                        .addNetworkInterceptor(new StethoInterceptor())
                        .readTimeout(60, TimeUnit.SECONDS)
                        .writeTimeout(60, TimeUnit.SECONDS)
                        .connectTimeout(60, TimeUnit.SECONDS);
            } else {
                builder.readTimeout(15, TimeUnit.SECONDS)
                        .writeTimeout(15, TimeUnit.SECONDS)
                        .connectTimeout(15, TimeUnit.SECONDS);
            }


            //initRetrofit
            retrofit = new Retrofit.Builder()
                    .baseUrl(URLConfig.BaseUrl)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(CustomGsonConverterFactory.create())
                    .client(builder.build())
                    .build();
        }
    }

    public static RemoteDataSource getInstance() {
        if (INSTANCE == null) {
            synchronized (RemoteDataSource.class) {
                if (INSTANCE == null) {
                    INSTANCE = new RemoteDataSource();
                }
            }
        }
        return INSTANCE;
    }

    /*@Override
    public Observable<BaseResultWrapper<DeviceBean>> getDeviceId() {
        return retrofit.create(API.BaseApi.class).getDeviceId();
    }

    @Override
    public Observable<BaseResultWrapper<H5PagerBean>> getH5Pagers() {
        return retrofit.create(API.BaseApi.class).getH5Pagers();
    }

    @Override
    public Observable<BaseResultWrapper<UploadBean>> upload(File file) {
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);

        MultipartBody.Part body =
                MultipartBody.Part.createFormData("file", file.getName(), requestFile);
        return retrofit.create(API.BaseApi.class).upload(body);
    }

    @Override
    public Observable<BaseResultWrapper> sendVerificationCode(VerificationCodeParam param) {
        return retrofit.create(API.BaseApi.class).sendVerificationCode(param);
    }


    @Override
    public Observable<BaseResultWrapper<NewsListBean>> getNewsList(int chId, int page_index, int page_size) {
        return retrofit.create(API.NewsApi.class).getNewsList(chId, page_index, page_size);
    }

    @Override
    public Observable<BaseResultWrapper<NewsListBean>> getHotArticle(int page_index, int page_size) {
        return retrofit.create(API.NewsApi.class).getHotArticle(page_index, page_size);
    }

    @Override
    public Observable<BaseResultWrapper<IndexListBean>> getIndexInfo() {
        return retrofit.create(API.BaseApi.class).getIndexInfo();
    }


    @Override
    public Observable<BaseResultWrapper<DoctorRecommListBean>> getDocRecomList(int page_index, int page_size) {
        return retrofit.create(API.NewsApi.class).getDocRecomList(page_index, page_size);
    }

    @Override
    public Observable<BaseResultWrapper<InviteFriendBean>> getInviteInfo() {
        return retrofit.create(API.UserApi.class).getInviteInfo();
    }

    @Override
    public Observable<BaseResultWrapper<PersonalInfo>> getPersionInfo() {
        return retrofit.create(API.UserApi.class).getPersionInfo();
    }

    @Override
    public Observable<BaseResultWrapper<InterestedOfficeBean>> modifyUserSex(int gender) {
        return retrofit.create(API.UserApi.class).modifyUserSex(gender);
    }

    @Override
    public Observable<BaseResultWrapper<StatusBean>> setCollectionNews(int article_id) {
        return retrofit.create(API.UserApi.class).setCollectionNews(article_id);
    }

    @Override
    public Observable<BaseResultWrapper<StatusBean>> getNewIsCollection(int article_id) {
        return retrofit.create(API.UserApi.class).getNewIsCollection(article_id);
    }
    @Override
    public Observable<BaseResultWrapper<SingleMessageBean>> articleReadInfo(int article_id) {
        return retrofit.create(API.UserApi.class).articleReadInfo(article_id);
    }

    @Override
    public Observable<BaseResultWrapper<ShareBean>> getShareInfo() {
        return retrofit.create(API.UserApi.class).getShareInfo();
    }

    @Override
    public Observable<BaseResultWrapper<HasMessageBean>> hasMessage() {
        return retrofit.create(API.UserApi.class).hasMessage();
    }

    @Override
    public Observable<BaseResultWrapper<FeedBackBean>> hasFeedBackMessage() {
        return retrofit.create(API.UserApi.class).hasFeedBackMessage();
    }

    @Override
    public Observable<BaseResultWrapper<SingleMessageBean>> enterOrder(Map<String, String> params) {
        return retrofit.create(API.UserApi.class).enterOrder(params);
    }

    @Override
    public Observable<BaseResultWrapper<InterestedOfficeBean>> modifyUserNick(String nickname, String avatar) {
        return retrofit.create(API.UserApi.class).modifyUserNick(nickname, avatar);
    }


    @Override
    public Observable<BaseResultWrapper<LoginBean>> login(LoginParam param) {
        return retrofit.create(API.UserApi.class).login(param);
    }

    @Override
    public Observable<BaseResultWrapper<WxLoginBean>> loginWx(String code) {
        return retrofit.create(API.UserApi.class).loginWx(code);
    }

    @Override
    public Observable<BaseResultWrapper<WxBindBean>> bindWX(String code) {
        return retrofit.create(API.UserApi.class).bindWX(code);
    }

    @Override
    public Observable<BaseResultWrapper<AddressListBean.AddressBean>> changeAddressInfo(ModifyAddressParam modifyAddressParam) {
        return retrofit.create(API.UserApi.class).changeAddressInfo(modifyAddressParam);
    }

    @Override
    public Observable<BaseResultWrapper<AddressListBean.AddressBean>> addAddressInfo(ModifyAddressParam modifyAddressParam) {
        return retrofit.create(API.UserApi.class).addAddressInfo(modifyAddressParam);
    }

    @Override
    public Observable<BaseResultWrapper<LogisticsDetailListBean>> getLogisticsDetailList(int order_id) {
        return retrofit.create(API.UserApi.class).getLogisticsDetailList(order_id);
    }

    @Override
    public Observable<BaseResultWrapper<ChannelListBean>> getChannelList() {
        return retrofit.create(API.NewsApi.class).getChannelList();
    }

    @Override
    public Observable<BaseResultWrapper<MyFriendListBean>> getMyFriendInfo(int page_index, int page_size) {
        return retrofit.create(API.UserApi.class).getMyFriendInfo(page_index, page_size);
    }

    @Override
    public Observable<BaseResultWrapper<InquiryOfficeListBean>> getMyInquiryOffice() {
        return retrofit.create(API.UserApi.class).getMyInquiryOffice();
    }

    @Override
    public Observable<BaseResultWrapper<String>> signOut() {
        return retrofit.create(API.UserApi.class).signOut();
    }

    @Override
    public Observable<BaseResultWrapper<MessageCountBean>> getMessageCount() {
        return retrofit.create(API.UserApi.class).getMessageCount();
    }

    @Override
    public Observable<BaseResultWrapper<AddressListBean>> getAddressInfo() {
        return retrofit.create(API.UserApi.class).getAddressInfo();
    }

    @Override
    public Observable<BaseResultWrapper<MyCouponListBean>> getMyCouponList() {
        return retrofit.create(API.UserApi.class).getMyCouponList();
    }

    @Override
    public Observable<BaseResultWrapper<OfficeListBean>> getOfficeList() {
        return retrofit.create(API.UserApi.class).getOfficeList();
    }

    @Override
    public Observable<BaseResultWrapper<InterestedOfficeBean>> getInterestedOffice(int office_id) {
        return retrofit.create(API.UserApi.class).getInterestedOffice(office_id);
    }

    @Override
    public Observable<BaseResultWrapper<AddressListBean.AddressBean>> setDefaultAddress(int address_id) {
        return retrofit.create(API.UserApi.class).setDefaultAddress(address_id);
    }

    @Override
    public Observable<BaseResultWrapper<FlagBean>> deleteAddress(int address_id) {
        return retrofit.create(API.UserApi.class).deleteAddress(address_id);
    }

    @Override
    public Observable<BaseResultWrapper<InquiryIndexListBean>> getMyInquiryIndexList(int office_Id) {
        return retrofit.create(API.UserApi.class).getMyInquiryIndexList(office_Id);
    }

    @Override
    public Observable<BaseResultWrapper<SystemMessageListBean>> getSystemMessageList(int page_index, int page_size) {
        return retrofit.create(API.UserApi.class).getSystemMessageList(page_index, page_size);
    }

    @Override
    public Observable<BaseResultWrapper<MyCollectionListBean>> getCollectionList(int page_index, int page_size) {
        return retrofit.create(API.UserApi.class).getCollectionList(page_index, page_size);
    }

    @Override
    public Observable<BaseResultWrapper<CommonMessageListBean>> getCommonMessageList(int page_index, int page_size, int type) {
        return retrofit.create(API.UserApi.class).getCommonMessageList(page_index, page_size, type);
    }

    @Override
    public Observable<BaseResultWrapper<ChatBean.DataBean>> sendChatMessage(ChatBean.MsgBean msgBean) {
        return retrofit.create(API.ChatApi.class).sendChatMessage(msgBean);
    }

    @Override
    public Observable<BaseResultWrapper<ChatBean>> startPolling() {
        return retrofit.create(API.ChatApi.class).startPolling();
    }

    @Override
    public Observable<BaseResultWrapper<ChatBean>> getChatList(String chatId) {
        return retrofit.create(API.ChatApi.class).getChatList(chatId, Cons.CHAT_PAGE_SIZE);
    }

    @Override
    public Observable<BaseResultWrapper<OrderListBean>> getAllOrderList(int pageIndex, int pageSize) {
        return retrofit.create(API.UserApi.class).getAllOrderList(pageIndex, pageSize);
    }

    @Override
    public Observable<BaseResultWrapper<OrderListBean>> getWaitPayOrderList(int pageIndex, int pageSize) {
        return retrofit.create(API.UserApi.class).getWaitPayOrderList(pageIndex, pageSize);
    }

    @Override
    public Observable<BaseResultWrapper<OrderListBean>> getWaitShipOrderList(int pageIndex, int pageSize) {
        return retrofit.create(API.UserApi.class).getWaitShipOrderList(pageIndex, pageSize);
    }

    @Override
    public Observable<BaseResultWrapper<OrderListBean>> getWaitReceiptOrderList(int pageIndex, int pageSize) {
        return retrofit.create(API.UserApi.class).getWaitReceiptOrderList(pageIndex, pageSize);
    }

    @Override
    public Observable<BaseResultWrapper<OrderListBean>> getClosedOrderList(int pageIndex, int pageSize) {
        return retrofit.create(API.UserApi.class).getClosedOrderList(pageIndex, pageSize);
    }

    public Observable<BaseResultWrapper<VersionUpdateBean>> checkUpdate(String ver_num, int channel_id) {
        return retrofit.create(API.BaseApi.class).checkUpdate(Cons.OS_ANDROID, ver_num, channel_id);
    }

    @Override
    public Observable<BaseResultWrapper> postRead(MessageReadParam param) {
        return retrofit.create(API.ChatApi.class).postRead(param);
    }

    @Override
    public Observable<BaseResultWrapper<RedPointBean>> pollingRedPoint() {
        return retrofit.create(API.ChatApi.class).pollingRedPoint();
    }

    @Override
    public Observable<BaseResultWrapper<WeChatPayParamsBean>> getWeChatPayParams(int orderId) {
        return retrofit.create(API.UserApi.class).getWeChatPayParams(orderId);
    }

    @Override
    public Observable<BaseResultWrapper<SingleMessageBean>> uploadWeChatPayLog(Map<String, String> params) {
        return retrofit.create(API.UserApi.class).uploadWeChatPayLog(params);
    }

    @Override
    public Observable<BaseResultWrapper<WeChatOrderStatusBean>> getWeChatOrderStatus(Map<String, String> params) {
        return retrofit.create(API.UserApi.class).getWeChatOrderStatus(params);
    }

    @Override
    public Observable<BaseResultWrapper<OrderPayResultBean>> getOrderPayResult(int orderId) {
        return retrofit.create(API.UserApi.class).getOrderPayResult(orderId);
    }

    @Override
    public Observable<BaseResultWrapper<OrderInfoBean>> getOrderInfo(int orderId) {
        return retrofit.create(API.ChatApi.class).getOrderInfo(orderId);
    }

    @Override
    public Observable<BaseResultWrapper<ConfirmedOrderDetailBean>> getConfirmedOrderDetail(int orderId) {
        return retrofit.create(API.UserApi.class).getConfirmedOrderDetail(orderId);
    }

    @Override
    public Observable<BaseResultWrapper<UnConfirmOrderBean>> getUnConfirmedOrderDetail(int orderId) {
        return retrofit.create(API.UserApi.class).getUnConfirmedOrderDetail(orderId);
    }

    @Override
    public Observable<BaseResultWrapper<UsableCouponBean>> getUsableCouponList(int orderId) {
        return retrofit.create(API.UserApi.class).getUsableCouponList(orderId);
    }

    @Override
    public Observable<BaseResultWrapper<FeedBackChatList>> getFeedBackChatList() {
        return retrofit.create(API.UserApi.class).getFeedBackList();
    }

    @Override
    public Observable<BaseResultWrapper<SingleMessageBean>> cancelOrder(int orderId) {
        return retrofit.create(API.UserApi.class).cancelOrder(orderId);
    }

    @Override
    public Observable<BaseResultWrapper<SingleMessageBean>> deleteOrder(int orderId) {
        return retrofit.create(API.UserApi.class).deleteOrder(orderId);
    }

    @Override
    public Observable<BaseResultWrapper<SingleMessageBean>> enterReceipt(int orderId) {
        return retrofit.create(API.UserApi.class).enterReceipt(orderId);
    }

    @Override
    public Observable<BaseResultWrapper<UpdateCouponBean>> updateCoupon(Map<String, String> params) {
        return retrofit.create(API.UserApi.class).updateCoupon(params);
    }

    @Override
    public Observable<BaseResultWrapper<SingleMessageBean>> endFeedBack() {
        return retrofit.create(API.UserApi.class).endFeedBack();
    }

    @Override
    public Observable<BaseResultWrapper<SingleMessageBean>> postFeedBack(Map<String, String> params) {
        return retrofit.create(API.UserApi.class).postFeedBack(params);
    }*/

}
