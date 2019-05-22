package net.minecraft.client.addon.tchestplate.fx.manager;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.addon.tchestplate.LavaChestPlate;
import net.minecraft.client.addon.tchestplate.player.ExtendedPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class Shock extends FX_base
{
    int ticks = 0;
    int intervalMills = 2;

    public Shock()
    {
    }

    public Shock(int var1, byte var2)
    {
        super(var1, var2);
    }

    public void sh(Entity var1)
    {
        byte var2 = 1;
        double var3 = var1.posX;
        double var5 = var1.boundingBox.minY + (double)var1.height + 0.05D;
        double var7 = var1.posZ;
        double var9 = 0.39269908169872414D;
        double var11 = var3;
        double var13 = var7;
        double var15 = 0.5D;
        double var17 = 0.0D;

        for (int var19 = 0; var19 < 10; ++var19)
        {
            double var20 = var11 + var15 * Math.cos(var17);
            double var22 = var13 + var15 * Math.sin(var17);
            var17 += var9;
            LavaChestPlate.proxy.sparkle((float)var20, (float)var5, (float)var22, 1.0F, 1, 0.1F, var2);
        }
    }

    public void Initialization(ExtendedPlayer var1, EntityPlayer var2, Side var3)
    {
        if (var3.isClient())
        {
            LavaChestPlate.proxy.movementEnabled(var2, 0.0F, 0.0F, false, false);
        }
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
        if (var3.isClient())
        {
            LavaChestPlate.proxy.movementDisabled(var2);
        }
    }

    public byte getIDeffect()
    {
        return (byte)1;
    }
}
