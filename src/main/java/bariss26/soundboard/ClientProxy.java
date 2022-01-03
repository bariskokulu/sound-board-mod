package bariss26.soundboard;

import cpw.mods.fml.common.FMLCommonHandler;

public class ClientProxy extends CommonProxy {

	@Override
	public void launch() {
		FMLCommonHandler.instance().bus().register(new KeyHandler());
	}
	
}
