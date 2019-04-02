package jiguang.chat.activity.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.activeandroid.query.Select;

import java.util.List;
import java.util.Random;

import jiguang.chat.R;
import jiguang.chat.application.JGApplication;
import jiguang.chat.controller.EController;
import jiguang.chat.database.SignEntry;
import jiguang.chat.utils.SharePreferenceManager;
import jiguang.chat.view.EView;

public class EFragment extends BaseFragment{

    private View mRootView;
    private EView mEView;
    private Context mContext;
    private EController mEController;
    private TextView mTextView;
    private EFragment.ReceiveBroadCast receiveBroadCast;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this.getActivity();
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        mRootView = layoutInflater.inflate(R.layout.fragment_e,
                (ViewGroup)getActivity().findViewById(R.id.mresult_view),false);
        mEView = mRootView.findViewById(R.id.eview);
        mEView.initModule();
        mEController = new EController(this,mEView);
        mEView.setListener(mEController);
        init();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mTextView = mRootView.findViewById(R.id.left_msg_1e);
        ViewGroup p = (ViewGroup) mRootView.getParent();
        if (p != null) {
            p.removeAllViewsInLayout();
        }
        return mRootView;
    }

    @Override
    public void onResume() {
        mEView.setImgColor();
        super.onResume();
    }

    private void init(){
        receiveBroadCast = new EFragment.ReceiveBroadCast();
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.gasFragment");    //只有持有相同的action的接受者才能接收此广播
        mContext.registerReceiver(receiveBroadCast, filter);
    }

    class ReceiveBroadCast extends BroadcastReceiver
    {
        @Override
        public void onReceive(Context context, Intent intent)
        {
            //得到广播中得到的数据，并显示出来
            Select select = new Select();
            List<SignEntry> signEntries = select.from(SignEntry.class).execute();
            Random random1 = new Random(SharePreferenceManager.getCachedSeed()+4);
            JGApplication.e = random1.nextInt(signEntries.size());
            String hhh = signEntries.get(JGApplication.e).getSign();
            mTextView.setText(hhh);
        }
    }

    @Override
    public void onDestroy() {
        mContext.unregisterReceiver(receiveBroadCast);
        super.onDestroy();
    }
}
