package bariss26.soundboard;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends CommonProxy {

	@Override
	public void launch() {
		FMLCommonHandler.instance().bus().register(new KeyHandler());
		FMLCommonHandler.instance().bus().register(new ClientTickHandler());
	}
	
}
