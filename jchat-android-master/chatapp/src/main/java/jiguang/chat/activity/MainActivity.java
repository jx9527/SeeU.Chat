package jiguang.chat.activity;

import android.Manifest;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.Toast;

import com.takusemba.spotlight.OnSpotlightStateChangedListener;
import com.takusemba.spotlight.Spotlight;
import com.takusemba.spotlight.shape.Circle;
import com.takusemba.spotlight.target.SimpleTarget;
import com.tbruyelle.rxpermissions.RxPermissions;

import cn.jiguang.api.JCoreInterface;
import jiguang.chat.R;
import jiguang.chat.controller.MainController;
import jiguang.chat.utils.HttpConnect;
import jiguang.chat.utils.SharePreferenceManager;
import jiguang.chat.view.MainView;
import rx.functions.Action1;

public class MainActivity extends FragmentActivity {

    private MainController mMainController;
    private MainView mMainView;
    private long mWaitTime=2000;
    private long mTochTime=0;
    private Button one;
    private Button two;
    private Button  three;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        two = findViewById(R.id.actionbar_pipei_btn);
        three = findViewById(R.id.actionbar_me_btn);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (SharePreferenceManager.getCachedPermission() == 0) {
                MultPermission();
                SharePreferenceManager.setCachedPermission(1);
            }
        }
        mMainView =  findViewById(R.id.main_view);
        mMainView.initModule();
        mMainController = new MainController(mMainView, this);
        mMainView.setOnClickListener(mMainController);
        mMainView.setOnPageChangeListener(mMainController);

    }

    public FragmentManager getSupportFragmentManger() {
        return getSupportFragmentManager();
    }

    @Override
    protected void onPause() {
        JCoreInterface.onPause(this);
        super.onPause();
    }

    @Override
    protected void onResume() {
        JCoreInterface.onResume(this);
        mMainController.sortConvList();
        super.onResume();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(SharePreferenceManager.getCachedRefresh() == 0) {
            //fist tatget
            one = findViewById(R.id.actionbar_msg_btn);
            int oneLocation[] = {0, 0};
            one.getLocationInWindow(oneLocation);
            float oneX = oneLocation[0] + one.getWidth() / 2f;
            float oneY = oneLocation[1] + one.getHeight() / 2f;
            SimpleTarget firstTarget = new SimpleTarget.Builder(this)
                    .setPoint(oneX, oneY)
                    .setShape(new Circle(100f))
                    .setTitle("会话")
                    .setDescription("会话聊天列表")
                    .build();
            //second target
            two = findViewById(R.id.actionbar_me_btn);
            int twoLocation[] = {0, 0};
            two.getLocationInWindow(twoLocation);
            float twoX = twoLocation[0] + two.getWidth() / 2f;
            float twoY = twoLocation[1] + two.getHeight() / 2f;
            SimpleTarget secondTarget = new SimpleTarget.Builder(this)
                    .setPoint(twoX, twoY)
                    .setShape(new Circle(100f))
                    .setTitle("个人主页")
                    .setDescription("展示个人信息")
                    .build();
            //third target
            three = findViewById(R.id.actionbar_pipei_btn);
            int threeLocation[] = {0, 0};
            three.getLocationInWindow(threeLocation);
            float threeX = threeLocation[0] + three.getWidth() / 2f;
            float threeY = threeLocation[1] + three.getHeight() / 2f;
            SimpleTarget thirdTarget = new SimpleTarget.Builder(this)
                    .setPoint(threeX, threeY)
                    .setShape(new Circle(100f))
                    .setTitle("匹配")
                    .setDescription("挑选自己喜欢的句子和她/他挑选的句子进行比较，相似度越高，匹配到心仪的人的概率就越高，快来匹配吧!")
                    .build();

            Spotlight.with(this)
                    .setOverlayColor(R.color.background)
                    .setDuration(1000L)
                    .setAnimation(new DecelerateInterpolator(2f))
                    .setTargets(firstTarget, secondTarget, thirdTarget)
                    .setClosedOnTouchedOutside(true)
                    .setOnSpotlightStateListener(new OnSpotlightStateChangedListener() {
                        @Override
                        public void onStarted() {
                            //   Toast.makeText(MainActivity.this, "spotlight is started", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onEnded() {
                            //    Toast.makeText(MainActivity.this, "spotlight is ended", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .start();
            SharePreferenceManager.setCachedRefresh(1);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

public boolean onKeyDown(int keyCode, KeyEvent event) {
    if (event.getAction() == KeyEvent.ACTION_DOWN
            && KeyEvent.KEYCODE_BACK == keyCode) {
        long currentTime = System.currentTimeMillis();
        if ((currentTime - mTochTime) >= mWaitTime) {
            //"再按返回键退出应用！"
            Toast.makeText(this, "再按一次退出系统", Toast.LENGTH_SHORT).show();
            mTochTime = currentTime;
        } else {
            finish();
            if (GuideActivity.sActivity!=null){
                GuideActivity.sActivity.finish();
            }
        }
        return true;
    }
    return super.onKeyDown(keyCode, event);
  }

    /**同时请求多个权限（合并结果）的情况*/
    private void MultPermission(){
        RxPermissions rxPermissions = new RxPermissions(MainActivity.this); // where this is an Activity instance
        rxPermissions.request(Manifest.permission.CAMERA,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.RECORD_AUDIO,
                 Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION)//权限名称，多个权限之间逗号分隔开
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        if(aBoolean) Toast.makeText(MainActivity.this,"权限申请成功",Toast.LENGTH_SHORT).show();
                        else Toast.makeText(MainActivity.this,"权限申请失败，请去权限设置页面",Toast.LENGTH_SHORT).show();
                    }
                });
    }

}
