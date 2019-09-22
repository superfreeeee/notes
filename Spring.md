# Spring學習筆記
## 重要概念
- IOC 控制反轉
- DI 依賴注入
- AOP 切面編程
- ApplicationContext 配置類，四種實現
  - FileSystemXmlApplicationContext
  - ClassPathXmlApplicationContext
  - XmlWebApplicationContext
  - *AnnotationConfigApplicationContext（Springboot 以注釋配置）

## 重要注釋
### Classify
```java
// 1. Configuration
@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackage="[path]")
@MapperScan("[mapper-path]")

// 2. Bean definition
@Bean([name="[name]"|name={"name1", "name2", ...}], [initMethod="func"], [destroyMethod="func"])
@Component("[id]")
@RestController
@Controller
@Service
@Repository

// 2.1 Bean decoration
@Scope("[type]", [proxyMode="ScopedProxyMode.[mode]"])  // type = [singleton, prototype, request, session, global session], mode = [NO, INTERFACES, TARGET_CLASS]

  // Specific Scope
  @RequestScope
  @SessionScope
  @ApplicationScope
@Description("[content]")
@PostConstruct  // on the specific function
@PreDestroy

// 3. Dependency Injection
@Resource
@Autowired

// 4. Parameter in Controller
@RequestMapping(value="/path/{param}", method=RequestMethod.[type]) // type = [GET, POST, DELETE, PUT, ...]
  // Specific RequestMapping
  @GetMapping
  @PostMapping
  @DeleteMapping
  @PutMapping
@PathVariable(value="param")  // in router
@RequestParam(value="param", defaultValue=[value], required=[boolean])  // parameter after ?


```
### Description
啟動類 | description
-|-
@SpringBootApplication | @Configuration + @EnableAutoConfiguration + @ComponentScan
@Configuration | 啟動Spring容器
@EnableAutoConfiguration | 自動配置所有@Configuration，導入spring.framwork包
@ComponentScan(basePackages="包路徑") | 掃描組件

註冊Bean | description
-|-
@Bean | 一般Bean類
@Component("Id") | 一般Bean，默認Id為小寫類名
@Service | 業務層組件
@Controller | 控制層組件
@RestController | ？默認返回JSON格式
@Repository | 持久層組件，即Dao層的Mapper

數據庫實體類 | description
-|-
@Entity(name="表名") | 實體類，對應數據庫表結構
@Table(name="表名", catalog="目錄", shcema="模式") | catalog數據庫名，（配合@Entity）
@Column | 字段與數據庫字段的對應關係

請求類（處理路由） | description
-|-
@RestController | 作為Http Request類
@RequestMapping() | URL對應的請求

## Lombok
擴充類功能 | description
-|-
@Data | 生成簡單POJO對象（封裝了@ToString, @EqualsAndHashCode, @Getter, @Setter, @RequireArgConstructor)
@NoArgsConstructor | 
@AllArgsConstructor | 

---
# SpringBoot Guide

## 1. Building a RESTful Web Service

### resource reprsentation class
```java
public class Response {
  
  private final int id;

  private final String content;

  public Response(int id, String content) {
    this.id = id;
    this.content = content;
  }

  public int getId() {
    return this.id;
  }

  public String getContent() {
    return this.content;
  }
}
// use `Jasckson JSON` to automatically tranfer Response to JSON
```

### resource controller
```java
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

  private static final String template = "Hello, %s";
  private final AtomicInteger atomicInteger = new AtomicInteger();
  
  @RequestMapping("/hello")
  public Response hello(@RequestParam(value="name", defaultValue="World") String name) {
    return new Response(atomicInteger.incrementAndGet(), String.format(template, name));
  }
}
/**
 * @RestController = @Controller + @ResponseBody
 * In @RestController, the return type is and resource representation object that
 *   will be transfer into a JSON rather than a server-side rendering template
 */

/**
 * To specify Http Method for Mapping
 * use @RequestMapping(Method=GET) to replace simple @RequestMapping
 * or use @GetMapping, @PostMapping ?
 */
```
### executable application
```java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
  
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
/**
 * @SpringBootApplication = @Configuration + @EnableAutoConfiguration + @ComponentScan
 * for @Configuration:
 *   it tags the class to as a source of beans definitions for the application context
 * for @EnableAutoConfiguration:
 *   it tells SpringBoot to start adding beans based on classpath ,which activate key behavior
 * for @ComponentScan:
 *   it tells SpringBoot to look for other components, configurations and services then find controllers
 */
```

## 2. Consuming a RESTful Web Service

### received data class
```java
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
public class Quote {

  private String type;

  private Value value;

  @Override
  public String toString() {
    return "Quote{" +
            "type='" + type + '\'' +
            ", value=" + value +
            '}';
  }
}
```
```java
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
public class Value {

  private long id;

  private String quote;

  @Override
  public String toString() {
    return "Value{" +
            "id=" + id +
            ", quote='" + quote + '\'' +
            '}';
  }
}
```
### execute application
```java
public class Application {

  private static final Logger log = LoggerFactory.getLogger(Application.class);

  public static void main(String[] args) {
    RestTemplate restTemplate = new RestTemplate();
    Quote quote = restTemplate.getForObject("https://gturnquist-quoters.cfapps.io/api/random", Quote.class);
    log.info(quote.toString());
  }
}
```
### execute with spring boot
```java
@SpringBootApplication
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String args[]) {
		SpringApplication.run(Application.class);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			Quote quote = restTemplate.getForObject(
					"https://gturnquist-quoters.cfapps.io/api/random", Quote.class);
			log.info(quote.toString());
		};
	}
}
```
## 3. Serving Web Content with Spring MVC

## 4. Accessing Data with MySQL

## 5. Accessing Relational Data using JDBC with Spring

## package SpringBoot with Maven project
```sh
# run application
$ ./mvnw spring-boot:run
# package application
$ ./mvnw clean package
```