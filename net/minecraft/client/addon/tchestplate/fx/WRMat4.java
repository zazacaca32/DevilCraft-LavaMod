package net.minecraft.client.addon.tchestplate.fx;

public class WRMat4
{
    float[] mat;

    public WRMat4()
    {
        this.loadIdentity();
    }

    public WRMat4 loadIdentity()
    {
        this.mat = new float[16];
        this.mat[15] = 1.0F;
        this.mat[10] = 1.0F;
        this.mat[5] = 1.0F;
        this.mat[0] = 1.0F;
        return this;
    }

    public WRVector3 translate(WRVector3 var1)
    {
        float var2 = var1.x * this.mat[0] + var1.y * this.mat[1] + var1.z * this.mat[2] + this.mat[3];
        float var3 = var1.x * this.mat[4] + var1.y * this.mat[5] + var1.z * this.mat[6] + this.mat[7];
        float var4 = var1.x * this.mat[8] + var1.y * this.mat[9] + var1.z * this.mat[10] + this.mat[11];
        var1.x = var2;
        var1.y = var3;
        var1.z = var4;
        return var1;
    }

    public static WRMat4 rotationMat(double var0, WRVector3 var2)
    {
        var2 = var2.copy().normalize();
        float var3 = var2.x;
        float var4 = var2.y;
        float var5 = var2.z;
        float var6 = (float)Math.cos(var0 *= 0.0174532925D);
        float var7 = 1.0F - var6;
        float var8 = (float)Math.sin(var0);
        WRMat4 var9 = new WRMat4();
        var9.mat[0] = var3 * var3 * var7 + var6;
        var9.mat[1] = var4 * var3 * var7 + var5 * var8;
        var9.mat[2] = var3 * var5 * var7 - var4 * var8;
        var9.mat[4] = var3 * var4 * var7 - var5 * var8;
        var9.mat[5] = var4 * var4 * var7 + var6;
        var9.mat[6] = var4 * var5 * var7 + var3 * var8;
        var9.mat[8] = var3 * var5 * var7 + var4 * var8;
        var9.mat[9] = var4 * var5 * var7 - var3 * var8;
        var9.mat[10] = var5 * var5 * var7 + var6;
        var9.mat[15] = 1.0F;
        return var9;
    }
}
