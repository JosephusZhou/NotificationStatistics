# Notification Statistics

2019-03 开发，不再维护

**实验性代码，申请通知读取权限，获取微信通知内容并存储，具有部分防撤回的意义，可以查看被撤回的消息，需要保持后台运行**

### 实验特性

* 学习 Android 通知权限和读取等
* 使用 JetPack 中的组件，如 LiveData、Room、Navigation 等，但是小型项目并没有特别好用，总感觉别扭
 
 
### Bug

* Navigation 中的 Fragment 每次切换都重复创建，这官方组件有点迷
* Paging 无法响应最新数据变化