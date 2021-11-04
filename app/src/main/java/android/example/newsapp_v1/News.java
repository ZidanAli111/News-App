package android.example.newsapp_v1;

public class News {

    private String mSectionName;

   private  String mDateTime;

    private String mTitle;

    private String mThumbnail;

    private String mUrl;

    public News(String sectionName, String title,String date, String thumbnail,String url) {
        this.mSectionName = sectionName;
       this.mDateTime = date;
        this.mThumbnail = thumbnail;
        this.mTitle = title;
        this.mUrl=url;
    }


    public String getmUrl() {
        return mUrl;
    }

    public String getmThumbnail() {
        return mThumbnail;
    }

    public String getmSectionName() {
        return mSectionName;
    }

    public String getmDateTime() {
        return mDateTime;
    }

    public String getmTitle() {
        return mTitle;
    }


}
