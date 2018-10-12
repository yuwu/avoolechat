package com.avoole.mm;

import android.content.Intent;
import android.support.text.emoji.EmojiCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.alibaba.mobileim.YWIMCore;
import com.alibaba.mobileim.YWIMKit;
import com.alibaba.mobileim.lib.presenter.account.Account;
import com.alibaba.mobileim.login.LoginByIM;
import com.alibaba.mobileim.ui.WxChattingActvity;
import com.alibaba.mobileim.utility.UserContext;
import com.alibaba.tcms.LoginStatusManager;
import com.alibaba.wxlib.util.SysUtil;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
       // System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        TextView tv = (TextView) findViewById(R.id.sample_text);
        tv.setText("stringFromJNI()");

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WxChattingActvity.class);

//                UserContext context = new UserContext("1", SysUtil.getAppkey());
//                intent.putExtra(UserContext.EXTRA_USER_CONTEXT_KEY, context);
//                intent.putExtra("isMyComputerConv", true);
//                intent.putExtra("extraUserId", "2");

                LoginByIM.getInstance().showLogin(MainActivity.this);

                //startActivity(intent);
            }
        });
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    //public native String stringFromJNI();
}
