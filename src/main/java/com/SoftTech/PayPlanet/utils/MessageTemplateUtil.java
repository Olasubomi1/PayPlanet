package com.SoftTech.PayPlanet.utils;

import lombok.NonNull;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Provides HTMl templates as String
 */
public class MessageTemplateUtil {
    public static String getTemplateOf(@NonNull String fileName)throws IOException{
        String fullFileName = fileName.concat(".html");
        String fullPublicFilePath = "http://localhost:8090/".concat(fullFileName);
        Document document = Jsoup.connect(fullPublicFilePath).ignoreHttpErrors(true).get();
//        System.out.println(document.outerHtml());
        return document.outerHtml();
    }
}
