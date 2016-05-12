/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.animedownloader.controllers;

import com.animedownloader.bean.scrape.Scraper;
import com.animedownloader.bean.scrape.SeriesHandler;
import javax.servlet.http.HttpServletRequest;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author jdomugho
 */
@Controller
public class EpisodeController {

    @RequestMapping(value = "/anime/episodes", method = RequestMethod.GET)
    public String naruto(ModelMap model, HttpServletRequest request) {
        String series = request.getParameter("series");
        model.addAttribute("episodes", fetchEpisode(series));
        return "episode";
    }

    private String fetchEpisode(String series) {
        Scraper scraper = SeriesHandler.getSeries(series);
        scraper.doGetEpisodes();

        return jsonArrToContent(scraper.getEpisodes());
    }

    private String jsonArrToContent(JSONArray jsonArray) {
        String content = jsonArray.size() > 0 ? "<table class=\"table\">" : "no episode found";

        if (!content.contains("no episode found")) {
            content += "<tr>";
            content += "<th>Action</th>";
            content += "<th>Description</th>";
            content += "<th>Status</th>";
            content += "</tr>";
        }

        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jObj = (JSONObject) jsonArray.get(i);
            content += "<tr>";
            content += "<td><button class=\"btn btn-primary\" onclick=\"requestDownload('"+jObj.getString("description")+"','"+jObj.getString("href")+"')\">Download</button></td>";
            content += "<td>"+jObj.getString("description")+"</td>";
            content += "<td style=\"color:red\"><i class=\"glyphicon glyphicon-remove\"></i> Not Found in drive</td>";
            content += "</tr>";
        }

        if (!content.contains("no episode found")) {
            content += "</table>";
        }

        return content;
    }
}


