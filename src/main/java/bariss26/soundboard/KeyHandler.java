package bariss26.soundboard;

import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.logging.log4j.LogManager;
import org.lwjgl.input.Keyboard;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import bariss26.soundboard.Main.SoundToPlay;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.audio.SoundList;
import net.minecraft.client.audio.SoundListSerializer;
import net.minecraft.client.audio.SoundRegistry;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;

public class KeyHandler {

	@SubscribeEvent
	public void asd(KeyInputEvent event) {
		for(SoundToPlay stp : Main.sounds) {
			if(Keyboard.isKeyDown(stp.key)) {
				NetworkHandler.channel.sendToServer(new SoundPacket(Minecraft.getMinecraft().thePlayer.getEntityId(), stp.name));
			}
		}

		if(Keyboard.isKeyDown(Keyboard.KEY_NUMPAD1)) {


//	        this.sndManager.reloadSoundSystem();
//	        this.sndRegistry.clearMap();
	        IResourceManager p_110549_1_ = Minecraft.getMinecraft().getResourceManager();
	        Iterator iterator = p_110549_1_.getResourceDomains().iterator();

	        while (iterator.hasNext())
	        {
	            String s = (String)iterator.next();
	            

	            System.out.println(s);
	            try
	            {
	                List list = p_110549_1_.getAllResources(new ResourceLocation(s, "sounds.json"));
	                Iterator iterator1 = list.iterator();

	                while (iterator1.hasNext())
	                {
	                    IResource iresource = (IResource)iterator1.next();
	                    Gson field_147699_c = (new GsonBuilder()).registerTypeAdapter(SoundList.class, new SoundListSerializer()).create();
	                    try
	                    {
	                    	ParameterizedType field_147696_d = new ParameterizedType()
	                        {
	                            private static final String __OBFID = "CL_00001148";
	                            public Type[] getActualTypeArguments()
	                            {
	                                return new Type[] {String.class, SoundList.class};
	                            }
	                            public Type getRawType()
	                            {
	                                return Map.class;
	                            }
	                            public Type getOwnerType()
	                            {
	                                return null;
	                            }
	                        };
	                    	
	                        Map map = (Map)field_147699_c.fromJson(new InputStreamReader(iresource.getInputStream()), field_147696_d);
	                        Iterator iterator2 = map.entrySet().iterator();

	                        while (iterator2.hasNext())
	                        {
	                            Entry entry = (Entry)iterator2.next();
//	                            System.out.println(new ResourceLocation(s, (String)entry.getKey())+""+(SoundList)entry.getValue());
//	                            this.loadSoundResource(new ResourceLocation(s, (String)entry.getKey()), (SoundList)entry.getValue());
	                        }
	                    }
	                    catch (RuntimeException runtimeexception)
	                    {
	                    	System.out.println("sdasddadda");
//	                        logger.warn("Invalid sounds.json", runtimeexception);
	                    }
	                }
	            }
	            catch (IOException e)
	            {
	            	e.printStackTrace();
	            }
	        }
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_NUMPAD0)) {
			try {
				SoundHandler sh = Minecraft.getMinecraft().getSoundHandler();
				Field field = sh.getClass().getDeclaredField("sndRegistry");
				field.setAccessible(true);
				SoundRegistry sr = (SoundRegistry) field.get(sh);
				Set s = sr.getKeys();
				for(Object o : s) {
					System.out.println(o);
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}
