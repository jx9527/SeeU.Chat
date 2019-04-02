package jiguang.chat.model;

//用来listView渲染视图
public class Sign_like {
    private String mText;
    private int imageId;
    public Sign_like(String text,int imageId){
        this.mText = text;
        this.imageId = imageId;
    }
    public String getText(){
        return mText;
    }
    public int  getImageId(){
        return imageId;
    }
}
