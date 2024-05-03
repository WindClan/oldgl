package com.notbronwyn.OldGL.mixins;


import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.client.util.Window;
import org.spongepowered.asm.mixin.injection.At;
import static org.lwjgl.glfw.GLFW.*;
import static com.notbronwyn.OldGL.Config.*;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Environment(EnvType.CLIENT)
@Mixin(Window.class)
public class WindowMixin {
	@Inject(method = "<init>", at = @At(value = "INVOKE",remap = false, target = "org/lwjgl/glfw/GLFW.glfwWindowHint (II)V", shift = At.Shift.AFTER))
	public void injected(CallbackInfo ci) {
		glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, OGL_MAJOR);
		glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, OGL_MINOR);
		glfwWindowHint(GLFW_OPENGL_PROFILE, OGL_PROFILE);
	}
}