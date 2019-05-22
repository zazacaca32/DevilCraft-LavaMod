package net.minecraft.client.addon.lavamobs.bow;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ShurikenEntity extends EntityArrow
{
    private int tick = 0;

    public ShurikenEntity(World par1World)
    {
        super(par1World);
        super.renderDistanceWeight = 10.0D;
        this.setSize(0.5F, 0.5F);
    }

    public ShurikenEntity(World par1World, EntityLiving par2EntityLiving, float par3)
    {
        super(par1World);
        super.renderDistanceWeight = 10.0D;
        super.shootingEntity = par2EntityLiving;

        if (par2EntityLiving instanceof EntityPlayer)
        {
            super.canBePickedUp = 1;
        }

        this.setSize(0.5F, 0.5F);
        this.setLocationAndAngles(par2EntityLiving.posX, par2EntityLiving.posY + (double)par2EntityLiving.getEyeHeight(), par2EntityLiving.posZ, par2EntityLiving.rotationYaw, par2EntityLiving.rotationPitch);
        super.posX -= (double)(MathHelper.cos(super.rotationYaw / 180.0F * (float)Math.PI) * 0.16F);
        super.posY -= 0.10000000149011612D;
        super.posZ -= (double)(MathHelper.sin(super.rotationYaw / 180.0F * (float)Math.PI) * 0.16F);
        this.setPosition(super.posX, super.posY, super.posZ);
        super.yOffset = 0.0F;
        super.motionX = (double)(-MathHelper.sin(super.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(super.rotationPitch / 180.0F * (float)Math.PI));
        super.motionZ = (double)(MathHelper.cos(super.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(super.rotationPitch / 180.0F * (float)Math.PI));
        super.motionY = (double)(-MathHelper.sin(super.rotationPitch / 180.0F * (float)Math.PI));
        this.setThrowableHeading(super.motionX, super.motionY, super.motionZ, par3 * 1.5F, 1.0F);
    }

    public void onUpdate()
    {
        ++this.tick;

        if (this.tick > 200)
        {
            this.setDead();
        }

        super.onUpdate();
    }

    public void onCollideWithPlayer(EntityPlayer par1EntityPlayer)
    {
    }

    protected void entityInit()
    {
        super.dataWatcher.addObject(16, Integer.valueOf(0));
    }
}
