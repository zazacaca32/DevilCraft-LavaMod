package net.minecraft.client.addon.tchestplate.entities.monster;

import java.awt.Color;
import net.minecraft.block.Block;
import net.minecraft.client.addon.tchestplate.LavaChestPlate;
import net.minecraft.client.addon.tchestplate.Utils;
import net.minecraft.client.addon.tchestplate.Bukkit.CallEventBukkit;
import net.minecraft.client.addon.tchestplate.packets.PacketMAparticle;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityWisp extends EntityFlying
{
    public double waypointX;
    public double waypointY;
    public double waypointZ;
    private Entity targetedEntity = null;
    public int prevAttackCounter = 0;
    public int attackCounter = -10;
    public byte type;
    public short attackCount = 1;
    public EntityPlayer shootingEntity;
    public int attackDamage = 1;

    public EntityWisp(World world)
    {
        super(world);
        this.setSize(0.5F, 0.5F);
        super.experienceValue = 5;
        this.type = 0;
    }

    protected boolean canTriggerWalking()
    {
        return false;
    }

    protected int decreaseAirSupply(int par1)
    {
        return par1;
    }

    public Entity getTargetedEntity()
    {
        return this.targetedEntity;
    }

    public void setTargetedEntity(EntityPlayer player, Entity targetedEntity)
    {
        this.shootingEntity = player;
        this.targetedEntity = targetedEntity;
    }

    protected void entityInit()
    {
        super.entityInit();
        super.dataWatcher.addObject(16, Integer.valueOf(0));
    }

    public int getMaxHealth()
    {
        return 20000;
    }

    public void onDeath(DamageSource par1DamageSource)
    {
        super.onDeath(par1DamageSource);

        if (super.worldObj.isRemote)
        {
            LavaChestPlate.proxy.burst(super.worldObj, super.posX, super.posY, super.posZ, 1.2F);
        }
    }

    public void onUpdate()
    {
        super.onUpdate();

        if (super.worldObj.isRemote && super.ticksExisted <= 1)
        {
            LavaChestPlate.proxy.burst(super.worldObj, super.posX, super.posY, super.posZ, 1.2F);
        }

        if (super.worldObj.isRemote && super.worldObj.rand.nextBoolean())
        {
            Color color = new Color(14276095);
            LavaChestPlate.proxy.wispFX(super.worldObj, super.posX + (double)((super.worldObj.rand.nextFloat() - super.worldObj.rand.nextFloat()) * 0.7F), super.posY + (double)((super.worldObj.rand.nextFloat() - super.worldObj.rand.nextFloat()) * 0.7F), super.posZ + (double)((super.worldObj.rand.nextFloat() - super.worldObj.rand.nextFloat()) * 0.7F), 0.1F, (float)color.getRed() / 255.0F, (float)color.getGreen() / 255.0F, (float)color.getBlue() / 255.0F);
        }
    }

    public byte getType()
    {
        return super.dataWatcher.getWatchableObjectByte(16);
    }

    protected void updateEntityActionState()
    {
        if (!super.worldObj.isRemote && super.worldObj.difficultySetting == 0)
        {
            this.setDead();
        }

        if (this.targetedEntity != null && this.attackCount > 0)
        {
            super.isImmuneToFire = true;
            this.prevAttackCounter = this.attackCounter;
            double attackrange = 16.0D;
            double d = this.waypointX - super.posX;
            double d1 = this.waypointY - super.posY;
            double d2 = this.waypointZ - super.posZ;
            double d3 = d * d + d1 * d1 + d2 * d2;

            if ((d3 < 600.0D || d3 > 3600.0D) && this.targetedEntity != null)
            {
                this.waypointX = this.targetedEntity.posX;
                this.waypointY = this.targetedEntity.posY + 2.5D;
                this.waypointZ = this.targetedEntity.posZ;
            }

            if (this.isCourseTraversable(this.waypointX, this.waypointY, this.waypointZ, d3 = (double)MathHelper.sqrt_double(d3)))
            {
                super.motionX += d / d3 * 0.1D;
                super.motionY += d1 / d3 * 0.1D;
                super.motionZ += d2 / d3 * 0.1D;
            }
            else
            {
                if (this.targetedEntity != null && this.targetedEntity.getDistanceSqToEntity(this) > 3600.0D)
                {
                    this.targetedEntity = null;
                    return;
                }

                this.setPosition(this.targetedEntity.posX, this.targetedEntity.posY, this.targetedEntity.posZ);
                this.waypointX = this.targetedEntity.posX;
                this.waypointY = this.targetedEntity.posY + 3.0D;
                this.waypointZ = this.targetedEntity.posZ;
            }

            if (this.targetedEntity != null && this.targetedEntity.isDead)
            {
                this.targetedEntity = null;
            }

            if (this.targetedEntity != null && this.targetedEntity.getDistanceSqToEntity(this) < attackrange * attackrange)
            {
                double d5 = this.targetedEntity.posX - super.posX;
                double d6 = this.targetedEntity.boundingBox.minY + (double)(this.targetedEntity.height / 2.0F) - (super.posY + (double)(super.height / 2.0F));
                double d7 = this.targetedEntity.posZ - super.posZ;
                super.renderYawOffset = super.rotationYaw = -((float)Math.atan2(d5, d7)) * 180.0F / (float)Math.PI;

                if (this.canEntityBeSeen(this.targetedEntity))
                {
                    ++this.attackCounter;

                    if (this.attackCounter >= 15)
                    {
                        if (!Utils.isClient())
                        {
                            if (this.shootingEntity != null)
                            {
                                if (this.targetedEntity instanceof EntityPlayer && CallEventBukkit.CallBlockCanAttackEvent(this.shootingEntity, (EntityPlayer)this.targetedEntity))
                                {
                                    this.setDead();
                                    return;
                                }

                                super.worldObj.playSoundAtEntity(this, "tchestplate.shock1", 1.0F, 1.1F);
                                LavaChestPlate.proxy.sendCustomPacketToAllNear((new PacketMAparticle(super.entityId, this.targetedEntity.entityId, 4)).makePacket(), 64.0D, this);
                                this.targetedEntity.attackEntityFrom(DamageSource.causePlayerDamage(this.shootingEntity), super.worldObj.rand.nextInt(this.attackDamage) + this.attackDamage);
                            }

                            --this.attackCount;
                        }

                        this.attackCounter = -20 + super.worldObj.rand.nextInt(15);
                    }
                }
            }
            else
            {
                super.renderYawOffset = super.rotationYaw = -((float)Math.atan2(super.motionX, super.motionZ)) * 180.0F / (float)Math.PI;

                if (this.attackCounter > 0)
                {
                    --this.attackCounter;
                }
            }
        }
        else
        {
            this.setDead();
        }
    }

    private boolean isCourseTraversable(double d, double d1, double d2, double d3)
    {
        double d4 = (this.waypointX - super.posX) / d3;
        double d5 = (this.waypointY - super.posY) / d3;
        double d6 = (this.waypointZ - super.posZ) / d3;
        AxisAlignedBB axisalignedbb = super.boundingBox.copy();
        int x;

        for (x = 1; (double)x < d3; ++x)
        {
            axisalignedbb.offset(d4, d5, d6);

            if (!super.worldObj.getCollidingBoundingBoxes(this, axisalignedbb).isEmpty())
            {
                return false;
            }
        }

        x = (int)this.waypointX;
        int y = (int)this.waypointY;
        int z = (int)this.waypointZ;

        if (super.worldObj.getBlockId(x, y, z) != Block.waterMoving.blockID && super.worldObj.getBlockId(x, y, z) != Block.waterStill.blockID && super.worldObj.getBlockId(x, y, z) != Block.lavaMoving.blockID && super.worldObj.getBlockId(x, y, z) != Block.lavaStill.blockID)
        {
            for (int a = 0; a < 11; ++a)
            {
                if (!super.worldObj.isAirBlock(x, y - a, z))
                {
                    return true;
                }
            }

            return false;
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

    protected void dropFewItems(boolean flag, int i)
    {
    }

    protected float getSoundVolume()
    {
        return 0.25F;
    }

    protected boolean canDespawn()
    {
        return true;
    }

    public boolean getCanSpawnHere()
    {
        return super.worldObj.difficultySetting > 0 && super.getCanSpawnHere();
    }

    public void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        super.writeEntityToNBT(nbttagcompound);
        nbttagcompound.setByte("Type", this.type);
    }

    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        super.readEntityFromNBT(nbttagcompound);
        this.type = nbttagcompound.getByte("Type");
    }
}
