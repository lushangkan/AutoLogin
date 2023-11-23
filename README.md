# AutoLogin

A Fabric Mod that automatically logs in when entering a server with authentication.

[中文版](README_ZH.md)

## How to use

### Modify the configuration file:

- Manually modify the configuration file located at `.minecraft/config/autologin.json`, for example:
    ```json
    {
      "mainToggle": true,
      "serverAddresses": "play.xxx.net,play.yyy.com,mc.xxx.cn",
      "password": "your password",
      "promptRequireLogin": "login",
      "promptRequireRegister": "register",
      "loginCommand": "/l %p",
      "registerCommand": "/reg %p %p"
    }
    ```

- Find and modify AutoLogin in the 'Mods' option.

### Configuration file explanation:

- `mainToggle`: Whether to enable AutoLogin


- `serverAddresses`: The server addresses that enable auto login, use ',' to separate multiple addresses


- `password`: Password


- `promptRequireLogin`: The keywords of the system message sent by the server when the player needs to log in, such as 'login required', use ',' to separate multiple keywords


- `promptRequireRegister`: The keywords of the system message sent by the server when the player needs to register, such as 'register required', use ',' to separate multiple keywords


- `loginCommand`: Login command, use '%p' to replace the password


- `registerCommand`: Register command, use '%p' to replace the password

## Precautions:
- This Mod depends on Fabric API, Cloth-Conf
- This Mod has only been tested on Minecraft 1.20.2 version, other versions have not been tested

## License:
This project uses GPLv3 license, please refer to [LICENSE](LICENSE) file for details.

---

### Support me by buying me a cup of coffee!


<a href="https://afdian.net/a/lushangkan"><img src="https://s2.loli.net/2023/11/21/iAuWGhQz4gFpalV.jpg" alt="afdian" width="300"/></a>

