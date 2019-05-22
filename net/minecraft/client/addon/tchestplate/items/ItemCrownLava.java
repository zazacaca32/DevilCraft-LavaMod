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

public class ItemCrownLava extends MultiItemBase implements IArmorExt, IRenderItemChestPlateExt
{
    private long lasttick;

    public ItemCrownLava(int i, int count)
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

            case 2:
                par2List.add("§aДает вам по ебалу хотеть не вредно");
                return;

            default:
        }
    }

    public int getSlot()
    {
        return 1;
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

            if (item.getItemDamage() == 0)
            {
                double pi = (Math.PI / 4D);
                double xc = px;
                double yc = pz;
                double radius = 0.45D;
                double u = 0.0D;
                GL11.glPushMatrix();

                for (int i = 0; i < 8; ++i)
                {
                    double x = xc + (radius - 0.0D) * Math.cos(u);
                    double y = yc + (radius - 0.0D) * Math.sin(u);
                    u += pi;
                    LavaChestPlate.proxy.spawnParticleFlame((double)((float)x), (double)((float)py + 0.2F), (double)((float)y), 9.999999747378752E-5D, 9.999999747378752E-5D, 9.999999747378752E-5D, pii.PredatorMode == 50);
                }

                GL11.glPopMatrix();
            }
        }
    }
}
