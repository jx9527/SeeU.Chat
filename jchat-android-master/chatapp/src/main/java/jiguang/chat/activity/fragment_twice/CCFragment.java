package jiguang.chat.activity.fragment_twice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.activeandroid.query.Select;

import java.util.List;
import java.util.Random;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.callback.GetUserInfoCallback;
import cn.jpush.im.android.api.model.UserInfo;
import jiguang.chat.R;
import jiguang.chat.activity.fragment.BaseFragment;
import jiguang.chat.application.JGApplication;
import jiguang.chat.controller.CCController;
import jiguang.chat.controller.CController;
import jiguang.chat.database.CatchUserEntry;
import jiguang.chat.database.SignEntry;
import jiguang.chat.utils.SharePreferenceManager;
import jiguang.chat.view.AView;
import jiguang.chat.view.CCView;

public class CCFragment extends BaseFragment {
    private View mRootView;
    public CCView mCCView;
    private CCController mCCController;
    private Context mContext;
    private TextView mPipeiName;
    private TextView mRightSign;
    private ReceiveBroadCast receiveBroadCast;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this.getActivity();
        init();
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        mRootView = layoutInflater.inflate(R.layout.fragment_cc,
                (ViewGroup)getActivity().findViewById(R.id.mresult_view),false);
        mCCView = mRootView.findViewById(R.id.cc_view);
        mCCView.initModule();
        mCCController = new CCController(this,mCCView);
        mCCView.setListenerCC(mCCController);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mPipeiName = mRootView.findViewById(R.id.pipeiname_2c);
        mRightSign = mRootView.findViewById(R.id.right_msg_2c);
        ViewGroup p = (ViewGroup) mRootView.getParent();
        if (p != null) {
            p.removeAllViewsInLayout();
        }
        return mRootView;
    }
    private void init(){
        receiveBroadCast = new ReceiveBroadCast();
        IntentFilter filter = new IntentFilter();
        filter.addAction("HHHH");    //只有持有相同的action的接受者才能接收此广播
        mContext.registerReceiver(receiveBroadCast, filter);
    }

    class ReceiveBroadCast extends BroadcastReceiver
    {
        @Override
        public void onReceive(Context context, Intent intent)
        {
            //得到广播中得到的数据，并显示出来
            Random random1 = new Random(SharePreferenceManager.getCachedSeed()+2);
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
            String hhh;
            Random random2 = new Random(SharePreferenceManager.getCachedSeed()+12);
            if (JGApplication.randomFlag) {
                Select mSelect = new Select();
                List<SignEntry> signEntries = mSelect.from(SignEntry.class).execute();
                hhh = signEntries.get(random2.nextInt(signEntries.size())).getSign();
            }else {
                hhh = JGApplication.matchSignsBeans.get(random2.nextInt(JGApplication.matchSignsBeans.size())).getSentence();
            }
            mRightSign.setText(hhh);
        }
    }
    @Override
    public void onDestroy() {
        mContext.unregisterReceiver(receiveBroadCast);
        super.onDestroy();
    }

}
