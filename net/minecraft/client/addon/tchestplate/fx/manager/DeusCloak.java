package net.minecraft.client.addon.tchestplate.fx.manager;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.addon.tchestplate.LavaChestPlate;
import net.minecraft.client.addon.tchestplate.player.ExtendedPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.src.ModLoader;

public class DeusCloak extends FX_base
{
    int ticks = 0;
    int intervalMills = 2;

    public DeusCloak()
    {
    }

    public DeusCloak(int var1, byte var2)
    {
        super(var1, var2);
    }

    public void sh(Entity var1)
    {
        double var2 = var1.posX;
        double var4 = var1.posY;
        double var6 = var1.posZ;

        if (var1.entityId != ModLoader.getMinecraftInstance().thePlayer.entityId)
        {
            var4 = var1.boundingBox.minY + (double)(var1.height / 2.0F) + 0.25D;
        }

        LavaChestPlate.proxy.sparkle((float)var2, (float)var4 + 0.4F, (float)var6, 1.0F, 1, 0.1F, 55);
    }

    public void Initialization(ExtendedPlayer var1, EntityPlayer var2, Side var3)
    {
    }

    public void Update(ExtendedPlayer var1, EntityPlayer var2, Side var3)
    {
        if (var3 == Side.CLIENT && this.ticks++ % this.intervalMills == 0)
        {
            this.sh(var2);
        }
    }

    public void Finalization(ExtendedPlayer var1, EntityPlayer var2, Side var3)
    {
    }

    public byte getIDeffect()
    {
        return (byte)3;
    }
}
