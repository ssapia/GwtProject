package com.mycompany.mywebapp.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.mycompany.mywebapp.client.user.UserRequest;
import com.mycompany.mywebapp.shared.FieldVerifier;
import com.mycompany.mywebapp.shared.user.User;

import java.util.List;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class MyWebApp implements EntryPoint {
  /**
   * The message displayed to the user when the server cannot be reached or
   * returns an error.
   */
  private static final String SERVER_ERROR = "An error occurred while "
          + "attempting to contact the server. Please check your network "
          + "connection and try again.";

  /**
   * Create a remote service proxy to talk to the server-side Greeting service.
   */
  private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

//  private List<User> request;

  private List<User> request;

  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {

    try {
      request = new UserRequest().request();
    } catch (RequestException e) {
    }


    final Button sendButton = new Button("Send");
    final TextBox nameField = new TextBox();
    nameField.setText("GWT User");
    final Label errorLabel = new Label();

    // We can add style names to widgets
    sendButton.addStyleName("sendButton");

    // Add the nameField and sendButton to the RootPanel
    // Use RootPanel.get() to get the entire body element
    RootPanel.get("nameFieldContainer").add(nameField);
    RootPanel.get("sendButtonContainer").add(sendButton);
    RootPanel.get("errorLabelContainer").add(errorLabel);

    // Focus the cursor on the name field when the app loads
    nameField.setFocus(true);
    nameField.selectAll();

    // Create the popup dialog box
    final DialogBox dialogBox = new DialogBox();
    dialogBox.setText("Remote Procedure Call");
    dialogBox.setAnimationEnabled(true);
    final Button closeButton = new Button("Close");
    // We can set the id of a widget by accessing its Element
    closeButton.getElement().setId("closeButton");
    final Label textToServerLabel = new Label();
    final HTML serverResponseLabel = new HTML();
    VerticalPanel dialogVPanel = new VerticalPanel();
    dialogVPanel.addStyleName("dialogVPanel");
    dialogVPanel.add(new HTML("<b>Sending name to the server:</b>"));
    dialogVPanel.add(textToServerLabel);
    dialogVPanel.add(new HTML("<br><b>Server replies:</b>"));
    dialogVPanel.add(serverResponseLabel);
    dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
    dialogVPanel.add(closeButton);
    dialogBox.setWidget(dialogVPanel);

    // Add a handler to close the DialogBox
    closeButton.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        dialogBox.hide();
        sendButton.setEnabled(true);
        sendButton.setFocus(true);
      }
    });

    // Create a handler for the sendButton and nameField
    class MyHandler implements ClickHandler, KeyUpHandler {
      /**
       * Fired when the user clicks on the sendButton.
       */
      public void onClick(ClickEvent event) {
        parseData();
      }

      /**
       * Fired when the user types in the nameField.
       */
      public void onKeyUp(KeyUpEvent event) {
        if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
//          parseData();
        }
      }

      /**
       * Send the name from the nameField to the server and wait for a response.
       */
      private void sendNameToServer() {
        // First, we validate the input.
        errorLabel.setText("");
        String textToServer = nameField.getText();
        if (!FieldVerifier.isValidName(textToServer)) {
          errorLabel.setText("Please enter at least four characters");
          return;
        }

        // Then, we send the input to the server.
        sendButton.setEnabled(false);
        textToServerLabel.setText(textToServer);
        serverResponseLabel.setText("");
        greetingService.greetServer(textToServer, new AsyncCallback<String>() {
          public void onFailure(Throwable caught) {
            // Show the RPC error message to the user
            dialogBox.setText("Remote Procedure Call - Failure");
            serverResponseLabel.addStyleName("serverResponseLabelError");
            serverResponseLabel.setHTML(SERVER_ERROR);
            dialogBox.center();
            closeButton.setFocus(true);
          }

          public void onSuccess(String result) {
            dialogBox.setText("Remote Procedure Call");
            serverResponseLabel.removeStyleName("serverResponseLabelError");
            serverResponseLabel.setHTML(result);
            dialogBox.center();
            closeButton.setFocus(true);
          }
        });
      }
    }

    // Add a handler to send the name to the server
    MyHandler handler = new MyHandler();
    sendButton.addClickHandler(handler);
    nameField.addKeyUpHandler(handler);
  }

  private void parseData() {

      for (User user : request) {
        Window.alert("Hello, " + user.getFirstName());
      }

//
//    String userst = "";
//
//    for (User user : request) {
//      userst += user.getFirstName() + user.getLastName() + "<br>";
//    }
//
//    Window.alert("usuarios"+ request.size() + userst);
  }
//  private void parseData() {
//    RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, "/json");
//    try {
//      requestBuilder.sendRequest(null, new RequestCallback() {
//
//        @Override
//        public void onResponseReceived(Request request, Response response) {
//          UserJs c = MyWebApp.parseJson(response.getText());
//          Window.alert(c.getFullName());
//        }
//
//        @Override
//        public void onError(Request request, Throwable exception) {
//          Window.alert("Some error occurred: " + exception.getMessage());
//        }
//
//      });
//    } catch (RequestException e) {
//      e.printStackTrace();
//    }
//
//  }
//
//  public static <T extends JavaScriptObject> T parseJson(String jsonStr) {
//    return JsonUtils.safeEval(jsonStr);
//  }
//
//  static class UserJs extends JavaScriptObject {
//
//    // Overlay types always have protected, zero-arg ctors
//    protected UserJs() {
//    }
//
//    // Typically, methods on overlay types are JSNI
//    public final native String getFirstName() /*-{ return this.firstName; }-*/;
//
//    public final native String getLastName() /*-{ return this.lastName;  }-*/;
//
//    // Note, though, that methods aren't required to be JSNI
//    public final String getFullName() {
//      return getFirstName() + " " + getLastName();
//    }
//
//  }
}