package net.minecraft.client.addon.tchestplate.fx;

import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Vec3;

public class WRVector3
{
    public float x;
    public float y;
    public float z;

    public WRVector3(double var1, double var3, double var5)
    {
        this.x = (float)var1;
        this.y = (float)var3;
        this.z = (float)var5;
    }

    public WRVector3(TileEntity var1)
    {
        this.x = (float)var1.xCoord + 0.5F;
        this.y = (float)var1.yCoord + 0.5F;
        this.z = (float)var1.zCoord + 0.5F;
    }

    public WRVector3(Entity var1)
    {
        this(var1.posX, var1.posY, var1.posZ);
    }

    public WRVector3 add(WRVector3 var1)
    {
        this.x += var1.x;
        this.y += var1.y;
        this.z += var1.z;
        return this;
    }

    public WRVector3 sub(WRVector3 var1)
    {
        this.x -= var1.x;
        this.y -= var1.y;
        this.z -= var1.z;
        return this;
    }

    public WRVector3 scale(float var1)
    {
        this.x *= var1;
        this.y *= var1;
        this.z *= var1;
        return this;
    }

    public WRVector3 scale(float var1, float var2, float var3)
    {
        this.x *= var1;
        this.y *= var2;
        this.z *= var3;
        return this;
    }

    public WRVector3 normalize()
    {
        float var1 = this.length();
        this.x /= var1;
        this.y /= var1;
        this.z /= var1;
        return this;
    }

    public float length()
    {
        return (float)Math.sqrt((double)(this.x * this.x + this.y * this.y + this.z * this.z));
    }

    public float lengthPow2()
    {
        return this.x * this.x + this.y * this.y + this.z * this.z;
    }

    public WRVector3 copy()
    {
        return new WRVector3((double)this.x, (double)this.y, (double)this.z);
    }

    public static WRVector3 crossProduct(WRVector3 var0, WRVector3 var1)
    {
        return new WRVector3((double)(var0.y * var1.z - var0.z * var1.y), (double)(var0.z * var1.x - var0.x * var1.z), (double)(var0.x * var1.y - var0.y * var1.x));
    }

    public static WRVector3 xCrossProduct(WRVector3 var0)
    {
        return new WRVector3(0.0D, (double)var0.z, (double)(-var0.y));
    }

    public static WRVector3 zCrossProduct(WRVector3 var0)
    {
        return new WRVector3((double)(-var0.y), (double)var0.x, 0.0D);
    }

    public static float dotProduct(WRVector3 var0, WRVector3 var1)
    {
        return var0.x * var1.x + var0.y * var1.y + var0.z * var1.z;
    }

    public static float angle(WRVector3 var0, WRVector3 var1)
    {
        return anglePreNorm(var0.copy().normalize(), var1.copy().normalize());
    }

    public static float anglePreNorm(WRVector3 var0, WRVector3 var1)
    {
        return (float)Math.acos((double)dotProduct(var0, var1));
    }

    public WRVector3 rotate(float var1, WRVector3 var2)
    {
        return WRMat4.rotationMat((double)var1, var2).translate(this);
    }

    public String toString()
    {
        return "[" + this.x + "," + this.y + "," + this.z + "]";
    }

    public Vec3 toVec3D()
    {
        return Vec3.createVectorHelper((double)this.x, (double)this.y, (double)this.z);
    }

    public static WRVector3 getPerpendicular(WRVector3 var0)
    {
        return var0.z == 0.0F ? zCrossProduct(var0) : xCrossProduct(var0);
    }

    public boolean isZero()
    {
        return this.x == 0.0F && this.y == 0.0F && this.z == 0.0F;
    }
}
