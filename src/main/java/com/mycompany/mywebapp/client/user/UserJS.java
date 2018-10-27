package com.mycompany.mywebapp.client.user;

import com.google.gwt.core.client.JavaScriptObject;
import com.mycompany.mywebapp.shared.user.User;

public class UserJS extends JavaScriptObject implements User {

    // Overlay types always have protected, zero-arg ctors
    protected UserJS() {
    }

    // Typically, methods on overlay types are JSNI
    public final native String getFirstName() /*-{ return this.firstName; }-*/;

    public final native String getLastName() /*-{ return this.lastName;  }-*/;

    // Note, though, that methods aren't required to be JSNI
    public final String getFullName() {
        return getFirstName() + " " + getLastName();
    }

}
