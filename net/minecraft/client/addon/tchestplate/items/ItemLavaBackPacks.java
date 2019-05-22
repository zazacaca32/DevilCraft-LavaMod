package net.minecraft.client.addon.tchestplate.items;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.addon.tchestplate.ClientProxy;
import net.minecraft.client.addon.tchestplate.LavaChestPlate;
import net.minecraft.client.addon.tchestplate.items.renders.models.BaseItemModel;
import net.minecraft.client.addon.tchestplate.overlaygui.IArmorExt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ItemLavaBackPacks
  extends MultiItemBase
  implements IArmorExt, IRenderItemChestPlate
{
  public ItemLavaBackPacks(int paramInt1, int paramInt2)
  {
    super(paramInt1, paramInt2);
    this.maxStackSize = 1;
  }
  
  @SideOnly(Side.CLIENT)
  public void addInformation(ItemStack paramItemStack, EntityPlayer paramEntityPlayer, List paramList, boolean paramBoolean)
  {
    switch (paramItemStack.getItemDamage())
    {
    case 0: 
      paramList.add("�6 Колчан для стрел");
      paramList.add("�7 Одеваеться на спину");
      int i = 0;
      if ((paramItemStack.stackTagCompound != null) && (paramItemStack.stackTagCompound.hasKey("arrows"))) {
        i = paramItemStack.stackTagCompound.getShort("arrows");
      }
      paramList.add("�5  Содержит: " + i + " из 760 стрел");
      break;
    case 1: 
      paramList.add("�6 Служит для сбора лава руд, урана");
      paramList.add("�7 Надеваеться на спину");
      paramList.add("�5  Количество в слоте зависит от улучшения");
    }
  }
  
  @SideOnly(Side.CLIENT)
  public BaseItemModel getItemModel(int paramInt)
  {
    if (paramInt > 1) {
      return null;
    }
    if (paramInt == 1) {
      return (BaseItemModel)ClientProxy.Models[93];
    }
    return (BaseItemModel)ClientProxy.Models[92];
  }
  
  @SideOnly(Side.CLIENT)
  public BaseItemModel getItemModelStatic(int paramInt)
  {
    return null;
  }
  
  public boolean getIsRepairable(ItemStack paramItemStack1, ItemStack paramItemStack2)
  {
    return false;
  }
  
  @SideOnly(Side.CLIENT)
  public boolean hasEffect(ItemStack paramItemStack)
  {
    return false;
  }
  
  public boolean isBookEnchantable(ItemStack paramItemStack1, ItemStack paramItemStack2)
  {
    return false;
  }
  
  public int getSlot()
  {
    return 6;
  }
}
