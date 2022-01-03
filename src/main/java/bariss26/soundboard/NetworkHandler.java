package bariss26.soundboard;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

public class NetworkHandler {

	public static SimpleNetworkWrapper channel;
	
	public static void launch() {
		channel = NetworkRegistry.INSTANCE.newSimpleChannel("soundboard");
		channel.registerMessage(SoundPacket.Handler.class, SoundPacket.class, 0, Side.CLIENT);
		channel.registerMessage(SoundPacket.Handler.class, SoundPacket.class, 1, Side.SERVER);
	}
	
}
