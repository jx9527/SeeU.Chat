package jiguang.chat.activity;

import android.content.Intent;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.allure.lbanners.LMBanners;

import com.allure.lbanners.transformer.TransitionEffect;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.util.ArrayList;


import jiguang.chat.R;
import jiguang.chat.adapter.LocalImgAdapter;

public class GuideActivity extends AppCompatActivity {
    private LMBanners lmBanners;
    public static GuideActivity sActivity;
    //本地图片
    private ArrayList<Integer> localImages = new ArrayList<Integer>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //取消标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //取消状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_guide);
        sActivity = this;
        initImageLoader();
        addLocalImg();
        initGuide();
    }

    private void addLocalImg() {
        localImages.add(R.mipmap.img1);
        localImages.add(R.mipmap.img2);
        localImages.add(R.mipmap.img3);
    }


    private void initImageLoader() {
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder().
                showImageForEmptyUri(R.drawable.icon)
                .cacheInMemory(true).cacheOnDisk(true).build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                getApplicationContext()).defaultDisplayImageOptions(defaultOptions)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO).build();
        ImageLoader.getInstance().init(config);
    }
    private void initGuide() {
        lmBanners = (LMBanners) findViewById(R.id.banners);
        lmBanners.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        lmBanners.isGuide(true);
//        lmBanners.setVertical(true);
        lmBanners.setAutoPlay(true);
        lmBanners.setCanLoop(true);
        lmBanners.setScrollDurtion(1200);
        lmBanners.setIndicatorBottomPadding(30);
        lmBanners.setIndicatorWidth(10);
        lmBanners.setSelectIndicatorRes(R.drawable.guide_indicator_select);//选中的原点
        lmBanners.setUnSelectUnIndicatorRes(R.drawable.guide_indicator_unselect);//未选中的原点
        lmBanners.setHoriZontalTransitionEffect(TransitionEffect.Default);
       // lmBanners.setHoriZontalCustomTransformer(new ParallaxTransformer(R.id.id_image));
        lmBanners.setIndicatorPosition(LMBanners.IndicaTorPosition.BOTTOM_MID);
        //本地用法
        lmBanners.setAdapter(new LocalImgAdapter(GuideActivity.this), localImages);

        /**
         * 若btnBgColor为-1，则表示不需要任何背景色
         * textColor 文字颜色
         * 点击事件
         */
//        lmBanners.setOnStartListener(-1,getResources().getColor(R.color.colorAccent),****);
        lmBanners.setOnStartListener(R.drawable.button_shape,0XFFAACCBB,new LMBanners.onStartListener() {
            @Override
            public void startOpen() {
                //回调跳转的逻辑
                startActivity(new Intent(GuideActivity.this,WelcomeActivity.class));
            }
        });
    }
    @Override
    protected void onPause() {
        super.onPause();
        lmBanners.stopImageTimerTask();
    }

    @Override
    protected void onResume() {
        super.onResume();
        lmBanners.startImageTimerTask();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        lmBanners.clearImageTimerTask();
    }
   public void Hfinish(){
        finish();
   }
}
