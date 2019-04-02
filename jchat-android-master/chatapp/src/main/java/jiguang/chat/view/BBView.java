package jiguang.chat.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.activeandroid.query.Select;

import java.util.List;
import java.util.Random;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.callback.GetUserInfoCallback;
import cn.jpush.im.android.api.model.UserInfo;
import jiguang.chat.R;
import jiguang.chat.application.JGApplication;
import jiguang.chat.database.CatchUserEntry;
import jiguang.chat.database.SignEntry;
import jiguang.chat.utils.HttpConnect;
import jiguang.chat.utils.SharePreferenceManager;

public class BBView extends LinearLayout {
    private Context mContext;
    private TextView mUserName;
    private TextView mLeftSign;
    private TextView mRightSign;
    private TextView mPipeiName;
    private Button mButton;

    public BBView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
    }

    public void initModule() {
        mButton = findViewById(R.id.btn_bb);
        mButton.setAllCaps(false);
        mLeftSign = findViewById(R.id.left_msg_2b);
        mUserName = findViewById(R.id.userId_2b);
        mRightSign =  findViewById(R.id.right_msg_2b);
        mPipeiName = findViewById(R.id.pipeiname_2b);
        mLeftSign.setText(JGApplication.mySign);
        mUserName.setText("["+JMessageClient.getMyInfo().getUserName()+"]");
        Random random1 = new Random(SharePreferenceManager.getCachedSeed()+1);
        List<CatchUserEntry> catchUserEntries = new Select().from(CatchUserEntry.class).execute();
        String userName = catchUserEntries.get(random1.nextInt(catchUserEntries.size())).getUsername();
        JMessageClient.getUserInfo(userName, new GetUserInfoCallback() {
            @Override
            public void gotResult(int responseCode, String responseMessage, UserInfo userInfo) {
                if (responseCode == 0){
                    if(!TextUtils.isEmpty(userInfo.getUserName())){
                        mPipeiName.setText("["+userInfo.getUserName()+"]");
                    }
                    if(!TextUtils.isEmpty(userInfo.getNickname())){
                        mPipeiName.setText("["+userInfo.getNickname()+"]");
                    }
                    if(!TextUtils.isEmpty(userInfo.getNotename())){
                        mPipeiName.setText("["+userInfo.getNotename()+"]");
                    }
                }else {
                    mPipeiName.setText("没有此人");
                }
            }
        });
        Select mSelect = new Select();
        List<SignEntry> signEntries = mSelect.from(SignEntry.class).execute();
        Random random2 = new Random(SharePreferenceManager.getCachedSeed()+11);
        String hhh = signEntries.get(random2.nextInt(signEntries.size())).getSign();

        mRightSign.setText(hhh);
    }
    public void setListenerBB(OnClickListener onClickListener){
        mButton.setOnClickListener(onClickListener);
    }
    public String  getPipeiNameBB(){
        return mPipeiName.getText().toString();
    }
    public String  getPipeiString(){
        return  mLeftSign.getText().toString();
    }
}