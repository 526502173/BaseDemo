package com.anlu.ld.basedemo.data.source;


import com.anlu.ld.basedemo.data.source.local.LocalDataSource;
import com.anlu.ld.basedemo.data.source.remote.RemoteDataSource;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * M层
 */
public class Repository implements DataSource {
    private static Repository INSTANCE = null;
    private final RemoteDataSource mRemoteDataSource;
    private final LocalDataSource mLocalDataSource;

    private Repository(RemoteDataSource remoteDataSource, LocalDataSource localDataSource) {
        this.mRemoteDataSource = remoteDataSource;
        this.mLocalDataSource = localDataSource;
    }

    public static Repository getInstance(RemoteDataSource remoteDataSource, LocalDataSource localDataSource) {
        if (INSTANCE == null) {
            synchronized (DataSource.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Repository(remoteDataSource, localDataSource);
                }
            }
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    /**
     * 封装线程调度
     */
    public <T> ObservableTransformer<T, T> initNetworkThread() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

   /* @Override
    public Observable<BaseResultWrapper<NewsListBean>> getNewsList(int chId, int page_index, int page_size) {
        return mRemoteDataSource.getNewsList(chId, page_index, page_size).compose(this.<BaseResultWrapper<NewsListBean>>initNetworkThread());
    }

    @Override
    public Observable<BaseResultWrapper<NewsListBean>> getHotArticle(int page_index, int page_size) {
        return mRemoteDataSource.getHotArticle(page_index, page_size).compose(this.<BaseResultWrapper<NewsListBean>>initNetworkThread());
    }

    @Override
    public Observable<BaseResultWrapper<IndexListBean>> getIndexInfo() {
        return mRemoteDataSource.getIndexInfo().compose(this.<BaseResultWrapper<IndexListBean>>initNetworkThread());
    }

    @Override
    public Observable<BaseResultWrapper<HasMessageBean>> hasMessage() {
        return mRemoteDataSource.hasMessage().compose(this.<BaseResultWrapper<HasMessageBean>>initNetworkThread());
    }

    @Override
    public Observable<BaseResultWrapper<FeedBackBean>> hasFeedBackMessage() {
        return mRemoteDataSource.hasFeedBackMessage().compose(this.<BaseResultWrapper<FeedBackBean>>initNetworkThread());
    }

    @Override
    public Observable<BaseResultWrapper<SingleMessageBean>> enterOrder(Map<String, String> params) {
        return mRemoteDataSource.enterOrder(params).compose(this.<BaseResultWrapper<SingleMessageBean>>initNetworkThread());
    }

    @Override
    public Observable<BaseResultWrapper<DoctorRecommListBean>> getDocRecomList(int page_index, int page_size) {
        return mRemoteDataSource.getDocRecomList(page_index, page_size).compose(this.<BaseResultWrapper<DoctorRecommListBean>>initNetworkThread());
    }

    @Override
    public Observable<BaseResultWrapper<UploadBean>> upload(File file) {
        return mRemoteDataSource.upload(file).compose(this.<BaseResultWrapper<UploadBean>>initNetworkThread());
    }


    @Override
    public Observable<BaseResultWrapper> sendVerificationCode(VerificationCodeParam param) {
        return mRemoteDataSource.sendVerificationCode(param).compose(this.<BaseResultWrapper>initNetworkThread());
    }

    @Override
    public Observable<BaseResultWrapper<LoginBean>> login(LoginParam param) {
        return mRemoteDataSource.login(param).compose(this.<BaseResultWrapper<LoginBean>>initNetworkThread());
    }

    @Override
    public Observable<BaseResultWrapper<WxLoginBean>> loginWx(String code) {
        return mRemoteDataSource.loginWx(code).compose(this.<BaseResultWrapper<WxLoginBean>>initNetworkThread());
    }

    @Override
    public Observable<BaseResultWrapper<WxBindBean>> bindWX(String code) {
        return mRemoteDataSource.bindWX(code).compose(this.<BaseResultWrapper<WxBindBean>>initNetworkThread());
    }

    @Override
    public Observable<BaseResultWrapper<AddressListBean.AddressBean>> changeAddressInfo(ModifyAddressParam modifyAddressParam) {
        return mRemoteDataSource.changeAddressInfo(modifyAddressParam).compose(this.<BaseResultWrapper<AddressListBean.AddressBean>>initNetworkThread());
    }

    @Override
    public Observable<BaseResultWrapper<AddressListBean.AddressBean>> addAddressInfo(ModifyAddressParam modifyAddressParam) {
        return mRemoteDataSource.addAddressInfo(modifyAddressParam).compose(this.<BaseResultWrapper<AddressListBean.AddressBean>>initNetworkThread());
    }

    @Override
    public Observable<BaseResultWrapper<LogisticsDetailListBean>> getLogisticsDetailList(int order_id) {
        return mRemoteDataSource.getLogisticsDetailList(order_id).compose(this.<BaseResultWrapper<LogisticsDetailListBean>>initNetworkThread());
    }


    @Override
    public Observable<BaseResultWrapper<DeviceBean>> getDeviceId() {
        return mRemoteDataSource.getDeviceId().compose(this.<BaseResultWrapper<DeviceBean>>initNetworkThread());
    }

    @Override
    public Observable<BaseResultWrapper<H5PagerBean>> getH5Pagers() {
        return mRemoteDataSource.getH5Pagers().compose(this.<BaseResultWrapper<H5PagerBean>>initNetworkThread());
    }

    @Override
    public Observable<BaseResultWrapper<ChannelListBean>> getChannelList() {
        return mRemoteDataSource.getChannelList().compose(this.<BaseResultWrapper<ChannelListBean>>initNetworkThread());
    }

    @Override
    public Observable<BaseResultWrapper<InviteFriendBean>> getInviteInfo() {
        return mRemoteDataSource.getInviteInfo().compose(this.<BaseResultWrapper<InviteFriendBean>>initNetworkThread());
    }

    @Override
    public Observable<BaseResultWrapper<PersonalInfo>> getPersionInfo() {
        return mRemoteDataSource.getPersionInfo().compose(this.<BaseResultWrapper<PersonalInfo>>initNetworkThread());
    }

    @Override
    public Observable<BaseResultWrapper<InterestedOfficeBean>> modifyUserSex(int gender) {
        return mRemoteDataSource.modifyUserSex(gender).compose(this.<BaseResultWrapper<InterestedOfficeBean>>initNetworkThread());
    }

    @Override
    public Observable<BaseResultWrapper<StatusBean>> setCollectionNews(int article_id) {
        return mRemoteDataSource.setCollectionNews(article_id).compose(this.<BaseResultWrapper<StatusBean>>initNetworkThread());
    }

    @Override
    public Observable<BaseResultWrapper<StatusBean>> getNewIsCollection(int article_id) {
        return mRemoteDataSource.getNewIsCollection(article_id).compose(this.<BaseResultWrapper<StatusBean>>initNetworkThread());
    }

    @Override
    public Observable<BaseResultWrapper<SingleMessageBean>> articleReadInfo(int article_id) {
        return mRemoteDataSource.articleReadInfo(article_id).compose(this.<BaseResultWrapper<SingleMessageBean>>initNetworkThread());
    }

    @Override
    public Observable<BaseResultWrapper<ShareBean>> getShareInfo() {
        return mRemoteDataSource.getShareInfo().compose(this.<BaseResultWrapper<ShareBean>>initNetworkThread());
    }

    @Override
    public Observable<BaseResultWrapper<InterestedOfficeBean>> modifyUserNick(String nickname, String avatar) {
        return mRemoteDataSource.modifyUserNick(nickname, avatar).compose(this.<BaseResultWrapper<InterestedOfficeBean>>initNetworkThread());
    }

    @Override
    public Observable<BaseResultWrapper<MyFriendListBean>> getMyFriendInfo(int page_index, int page_size) {
        return mRemoteDataSource.getMyFriendInfo(page_index, page_size).compose(this.<BaseResultWrapper<MyFriendListBean>>initNetworkThread());
    }

    @Override
    public Observable<BaseResultWrapper<InquiryOfficeListBean>> getMyInquiryOffice() {
        return mRemoteDataSource.getMyInquiryOffice().compose(this.<BaseResultWrapper<InquiryOfficeListBean>>initNetworkThread());
    }

    @Override
    public Observable<BaseResultWrapper<String>> signOut() {
        return mRemoteDataSource.signOut().compose(this.<BaseResultWrapper<String>>initNetworkThread());
    }

    @Override
    public Observable<BaseResultWrapper<InquiryIndexListBean>> getMyInquiryIndexList(int office_Id) {
        return mRemoteDataSource.getMyInquiryIndexList(office_Id).compose(this.<BaseResultWrapper<InquiryIndexListBean>>initNetworkThread());
    }

    @Override
    public Observable<BaseResultWrapper<MessageCountBean>> getMessageCount() {
        return mRemoteDataSource.getMessageCount().compose(this.<BaseResultWrapper<MessageCountBean>>initNetworkThread());
    }

    @Override
    public Observable<BaseResultWrapper<AddressListBean>> getAddressInfo() {
        return mRemoteDataSource.getAddressInfo().compose(this.<BaseResultWrapper<AddressListBean>>initNetworkThread());
    }

    @Override
    public Observable<BaseResultWrapper<MyCouponListBean>> getMyCouponList() {
        return mRemoteDataSource.getMyCouponList().compose(this.<BaseResultWrapper<MyCouponListBean>>initNetworkThread());
    }

    @Override
    public Observable<BaseResultWrapper<OfficeListBean>> getOfficeList() {
        return mRemoteDataSource.getOfficeList().compose(this.<BaseResultWrapper<OfficeListBean>>initNetworkThread());
    }

    @Override
    public Observable<BaseResultWrapper<InterestedOfficeBean>> getInterestedOffice(int office_id) {
        return mRemoteDataSource.getInterestedOffice(office_id).compose(this.<BaseResultWrapper<InterestedOfficeBean>>initNetworkThread());
    }

    @Override
    public Observable<BaseResultWrapper<AddressListBean.AddressBean>> setDefaultAddress(int address_id) {
        return mRemoteDataSource.setDefaultAddress(address_id).compose(this.<BaseResultWrapper<AddressListBean.AddressBean>>initNetworkThread());
    }

    @Override
    public Observable<BaseResultWrapper<FlagBean>> deleteAddress(int address_id) {
        return mRemoteDataSource.deleteAddress(address_id).compose(this.<BaseResultWrapper<FlagBean>>initNetworkThread());
    }

    @Override
    public Observable<BaseResultWrapper<SystemMessageListBean>> getSystemMessageList(int page_index, int page_size) {
        return mRemoteDataSource.getSystemMessageList(page_index, page_size).compose(this.<BaseResultWrapper<SystemMessageListBean>>initNetworkThread());
    }

    @Override
    public Observable<BaseResultWrapper<MyCollectionListBean>> getCollectionList(int page_index, int page_size) {
        return mRemoteDataSource.getCollectionList(page_index, page_size).compose(this.<BaseResultWrapper<MyCollectionListBean>>initNetworkThread());
    }

    @Override
    public Observable<BaseResultWrapper<OrderListBean>> getAllOrderList(int pageIndex, int pageSize) {
        return mRemoteDataSource.getAllOrderList(pageIndex, pageSize).compose(this.<BaseResultWrapper<OrderListBean>>initNetworkThread());
    }

    @Override
    public Observable<BaseResultWrapper<OrderListBean>> getWaitPayOrderList(int pageIndex, int pageSize) {
        return mRemoteDataSource.getWaitPayOrderList(pageIndex, pageSize).compose(this.<BaseResultWrapper<OrderListBean>>initNetworkThread());
    }

    @Override
    public Observable<BaseResultWrapper<OrderListBean>> getWaitShipOrderList(int pageIndex, int pageSize) {
        return mRemoteDataSource.getWaitShipOrderList(pageIndex, pageSize).compose(this.<BaseResultWrapper<OrderListBean>>initNetworkThread());
    }

    @Override
    public Observable<BaseResultWrapper<OrderListBean>> getWaitReceiptOrderList(int pageIndex, int pageSize) {
        return mRemoteDataSource.getWaitReceiptOrderList(pageIndex, pageSize).compose(this.<BaseResultWrapper<OrderListBean>>initNetworkThread());
    }

    @Override
    public Observable<BaseResultWrapper<OrderListBean>> getClosedOrderList(int pageIndex, int pageSize) {
        return mRemoteDataSource.getClosedOrderList(pageIndex, pageSize).compose(this.<BaseResultWrapper<OrderListBean>>initNetworkThread());
    }

    @Override
    public Observable<BaseResultWrapper<ConfirmedOrderDetailBean>> getConfirmedOrderDetail(int orderId) {
        return mRemoteDataSource.getConfirmedOrderDetail(orderId).compose(this.<BaseResultWrapper<ConfirmedOrderDetailBean>>initNetworkThread());
    }

    @Override
    public Observable<BaseResultWrapper<UnConfirmOrderBean>> getUnConfirmedOrderDetail(int orderId) {
        return mRemoteDataSource.getUnConfirmedOrderDetail(orderId).compose(this.<BaseResultWrapper<UnConfirmOrderBean>>initNetworkThread());
    }

    @Override
    public Observable<BaseResultWrapper<UsableCouponBean>> getUsableCouponList(int orderId) {
        return mRemoteDataSource.getUsableCouponList(orderId).compose(this.<BaseResultWrapper<UsableCouponBean>>initNetworkThread());
    }

    @Override
    public Observable<BaseResultWrapper<FeedBackChatList>> getFeedBackChatList() {
        return mRemoteDataSource.getFeedBackChatList().compose(this.<BaseResultWrapper<FeedBackChatList>>initNetworkThread());
    }

    @Override
    public Observable<BaseResultWrapper<SingleMessageBean>> cancelOrder(int orderId) {
        return mRemoteDataSource.cancelOrder(orderId).compose(this.<BaseResultWrapper<SingleMessageBean>>initNetworkThread());
    }

    @Override
    public Observable<BaseResultWrapper<SingleMessageBean>> deleteOrder(int orderId) {
        return mRemoteDataSource.deleteOrder(orderId).compose(this.<BaseResultWrapper<SingleMessageBean>>initNetworkThread());
    }

    @Override
    public Observable<BaseResultWrapper<SingleMessageBean>> enterReceipt(int orderId) {
        return mRemoteDataSource.enterReceipt(orderId).compose(this.<BaseResultWrapper<SingleMessageBean>>initNetworkThread());
    }

    @Override
    public Observable<BaseResultWrapper<UpdateCouponBean>> updateCoupon(Map<String, String> params) {
        return mRemoteDataSource.updateCoupon(params).compose(this.<BaseResultWrapper<UpdateCouponBean>>initNetworkThread());
    }

    @Override
    public Observable<BaseResultWrapper<SingleMessageBean>> endFeedBack() {
        return mRemoteDataSource.endFeedBack().compose(this.<BaseResultWrapper<SingleMessageBean>>initNetworkThread());
    }

    @Override
    public Observable<BaseResultWrapper<SingleMessageBean>> postFeedBack(Map<String, String> params) {
        return mRemoteDataSource.postFeedBack(params).compose(this.<BaseResultWrapper<SingleMessageBean>>initNetworkThread());
    }

    @Override
    public Observable<BaseResultWrapper<CommonMessageListBean>> getCommonMessageList(int page_index, int page_size, int type) {
        return mRemoteDataSource.getCommonMessageList(page_index, page_size, type).compose(this.<BaseResultWrapper<CommonMessageListBean>>initNetworkThread());
    }

    @Override
    public Observable<BaseResultWrapper<ChatBean.DataBean>> sendChatMessage(ChatBean.MsgBean msgBean) {
        return mRemoteDataSource.sendChatMessage(msgBean).compose(this.<BaseResultWrapper<ChatBean.DataBean>>initNetworkThread());
    }

    @Override
    public Observable<BaseResultWrapper<ChatBean>> startPolling() {
        return mRemoteDataSource.startPolling();
    }

    @Override
    public Observable<BaseResultWrapper<ChatBean>> getChatList(String chatId) {
        return mRemoteDataSource.getChatList(chatId).compose(this.<BaseResultWrapper<ChatBean>>initNetworkThread());
    }

    @Override
    public Observable<BaseResultWrapper<VersionUpdateBean>> checkUpdate(String ver_num, int channel_id) {
        return mRemoteDataSource.checkUpdate(ver_num, channel_id).compose(this.<BaseResultWrapper<VersionUpdateBean>>initNetworkThread());
    }

    @Override
    public Observable<BaseResultWrapper> postRead(MessageReadParam param) {
        return mRemoteDataSource.postRead(param).compose(this.<BaseResultWrapper>initNetworkThread());
    }

    @Override
    public Observable<BaseResultWrapper<RedPointBean>> pollingRedPoint() {
        return mRemoteDataSource.pollingRedPoint();
    }

    @Override
    public Observable<BaseResultWrapper<WeChatPayParamsBean>> getWeChatPayParams(int orderId) {
        return mRemoteDataSource.getWeChatPayParams(orderId).compose(this.<BaseResultWrapper<WeChatPayParamsBean>>initNetworkThread());
    }

    @Override
    public Observable<BaseResultWrapper<OrderInfoBean>> getOrderInfo(int orderId) {
        return mRemoteDataSource.getOrderInfo(orderId).compose(this.<BaseResultWrapper<OrderInfoBean>>initNetworkThread());
    }

    public Observable<BaseResultWrapper<SingleMessageBean>> uploadWeChatPayLog(Map<String, String> params) {
        return mRemoteDataSource.uploadWeChatPayLog(params).compose(this.<BaseResultWrapper<SingleMessageBean>>initNetworkThread());
    }

    @Override
    public Observable<BaseResultWrapper<WeChatOrderStatusBean>> getWeChatOrderStatus(Map<String, String> params) {
        return mRemoteDataSource.getWeChatOrderStatus(params).compose(this.<BaseResultWrapper<WeChatOrderStatusBean>>initNetworkThread());
    }

    @Override
    public Observable<BaseResultWrapper<OrderPayResultBean>> getOrderPayResult(int orderId) {
        return mRemoteDataSource.getOrderPayResult(orderId).compose(this.<BaseResultWrapper<OrderPayResultBean>>initNetworkThread());
    }*/

}
