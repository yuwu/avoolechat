package com.avoole.im.conversation;

import com.avoole.im.conversation.YWMessageType.SendState;

public interface IYWMessageModel {
    long getMsgId();

    String getConversationId();

    int getSubType();

    int getAtFlag();

    String getContent();

    long getTime();

    YWMessageBody getMessageBody();

    SendState getHasSend();

    int getMsgReadStatus();

    String getAuthorUserId();

    String getAuthorUserName();

    String getAuthorAppkey();

    boolean getIsLocal();

    boolean getNeedSave();

    boolean isLocallyHideMessage();

    String getAtMsgAckUid();

    String getAtMsgAckUUid();

    boolean isAtMsgAck();

    boolean isAtMsgHasRead();

    int getReadCount();

    int getUnreadCount();
}
