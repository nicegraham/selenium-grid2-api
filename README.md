selenium-grid2-api
==================

This is an attempt to document the Grid2 API endpoints, along with some code to interact with same and give some sample custom proxies, html renderers and capability matchers.



Grid2 available endpoints:
* /grid/console/
* /grid/beta/console/
* /grid/register/
* /grid/driver/
* /wd/hub/
* /selenium-server/driver/
* /grid/resources/
* /grid/api/proxy/
* /grid/api/hub/
* /grid/api/testsession/
* /lifecycle-manager/ 

console
-------
/grid/console/
/grid/beta/console/


/grid/register/
---------------
Used to register a node on the hub.

**base url:**
```
http://localhost:4444/grid/register/
```

**parameter formats accepted:**
```
JSON as per https://code.google.com/p/selenium/wiki/DesiredCapabilities
```

**sample request:**
```
http://localhost:4444/grid/register/



```

**response:**
```
```

/grid/driver/
-------------

/wd/hub
-------

**base url:**
```
http://localhost:4444/wd/hub
```

**parameter formats accepted:**
```
url parameters
```

**sample request:**
```
http://localhost:4444/wd/hub/status
```

**response from hub:**
```
{
    "status": 13,
    "value": {
        "message": "Session [(null externalkey)] not available and is not among the last 1000 terminated sessions.\nActive sessions are[ext. key bf655635-3ef8-4997-865f-3a00ffadf01e, ext. key b3a098fa-4dce-47ca-8ed0-8bc02e734155, 5cf0bdd3-07e9-4dd7-a1e1-9c87da8b8c4c (int. key, remote not contacted yet.), ext. key 5ae2b631-d8fe-40dc-8851-a9415290b6dd, ext. key f575c810-aa50-43ad-973c-dfed62660a34, ext. key d466b0ba-4cbc-4aa1-a785-92ffa486e86b]",
        "class": "org.openqa.grid.common.exception.GridException",
        "stackTrace": [
            {
                "fileName": "ActiveTestSessions.java",
                "lineNumber": 109,
                "className": "org.openqa.grid.internal.ActiveTestSessions",
                "methodName": "getExistingSession"
            },
            {
                "fileName": "Registry.java",
                "lineNumber": 423,
                "className": "org.openqa.grid.internal.Registry",
                "methodName": "getExistingSession"
            },
            {
                "fileName": "RequestHandler.java",
                "lineNumber": 234,
                "className": "org.openqa.grid.web.servlet.handler.RequestHandler",
                "methodName": "getSession"
            }
        ]
    }
}
```

**response from node:**
```
{
    "sessionId": null,
    "status": 0,
    "value": {
        "os": {
            "arch": "x86",
            "name": "Windows XP",
            "version": "5.1"
        },
        "java": {
            "version": "1.7.0_21"
        },
        "build": {
            "revision": "unknown",
            "time": "unknown",
            "version": "unknown"
        }
    },
    "state": "success",
    "class": "org.openqa.selenium.remote.Response",
    "hCode": 4684600
}
```

/grid/resources/
----------------

Allows you to access resources stored in the server .jar file. 

**base url:**
```
http://localhost:4444/grid/resources/
```
**parameter formats accepted:**
```
url parameters
```

**sample request:**
```
http://localhost:4444/grid/resources/org/openqa/grid/images/console-beta.js
```

**response:**
```
The contents of the console-beta.js
```

/grid/api/proxy/
----------------

**base url:**
```
http://localhost:4444/grid/api/proxy/
```

**parameter formats accepted:**
```
JSON, url parameters
```

**sample request:**
```
http://localhost:4444/grid/api/proxy/

{
    "id": "http://10.105.140.42:5555"
}
 
 or
 
http://localhost:4444/grid/api/proxy?id=http://10.20.30.40:5555
```

**sample response:**
```
{
    "id": "http://10.105.140.42:5555",
    "request": {
        "class": "org.openqa.grid.common.RegistrationRequest",
        "capabilities": [
            {
                "platform": "XP",
                "browserName": "internet explorer",
                "maxInstances": "1",
                "version": "7"
            },
            {
                "platform": "XP",
                "browserName": "firefox",
                "maxInstances": "2",
                "version": "9"
            },
            {
                "platform": "XP",
                "browserName": "chrome",
                "maxInstances": "2"
            }
        ],
        "configuration": {
            "port": 5555,
            "hubConfig": "hubServlets.json",
            "registerCycle": 5000,
            "hub": "http://10.20.30.40:4444/grid/register",
            "newSessionWaitTimeout": -1,
            "remoteHost": "http://10.105.140.42:5555",
            "prioritizer": null,
            "throwOnCapabilityNotPresent": true,
            "proxy": "org.openqa.grid.selenium.proxy.DefaultRemoteProxy",
            "browser": "browserName=internet explorer,platform=XP,version=7,maxInstances=1",
            "maxSession": 5,
            "role": "node",
            "Dport=5556": "ensureCleanSession=true",
            "Dwebdriver.ie.driver=C:\\automation\\IEDriverServer.exe": "",
            "servlets": "com.groupon.SeleniumGridExtrasServlet",
            "host": "10.105.140.42",
            "cleanUpCycle": 5000,
            "browserTimeout": 600000,
            "hubHost": "autotestweb-002",
            "capabilityMatcher": "org.openqa.grid.internal.utils.DefaultCapabilityMatcher",
            "url": "http://10.105.140.42:5555",
            "register": true,
            "nodePolling": 5000,
            "Dwebdriver.server.session.timeout": "120",
            "hubPort": 4444,
            "Dwebdriver.chrome.driver=C:\\automation\\chromedriver.exe": "",
            "timeout": 600000
        }
    },
    "msg": "proxy found !",
    "success": true
}
```

/grid/api/hub/
--------------
API to query the hub config remotely.
 
use the API by sending a GET to grid/api/hub/
with the content of the request in JSON,specifying the 
parameters you're interesting in, for instance, to get 
the timeout of the hub and the registered servlets :
 
**base url:**
```
http://localhost:4444/grid/api/hub/
```

**parameter formats accepted:**
```
JSON
```

**sample request:**
```
http://localhost:4444/grid/api/hub/

{"configuration":
      [
      "timeout",
      "servlets"
      ]
 }
 
 if no param is specified, all params known to the hub are returned.
 
 {"configuration": []  }

```

**sample response:**
```
{
    "port": 4444,
    "servlets": "",
    "host": null,
    "throwOnCapabilityNotPresent": true,
    "cleanUpCycle": 5000,
    "nodePolling": 5000,
    "browserTimeout": 0,
    "maxSession": 5,
    "role": "hub",
    "capabilityMatcher": "org.openqa.grid.internal.utils.DefaultCapabilityMatcher",
    "newSessionWaitTimeout": -1,
    "success": true,
    "timeout": 300000,
    "prioritizer": null
}
```

/grid/api/testsession/
----------------------


**base url:**
```
http://localhost:4444/grid/api/testsession/
```

**parameter formats accepted:**
```
url parameters, JSON
```

**sample request:**
```
http://localhost:4444/grid/api/testsession?session=196555
```

**sample response:**
```

```

lifecycle-manager
-----------------

The only implemented action is to shutdown the node.

**base url:**
```
http://localhost:4444/lifecycle-manager
```

**parameter formats accepted:**
```
url parameters
```

**sample request:**
```
http://localhost:4444/lifecycle-manager?action=shutdown
```

**sample response:**
```
null
```

