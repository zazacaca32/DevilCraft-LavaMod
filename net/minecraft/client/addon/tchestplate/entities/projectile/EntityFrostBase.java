package net.minecraft.client.addon.tchestplate.entities.projectile;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.addon.tchestplate.LavaChestPlate;
import net.minecraft.client.addon.tchestplate.Bukkit.CallEventBukkit;
import net.minecraft.client.addon.tchestplate.fx.manager.ManagerFX;
import net.minecraft.client.addon.tchestplate.fx.manager.Slowe;
import net.minecraft.client.addon.tchestplate.player.ExtendedPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityFrostBase extends Entity implements IProjectile
{
    private int xTile = -1;
    private int yTile = -1;
    private int zTile = -1;
    private int inTile = 0;
    private int inData = 0;
    private boolean inGround = false;
    public Entity shootingEntity;
    public int ticksInGround;
    private int ticksInAir = 0;
    public double damage = 1.0D;
    private int knockbackStrength;
    private boolean hitEntity = false;
    public byte duration = 0;
    public int millisec = 0;

    public EntityFrostBase(World par1World)
    {
        super(par1World);
        super.renderDistanceWeight = 10.0D;
        this.setSize(0.5F, 0.5F);
    }

    public EntityFrostBase(World par1World, double par2, double par4, double par6)
    {
        super(par1World);
        super.renderDistanceWeight = 10.0D;
        this.setSize(0.25F, 0.25F);
        this.setPosition(par2, par4, par6);
        super.yOffset = 0.0F;
    }

    public EntityFrostBase(World par1World, EntityLiving par2EntityLiving, EntityLiving par3EntityLiving, float par4, float par5)
    {
        super(par1World);
        super.renderDistanceWeight = 10.0D;
        this.shootingEntity = par2EntityLiving;
        super.posY = par2EntityLiving.posY + (double)par2EntityLiving.getEyeHeight() - 0.10000000149011612D;
        double var6 = par3EntityLiving.posX - par2EntityLiving.posX;
        double var7 = par3EntityLiving.boundingBox.minY + (double)(par3EntityLiving.height / 3.0F) - super.posY;
        double var8 = par3EntityLiving.posZ - par2EntityLiving.posZ;
        double var9 = (double)MathHelper.sqrt_double(var6 * var6 + var8 * var8);

        if (var9 >= 1.0E-7D)
        {
            float var10 = (float)(Math.atan2(var8, var6) * 180.0D / Math.PI) - 90.0F;
            float var11 = (float)(-(Math.atan2(var7, var9) * 180.0D / Math.PI));
            double var12 = var6 / var9;
            double var13 = var8 / var9;
            this.setLocationAndAngles(par2EntityLiving.posX + var12, super.posY, par2EntityLiving.posZ + var13, var10, var11);
            super.yOffset = 0.0F;
            float var14 = (float)var9 * 0.2F;
            this.setThrowableHeading(var6, var7 + (double)var14, var8, par4, par5);
        }
    }

    public EntityFrostBase(World par1World, EntityLiving par2EntityLiving, float par3)
    {
        super(par1World);
        super.renderDistanceWeight = 10.0D;
        this.shootingEntity = par2EntityLiving;
        this.setSize(0.25F, 0.25F);
        this.setLocationAndAngles(par2EntityLiving.posX, par2EntityLiving.posY + (double)par2EntityLiving.getEyeHeight(), par2EntityLiving.posZ, par2EntityLiving.rotationYaw, par2EntityLiving.rotationPitch);
        super.posX -= (double)(MathHelper.cos(super.rotationYaw / 180.0F * (float)Math.PI) * 0.16F);
        super.posY -= 0.1500000001490116D;
        super.posZ -= (double)(MathHelper.sin(super.rotationYaw / 180.0F * (float)Math.PI) * 0.16F);
        Vec3 vec3d = par2EntityLiving.getLook(1.0F);
        super.posX += vec3d.xCoord;
        super.posY += vec3d.yCoord;
        super.posZ += vec3d.zCoord;
        this.setPosition(super.posX, super.posY, super.posZ);
        super.yOffset = 0.0F;
        super.motionX = (double)(-MathHelper.sin(super.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(super.rotationPitch / 180.0F * (float)Math.PI));
        super.motionZ = (double)(MathHelper.cos(super.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(super.rotationPitch / 180.0F * (float)Math.PI));
        super.motionY = (double)(-MathHelper.sin(super.rotationPitch / 180.0F * (float)Math.PI));
        this.setThrowableHeading(super.motionX, super.motionY, super.motionZ, par3 * 1.5F, 1.0F);
    }

    protected void entityInit()
    {
    }

    public void setThrowableHeading(double par1, double par3, double par5, float par7, float par8)
    {
        float var9 = MathHelper.sqrt_double(par1 * par1 + par3 * par3 + par5 * par5);
        par1 /= (double)var9;
        par3 /= (double)var9;
        par5 /= (double)var9;
        par1 += super.rand.nextGaussian() * 0.007499999832361937D * (double)par8;
        par3 += super.rand.nextGaussian() * 0.007499999832361937D * (double)par8;
        par5 += super.rand.nextGaussian() * 0.007499999832361937D * (double)par8;
        par1 *= (double)par7;
        par3 *= (double)par7;
        par5 *= (double)par7;
        super.motionX = par1;
        super.motionY = par3;
        super.motionZ = par5;
        float var10 = MathHelper.sqrt_double(par1 * par1 + par5 * par5);
        float n = (float)(Math.atan2(par1, par5) * 180.0D / Math.PI);
        super.rotationYaw = n;
        super.prevRotationYaw = n;
        float n2 = (float)(Math.atan2(par3, (double)var10) * 180.0D / Math.PI);
        super.rotationPitch = n2;
        super.prevRotationPitch = n2;
        this.ticksInGround = 0;
    }

    @SideOnly(Side.CLIENT)
    public void setPositionAndRotation2(double par1, double par3, double par5, float par7, float par8, int par9)
    {
        this.setPosition(par1, par3, par5);
        this.setRotation(par7, par8);
    }

    @SideOnly(Side.CLIENT)
    public void setVelocity(double par1, double par3, double par5)
    {
        super.motionX = par1;
        super.motionY = par3;
        super.motionZ = par5;

        if (super.prevRotationPitch == 0.0F && super.prevRotationYaw == 0.0F)
        {
            float var7 = MathHelper.sqrt_double(par1 * par1 + par5 * par5);
            float n = (float)(Math.atan2(par1, par5) * 180.0D / Math.PI);
            super.rotationYaw = n;
            super.prevRotationYaw = n;
            float n2 = (float)(Math.atan2(par3, (double)var7) * 180.0D / Math.PI);
            super.rotationPitch = n2;
            super.prevRotationPitch = n2;
            super.prevRotationPitch = super.rotationPitch;
            super.prevRotationYaw = super.rotationYaw;
            this.setLocationAndAngles(super.posX, super.posY, super.posZ, super.rotationYaw, super.rotationPitch);
            this.ticksInGround = 0;
        }
    }

    public void onUpdate()
    {
        super.onUpdate();
        int var21;

        if (this.inGround)
        {
            var21 = super.worldObj.getBlockId(this.xTile, this.yTile, this.zTile);
            int var161 = super.worldObj.getBlockMetadata(this.xTile, this.yTile, this.zTile);

            if (var21 == this.inTile && var161 == this.inData)
            {
                ++this.ticksInGround;

                if (this.ticksInGround == 200)
                {
                    this.setDead();
                }
            }
            else
            {
                this.inGround = false;
                super.motionX *= (double)(super.rand.nextFloat() * 0.2F);
                super.motionY *= (double)(super.rand.nextFloat() * 0.2F);
                super.motionZ *= (double)(super.rand.nextFloat() * 0.2F);
                this.ticksInGround = 0;
                this.ticksInAir = 0;
            }
        }
        else
        {
            for (var21 = 0; var21 < 5; ++var21)
            {
                LavaChestPlate.proxy.sparkle((float)super.posX - 0.1F + super.rand.nextFloat() * 0.2F, (float)super.posY + super.rand.nextFloat() * 0.2F, (float)super.posZ - 0.1F + super.rand.nextFloat() * 0.2F, 0.3F, 6, 0.005F);
            }

            ++this.ticksInAir;
            Vec3 var161 = super.worldObj.getWorldVec3Pool().getVecFromPool(super.posX, super.posY, super.posZ);
            Vec3 var16 = super.worldObj.getWorldVec3Pool().getVecFromPool(super.posX + super.motionX, super.posY + super.motionY, super.posZ + super.motionZ);
            MovingObjectPosition zz = super.worldObj.rayTraceBlocks_do_do(var161, var16, false, true);
            var161 = super.worldObj.getWorldVec3Pool().getVecFromPool(super.posX, super.posY, super.posZ);
            var16 = super.worldObj.getWorldVec3Pool().getVecFromPool(super.posX + super.motionX, super.posY + super.motionY, super.posZ + super.motionZ);

            if (zz != null)
            {
                var16 = super.worldObj.getWorldVec3Pool().getVecFromPool(zz.hitVec.xCoord, zz.hitVec.yCoord, zz.hitVec.zCoord);
            }

            Entity var23 = null;
            List var24 = super.worldObj.getEntitiesWithinAABBExcludingEntity(this, super.boundingBox.addCoord(super.motionX, super.motionY, super.motionZ).expand(1.0D, 1.0D, 1.0D));
            double var25 = 0.0D;

            for (int var18 = 0; var18 < var24.size(); ++var18)
            {
                Entity var191 = (Entity)var24.get(var18);

                if (var191.canBeCollidedWith() && this.ticksInAir > 2)
                {
                    float var281 = 0.3F;
                    AxisAlignedBB var29 = var191.boundingBox.expand(0.30000001192092896D, 0.30000001192092896D, 0.30000001192092896D);
                    MovingObjectPosition var30 = var29.calculateIntercept(var161, var16);

                    if (var30 != null)
                    {
                        double var31 = var161.distanceTo(var30.hitVec);

                        if (var31 < var25 || var25 == 0.0D)
                        {
                            var23 = var191;
                            var25 = var31;
                        }
                    }
                }
            }

            if (var23 != null)
            {
                zz = new MovingObjectPosition(var23);
            }

            if (zz != null)
            {
                if (zz.entityHit != null && zz.entityHit instanceof EntityPlayerMP)
                {
                    if (this.hitEntity)
                    {
                        return;
                    }

                    if (!super.worldObj.isRemote)
                    {
                        if (CallEventBukkit.CallBlockCanAttackEvent((EntityPlayer)this.shootingEntity, (EntityPlayer)zz.entityHit))
                        {
                            this.setDead();
                            return;
                        }

                        Slowe var17 = new Slowe(this.millisec, this.duration);
                        ExtendedPlayer.get((EntityPlayer)zz.entityHit).addEffect(var17);
                        ManagerFX.sendEffectToClientsAllPlayers((EntityPlayerMP)zz.entityHit, var17);
                        zz.entityHit.worldObj.playSoundEffect(zz.entityHit.posX + 0.5D, zz.entityHit.posY + 0.5D, zz.entityHit.posZ + 0.5D, "tchestplate.friz", 3.0F, 1.0F);
                    }

                    this.hitEntity = true;
                }

                this.setDead();
            }

            super.posX += super.motionX;
            super.posY += super.motionY;
            super.posZ += super.motionZ;

            for (super.rotationYaw = (float)(Math.atan2(super.motionX, super.motionZ) * 180.0D / Math.PI); super.rotationPitch - super.prevRotationPitch >= 180.0F; super.prevRotationPitch += 360.0F)
            {
                ;
            }

            while (super.rotationYaw - super.prevRotationYaw < -180.0F)
            {
                super.prevRotationYaw -= 360.0F;
            }

            while (super.rotationYaw - super.prevRotationYaw >= 180.0F)
            {
                super.prevRotationYaw += 360.0F;
            }

            super.rotationPitch = super.prevRotationPitch + (super.rotationPitch - super.prevRotationPitch) * 0.2F;
            super.rotationYaw = super.prevRotationYaw + (super.rotationYaw - super.prevRotationYaw) * 0.2F;
            float var181 = 0.99F;
            float var19 = 0.05F;
            super.motionX *= 0.9900000095367432D;
            super.motionY *= 0.9900000095367432D;
            super.motionZ *= 0.9900000095367432D;
            super.motionY -= 0.05000000074505806D;
            this.setPosition(super.posX, super.posY, super.posZ);
            this.doBlockCollisions();
        }
    }

    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
    {
        par1NBTTagCompound.setShort("xTile", (short)this.xTile);
        par1NBTTagCompound.setShort("yTile", (short)this.yTile);
        par1NBTTagCompound.setShort("zTile", (short)this.zTile);
        par1NBTTagCompound.setByte("inTile", (byte)this.inTile);
        par1NBTTagCompound.setByte("inData", (byte)this.inData);
        par1NBTTagCompound.setByte("inGround", (byte)(this.inGround ? 1 : 0));
        par1NBTTagCompound.setDouble("damage", this.damage);
    }

    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        this.xTile = par1NBTTagCompound.getShort("xTile");
        this.yTile = par1NBTTagCompound.getShort("yTile");
        this.zTile = par1NBTTagCompound.getShort("zTile");
        this.inTile = par1NBTTagCompound.getByte("inTile") & 255;
        this.inData = par1NBTTagCompound.getByte("inData") & 255;
        this.inGround = par1NBTTagCompound.getByte("inGround") == 1;

        if (par1NBTTagCompound.hasKey("damage"))
        {
            this.damage = par1NBTTagCompound.getDouble("damage");
        }
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

    public void setDamage(double par1)
    {
        this.damage = par1;
    }

    public double getDamage()
    {
        return this.damage;
    }

    public void setKnockbackStrength(int par1)
    {
        this.knockbackStrength = par1;
    }

    public boolean canAttackWithItem()
    {
        return false;
    }
}
