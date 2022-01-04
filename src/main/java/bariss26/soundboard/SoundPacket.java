package bariss26.soundboard;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;

public class SoundPacket implements IMessage {

	public int player;
	public String name;
	
	public SoundPacket() {
		
	}
	
	public SoundPacket(int player, String name) {
		this.player = player;
		this.name = name;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		player = buf.readInt();
		name = ByteBufUtils.readUTF8String(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(player);
		ByteBufUtils.writeUTF8String(buf, name);
	}
	
	public static class Handler implements IMessageHandler<SoundPacket, IMessage> {

		@Override
		public IMessage onMessage(SoundPacket message, MessageContext ctx) {
			if(ctx.side==Side.CLIENT) {
				
			} else {
				for(SoundToPlay stp : Main.sounds) {
					if(message.name.equals(stp.name)) {
						EntityPlayer p = ctx.getServerHandler().playerEntity;
						p.worldObj.playSoundAtEntity(p, "soundboard:"+stp.name, stp.volume, stp.pitch);
					}
				}
			}
			return null;
		}
		
	}

}
