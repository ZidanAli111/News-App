package android.example.newsapp_v1;

public class News {

    private  String mSectionName;

    private  String mDate;

    private  String mTitle;

    public String getmSectionName() {
        return mSectionName;
    }

    public String getmDate() {
        return mDate;
    }

    public String getmTitle() {
        return mTitle;
    }

    public News(String mSectionName, String mDate, String mTitle) {
        this.mSectionName = mSectionName;
        this.mDate = mDate;
        this.mTitle = mTitle;
    }
}
