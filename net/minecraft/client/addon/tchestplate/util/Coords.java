package net.minecraft.client.addon.tchestplate.util;

import net.minecraft.entity.player.EntityPlayer;

public class Coords
{
    public float x;
    public float y;
    public float z;

    public Coords(float x, float y, float z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Coords(EntityPlayer player)
    {
        this.setCoods(player);
    }

    public Coords subtract(float x, float y, float z)
    {
        this.x -= x;
        this.y -= y;
        this.z -= z;
        return this;
    }

    public Coords add(float x, float y, float z)
    {
        this.x += x;
        this.y += y;
        this.z += z;
        return this;
    }

    public void setCoods(EntityPlayer player)
    {
        this.x = (float)player.posX;
        this.y = (float)player.posY;
        this.z = (float)player.posZ;
    }

    public boolean isInCube(Coords coord, int radius)
    {
        return this.x > coord.x - (float)radius && this.x < coord.x + (float)radius && this.z > coord.z - (float)radius && this.z < coord.z + (float)radius && this.y > coord.y - (float)radius && this.y < coord.y + (float)radius;
    }
}
