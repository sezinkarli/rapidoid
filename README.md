Rapidoid
========

Rapidoid is the way of rapid development of high-performance (web) applications.

# Apache Public License v2

The software is released under the liberal APL v2 license, so it is free to use for both commercial and non-commercial projects.

# Using with Maven

Add the following snippet to the `<dependencies>` section in pom.xml:

```xml
<dependency>
    <groupId>org.rapidoid</groupId>
    <artifactId>rapidoid-all</artifactId>
    <version>0.9.0</version>
</dependency>
```

# Quick start

* Add the `rapidoid-all` dependency to your Maven project (as described above).

* Add the following code to your project:
 
```java
import java.util.List;
import java.util.Map;

public class MainService {

	public String index() {
		return "hi!";
	}

	public String hey(String name, int age) {
		return "Hey " + name + " (" + age + ")";
	}

	public List<String> foo(List<String> params) {
		return params;
	}

	public Map<String, Object> bar(Map<String, Object> params) {
		return params;
	}

}
```

* Execute the `org.rapidoid.web.Pojo` application (found on your classpath). 
It will scan for `*Service` classes on your classpath and start a fast embedded HTTP server (`rapidoid-http`).

* Navigate to:
 * [http://localhost:8080/](http://localhost:8080/) (returns `hi!`)
 * [http://localhost:8080/hey/joe/22](http://localhost:8080/hey/joe/22) (returns `Hey joe (22)`)
 * [http://localhost:8080/foo/aa/bbb/c](http://localhost:8080/foo/aa/bbb/c) (returns `["aa","bbb","c"]`)
 * [http://localhost:8080/bar?x=1&y=2](http://localhost:8080/bar?x=1&y=2) (returns `{"y":"2","x":"1"}`)

# Important modules

- rapidoid-u: simple and elegant static utilities useful for any kind of Java software development,
- rapidoid-buffer: high-performance and extensible off-heap buffer, the heart of network communication,
- rapidoid-net: high-performance asynchronous framework for super-easy development of various network protocol servers,
- rapidoid-http: high-performance asynchronous HTTP server,
- rapidoid-websocket: high-performance asynchronous WebSocket server (coming soon...),
- rapidoid-test-commons: helpers for writing more elegant tests,
- rapidoid-pojo: writing web apps/services through simple POJOs,
- rapidoid-?: a lot more is coming...

# Contributing

1. Fork (and then `git clone https://github.com/rapidoid/rapidoid.git`).
2. Create a branch (`git checkout -b branch_name`).
3. Commit your changes (`git commit -am "Description of contribution"`).
4. Push to the branch (`git push origin branch_name`).
5. Open a Pull Request.
6. Thank you for your contribution! Wait for a response...

