package jiguang.chat.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import jiguang.chat.R;
import jiguang.chat.activity.LikeActivity;
import jiguang.chat.activity.MResultActivity;
import jiguang.chat.application.JGApplication;
import jiguang.chat.model.Sign_like;

public class SignLikeAdapter extends RecyclerView.Adapter<SignLikeAdapter.ViewHolder> {
           private List<Sign_like> mSign_likes;
           private OnItemClickListener mOnItemClickListener;
           private Context mContext;
        public static interface OnItemClickListener {
              void onItemClick(View view,String string);
       }
       public SignLikeAdapter setClickLsiter(OnItemClickListener lsiter){
          this.mOnItemClickListener = lsiter;
          return this;
       }
           static class ViewHolder extends RecyclerView.ViewHolder{
               ImageView  mImageView;
               TextView mTextView;
               public ViewHolder(View view){
                   super(view);
                   mImageView = view.findViewById(R.id.like_image);
                   mTextView = view.findViewById(R.id.like_string);
               }
           }

           public SignLikeAdapter(Context context,List<Sign_like> sign_likes){
               mContext = context;
               mSign_likes = sign_likes;
           }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
         View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fruit_item,null,false);
         ViewHolder holder = new ViewHolder(view);
         return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
          final Sign_like sign_like = mSign_likes.get(position);
          holder.mImageView.setImageResource(sign_like.getImageId());
          holder.mTextView.setText(sign_like.getText()+"                                                                 ");
          holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(v,sign_like.getText());
                Log.d("得多大噢", "onClick: "+sign_like.getText());
                Intent intent  = new Intent(mContext,MResultActivity.class);
                JGApplication.mySign =sign_like.getText();
                JGApplication.pageChoose = 'A';
                mContext.startActivity(intent);
            }
        });

    }
    @Override
    public int getItemCount() {
        return mSign_likes.size();
    }
}
