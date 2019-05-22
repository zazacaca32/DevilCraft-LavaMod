package net.minecraft.client.addon.tchestplate.armors.items;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.client.addon.tchestplate.Utils;
import net.minecraft.client.addon.tchestplate.armors.items.LavaChestPlateItem;
import net.minecraft.client.addon.tchestplate.util.KeyboardClient;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingFallEvent;

public class LavaChestPlateHelmet extends LavaChestPlateItem {

   public static Map jumpChargeMap = new HashMap();


   public LavaChestPlateHelmet(int par1, EnumArmorMaterial par2EnumArmorMaterial, int par3, int par4) {
      super(par1, par2EnumArmorMaterial, par3, par4, 2000, 1.0D);
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

   public LavaChestPlateHelmet(int par1, EnumArmorMaterial par2EnumArmorMaterial, int par3, int par4, int par5, int par6) {
      super(par1, par2EnumArmorMaterial, par3, par4, par5, (double)par6);
      MinecraftForge.EVENT_BUS.register(this);
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
      super.itemIcon = par1IconRegister.registerIcon("underqoder:LHelmet");
   }

   @SideOnly(Side.CLIENT)
   public String getArmorTexture(ItemStack stack, Entity entity, int slot, int layer) {
      return "/mods/underqoder/Armors/Lava/lavachest.png";
   }

   public boolean getNitghtVision(ItemStack armor) {
      if(armor.stackSize > 1) {
         return false;
      } else {
         NBTTagCompound nbtData = Utils.getOrCreateNbtData(armor);
         return nbtData.getBoolean("NitghtVision");
      }
   }

   public void setNitghtVision(ItemStack armor, boolean vision) {
      if(armor.stackSize <= 1) {
         NBTTagCompound nbtData = Utils.getOrCreateNbtData(armor);
         boolean _vision = nbtData.getBoolean("NitghtVision");
         if(_vision != vision) {
            nbtData.setBoolean("NitghtVision", vision);
         }
      }

   }

   @SideOnly(Side.CLIENT)
   public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List, boolean par4) {
      super.addInformation(par1ItemStack, par2EntityPlayer, par2List, par4);
      par2List.add("§7 Можно видеть ночью на " + KeyboardClient.tcNightKey);
   }

   public int getSlot() {
      return 0;
   }

}
