package com.company;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by ekaterina on 28.07.16.
 */
public class Main {
    public static void main(String args[]){
        Main main = new Main();
        main.go();
    }

    public void go(){
        String information = null;
        try {
            Document doc = Jsoup.connect("http://www.dukelearntoprogram.com/course2/data/manylinks.html").get();
            Elements body = doc.select("body a");
            information = String.valueOf(body);
        }catch (IOException e){
            e.printStackTrace();
        }

        String links = "";
        int start = 0;
        int end = 0;
        while(true) {
            start = information.indexOf("http", end + 1);
            end = information.indexOf("\"", start + 4);

            if((start == -1)||(end == -1)){
                break;
            }

            links += information.substring(start, end) + "\n";
        }

        links = links.toLowerCase();

        start = 0;
        end = 0;

        String youtube = "";

        while(true){
            start = links.indexOf("youtube", start);
            end = links.indexOf("http", start)-1;

            if(start == -1)
                break;

            youtube += "http://www." + links.substring(start, end) + "\n";
            start = end;
        }

        System.out.println(youtube);

    }
}
