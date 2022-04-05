package com.example.registration.RetrofitAPI.api;

import com.example.registration.RetrofitAPI.interfaces.IRetrofitContract;

import java.io.IOException;
import java.net.URI;
import java.util.concurrent.TimeUnit;

import okhttp3.CertificatePinner;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitFactory {
    private static Retrofit retrofit;
    private static Retrofit mRetrofitBuilder;
    private static RetrofitFactory retrofitFactory;
    private static IRetrofitContract iRetrofitContract;

    private RetrofitFactory() {
        super();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://d1-slp-api.supremelifeplatform.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();

    }
//        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
//
//        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .connectTimeout(2, TimeUnit.MINUTES)
//                .readTimeout(300, TimeUnit.SECONDS)
//                .writeTimeout(300, TimeUnit.SECONDS)
//                .addInterceptor(loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY))
//                .certificatePinner(new CertificatePinner.Builder().add("https://d1-slp-api.supremelifeplatform.com","sha256/JSMzqOOrtyOT1kmau6zKhgT676hGgczD5VMdRMyJZFA=").build())
//                .addInterceptor(new Interceptor() {
//
//                    @Override
//                    public okhttp3.Response intercept(Chain chain) throws IOException {
//
//                        Request request = chain.request();
//                        okhttp3.Response response = chain.proceed(request);
//
//                        //TODO handle it
//                        if (response.code() == 401) {
//                        }
//                        else if (response.code() == 500) {
//                            //TODO
//                            System.out.print("Retrofit server error : " + response.body());
//                        } else if (response.code() == 404) {
//                            //TODO
//                            System.out.print("Retrofit not found error : " + response.body());
//                        } else {
//                            //TODO
//                            System.out.print("Retrofit unknown error : " + response.body());
//                        }
//
//                        return response;
//                    }
//                })
//                .build();



    public static synchronized RetrofitFactory getInstance(){
        if(retrofitFactory==null){
            retrofitFactory=new RetrofitFactory();
        }
        return retrofitFactory;
    }

    public IRetrofitContract getApi(){
        return retrofit.create(IRetrofitContract.class);
    }

    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
    OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(2, TimeUnit.MINUTES)
            .readTimeout(300, TimeUnit.SECONDS)
            .writeTimeout(300, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY))
            .certificatePinner(new CertificatePinner.Builder().add("https://d1-slp-api.supremelifeplatform.com/").build())
            .addInterceptor(new Interceptor() {

                @Override
                public okhttp3.Response intercept(Chain chain) throws IOException {

                    Request request = chain.request();
                    okhttp3.Response response = chain.proceed(request);

                    //TODO handle it
                    if (response.code() == 401) {
                    }
                    else if (response.code() == 500) {
                        //TODO
                        System.out.print("Retrofit server error : " + response.body());
                    } else if (response.code() == 404) {
                        //TODO
                        System.out.print("Retrofit not found error : " + response.body());
                    } else {
                        //TODO
                        System.out.print("Retrofit unknown error : " + response.body());
                    }

                    return response;
                }
            })
            .build();




    public IRetrofitContract getRetrofitContract(String baseUrl) {
        iRetrofitContract = getRetrofitBuilder(baseUrl).create(IRetrofitContract.class);
        return iRetrofitContract;
    }

    private Retrofit getRetrofitBuilder(String baseUrl) {
        if (mRetrofitBuilder == null) {
            mRetrofitBuilder = new retrofit2.Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return mRetrofitBuilder;
    }

}
