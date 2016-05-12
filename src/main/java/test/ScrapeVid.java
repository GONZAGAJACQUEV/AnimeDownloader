/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import com.animedownloader.util.site.SiteFetcher;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author jdomugho
 */
public class ScrapeVid {

    public static int downloaded = 0;
    public static int totalFileSize = 0;
    public static boolean downloading = true;
    public static float percentage = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread th = new Thread(new DownloadVid());
        th.start();
        long startTime = System.currentTimeMillis();

        while (downloading) {
            if (totalFileSize != 0) {
                percentage = ((float) downloaded / totalFileSize) * 100;
                System.out.println(downloaded + " / " + totalFileSize + " - " + percentage + "%");
            }
            TimeUnit.SECONDS.sleep(10);
        }
        percentage = ((float) downloaded / totalFileSize) * 100;
        System.out.println(downloaded + " / " + totalFileSize + " - " + percentage + "%");
        long endTime = System.currentTimeMillis();
        System.out.println("That took " + LocalTime.ofSecondOfDay(((endTime - startTime) / 1000)).toString());
    }

    private static class DownloadVid implements Runnable {

        @Override
        public void run() {
            try {
                String url = "http://st3.anime1.com/%5BANBU-AonE%5D_Naruto_20_%5BE3B0BFEC%5D_a1.mp4?st=srI1IdN73oLfz-koW2eSSg&e=1447180425";

                System.out.println("Fetching vid url");

                SiteFetcher siteFetcher = new SiteFetcher(url, "10.10.11.201:3128");
                siteFetcher.get();

                totalFileSize = Integer.parseInt(siteFetcher.getResponseHeader("Content-Length"));
                System.out.println("StatusCode: " + siteFetcher.getStatusCode());
                System.out.println("Headers: " + siteFetcher.getResponseHeader("Content-Length"));

                if (siteFetcher.getStatusCode() == 200) {
                    System.out.println("Converting to file");

                    InputStream isVid = new BufferedInputStream(siteFetcher.getResponseAsInputStream());
                    try (OutputStream outputStream = new FileOutputStream(new File("/home/jdomugho/Documents/Naruto/20.mp4"))) {
                        int read = 0;
                        byte[] bytes = new byte[2048];
                        while ((read = isVid.read(bytes)) != -1) {
                            downloaded += read;
                            outputStream.write(bytes, 0, read);
                        }
                        outputStream.close();
                    }
                } else {
                    System.out.println("Status is not ok");
                }

            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                downloading = false;
                System.out.println("Done.");
            }
        }

    }
}
