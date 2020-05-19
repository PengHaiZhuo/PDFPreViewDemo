package com.phz.pdfpreviewdemo.config;

/**
 * @author haizhuo
 * @introduction 全局常量
 */
public interface Constract {
    /**
     * 火狐部署在github pages上的Viewer
     */
    /*String PDF_MOZILLA_VIEWER_URL="http://mozilla.github.io/pdf.js/web/viewer.html?file=";*/

    /**
     * 火狐部署在github pages上的Viewer(es5版)
     */
    String PDF_MOZILLA_VIEWER_URL="http://mozilla.github.io/pdf.js/es5/web/viewer.html?file=";

    /**
     * 调用本地pdf.js库-url加载前缀路径
     */
   String PDF_JS_URL="file:///android_asset/pdf_js/web/viewer.html?file=";

    /**
     * Google的PDF Viewer
     */
    String PDF_GOOGLE_VIEWER_URL="https://drive.google.com/viewerng/viewer?embedded=true&url=";
}
