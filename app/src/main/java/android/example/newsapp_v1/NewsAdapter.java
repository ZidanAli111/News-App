package android.example.newsapp_v1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class NewsAdapter  extends ArrayAdapter<News> {
    TextView sectionNameView;
    TextView titleView;
    TextView dateView;
    TextView timeView;


    public NewsAdapter( Context context, List<News> objects) {
        super(context, 0, objects);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View newsItemView=convertView;

        if (newsItemView==null)
        {
            newsItemView= LayoutInflater.from(getContext()).
                    inflate(R.layout.news_list,parent,false);

        }

        News currentNews=getItem(position);

        sectionNameView=newsItemView.findViewById(R.id.section_name);
        titleView=newsItemView.findViewById(R.id.newsTitle);
        dateView=newsItemView.findViewById(R.id.date);
        timeView=newsItemView.findViewById(R.id.time);



        Date dateobj=new Date(currentNews.getmDate());

        String sectionName=currentNews.getmSectionName();
         String title=currentNews.getmTitle();

         String date=formatDate(dateobj);
         String time=formatTime(dateobj);


         sectionNameView.setText(sectionName);

         titleView.setText(title);
         dateView.setText(date);
         timeView.setText(time);


        return newsItemView;
    }

    private String formatTime(Date dateobj) {

        SimpleDateFormat sd = new SimpleDateFormat("LLL dd,yyyy");
        return sd.format(dateobj);
    }

    private String formatDate(Date dateobj) {

        SimpleDateFormat st = new SimpleDateFormat("h :mm a");
        return st.format(dateobj);

    }
}
