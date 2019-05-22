package net.minecraft.client.addon.lavamobs.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.addon.lavamobs.LavaModMobs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.ai.EntityAIBreakDoor;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityShadowOfDeath extends LavaEntityMob implements IBossDisplayData
{
    public int smashCounter = 0;
    private boolean smash = false;
    private long time_count = 0L;
    public int posx;
    public int posy;
    public int posz;
    public int meta;
    private boolean flag = true;
    private int ticktarget = 0;

    public EntityShadowOfDeath(World par1World)
    {
        super(par1World);
        super.texture = "/mods/lavamobs/textures/shadowofdeath.png";
        super.moveSpeed = 0.5F;
        this.setSize(0.9F, 2.5F);
        super.tasks.addTask(0, new EntityAISwimming(this));
        super.tasks.addTask(1, new EntityAIBreakDoor(this));
        super.tasks.addTask(2, new LavaEntityAIAttackOnCollide(this, EntityPlayer.class, super.moveSpeed, false));
        super.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        super.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
        super.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 10.0F, 0, true));
        this.time_count = System.currentTimeMillis() + 3600000L;
    }

    public void SetBlockLocate(int xCoord, int yCoord, int zCoord, int blockMetadata)
    {
        this.posx = xCoord;
        this.posy = yCoord;
        this.posz = zCoord;
        this.meta = blockMetadata;
    }

    public void readFromNBT(NBTTagCompound nbttagcompound)
    {
        super.readFromNBT(nbttagcompound);
        this.posx = nbttagcompound.getInteger("posx");
        this.posy = nbttagcompound.getInteger("posy");
        this.posz = nbttagcompound.getInteger("posz");
        this.meta = nbttagcompound.getInteger("meta");
        this.time_count = nbttagcompound.getLong("time_count");
    }

    public void writeToNBT(NBTTagCompound nbttagcompound)
    {
        super.writeToNBT(nbttagcompound);
        nbttagcompound.setInteger("posx", this.posx);
        nbttagcompound.setInteger("posy", this.posy);
        nbttagcompound.setInteger("posz", this.posz);
        nbttagcompound.setInteger("meta", this.meta);
        nbttagcompound.setLong("time_count", this.time_count);
    }

    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readEntityFromNBT(par1NBTTagCompound);
        super.dataWatcher.updateObject(16, Integer.valueOf(super.health));
    }

    public int getBossHealth()
    {
        return super.dataWatcher.getWatchableObjectInt(16);
    }

    protected void entityInit()
    {
        super.entityInit();
        super.dataWatcher.addObject(16, new Integer(100));
    }

    public int getAttackStrength(Entity par1Entity)
    {
        return 600;
    }

    protected boolean isAIEnabled()
    {
        return true;
    }

    public int getMaxHealth()
    {
        return 12000;
    }

    public void onLivingUpdate()
    {
        if (!super.worldObj.isRemote)
        {
            if (super.posX - 30.0D >= (double)this.posx || super.posX + 30.0D <= (double)this.posx || super.posZ - 30.0D >= (double)this.posz || super.posZ + 30.0D <= (double)this.posz)
            {
                this.setLocationAndAngles((double)this.posx, (double)this.posy, (double)this.posz, 90.0F, 0.0F);
            }

            if (super.health <= 0 && this.flag)
            {
                this.flag = false;
                super.worldObj.setBlock(this.posx, this.posy, this.posz, LavaModMobs.ShadowOfDeathBlock.blockID, this.meta, 2);
            }

            super.dataWatcher.updateObject(16, Integer.valueOf(super.health));
        }
        else
        {
            super.health = this.getBossHealth();
        }

        super.onLivingUpdate();
    }

    @SideOnly(Side.CLIENT)
    public int getBrightnessForRender(float par1)
    {
        return 15728880;
    }

    protected void onDeathUpdate()
    {
        if (!super.worldObj.isRemote)
        {
            this.entityDropItem(new ItemStack(LavaModMobs.TotemItem.itemID, 10, 3), 1.0F);
            int ult = super.worldObj.rand.nextInt(100);

            if (ult > 45 && ult < 50)
            {
                int id = 2813 + super.worldObj.rand.nextInt(3);
                this.entityDropItem(new ItemStack(id, 1, 0), 1.0F);
            }
            else if (ult > 80 && ult < 85)
            {
                this.entityDropItem(new ItemStack(2817, 1, 0), 1.0F);
            }
            else if (ult > 90 && ult < 100)
            {
                this.entityDropItem(new ItemStack(2819, 16, 2), 1.0F);
            }
        }

        this.setDead();
    }

    public EnumCreatureAttribute getCreatureAttribute()
    {
        return EnumCreatureAttribute.UNDEAD;
    }
}
