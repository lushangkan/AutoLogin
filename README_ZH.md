# AutoLogin

一个Fabric Mod，用于进入有认证的服务器时自动登录。

[英文版](README.md)

## 使用方法

### 修改配置文件:

- 手动修改位于`.minecraft/config/autologin.json`的配置文件，例如：
    ```json
    {
      "mainToggle": true,
      "serverAddresses": "play.xxx.net,play.yyy.com,mc.xxx.cn",
      "password": "你的密码",
      "promptRequireLogin": "登录",
      "promptRequireRegister": "注册",
      "loginCommand": "/l %p",
      "registerCommand": "/reg %p %p"
    }
    ```

- 在 '模组' 选项中找到AutoLogin修改。

### 配置文件说明:

- `mainToggle`: 是否启用AutoLogin
  

- `serverAddresses`: 启用自动登录的服务器地址，使用','分割多个地址
   

- `password`: 密码
  

- `promptRequireLogin`: 服务器需要玩家登录时发送的系统消息的关键词，例如'需要登录'，使用','分割多个关键词
  

- `promptRequireRegister`: 服务器需要玩家注册时发送的系统消息的关键词，例如'需要注册'，使用','分割多个关键词
  

- `loginCommand`: 登录命令，使用'%p'代替密码
  

- `registerCommand`: 注册命令，使用'%p'代替密码

## 注意事项:
- 此Mod依赖于Fabric API，Cloth-Conf
- 此Mod仅在Minecraft 1.20.2版本测试过，其他版本未经测试

## 许可证:
此项目使用GPLv3许可证，详情请参阅[LICENSE](LICENSE)文件。

---

### 支持我就帮我买杯咖啡吧!


<a href="https://afdian.net/a/lushangkan"><img src="https://s2.loli.net/2023/11/21/iAuWGhQz4gFpalV.jpg" alt="afdian" width="300"/></a>

