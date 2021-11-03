package android.example.newsapp_v1;

import android.content.Context;
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
//    TextView date_timeView;
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
//            date_timeView = newsItemView.findViewById(R.id.time);
//
//            date_timeView.setText("24 Oct 2021 , 10:30 AM");
        }
        return newsItemView;

    }




}
