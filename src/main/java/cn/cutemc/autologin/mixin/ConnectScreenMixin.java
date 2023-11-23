package cn.cutemc.autologin.mixin;

import cn.cutemc.autologin.AutoLogin;
import cn.cutemc.autologin.records.ServerStatus;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ConnectScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.network.ServerAddress;
import net.minecraft.client.network.ServerInfo;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.regex.Pattern;

@Mixin(ConnectScreen.class)
public class ConnectScreenMixin {

    @Inject(method = "connect(Lnet/minecraft/client/gui/screen/Screen;Lnet/minecraft/client/MinecraftClient;Lnet/minecraft/client/network/ServerAddress;Lnet/minecraft/client/network/ServerInfo;Z)V", at = @At(value = "TAIL"), locals = LocalCapture.CAPTURE_FAILHARD)
    private static void connectInject(Screen screen, MinecraftClient client, ServerAddress address, ServerInfo info, boolean quickPlay, CallbackInfo ci, ConnectScreen connectScreen) {
        if (address != null) {
            String addressStr = address.getAddress();
            Pattern pattern = Pattern.compile(":[0-9]{1,5}", Pattern.MULTILINE);
            // address没有端口则加上默认端口
            addressStr = pattern.matcher(addressStr).find() ? addressStr : addressStr + ":25565";

            AutoLogin.server = new ServerStatus(addressStr, false, false);
        };
    }

}
