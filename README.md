# PDFPreViewDemo
使用WebView实现的远程PDF文件预览

具体查看博客[Android应用中预览pdf](https://blog.csdn.net/qq_16692517/article/details/106250733)

## 1. Google PDF Viewer
讲实话，谷歌的文档服务我认为是几种实现方式中最优雅的。不过，需要科学上网（翻墙），最终放弃了这种方式。

>重点是这个要拼接的url：https://drive.google.com/viewerng/viewer?embedded=true&url=

```
WebView webview = (WebView) findViewById(R.id.webview);
webview.getSettings().setJavaScriptEnabled(true); 
String pdf = "http://www.adobe.com/devnet/acrobat/pdfs/pdf_open_parameters.pdf";
webview.loadUrl("https://drive.google.com/viewerng/viewer?embedded=true&url=" + pdf);
```

## 2.mozilla PDF Viewer
挺好的，不需要翻墙。但是存在跨域问题。（由于浏览器的同源策略，即属于不同域的页面之间不能相互访问各自的页面内容）
>重点是这个要拼接的url：http://mozilla.github.io/pdf.js/web/viewer.html?file=
>还有一个： http://mozilla.github.io/pdf.js/es5/web/viewer.html?file=

```
//先设置WebView
webview.getSettings().setJavaScriptEnabled(true);
webview .getSettings().setAllowFileAccess(true);
webview .getSettings().setAllowFileAccessFromFileURLs(true);
//能否访问来自于任何源的文件标识的URL
webview .getSettings().setAllowUniversalAccessFromFileURLs(true);

webview .loadUrl("http://mozilla.github.io/pdf.js/web/viewer.html?file=" + pdfUrl);
```

### mozilla PDF Viewer整到项目里
```
//先设置WebView
webview.getSettings().setJavaScriptEnabled(true);
webview .getSettings().setAllowFileAccess(true);
webview .getSettings().setAllowFileAccessFromFileURLs(true);
//能否访问来自于任何源的文件标识的URL
webview .getSettings().setAllowUniversalAccessFromFileURLs(true);
webview .loadUrl("file:///android_asset/pdfjs/web/viewer.html?file="+pdfUrl);
```
#### 实现手势放大缩小html

查看viewer.html文件，找到
```
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
```
把最大缩放规模改成3.0，并添加属性user-scalable=yes：

```
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=3.0,user-scalable=yes">
```

#### 关于跨域检查
查看<font color="#dd0000">**viewer.js**</font>发现有一个**validateFileURL**变量。注释掉validateFileURL方法。

