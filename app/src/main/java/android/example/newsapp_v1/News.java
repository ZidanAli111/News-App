package android.example.newsapp_v1;

public class News {

    private String mSectionName;

    //  private  String mDateTime;

    private String mTitle;

    private String mThumbnail;

    public News(String sectionName, String title, String thumbnail) {
        this.mSectionName = sectionName;
        //  this.mDateTime = date;
        this.mThumbnail = thumbnail;
        this.mTitle = title;
    }


    public String getmThumbnail() {
        return mThumbnail;
    }

    public String getmSectionName() {
        return mSectionName;
    }

//    public String getmDateTime() {
//        return mDateTime;
//    }

    public String getmTitle() {
        return mTitle;
    }


}
