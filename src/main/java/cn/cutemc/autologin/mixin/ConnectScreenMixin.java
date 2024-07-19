package cn.cutemc.autologin.mixin;

import cn.cutemc.autologin.AutoLogin;
import cn.cutemc.autologin.records.ServerStatus;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.multiplayer.ConnectScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.network.ServerAddress;
import net.minecraft.client.network.ServerInfo;
import net.minecraft.client.network.CookieStorage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import org.jetbrains.annotations.Nullable;

import java.util.regex.Pattern;

@Mixin(ConnectScreen.class)
public class ConnectScreenMixin {
    @Inject(method = "connect(Lnet/minecraft/client/gui/screen/Screen;Lnet/minecraft/client/MinecraftClient;Lnet/minecraft/client/network/ServerAddress;Lnet/minecraft/client/network/ServerInfo;ZLnet/minecraft/client/network/CookieStorage;)V", at = @At(value = "TAIL"), locals = LocalCapture.CAPTURE_FAILHARD)
    private static void connectInject(Screen screen, MinecraftClient client, ServerAddress address, ServerInfo info, boolean quickPlay, @Nullable CookieStorage cookieStorage, CallbackInfo ci) {
        if (address != null) {
            String addressStr = address.getAddress();
            Pattern pattern = Pattern.compile(":[0-9]{1,5}", Pattern.MULTILINE);
            // If address has no port, add the default port
            addressStr = pattern.matcher(addressStr).find() ? addressStr : addressStr + ":25565";

            AutoLogin.server = new ServerStatus(addressStr, false, false);
        };
    }

}
