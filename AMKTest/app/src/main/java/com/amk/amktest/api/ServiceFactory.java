package com.amk.amktest.api;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.amk.amktest.BuildConfig;
import com.amk.amktest.api.services.CollectionsService;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Jones on 29/09/17.
 */
public class ServiceFactory {
    private static final String TAG = "ServiceFactory";

    private static Retrofit.Builder mRetrofitBuilder;

    private ServiceFactory() {
    }

    /**
     * This method creates the builder and configures the interceptors, there's no need to modify the builder in the future.
     */
    private static void setRetrofit(@NonNull String baseUrl) {
        Log.d(TAG, "setRetrofit() called");
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        clientBuilder.readTimeout(15, TimeUnit.SECONDS);
        clientBuilder.connectTimeout(15, TimeUnit.SECONDS);

        Interceptor retryInterceptor = chain -> {
            Request request = chain.request();
            Response response = chain.proceed(request);
            int tryCount = 0;

            while (!response.isSuccessful() && tryCount < 5) {
                Log.d(TAG, "intercept: timeout/connection failure, performing automatic retry " + (tryCount + 1));
                tryCount++;
                response = chain.proceed(request);
            }

            return response;
        };

        Interceptor userAgentInterceptor = chain -> {
            Request originalRequest = chain.request();
            Request requestWithUserAgent = originalRequest.newBuilder()
                    .removeHeader("User-Agent")
                    .addHeader("User-Agent", "AMK-Test-" + Build.MODEL + "|" + Build.BRAND + "|" + Build.VERSION.SDK_INT)
                    .build();

            return chain.proceed(requestWithUserAgent);
        };

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);

        clientBuilder.addInterceptor(retryInterceptor);
        clientBuilder.addInterceptor(userAgentInterceptor);
        clientBuilder.addInterceptor(loggingInterceptor);

        mRetrofitBuilder = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(clientBuilder.build());

        Log.d(TAG, "setRetrofit() returned");
        Log.d(TAG, "setRetrofit: returned, but the base url is: " + baseUrl);
    }

    /**
     * Call this method to fast configure a instance of {@link Retrofit} and get your service configured.
     *
     * @param type       the Service that you want to assign
     * @param converters If you want to use {@link com.google.gson.Gson} or another converter, pass it as you second parameter.
     * @return an object that you can use asi your rest client. You can call the methods declared on serviceClass
     */
    public static <T> T getServiceInstance(Class<T> type, @Nullable Converter.Factory... converters) {
        Log.d(TAG, "getServiceInstance() called with: " + "type = [" + type + "], converters = [" + (converters == null ? null : converters.length) + "]");

        if (mRetrofitBuilder == null) {
            setRetrofit(BuildConfig.BASE_URL);
        }

        if (converters != null) {
            for (Converter.Factory factory : converters) {
                mRetrofitBuilder.addConverterFactory(factory);
            }
        }

        Log.d(TAG, "getServiceInstance() returned");
//        type = (T) mRetrofitBuilder.build().create(type.getClass());
        return mRetrofitBuilder.build().create(type);
    }

    /**
     * Call this method to fast configure a instance of {@link Retrofit} and get your service configured.
     *
     * @param type       the Service that you want to assign
     * @param baseUrl    the base url to use with this service
     * @param converters If you want to use {@link com.google.gson.Gson} or another converter, pass it as you second parameter.
     * @return an object that you can use asi your rest client. You can call the methods declared on serviceClass
     */
    public static <T> T getServiceInstance(Class<T> type, String baseUrl, @Nullable Converter.Factory... converters) {
        Log.d(TAG, "getServiceInstance() called with: " + "type = [" + type + "], converters = [" + (converters == null ? null : converters.length) + "]");

        if (mRetrofitBuilder == null) {
            setRetrofit(baseUrl);
        }

        if (converters != null) {
            for (Converter.Factory factory : converters) {
                mRetrofitBuilder.addConverterFactory(factory);
            }
        }

        Log.d(TAG, "getServiceInstance() returned");
//        type = (T) mRetrofitBuilder.build().create(type.getClass());
        return mRetrofitBuilder.build().create(type);
    }


}

