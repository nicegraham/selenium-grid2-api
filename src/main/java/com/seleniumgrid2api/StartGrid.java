package com.seleniumgrid2api;

import org.openqa.grid.selenium.GridLauncher;

public class StartGrid {

  public static void main(String[] args) throws Exception {
    String[] a = {"-port", "4444",
        "-host", "localhost",
        "-role", "hub",
        "-servlets", "com.seleniumgrid2api.servlet.AllProxiesJsonServlet,com.seleniumgrid2api.servlet.ProxyStatusJsonServlet" };
    GridLauncher.main(a);
  }
}