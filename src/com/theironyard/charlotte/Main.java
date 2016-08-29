package com.theironyard.charlotte;

import spark.ModelAndView;
import spark.Session;
import spark.Spark;
import spark.template.mustache.MustacheTemplateEngine;
import sun.plugin2.message.Message;

import java.util.ArrayList;
import java.util.HashMap;


public class Main {
    static ArrayList<Message> messages = new ArrayList<>();
    static HashMap<String, User> users = new HashMap<>();


    public static void main(String[] args) {
        Spark.init();
        Spark.get(
                "/",
                ((request, response) -> {
                    String username = request.queryParams("username");
                    String password = request.queryParams("password");
                    User user = users.get(username);
                    HashMap usernamemap = new HashMap();

                    ArrayList<User> userinfo = new ArrayList<User>();

                    usernamemap.put("username", userinfo);
                    return new ModelAndView(usernamemap, "home.html");

                }),
                new MustacheTemplateEngine()


                );
        Spark.post(
                "/create-user",
                ((request, response) -> {
                    String username2 = request.queryParams("username2");
                    User user = users.get(username2);
                    if (user == null){
                        user = new User (username2);
                        users.put(username2, user);

                    }
                    Session session = request.session ();
                    response.redirect("/");
                    return "";

                }),
        );


    }
}


