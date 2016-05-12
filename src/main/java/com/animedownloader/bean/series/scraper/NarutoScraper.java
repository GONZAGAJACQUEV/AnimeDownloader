/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.animedownloader.bean.series.scraper;

import com.animedownloader.bean.scrape.Scraper;
import com.animedownloader.util.site.SiteFetcher;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author jdomugho
 */
public class NarutoScraper extends Scraper {

    private String episodesUrl = "http://www.watchnarutotv.com/naruto_episodes";

    public NarutoScraper() {
        super();
    }

    @Override
    public boolean doGetEpisodes() {

        boolean succes = true;
        try {
            SiteFetcher fetcher = new SiteFetcher(episodesUrl, "10.10.11.201:3128");
            fetcher.get();
            Elements episodes = Jsoup.parse(fetcher.getHtmlContent()).select("ul.main-list > li > a");
            for (Element anchor : episodes) {
                JSONObject jsonObj = new JSONObject();
                jsonObj.put("description", anchor.text());
                jsonObj.put("href", anchor.attr("href"));

                this.getEpisodes().add(jsonObj);
            }

        } catch (IOException ex) {
            succes = false;
        }
        return true;
    }

    @Override
    public boolean doDownloadEpisodes(String url, String filename) {
        boolean success = false;
        try {
            SiteFetcher fetcher = new SiteFetcher(url, "10.10.11.201:3128");
            fetcher.get();
            success = true;
        } catch (IOException ex) {
        }
        return success;
    }
}
