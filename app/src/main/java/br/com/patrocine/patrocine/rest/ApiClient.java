package br.com.patrocine.patrocine.rest;

import br.com.patrocine.patrocine.BuildConfig;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static final String BASE_URL = BuildConfig.api_server;
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
