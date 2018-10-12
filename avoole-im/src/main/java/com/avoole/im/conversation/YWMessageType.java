package com.avoole.im.conversation;

import com.avoole.im.exception.WXRuntimeException;

public class YWMessageType {
    public YWMessageType() {
    }

    public static enum SendState {
        init(0),
        sending(1),
        sended(2),
        received(3),
        readed(4),
        failed(5);

        private final int value;

        private SendState(int state) {
            this.value = state;
        }

        public static YWMessageType.SendState valueOf(int state) {
            switch(state) {
                case 0:
                    return init;
                case 1:
                    return sending;
                case 2:
                    return sended;
                case 3:
                    return received;
                case 4:
                    return readed;
                case 5:
                    return failed;
                default:
                    return sended;
            }
        }

        public int getValue() {
            return this.value;
        }
    }

    public static enum ReadState {
        init(0),
        read(2);

        private final int value;

        private ReadState(int state) {
            this.value = state;
        }

        public static YWMessageType.ReadState valueOf(int state) {
            switch(state) {
                case 0:
                    return init;
                case 1:
                    return read;
                default:
                    return read;
            }
        }

        public int getValue() {
            return this.value;
        }
    }

    public static enum DownloadState {
        init(0),
        success(1),
        fail(2);

        private final int value;

        private DownloadState(int state) {
            this.value = state;
        }

        public static YWMessageType.DownloadState valueOf(int state) {
            switch(state) {
                case 0:
                    return init;
                case 1:
                    return success;
                case 2:
                    return fail;
                default:
                    throw new WXRuntimeException("bad DownLoadState value:" + state);
            }
        }

        public int getValue() {
            return this.value;
        }
    }
}
