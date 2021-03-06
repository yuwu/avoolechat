package com.avoole.im.viewfeatures;


import android.support.v7.widget.RecyclerView;

import com.avoole.im.ui.ChatInput;

/**
 * 聊天界面的接口
 */
public interface ChatView extends MvpView {

    /**
     * 最新输入字符
     *
     * @param changedText
     * @param start
     * @param end
     */
    void onInputText(CharSequence changedText, int start, int end);

    void onInputModeChangeBefore(ChatInput.InputMode oldMode);

    void onInputModeChangeAlfter(ChatInput.InputMode newMode);

    /**
     * 显示消息
     */
    //void showMessage(TIMMessage message);

    /**
     * 显示消息
     */
    //void showMessage(List<TIMMessage> messages);

    /**
     * 显示撤回消息
     */
    //void showRevokeMessage(TIMMessageLocator timMessageLocator);

    /**
     * 清除所有消息(离线恢复),并等待刷新
     */
    void clearAllMessage();

    /**
     * 发送消息成功
     *
     * @param message 返回的消息
     */
    //void onSendMessageSuccess(TIMMessage message);

    /**
     * 发送消息失败
     *
     * @param code    返回码
     * @param desc    返回描述
     * @param message 发送的消息
     */
    //void onSendMessageFail(int code, String desc, TIMMessage message);

    /**
     * 发送图片消息
     */
    void sendImage();

    /**
     * 发送照片消息
     */
    void sendPhoto();

    /**
     * 发送文字消息
     */
    void sendText();

    /**
     * 发送文件
     */
    void sendFile();

    /**
     * 开始发送语音消息
     */
    void startSendVoice();

    /**
     * 结束发送语音消息
     */
    void endSendVoice();

    /**
     * 发送位置
     */
    void sendLocation();

    /**
     * 发送小视频消息
     *
     * @param fileName 文件名
     */
    void sendVideo(String fileName);

    /**
     * 结束发送语音消息
     */
    void cancelSendVoice();

    /**
     * 正在发送
     */
    void sending();

    /**
     * 显示草稿
     */
    //void showDraft(TIMMessageDraft draft);

    /**
     * 视频按钮点击事件
     */
    void videoAction();

    /**
     * 显示toast
     */
    void showToast(String msg);

    /**
     * 网络图库按钮点击事件
     */
    void imageGalleryAction();

    /**
     * 分享贴子/转会/招募
     **/
    void shearPost(Object post);
}
