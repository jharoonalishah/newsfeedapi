package com.codetask.alfutaim.dailynewsfeed.app.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.codetask.alfutaim.dailynewsfeed.ApplicationContext;
import com.codetask.alfutaim.dailynewsfeed.R;
import com.codetask.alfutaim.dailynewsfeed.app.adaptor.MainPageAdaptor;
import com.codetask.alfutaim.dailynewsfeed.app.constant.AppNewsApiCredentials;
import com.codetask.alfutaim.dailynewsfeed.app.interfacer.Callable;
import com.codetask.alfutaim.dailynewsfeed.app.model.Master;
import com.codetask.alfutaim.dailynewsfeed.core.activity.BaseActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainFeedActivity extends BaseActivity {
    // Progress bar
    ProgressBar pg;
    private static final String TAG = "NewsApp";
    ListView newsFeedListView ;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_feed);

        context = ApplicationContext.getContext();
        newsFeedListView = findViewById(R.id.newsFeedListView);
        pg =findViewById(R.id.progressBar);

        //Data source object
        final List<Master> masters = new ArrayList<>();


        //- Call to NewsApi
        newsApiCaller(masters);


        //- List item clicked event capturer
        newsFeedListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("Master", masters.get(i));
                startActivity(intent);
            }
        });


    }

    private void newsApiCaller(final List<Master> masters) {

        getAppNewsApiController().newsApiFeedCall(AppNewsApiCredentials.NEWS_API_URL, new Callable() {
            @Override
            public void onCompletion(JSONArray jsonArray) {

                for (int i=0; i < jsonArray.length(); i++) {
                    try{

                        Master master = new Master();
                        final JSONObject jsonObject = jsonArray.getJSONObject(i);

                        master.setTitle(jsonObject.get("title").toString());
                        master.setDescription(jsonObject.get("description").toString().substring(0, 100) + "...");
                        master.setImage(jsonObject.get("urlToImage").toString());
                        master.setPublished(jsonObject.get("publishedAt").toString());
                        master.setContent(jsonObject.get("content").toString());

                        masters.add(master);

                        Log.d(TAG, masters.toString());

                    }catch (Exception e){
                        Log.d("Error", String.valueOf(e.getCause()));
                    }

                }


                setNewsFeedAdaptor(masters);
            }
        });
    }

    public void refreshData(View view){
        pg.setVisibility(View.VISIBLE);
        //Data source object
        final List<Master> masters = new ArrayList<>();
        newsApiCaller(masters);
    }

    private void setNewsFeedAdaptor(List<Master> masters) {

        MainPageAdaptor mainPageAdaptor = new MainPageAdaptor(ApplicationContext.getContext(),masters);
        newsFeedListView.setAdapter(mainPageAdaptor);


        pg.setVisibility(View.INVISIBLE);

    }


}
