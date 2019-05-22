package net.minecraft.client.addon.tchestplate.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.addon.tchestplate.ClientTickHandler;
import net.minecraft.client.addon.tchestplate.LavaChestPlate;
import net.minecraft.client.addon.tchestplate.items.renders.models.BaseItemModel;
import net.minecraft.client.addon.tchestplate.overlaygui.IArmorExt;
import net.minecraft.client.addon.tchestplate.player.ExtendedPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.src.ModelPlayer;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class ItemKorona extends MultiItemBase implements IArmorExt, IRenderItemChestPlateExt
{
    private long lasttick;

    public ItemKorona(int var1, int var2)
    {
        super(var1, var2);
        super.maxStackSize = 1;
    }

    public void onCreated(ItemStack var1, World var2, EntityPlayer var3)
    {
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack var1, EntityPlayer var2, List var3, boolean var4)
    {
        switch (var1.getItemDamage())
        {
            case 0:
                var3.add("§6 Даёт огненную корону");
                return;

            default:
        }
    }

    public int getSlot()
    {
        return 1;
    }

    @SideOnly(Side.CLIENT)
    public BaseItemModel getItemModel(int var1)
    {
        return null;
    }

    @SideOnly(Side.CLIENT)
    public BaseItemModel getItemModelStatic(int var1)
    {
        return null;
    }

    public boolean getIsRepairable(ItemStack var1, ItemStack var2)
    {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack var1)
    {
        return false;
    }

    public boolean isBookEnchantable(ItemStack var1, ItemStack var2)
    {
        return false;
    }

    protected float interpolateRotation(float var1, float var2, float var3)
    {
        float var4;

        for (var4 = var2 - var1; var4 < -180.0F; var4 += 360.0F)
        {
            ;
        }

        while (var4 >= 180.0F)
        {
            var4 -= 360.0F;
        }

        return var1 + var3 * var4;
    }

    public void doRender(ModelPlayer var1, EntityPlayer var2, float var3, float var4, ExtendedPlayer var5, float var6, float var7, float var8, float var9, ItemStack var10)
    {
        if (ClientTickHandler.timer != this.lasttick)
        {
            this.lasttick = ClientTickHandler.timer;
            double var11 = var2.posX;
            double var13 = 0.1D;

            if (var2.inventory.armorItemInSlot(3) != null)
            {
                var13 = 0.3D;
            }

            double var15 = var2.boundingBox.minY + (double)var2.height + var13;
            double var17 = var2.posZ;
            float var19 = 0.01F;
            double var24;
            double var26;
            double var28;
            int var30;
            double var31;
            double var33;
            double var20;
            double var22;

            if (var10.getItemDamage() == 0)
            {
                var20 = (Math.PI / 4D);
                var22 = var11;
                var24 = var17;
                var26 = 0.45D;
                var28 = 0.0D;
                GL11.glPushMatrix();

                for (var30 = 0; var30 < 8; ++var30)
                {
                    var31 = var22 + (var26 - 0.0D) * Math.cos(var28);
                    var33 = var24 + (var26 - 0.0D) * Math.sin(var28);
                    var28 += var20;
                    LavaChestPlate.proxy.spawnParticleFlame((double)((float)var31), (double)((float)var15 + 0.2F), (double)((float)var33), 9.999999747378752E-5D, 9.999999747378752E-5D, 9.999999747378752E-5D, var5.PredatorMode == 50);
                }

                GL11.glPopMatrix();
            }
            else if (var10.getItemDamage() == 1)
            {
                var20 = 0.39269908169872414D;
                var22 = var11;
                var24 = var17;
                var26 = 0.45D;
                var28 = 0.0D;
                var26 = 0.65D;
                int var35;

                for (var30 = 0; var30 < 7; ++var30)
                {
                    for (var35 = 0; var35 < 16; ++var35)
                    {
                        var31 = var22 + (var26 - (double)((float)var30 / 10.0F)) * Math.cos(var28);
                        var33 = var24 + (var26 - (double)((float)var30 / 10.0F)) * Math.sin(var28);
                        var28 += var20;
                        LavaChestPlate.proxy.sparkle((float)var31, (float)var15 - 0.2F + (float)var30 / 10.0F, (float)var33, 1.0F, 2, 0.1F, 7, var5.PredatorMode == 50);
                    }
                }

                var26 = 0.4D;
                byte var37 = 40;

                for (var35 = 0; var35 < 2; ++var35)
                {
                    for (int var36 = 0; var36 < 16; ++var36)
                    {
                        var31 = var22 + (var26 + (double)((float)var35 * 0.005F)) * Math.cos(var28);
                        var33 = var24 + (var26 + (double)((float)var35 * 0.005F)) * Math.sin(var28);
                        var28 += var20;
                        LavaChestPlate.proxy.sparkle((float)var31, (float)var15 - 0.2F - (float)var35 * 0.1F, (float)var33, 1.0F, 5, 0.1F, var37, var5.PredatorMode == 50);
                    }
                }
            }
        }
    }
}
