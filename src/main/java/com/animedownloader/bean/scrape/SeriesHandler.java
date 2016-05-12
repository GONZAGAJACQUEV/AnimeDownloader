/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.animedownloader.bean.scrape;

import com.animedownloader.bean.series.scraper.NarutoScraper;
import java.util.HashMap;

/**
 *
 * @author jdomugho
 */
public class SeriesHandler {

    private static final HashMap<String, Scraper> seriesMapping = new HashMap<>();

    static {
        seriesMapping.put("naruto", new NarutoScraper());
    }

    public static Scraper getSeries(String series) {
        return seriesMapping.get(series);
    }

}
