/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.animedownloader.util.site;

import org.apache.http.client.CookieStore;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.cookie.BasicClientCookie;

/**
 *
 * @author jdomugho
 */
class SiteCookies {

    private CookieStore cookieStore;

    public SiteCookies() {
        cookieStore = new BasicCookieStore();
    }

    public void add(BasicClientCookie cookie) {
        cookieStore.addCookie(cookie);
    }

    public void add(String name, String value, String domain, String path, boolean secure) {
        BasicClientCookie cookie = new BasicClientCookie(name, value);
        cookie.setDomain(domain);
        cookie.setExpiryDate(null);
        cookie.setPath(path);
        cookie.setSecure(secure);

        cookieStore.addCookie(cookie);
    }

    public CookieStore getCookieStore() {
        return cookieStore;
    }

    public void setCookieStore(CookieStore cookieStore) {
        this.cookieStore = cookieStore;
    }
}
