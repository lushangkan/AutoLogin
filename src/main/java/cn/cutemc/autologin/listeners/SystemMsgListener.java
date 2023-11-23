package cn.cutemc.autologin.listeners;

import cn.cutemc.autologin.AutoLogin;
import cn.cutemc.autologin.config.MainConfig;
import cn.cutemc.autologin.records.ServerStatus;
import com.mojang.authlib.GameProfile;
import lombok.extern.log4j.Log4j2;
import net.fabricmc.fabric.api.client.message.v1.ClientReceiveMessageEvents;
import net.fabricmc.fabric.api.message.v1.ServerMessageEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.QuickPlayLogger;
import net.minecraft.network.message.MessageType;
import net.minecraft.network.message.SignedMessage;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

@Log4j2
public class SystemMsgListener implements  ClientReceiveMessageEvents.Game {

    public SystemMsgListener() {
        ClientReceiveMessageEvents.GAME.register(this);
    }

    @Override
    public void onReceiveGameMessage(Text message, boolean overlay) {
        if (AutoLogin.server.isLogin() || AutoLogin.server.isRegister()) return;

        MainConfig config = AutoLogin.modConfig.mainConfig;

        MinecraftClient client = MinecraftClient.getInstance();

        List<String> serverAddresses = Arrays.stream(config.serverAddresses.split(",")).map((addr) -> {
            Pattern pattern = Pattern.compile(":[0-9]{1,5}", Pattern.MULTILINE);
            // address没有端口则加上默认端口
            return pattern.matcher(addr).find() ? addr : addr + ":25565";
        }).toList();

        if (!serverAddresses.contains(AutoLogin.server.address())) return;

        String msgStr = message.getString();

        if (
                !config.mainToggle
                || ((config.loginCommand.equals("") && config.promptRequireLogin.equals("")) && (config.registerCommand.equals("") && config.promptRequireRegister.equals("")))
                || config.password.equals("")
                || config.serverAddresses.equals("")
        ) return;

        boolean matchLogin = false;
        boolean matchRegister = false;

        if (!config.promptRequireLogin.equals("")) {
            List<String> words = List.of(config.promptRequireLogin.split(","));

            boolean match = true;

            for (String word : words) match = msgStr.contains(word);

            if (match) matchLogin = true;
        }

        if (!config.promptRequireRegister.equals("")) {
            List<String> words = List.of(config.promptRequireRegister.split(","));

            boolean match = true;

            for (String word : words) match = msgStr.contains(word);

            if (match) matchRegister = true;
        }

        if (matchLogin && matchRegister) {
            log.warn("Both login and register prompts matched! Please check your config!");
            log.warn("Run login command...");

            matchRegister = false;
        }

        if (!matchLogin && !matchRegister) return;

        if (client.player == null) return;

        if (matchLogin) {
            log.info("Login prompt matched! Logging in...");
            client.player.networkHandler.sendChatCommand(config.loginCommand.replace("%p", config.password).substring(1));
            AutoLogin.server = new ServerStatus(AutoLogin.server.address(), true, false);
        }

        if (matchRegister) {
            log.info("Register prompt matched! Registering...");
            client.player.networkHandler.sendChatCommand(config.registerCommand.replace("%p", config.password).substring(1));
            AutoLogin.server = new ServerStatus(AutoLogin.server.address(), false, true);
        }

    }
}

