package cn.cutemc.autologin;

import cn.cutemc.autologin.config.ModConfig;
import cn.cutemc.autologin.listeners.SystemMsgListener;
import cn.cutemc.autologin.records.ServerStatus;
import lombok.extern.log4j.Log4j2;
import net.fabricmc.api.ClientModInitializer;

@Log4j2
public class AutoLogin implements ClientModInitializer {

    public static ModConfig modConfig;

    public static volatile ServerStatus server = null;

    @Override
    public void onInitializeClient() {

        log.info("Initializing AutoLogin...");

        log.info("Loading config...");
        modConfig = new ModConfig();

        log.info("Registering listeners...");
        new SystemMsgListener();

        log.info("AutoLogin initialized!");
    }

}
