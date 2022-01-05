package bariss26.soundboard;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IResourcePack;

@Mod(name="SoundBoard",version="1.0.0 TEST",modid="soundboard")
public class Main {

	public static ArrayList<SoundToPlay> sounds = new ArrayList<SoundToPlay>();

	@SidedProxy(clientSide = "bariss26.soundboard.ClientProxy", serverSide = "bariss26.soundboard.CommonProxy")
	public static CommonProxy proxy;

	static File config;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		config = event.getModConfigurationDirectory();
		NetworkHandler.launch();
		proxy.preinit();
	}


	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		try {
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void loadKeysCreateIfDoesntExistIWonderIfThereIsALengthLimitToMethodNames() {
		try {
			File fsb = new File(config.toPath()+"/soundboard");
			File fc = new File(fsb.toPath()+"/keys.json");
			Gson gson = new Gson();
			if(fc.exists()) {
				BufferedReader br = Files.newBufferedReader(fc.toPath());
				ArrayList<SoundToPlay> l = gson.fromJson(br, new TypeToken<ArrayList<SoundToPlay>>() {}.getType());
				br.close();
				if(l!=null) {
					sounds = l;
				}
			} else {
				BufferedWriter bf = Files.newBufferedWriter(fc.toPath());
				gson.toJson(sounds, bf);
				bf.close();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void saveKeys() {
		try {
			File fsb = new File(config.toPath()+"/soundboard");
			File fc = new File(fsb.toPath()+"/keys.json");
			Gson gson = new Gson();
//			if(fc.exists()) {
//				BufferedReader br = Files.newBufferedReader(fc.toPath());
//				ArrayList<SoundToPlay> l = gson.fromJson(br, new TypeToken<ArrayList<SoundToPlay>>() {}.getType());
//				br.close();
//				if(l!=null) {
//					sounds = l;
//				}
//			} else {
				BufferedWriter bf = Files.newBufferedWriter(fc.toPath());
				gson.toJson(sounds, bf);
				bf.close();
//			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
