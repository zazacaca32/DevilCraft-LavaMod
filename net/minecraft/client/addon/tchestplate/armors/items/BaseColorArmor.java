package net.minecraft.client.addon.tchestplate.armors.items;

import net.minecraft.entity.Entity;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.IArmorTextureProvider;

public class BaseColorArmor extends ItemArmor
{
    /** @deprecated */
    @Deprecated
    private final boolean isArmorProvider = this instanceof IArmorTextureProvider;

    public BaseColorArmor(int par1, EnumArmorMaterial par2EnumArmorMaterial, int par3, int par4)
    {
        super(par1, par2EnumArmorMaterial, par3, par4);
    }

    public String getArmorTextur(ItemStack stack, Entity entity, int slot, int layer, NBTTagCompound nbtData)
    {
        return this.isArmorProvider ? ((IArmorTextureProvider)this).getArmorTextureFile(stack) : null;
    }
}
