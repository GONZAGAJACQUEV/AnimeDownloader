/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.animedownloader.bean.scrape;

import net.sf.json.JSONArray;

/**
 *
 * @author jdomugho
 */
public class Scraper {

    private String series;
    private JSONArray episodes;

    public Scraper() {
        episodes = new JSONArray();
    }

    public boolean doScrape() {
        return true;
    }

    public boolean doGetEpisodes() {
        return true;
    }
    
    public boolean doDownloadEpisodes(String url, String filename){
        return true;
    }

    public JSONArray getEpisodes() {
        return episodes;
    }

    public void setEpisodes(JSONArray episodes) {
        this.episodes = episodes;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }
}
