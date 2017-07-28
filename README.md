[![CircleCI](https://circleci.com/gh/stacs-srg/guid-sta.svg?style=svg&circle-token=15265a5530122a6be6e5473a2ed7176f1a385883)](https://circleci.com/gh/stacs-srg/guid-sta)

# guid-sta

`guid-sta` is a small and simple java utility library that simplifies the process of generating GUIDs.

## Usage via Maven

```
<repository>
    <id>uk.ac.standrews.cs.maven.repository</id>
    <name>School of Computer Science Maven Repository</name>
    <url>http://maven.cs.st-andrews.ac.uk/</url>
</repository>
```

```
<dependency>
    <groupId>uk.ac.standrews.cs</groupId>
    <artifactId>guid-sta</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```

## GUID Examples

You can generate GUIDs and PIDs via the `GUIDFactory` and the `PIDFactory`.

 ```
 IGUID guid = GUIDFactory.generateRandomGUID(ALGORITHM.SHA1);
 System.out.println(guid.toString());
 ```

 Output is:
 ```
 $ 23cec17ec246418a8e82fcc97d70adfe
 ```

 You can also generate a GUID given its string version:
 ```
 IGUID guid = GUIDFactory.recreateGUID("SHA1:16:23cec17ec246418a8e82fcc97d70adfe");
 System.out.println(guid.toString());
 ```

 Output is:
 ```
 $ 23cec17ec246418a8e82fcc97d70adfe
 ```

 Otherwise, you can generate a *brand new* GUID given an string as input (or an InputStream):
 ```
 IGUID guid = GUIDFactory.generateGUID(ALGORITHM.SHA1, "TEST"); // This method can also take an InputStream as input
 System.out.println(guid.toString());
 ```

 Output is:
 ```
 $ 984816fd329622876e14907634264e6f332e9fb3
 ```


## Options

### SHA Algorithms

- SHA1
- SHA256
- SHA384
- SHA512

### Bases

- HEX
- CANON (HEX, but with 8-4-4-4-12 pattern)
- BASE_64


## TODO

- [ ] UUID
- [ ] ETags


## Authors

This project originates from the **asa** project developed by Alan Dearle, Graham Kirby, and Stuart Norcross (University of St Andrews).

This project is currently maintained by Simone I. Conte (University of St Andrews).


## Useful resources

- https://quickhash.com/