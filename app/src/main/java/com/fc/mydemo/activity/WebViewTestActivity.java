package com.fc.mydemo.activity;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

import com.fc.mydemo.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_web_view_test)
public class WebViewTestActivity extends BaseActivity {

    @ViewById(R.id.webview)
    protected WebView webView;
    @ViewById(R.id.back)
    protected Button back;
    @ViewById(R.id.refresh)
    protected Button refresh;
    @ViewById(R.id.title)
    protected TextView titleView;
    @ViewById(R.id.error)
    protected TextView error;

    @AfterViews
    protected void init() {
//        webView.loadUrl("http://shouji.baidu.com/software/");
        webView.loadUrl("http://www.bing.com");
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                //设置标题
                titleView.setText(title);
                super.onReceivedTitle(view, title);
            }
        });

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                //返回值为true则用webView打开，返回false则用第三方浏览器打开
                return super.shouldOverrideUrlLoading(view, url);
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                //加载本地页面，处理错误码
                view.loadUrl("file:///android_asset/html/error.html");
                //通过textView处理错误码
                error.setText("404 error.");
                webView.setVisibility(View.GONE);
            }
        });
        webView.setDownloadListener(new MyDownload());
    }

    //WebView下载文件
    class MyDownload implements DownloadListener {
        @Override
        public void onDownloadStart(String url, String userAgent, String contentDisposition
                , String mimetype, long contentLength) {
            if (url.endsWith(".apk")) {
                //通过自定义线程下载apk文件
//                new HttpThread(url).start();
                //通过系统方式下载
                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        }
    }

    @Click({R.id.back, R.id.refresh, R.id.title})
    protected void click(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.refresh:
                webView.reload();
                break;
            case R.id.title:
                break;
            default:
                break;
        }
    }
}
