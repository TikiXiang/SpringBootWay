SpringBoot初接入概览
=====
# 项目文件结构 #

> 新建的Springboot项目的文件结构如下：

<pre>
|-customer(项目名称)
|  -  src
|  |  -  main
|  |  |  -  java
|  |  |  -  resources
|  |  |  |  -  static
|  |  |  |  -  public
|  |  -  test
|  |  |  -  java
|  - pom.xml
|  - customer.iml
</pre>



>customer：是项目名称；
>
>src/main/java：目录下放置所有java文件（源代码文件）；
>
>src/main/resources：放置所有的配置文件、页面文件、静态资源文件；
>
>src/main/resources/static：是静态资源文件目录，在这个目录中的所有文件将可以被直接访问，如果没有这个文件夹可自行创建；
>
>src/main/resources/public：作用和src/main/resources/static目录一样。
配置文件
>
>Springboot把使用Spring来开发Web项目的很多配置进行了统一管理，且都配置了默认值。很多默认值是基本不用修改的，但也有部份配置是不能满足实际需求的，所以需要修改这些配置。
>
>Springboot默认支持两种配置文件类型：.properties和.yml

比如将默认的8080端口修改为9090，则可以配置为：

application.properties ：

<pre><code>
server.port = 9090
</code></pre>

application.yml ：
```
server:
    port: 9090
```


**注意：**Springboot会自动在src/main/resources/目录下找application.properties或application.yml配置文件，找到后将应用此配置文件中的配置，否则使用其默认值。这两种类型的配置文件有其一即可，也可两者并存。

.properties配置文件的优先级更高，将在application.properties中配置了server.port=9090同时也在application.yml中配置了server: port: 9091时，系统将使用.properties中的9090端口。
常用配置

```server.port=9090 # 服务端口号
server.tomcat.uri-encoding=UTF-8 #以Tomcat为web容器时的字符编码

spring.application.name=customer # 应用名称，一般就是项目名称，这个名称在SpringCloud中比较关键
spring.profiles.active=dev #指定当前的活动配置文件，主要用于多环境多配置文件的应用中
spring.http.encoding.charset=UTF-8 #http请求的字符编码
spring.http.multipart.max-file-size=10MB #设置文件上传时单个文件的大小限制
spring.http.multipart.max-request-size=100MB #设置文件上传时总文件大小限制

spring.thymeleaf.prefix=classpath:/templates/ #配置在使用Thymeleaf做页面模板时的前缀，即页面所在路径
spring.thymeleaf.suffix=.html #设置在使用Thymeleaf做页面模板时的后缀
spring.thymeleaf.cache=false #设置在使用Thymeleaf做页面模板时是否启用缓存

spring.mvc.static-path-pattern=/** #设置静态资源的请求路径
spring.resources.static-locations=classpath:/static/,classpath:/public/ #指定静态资源的路径

##以下是使用MySQL数据库的配置
hibernate.dialect=org.hibernate.dialect.MySQL5Dialect #指定数据库方言
hibernate.show_sql=true #是否显示sql语句
hibernate.hbm2dll.auto=update #设置使用Hibernate的自动建表方式
entitymanager.packagesToScan=com.zslin #设置自动扫描的包前缀

spring.datasource.url=jdbc:mysql://localhost:3306/customer?\
useUnicode=true&characterEncoding=utf-8&useSSL=true&autoReconnect=true #数据库链接
spring.datasource.username=root #数据库用户名
spring.datasource.password=123 #数据库用户对应的密码
spring.datasource.driver-class-name=com.mysql.jdbc.Driver #数据库驱动名称
```


hibernate.hbm2dll.auto有几种配置：

    create：每次加载Hibernate时都会删除上一次生成的表，然后重新生成新表，即使两次没有任何修改也会这样执行，这就导致每次启动都是一个新的数据库，也是导致数据丢失的重要原因。

    create-drop：每次加载Hibernate时都会生成表，但当SessionFactory关闭时，所生成的表将自动删除。

    update：最常用的属性值，第一次加载Hibernate时创建数据表（前提是需要先有数据库），以后加载HIbernate时只会根据model更新，即使model已经删除了某些属性，数据表也不会随之删除字段。

    validate：每次加载Hibernate时都会验证数据表结构，只会和已经存在的数据表进行比较，根据model修改表结构，但不会创建新表。
