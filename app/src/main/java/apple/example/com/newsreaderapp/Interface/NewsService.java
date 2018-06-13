package apple.example.com.newsreaderapp.Interface;

import apple.example.com.newsreaderapp.Model.WebSite;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsService {

    @GET("v2/sources")
    Call<WebSite> getSources(@Query("language") String language,@Query("api_key") String apiKey);
}
