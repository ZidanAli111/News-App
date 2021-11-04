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

            String dateS= currentNews.getmDateTime().substring(0,10);

            String time=currentNews.getmDateTime().substring(11,16);

            publicationDate.setText(dateS+", "+time);


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




}
