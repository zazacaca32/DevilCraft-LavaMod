package net.minecraft.client.addon.lavamobs;

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
import net.minecraft.util.MathHelper;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Post;
import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.event.ForgeSubscribe;

public class EventHandler
{
    private float farPlaneDistance;
    private float rendererUpdateCount;
    public static boolean flag = false;
    long tick = 0L;

    @SideOnly(Side.CLIENT)
    @ForgeSubscribe
    public void OverlayEvent(Post event)
    {
        if (event.type == ElementType.ALL)
        {
            if (flag)
            {
                flag = false;
                this.tick = System.currentTimeMillis() + 10000L;
            }

            if (this.tick > System.currentTimeMillis())
            {
                Minecraft mc = Minecraft.getMinecraft();
                mc.thePlayer.cameraYaw += MathHelper.cos(mc.theWorld.rand.nextFloat());
            }
        }
    }

    @SideOnly(Side.CLIENT)
    @ForgeSubscribe
    public void loadingSounds(SoundLoadEvent event)
    {
        event.manager.soundPoolSounds.isGetRandomSound = false;
        String[] soundFiles = new String[] {"attackguard.ogg", "blockraid.ogg", "catacomb.ogg", "deathraidboss.ogg", "earthquake.ogg", "hurtraidboss.ogg"};

        for (int i = 0; i < soundFiles.length; ++i)
        {
            try
            {
                File var5 = this.extractAndLoadResource(Minecraft.getMinecraft(), soundFiles[i]);
                event.manager.soundPoolSounds.addSound("lavamobs/" + soundFiles[i], var5);
                FMLCommonHandler.instance().getFMLLogger().log(Level.INFO, "[lavamobs] Loaded sound " + soundFiles[i]);
            }
            catch (Exception var51)
            {
                FMLCommonHandler.instance().getFMLLogger().log(Level.SEVERE, "[lavamobs] Could not load sound " + soundFiles[i], var51.toString());
            }
        }

        event.manager.soundPoolSounds.isGetRandomSound = true;
    }

    @SideOnly(Side.CLIENT)
    private File extractAndLoadResource(Minecraft mc, String resName) throws Exception
    {
        File resDestDir = new File(mc.mcDataDir, "/resources/lavamobs/sound/");

        if (!resDestDir.exists())
        {
            resDestDir.mkdirs();
        }

        File resFile;

        if (!(resFile = new File(resDestDir, resName)).exists())
        {
            InputStream streamIn = LavaModMobs.class.getResourceAsStream("/lavamobs/sound/" + resName);
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
