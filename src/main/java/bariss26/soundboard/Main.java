package bariss26.soundboard;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.audio.SoundRegistry;
import net.minecraft.client.resources.FolderResourcePack;
import net.minecraft.client.resources.IResourcePack;

@Mod(name="SoundBoard",version="1.0.0",modid="soundboard")
public class Main {
	
	public static ArrayList<SoundToPlay> sounds = new ArrayList<SoundToPlay>();
	
	public class SoundToPlay {
		public int key;
		public String name;
		public SoundToPlay(int key, String name) {
			this.key = key;
			this.name = name;
		}
	}
	
	@SidedProxy(clientSide = "bariss26.soundboard.ClientProxy", serverSide = "bariss26.soundboard.CommonProxy")
	public static CommonProxy proxy;

	File config;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		config = event.getModConfigurationDirectory();
		NetworkHandler.launch();
		proxy.launch();
	}


	@EventHandler
	public void init(FMLInitializationEvent event) {
		try {
			File fsb = new File(config.toPath()+"/soundboard");
			if(!fsb.exists()) {
				fsb.mkdir();
			} else {
				File fa = new File(config.toPath()+"/soundboard.zip");
//				File fa = new File(fsb.toPath()+"/assets");
				if(fa.exists()) {
				     List<IResourcePack> defaultResourcePacks = ObfuscationReflectionHelper.getPrivateValue(Minecraft.class, Minecraft.getMinecraft(), "defaultResourcePacks" , "Field_110449_ao");

				     defaultResourcePacks.add(new SoundsFolder(fsb));
				     
//					Field field = FMLClientHandler.class.getDeclaredField("resourcePackList"); 
//					field.setAccessible(true);
//					List<IResourcePack> l = (List<IResourcePack>) field.get(FMLClientHandler.instance());
//					l.add(new FileResourcePack(fa));
				}
				File fc = new File(fsb.toPath()+"/keys.json");
				if(fc.exists()) {

				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
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
