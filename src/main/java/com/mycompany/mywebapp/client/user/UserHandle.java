package com.mycompany.mywebapp.client.user;

import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsonUtils;
import com.google.gwt.http.client.*;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.mycompany.mywebapp.shared.user.User;

import java.util.ArrayList;
import java.util.List;

public class UserHandle {

    RequestBuilder requestBuilder;
    Command command;

    public UserHandle(Command command) {
        this.command = command;
        this.requestBuilder = new RequestBuilder(RequestBuilder.GET, "/json");
    }

    public List<User> handleResponse() throws RequestException {

        List<User> users = new ArrayList<>();

        requestBuilder.sendRequest(null, new RequestCallback() {

            @Override
            public void onResponseReceived(Request request, Response response) {

                JsArray<UserJS> c = JsonUtils.safeEval(response.getText()).cast();

                for (int i = 0, n = c.length(); i < n; ++i) {
                    users.add(c.get(i));
                }

                command.execute();
            }

            @Override
            public void onError(Request request, Throwable exception) {
                Window.alert("Some error occurred: " + exception.getMessage());
            }

        });

        return users;
    }
}
