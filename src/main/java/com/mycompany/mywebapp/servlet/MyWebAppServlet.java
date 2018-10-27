package com.mycompany.mywebapp.servlet;

import com.google.gson.Gson;
import com.mycompany.mywebapp.server.user.UserImpl;
import com.mycompany.mywebapp.shared.user.User;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


@WebServlet(urlPatterns = {"/json"}, asyncSupported = true)
public class MyWebAppServlet extends HttpServlet {

//
//    private final Queue<AsyncContext> ongoingRequests = new ConcurrentLinkedQueue<>();
//    private ScheduledExecutorService service;
//
//    @Override
//    public void init(ServletConfig config) throws ServletException {
//
//        Notifier notifier = new Notifier();
//
//        service = Executors.newScheduledThreadPool(10);
//        service.scheduleAtFixedRate(notifier, 1, 1, TimeUnit.SECONDS);
//    }
//
//    @Override
//    public void doGet(HttpServletRequest req, HttpServletResponse res) {
//        res.setContentType("text/event-stream");
//        res.setCharacterEncoding("UTF-8");
//
//        final AsyncContext ac = req.startAsync(req, res);
//
//        ac.setTimeout(60 * 1000);
//        ac.addListener(new AsyncListener() {
//            @Override public void onComplete(AsyncEvent event) throws IOException {ongoingRequests.remove(ac);}
//            @Override public void onTimeout(AsyncEvent event) throws IOException {ongoingRequests.remove(ac);}
//            @Override public void onError(AsyncEvent event) throws IOException {ongoingRequests.remove(ac);}
//            @Override public void onStartAsync(AsyncEvent event) throws IOException {}
//        });
//        ongoingRequests.add(ac);
//    }
//
//
//    private class Notifier implements Runnable {
//
//        @Override
//        public void run() {
//
//            final Iterator<AsyncContext> iterator = ongoingRequests.iterator();
//            //not using for : in to allow removing items while iterating
//            while (iterator.hasNext()) {
//                AsyncContext ac = iterator.next();
//                Random random = new Random();
//                final ServletResponse res = ac.getResponse();
//                PrintWriter out;
//                try {
//                    out = res.getWriter();
//
//                    User user = new User("Salvador" + String.valueOf(random.nextInt(100) + 1));
//
////                    String next = "data: " + String.valueOf(random.nextInt(100) + 1) + "num of clients = " + ongoingRequests.size() + "\n\n";
//                    out.write(user.getName() + "\n\n");
//                    if (out.checkError()) { //checkError calls flush, and flush() does not throw IOException
//                        iterator.remove();
//                    }
//                } catch (IOException ignored) {
//                    iterator.remove();
//                }
//            }
//        }
//    }


    // https://www.programcreek.com/java-api-examples/?api=com.google.gwt.json.client.JSONValue
    // https://stackoverflow.com/questions/10878243/sse-and-servlet-3-0
    // https://examples.javacodegeeks.com/enterprise-java/gwt/gwt-json-example/

    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Gson gson = new Gson();

        List<User> usersLst = new ArrayList<>();
        usersLst.add(new UserImpl("Carlos", "Sapia"));
        usersLst.add(new UserImpl("Salvador", "Sapia"));

        //UsersImpl users = new UsersImpl(usersLst);

        PrintWriter writer = response.getWriter();
        writer.write(gson.toJson(usersLst));
        writer.flush();
    }

}
