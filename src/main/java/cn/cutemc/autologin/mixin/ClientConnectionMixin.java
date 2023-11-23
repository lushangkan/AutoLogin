package cn.cutemc.autologin.mixin;

import cn.cutemc.autologin.AutoLogin;
import net.minecraft.network.ClientConnection;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientConnection.class)
public class ClientConnectionMixin {

    @Inject(method = "disconnect(Lnet/minecraft/text/Text;)V", at = @At("RETURN"))
    public void disconnectInject(Text disconnectReason, CallbackInfo ci) {
        AutoLogin.server = null;
    }
}
