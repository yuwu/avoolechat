package com.avoole.im.conversation;

import android.text.TextUtils;
import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

public class YWMessageBody implements Serializable {
    private static final long serialVersionUID = 1L;
    private String mSummary = "";
    private String mContent = "";
    private Object mExtraData;

    public YWMessageBody() {
    }

    public String getSummary() {
        return this.mSummary;
    }

    public void setSummary(String summary) {
        this.mSummary = summary;
    }

    public String getContent() {
        return this.mContent;
    }

    public void setContent(String content) {
        this.mContent = content;
    }

    public Object getExtraData() {
        return this.mExtraData;
    }

    public void setExtraData(Object extraData) {
        this.mExtraData = extraData;
    }

    public boolean isAppSupport() {
        return this.isInternalVideoMsg();
    }

    public boolean isInternalVideoMsg() {
        if (!TextUtils.isEmpty(this.mContent)) {
            try {
                JSONObject jsonObject = new JSONObject(this.mContent);
                if (jsonObject != null && jsonObject.has("customType")) {
                    String customType = jsonObject.getString("customType");
                    if (!TextUtils.isEmpty(customType)) {
                        int type = Integer.parseInt(customType);
                        if (type >= 10000 && type <= 10005) {
                            return true;
                        }
                    }
                }
            } catch (JSONException var4) {
                var4.printStackTrace();
            } catch (Exception var5) {
                var5.printStackTrace();
            }
        }

        return false;
    }

    public boolean isMergedForwardMsg() {
        return false;
    }
}
