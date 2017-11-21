package ru.rbt.mvnprjs.app.controller;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import ru.rbt.mvnprjs.app.model.Parser;
import ru.rbt.mvnprjs.app.other.DaoManager;

import javax.faces.component.UIInput;
import javax.inject.Inject;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;


public class ParserController {
//    @Inject
    private DaoManager daoManager;

    String nameOf;
    String name= "customer-order";
    Long num = Long.parseLong("2");
    public String getNameOf() {
        return nameOf;
    }

    public String getName() {
        return name;
    }

    public Long getNum() {
        return num;
    }

    public String dBHelper(){
        nameOf = "HOPAAAa";

        List<String> result = daoManager.handleRequest(name, num);
//        pw.println(orderRestImpl.findByNameAndId(name, num));
        for (String s : result) {
            nameOf = s;
        }
    return nameOf;
}

    private Parser parser;
    String rateStatus = "Please press GetCourse";
    static BigDecimal currentElem;
    static LinkedList<BigDecimal> courseElements = new LinkedList<>();
    volatile static Queue<BigDecimal> queue = new PriorityQueue<>();
    //For queue analyze Thread threadRead


    public String getRateStatus() {
        return rateStatus;
    }

    String courseD[];


    public void setParser(Parser parser) {
        this.parser = parser;
    }


public String CourseD() {


    StringBuilder sb = new StringBuilder();



    Thread threadWrite = new Thread(() -> {
        BigDecimal finalCourse = BigDecimal.valueOf(0);
        Integer i = Integer.valueOf(0);
        do {
            try {
                finalCourse = siteParser(sb);
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            queue.add(finalCourse);

            i++;
        } while (i < 24);
        Thread.interrupted();
    });

    Thread threadRead = new Thread(() -> {
        int compare;
        BigDecimal first, last;
        courseElements.clear();
        try {
            Thread.sleep(120000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        do {
            currentElem = queue.poll();
            if (currentElem != null)
                courseElements.add(currentElem);
        } while (currentElem != null);
        first = courseElements.getFirst();
        last = courseElements.getLast();
        compare = first.compareTo(last);
        if (compare == -1) {
            rateStatus = "The rate fell by: " + (first.subtract(last)) + " and amounted to " + last;
        } else if (compare == 1) {
            rateStatus = "The rate increased by: " + (last.subtract(first)) + " and amounted to " + last;
        } else {
            rateStatus = "The rate has not changed and is equal to: " + first;
        }
        Thread.interrupted();
    });


    try {
        threadRead.join();
        threadWrite.join();
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
    threadWrite.start();
    threadRead.start();
    threadWrite.interrupt();
    threadRead.interrupt();
    return rateStatus;
}

    public BigDecimal siteParser(StringBuilder sb) throws IOException {
        String finalSCourse;
        BigDecimal finalCourse;
        String HTMLSTring = siteLoader(sb);

        Document html = Jsoup.parse(HTMLSTring);

        String h1 = html.body().getElementsByClass("t18").text();

        courseD = h1.split(" ");
        finalSCourse = courseD[3];

        finalCourse = BigDecimal.valueOf(Double.parseDouble(finalSCourse));
        return finalCourse;
    }

    public String siteLoader(StringBuilder sb) throws IOException {

        URLConnection connection = new URL("https://www.calc.ru/forex-USD-RUB.html").openConnection();

        InputStream is = connection.getInputStream();
        InputStreamReader reader = new InputStreamReader(is);
        char[] buffer = new char[256];
        int rc;


        while ((rc = reader.read(buffer)) != -1)
            sb.append(buffer, 0, rc);

        reader.close();
        String newSb = sb.toString();

        return newSb;
    }
}
