package com.notbronwyn.OldGL.mixins;


import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.client.util.Window;
import org.spongepowered.asm.mixin.injection.At;
import static org.lwjgl.glfw.GLFW.*;
import static com.notbronwyn.OldGL.Config.*;
import static com.notbronwyn.OldGL.Output.*;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Environment(EnvType.CLIENT)
@Mixin(Window.class)
public class WindowMixin {
	// Inject our function right before the window is created
	@Inject(method = "<init>", at = @At(value = "INVOKE",remap = false, target = "org/lwjgl/glfw/GLFW.glfwCreateWindow (IILjava/lang/CharSequence;JJ)J", shift = At.Shift.BEFORE))
	public void injected(CallbackInfo ci) {
		// Throw out whatever vanilla tells GLFW
		glfwDefaultWindowHints();

		// Set API version
		glfwWindowHint(GLFW_CLIENT_API, GLFW_OPENGL_API);

		// Set OpenGL version
		glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, OGL_MAJOR);
		glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, OGL_MINOR);
		glfwWindowHint(GLFW_OPENGL_PROFILE, OGL_PROFILE); // making sure it doesnt default to the core profile

		// Misc OpenGL hints
		glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GLFW_FALSE);
		glfwWindowHint(GLFW_CONTEXT_CREATION_API, GLFW_NATIVE_CONTEXT_API);

		print("Patched window hints!");
	}
}