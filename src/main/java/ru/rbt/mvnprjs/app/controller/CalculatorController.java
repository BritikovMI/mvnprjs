package ru.rbt.mvnprjs.app.controller;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import ru.rbt.mvnprjs.app.model.Calculator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;


public class CalculatorController {

    private Calculator calculator;
    private UIInput firstNumberInput;
    private UIInput secondNumberInput;
    String rateStatus = "Please press GetCourse";
    static BigDecimal currentElem;
    static LinkedList<BigDecimal> courseElements = new LinkedList<>();
    volatile static Queue<BigDecimal> queue = new PriorityQueue<>();
    //For queue analyze Thread threadRead


    public String getRateStatus() {
        return rateStatus;
    }

    String courseD[];


    public void setCalculator(Calculator calculator) {
        this.calculator = calculator;
    }

    public Calculator getCalculator() {
        return calculator;
    }

    public String add() {

        FacesContext facesContext = FacesContext.getCurrentInstance();

        try {
            calculator.add();
            facesContext.addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_INFO, "Added successfully", null));

        } catch (Exception ex) {
            facesContext.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), null));
        }
        return "results";
    }

    public String multiply() {

        FacesContext facesContext = FacesContext.getCurrentInstance();

        try {
            calculator.multiply();
            facesContext.addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_INFO, "Multiplied successfully", null));

        } catch (Exception ex) {
            facesContext.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), null));
        }
        return "results";
    }

    public String divide() {

        FacesContext facesContext = FacesContext.getCurrentInstance();

        try {
            calculator.divide();
            facesContext.addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_INFO, "Divided successfully", null));

        } catch (Exception ex) {
            if (ex instanceof ArithmeticException) {
                secondNumberInput.setValue(Integer.valueOf(1));
            }
            facesContext.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), null));
        }
        return "results";
    }

    public String clear() {

        FacesContext facesContext = FacesContext.getCurrentInstance();

        try {
            calculator.clear();
            facesContext.addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_INFO, "Results cleared", null));

        } catch (Exception ex) {
            facesContext.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), null));
        }
        return null;
    }

    public String getFirstNumberStyleClass() {
        if (firstNumberInput.isValid()) {
            return "labelClass";
        } else {
            return "errorClass";
        }
    }

    public String getSecondNumberStyleClass() {
        if (secondNumberInput.isValid()) {
            return "labelClass";
        } else {
            return "errorClass";
        }
    }

    public UIInput getFirstNumberInput() {
        return firstNumberInput;
    }

    public void setFirstNumberInput(UIInput firstNumberInput) {
        this.firstNumberInput = firstNumberInput;
    }

    public UIInput getSecondNumberInput() {
        return secondNumberInput;
    }

    public void setSecondNumberInput(UIInput secondNumberInput) {
        this.secondNumberInput = secondNumberInput;
    }

public String CourseD() {
//    String[] myParams = request.getRequestURI().split("/");

    StringBuilder sb = new StringBuilder();

//        String name = request.getServletPath();
//        String sNum = request.getRequestURI();
//
//    String name = myParams[4];
//    //        String name = "customer-order";
//    Long num = Long.parseLong(myParams[6]);
//        Long num = Long.parseLong("2");

//        response.setCharacterEncoding("UTF-8");
//        response.setContentType("text/html");
//    PrintWriter pw = response.getWriter();
//        pw.println("<pre>");
//        pw.println("<h1>Hello, the name is: </h1>" + name + "<h3>Your table</h3>");
////        List<String> result = daoManager.handleRequest(name, num);
////        pw.println(orderRestImpl.findByNameAndId(name, num));
////        for (String s : result) {
////            pw.println(s);
////        }


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
//                value = sb.toString().split("<Value>");
//                dCourse = value[11].split("</Value>");
//                preFinalSCourse = dCourse[0].split(",");
//                finalSCourse = preFinalSCourse[0] + "." + preFinalSCourse[1];
//                finalCourse = Double.parseDouble(finalSCourse);
            queue.add(finalCourse);
//                pw.println("Hello from " + Thread.currentThread());

            i++;
        } while (i < 24);
        Thread.interrupted();
    });

    Thread threadRead = new Thread(() -> {
        int compare;
        BigDecimal first, last;
//            Integer i = Integer.valueOf(0);
//            do {
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
//                i++;
//            } while (i < 1);
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
//        Runnable task = () -> {
//            try {
//                siteLoader(sb);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            value = sb.toString().split("<Value>");
//            dCourse = value[11].split("</Value>");
//            preFinalSCourse = dCourse[0].split(",");
//            finalSCourse = preFinalSCourse[0] + "." + preFinalSCourse[1];
//            Double finalCourse = Double.parseDouble(finalSCourse);
//
//            pw.print(finalCourse);
//        };
//
//        task.run();
//
//        Thread thread = new Thread(task);
//        thread.start();

//        pw.println("</pre>");
//        pw.close();


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
//        getDate();
//        newDate = dayS;
//        if (month < 10)
//            newMonth = "0" + String.valueOf(month);
//        else
//            newMonth = String.valueOf(month);

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
