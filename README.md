# LibraryServer
Java RMI practice

---

## RMIBasic
The basic RMI practice

### How to run?
#### server side
- Place ComputingService.java, ComputingServiceImpl.java and RMIServer.java in the server.
- use javac to compile them
  ``` java
  javac ComputingService.java
  javac ComputingServiceImpl.java
  javac RMIServer.java
  ```
- run it
  ``` java
  java RMIServer
  ```
- if the default port 1099 is occupied, change it to an another port. In the 23rd line of RMIServer.java, then recomplie them and run.
  ``` java
  // open RMIRegistry
  LocateRegistry.createRegistry(1099);
  ```
#### client side
- Place ComputingService.java and RMIClient.java in client
- if your server and client isn't the same computer, change the "localhost" in 16th line of RMIClient.java to the ip address of your server
  ``` java
  // get register from localhost:1099(host:port)
  Registry registry = LocateRegistry.getRegistry("localhost", 1099);
  ```
- use javac to compile them
  ``` java
  javac ComputingService.java
  javac RMIClient.java
  ```
- run it and you will see an output 11
  ``` java
  java RMIClient
  ```
**Note: if you want to run it in a project, please give the two interface file (server and client) a same package name, or you will get an error: no security manager: RMI class loader disabled**

---

## Library Management System
A traditional client/server mode library.

### What does it look like?
- Homepage
  ![homepage][1]
- Add a book to library
  ![addpage][2]
- Delete a book from library
  ![deletepage][3]
- Query books from library
  ![single result][4]
  ![multiple result][5]

### How to run?
#### server side
- Place LibraryServer in server side
- Use javac to compile them
  ``` java
  javac service\*.java
  javac LibraryServer.java
  ```
- Run it
  ``` java
  java LibraryServer
  ```
- if the default port 1099 is occupied, change it to an another port in Line 18 of LibraryServer.java, then recompile them.
  ``` java
  // open RMIRegistry
  LocateRegistry.createRegistry(1099);
  ```
#### client side
- Place LibraryClient in client side
- If your server and client isn't the same computer, change the "localhost" in line 60 of view.MainController.java to the ip address of your server
  ``` java
  // get register from localhost:1099
  Registry registry = LocateRegistry.getRegistry("localhost", 1099);
  ```
- Use javac to compile them
  ``` java
  javac -encoding utf-8 service\*.java
  javac -encoding utf-8 view\*.java
  javac -encoding utf-8 stage\*.java
  ```
- Run it and you will see the UI window
  ``` java
  java stage.Main
  ```


[1]: http://image.littlegreenmouse.cn/LibraryServer/homepage.jpg
[2]: http://image.littlegreenmouse.cn/LibraryServer/Addpage.gif
[3]: http://image.littlegreenmouse.cn/LibraryServer/Deletepage.gif
[4]: http://image.littlegreenmouse.cn/LibraryServer/SingleResult.gif
[5]: http://image.littlegreenmouse.cn/LibraryServer/MultipleResult.gif