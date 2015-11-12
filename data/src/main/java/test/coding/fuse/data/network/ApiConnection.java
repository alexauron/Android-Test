package test.coding.fuse.data.network;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;


public class ApiConnection {

    private Retrofit retrofit;

    private ApiConnection(String url) {
        this.retrofit = new Retrofit.Builder().
                addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(url)
                .build();
    }

    public static ApiConnection createGET(String url) {
        return new ApiConnection(url);
    }

    public Object call(Class<?> serviceClass) {
        return retrofit.create(serviceClass);
    }
}

