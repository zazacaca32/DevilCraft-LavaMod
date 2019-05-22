package net.minecraft.client.addon.Tchat;

import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntitySmokeFX;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy
{
    public void registerRenderers()
    {
        MinecraftForgeClient.registerItemRenderer(lavabow.lbow.itemID, new Render3Dlbow());
        MinecraftForgeClient.registerItemRenderer(lavabow.lsword.itemID, new Render3DlswordUltima());
        MinecraftForgeClient.registerItemRenderer(lavabow.lswordRB.itemID, new Render3DlswordRBnew());
        MinecraftForgeClient.registerItemRenderer(lavabow.lswordLava.itemID, new Render3DlswordLava());
    }

    public void runSpawnParticle(World world, double x, double y, double z, int ID, int counter)
    {
        Random rand = new Random();
        int i;

        switch (ID)
        {
            case 1:
                for (i = 0; i < counter; ++i)
                {
                    Minecraft.getMinecraft().effectRenderer.addEffect(new net.minecraft.client.particle.EntityFlameFX(world, x + rand.nextGaussian() / 2.0D, y + rand.nextGaussian() / 2.0D, z + rand.nextGaussian() / 2.0D, 0.0D, 0.0D, 0.0D));
                }

                Minecraft.getMinecraft().effectRenderer.updateEffects();

            case 2:
                for (i = 0; i < counter; ++i)
                {
                    Minecraft.getMinecraft().effectRenderer.addEffect(new EntitySmokeFX(world, x + rand.nextGaussian() / 2.0D, y + rand.nextGaussian() / 2.0D, z + rand.nextGaussian() / 2.0D, 0.0D, 0.0D, 0.0D));
                }

                Minecraft.getMinecraft().effectRenderer.updateEffects();
        }
    }
}
