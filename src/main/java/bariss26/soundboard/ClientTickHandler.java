package bariss26.soundboard;

import java.lang.reflect.Field;
import java.util.Set;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.audio.SoundRegistry;

public class ClientTickHandler {

	public int lastRegisteredSoundCount;
	
	@SubscribeEvent
	public void asd(ClientTickEvent event) {
//		System.out.println("asdasdaddsadsa");
		try {
			SoundHandler sh = Minecraft.getMinecraft().getSoundHandler();
			Field field = sh.getClass().getDeclaredField("sndRegistry");
			field.setAccessible(true);
			SoundRegistry sr = (SoundRegistry) field.get(sh);
			Set s = sr.getKeys();
			if(lastRegisteredSoundCount!=s.size()) {
				lastRegisteredSoundCount = s.size();
				for(Object o : s) {
					System.out.println(o);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
