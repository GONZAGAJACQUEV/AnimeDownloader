/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.animedownloader.util.site;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

/**
 *
 * @author jdomugho
 */
public class SiteFetcher {

    private String url;
    private String characterEncoding = "UTF-8";
    private String redirectUrl;
    private String proxyaddress = null;

    private int statusCode = 0;

    private SiteParams params;
    private SiteCookies cookies;
    private HashMap<String, String> requestHeaders;

    private RequestConfig config;
    private CloseableHttpClient httpclient;
    private CloseableHttpResponse response = null;
    private HttpEntity entity;
    private String htmlContent;
    private InputStream responseAsInputStream;

    public SiteFetcher(String url) {
        this.url = url;

        initFetcher();
        initProxy();
    }

    public SiteFetcher(String url, String proxyaddress) {
        this.url = url;
        this.proxyaddress = proxyaddress;

        initFetcher();
        initProxy();
    }

    private void initProxy() {
        String strProxyAddress = "10.10.11.193:3128"; //local proxy
//        if (proxyaddress != null) {
//            strProxyAddress = this.proxyaddress;
//        }
        String p[] = strProxyAddress.split(":");
        HttpHost proxyfull = new HttpHost(p[0], Integer.parseInt(p[1]));
        config = RequestConfig.custom().setProxy(proxyfull).setConnectTimeout(30 * 1000).build();
    }

    private void initFetcher() {
        cookies = new SiteCookies();
        requestHeaders = new HashMap<>();
        requestHeaders.put("User-Agent", "Mozilla/5.0 (X11; Linux i686) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/37.0.2062.120 Safari/537.3");
    }

    public void get() throws IOException {
        httpclient = HttpClientBuilder.create().build();
        HttpClientContext context = HttpClientContext.create();

        if (params != null) {
            url += "?";
            params.getParams().stream().forEach((a) -> {
                try {
                    url += a.getName() + "=" + URLEncoder.encode(a.getValue(), characterEncoding) + "&";
                } catch (UnsupportedEncodingException ex) {
                }
            });
            url = url.substring(0, url.length() - 1);
        }

        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(config);

        requestHeaders.entrySet().stream().forEach((e) -> {
            httpGet.addHeader(e.getKey(), e.getValue());
        });

        context.setCookieStore(cookies.getCookieStore());

        response = httpclient.execute(httpGet, context);
        statusCode = response.getStatusLine().getStatusCode();
        setResponseHeader(response.getAllHeaders());

        if (statusCode == 302) {
            this.redirectUrl = response.getFirstHeader("Location").getValue();
        }

        entity = response.getEntity();
//        htmlContent = getStringFromInputStream(entity.getContent());
        responseAsInputStream = entity.getContent();

    }

    public void post() throws IOException {
        httpclient = HttpClientBuilder.create().build();

        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(config);

        requestHeaders.entrySet().stream().forEach((e) -> {
            httpPost.addHeader(e.getKey(), e.getValue());
        });

        HttpClientContext context = HttpClientContext.create();
        context.setCookieStore(cookies.getCookieStore());
        try {
            if (params != null) {
                httpPost.setEntity(new UrlEncodedFormEntity(params.getParams()));
            }

            response = httpclient.execute(httpPost, context);
            statusCode = response.getStatusLine().getStatusCode();
            setResponseHeader(response.getAllHeaders());

            if (statusCode == 302) {
                this.redirectUrl = response.getFirstHeader("Location").getValue();
            }

            entity = response.getEntity();
            responseAsInputStream = entity.getContent();
//            htmlContent = getStringFromInputStream(entity.getContent());

        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }
    }

    private String getStringFromInputStream(InputStream is) {

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            br = new BufferedReader(new InputStreamReader(is, characterEncoding));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();

    }

    private void setResponseHeader(Header[] headers) {
        for (Header header : headers) {
            String fullStr = header.toString();
            String key = fullStr.split(":")[0].trim();
            String val = fullStr.replace(key + ":", "").trim();
            requestHeaders.put(key, val);
        }
    }

    public HttpEntity getEntity() {
        return entity;
    }

    public String getHtmlContent() {
        return htmlContent;
    }

    public String getCharacterEncoding() {
        return characterEncoding;
    }

    public void setCharacterEncoding(String characterEncoding) {
        this.characterEncoding = characterEncoding;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public boolean closeAll() {
        boolean success = true;
        try {
            response.close();
            httpclient.close();
        } catch (IOException ex) {
            success = false;
        }
        return success;
    }

    public void setRequestHeader(String key, String value) {
        this.requestHeaders.put(key, value);
    }

    public InputStream getResponseAsInputStream() {
        return responseAsInputStream;
    }

    public void setResponseAsInputStream(InputStream responseAsInputStream) {
        this.responseAsInputStream = responseAsInputStream;
    }

    public String getResponseHeader(String key) {
        return requestHeaders.get(key);
    }

    public int getStatusCode() {
        return statusCode;
    }

}
