package net.minecraft.client.addon.tco.tiny.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.addon.tchestplate.LavaChestPlate;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class Trap11 extends Item {

   public Trap11(int par1) {
      super(par1);
      this.setCreativeTab(LavaChestPlate.tabItemss);
   }

   public void registerIcons(IconRegister par1IconRegister) {
      super.itemIcon = par1IconRegister.registerIcon("provider:toch_11");
   }

   @SideOnly(Side.CLIENT)
   public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List, boolean par4) {
      par2List.add("§8 Свиток может использоватся в");
      par2List.add("§8 прессе оружия в левом слоте.");
      par2List.add("§8 Шанс спрессовки §a70%");
   }
}
