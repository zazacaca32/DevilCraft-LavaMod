package net.minecraft.client.addon.lavamobs.entity;

import net.minecraft.client.addon.lavamobs.LavaModMobs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.ai.EntityAIBreakDoor;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityDemon extends LavaEntityMob
{
    public int smashCounter = 0;
    private boolean smash = false;
    private int x = 0;
    private int z = 0;
    private int ticktarget = 0;

    public EntityDemon(World par1World)
    {
        super(par1World);
        super.texture = "/mods/Alo/mob/demon.png";
        super.moveSpeed = 0.25F;
        setSize(0.6F, 2.0F);
        this.tasks.addTask(0, new LavaEntityAIAttackOnCollide(this, EntityPlayer.class, this.moveSpeed, false));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 10.0F, 0, true));
    }

    public int getAttackStrength(Entity par1Entity)
    {
        return 1200;
    }

    protected boolean isAIEnabled()
    {
        return true;
    }

    public int getMaxHealth()
    {
        return 2400;
    }

    protected void fall(float par1)
    {
    }

    public void onLivingUpdate()
    {
        if (this.x == 0 && this.z == 0)
        {
            this.x = (int)super.posX;
            this.z = (int)super.posZ;
        }

        if (super.onGround)
        {
            super.motionY += 0.1D;
        }

        if (!super.worldObj.isRemote && (super.posX - 10.0D >= (double)this.x || super.posX + 10.0D <= (double)this.x || super.posZ - 10.0D >= (double)this.z || super.posZ + 10.0D <= (double)this.z))
        {
            if (this.getAttackTarget() != null)
            {
                this.ticktarget = 0;
            }
            else
            {
                ++this.ticktarget;

                if (this.ticktarget > 400)
                {
                    this.setDead();
                }
            }
        }

        super.onLivingUpdate();
    }

    protected void onDeathUpdate()
    {
        if (!super.worldObj.isRemote)
        {
            this.entityDropItem(new ItemStack(LavaModMobs.TotemItem.itemID, 2, 3), 1.0F);
        }

        this.setDead();
    }

    public EnumCreatureAttribute getCreatureAttribute()
    {
        return EnumCreatureAttribute.UNDEAD;
    }
}
