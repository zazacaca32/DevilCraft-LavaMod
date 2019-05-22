package net.minecraft.client.addon.tchestplate.entities.projectile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class EntityFrostRed extends EntityFrostBase
{
    public EntityFrostRed(World par1World)
    {
        super(par1World);
    }

    public EntityFrostRed(World world, EntityPlayer p, int max)
    {
        super(world, p, 1.0F);
        super.duration = 2;
        super.millisec = max;
    }
}
