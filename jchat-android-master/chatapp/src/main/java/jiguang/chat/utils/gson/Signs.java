package jiguang.chat.utils.gson;


public class Signs  {
    private int mSentenceId;
    private String mSentence;
    public Signs(int sentenceId,String sentence){
        this.mSentenceId = sentenceId;
        this.mSentence = sentence;
    }
    public int getSentenceId() {
        return mSentenceId;
    }

    public void setSentenceId(int sentenceId) {
        mSentenceId = sentenceId;
    }

    public String getSentence() {
        return mSentence;
    }

    public void setSentence(String sentence) {
        mSentence = sentence;
    }
}
