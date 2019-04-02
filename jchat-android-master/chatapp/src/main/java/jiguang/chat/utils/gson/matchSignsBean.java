package jiguang.chat.utils.gson;

public class matchSignsBean {
    private  int sentenceId;
    private String sentence;
    matchSignsBean(){
    }
    public int getSentenceId() {
        return sentenceId;
    }
    public void setSentenceId(int sentenceId) {
        this.sentenceId = sentenceId;
    }
    public String getSentence() {
        return sentence;
    }
    public void setSentence(String sentence) {
        this.sentence = sentence;
    }
}