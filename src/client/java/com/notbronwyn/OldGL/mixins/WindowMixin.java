package com.notbronwyn.OldGL.mixins;


import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.client.util.Window;
import org.spongepowered.asm.mixin.injection.At;
import static org.lwjgl.glfw.GLFW.*;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Environment(EnvType.CLIENT)
@Mixin(Window.class)
public class WindowMixin {
	@Inject(method = "<init>", at = @At(value = "INVOKE",remap = false, target = "org/lwjgl/glfw/GLFW.glfwWindowHint (II)V", shift = At.Shift.AFTER))
	public void injected(CallbackInfo ci) {
		glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
		glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 0);
		glfwWindowHint(GLFW_OPENGL_PROFILE,0);
	}
}