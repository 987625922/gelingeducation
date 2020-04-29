# 视频管理后台

## 前言

> `gelingeducation`是一个在学习过程中诞生的项目，包含了后台代码和前台的`vue`代码，采用了前后端分离的方式构建了这一个项目，目的是整合阶段所学的知识和构建一个以后转型后端，找`java`工作的项目。

### 组织结构

``` lua
gelingeducation
├── common -- 工具类，通用代码，过滤器，身份认证
├── controller -- 后台管理系统的接口
├── dao -- hibernate操作数据库的dao层
├── domain -- 存放数据映射的bean
└── service -- 后台管理系统的service层
```

### 后端技术

| 技术          | 版本   |
| ------------- | ------ |
| spring        | 5.2.4  |
| hibernate     | 5.4.12 |
| redis.clients | 3.1.0  |
| shiro         | 1.4.2  |

### 前端项目地址

- [vue-gelingeducation](https://github.com/987625922/Vue-Gelingeducation/tree/master)

### 部署（暂时写个简单的先）

1. 先用idea 标题栏build -> build artifacts -> 项目名:war ->build

2. 先安装docker，docker装上mysql

3. <details>
   <summary>docker安装tomcat</summary>
   <pre><code>

   1. ```
      docker pull tomcat
      ```

       拉取tomcat镜像

   2. ```
      docker run -it -d -p 6789:8080 tomcat 
      ```

      把容器的8080映射到服务器的6789端口

   3. ```
      docker exec -it 8e08b2d49b85 /bin/bash
      ```

      进入容器，8e08b2d49b85 为容器的id

   4. ```
      cd /usr/local/tomcat/conf 
      ```

       进入目录  

      ```
      vim /conf/server.xml
      ```

      打开server.xml文件    

   5. 在server.xml 的 Host节点下新增Context 节点配置 保存并退出

      ```
      <Context docBase="gelingeducation" path="/" reloadable="false" />
      ```

      path:指定访问该Web应用的URL入口。
      docBase:指定Web应用的文件路径，可以给定绝对路径，也可以给定相对于的appBase属性的相对路径，如果Web应用采用开放目录结构，则指定Web应用的根目录，如果Web应用是个war文件，则指定war文件的路径。
      reloadable:如果这个属性设为true，tomcat服务器在运行状态下会监视在WEB-INF/classes和WEB-INF/lib目录下class文件的改动，如果监测到有class文件被更新的，服务器会自动重新加载Web应用。

   6. 把打包出来的war文件上传到服务器，再通过

      ```
      docker cp /opt/gelingeducation 8e08b2d49b85 :/usr/local/tomcat/webapps/ 
      ```

      复制到容器

   7. ```
      docker restart 8e08b2d49b85
      ```

      重新启动tomcat

   </code></pre>
   </details>