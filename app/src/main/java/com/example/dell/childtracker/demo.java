package com.example.dell.childtracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class demo extends AppCompatActivity
{
    WebView wv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        initWeb();
    }

    void initWeb()
    {
        wv=(WebView) findViewById(R.id.web);
        WebViewClient client=new WebViewClient();
        wv.setWebViewClient(client);

        wv.getSettings().setJavaScriptEnabled(true);

        String code= "<html >\n" +
                "<head>\n" +
                "  <meta charset=\"UTF-8\">\n" +
                "  <title>Login form on iPhone</title>\n" +
                "  \n" +
                "  <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css\">\n" +
                "\n" +
                "  \n" +
                "      <link rel=\"stylesheet\" href=\"css/style.css\">\n" +
                "\n" +
                "  \n" +
                "</head>\n" +
                "\n" +
                "<body>\n" +
                "  <!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "  <meta charset=\"utf-8\">\n" +
                "\t<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\">\n" +
                "\n" +
                "\t<title>Login form</title>\n" +
                "\t<meta name=\"description\" content=\"\">\n" +
                "\t<meta name=\"viewport\" content=\"width=device-width\">\n" +
                "</head>\n" +
                "<body>\n" +
                "\t<div class=\"iphone-wrap\">\n" +
                "\t\t<div class=\"login-wrap\">\n" +
                "\t\t\t<div class=\"login-form-wrap\">\n" +
                "\t\t\t\t<div class=\"card-holder-wrap\">\n" +
                "\t\t\t\t\t<div class=\"hole\"></div>\n" +
                "\t\t\t\t\t<div class=\"l-stroke\"></div>\n" +
                "\t\t\t\t\t<div class=\"r-stroke\"></div>\n" +
                "\t\t\t\t\t<div class=\"ring-large-wrap\"></div>\n" +
                "\t\t\t\t\t<div class=\"ring-small-wrap\"></div>\n" +
                "\t\t\t\t</div>\n" +
                "\t\t\t\t<form action=\"#\" class=\"login-form\">\n" +
                "\t\t\t\t\t<h1 class=\"freeb\"><img src=\"https://i.imgur.com/U4E6o3x.png\" alt=\"freebies gallery\"></h1>\n" +
                "\t\t\t\t\t<div class=\"input-wrap\">\n" +
                "\t\t\t\t\t\t<label for=\"\" class=\"user-id\"><input type=\"text\" name=\"userId\" placeholder=\"Enter your User ID\"></label> \n" +
                "\t\t\t\t\t\t<hr class=\"form-hr\">\n" +
                "\t\t\t\t\t\t<label for=\"\" class=\"password\"><input type=\"password\" name=\"password\" placeholder=\"Enter Your Password\"></label> \n" +
                "\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t<div class=\"remember\">\n" +
                "\t\t\t\t\t\t<span>Remember me</span>\n" +
                "\t\t\t\t\t\t<div class=\"switch\">\n" +
                "\t\t\t\t\t\t    <input type=\"checkbox\" id=\"switch\" class=\"switch-check\">\n" +
                "\t\t\t\t\t\t    <label for=\"switch\" class=\"switch-label\">\t\t\t\t   \n" +
                "\t\t\t\t\t    \t<span class=\"switch-slider switch-slider-on\"></span>\n" +
                "        \t\t\t\t\t<span class=\"switch-slider switch-slider-off\"></span></label>\n" +
                "\n" +
                "\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t<input type=\"submit\" class=\"button\" value=\"Login\">\n" +
                "\t\t\t\t\t<a href=\"#\" class=\"forgot\">Forgot Password?</a>\n" +
                "\t\t\t\t</form>\n" +
                "\t\t\t</div>\n" +
                "\t\t</div>\n" +
                "\t</div>\n" +
                "</body>\n" +
                "</html>\n" +
                "  \n" +
                "  \n" +
                "</body>\n" +
                "</html>\n";

        //String hi="<html><body><h3>HI</h3</body></html>";
        //wv.loadData(hi,"txt/html","utf-8");
        //wv.loadUrl("http://www.ndtv.com/");

        wv.loadUrl("file:///android_asset/index1.html");

    }
}
