package com.mycompany.mywebapp.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.mycompany.mywebapp.client.user.UserHandle;
import com.mycompany.mywebapp.shared.user.User;

import java.util.List;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class MyWebApp implements EntryPoint {

  private List<User> request;

  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {

    VerticalPanel dialogVPanel = new VerticalPanel();
    dialogVPanel.addStyleName("dialogVPanel");
    dialogVPanel.add(new HTML("<b>Users:</b>"));

    Command command = () -> {
      for (User user : request) {
        dialogVPanel.add(new HTML(user.getFirstName() + " " + user.getLastName() + "<br>"));
      }
    };

    try {
      request = new UserHandle(command).handleResponse();
    } catch (RequestException e) {
      Window.alert("Ops..." + e.getMessage());
    }

    RootPanel.get("nameFieldContainer").add(dialogVPanel);

  }
}