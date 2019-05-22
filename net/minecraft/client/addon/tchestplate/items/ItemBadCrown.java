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

public class ItemBadCrown extends MultiItemBase implements IArmorExt, IRenderItemChestPlateExt
{
    private long lasttick;

    public ItemBadCrown(int i, int count)
    {
        super(i, count);
        super.maxStackSize = 1;
    }

    public void onCreated(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List, boolean par4)
    {
        switch (par1ItemStack.getItemDamage())
        {
            case 0:
                par2List.add("§6 Даёт огненную корону");
                return;

            default:
        }
    }

    public int getSlot()
    {
        return 0;
    }

    @SideOnly(Side.CLIENT)
    public BaseItemModel getItemModel(int Damage)
    {
        return null;
    }

    @SideOnly(Side.CLIENT)
    public BaseItemModel getItemModelStatic(int Damage)
    {
        return null;
    }

    public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack)
    {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack par1ItemStack)
    {
        return false;
    }

    public boolean isBookEnchantable(ItemStack itemstack1, ItemStack itemstack2)
    {
        return false;
    }

    protected float interpolateRotation(float par1, float par2, float par3)
    {
        float f3;

        for (f3 = par2 - par1; f3 < -180.0F; f3 += 360.0F)
        {
            ;
        }

        while (f3 >= 180.0F)
        {
            f3 -= 360.0F;
        }

        return par1 + par3 * f3;
    }

    public void doRender(ModelPlayer modelPlayer, EntityPlayer player, float scale, float time, ExtendedPlayer pii, float var2, float var3, float var5, float var6, ItemStack item)
    {
        if (ClientTickHandler.timer != this.lasttick)
        {
            this.lasttick = ClientTickHandler.timer;
            double px = player.posX;
            double f10 = 0.1D;

            if (player.inventory.armorItemInSlot(3) != null)
            {
                f10 = 0.3D;
            }

            double py = player.boundingBox.minY + (double)player.height + f10;
            double pz = player.posZ;
            float par1 = 0.01F;
            double x;
            double y;
            double pi;
            double xc;
            double yc;

            if (pii.inventoryExt.getStackInSlot(0).getItemDamage() == 0)
            {
                x = (Math.PI / 4D);
                y = px;
                pi = pz;
                xc = 0.45D;
                yc = 0.0D;
                GL11.glPushMatrix();

                for (int var371 = 0; var371 < 8; ++var371)
                {
                    double x1 = y + (xc - 0.0D) * Math.cos(yc);
                    double y1 = pi + (xc - 0.0D) * Math.sin(yc);
                    yc += x1;
                    LavaChestPlate.proxy.spawnParticleFlame((double)((float)x1), (double)((float)py + 0.2F), (double)((float)y1), 9.999999747378752E-5D, 9.999999747378752E-5D, 9.999999747378752E-5D, pii.PredatorMode == 50);
                }

                GL11.glPopMatrix();
            }
            else if (pii.inventoryExt.getStackInSlot(0).getItemDamage() == 1)
            {
                pi = 0.39269908169872414D;
                xc = px;
                yc = pz;
                double var37 = 0.45D;
                double u = 0.0D;
                var37 = 0.65D;

                for (int var381 = 0; var381 < 7; ++var381)
                {
                    for (var381 = 0; var381 < 16; ++var381)
                    {
                        x = xc + (var37 - (double)((float)var381 / 10.0F)) * Math.cos(u);
                        y = yc + (var37 - (double)((float)var381 / 10.0F)) * Math.sin(u);
                        u += pi;
                        LavaChestPlate.proxy.sparkle((float)x, (float)py - 0.2F + (float)var381 / 10.0F, (float)y, 1.0F, 2, 0.1F, 7, pii.PredatorMode == 50);
                    }
                }

                var37 = 0.4D;
                byte var38 = 40;

                for (int r = 0; r < 2; ++r)
                {
                    for (int i = 0; i < 16; ++i)
                    {
                        x = xc + (var37 + (double)((float)r * 0.005F)) * Math.cos(u);
                        y = yc + (var37 + (double)((float)r * 0.005F)) * Math.sin(u);
                        u += pi;
                        LavaChestPlate.proxy.sparkle((float)x, (float)py - 0.2F - (float)r * 0.1F, (float)y, 1.0F, 5, 0.1F, var38, pii.PredatorMode == 50);
                    }
                }
            }
        }
    }
}
