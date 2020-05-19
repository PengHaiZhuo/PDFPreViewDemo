package com.phz.pdfpreviewdemo;

import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.phz.pdfpreviewdemo.config.Constract;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private LinearLayout ll_container;

    /**
     * 通常都是使用addView添加WebView,这样可以避免内存泄漏几率
     */
    private WebView webView;

    private static final String PDF_URL="https://download.brother.com/welcome/docp000648/cv_pt3600_schn_sig_lad962001.pdf";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ll_container=findViewById(R.id.ll_container);
        initWebView();

    }

    private void initWebView(){
        webView = new WebView(this);
        ViewGroup.LayoutParams layoutParams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        //给一个布局添加子布局
        ll_container.addView(webView,layoutParams);
        WebSettings webSettings = webView.getSettings();
        //不显示滑动条
        webView.setHorizontalScrollBarEnabled(false);
        //不显示滑动条
        webView.setVerticalScrollBarEnabled(false);
        //支持javascript
        webSettings.setJavaScriptEnabled(true);
        //是否支持缩放（这个是前提）
        webSettings.setSupportZoom(true);
        //是否显示缩放工具（当上门那个成立，再设置这个，必要条件）
        webSettings.setBuiltInZoomControls(false);
        //设置此属性，可任意比例缩放
        webSettings.setUseWideViewPort(true);
        //设置充满全屏
        webSettings.setLoadWithOverviewMode(true);
        //把所有内容放到WebView组件等宽的一列中
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //不建议使用这个，从任何来源加载内容。5.1以上默认禁止了https和http混用 这是开启
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        webSettings.setAllowFileAccess(true);
        webSettings.setAllowFileAccessFromFileURLs(true);
        //能否访问来自于任何源的文件标识的URL
        webSettings.setAllowUniversalAccessFromFileURLs(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (webView != null) {
            ll_container.removeView(webView);
            webView.stopLoading();
            webView.getSettings().setJavaScriptEnabled(false);
            webView.clearHistory();
            webView.clearCache(true);
            webView.clearSslPreferences();
            webView.removeAllViews();
            webView.destroy();
            webView=null;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_eg, menu);
        //获取menuItem,然后可以通过menuItem的方法设置标题、隐藏、选中等等。
        MenuItem menuItemOne=menu.findItem(R.id.actionbar_menu_selection_one);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.actionbar_menu_selection_one:
                //挺好用的，就是需要科学上网才能用（手机需要翻墙）
                webView.loadUrl(Constract.PDF_GOOGLE_VIEWER_URL+PDF_URL);
                break;
            case R.id.actionbar_menu_selection_two:
                //挺多问题的，比如跨域、未能获取、请使用es5等
                webView.loadUrl(Constract.PDF_MOZILLA_VIEWER_URL+PDF_URL);
                break;
            case R.id.actionbar_menu_selection_three:
                //这个不错，手动把跨域的判断屏蔽了，所以基本能行。有网页开发基础的可以自己修改asset下pdf_js里对应页面。
                webView.loadUrl(Constract.PDF_JS_URL+PDF_URL);
                break;
            case android.R.id.edit:
                //这里就是判断的安卓自带的控件id
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onOptionsMenuClosed(Menu menu) {
        super.onOptionsMenuClosed(menu);
    }
}
