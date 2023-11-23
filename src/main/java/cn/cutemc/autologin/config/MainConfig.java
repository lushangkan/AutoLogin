package cn.cutemc.autologin.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = "autologin")
public class MainConfig implements ConfigData {

    public boolean mainToggle = true;

    @ConfigEntry.Gui.Tooltip()
    public String serverAddresses = "";

    public String password = "";

    @ConfigEntry.Gui.Tooltip()
    public String promptRequireLogin = "";

    @ConfigEntry.Gui.Tooltip()
    public String promptRequireRegister = "";

    @ConfigEntry.Gui.Tooltip()
    public String loginCommand = "/l %p";

    @ConfigEntry.Gui.Tooltip()
    public String registerCommand = "/reg %p %p";

}
