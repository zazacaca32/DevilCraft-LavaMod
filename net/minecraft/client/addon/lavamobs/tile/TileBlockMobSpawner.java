package net.minecraft.client.addon.lavamobs.tile;

import net.minecraft.client.addon.lavamobs.entity.EntityCrazyMonkey;
import net.minecraft.client.addon.lavamobs.entity.EntityDendroid;
import net.minecraft.client.addon.lavamobs.entity.EntityFlySkeleton;
import net.minecraft.client.addon.lavamobs.entity.EntityGorilla;
import net.minecraft.client.addon.lavamobs.entity.EntityHamster;
import net.minecraft.client.addon.lavamobs.entity.EntityDemon;
import net.minecraft.client.addon.lavamobs.entity.EntityMobGoblin;
import net.minecraft.client.addon.lavamobs.entity.EntitySpiderPrison;
import net.minecraft.client.addon.lavamobs.entity.EntityWolfPrison;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

public class TileBlockMobSpawner extends TileEntity
{
    private int timer = 1;

    public void updateEntity()
    {
        --this.timer;

        if (this.timer <= 0)
        {
            this.timer = 400;

            if (!super.worldObj.isRemote)
            {
                int id = super.worldObj.getBlockMetadata(super.xCoord, super.yCoord, super.zCoord);
                int count = this.getPlayerCount(EntityPlayer.class);

                if (count > 0)
                {
                    this.timer = 400 / count;

                    switch (id)
                    {
                        case 0:
                            if (this.getPlayerCount(EntityMobGoblin.class) <= count * 2)
                            {
                                this.spawnlavamob(new EntityMobGoblin(super.worldObj));
                            }

                            break;

                        case 1:
                            if (this.getPlayerCount(EntitySpiderPrison.class) <= count * 2)
                            {
                                this.spawnlavamob(new EntitySpiderPrison(super.worldObj));
                            }

                            break;

                        case 2:
                            if (this.getPlayerCount(EntityHamster.class) <= count * 2)
                            {
                                this.spawnlavamob(new EntityHamster(super.worldObj));
                            }

                            break;

                        case 3:
                            if (this.getPlayerCount(EntityGorilla.class) <= count * 2)
                            {
                                this.spawnlavamob(new EntityGorilla(super.worldObj));
                            }

                            break;

                        case 4:
                            if (this.getPlayerCount(EntityDendroid.class) <= count * 2)
                            {
                                this.spawnlavamob(new EntityDendroid(super.worldObj));
                            }

                            break;

                        case 5:
                            if (this.getPlayerCount(EntityFlySkeleton.class) <= count * 2)
                            {
                                this.spawnlavamob(new EntityFlySkeleton(super.worldObj));
                            }

                            break;

                        case 6:
                            if (this.getPlayerCount(EntityCrazyMonkey.class) <= count * 2)
                            {
                                this.spawnlavamob(new EntityCrazyMonkey(super.worldObj));
                            }

                            break;

                        case 7:
                            if (this.getPlayerCount(EntityDemon.class) <= count * 2)
                            {
                                this.spawnlavamob(new EntityDemon(super.worldObj));
                            }
                    }
                }
            }
        }
    }

    public int getPlayerCount(Class entclass)
    {
        int j = super.worldObj.getEntitiesWithinAABB(entclass, AxisAlignedBB.getAABBPool().getAABB((double)super.xCoord, (double)super.yCoord, (double)super.zCoord, (double)(super.xCoord + 1), (double)(super.yCoord + 1), (double)(super.zCoord + 1)).expand(12.0D, 8.0D, 12.0D)).size();
        return j;
    }

    public void spawnlavamob(Entity eentity)
    {
        eentity.setLocationAndAngles((double)super.xCoord, (double)(super.yCoord + 1), (double)super.zCoord, 0.0F, 0.0F);
        super.worldObj.spawnEntityInWorld(eentity);
    }
}
