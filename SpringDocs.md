# Spring官方文檔

# Spring Framework

## 0. Bean definition
Property | explained in
---|---
Class | Instantiating Beans
Name | Naming Beans
Scope | Bean Scopes
Constructor arguments | Dependency Injection
Properties | Dependency Injection
Autowiring mode | Autowiring Collaborators
Lazy initialization mode | Lazy-initialized Beans
Initialization method | Initialization Callbacks
Destruction method | Destruction Callbacks

## 1. Inversion of Controller : IOC

### 1.1 create Bean with `static factory method`
```xml
<bean 
  id="clientService"
  name=""
  class="examples.ClientService"
  factory-method="createInstance"
/>
```
```java
public class ClientService {
    private static ClientService clientService = new ClientService();
    private ClientService() {}

    public static ClientService createInstance() {
        return clientService;
    }
}
```
### 1.2 create Bean with `instance factory method`
```xml
<bean id="serviceLocator" 
    class="examples.DefaultServiceLocator"/>

<bean id="clientService"
    factory-bean="serviceLocator"
    factory-method="createClientServiceInstance"/>

<bean id="accountService"
    factory-bean="serviceLocator"
    factory-method="createAccountServiceInstance"/>
```
```java
public class DefaultServiceLocator {

    private static ClientService clientService = new ClientServiceImpl();

    private static AccountService accountService = new AccountServiceImpl();

    public ClientService createClientServiceInstance() {
        return clientService;
    }

    public AccountService createAccountServiceInstance() {
        return accountService;
    }
}
```

## 2. Dependency Injection : DI

### 2.1dependency injection with `constructor-arg`
```xml
<beans>
    <bean id="beanOne" class="x.y.ThingOne">
        <constructor-arg ref="beanTwo"/>
        <constructor-arg ref="beanThree"/>
    </bean>

    <bean id="beanTwo" class="x.y.ThingTwo"/>

    <bean id="beanThree" class="x.y.ThingThree"/>
</beans>
```
```java
package x.y;

public class ThingOne {

    public ThingOne(ThingTwo thingTwo, ThingThree thingThree) {
        // ...
    }
}
```
```xml
<!-### specify argument by type -->
<bean id="exampleBean" class="examples.ExampleBean">
    <constructor-arg type="int" value="7500000"/>
    <constructor-arg type="java.lang.String" value="42"/>
</bean>

<!-### specify argument by index -->
<bean id="exampleBean" class="examples.ExampleBean">
    <constructor-arg index="0" value="7500000"/>
    <constructor-arg index="1" value="42"/>
</bean>

<!-### specify argument by name -->
<bean id="exampleBean" class="examples.ExampleBean">
    <constructor-arg name="years" value="7500000"/>
    <constructor-arg name="ultimateAnswer" value="42"/>
</bean>
```
### 2.2`constructor-based` dependency injection with java configuration

```java
package examples;

public class ExampleBean {

    @ConstructorProperties({"years", "ultimateAnswer"})
    public ExampleBean(int years, String ultimateAnswer) {
        this.years = years;
        this.ultimateAnswer = ultimateAnswer;
    }
}
```
### 2.3`setter-based` dependency injection with xml
```xml
<bean id="exampleBean" class="examples.ExampleBean">
    <!-### setter injection using the nested ref element -->
    <property name="beanOne">
        <ref bean="anotherExampleBean"/>
    </property>

    <!-### setter injection using the neater ref attribute -->
    <property name="beanTwo" ref="yetAnotherBean"/>
    <property name="integerProperty" value="1"/>
</bean>

<bean id="anotherExampleBean" class="examples.AnotherBean"/>
<bean id="yetAnotherBean" class="examples.YetAnotherBean"/>
```
```java
public class ExampleBean {

    private AnotherBean beanOne;

    private YetAnotherBean beanTwo;

    private int i;

    public void setBeanOne(AnotherBean beanOne) {
        this.beanOne = beanOne;
    }

    public void setBeanTwo(YetAnotherBean beanTwo) {
        this.beanTwo = beanTwo;
    }

    public void setIntegerProperty(int i) {
        this.i = i;
    }
}
```
### 2.4`property` declaration
```xml
<bean id="myDataSource" class="org.apache.commons.dbcp.BasicDataSource" destroy-method="close">
    <!-- results in a setDriverClassName(String) call -->
    <property name="driverClassName" value="com.mysql.jdbc.Driver"/>
    <property name="url" value="jdbc:mysql://localhost:3306/mydb"/>
    <property name="username" value="root"/>
    <property name="password" value="masterkaoli"/>
</bean>

<!-- use p-namespace -->
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:p="http://www.springframework.org/schema/p"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
    https://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="myDataSource" class="org.apache.commons.dbcp.BasicDataSource"
        destroy-method="close"
        p:driverClassName="com.mysql.jdbc.Driver"
        p:url="jdbc:mysql://localhost:3306/mydb"
        p:username="root"
        p:password="masterkaoli"/>

</beans>

<!-- use java.util.Porperties -->
<bean id="mappings"
    class="org.springframework.beans.factory.config.PropertyPlaceholderConfigurer">

    <!-- typed as a java.util.Properties -->
    <property name="properties">
        <value>
            jdbc.driver.className=com.mysql.jdbc.Driver
            jdbc.url=jdbc:mysql://localhost:3306/mydb
        </value>
    </property>
</bean>

<!-- use idred, which let container validate -->
<bean id="theTargetBean" class="..."/>

<bean id="theClientBean" class="...">
    <property name="targetName">
        <idref bean="theTargetBean"/>
    </property>
</bean>
```
## 3. dependencies and configuration in detail

### 3.1 reference to other bean
```xml
<!-- in the parent context -->
<bean id="accountService" class="com.something.SimpleAccountService">
    <!-- insert dependencies as required as here -->
</bean>

<!-- in the child (descendant) context -->
<bean id="accountService" <!-- bean name is the same as the parent bean -->
    class="org.springframework.aop.framework.ProxyFactoryBean">
    <property name="target">
        <ref parent="accountService"/> <!-- notice how we refer to the parent bean -->
    </property>
    <!-- insert other configuration and dependencies as required here -->
</bean>
```
### 3.2 Inner Bean
```xml
<bean id="outer" class="...">
    <!-- instead of using a reference to a target bean, simply define the target bean inline -->
    <property name="target">
        <bean class="com.example.Person"> <!-- this is the inner bean -->
            <property name="name" value="Fiona Apple"/>
            <property name="age" value="25"/>
        </bean>
    </property>
</bean>
```
### 3.3 Collection Bean
```xml
<bean id="moreComplexObject" class="example.ComplexObject">
    <!-- results in a setAdminEmails(java.util.Properties) call -->
    <property name="adminEmails">
        <props>
            <prop key="administrator">administrator@example.org</prop>
            <prop key="support">support@example.org</prop>
            <prop key="development">development@example.org</prop>
        </props>
    </property>
    <!-- results in a setSomeList(java.util.List) call -->
    <property name="someList">
        <list>
            <value>a list element followed by a reference</value>
            <ref bean="myDataSource" />
        </list>
    </property>
    <!-- results in a setSomeMap(java.util.Map) call -->
    <property name="someMap">
        <map>
            <entry key="an entry" value="just some string"/>
            <entry key ="a ref" value-ref="myDataSource"/>
        </map>
    </property>
    <!-- results in a setSomeSet(java.util.Set) call -->
    <property name="someSet">
        <set>
            <value>just some string</value>
            <ref bean="myDataSource" />
        </set>
    </property>
</bean>
```
### 3.4 Collection Merging
```xml
<beans>
    <bean id="parent" abstract="true" class="example.ComplexObject">
        <property name="adminEmails">
            <props>
                <prop key="administrator">administrator@example.com</prop>
                <prop key="support">support@example.com</prop>
            </props>
        </property>
    </bean>
    <bean id="child" parent="parent">
        <property name="adminEmails">
            <!-- the merge is specified on the child collection definition -->
            <props merge="true">
                <prop key="sales">sales@example.com</prop>
                <prop key="support">support@example.co.uk</prop>
            </props>
        </property>
    </bean>
<beans>
```
### 3.5 Strongly typed-Collection
```xml
<beans>
    <bean id="something" class="x.y.SomeClass">
        <property name="accounts">
            <map>
                <entry key="one" value="9.99"/>
                <entry key="two" value="2.75"/>
                <entry key="six" value="3.99"/>
            </map>
        </property>
    </bean>
</beans>
```
```java
public class SomeClass {

    private Map<String, Float> accounts;

    public void setAccounts(Map<String, Float> accounts) {
        this.accounts = accounts;
    }
}
```
```xml
<!-- empty String -->
<bean class="ExampleBean">
    <property name="email" value=""/>
</bean>

<!-- null -->
<bean class="ExampleBean">
    <property name="email">
        <null/>
    </property>
</bean>
```
### 3.6 XML shortcut with p-namespace : property injection
```xml
<!-- In <property> -->
<bean name="john-classic" class="com.example.Person">
    <property name="name" value="John Doe"/>
    <property name="spouse" ref="jane"/>
</bean>
<!-- p:property_name = value -->
<bean name="classic" class="com.example.ExampleBean">
    <property name="email" value="someone@somewhere.com"/>
</bean>

<bean name="p-namespace" class="com.example.ExampleBean"
    p:email="someone@somewhere.com"/>

<!-- p:name-ref to reference another bean -->
<bean name="john-modern"
    class="com.example.Person"
    p:name="John Doe"
    p:spouse-ref="jane"/>
```
### 3.7 XML shortcut with c-namespace : constructor injection
```xml
<!-- In <constructor-arg> -->
<bean id="beanOne" class="x.y.ThingOne">
    <constructor-arg name="thingTwo" ref="beanTwo"/>
    <constructor-arg name="thingThree" ref="beanThree"/>
    <constructor-arg name="email" value="something@somewhere.com"/>
</bean>

<!-- c-namespace declaration with argument names -->
<bean id="beanOne" class="x.y.ThingOne" c:thingTwo-ref="beanTwo"
    c:thingThree-ref="beanThree" c:email="something@somewhere.com"/>

<!-- c-namespace index declaration -->
<bean id="beanOne" class="x.y.ThingOne" c:_0-ref="beanTwo" c:_1-ref="beanThree"
    c:_2="something@somewhere.com"/>
```
### 3.8 `depends-on` force bean to be initialized
```xml
<bean id="beanOne" class="ExampleBean" depends-on="manager"/>

<!-- multiple beans -->
<bean id="beanOne" class="ExampleBean" depends-on="manager,accountDao">
    <property name="manager" ref="manager" />
</bean>
```
### 3.9 `Lazy-initialized` Beans
```xml
<bean id="lazy" class="com.something.ExpensiveToCreateBean" lazy-init="true"/>

<!-- control lazy-initialization at the container level -->
<beans default-lazy-init="true">
    <!-- no beans will be pre-instantiated... -->
</beans>
```
### 3.10 Autowiring Collaborators
```xml
<!-- autowire attribute default to no, other options: byName, byType, constructor -->
<bean id="someThing" class="example.SomeThing" autowire="byType"/>
```
### 3.11 Methods Injection
```java
// no more Spring imports!

public abstract class CommandManager {

    public Object process(Object commandState) {
        // grab a new instance of the appropriate Command interface
        Command command = createCommand();
        // set the state on the (hopefully brand new) Command instance
        command.setState(commandState);
        return command.execute();
    }

    // okay... but where is the implementation of this method?
    protected abstract Command createCommand();

    // use annotation and bean as param
    @Lookup("myCommand")
    protected abstract Command createCommand();

    // specify the bean by return type
    @Lookup
    protected abstract MyCommand createCommand();
}
```
```xml
<!-- a stateful bean deployed as a prototype (non-singleton) -->
<bean id="myCommand" class="fiona.apple.AsyncCommand" scope="prototype">
    <!-- inject dependencies here as required -->
</bean>

<!-- commandProcessor uses statefulCommandHelper -->
<bean id="commandManager" class="fiona.apple.CommandManager">
    <lookup-method name="createCommand" bean="myCommand"/>
</bean>
```

## 4. Beans Scopes 
Scope | Description
---|---
singleton | (Default) Scopes a single bean definition to a single object instance for each Spring IoC container.
prototype | Scopes a single bean definition to any number of object instances.
request | Scopes a single bean definition to the lifecycle of a single HTTP request. That is, each HTTP request has its own instance of a bean created off the back of a single bean definition. Only valid in the context of a web-aware Spring ApplicationContext.
session | Scopes a single bean definition to the lifecycle of an HTTP Session. Only valid in the context of a web-aware Spring ApplicationContext.
application | Scopes a single bean definition to the lifecycle of a ServletContext. Only valid in the context of a web-aware Spring ApplicationContext.
websocket | Scopes a single bean definition to the lifecycle of a WebSocket. Only valid in the context of a web-aware Spring ApplicationContext.

Scope | Shortcut
---|---
singleton | 
### 4.1 `Singleton` Scope
- shortcut: only one instance created and returned when referenced
```xml
<!-- creation example -->
<bean id="accountService" class="com.something.DefaultAccountService"/>

<!-- the following is equivalent, though redundant (singleton scope is the default) -->
<bean id="accountService" class="com.something.DefaultAccountService" scope="singleton"/>
```
### 4.2 `Prototype` Scope
- shortcut: a instance created every time it is referenced
```xml
<!-- creation example -->
<bean id="accountService" class="com.something.DefaultAccountService" scope="prototype"/>
```
### 4.3 `Request`, `Session`, `Application`, `WebSocket` Scope
- shortcut: only for web-aware Spring ApplicationContext
#### 4.3.1 set-up
- using the `WebApplicationInitializer` interface
```xml
<web-app>
    ...
    <listener>
        <listener-class>
            org.springframework.web.context.request.RequestContextListener
        </listener-class>
    </listener>
    ...
</web-app>

<!-- filter mapping, also work same by DispatcherServlet, RequestContextListener, and RequestContextFilter -->
<web-app>
    ...
    <filter>
        <filter-name>requestContextFilter</filter-name>
        <filter-class>org.springframework.web.filter.RequestContextFilter</filter-class>
    </filter>
    <filter-mapping>
        <filter-name>requestContextFilter</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>
    ...
</web-app>
```
#### 4.3.2 Request Scope
- for lifetime of a HTTP request
```xml
<bean id="loginAction" class="com.something.LoginAction" scope="request"/>
```
```java
@RequestScope
@Component
public class LoginAction {
    // ...
}
```
#### 4.3.3 Session Scope
- for lifetime of a HTTP Session
```xml
<bean id="userPreferences" class="com.something.UserPreferences" scope="session"/>
```
```java
@SessionScope
@Component
public class UserPreferences {
    // ...
}
```
#### 4.3.4 Application Scope
```xml
<bean id="appPreferences" class="com.something.AppPreferences" scope="application"/>
```
```java
@ApplicationScope
@Component
public class AppPreferences {
    // ...
}
```
### 4.4 Scoped beans as dependency: scopedProxy
```xml
<!-- an HTTP Session-scoped bean exposed as a proxy -->
<bean id="userPreferences" class="com.something.UserPreferences" scope="session">
    <!-- instructs the container to proxy the surrounding bean -->
    <aop:scoped-proxy/> 
</bean>

<!-- a singleton-scoped bean injected with a proxy to the above bean -->
<bean id="userService" class="com.something.SimpleUserService">
    <!-- a reference to the proxied userPreferences bean -->
    <property name="userPreferences" ref="userPreferences"/>
</bean>
```
#### 4.4.1 use JDK interface-based proxies
```xml
<!-- DefaultUserPreferences implements the UserPreferences interface -->
<bean id="userPreferences" class="com.stuff.DefaultUserPreferences" scope="session">
    <aop:scoped-proxy proxy-target-class="false"/>
</bean>

<bean id="userManager" class="com.stuff.UserManager">
    <property name="userPreferences" ref="userPreferences"/>
</bean>
```

### 4.5 Custom Scope
- implements `org.springframework.beans.factory.config.Scope` interface

#### 4.5.1 Create Custom Scope 
```java
// when bean be gotten
Object get(String name, ObjectFactory objectFactory);

// clean up its dependency when life cycle comes to end
Object remove(String name);

// when to destroy the bean
void registerDestructionCallback(String name, Runnable destructionCallback);

//
String getConversationId();
```

#### 4.5.2 Register Custom Scope
```java
void registerScope(String scopeName, Scope scope);
```
- register custom scope by `CustomScopeConfigurer`
```xml
<bean class="org.springframework.beans.factory.config.CustomScopeConfigurer">
    <property name="scopes">
        <map>
            <entry key="thread">
                <bean class="org.springframework.context.support.SimpleThreadScope"/>
            </entry>
        </map>
    </property>
</bean>

<bean id="thing2" class="x.y.Thing2" scope="thread">
    <property name="name" value="Rick"/>
    <aop:scoped-proxy/>
</bean>

<bean id="thing1" class="x.y.Thing1">
    <property name="thing2" ref="thing2"/>
</bean>
```

## 5. Customize the Nature of Bean

### 5.1 Interfaces for the Nature of Bean
- `BeanPostProcessor` process any callback interfaces
- `InitializingBean`'s `afterPropertiesSet()` for initialization
- `DisposableBean`'s `destroy()` for destruction
- `Lifecycle` as driven by the container’s own lifecycle

### 5.2 Initialization Callbacks
- interface for initializing
```java
// InitializingBean
// Suggest using @PostConstruct
package org.springframework.beans.factory

public interface InitializingBean {
    void afterPropertiesSet() throws Exception;
}
```
- xml-based configuration
```xml
<bean id="exampleInitBean" class="examples.ExampleBean" init-method="init"/>
```
```java
public class ExampleBean {

    public void init() {
        // do some initialization work
    }
}
```
- interface-based configuration
```xml
<bean id="exampleInitBean" class="examples.AnotherExampleBean"/>
```
```java
public class AnotherExampleBean implements InitializingBean {

    public void afterPropertiesSet() {
        // do some initialization work
    }
}
```

### 5.3 Destruction Callbacks
- interface for Destruction
```java
// InitializingBean
// Suggest using @PreDestroy
package org.springframework.beans.factory

public interface DisposableBean {
    void destroy() throws Exception;
}
```
- xml-based configuration
```xml
<bean id="exampleInitBean" class="examples.ExampleBean" destroy-method="cleanup"/>
```
```java
public class ExampleBean {

    public void cleanup() {
        // do some destruction work (like releasing pooled connections)
    }
}
```
- interface-based configuration
```xml
<bean id="exampleInitBean" class="examples.AnotherExampleBean"/>
```
```java
public class AnotherExampleBean implements DisposableBean {

    public void destroy() {
        // do some destruction work (like releasing pooled connections)
    }
}
```
### 5.4 Default Initialization and Destroy Method
- `init()`, `initialize()`, `dispose()`
```java
public class DefaultBlogService implements BlogService {

    private BlogDao blogDao;

    public void setBlogDao(BlogDao blogDao) {
        this.blogDao = blogDao;
    }

    // this is (unsurprisingly) the initialization callback method
    public void init() {
        if (this.blogDao == null) {
            throw new IllegalStateException("The [blogDao] property must be set.");
        }
    }
}
```
```xml
<!-- set default-init-method attrubute on <beans> -->
<beans default-init-method="init">

    <bean id="blogService" class="com.something.DefaultBlogService">
        <property name="blogDao" ref="blogDao" />
    </bean>

</beans>

<!-- similarly, set default-destroy-method for destruction -->
```
### 5.5 Spring-managed Object interface for lifecycle control
```java
// Lifecycle interface
package org.springframework.context;

public interface Lifecycle {

    void start();

    void stop();

    boolean isRunning();
}

// Phased interface
public interface Phased {

    int getPhase();
}

// SamrtLifeCycle, for auto-startup
import org.springframework.context.SmartLifeCycle;

public interface SmartLifecycle extends Lifecycle, Phased {

    boolean isAutoStartup();

    void stop(Runnable callback);
}

// extended interface from Lifecycle
public interface LifecycleProcessor extends Lifecycle {

    void onRefresh();

    void onClose();
}
```

### 5.6 ApplicationContextAware and BeanNameAware
```java
// ApplicationContextAware interface
package org.springframework.context;

public interface ApplicationContextAware {

    // provide a reference to applicationContext
    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;
}

// BeanNameAware interface
package org.springframework.beans.factory;
public interface BeanNameAware {

    void setBeanName(String name) throws BeansException;
}
```

### 5.7 Other Aware Interfaces
Name | Injected Dependency | Explained in…​
---|---|---
ApplicationContextAware | Declaring ApplicationContext. | ApplicationContextAware and BeanNameAware
ApplicationEventPublisherAware | Event publisher of the enclosing ApplicationContext. | Additional Capabilities of the ApplicationContext
BeanClassLoaderAware | Class loader used to load the bean classes. | Instantiating Beans
BeanFactoryAware | Declaring BeanFactory. | ApplicationContextAware and BeanNameAware
BeanNameAware | Name of the declaring bean. | ApplicationContextAware and BeanNameAware
BootstrapContextAware | Resource adapter BootstrapContext the container runs in. Typically available only in JCA-aware ApplicationContext instances. | JCA CCI
LoadTimeWeaverAware | Defined weaver for processing class definition at load time. | Load-time Weaving with AspectJ in the Spring Framework
MessageSourceAware | Configured strategy for resolving messages (with support for parametrization and internationalization). | Additional Capabilities of the ApplicationContext
NotificationPublisherAware | Spring JMX notification publisher. | Notifications
ResourceLoaderAware | Configured loader for low-level access to resources. | Resources
ServletConfigAware | Current ServletConfig the container runs in. Valid only in a web-aware Spring ApplicationContext. | Spring MVC
ServletContextAware | Current ServletContext the container runs in. Valid only in a web-aware Spring ApplicationContext. | Spring 

## 6. Bean Definition Inheritance

### 6.1 Define parent on xml-based configuration
- inherits `scope`, `constructor argument values`, `property values`, and `method overrides` from the parent
- inherits `scope`, `initialization method`, `destroy method`, or `static factory method` settings which have option to be overrided by child bean
- always taken from child definition: `depends on`, `autowire mode`, `dependency check`, `singleton`, and `lazy init`
```xml
<bean id="inheritedTestBean" abstract="true"
        class="org.springframework.beans.TestBean">
    <property name="name" value="parent"/>
    <property name="age" value="1"/>
</bean>

<bean id="inheritsWithDifferentClass"
        class="org.springframework.beans.DerivedTestBean"
        parent="inheritedTestBean" init-method="initialize">  
    <property name="name" value="override"/>
    <!-- the age property value of 1 will be inherited from parent -->
</bean>
```
### 6.2 Inject property value with abstract parent
- while bean has `abstract="true"` attribute, its unnecessary to specify `class` attribute
```xml
<bean id="inheritedTestBeanWithoutClass" abstract="true">
    <property name="name" value="parent"/>
    <property name="age" value="1"/>
</bean>

<bean id="inheritsWithClass" class="org.springframework.beans.DerivedTestBean"
        parent="inheritedTestBeanWithoutClass" init-method="initialize">
    <property name="name" value="override"/>
    <!-- age will inherit the value of 1 from the parent bean definition-->
</bean>
```

## 7. Container Extension Points

### 7.1 Customizing Beans by Using BeanPostProcessor
- `BeanPostProcessor` enable developer to provide his own logic for `Spring Container` 
- Customizing `BeanPostProcessor` should implements `Ordered` interface
- To change the actual bean definition for beans in another hierarchies, use `BeanFactoryPostProcessor`