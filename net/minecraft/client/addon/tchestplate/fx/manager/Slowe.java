package net.minecraft.client.addon.tchestplate.fx.manager;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.addon.tchestplate.LavaChestPlate;
import net.minecraft.client.addon.tchestplate.player.ExtendedPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class Slowe extends FX_base
{
    private float speed;
    private int color;
    int ticks = 0;
    int intervalMills = 5;

    public Slowe()
    {
    }

    public Slowe(int var1, byte var2)
    {
        super(var1, var2);
    }

    public void sh(Entity var1)
    {
        byte var2 = 10;
        double var3 = var1.posX;
        double var5 = var1.boundingBox.minY + (double)var1.height - 0.5D;
        double var7 = var1.posZ;
        double var9 = 0.017453292519943295D;
        double var11 = var3;
        double var13 = var7;
        double var15 = 0.5D;
        double var17 = 0.0D;

        for (int var19 = 0; var19 < 2; ++var19)
        {
            var17 = (var17 + var9) * Math.random() * 360.0D;
            double var20 = var11 + var15 * Math.cos(var17);
            double var22 = var13 + var15 * Math.sin(var17);
            LavaChestPlate.proxy.sparkle((float)var20, (float)var5, (float)var22, 0.7F, this.color, 0.2F, var2);
        }
    }

    public void Initialization(ExtendedPlayer var1, EntityPlayer var2, Side var3)
    {
        if (!var3.isServer())
        {
            if (super.duration == 1)
            {
                this.speed = 0.3F;
                this.color = 6;
            }
            else if (super.duration == 2)
            {
                this.speed = 0.15F;
                this.color = 8;
            }

            LavaChestPlate.proxy.movementEnabled(var2, this.speed, this.speed, true, false);
        }
    }

    public void Update(ExtendedPlayer var1, EntityPlayer var2, Side var3)
    {
        if (var3.isClient() && this.ticks++ % this.intervalMills == 0)
        {
            this.sh(var2);
        }
    }

    public void Finalization(ExtendedPlayer var1, EntityPlayer var2, Side var3)
    {
        if (!var3.isServer())
        {
            LavaChestPlate.proxy.movementDisabled(var2);
        }
    }

    public byte getIDeffect()
    {
        return (byte)2;
    }
}
