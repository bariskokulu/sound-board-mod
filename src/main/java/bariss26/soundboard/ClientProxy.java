package bariss26.soundboard;

import java.io.File;
import java.util.List;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.ObfuscationReflectionHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IResourcePack;

public class ClientProxy extends CommonProxy {

	@Override
	public void preinit() {
		FMLCommonHandler.instance().bus().register(new KeyHandler());
	}

	@Override
	public void init() {
		KeyHandler.registerKeys();
		try {
			File fsb = new File(Main.config.toPath()+"/soundboard");
			if(!fsb.exists()) {
				fsb.mkdir();
			} else {
				List<IResourcePack> defaultResourcePacks = ObfuscationReflectionHelper.getPrivateValue(Minecraft.class, Minecraft.getMinecraft(), "defaultResourcePacks" , "Field_110449_ao", "field_110449_ao");
				defaultResourcePacks.add(new SoundsFolder(fsb));
				Main.loadKeysCreateIfDoesntExistIWonderIfThereIsALengthLimitToMethodNames();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
