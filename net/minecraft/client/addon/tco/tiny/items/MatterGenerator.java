package net.minecraft.client.addon.tco.tiny.items;

import java.util.List;
import net.minecraft.client.addon.tchestplate.LavaChestPlate;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class MatterGenerator extends Item {

   public MatterGenerator(int i, int j) {
      super(i);
      super.maxStackSize = j;
      this.setCreativeTab(LavaChestPlate.tabItemss);
   }

   public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List, boolean par4) {
      par2List.add("§dДобавляет к производству материи 16384 E/U");
   }

   public void registerIcons(IconRegister par1IconRegister) {
      super.itemIcon = par1IconRegister.registerIcon("tco:mattergenerator");
   }
}
