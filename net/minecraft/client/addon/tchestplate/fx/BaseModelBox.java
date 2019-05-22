package net.minecraft.client.addon.tchestplate.fx;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

@SideOnly(Side.CLIENT)
public abstract class BaseModelBox extends ModelBase
{
    double f1;
    double f2;

    public abstract void render(Vec3 var1, float var2, byte var3);

    public void setRotate(float var1)
    {
        this.f1 = (double)MathHelper.cos(-var1 * 0.017453292F);
        this.f2 = (double)MathHelper.sin(-var1 * 0.017453292F);
    }

    public abstract void finalRender(double var1, double var3, double var5);
}
