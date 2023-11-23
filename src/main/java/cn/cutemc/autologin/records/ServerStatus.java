package cn.cutemc.autologin.records;

import lombok.Setter;

public record ServerStatus(String address, boolean isLogin, boolean isRegister) {

}
