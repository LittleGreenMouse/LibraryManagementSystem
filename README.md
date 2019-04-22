# LibraryServer
Java RMI practice

---

## RMIBasic
The basic RMI practice

### How to run?
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

---

