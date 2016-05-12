/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.animedownloader.util.site;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

/**
 *
 * @author jdomugho
 */
public class SiteParams {
    private List<NameValuePair> params;
    
    public SiteParams() {
        this.params = new ArrayList<>();
    }
    
    public void add(String key, String value) {
        params.add(new BasicNameValuePair(key, value));
    }
    
    public List<NameValuePair> getParams() {
        return params;
    }
}
