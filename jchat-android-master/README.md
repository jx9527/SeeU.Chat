# SeeU 

### 简介

SeeU 是基于 JMessage SDK 带有完整 UI 界面的二次开发即时通讯应用。 
特点是加入了nlp算法，将传统的通过搜索方式添加好友模式变成用户通过选取标签，系统进行相似度计算，并且推荐相似度高的好友进行添加，并以匿名社交为主题。

* 单聊、会话列表
* 支持发送文本、图片、语音、表情、位置、小视频；
* 提供用户管理、黑名单等功能；

SeeU无好友模式：

* 无好友模式：无需将对方加为好友，通过搜索对方的用户名可直接发起聊天。
   
### 应用截图

### 搭建环境

### SeeU 的工程结构
* SeeU 的架构模型参考了 Android Passive MVC 架构(但是去掉了 Listener 模块)，有兴趣的可以[参考这里](http://pan.baidu.com/s/1mhoms4o)以及这篇文章[《Android Passive MVC 架构》](http://www.jianshu.com/p/1af58b6e8930)
  
  - Application —— 主要作用是 jmessage-sdk 的初始化以及 Notification 的相关设置；
  
  - activity 包 —— JChat 的 Activity 的集合，主要负责绑定 Controller 和 View，以及界面的跳转；
  
  - controller 包 —— 主要负责事件的点击、数据处理等，是 Activity 和 View 的中间层；
  
  - view 包 —— 主要负责界面的展示、控件的初始化、点击事件的绑定等；
  
  - adapter 包 —— 主要负责 ListView 或 GridView 的数据处理；
  
  - tools 包 —— 工具类的集合。
  
### SeeU项目中用到的部分开源项目
- ActiveAndroid —— 对象关系映射（ORM）操作数据库
- EventBus —— 在组件之间传递消息
- Picasso —— 加载、显示、缓存图片
- 百度地图

### 特别说明
* SeeU 是一个单独的完整项目,不能作为module添加到其他项目中

### 相关文档
