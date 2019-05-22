package net.minecraft.client.addon.tchestplate.armors.items;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.addon.tchestplate.armors.items.LavaChestPlateItem;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingFallEvent;

public class LavaChestPlateLeggins extends LavaChestPlateItem {

   public static Map jumpChargeMap = new HashMap();


   public LavaChestPlateLeggins(int par1, EnumArmorMaterial par2EnumArmorMaterial, int par3, int par4) {
      super(par1, par2EnumArmorMaterial, par3, par4, 2000, 1.0D);
      MinecraftForge.EVENT_BUS.register(this);
   }

   public LavaChestPlateLeggins(int par1, EnumArmorMaterial par2EnumArmorMaterial, int par3, int par4, int par5, int par6) {
      super(par1, par2EnumArmorMaterial, par3, par4, par5, (double)par6);
      MinecraftForge.EVENT_BUS.register(this);
   }

   @SideOnly(Side.CLIENT)
   protected int getModel(ItemStack var1) {
      return 21;
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
      super.itemIcon = par1IconRegister.registerIcon("underqoder:LLeggins");
   }

   @SideOnly(Side.CLIENT)
   public String getArmorTexture(ItemStack stack, Entity entity, int slot, int layer) {
      return "/mods/underqoder/Armors/Lava/lavachest.png";
   }

   public void onArmorTickUpdate(World world, EntityPlayer player, ItemStack itemStack) {
      super.onArmorTickUpdate(world, player, itemStack);
      if(FMLCommonHandler.instance().getEffectiveSide().isClient()) {
         if(Minecraft.getMinecraft().thePlayer != player) {
            return;
         }

         Minecraft.getMinecraft().mcProfiler.startSection("LavaChestPlateLeggins");
         if(player.onGround && player.isSprinting()) {
            float speed = 0.22F;
            if(player.isInWater()) {
               speed = 0.1F;
               if(player.isJumping) {
                  player.motionY += 0.1000000014901161D;
               }
            }

            if(speed > 0.0F) {
               player.moveFlying(0.0F, 1.0F, speed);
            }
         }

         Minecraft.getMinecraft().mcProfiler.endSection();
      }

   }

   @SideOnly(Side.CLIENT)
   public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List, boolean par4) {
      super.addInformation(par1ItemStack, par2EntityPlayer, par2List, par4);
      par2List.add("§7 Можно быстро бегать");
   }

   public int getSlot() {
      return 2;
   }

}
