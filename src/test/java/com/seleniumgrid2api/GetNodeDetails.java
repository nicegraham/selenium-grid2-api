package com.seleniumgrid2api;

import com.seleniumgrid2api.api.GridInfo;
import com.seleniumgrid2api.api.GridInfoExtractor;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class GetNodeDetails {

  @Test
  public void getNodeDetailsViaTestSession() throws Exception {

    DesiredCapabilities capability = DesiredCapabilities.firefox();

    URL hub = new URL("http://localhost:4444/wd/hub");

    WebDriver driver = new RemoteWebDriver(hub, capability);

    System.out.println("Session Id: " + ((RemoteWebDriver) driver).getSessionId());

    GridInfo gridInfo = GridInfoExtractor.getHostNameAndPort(hub.getHost(), hub.getPort(), ((RemoteWebDriver) driver).getSessionId());

    System.out.println("I'm running on: " + gridInfo.getHost() + ":" + gridInfo.getPort());

  }
}
