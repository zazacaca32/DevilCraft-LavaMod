package net.minecraft.client.addon.tchestplate.entities.projectile;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityGateCastle extends Entity
{
    long time;

    public EntityGateCastle(World par1World)
    {
        super(par1World);
        super.renderDistanceWeight = 10.0D;
        this.setSize(10.0F, 10.0F);
        this.time = System.currentTimeMillis() + 10000L;
    }

    public EntityGateCastle(World par1World, double posX, double posY, double posZ)
    {
        super(par1World);
        super.posX = posX;
        super.posY = posY;
        super.posZ = posZ;
        super.renderDistanceWeight = 10.0D;
        this.setSize(10.0F, 10.0F);
        this.time = System.currentTimeMillis() + 10000L;
    }

    protected void entityInit()
    {
    }

    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
    {
    }

    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
    {
    }

    protected boolean canTriggerWalking()
    {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public float getShadowSize()
    {
        return 0.0F;
    }

    public void onUpdate()
    {
        super.onUpdate();

        if (System.currentTimeMillis() > this.time)
        {
            this.setDead();
        }
    }
}
