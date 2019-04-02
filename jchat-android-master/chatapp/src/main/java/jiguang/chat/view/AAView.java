package jiguang.chat.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.activeandroid.query.Select;

import java.util.List;
import java.util.Random;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.callback.GetUserInfoCallback;
import cn.jpush.im.android.api.model.UserInfo;
import cz.msebera.android.httpclient.params.HttpProtocolParamBean;
import jiguang.chat.R;
import jiguang.chat.application.JGApplication;
import jiguang.chat.database.CatchUserEntry;
import jiguang.chat.database.SignEntry;
import jiguang.chat.utils.HttpConnect;
import jiguang.chat.utils.SharePreferenceManager;

public class AAView extends LinearLayout {
    private Context mContext;
    private Button mButton;
    private TextView mUserName;
    private TextView mPiPeiName;
    private TextView mLeftSign;
    private TextView mRightSign;
    public AAView(Context context, AttributeSet attrs){
        super(context,attrs);
        this.mContext = context;
    }
    public void initModule(){
        mButton = findViewById(R.id.aa_btn);
        mButton.setAllCaps(false);
        mUserName = findViewById(R.id.username_2a);
        mPiPeiName = findViewById(R.id.pipeiname_2a);
        mLeftSign = findViewById(R.id.left_msg_2a);
        mRightSign = findViewById(R.id.right_msg_2a);
        mLeftSign.setText(JGApplication.mySign);
        mUserName.setText("["+JMessageClient.getMyInfo().getUserName()+"]");
        Random random1 = new Random(SharePreferenceManager.getCachedSeed());
        List<CatchUserEntry> catchUserEntries = new Select().from(CatchUserEntry.class).execute();
        String userName = catchUserEntries.get(random1.nextInt(catchUserEntries.size())).getUsername();
        JMessageClient.getUserInfo(userName, new GetUserInfoCallback() {
            @Override
            public void gotResult(int responseCode, String responseMessage, UserInfo userInfo) {
                if (responseCode == 0){
                    if(!TextUtils.isEmpty(userInfo.getUserName())){
                        mPiPeiName.setText("["+userInfo.getUserName()+"]");
                    }
                    if(!TextUtils.isEmpty(userInfo.getNickname())){
                        mPiPeiName.setText("["+userInfo.getNickname()+"]");
                    }
                    if(!TextUtils.isEmpty(userInfo.getNotename())){
                        mPiPeiName.setText("["+userInfo.getNotename()+"]");
                    }
                }else {
                    mPiPeiName.setText("没有此人");
                }
            }
        });
        Select mSelect = new Select();
        List<SignEntry> signEntries = mSelect.from(SignEntry.class).execute();
        Random random2 = new Random(SharePreferenceManager.getCachedSeed() + 10);
        String hhh = signEntries.get(random2.nextInt(signEntries.size())).getSign();
        mRightSign.setText(hhh);
    }
    public void setListener(OnClickListener onClickListener){
        mButton.setOnClickListener(onClickListener);
    }
    public String  getPipeiName(){
        return mPiPeiName.getText().toString();
    }
    public String  getPipeiString(){return mLeftSign.getText().toString();}
}
