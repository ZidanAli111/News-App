package android.example.newsapp_v1;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class NewsAdapter extends ArrayAdapter<News> {
    TextView sectionNameView;
    TextView titleView;
  TextView publicationDate;
    ImageView thumbnailView;


    public NewsAdapter(Context context, List<News> objects) {
        super(context, 0, objects);
    }




    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View newsItemView = convertView;

        if (newsItemView == null) {
            newsItemView = LayoutInflater.from(getContext()).
                    inflate(R.layout.news_list, parent, false);

        }

        News currentNews = getItem(position);

        if (currentNews != null) {


            publicationDate= (TextView) newsItemView.findViewById(R.id.pdt);


            publicationDate.setText(formatPublishTime(currentNews.getmDateTime()));


            Log.i("DATENEWS","THE PUBLICATION DATE IS:"+currentNews.getmDateTime());

            sectionNameView = newsItemView.findViewById(R.id.section_name);
            sectionNameView.setText(currentNews.getmSectionName());

            titleView = newsItemView.findViewById(R.id.newsTitle);
            titleView.setText(currentNews.getmTitle());


            thumbnailView = newsItemView.findViewById(R.id.article_thumbnail_image_view);

            if (!currentNews.getmThumbnail().isEmpty()) {
                Glide.with(getContext()).load(currentNews.getmThumbnail()).into(thumbnailView);

            } else {
                thumbnailView.setBackgroundResource(R.drawable.news);
            }

        }
        return newsItemView;

    }


    private String formatPublishTime(final String time) {
        String rTime = "N.A.";
        if ((time != null) && (!time.isEmpty())) {
            try {
                SimpleDateFormat currentSDF = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
                SimpleDateFormat newSDF = new SimpleDateFormat("yyyy.MM.dd / HH:mm");//("MM dd, yyyy");
                rTime = newSDF.format(currentSDF.parse(time));
            } catch (ParseException parseEx) {
                rTime = "N.A.";
                Log.e("SIGMA", "Error while parsing the published date", parseEx);
            }
        }

        return rTime;
    }




}
