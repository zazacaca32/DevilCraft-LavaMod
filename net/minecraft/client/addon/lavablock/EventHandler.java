package net.minecraft.client.addon.lavablock;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.event.ForgeSubscribe;

public class EventHandler
{
    @SideOnly(Side.CLIENT)
    @ForgeSubscribe
    public void loadingSounds(SoundLoadEvent event)
    {
        event.manager.soundPoolSounds.isGetRandomSound = false;
        String[] soundFiles = new String[] {"sound1.ogg", "newyear.ogg"};

        for (int i = 0; i < soundFiles.length; ++i)
        {
            try
            {
                File var5 = this.extractAndLoadResource(Minecraft.getMinecraft(), soundFiles[i]);
                event.manager.soundPoolSounds.addSound("lavablock/" + soundFiles[i], var5);
                FMLCommonHandler.instance().getFMLLogger().log(Level.INFO, "[lavablock] Loaded sound " + soundFiles[i]);
            }
            catch (Exception var51)
            {
                FMLCommonHandler.instance().getFMLLogger().log(Level.SEVERE, "[lavablock] Could not load sound " + soundFiles[i], var51.toString());
            }
        }

        event.manager.soundPoolSounds.isGetRandomSound = true;
    }

    @SideOnly(Side.CLIENT)
    private File extractAndLoadResource(Minecraft mc, String resName) throws Exception
    {
        File resDestDir = new File(mc.mcDataDir, "/resources/lavablock/sound/");

        if (!resDestDir.exists())
        {
            resDestDir.mkdirs();
        }

        File resFile = new File(resDestDir, resName);

        if (!resFile.exists())
        {
            InputStream streamIn = ModBlocks.class.getResourceAsStream("/lavablock/sound/" + resName);
            BufferedOutputStream streamOut = new BufferedOutputStream(new FileOutputStream(resFile));
            byte[] buffer = new byte[1024];
            boolean var8 = false;
            int len;

            while ((len = streamIn.read(buffer)) >= 0)
            {
                streamOut.write(buffer, 0, len);
            }

            streamIn.close();
            streamOut.close();
        }

        if (resFile.length() < 3L)
        {
            throw new IOException();
        }
        else
        {
            return resFile;
        }
    }
}
