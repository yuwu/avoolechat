package com.avoole.im.conversation;

import com.alibaba.mobileim.channel.YWEnum.SendImageResolutionType;
import com.alibaba.mobileim.contact.IYWContact;
import com.alibaba.mobileim.conversation.YWMessageType.SendState;
import java.io.Serializable;

public abstract class YWMessage implements Serializable, IYWMessageModel {
    private static final long serialVersionUID = -2149002680762819354L;
    public static final int MSG_UNREADED_STATUS = 0;
    public static final int MSG_READED_STATUS = 1;

    public YWMessage() {
    }

    public abstract String getConversationId();

    public abstract SendState getHasSend();

    public abstract String getAuthorUserId();

    public abstract String getAuthorAppkey();

    public abstract String getAuthorUserName();

    public abstract long getMsgId();

    public abstract long getTime();

    public abstract long getTimeInMillisecond();

    public abstract int getSubType();

    public abstract YWMessageBody getMessageBody();

    public abstract String getAuthorId();

    public abstract String getContent();

    public abstract void setContent(String var1);

    public abstract boolean isAtMsgHasRead();

    public abstract int getReadCount();

    public abstract int getUnreadCount();

    public abstract int getAtFlag();

    public abstract boolean isAtMsgAck();

    public abstract String getAtMsgAckUUid();

    public abstract String getAtMsgAckUid();

    public abstract int getMsgReadStatus();

    public abstract void setMsgReadStatus(int var1);

    public abstract SendImageResolutionType getSendImageResolutionType();

    public abstract void setPushInfo(YWPushInfo var1);

    public abstract void setIsLocal(boolean var1);

    public abstract boolean getIsLocal();

    public abstract void setNeedSave(boolean var1);

    public abstract boolean getNeedSave();

    public abstract void setLocallyHideMessage(boolean var1);

    public abstract boolean isLocallyHideMessage();

    public abstract int getCustomMsgSubType();

    public abstract void setCustomMsgSubType(int var1);

    public abstract void setMessageAuthor(IYWContact var1);

    public abstract void setLocalMessageUnreadCount(int var1);

    public static class CUSTOM_MSG_SUB_MSG_TYPE {
        public static final int IM_GENERAL_CUSTOM_MSG = 0;
        public static final int IM_DEVICE_MSG = 1;

        public CUSTOM_MSG_SUB_MSG_TYPE() {
        }
    }

    public static class LOCAL_TRIBE_SYS_MESSAGE_TYPE {
        public static final int QUITE_TRIBE = 1;
        public static final int EXPEL_TRIBE = 2;
        public static final int MODIFY_TRIBE_NAME = 3;
        public static final int MODIFY_TRIBE_NOTICE = 4;
        public static final int MODIFY_TRIBE_CHECK_MODE = 5;
        public static final int MODIFY_TRIBE_NICK = 6;
        public static final int SET_TRIBE_MANAGER = 7;
        public static final int CANCEL_TRIBE_MANAGER = 8;
        public static final int JOIN_TRIBE = 9;
        public static final int MODIFY_TRIBE_NAME_AND_NOTICE = 10;

        public LOCAL_TRIBE_SYS_MESSAGE_TYPE() {
        }
    }

    public static class SUB_MSG_TYPE {
        public static final int IM_SYSTEM = -1;
        public static final int IM_SYSTEM_TIP = -3;
        public static final int IM_TICKET_TIP = -4;
        public static final int IM_TEXT = 0;
        public static final int IM_IMAGE = 1;
        public static final int IM_AUDIO = 2;
        public static final int IM_VIDEO = 3;
        public static final int IM_GIF = 4;
        public static final int IM_GEO = 8;
        public static final int IM_P2P_CUS = 66;
        public static final int IM_TRIBE_CUS = 17;
        public static final int IM_TEMPLATE = 65;
        public static final int IM_AUTO_REPLY_RSP = 67;
        public static final int IM_PROFILE_CARD = 52;
        public static final int IM_CARD = 20;
        public static final int IM_SHARE = 55;
        public static final int IM_ORDER_TRADEFOCUS = 56;
        public static final int IM_ORDER_CHANGED = 57;
        public static final int IM_INPUT_STATUS = 11;
        public static final int IM_GOODS_TRADEFOCUS = 9;
        public static final int TRIBE_TEMPLATE_MSG = 211;
        public static final int IM_HONGBAO_MSG = 227;

        public SUB_MSG_TYPE() {
        }
    }
}
