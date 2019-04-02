package jiguang.chat.activity.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.activeandroid.query.Select;

import java.util.List;
import java.util.Random;

import jiguang.chat.R;
import jiguang.chat.application.JGApplication;
import jiguang.chat.controller.AAController;
import jiguang.chat.controller.AController;
import jiguang.chat.database.SignEntry;
import jiguang.chat.utils.SharePreferenceManager;
import jiguang.chat.view.AView;

public class AFragment extends Fragment {
    private View mRootView;
    private AView mAView;
    private Context mContext;
    public AController mAController;
    private TextView mTextView;
    private ReceiveBroadCast receiveBroadCast;
    private ImageView mImageView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this.getActivity();
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        mRootView = layoutInflater.inflate(R.layout.fragment_a,
                (ViewGroup)getActivity().findViewById(R.id.match_view),false);
        mAView = mRootView.findViewById(R.id.aview);
        mAView.initModule();
        mAController = new AController(this,mAView);
        mAView.setListener(mAController);
        init();
    }

    private void init(){
        receiveBroadCast = new ReceiveBroadCast();
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.gasFragment");    //只有持有相同的action的接受者才能接收此广播
        mContext.registerReceiver(receiveBroadCast, filter);
    }

//防止onCreatView被调用两次
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mTextView = mRootView.findViewById(R.id.left_msg_1a);
        mImageView = mRootView.findViewById(R.id.a_heart);
        ViewGroup p = (ViewGroup) mRootView.getParent();
        if (p != null) {
            p.removeAllViewsInLayout();
        }
        return mRootView;
    }

    @Override
    public void onResume() {
        mAView.setImgColor();
        super.onResume();
    }

    class ReceiveBroadCast extends BroadcastReceiver
    {
        @Override
        public void onReceive(Context context, Intent intent)
        {
            Select select = new Select();
            List<SignEntry> signEntries = select.from(SignEntry.class).execute();
            Random random1 = new Random(SharePreferenceManager.getCachedSeed());
            JGApplication.a = random1.nextInt(signEntries.size());
            String hhh = signEntries.get(JGApplication.a).getSign();
            mTextView.setText(hhh);
            mAView.setImgColor();
            }
        }

    @Override
    public void onDestroy() {
        Log.d("A的广播解绑", "onDestroy: "+"AAAA");
        mContext.unregisterReceiver(receiveBroadCast);
        super.onDestroy();
    }

}



