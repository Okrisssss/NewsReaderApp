package apple.example.com.newsreaderapp.Interface;

import apple.example.com.newsreaderapp.Model.IconBetterIdea;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface IconBetterIdeaService {


    @GET("allicons.json")
    Call<IconBetterIdea> getIconUrl(@Query("Url") String url);
}
