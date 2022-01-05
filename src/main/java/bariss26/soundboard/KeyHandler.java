package bariss26.soundboard;

import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.lwjgl.input.Keyboard;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.audio.SoundList;
import net.minecraft.client.audio.SoundListSerializer;
import net.minecraft.client.audio.SoundRegistry;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.ResourceLocation;

public class KeyHandler {
	
	static KeyBinding keyKeybinds = new KeyBinding("Keybinds", Keyboard.KEY_K, "SoundBoard");
	
	public static void registerKeys() {
		ClientRegistry.registerKeyBinding(keyKeybinds);
	}

	@SubscribeEvent
	public void asd(KeyInputEvent event) {
		for(SoundToPlay stp : Main.sounds) {
			if(Keyboard.isKeyDown(stp.key)) {
				NetworkHandler.channel.sendToServer(new SoundPacket(Minecraft.getMinecraft().thePlayer.getEntityId(), stp.name));
			}
		}
		if(keyKeybinds.isPressed()) {
			Minecraft.getMinecraft().displayGuiScreen(new GuiSoundBoardKeybinds());
		}
	}
	
}
