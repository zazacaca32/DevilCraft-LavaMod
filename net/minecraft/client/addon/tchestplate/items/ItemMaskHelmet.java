package net.minecraft.client.addon.tchestplate.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.addon.tchestplate.ClientProxy;
import net.minecraft.client.addon.tchestplate.overlaygui.IArmorExt;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemMaskHelmet extends MultiItemBase implements IArmorExt
{
    public ItemMaskHelmet(int i, int count)
    {
        super(i, count);
        super.maxStackSize = 1;
    }

    @SideOnly(Side.CLIENT)
    public ModelBiped getItemModel(int Damage)
    {
        switch (Damage)
        {
            case 0:
                return (ModelBiped)ClientProxy.Models[12];

            case 1:
                return (ModelBiped)ClientProxy.Models[13];

            case 2:
                return (ModelBiped)ClientProxy.Models[15];

            case 3:
                return (ModelBiped)ClientProxy.Models[16];

            case 4:
                return (ModelBiped)ClientProxy.Models[17];

            default:
                return null;
        }
    }

    public void onCreated(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List, boolean par4)
    {
        par2List.add("§6 Отображается на шлемах лава мода");

        switch (par1ItemStack.getItemDamage())
        {
            case 0:
                par2List.add("§6 Дает защиту 5%");
                break;

            case 1:
                par2List.add("§6 Дает защиту 5%");
                break;

            case 2:
                par2List.add("§6 Дает защиту 5%");
                break;

            case 3:
                par2List.add("§6 Дает защиту 5%");
                break;

            case 4:
                par2List.add("§6 Шапка выпускника 2019 год.");
                par2List.add("§6 Выдана в честь окончания");
                par2List.add("§6 учебного полугодия.");
        }
    }

    public int getSlot()
    {
        return 0;
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
}
