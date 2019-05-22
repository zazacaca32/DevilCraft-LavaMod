package net.minecraft.client.addon.lavamobs.gyroscooter;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.addon.lavamobs.LavaModMobs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.AchievementList;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityGyroscooter extends EntityCreature
{
    private final EntityAIControlledByPlayerAI aiControlledByPlayer;
    public String OwnerPlayer;
    public Long EntityID;
    public byte EntityColor;
    private byte flag = 0;

    public EntityGyroscooter(World par1World)
    {
        super(par1World);
        super.texture = "/mods/lavamobs/textures/gyroscooter/gyroscooter.png";
        this.setSize(0.9F, 1.1F);
        this.getNavigator().setAvoidsWater(true);
        super.tasks.addTask(0, this.aiControlledByPlayer = new EntityAIControlledByPlayerAI(this, 0.36F));
    }

    protected void collideWithEntity(Entity par1Entity)
    {
    }

    public boolean canBePushed()
    {
        return false;
    }

    public boolean shouldRiderFaceForward(EntityPlayer player)
    {
        return true;
    }

    public void onLivingUpdate()
    {
        if (this.EntityColor != this.flag)
        {
            this.flag = this.EntityColor;
            this.setColor();
        }

        super.onLivingUpdate();
    }

    @SideOnly(Side.SERVER)
    private void onLivingUpdateServer()
    {
        this.onLivingUpdate();
    }

    public boolean attackEntityFrom(DamageSource par1DamageSource, int par2)
    {
        Entity entity = par1DamageSource.getEntity();

        if (entity != null)
        {
            if (super.riddenByEntity != null)
            {
                super.riddenByEntity.attackEntityFrom(par1DamageSource, par2);
                return false;
            }

            if (entity instanceof EntityPlayer && !entity.getEntityName().equals(this.OwnerPlayer))
            {
                return false;
            }
        }

        return super.attackEntityFrom(par1DamageSource, par2);
    }

    protected void onDeathUpdate()
    {
        this.setDead();
    }

    protected boolean canDespawn()
    {
        return true;
    }

    public void setDead()
    {
        LavaModMobs.LavaEntityID.remove(this.EntityID);
        super.isDead = true;
    }

    public boolean isAIEnabled()
    {
        return true;
    }

    public int getMaxHealth()
    {
        return 1;
    }

    protected void updateAITasks()
    {
        super.updateAITasks();
    }

    public boolean canBeSteered()
    {
        return true;
    }

    protected void entityInit()
    {
        super.entityInit();
        super.dataWatcher.addObject(16, Byte.valueOf(this.EntityColor));
    }

    public byte getColor()
    {
        return super.dataWatcher.getWatchableObjectByte(16);
    }

    public void setColor()
    {
        super.dataWatcher.updateObject(16, Byte.valueOf(this.EntityColor));
    }

    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeEntityToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setString("OwnerPlayer", this.OwnerPlayer);
        par1NBTTagCompound.setLong("EntityID", this.EntityID.longValue());
        par1NBTTagCompound.setByte("EntityColor", this.EntityColor);
    }

    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readEntityFromNBT(par1NBTTagCompound);
        this.OwnerPlayer = par1NBTTagCompound.getString("OwnerPlayer");
        this.EntityID = Long.valueOf(par1NBTTagCompound.getLong("EntityID"));
        this.EntityColor = par1NBTTagCompound.getByte("EntityColor");
    }

    protected String getLivingSound()
    {
        return "";
    }

    protected String getHurtSound()
    {
        return "";
    }

    protected String getDeathSound()
    {
        return "";
    }

    protected void playStepSound(int par1, int par2, int par3, int par4)
    {
    }

    public boolean interact(EntityPlayer par1EntityPlayer)
    {
        if (!par1EntityPlayer.getEntityName().equals(this.OwnerPlayer))
        {
            return false;
        }
        else if (super.interact(par1EntityPlayer))
        {
            return true;
        }
        else if (!super.worldObj.isRemote && (super.riddenByEntity == null || super.riddenByEntity == par1EntityPlayer))
        {
            par1EntityPlayer.mountEntity(this);
            this.getMoveHelper().setMoveTo(super.posX + 0.1D, super.posY, super.posZ, 0.36F);
            return true;
        }
        else
        {
            return false;
        }
    }

    protected int getDropItemId()
    {
        return 0;
    }

    protected void dropFewItems(boolean par1, int par2)
    {
    }

    public void onStruckByLightning(EntityLightningBolt par1EntityLightningBolt)
    {
    }

    protected void fall(float par1)
    {
        super.fall(par1);

        if (par1 > 5.0F && super.riddenByEntity instanceof EntityPlayer)
        {
            ((EntityPlayer)super.riddenByEntity).triggerAchievement(AchievementList.killEnemy);
        }
    }

    public boolean isBreedingItem(ItemStack par1ItemStack)
    {
        return false;
    }

    public EntityAIControlledByPlayerAI getAIControlledByPlayer()
    {
        return this.aiControlledByPlayer;
    }

    public EntityAgeable createChild(EntityAgeable par1EntityAgeable)
    {
        return null;
    }
}
