package net.minecraft.client.addon.lavamobs.entity;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class LavaEntityAIAttackOnCollide extends EntityAIBase
{
    World worldObj;
    LavaEntityMob attacker;
    EntityLiving entityTarget;
    int attackTick;
    float field_75440_e;
    boolean field_75437_f;
    PathEntity entityPathEntity;
    Class classTarget;
    private int field_75445_i;

    public LavaEntityAIAttackOnCollide(LavaEntityMob par1EntityLiving, Class par2Class, float par3, boolean par4)
    {
        this(par1EntityLiving, par3, par4);
        this.classTarget = par2Class;
    }

    public LavaEntityAIAttackOnCollide(LavaEntityMob par1EntityLiving, float par2, boolean par3)
    {
        this.attackTick = 0;
        this.attacker = par1EntityLiving;
        this.worldObj = par1EntityLiving.worldObj;
        this.field_75440_e = par2;
        this.field_75437_f = par3;
        this.setMutexBits(3);
    }

    public boolean shouldExecute()
    {
        EntityLiving entityliving = this.attacker.getAttackTarget();

        if (entityliving == null)
        {
            return false;
        }
        else if (this.classTarget != null && !this.classTarget.isAssignableFrom(entityliving.getClass()))
        {
            return false;
        }
        else
        {
            this.entityTarget = entityliving;
            this.entityPathEntity = this.attacker.getNavigator().getPathToEntityLiving(this.entityTarget);
            return this.entityPathEntity != null;
        }
    }

    public boolean continueExecuting()
    {
        EntityLiving entityliving = this.attacker.getAttackTarget();
        return entityliving == null ? false : (!this.entityTarget.isEntityAlive() ? false : (!this.field_75437_f ? !this.attacker.getNavigator().noPath() : this.attacker.isWithinHomeDistance(MathHelper.floor_double(this.entityTarget.posX), MathHelper.floor_double(this.entityTarget.posY), MathHelper.floor_double(this.entityTarget.posZ))));
    }

    public void startExecuting()
    {
        this.attacker.getNavigator().setPath(this.entityPathEntity, this.field_75440_e);
        this.field_75445_i = 0;
    }

    public void resetTask()
    {
        this.entityTarget = null;
        this.attacker.getNavigator().clearPathEntity();
    }

    public void updateTask()
    {
        this.attacker.getLookHelper().setLookPositionWithEntity(this.entityTarget, 30.0F, 30.0F);

        if ((this.field_75437_f || this.attacker.getEntitySenses().canSee(this.entityTarget)) && --this.field_75445_i <= 0)
        {
            this.field_75445_i = 4 + this.attacker.getRNG().nextInt(7);
            this.attacker.getNavigator().tryMoveToEntityLiving(this.entityTarget, this.field_75440_e);
        }

        this.attackTick = Math.max(this.attackTick - 1, 0);
        double d0 = (double)(this.attacker.width * 4.0F * this.attacker.width * 4.0F);

        if (this.attacker.getDistanceSq(this.entityTarget.posX, this.entityTarget.boundingBox.minY, this.entityTarget.posZ) <= d0 && this.attackTick <= 0)
        {
            this.attackTick = 20;
            this.attacker.attackEntityAsMob(this.entityTarget);
        }
    }
}
