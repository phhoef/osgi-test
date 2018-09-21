# OSGi Example Running in Apache Karaf

## Installation

On a running Karaf instance (simply started with `bin/karaf`), after having built the project, you can register `my-app-features` features repository:

```
karaf@root()> feature:repo-add mvn:com.my.app/my-app-features/1.0-SNAPSHOT/xml
```

Then, you can install the `my-app-rest` feature, installing under the hood `my-app-api`, `my-app-repository`, ... features:

```
karaf@root()> feature:install my-app-rest
```

## Monitoring 

Karaf already provides a bunch of MBeans accessible via JConsole (local process or remote URL).

I will add an example how to add custom MBeans in the Karaf MBean server.

## Custom distribution / Docker image (WIP)

The `distribution` module will provide a custom Karaf distribution packaging all in turnkey archive.
