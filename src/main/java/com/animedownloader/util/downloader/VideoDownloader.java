/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.animedownloader.util.downloader;

import com.animedownloader.util.Ticket;
import com.animedownloader.util.Tickets;
import com.animedownloader.util.site.SiteFetcher;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author jdomugho
 */
public class VideoDownloader implements Runnable {

    private final Ticket ticket;

    public VideoDownloader(Ticket ticket) {
        this.ticket = ticket;
        Tickets.addTicket(this.ticket);
    }

    @Override
    public void run() {
        try {
            ticket.setStatus("Fetching vid url");

            SiteFetcher siteFetcher = new SiteFetcher(ticket.getUrl(), "10.10.11.193:3128");            
            siteFetcher.get();

            ticket.setTotalFileSize(Integer.parseInt(siteFetcher.getResponseHeader("Content-Length")));
            ticket.setStatus("StatusCode: " + siteFetcher.getStatusCode());
            ticket.setStatus("Headers: " + siteFetcher.getResponseHeader("Content-Length"));

            if (siteFetcher.getStatusCode() == 200) {
                ticket.setStatus("Downloading file");

                InputStream isVid = new BufferedInputStream(siteFetcher.getResponseAsInputStream());
                try (OutputStream outputStream = new FileOutputStream(new File("/home/jdomugho/Documents/Naruto/" + ticket.getFileName() + ticket.getFileExtension()))) {
                    int read = 0;
                    byte[] bytes = new byte[2048];
                    int downloaded = 0;
                    while ((read = isVid.read(bytes)) != -1) {
                        downloaded += read;
                        ticket.setDownloaded(downloaded);
                        outputStream.write(bytes, 0, read);
                    }
                    outputStream.close();
                }
            } else {
                ticket.setStatus("Status is not ok 200");
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            ticket.setDownloading(false);
//            ticket.setStatus("Done.");z
        }
    }

}
