package bariss26.soundboard;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.logging.log4j.LogManager;

import com.google.common.collect.Sets;

import net.minecraft.client.resources.IResourcePack;
import net.minecraft.client.resources.data.IMetadataSection;
import net.minecraft.client.resources.data.IMetadataSerializer;
import net.minecraft.util.ResourceLocation;

public class SoundsFolder implements IResourcePack {

	public File f;
	
	public SoundsFolder() {
		
	}

	public SoundsFolder(File f) {
		this.f = f;
	}
	
	@Override
	public InputStream getInputStream(ResourceLocation p_110590_1_) throws IOException {
		String s = String.format("%s/%s/%s", new Object[] {"assets", p_110590_1_.getResourceDomain(), p_110590_1_.getResourcePath()}).replace(".mcmeta", "");
        return new BufferedInputStream(new FileInputStream(new File(f, s)));
	}

	@Override
	public boolean resourceExists(ResourceLocation p_110589_1_) {
		return true;
	}

    protected static String getRelativeName(File p_110595_0_, File p_110595_1_)
    {
        return p_110595_0_.toURI().relativize(p_110595_1_.toURI()).getPath();
    }
    
	@Override
	public Set getResourceDomains() {
        HashSet hashset = Sets.newHashSet();
        File file1 = new File(f, "assets/");

        if (file1.isDirectory())
        {
            File[] afile = file1.listFiles((java.io.FileFilter)DirectoryFileFilter.DIRECTORY);
            int i = afile.length;

            for (int j = 0; j < i; ++j)
            {
                File file2 = afile[j];
                String s = getRelativeName(file1, file2);

                if (!s.equals(s.toLowerCase()))
                {
                	LogManager.getLogger().warn("ResourcePack: ignored non-lowercase namespace: {} in {}", new Object[] {s, f});
                }
                else
                {
                    hashset.add(s.substring(0, s.length() - 1));
                }
            }
        }
        return hashset;
	}

	@Override
	public IMetadataSection getPackMetadata(IMetadataSerializer p_135058_1_, String p_135058_2_) throws IOException {
		return null;
	}

	@Override
	public BufferedImage getPackImage() throws IOException {
		return null;
	}

	@Override
	public String getPackName() {
		return "SoundBoard";
	}

}
