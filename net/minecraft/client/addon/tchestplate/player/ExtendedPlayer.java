package net.minecraft.client.addon.tchestplate.player;

import java.util.ArrayList;
import java.util.Iterator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.addon.tchestplate.Utils;
import net.minecraft.client.addon.tchestplate.fx.manager.FX_base;
import net.minecraft.client.addon.tchestplate.overlaygui.ArmorExtInventory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class ExtendedPlayer implements IExtendedEntityProperties
{
    public ArrayList activepotions = new ArrayList(2);
    public static final String EXT_PROP_NAME = "epa";
    public final EntityPlayer player;
    public ArmorExtInventory inventoryExt;
    public float prevPetBodyYawOffset = 0.0F;
    private long firstPlayed = 0L;
    private long lastPlayed = 0L;
    public double lastX;
    public double lastZ;
    public double dist;
    private int swing;
    public int RightSwingCustom;
    private byte i;
    long ltime;
    public byte idmob = 0;
    public short mobcount = 0;
    public byte idevent = -1;
    double trimmer;
    public int PredatorMode;

    public int getSwing()
    {
        return this.swing;
    }

    public void setSwing(int swing)
    {
        this.swing = swing;
    }

    public void DecSwing()
    {
        if (this.swing > 0)
        {
            --this.swing;
        }
    }

    public ExtendedPlayer(EntityPlayer player)
    {
        this.player = player;
        this.inventoryExt = new ArmorExtInventory(player);
    }

    public static final void register(EntityPlayer player)
    {
        player.registerExtendedProperties("epa", new ExtendedPlayer(player));
    }

    public static final ExtendedPlayer get(EntityPlayer player)
    {
        return (ExtendedPlayer)player.getExtendedProperties("epa");
    }

    public void saveNBTData(NBTTagCompound compound)
    {
        if (!Utils.isClient())
        {
            NBTTagCompound properties = new NBTTagCompound();

            if (this.activepotions == null)
            {
                properties.removeTag("AcE");
            }
            else
            {
                if (!this.activepotions.isEmpty())
                {
                    NBTTagList nbttaglist1 = new NBTTagList();
                    Iterator iterator = this.activepotions.iterator();

                    while (iterator.hasNext())
                    {
                        FX_base potioneffect = (FX_base)iterator.next();

                        if (potioneffect.getMilliseconds() > 0)
                        {
                            nbttaglist1.appendTag(potioneffect.writeCustomPotionEffectToNBT(new NBTTagCompound()));
                        }
                    }

                    compound.setInteger("Ace", this.swing);
                    properties.setTag("AcE", nbttaglist1);
                }

                this.inventoryExt.saveToNBT(properties);
                compound.setTag("epa", properties);
            }
        }
    }

    public void loadNBTData(NBTTagCompound compound)
    {
        if (!Utils.isClient())
        {
            NBTTagCompound properties;

            if (compound.hasKey("bukkit"))
            {
                properties = compound.getCompoundTag("bukkit");

                if (properties.hasKey("firstPlayed"))
                {
                    this.firstPlayed = properties.getLong("firstPlayed");
                    this.lastPlayed = properties.getLong("lastPlayed");
                }
            }

            properties = (NBTTagCompound)compound.getTag("epa");

            if (properties != null)
            {
                this.inventoryExt.readFromNBT(properties);

                if (properties.hasKey("AcE"))
                {
                    NBTTagList nbttaglist = properties.getTagList("AcE");

                    for (int i = 0; i < nbttaglist.tagCount(); ++i)
                    {
                        NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.tagAt(i);
                        FX_base potioneffect = FX_base.readCustomPotionEffectFromNBT(nbttagcompound1);

                        if (potioneffect != null && this.addEffect(potioneffect))
                        {
                            ;
                        }
                    }
                }
            }
        }
    }

    public boolean addEffect(FX_base var1)
    {
        Iterator var2 = this.activepotions.iterator();
        FX_base var3;

        do
        {
            if (!var2.hasNext())
            {
                this.activepotions.add(var1);
                return true;
            }

            var3 = (FX_base)var2.next();
        }
        while (var3.getIDeffect() != var1.getIDeffect());

        return false;
    }

    public boolean removeEffect(int fx_ID)
    {
        Iterator i = this.activepotions.iterator();
        FX_base s;

        do
        {
            if (!i.hasNext())
            {
                return false;
            }

            s = (FX_base)i.next();
        }
        while (s.getIDeffect() != fx_ID);

        i.remove();
        return true;
    }

    public void init(Entity entity, World world)
    {
    }

    public void copy(ExtendedPlayer props)
    {
        this.activepotions = props.activepotions;
        this.inventoryExt = props.inventoryExt;
    }

    public void dist()
    {
        if (this.lastX == 0.0D || this.lastZ == 0.0D)
        {
            this.lastX = this.player.posX;
            this.lastZ = this.player.posZ;
        }

        if (!this.player.capabilities.isCreativeMode)
        {
            double d3 = this.lastX - this.player.posX;
            double d5 = this.lastZ - this.player.posZ;
            this.dist = (double)MathHelper.sqrt_double(d3 * d3 + d5 * d5);

            if (this.dist > 1.95111111D && this.player.capabilities.isFlying)
            {
                long tl = System.currentTimeMillis();

                if (this.ltime > tl)
                {
                    if (this.trimmer <= this.dist)
                    {
                        if (this.i > 10)
                        {
                            Minecraft.getMinecraft().running = false;
                        }

                        ++this.i;
                    }

                    this.trimmer = this.dist;
                }
                else
                {
                    this.ltime = tl + 5000L;
                    this.i = 0;
                    this.trimmer = 0.0D;
                }
            }

            this.lastX = this.player.posX;
            this.lastZ = this.player.posZ;
        }
    }

    public void PredatorMode(int flagPredator)
    {
        if (flagPredator == 50)
        {
            if (this.PredatorMode != flagPredator)
            {
                this.PredatorMode = flagPredator;
            }
        }
        else if (this.PredatorMode != 0)
        {
            this.PredatorMode = 0;
        }
    }
}
