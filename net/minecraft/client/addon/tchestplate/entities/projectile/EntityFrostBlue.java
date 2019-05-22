package net.minecraft.client.addon.tchestplate.entities.projectile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class EntityFrostBlue extends EntityFrostBase
{
    public EntityFrostBlue(World par1World)
    {
        super(par1World);
    }

    public EntityFrostBlue(World world, EntityPlayer p, int max)
    {
        super(world, p, 1.0F);
        super.duration = 1;
        super.millisec = max;
    }
}
