# guid-sta

This is a small and simple java utility library that simplifies the process of generating GUIDs and PIDs.

## Usage

**Via maven**

```
 <dependency>
    <groupId>uk.ac.standrews.cs</groupId>
    <artifactId>guid-sta</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```

Note: this project has not been added to the maven repository yet, so you will have to download and install this utility first (`mvn install`)

You can generate GUIDs and PIDs via the `GUIDFactory` and the `PIDFactory`.

 ```
 IGUID guid = GUIDFactory.generateRandomGUID();
 System.out.println(guid.toString());
 # $ 23cec17ec246418a8e82fcc97d70adfe
 ```

 You can also generate a GUID given its string version:
 ```
 IGUID guid = GUIDFactory.recreateGUID("23cec17ec246418a8e82fcc97d70adfe");
 System.out.println(guid.toString());
  # $ 23cec17ec246418a8e82fcc97d70adfe
 ```

 Otherwise, you can generate a *brand new* GUID given an string as input (or an InputStream):
 ```
 IGUID guid = GUIDFactory.generateGUID("TEST"); // This can also be an InputStream
  System.out.println(guid.toString());
   # $ 984816fd329622876e14907634264e6f332e9fb3
 ```


You can only generate random PIDs
```
IPID pid = PIDFactory.generateRandomPID();
```


## TODO

- UUID
- ETags


## Authors

This project originates from the **asa** project developed by Alan Dearle, Graham Kirby, and Stuart Norcross (University of St Andrews).

This project is currently maintained by Simone I. Conte (University of St Andrews).