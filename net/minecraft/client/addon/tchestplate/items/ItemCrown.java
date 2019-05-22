package net.minecraft.client.addon.tchestplate.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.addon.tchestplate.ClientTickHandler;
import net.minecraft.client.addon.tchestplate.IUpdateItemExtPlayer;
import net.minecraft.client.addon.tchestplate.LavaChestPlate;
import net.minecraft.client.addon.tchestplate.overlaygui.IArmorExt;
import net.minecraft.client.addon.tchestplate.player.ExtendedPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class ItemCrown extends MultiItemBase implements IArmorExt, IUpdateItemExtPlayer
{
    @SideOnly(Side.CLIENT)
    public static Item[] item;
    private Double alfa = Double.valueOf(0.0D);
    private long lasttick;

    public ItemCrown(int i, int count)
    {
        super(i, count);
        super.maxStackSize = 1;
    }

    public void onCreated(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
    }

    public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack)
    {
        return false;
    }

    public void addInformation(ItemStack item, EntityPlayer par2EntityPlayer, List info, boolean par4)
    {
        switch (item.getItemDamage())
        {
            case 0:
                info.add("§8Горит над головой");
                info.add("вss!");
                break;

            case 1:
                info.add("§8Булькает только под водой");
                info.add("вss!");
        }
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

    public int getCalculateProcDamage(ItemStack it, int damage_)
    {
        return damage_;
    }

    public int getCalculateAttackDamage(ItemStack it, int damage_)
    {
        return damage_;
    }

    public int getSlot()
    {
        return 0;
    }

    public int getProcofDamage(int damage)
    {
        return 0;
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

    public void onUpdate(ItemStack stack, World world, ExtendedPlayer eplayer, int slot, boolean isCurrentItem, long tickinslot, IUpdateItemExtPlayer.SideExt arg6)
    {
        if (ClientTickHandler.timer != this.lasttick)
        {
            this.lasttick = ClientTickHandler.timer;
            double px = eplayer.player.posX;
            double f10 = 0.1D;

            if (eplayer.player.inventory.armorItemInSlot(3) != null)
            {
                f10 = 0.3D;
            }

            double py = eplayer.player.boundingBox.minY + (double)eplayer.player.height + f10;
            double pz = eplayer.player.posZ;
            float par1 = 0.01F;
            double pi2;
            double xc2;
            double yc2;
            double radius2;
            double u2;
            int t;
            double x2;
            double y21;

            switch (eplayer.inventoryExt.inventory[0].getItemDamage())
            {
                case 0:
                    if (eplayer != null && eplayer.inventoryExt != null && eplayer.inventoryExt.inventory != null && eplayer.inventoryExt.inventory[0] != null & slot == 0)
                    {
                        pi2 = (Math.PI / 4D);
                        xc2 = px;
                        yc2 = pz;
                        radius2 = 0.45D;
                        u2 = 0.0D;
                        GL11.glPushMatrix();

                        for (t = 0; t < 8; ++t)
                        {
                            y21 = xc2 + 0.45D * Math.cos(u2);
                            x2 = yc2 + 0.45D * Math.sin(u2);
                            u2 += (Math.PI / 4D);
                            LavaChestPlate.proxy.spawnParticleFlame((double)((float)y21), (double)((float)py + 0.2F), (double)((float)x2), 9.999999747378752E-5D, 9.999999747378752E-5D, 9.999999747378752E-5D, eplayer.PredatorMode == 50);
                        }

                        GL11.glPopMatrix();
                    }

                    break;

                case 1:
                    if (eplayer != null && eplayer.inventoryExt != null && eplayer.inventoryExt.inventory != null && eplayer.inventoryExt.inventory[0] != null & slot == 0)
                    {
                        pi2 = 0.39269908169872414D;
                        xc2 = px;
                        yc2 = pz;
                        radius2 = 0.45D;
                        u2 = 0.0D;
                        radius2 = 0.65D;
                        int r2;

                        for (t = 0; t < 7; ++t)
                        {
                            for (r2 = 0; r2 < 16; ++r2)
                            {
                                double var36 = xc2 + (radius2 - (double)((float)t / 10.0F)) * Math.cos(u2);
                                y21 = yc2 + (radius2 - (double)((float)t / 10.0F)) * Math.sin(u2);
                                u2 += 0.39269908169872414D;
                                LavaChestPlate.proxy.sparkle((float)var36, (float)py - 0.2F + (float)t / 10.0F, (float)y21, 1.0F, 2, 0.1F, 7, eplayer.PredatorMode == 50);
                            }
                        }

                        radius2 = 0.4D;
                        boolean var361 = true;

                        for (r2 = 0; r2 < 2; ++r2)
                        {
                            for (int var37 = 0; var37 < 16; ++var37)
                            {
                                x2 = xc2 + (radius2 + (double)((float)r2 * 0.005F)) * Math.cos(u2);
                                y21 = yc2 + (radius2 + (double)((float)r2 * 0.005F)) * Math.sin(u2);
                                u2 += 0.39269908169872414D;
                                LavaChestPlate.proxy.sparkle((float)x2, (float)py - 0.2F - (float)r2 * 0.1F, (float)y21, 1.0F, 5, 0.1F, 40, eplayer.PredatorMode == 50);
                            }
                        }
                    }
            }
        }
    }
}
