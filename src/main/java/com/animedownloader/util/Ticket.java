/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.animedownloader.util;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author jdomugho
 */
public class Ticket {

    private int downloaded = 0;
    private int totalFileSize = 0;
    private boolean downloading = true;
    private float percentage = 0;
    private String fileName;
    private String fileExtension;
    private String url;
    private String status;
    private String proxy;

    public Ticket(HttpServletRequest request) {
        this.url = request.getParameter("url");
        this.fileName = request.getParameter("filename");
        this.fileExtension = request.getParameter("fileExtension");
        this.proxy = "10.10.11.193:3128";

        System.out.println("Proxy: " + this.proxy);
        this.status = "Initializing";
    }

    public Ticket() {
    }

    public int getDownloaded() {
        return downloaded;
    }

    public void setDownloaded(int downloaded) {
        this.downloaded = downloaded;
    }

    public int getTotalFileSize() {
        return totalFileSize;
    }

    public void setTotalFileSize(int totalFileSize) {
        this.totalFileSize = totalFileSize;
    }

    public boolean isDownloading() {
        return downloading;
    }

    public void setDownloading(boolean downloading) {
        this.downloading = downloading;
    }

    public float getPercentage() {
        this.percentage = ((float) downloaded / totalFileSize) * 100;
        return this.percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setProxy(String proxy) {
        this.proxy = proxy;
    }

    public String getProxy() {
        return this.proxy;
    }

}
