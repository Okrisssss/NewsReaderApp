package apple.example.com.newsreaderapp.Common;

import apple.example.com.newsreaderapp.Interface.IconBetterIdeaService;
import apple.example.com.newsreaderapp.Interface.NewsService;
import apple.example.com.newsreaderapp.Model.IconBetterIdea;
import apple.example.com.newsreaderapp.Remote.IconBetterIdeaClient;
import apple.example.com.newsreaderapp.Remote.RetrofitClient;

public class Common {
    private static final String BASE_URL = "https://newsapi.org/";
    private static final String BASE_URL_1 = "https://icons.better-idea.org/";
    public static final String API_KEY = "0e3e1ce2505e43b29069e15bed5d8609";

    public static NewsService getNewsService(){

        return RetrofitClient.getClient(BASE_URL).create(NewsService.class);
    }

    public static IconBetterIdeaService getIconService(){

        return RetrofitClient.getClient(BASE_URL_1).create(IconBetterIdeaService.class);
    }
}
