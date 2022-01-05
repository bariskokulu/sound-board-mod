package bariss26.soundboard;

import java.lang.reflect.Field;
import java.util.Set;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.audio.SoundRegistry;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;

public class GuiSoundBoardKeybinds extends GuiScreen {

	int page;
	SoundToPlay chosenSound;

	@Override
	public void initGui() {
		try {
			SoundHandler sh = Minecraft.getMinecraft().getSoundHandler();
			Field field = sh.getClass().getDeclaredField("sndRegistry");
			field.setAccessible(true);
			SoundRegistry sr = (SoundRegistry) field.get(sh);
			Set<ResourceLocation> k = sr.getKeys();
			for(ResourceLocation s : k) {
				if(s.getResourceDomain().equals("soundboard")&&!s.getResourcePath().endsWith(".png")) {
					boolean alreadyin = false;
					for(SoundToPlay stp : Main.sounds) {
						if(stp.name.equals(s.getResourcePath())) {
							alreadyin = true;
						}
						try {
							String ss = Keyboard.getKeyName(stp.key);
						} catch(Exception e) {
							stp.key = Keyboard.KEY_NONE;
						}
					}
					if(!alreadyin) {
						Main.sounds.add(new SoundToPlay(Keyboard.KEY_NONE, s.getResourcePath()));
					}
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		super.initGui();
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		GL11.glPushMatrix();
		TextureManager tm = Minecraft.getMinecraft().getTextureManager();
		
		GL11.glColor4d(0, 0, 0, 0.8);
		tm.bindTexture(new ResourceLocation("soundboard:textures/gui/solidwhite.png"));
		drawTexturedModalRect(0, 0, 0, 0, width, height);
		
		buttonList.clear();
		int i = 0;
		int lastid = 0;
		buttonList.add(new GuiButton(-1, width/2-202, height/2-114, 190, 20, "Prev"));
		buttonList.add(new GuiButton(-2, width/2+12, height/2-114, 190, 20, "Next"));
		drawString(fontRendererObj, page+"", width/2-fontRendererObj.getStringWidth(page+"")/2, height/2-114+fontRendererObj.FONT_HEIGHT/2+2, 0xffffff);
//		System.out.println();
		for(SoundToPlay stp : Main.sounds) {
			if(page*20<=i&&i<(page+1)*20) {
				++lastid;
//				System.out.println((lastid));
				int x = lastid<=10?width/2-202:width/2+2;
				int y = lastid<=10?lastid*20:(lastid-10)*20;
				y+=height/2-107;
				GuiButtonSoundBoardKeybind b = new GuiButtonSoundBoardKeybind(stp, lastid, x, y, 200, 20, stp.name+" : "+Keyboard.getKeyName(stp.key));
				if(stp.equals(chosenSound)) {
					b.packedFGColour = 69420;
				} else {
					b.packedFGColour = 0;
				}
				buttonList.add(b);
			}
			i++;
		}
		GL11.glColor4d(1,1,1,1);
		super.drawScreen(mouseX, mouseY, partialTicks);
		
		GL11.glPopMatrix();
	}

	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) {
		if(mouseButton==0) chosenSound = null;
		super.mouseClicked(mouseX, mouseY, mouseButton);
	}

	@Override
	protected void actionPerformed(GuiButton b) {
		super.actionPerformed(b);
		if(b instanceof GuiButtonSoundBoardKeybind) {
			chosenSound = ((GuiButtonSoundBoardKeybind)b).stp;
		} else if(b.id==-1) {
			page--;
			if(page<0) page = 0;
		} else if(b.id==-2) {
			page++;
		}
	}

	@Override
	protected void keyTyped(char typedChar, int keyCode) {
		if(keyCode!=Keyboard.KEY_E) super.keyTyped(typedChar, keyCode);
		if(chosenSound!=null) {
			chosenSound.key = keyCode;
			chosenSound = null;
			Main.saveKeys();
		}
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}

}
