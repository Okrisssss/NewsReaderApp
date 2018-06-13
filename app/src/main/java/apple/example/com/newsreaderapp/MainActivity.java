package apple.example.com.newsreaderapp;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;

import apple.example.com.newsreaderapp.Adapter.ListSourceAdapter;
import apple.example.com.newsreaderapp.Common.Common;
import apple.example.com.newsreaderapp.Interface.IconBetterIdeaService;
import apple.example.com.newsreaderapp.Interface.NewsService;
import apple.example.com.newsreaderapp.Model.IconBetterIdea;
import apple.example.com.newsreaderapp.Model.Source;
import apple.example.com.newsreaderapp.Model.WebSite;
import dmax.dialog.SpotsDialog;
import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private  static final String TAG = MainActivity.class.getSimpleName();
    RecyclerView listWebsite;
    RecyclerView.LayoutManager layoutManager;
    NewsService mService;
    ListSourceAdapter adapter;
    SpotsDialog dialog;
    SwipeRefreshLayout swipeRefreshLayout;
    private static final String API_KEY = "0e3e1ce2505e43b29069e15bed5d8609";

    private Context context;
    private WebSite webSite;
    private IconBetterIdea iconBetterIdea;
    IconBetterIdea mIconBetterIdeaService;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RecyclerView recyclerView = (RecyclerView)findViewById(R.id.list_source);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Init cache
        Paper.init(this);
        //Init Service
        mService = Common.getNewsService();
        //Init view
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefrersh);
        listWebsite = (RecyclerView) findViewById(R.id.list_source);
        listWebsite.setHasFixedSize(true);
        layoutManager =  new LinearLayoutManager(this);
        listWebsite.setLayoutManager(layoutManager);
        dialog = new SpotsDialog(this);
        loadWebsiteSource(false);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadWebsiteSource(true);
            }
        });
    }

    private void loadWebsiteSource(boolean isRefreshed) {
    if (!isRefreshed){
        String cache = Paper.book().read("cache");
        if (cache != null && !cache.isEmpty() && !cache.equals("null")){

            WebSite webSite = new Gson().fromJson(cache, WebSite.class); // Convert cache from GSON to Object
            adapter = new ListSourceAdapter(getBaseContext(), webSite);
            adapter.notifyDataSetChanged();
            listWebsite.setAdapter(adapter);
        }
        else {

        }
        dialog.show();
        mService.getSources("en",API_KEY).enqueue(new Callback<WebSite>() {
            @Override
            public void onResponse(Call<WebSite> call, Response<WebSite> response) {
                adapter = new ListSourceAdapter(getBaseContext(), response.body());
                adapter.notifyDataSetChanged();
                listWebsite.setAdapter(adapter);

                //Save to cache
                Paper.book().write("cache", new Gson().toJson(response.body()));


            }

            @Override
            public void onFailure(Call<WebSite> call, Throwable t) {

            }
        });
        }
        else { //If from Swipe to Refresh
        dialog.show();
        mService.getSources("en",API_KEY).enqueue(new Callback<WebSite>() {
            @Override
            public void onResponse(Call<WebSite> call, Response<WebSite> response) {
                adapter = new ListSourceAdapter(getBaseContext(), response.body());
                adapter.notifyDataSetChanged();
                listWebsite.setAdapter(adapter);

                //Save to cache
                Paper.book().write("cache", new Gson().toJson(response.body()));

                //Dismiss refresh progressing
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<WebSite> call, Throwable t) {

            }
        });
    }
    }

    private void getDataFromWebsite(List sources) {

        if (API_KEY.isEmpty()){
            Toast.makeText(getApplicationContext(), "Please obtain API KEY fisrt!", Toast.LENGTH_SHORT).show();
            return;
        }

        NewsService newsService = Common.getNewsService();
        retrofit2.Call<WebSite> call = newsService.getSources("en",API_KEY);

        call.enqueue(new Callback<WebSite>() {
            @Override
            public void onResponse(Call<WebSite> call, Response<WebSite> response) {
                int statusCode = response.code();
                List<Source> sources = response.body().getSources();
            }

            @Override
            public void onFailure(Call<WebSite> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });

        StringBuilder iconBetterAPI = new StringBuilder("https://icons.better-idea.org/allicons.json?url=");
    iconBetterAPI.append(webSite.getSources().get(0).getUrl());




    }
    }

