/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.animedownloader.controllers;

import com.animedownloader.util.Ticket;
import com.animedownloader.util.Tickets;
import com.animedownloader.util.downloader.VideoDownloader;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(ModelMap model) {
        model.addAttribute("ticketsJson", Tickets.toJsonString());
        return "index";
    }

    @RequestMapping(value = "/runDownloader", method = RequestMethod.POST)
    @ResponseBody
    public void runDownloader(HttpServletRequest request, ModelMap model) {
        Ticket ticket = new Ticket(request);
        
        Thread vidDownloaderThread = new Thread(new VideoDownloader(ticket));
        vidDownloaderThread.start();

//        Ticket ticket = new Ticket();
//        ticket.setUrl("http://yrdsdsdfsf");
//        ticket.setFileName("21");
//        ticket.setFileExtension(".mp4");
//        ticket.setDownloaded(50);
//        ticket.setTotalFileSize(100);
//        ticket.setStatus("weasd");
//
//        Tickets.addTicket(ticket);
    }

    @RequestMapping(value = "/getDownloads", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getDownloads(HttpServletRequest request, ModelMap model) {
        return Tickets.toJsonString();
    }
}
