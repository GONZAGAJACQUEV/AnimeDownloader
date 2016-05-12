/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.animedownloader.util;

import java.util.ArrayList;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 *
 * @author jdomugho
 */
public class Tickets {

    private static ArrayList<Ticket> tickets = new ArrayList<>();

    public static ArrayList<Ticket> getTickets() {
        return tickets;
    }

    public static void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }

    public static String toJsonString() {
        JSONArray jsonArr = new JSONArray();

        tickets.stream().map((ticket) -> {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("downloaded", ticket.getDownloaded());
            jSONObject.put("totalfilesize", ticket.getTotalFileSize());
            jSONObject.put("percentage", String.valueOf(ticket.getPercentage()));
            jSONObject.put("status", ticket.getStatus());
            jSONObject.put("fileextension", ticket.getFileExtension());
            jSONObject.put("filename", ticket.getFileName());
            jSONObject.put("url", ticket.getUrl());
            return jSONObject;
        }).forEach((jSONObject) -> {
            jsonArr.add(jSONObject);
        });
        
        

        return jsonArr.toString();
    }

}
