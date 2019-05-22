package net.minecraft.client.addon.tchestplate.fx;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.Vec3;

@SideOnly(Side.CLIENT)
public class BoxModel
{
    BaseModelBox b1;
    public float x;
    public float y;
    public float z;
    public boolean mirror;

    public BoxModel(BaseModelBox var1, int var2, int var3)
    {
        this.b1 = var1;
    }

    public void render(Vec3 var1)
    {
        double var2 = (double)this.x * this.b1.f1 + (double)this.z * this.b1.f2;
        double var4 = (double)this.y;
        double var6 = (double)this.z * this.b1.f1 - (double)this.x * this.b1.f2;
        this.b1.finalRender(var1.xCoord + var2, var1.yCoord + (double)this.y, var1.zCoord + var6);
    }

    public void addBox(float var1, float var2, float var3, int var4, int var5, int var6)
    {
        this.x = -var1 / 16.0F;
        this.y = -var2 / 16.0F;
        this.z = -var3 / 16.0F;
    }

    public void setRotationPoint(float var1, float var2, float var3)
    {
    }

    public void setTextureSize(int var1, int var2)
    {
    }
}
