package net.minecraft.client.addon.tchestplate.fx.manager;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.addon.tchestplate.player.ExtendedPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;

public abstract class FX_base
{
    long Milliseconds;
    public byte duration;
    byte checkFlag;

    public FX_base()
    {
    }

    public FX_base(int var1, byte var2)
    {
        this.Milliseconds = System.currentTimeMillis() + (long)var1;
        this.duration = var2;
        this.checkFlag = 0;
    }

    public void setMilliseconds(int var1)
    {
        this.Milliseconds = System.currentTimeMillis() + (long)var1;
    }

    public int getMilliseconds()
    {
        return (int)(this.Milliseconds - System.currentTimeMillis());
    }

    public void setDuration(byte var1)
    {
        this.duration = var1;
    }

    public void setCheckFlag(byte var1)
    {
        this.checkFlag = var1;
    }

    private boolean onTime()
    {
        return this.Milliseconds >= System.currentTimeMillis();
    }

    public boolean UpdateClient(ExtendedPlayer var1, EntityPlayer var2)
    {
        switch (this.checkFlag)
        {
            case 0:
                this.Initialization(var1, var2, Side.CLIENT);
                this.checkFlag = 1;

            case 1:
                this.Update(var1, var2, Side.CLIENT);

                if (this.onTime())
                {
                    return true;
                }

            case 2:
                this.Finalization(var1, var2, Side.CLIENT);

            default:
                return false;
        }
    }

    public boolean UpdateServer(ExtendedPlayer var1, EntityPlayer var2)
    {
        switch (this.checkFlag)
        {
            case 0:
                this.Initialization(var1, var2, Side.SERVER);
                this.checkFlag = 1;

            case 1:
                this.Update(var1, var2, Side.SERVER);

                if (this.onTime())
                {
                    return true;
                }

            case 2:
                this.Finalization(var1, var2, Side.SERVER);

            default:
                return false;
        }
    }

    public abstract void Initialization(ExtendedPlayer var1, EntityPlayer var2, Side var3);

    public abstract void Update(ExtendedPlayer var1, EntityPlayer var2, Side var3);

    public abstract void Finalization(ExtendedPlayer var1, EntityPlayer var2, Side var3);

    public abstract byte getIDeffect();

    public NBTBase writeCustomPotionEffectToNBT(NBTTagCompound var1)
    {
        var1.setByte("Id", this.getIDeffect());
        var1.setByte("dur", this.duration);
        var1.setInteger("t", this.getMilliseconds());
        return var1;
    }

    public static FX_base readCustomPotionEffectFromNBT(NBTTagCompound var0)
    {
        byte var1 = var0.getByte("Id");
        byte var2 = var0.getByte("dur");
        int var3 = var0.getInteger("t");

        if (var3 < 0)
        {
            return null;
        }
        else
        {
            try
            {
                FX_base var5 = ManagerFX.getFXfromID(var1);
                var5.duration = var2;
                var5.setMilliseconds(var3);
                return var5;
            }
            catch (Exception var51)
            {
                return null;
            }
        }
    }
}
