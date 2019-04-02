package jiguang.chat.activity.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import jiguang.chat.R;
import jiguang.chat.controller.PiPeiController;
import jiguang.chat.view.PiPeiView;

public class PiPeiFragment extends BaseFragment {
    private View mRootView;
    public PiPeiView mPiPeiView;
    private PiPeiController mPiPeiController;
    private Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this.getActivity();
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        mRootView = layoutInflater.inflate(R.layout.fragment_pipei,
                (ViewGroup)getActivity().findViewById(R.id.main_view),false);
        mPiPeiView = mRootView.findViewById(R.id.PiPei_view);
        mPiPeiView.initModule();
        mPiPeiController = new PiPeiController(this);
        mPiPeiView.setListener(mPiPeiController);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup p = (ViewGroup) mRootView.getParent();
        if (p != null) {
            p.removeAllViewsInLayout();
        }
        return mRootView;
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}

