package com.featherwhisker.OldGL.mixins;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.SplashTextRenderer;
import net.minecraft.client.resource.SplashTextResourceSupplier;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.Objects;

import static com.featherwhisker.OldGL.Config.OGL_SPLASH;

@Environment(EnvType.CLIENT)
@Mixin(SplashTextResourceSupplier.class)
public class SplashTextResourceSupplierMixin {
    @Shadow
    private List<String> splashTexts;
    @Shadow
    private static Random RANDOM;
    @Inject(method = "get", at = @At(value = "INVOKE",remap = false, target = "java/util/List.get (I)Ljava/lang/Object;", shift = At.Shift.AFTER), cancellable = true)
    public void injected(CallbackInfoReturnable ci) {
        String splash = this.splashTexts.get(RANDOM.nextInt(this.splashTexts.size()));
        if (Objects.equals(splash, "Now on OpenGL 3.2 core profile!")) {
            ci.setReturnValue(new SplashTextRenderer(OGL_SPLASH));
        } else {
            ci.setReturnValue(new SplashTextRenderer(splash));
            //ci.setReturnValue(new SplashTextRenderer("debug"));
        }
        ci.cancel();
    }
}
