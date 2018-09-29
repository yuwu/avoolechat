package com.avoole.im.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.avoole.im.R;

/**
 * Created by wuyu on 18/9/29.
 */

public class ChatActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.im_activity_chat);
    }
}
