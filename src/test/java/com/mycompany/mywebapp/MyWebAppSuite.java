package com.mycompany.mywebapp;

import com.mycompany.mywebapp.client.MyWebAppTest;
import com.google.gwt.junit.tools.GWTTestSuite;
import junit.framework.Test;
import junit.framework.TestSuite;

public class MyWebAppSuite extends GWTTestSuite {
  public static Test suite() {
    TestSuite suite = new TestSuite("Tests for MyWebApp");
    suite.addTestSuite(MyWebAppTest.class);
    return suite;
  }
}
