package jiguang.chat.activity.fragment_twice;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.BroadcastReceiver;
import android.widget.TextView;

import com.activeandroid.query.Select;

import java.util.List;
import java.util.Random;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.callback.GetUserInfoCallback;
import cn.jpush.im.android.api.model.UserInfo;
import jiguang.chat.R;

import jiguang.chat.activity.fragment.AFragment;
import jiguang.chat.activity.fragment.BaseFragment;
import jiguang.chat.application.JGApplication;
import jiguang.chat.controller.AAController;
import jiguang.chat.database.CatchUserEntry;
import jiguang.chat.database.SignEntry;
import jiguang.chat.utils.SharePreferenceManager;
import jiguang.chat.view.AAView;

public class AAFragment extends BaseFragment {
    private View mRootView;
    public AAView mAAView;
    private Context mContext;
    private AAController mAAController;
    private ReceiveBroadCast receiveBroadCast;
    private TextView mPipeiName;
    private TextView mRightSign;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this.getActivity();
        init();
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        mRootView = layoutInflater.inflate(R.layout.fragment_aa,
                (ViewGroup)getActivity().findViewById(R.id.mresult_view),false);
        mAAView = mRootView.findViewById(R.id.aa_view);
        mAAView.initModule();
        mAAController = new AAController(this,mAAView);
        mAAView.setListener(mAAController);
    }

    private void init(){
        receiveBroadCast = new ReceiveBroadCast();
        IntentFilter filter = new IntentFilter();
        filter.addAction("HHHH");    //只有持有相同的action的接受者才能接收此广播
        mContext.registerReceiver(receiveBroadCast, filter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       mRightSign = mRootView.findViewById(R.id.right_msg_2a);
       mPipeiName = mRootView.findViewById(R.id.pipeiname_2a);
        ViewGroup p = (ViewGroup) mRootView.getParent();
        if (p != null) {
            p.removeAllViewsInLayout();
        }
        return mRootView;
    }


    class ReceiveBroadCast extends BroadcastReceiver
    {
        @Override
        public void onReceive(Context context, Intent intent)
        {
            Log.d("receice 及哈哈哈", "onReceive: ");
            //得到广播中得到的数据，并显示出来
            Random random1 = new Random(SharePreferenceManager.getCachedSeed());
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
            Random random2 = new Random(SharePreferenceManager.getCachedSeed() + 10);
            if (JGApplication.randomFlag) {
                Log.d("true", "onReceive: ");
                Select mSelect = new Select();
                List<SignEntry> signEntries = mSelect.from(SignEntry.class).execute();
                hhh = signEntries.get(random2.nextInt(signEntries.size())).getSign();
            }else {
                Log.d("false", "onReceive: "+JGApplication.matchSignsBeans.size()+"  ");
                Log.d("ssss", "onReceive: "+random2.nextInt(JGApplication.matchSignsBeans.size()));
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
