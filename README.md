selenium-grid2-api
==================

All available endpoints:
* /grid/console/
* /grid/beta/console/
* /grid/old/console/
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

register
--------

driver
------

hub
---

resources
---------



Allows you to access resources stored in the server .jar file. 

**base url:**
```
http://localhost:4444/grid/resources/
```

**sample request:**
```
http://localhost:4444/grid/resources/org/openqa/grid/images/console-beta.js
```

**response:**
```
The contents of the console-beta.js
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

