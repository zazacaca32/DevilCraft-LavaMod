package net.minecraft.client.addon.tchestplate.armors.items;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.client.addon.tchestplate.armors.items.LavaChestPlateItem;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingFallEvent;

public class LavaChestPlateChest extends LavaChestPlateItem {

   public static Map jumpChargeMap = new HashMap();


   public LavaChestPlateChest(int par1, EnumArmorMaterial par2EnumArmorMaterial, int par3, int par4) {
      super(par1, par2EnumArmorMaterial, par3, par4, 2000, 1.0D);
      MinecraftForge.EVENT_BUS.register(this);
   }

   public LavaChestPlateChest(int par1, EnumArmorMaterial par2EnumArmorMaterial, int par3, int par4, int par5, int par6) {
      super(par1, par2EnumArmorMaterial, par3, par4, par5, (double)par6);
      MinecraftForge.EVENT_BUS.register(this);
   }

   @SideOnly(Side.CLIENT)
   protected int getModel(ItemStack var1) {
      return 20;
   }

   @SideOnly(Side.CLIENT)
   public boolean hasEffect(ItemStack par1ItemStack) {
      return true;
   }

   @ForgeSubscribe
   public void onEntityLivingFallEvent(LivingFallEvent event) {
      if(!FMLCommonHandler.instance().getEffectiveSide().isClient() && event.entity instanceof EntityLiving) {
         EntityLiving entity = (EntityLiving)event.entity;
         ItemStack armor = entity.getCurrentArmor(0);
         if(armor != null && armor.getItem() == this) {
            event.distance = 0.0F;
            event.setCanceled(true);
         }
      }

   }

   @SideOnly(Side.CLIENT)
   public void registerIcons(IconRegister par1IconRegister) {
      super.itemIcon = par1IconRegister.registerIcon("underqoder:LChest");
   }

   @SideOnly(Side.CLIENT)
   public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List, boolean par4) {
      super.addInformation(par1ItemStack, par2EntityPlayer, par2List, par4);
      par2List.add("§7 Можно летать как в креативе");
   }

   @SideOnly(Side.CLIENT)
   public String getArmorTexture(ItemStack stack, Entity entity, int slot, int layer) {
      return "/mods/underqoder/Armors/Lava/lavachest.png";
   }

   public int getSlot() {
      return 1;
   }

}
