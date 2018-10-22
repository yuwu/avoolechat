package com.avoole.im.core;

import java.util.List;

public interface MessageListener {

    boolean onNewMessages(List<IMMessage> messages);
}
