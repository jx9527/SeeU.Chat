package jiguang.chat.activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.activeandroid.query.Select;
import java.util.ArrayList;
import java.util.List;
import jiguang.chat.R;
import jiguang.chat.adapter.SignLikeAdapter;
import jiguang.chat.database.SignEntry;
import jiguang.chat.model.Sign_like;

public class LikeActivity extends BaseActivity {
     private List<Sign_like> mSign_likes = new ArrayList<>();
     private String TAG = "JAJJAJAJA";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
        initSignLike();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView_Sign);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new HotFgItemDecoration());
        SignLikeAdapter adapter = new SignLikeAdapter(LikeActivity.this,mSign_likes);

        adapter.setClickLsiter(new SignLikeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, String string) {
                Log.d("LikeActivity",  "LikeActivity");
            }
        });
        recyclerView.setAdapter(adapter);
    }
    public void initSignLike(){
        initTitle(true, true, "喜欢的句子", "", false, "");
        Select  select = new Select();
        //在数据库里边查找喜欢的，并显示
        List<SignEntry> signEntries = select.from(SignEntry.class).where("cs=?",1).execute();
        for(int i = 0;i<signEntries.size();i++){
            String mmm = signEntries.get(i).getSign();
            Sign_like sign_like = new Sign_like(mmm,R.drawable.match_up);
            mSign_likes.add(sign_like);
        }
    }

    public class HotFgItemDecoration extends RecyclerView.ItemDecoration{
        @Override
        public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
            super.onDraw(c, parent, state);
        }
        @Override
        public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
            super.onDrawOver(c, parent, state);
        }
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            /*item距离上20px,距离左右各16px*/
            outRect.top = 20;
            outRect.right = 16;
            outRect.left = 16;
        }
    }
}
