package com.avoole.im.conversation;

import java.util.List;

public abstract class YWConversation {
    public YWConversation() {
    }

    public abstract String getConversationId();

    public abstract int getUnreadCount();

    public abstract long getLatestTimeInMillisecond();

    public abstract String getLatestContent();

    public abstract YWMessage getLastestMessage();

    public abstract boolean isTop();

    public abstract YWConversationType getConversationType();

    //public abstract YWMessageSender getMessageSender();

    //public abstract YWMessageLoader getMessageLoader();

    //public abstract YWConversationBody getConversationBody();

    //public abstract void addUnreadChangeListener(IYWConversationUnreadChangeListener var1);

    //public abstract void removeUnreadChangeListener(IYWConversationUnreadChangeListener var1);

    public abstract boolean hasUnreadAtMsg();

    public abstract YWMessage getLatestUnreadAtMsg();

    //public abstract void sendAtMsgReadAck(YWMessage var1, IWxCallback var2);

    //public abstract void sendAtMsgReadAckBatch(List<YWMessage> var1, IWxCallback var2);

    public abstract List<YWMessage> getAtMsgInConversation(String var1, int var2);

    public abstract List<YWMessage> getUnreadAtMsgInConversation(String var1);

    public abstract void updateAtMsgInConversationRead(String var1);

    public abstract void updateAtMsgRead(YWMessage var1, String var2);

    public abstract void updateAtMsgsRead(List<YWMessage> var1, String var2);

    //public abstract void getAtMsgReadUnReadCount(List<YWMessage> var1, IWxCallback var2);

    //public abstract void getAtMsgReadUnReadCount(YWMessage var1, IWxCallback var2);

    //public abstract void getAtMsgReadUnreadTribeMemberList(YWMessage var1, IWxCallback var2);

    public abstract String getLatestMessageAuthorId();

    public abstract String getLatestMessageAuthorAppKey();

    //public abstract void setMsgReadedStatusToServer(YWMessage var1, IWxCallback var2);

    //public abstract void setMsgReadedStatusToServer(List<YWMessage> var1, IWxCallback var2);

    //public abstract void getMsgReadedStatusFromServer(YWMessage var1, IWxCallback var2);

    //public abstract void getMsgReadedStatusFromServer(List<YWMessage> var1, IWxCallback var2);

    //public abstract YWConversationDraft createDraft();

    //public abstract YWConversationDraft createDraft(String var1, long var2);

    //public abstract YWConversationDraft getConversationDraft();

    //public abstract void setConversationDraft(YWConversationDraft var1);

    public abstract void saveDraft();

    public abstract String getLatestEServiceContactId();

    public abstract void updateCustomMessageExtraData(YWConversation var1, YWMessage var2);

    public abstract void updateMessageReadStatus(YWConversation var1, long var2);

    public abstract void addFailedInternalMessageLocally(YWConversation var1, YWMessage var2);

    public abstract boolean isMessageTimeVisible();

    public abstract void setMessageTimeVisible(boolean var1);
}
