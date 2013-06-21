package com.seleniumgrid2api;

import org.openqa.grid.selenium.GridLauncher;

public class StartGrid {

  public static void main(String[] args) throws Exception {
    String[] a = {"-port", "4455",
        "-host", "localhost",
        "-role", "hub"};
    GridLauncher.main(a);
  }
}