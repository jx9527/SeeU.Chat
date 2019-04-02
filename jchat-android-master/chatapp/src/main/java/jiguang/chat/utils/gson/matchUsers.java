package jiguang.chat.utils.gson;

public class matchUsers {

    private int mUserId;
    private String mUserName;
    private int mSendMessage = 0; //默认是0
    public matchUsers(int userId,String userName){
        this.mUserId = userId;
        this.mUserName = userName;
    }
    public int getUserId() {
        return mUserId;
    }

    public void setUserId(int userId) {
        mUserId = userId;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        mUserName = userName;
    }

    public int getmSendMessage() {
        return mSendMessage;
    }

    public void setmSendMessage(int mSendMessage) {
        this.mSendMessage = mSendMessage;
    }
}
