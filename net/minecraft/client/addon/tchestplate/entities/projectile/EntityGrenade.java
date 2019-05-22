package net.minecraft.client.addon.tchestplate.entities.projectile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class EntityGrenade extends EntityFrostBase
{
    public EntityGrenade(World par1World)
    {
        super(par1World);
    }

    public EntityGrenade(World world, EntityPlayer p)
    {
        super(world, p, 1.0F);
        super.duration = 1;
        super.millisec = 500;
        super.damage = 1000.0D;
    }
}
