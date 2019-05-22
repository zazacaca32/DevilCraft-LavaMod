package net.minecraft.client.addon.lavamobs.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.addon.lavamobs.packets.PacketMAUpdatebyte;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.potion.Potion;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.ForgeHooks;

public abstract class LavaEntityMob extends EntityMob
{
    private int swingProgressIntq = -1;
    public boolean isSwingInProgressq = false;
    private float swingProgressq;
    private float prevSwingProgressq;

    public LavaEntityMob(World par1World)
    {
        super(par1World);
    }

    public void swingMob()
    {
        if (!this.isSwingInProgressq || this.swingProgressIntq >= this.getArmSwingAnimationEnd() / 2 || this.swingProgressIntq < 0)
        {
            this.swingProgressIntq = -1;
            this.isSwingInProgressq = true;

            if (super.worldObj instanceof WorldServer)
            {
                ((WorldServer)super.worldObj).getEntityTracker().sendPacketToAllPlayersTrackingEntity(this, (new PacketMAUpdatebyte(this)).makePacket());
            }
        }
    }

    public boolean attackEntityFrom(DamageSource par1DamageSource, int par2)
    {
        if (ForgeHooks.onLivingAttack(this, par1DamageSource, par2))
        {
            return false;
        }
        else if (this.isEntityInvulnerable())
        {
            return false;
        }
        else if (super.worldObj.isRemote)
        {
            return false;
        }
        else
        {
            if (par2 > 30)
            {
                par2 = (int)((double)par2 * 0.03D + 30.0D);
            }

            par2 /= 3;
            super.entityAge = 0;

            if (super.health <= 0)
            {
                return false;
            }
            else if (par1DamageSource.isFireDamage() && this.isPotionActive(Potion.fireResistance))
            {
                return false;
            }
            else
            {
                if ((par1DamageSource == DamageSource.anvil || par1DamageSource == DamageSource.fallingBlock) && this.getCurrentItemOrArmor(4) != null)
                {
                    this.getCurrentItemOrArmor(4).damageItem(par2 * 4 + super.rand.nextInt(par2 * 2), this);
                    par2 = (int)((float)par2 * 0.75F);
                }

                super.limbYaw = 1.5F;
                boolean flag = true;

                if ((float)super.hurtResistantTime > (float)super.maxHurtResistantTime / 2.0F)
                {
                    if (par2 <= super.lastDamage)
                    {
                        return false;
                    }

                    this.damageEntity(par1DamageSource, par2 - super.lastDamage);
                    super.lastDamage = par2;
                    flag = false;
                }
                else
                {
                    super.lastDamage = par2;
                    super.prevHealth = super.health;
                    super.hurtResistantTime = super.maxHurtResistantTime;
                    this.damageEntity(par1DamageSource, par2);
                    super.maxHurtTime = 10;
                    super.hurtTime = 10;
                }

                super.attackedAtYaw = 0.0F;
                Entity entity = par1DamageSource.getEntity();

                if (entity != null && entity instanceof EntityLiving)
                {
                    this.setRevengeTarget((EntityLiving)entity);
                }

                if (flag)
                {
                    super.worldObj.setEntityState(this, (byte)2);

                    if (par1DamageSource != DamageSource.drown)
                    {
                        this.setBeenAttacked();
                    }
                }

                if (super.health <= 0)
                {
                    if (flag)
                    {
                        this.playSound(this.getDeathSound(), this.getSoundVolume(), this.getSoundPitch());
                    }

                    this.onDeath(par1DamageSource);
                }
                else if (flag)
                {
                    this.playSound(this.getHurtSound(), this.getSoundVolume(), this.getSoundPitch());
                }

                return true;
            }
        }
    }

    private int getArmSwingAnimationEnd()
    {
        return this.isPotionActive(Potion.digSpeed) ? 6 - (1 + this.getActivePotionEffect(Potion.digSpeed).getAmplifier()) * 1 : (this.isPotionActive(Potion.digSlowdown) ? 6 + (1 + this.getActivePotionEffect(Potion.digSlowdown).getAmplifier()) * 2 : 6);
    }

    protected void updateArmSwingProgressq()
    {
        int i = this.getArmSwingAnimationEnd();

        if (this.isSwingInProgressq)
        {
            ++this.swingProgressIntq;

            if (this.swingProgressIntq >= i)
            {
                this.swingProgressIntq = 0;
                this.isSwingInProgressq = false;
            }
        }
        else
        {
            this.swingProgressIntq = 0;
        }

        this.swingProgressq = (float)this.swingProgressIntq / (float)i;
    }

    public void onLivingUpdate()
    {
        this.prevSwingProgressq = this.swingProgressq;
        this.updateArmSwingProgressq();
        super.onLivingUpdate();
    }

    @SideOnly(Side.CLIENT)
    public float getSwingProgressq(float par1)
    {
        float f1 = this.swingProgressq - this.prevSwingProgressq;

        if (f1 < 0.0F)
        {
            ++f1;
        }

        return this.prevSwingProgressq + f1 * par1;
    }
}
