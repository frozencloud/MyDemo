package com.fc.mydemo.activity.annotations;

import android.content.Context;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Post;
import org.androidannotations.annotations.rest.Rest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;
import java.net.URI;

/**
 * Created by sea on 2016/1/7.
 * 官方说明：https://github.com/excilys/androidannotations/wiki/Rest-API#rest
 * REST API interface是基于Spring for Android的，所有需要引入相关jar包
 * rootUrl：请求的网络地址，可以省略，但是需要在每个方法上都添加完整的请求路径
 * converters：转换器结果数据类型，有Spring提供，一般有String、JSON等类型，
 * 可以为两个值以都好分割，当第一种解析失败时使用第二种
 * interceptors：拦截器，
 * requestFactory：
 * responseErrorHandler：4.0版本引入的新属性，目前不能用
 */
//第一种写法
//@Rest(rootUrl = "http://www.baidu.com", converters = {StringHttpMessageConverter.class}
// , interceptors = { HttpBasicAuthenticatorInterceptor.class }
//  , responseErrorHandler  = MyResponseErrorHandler.class)
//第二种写法
//@Rest(converters = {StringHttpMessageConverter.class}
//        , interceptors = {HttpBasicAuthenticatorInterceptor.class}
//        , requestFactory = MyRequestFactory.class)
//官方示例
@Rest(rootUrl = "http://company.com/ajax/services"
        , converters = {MappingJackson2HttpMessageConverter.class})
public interface MyRestClient {

    @Get("/events/{year}/{location}")
    String getEventsByYearAndLocation( int year,  String location);

    @Get("/events/{year}/{location}")
    String getEventsByLocationAndYear( String location,  int year);

    @Get("http://www.baidu.com")
    String getBaidu();

    @Post("")
    String getHotelList();

    @Post("/events")
    void addEvent();
}

class HttpBasicAuthenticatorInterceptor implements ClientHttpRequestInterceptor {
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] data, ClientHttpRequestExecution execution) throws IOException {
        // do something
        return execution.execute(request, data);
    }
}

@EBean
class MyRequestFactory implements ClientHttpRequestFactory {
    @RootContext
    Context context;

    @Override
    public ClientHttpRequest createRequest(URI uri, HttpMethod httpMethod) throws IOException {
        return null;
    }

    // ...
}

class MyResponseErrorHandler implements ResponseErrorHandler {
    public MyResponseErrorHandler() {
    }

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return false;
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
    }
    // ...
}