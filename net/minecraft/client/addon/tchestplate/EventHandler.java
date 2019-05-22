package net.minecraft.client.addon.tchestplate;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.Color;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.logging.Level;
import net.minecraft.client.Minecraft;
import net.minecraft.client.addon.tchestplate.ClientProxy;
import net.minecraft.client.addon.tchestplate.ClientTickHandler;
import net.minecraft.client.addon.tchestplate.IFastRightClickItem;
import net.minecraft.client.addon.tchestplate.ILavaItem;
import net.minecraft.client.addon.tchestplate.LavaChestPlate;
import net.minecraft.client.addon.tchestplate.Utils;
import net.minecraft.client.addon.tchestplate.aaamodule.fx.ILavaItemd;
import net.minecraft.client.addon.tchestplate.armors.items.LavaChestPlateItem;
import net.minecraft.client.addon.tchestplate.armors.models.ModelBaseLavaArmor;
import net.minecraft.client.addon.tchestplate.donate.gui.DonateGui;
import net.minecraft.client.addon.tchestplate.fx.manager.FX_base;
import net.minecraft.client.addon.tchestplate.items.ItemAmulets;
import net.minecraft.client.addon.tchestplate.items.ItemBracelets;
import net.minecraft.client.addon.tchestplate.items.ItemRunes;
import net.minecraft.client.addon.tchestplate.items.ItemShields;
import net.minecraft.client.addon.tchestplate.packets.PacketMARemoveFX;
import net.minecraft.client.addon.tchestplate.packets.PacketMASendItemStackInSlot;
import net.minecraft.client.addon.tchestplate.packets.PacketMAUpdateByte;
import net.minecraft.client.addon.tchestplate.packets.PacketMAUpdateFX;
import net.minecraft.client.addon.tchestplate.packets.PacketMAUpdateInt;
import net.minecraft.client.addon.tchestplate.packets.PacketMAparticle;
import net.minecraft.client.addon.tchestplate.player.ExtendedPlayer;
import net.minecraft.client.addon.tchestplate.weapon.items.BaseEnergyItem;
import net.minecraft.client.addon.tchestplate.weapon.items.BaseEnergyOilItem;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Post;
import net.minecraftforge.client.event.RenderPlayerEvent.SetArmorModel;
import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.PlayerDestroyItemEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;
import org.lwjgl.opengl.GL11;

public class EventHandler {
   final String[] getModelArmor = new String[]{"field_77111_i", "modelArmor"};
   final String[] getModelArmorChestplate = new String[]{"field_77108_b", "modelArmorChestplate"};
   final String[] getMainModel = new String[]{"field_77045_g", "mainModel"};
   
   @SideOnly(Side.CLIENT) 
   @ForgeSubscribe 
   public void Post(RenderGameOverlayEvent event) { 
   if (event.type == RenderGameOverlayEvent.ElementType.HOTBAR) { 
   GL11.glPushMatrix(); 
   GL11.glScalef(0.70f, 0.70f, 0.70f); 
   ClientProxy.mc.fontRenderer.drawStringWithShadow("DevilCraft.ru: NewDupe", 25, 6, Color.yellow.getRGB()); 
   GL11.glPopMatrix(); 
   } 
   }

   @SideOnly(Side.CLIENT)
   @ForgeSubscribe
   public void PlayerInteractEventE(PlayerInteractEvent event) {
      if(event.entityPlayer == Minecraft.getMinecraft().thePlayer) {
         event.setCanceled(playerInteractFastClick(event.entityPlayer, event.action, event.entityPlayer.inventory.currentItem));
      }

   }

   @SideOnly(Side.CLIENT)
   public static int getInventorySlotContainItemAndDamage(EntityPlayer entityPlayer, int id, int damage) {
      for(int k = 0; k < 9; ++k) {
         if(entityPlayer.inventory.mainInventory[k] != null && entityPlayer.inventory.mainInventory[k].itemID == id && entityPlayer.inventory.mainInventory[k].getItemDamage() < damage && entityPlayer.inventory.mainInventory[k].getItemDamage() >= 0) {
            return k;
         }
      }

      return -1;
   }

   @SideOnly(Side.CLIENT)
   public static boolean playerInteractFastClick(EntityPlayer entityPlayer, Action action, int slot) {
      if(slot < 9 && slot >= 0) {
         ItemStack it = entityPlayer.inventory.mainInventory[slot];
         if(it != null && it.getItem() instanceof IFastRightClickItem) {
            if(action == Action.RIGHT_CLICK_AIR) {
               PacketDispatcher.sendPacketToServer((new PacketMAUpdateByte((byte)slot)).makePacket());
               ((IFastRightClickItem)it.getItem()).FastRightClick(entityPlayer, it);
            }

            if(it.stackSize <= 0) {
               entityPlayer.inventory.mainInventory[slot] = null;
            }

            return true;
         }
      }

      return false;
   }

   @SideOnly(Side.CLIENT)
   @ForgeSubscribe
   public void loadingSounds(SoundLoadEvent event) {
      event.manager.soundPoolSounds.isGetRandomSound = false;
      String[] soundFiles = new String[]{"shock1.ogg", "shock2.ogg", "jacobs.ogg", "jacobs1.ogg", "friz.ogg", "shoot.ogg", "trow.ogg", "lokh.ogg", "spearsong.ogg", "spower.ogg", "suriken.ogg"};

      for(int i = 0; i < soundFiles.length; ++i) {
         try {
            File var5 = this.extractAndLoadResource(Minecraft.getMinecraft(), soundFiles[i]);
            event.manager.soundPoolSounds.addSound("tchestplate/" + soundFiles[i], var5);
            FMLCommonHandler.instance().getFMLLogger().log(Level.INFO, "[tchestplate] Loaded sound " + soundFiles[i]);
         } catch (Exception var51) {
            FMLCommonHandler.instance().getFMLLogger().log(Level.SEVERE, "[tchestplate] Could not load sound " + soundFiles[i], var51.toString());
         }
      }

      event.manager.soundPoolSounds.isGetRandomSound = true;
   }

   @SideOnly(Side.CLIENT)
   private File extractAndLoadResource(Minecraft mc, String resName) throws Exception {
      File resDestDir = new File(mc.mcDataDir, "/resources/tchestplate/sound/");
      if(!resDestDir.exists()) {
         resDestDir.mkdirs();
      }

      File resFile = new File(resDestDir, resName);
      if(!resFile.exists()) {
         InputStream streamIn = LavaChestPlate.class.getResourceAsStream("/tchestplate/sound/" + resName);
         BufferedOutputStream streamOut = new BufferedOutputStream(new FileOutputStream(resFile));
         byte[] buffer = new byte[1024];
         boolean var8 = false;

         int len1;
         while((len1 = streamIn.read(buffer)) >= 0) {
            streamOut.write(buffer, 0, len1);
         }

         streamIn.close();
         streamOut.close();
      }

      if(resFile.length() < 3L) {
         throw new IOException();
      } else {
         return resFile;
      }
   }

   @ForgeSubscribe
   public void onUpdateEntity(LivingUpdateEvent event) {
      if(event.entity instanceof EntityPlayerMP && !event.entity.worldObj.isRemote) {
         boolean flag = false;
         ExtendedPlayer Epl = ExtendedPlayer.get((EntityPlayer)event.entity);
         EntityPlayerMP pl = (EntityPlayerMP)event.entity;

         try {
            if(Epl.inventoryExt != null && pl.ticksExisted % 200 == 0) {
               for(int var71 = 0; var71 < Epl.inventoryExt.getSizeInventory(); ++var71) {
                  ItemStack var10 = Epl.inventoryExt.inventory[var71];
                  if(var10 != null) {
                     PacketDispatcher.sendPacketToAllPlayers((new PacketMASendItemStackInSlot(pl.entityId, (byte)1, (byte)var71, new Object[]{var10})).makePacket());
                  }
               }
            }
         } catch (Exception var7) {
            var7.printStackTrace();
         }
      }

   }

   @ForgeSubscribe
   public void onEntityJoinWorld(EntityJoinWorldEvent event) {
      if(event.entity instanceof EntityPlayerMP && !event.entity.worldObj.isRemote) {
         boolean flag = false;
         final ExtendedPlayer Epl = ExtendedPlayer.get((EntityPlayer)event.entity);
         final EntityPlayerMP pl = (EntityPlayerMP)event.entity;
         pl.playerNetServerHandler.sendPacketToPlayer((new PacketMAUpdateInt((byte)1, Epl.inventoryExt.coin)).makePacket());
         (new Thread() {
            public void run() {
               try {
                  Thread.sleep(100L);
                  if(Epl.inventoryExt != null) {
                     for(int var31 = 0; var31 < Epl.inventoryExt.getSizeInventory(); ++var31) {
                        ItemStack var10 = Epl.inventoryExt.inventory[var31];
                        if(var10 != null) {
                           PacketDispatcher.sendPacketToAllPlayers((new PacketMASendItemStackInSlot(pl.entityId, (byte)1, (byte)var31, new Object[]{var10})).makePacket());
                        }
                     }
                  }
               } catch (Exception var3) {
                  var3.printStackTrace();
               }

               this.stop();
            }
         }).start();
         ArrayList var91 = Epl.activepotions;
         if(var91 != null && !var91.isEmpty()) {
            Iterator var101 = var91.iterator();

            while(var101.hasNext()) {
               FX_base s = (FX_base)var101.next();
               if(!s.UpdateServer(Epl, (EntityPlayer)event.entity)) {
                  var101.remove();
                  pl.playerNetServerHandler.sendPacketToPlayer((new PacketMARemoveFX(s.getIDeffect(), pl.entityId)).makePacket());
               } else {
                  int time = s.getMilliseconds();
                  if(time > 0) {
                     pl.playerNetServerHandler.sendPacketToPlayer((new PacketMAUpdateFX(s.getIDeffect(), pl.entityId, time, (byte)0)).makePacket());
                  } else {
                     pl.playerNetServerHandler.sendPacketToPlayer((new PacketMARemoveFX(s.getIDeffect(), pl.entityId)).makePacket());
                  }
               }
            }
         }
      }

   }

   @ForgeSubscribe
   public void onEntityConstructing(EntityConstructing event) {
      if(event.entity instanceof EntityPlayer && ExtendedPlayer.get((EntityPlayer)event.entity) == null) {
         ExtendedPlayer.register((EntityPlayer)event.entity);
      }

      if(event.entity instanceof EntityPlayer && event.entity.getExtendedProperties("epa") == null) {
         event.entity.registerExtendedProperties("epa", new ExtendedPlayer((EntityPlayer)event.entity));
      }

   }

   public Integer getCDCharge(ItemStack armor) {
      if(armor.stackSize > 1) {
         return null;
      } else {
         NBTTagCompound nbtData = Utils.getOrCreateNbtData(armor);
         return Integer.valueOf(nbtData.getByte("cd"));
      }
   }

   @ForgeSubscribe
   public void onDamagePlayer(LivingAttackEvent event) {
      EntityPlayer defender = null;
      EntityPlayer attacker = null;
      if(event.entity instanceof EntityPlayer) {
         defender = (EntityPlayer)event.entity;
      }

      if(event.source.getEntity() instanceof EntityPlayer) {
         attacker = (EntityPlayer)event.source.getEntity();
      }

      if(attacker != null && defender != null) {
         double chance = 0.0D;
         int count;
         if(defender.inventory.armorInventory[0] != null && defender.inventory.armorInventory[0].getItem() instanceof LavaChestPlateItem && this.getCDCharge(defender.inventory.armorInventory[0]).intValue() > 0) {
            for(count = 0; count < this.getCDCharge(defender.inventory.armorInventory[0]).intValue(); ++count) {
               chance += 0.08D;
            }
         }

         if(defender.inventory.armorInventory[1] != null && defender.inventory.armorInventory[1].getItem() instanceof LavaChestPlateItem && this.getCDCharge(defender.inventory.armorInventory[1]).intValue() > 0) {
            for(count = 0; count < this.getCDCharge(defender.inventory.armorInventory[1]).intValue(); ++count) {
               chance += 0.08D;
            }
         }

         if(defender.inventory.armorInventory[2] != null && defender.inventory.armorInventory[2].getItem() instanceof LavaChestPlateItem && this.getCDCharge(defender.inventory.armorInventory[2]).intValue() > 0) {
            for(count = 0; count < this.getCDCharge(defender.inventory.armorInventory[2]).intValue(); ++count) {
               chance += 0.08D;
            }
         }

         if(defender.inventory.armorInventory[3] != null && defender.inventory.armorInventory[3].getItem() instanceof LavaChestPlateItem && this.getCDCharge(defender.inventory.armorInventory[3]).intValue() > 0) {
            for(count = 0; count < this.getCDCharge(defender.inventory.armorInventory[3]).intValue(); ++count) {
               chance += 0.08D;
            }
         }

         if(chance > 0.0D && Math.random() < chance / 100.0D) {
            attacker.worldObj.playSoundEffect(attacker.posX, attacker.posY, attacker.posZ, "tchestplate.shock1", 2.0F, 1.1F);
            LavaChestPlate.proxy.sendCustomPacketToAllNear((new PacketMAparticle(attacker.entityId, defender.entityId, 2)).makePacket(), 64.0D, defender);
            defender.addChatMessage("[Server] Вы нанесли смертельный удар.");
            attacker.addChatMessage("[Server] По вам прошел смертельный удар.");
            if(attacker.inventory.armorInventory[0] != null && attacker.inventory.armorInventory[0].getItem() instanceof LavaChestPlateItem) {
               this.discharge(attacker.inventory.armorInventory[0], this.getCharge(attacker.inventory.armorInventory[0]).intValue() / 100 * 30);
            }

            if(attacker.inventory.armorInventory[1] != null && attacker.inventory.armorInventory[1].getItem() instanceof LavaChestPlateItem) {
               this.discharge(attacker.inventory.armorInventory[1], this.getCharge(attacker.inventory.armorInventory[1]).intValue() / 100 * 30);
            }

            if(attacker.inventory.armorInventory[2] != null && attacker.inventory.armorInventory[2].getItem() instanceof LavaChestPlateItem) {
               this.discharge(attacker.inventory.armorInventory[2], this.getCharge(attacker.inventory.armorInventory[2]).intValue() / 100 * 30);
            }

            if(attacker.inventory.armorInventory[3] != null && attacker.inventory.armorInventory[3].getItem() instanceof LavaChestPlateItem) {
               this.discharge(attacker.inventory.armorInventory[3], this.getCharge(attacker.inventory.armorInventory[3]).intValue() / 100 * 30);
            }
         }
      }

   }

   public Integer getCharge(ItemStack armor) {
      LavaChestPlateItem item = (LavaChestPlateItem)armor.getItem();
      if(armor.stackSize > 1) {
         return Integer.valueOf(0);
      } else {
         NBTTagCompound nbtData = Utils.getOrCreateNbtData(armor);
         int charge = nbtData.getInteger("charge");
         int maxCharge = item.getMaxCharge(nbtData).intValue();
         if(charge > maxCharge) {
            charge = maxCharge;
         }

         return Integer.valueOf(charge);
      }
   }

   public long discharge(ItemStack armor, int value) {
      LavaChestPlateItem item = (LavaChestPlateItem)armor.getItem();
      if(armor.stackSize > 1) {
         return 0L;
      } else {
         NBTTagCompound nbtData = Utils.getOrCreateNbtData(armor);
         int charge = nbtData.getInteger("charge");
         int maxCharge = item.getMaxCharge(nbtData).intValue();
         int temp_charge = charge;
         if(charge <= 0) {
            return 0L;
         } else {
            if(charge > maxCharge) {
               charge = maxCharge;
            }

            if(charge - value >= 0) {
               charge -= value;
            } else {
               charge = 0;
            }

            nbtData.setInteger("charge", charge);
            temp_charge = value - temp_charge;
            return temp_charge > 0?(long)charge << 32 | (long)temp_charge & 4294967295L:(long)charge << 32;
         }
      }
   }

   @ForgeSubscribe
   public void DamageEvent(LivingHurtEvent event) {
      if(event.entity instanceof EntityPlayer) {
         int damage_ = event.ammount;
         ExtendedPlayer Exp = ExtendedPlayer.get((EntityPlayer)event.entity);
         if(event.source.getEntity() instanceof EntityPlayer) {
            ExtendedPlayer var20 = ExtendedPlayer.get((EntityPlayer)event.source.getEntity());
            ItemStack var23 = var20.inventoryExt.getStackInSlot(3);
            ItemStack var25 = var20.inventoryExt.getStackInSlot(1);
            if(var23 != null && var23.getItem() instanceof ItemAmulets) {
               damage_ = ((ItemAmulets)var23.getItem()).getCalculateAttackDamage(var23, damage_);
            }

            if(var25 != null && var25.getItem() instanceof ItemRunes) {
               damage_ = ((ItemRunes)var25.getItem()).getCalculateAttackDamage(var25, damage_);
            }
         }

         ItemStack var201 = Exp.inventoryExt.getStackInSlot(5);
         if(var201 != null && var201.getItem() instanceof ItemShields) {
            damage_ = ((ItemShields)var201.getItem()).getCalculateProcDamage(var201, damage_);
         }

         var201 = Exp.inventoryExt.getStackInSlot(2);
         if(var201 != null) {
            byte var21 = Utils.getOrCreateNbtData(var201).getByte("plvl");
         }

         var201 = Exp.inventoryExt.getStackInSlot(3);
         if(var201 != null && var201.getItem() instanceof ItemAmulets) {
            damage_ = ((ItemAmulets)var201.getItem()).getCalculateProcDamage(var201, damage_);
         }

         var201 = Exp.inventoryExt.getStackInSlot(4);
         if(var201 != null && var201.getItem() instanceof ItemBracelets) {
            damage_ = ((ItemBracelets)var201.getItem()).getCalculateProcDamage(var201, damage_, (EntityPlayer)event.entity, event.source.getEntity());
         }

         var201 = Exp.inventoryExt.getStackInSlot(1);
         if(var201 != null && var201.getItem() instanceof ItemRunes) {
            damage_ = ((ItemRunes)var201.getItem()).getDamage(var201, damage_, (EntityPlayer)event.entity, event.source.getEntity());
         }

         var201 = Exp.inventoryExt.getStackInSlot(1);
         if(var201 != null && var201.getItem() instanceof ItemRunes) {
            damage_ = ((ItemRunes)var201.getItem()).getCalculateProcDamage(var201, damage_);
         }

         var201 = Exp.inventoryExt.getStackInSlot(1);
         if(var201 != null && var201.getItem() instanceof ItemRunes) {
            if((var201.getItemDamage() == 5 || var201.getItemDamage() == 2) && Exp.player.isBurning()) {
               Exp.player.extinguish();
            }

            if(var201.getItemDamage() == 5) {
               var201 = Exp.player.inventory.armorInventory[0];
               if(var201.getItem() instanceof LavaChestPlateItem) {
                  NBTTagCompound var22 = var201.getTagCompound();
                  int var24 = var22.getInteger("charge");
                  var201 = Exp.player.inventory.armorInventory[0];
                  if(var201.getItem().equals(Boolean.valueOf(var22.getInteger("charge") < 1000))) {
                     ;
                  }

                  var201 = Exp.inventoryExt.getStackInSlot(1);
                  if(var201.getItemDamage() == 5 && ((ItemRunes)var201.getItem()).isAlived(var201)) {
                     var201 = Exp.player.inventory.armorInventory[0];
                     NBTTagCompound var26 = var201.getTagCompound();
                     var201.setTagCompound(var26);
                     var26.setInteger("charge", var22.getInteger("charge") + var22.getInteger("charge") * 20 / 100);
                  }
               }
            }
         }

         if(damage_ <= 0) {
            event.ammount = 0;
         }

         int var231 = 0;
         EntityPlayer var251 = (EntityPlayer)event.entity;
         ItemStack[] var261 = var251.inventory.armorInventory;

         for(int var27 = 0; var27 < var261.length; ++var27) {
            ItemStack var281 = var261[var27];
            if(var281 != null && var281.getItem() instanceof ILavaItem) {
               ++var231;
            }
         }

         long[] var271 = new long[var261.length];
         if(damage_ > 0 && var231 > 0) {
            int var28 = damage_ / var231;
            damage_ = 0;
            int acomulate_charge = 0;

            int slot;
            for(int var291 = 0; var291 < var261.length; ++var291) {
               ItemStack var301 = var261[var291];
               if(var301 != null && var301.getItem() instanceof ILavaItem) {
                  long var31;
                  ItemStack iu;
                  if(damage_ > 0) {
                     var31 = ((ILavaItem)var301.getItem()).getShildDamage(var251, var301, var28 + damage_, var291);
                     if(var301.stackSize <= 0) {
                        iu = var261[var291];
                        var261[var291] = null;
                        MinecraftForge.EVENT_BUS.post(new PlayerDestroyItemEvent(var251, iu));
                     }

                     damage_ = (int)var31;
                     slot = (int)(var31 >> 32);
                     acomulate_charge += slot;
                  } else {
                     var31 = ((ILavaItem)var301.getItem()).getShildDamage(var251, var301, var28, var291);
                     if(var301.stackSize <= 0) {
                        iu = var261[var291];
                        var261[var291] = null;
                        MinecraftForge.EVENT_BUS.post(new PlayerDestroyItemEvent(var251, iu));
                     }

                     slot = (int)(var31 >> 32);
                     acomulate_charge += slot;
                     var271[var291] = (long)slot << 32 | (long)var291 & 4294967295L;
                     damage_ += (int)var31;
                  }
               }
            }

            if(acomulate_charge > 0) {
               Arrays.sort(var271);
               long[] var29 = var271;
               int var30 = var271.length;
               int var311 = 0;

               while(true) {
                  if(var311 >= var30) {
                     Object var32 = null;
                     break;
                  }

                  long i = var29[var311];
                  slot = (int)i;
                  int charge = (int)(i >> 32);
                  if(slot != 0 || charge != 0) {
                     ItemStack stack = var261[slot];
                     if(stack != null && stack.getItem() instanceof ILavaItem && damage_ > 0) {
                        damage_ = (int)((ILavaItem)stack.getItem()).getShildDamage(var251, stack, damage_, slot);
                        if(stack.stackSize <= 0) {
                           ItemStack orig = var261[slot];
                           var261[slot] = null;
                           MinecraftForge.EVENT_BUS.post(new PlayerDestroyItemEvent(var251, orig));
                        }
                     }
                  }

                  ++var311;
               }
            }

            if(damage_ > 0) {
               event.ammount = damage_;
            } else {
               event.ammount = 0;
            }
         }
      }

   }

   	   @SideOnly(Side.CLIENT)
	   @ForgeSubscribe
	   public void OverlayEvent7(RenderGameOverlayEvent.Post event)
	   {
		 ExtendedPlayer player = ExtendedPlayer.get(ClientProxy.mc.thePlayer);
	   	 ItemStack localItemStack2;
	   	 if (((localItemStack2 = ClientProxy.mc.thePlayer.inventory.getCurrentItem()) != null) && 
	   	 (localItemStack2.getItem().itemID == LavaChestPlate.demon.itemID))
	     {
	   		Integer str21 = Demonic.maxCharge;	   		  		
	       int wigth = ClientProxy.mc.fontRenderer.getStringWidth("" + str21);
	       ScaledResolution scaledresolution = new ScaledResolution(ClientProxy.mc.gameSettings, ClientProxy.mc.displayWidth, ClientProxy.mc.displayHeight);
	       int k = scaledresolution.getScaledWidth();
	       int l = scaledresolution.getScaledHeight();
	       GL11.glPushMatrix();
	       int x = event.resolution.getScaledWidth() / 2;
	       int y = event.resolution.getScaledHeight() / 2;
	       ClientProxy.mc.fontRenderer.drawStringWithShadow("" + str21, k - wigth - 28, l - 139, 10092288);
	       Utils.renderItemStack(ClientProxy.mc.fontRenderer, new ItemStack(LavaChestPlate.demon, 1, 8), k - 24, l - 144);
	       GL11.glDisable(3042);
	       RenderHelper.disableStandardItemLighting();
	       GL11.glPopMatrix();
	     }
	   }

   @SideOnly(Side.CLIENT)
   @ForgeSubscribe
   public void OverlayEvent(Post event) {
      if(event.type == ElementType.HOTBAR) {
         String coin = String.valueOf(ExtendedPlayer.get(ClientProxy.mc.thePlayer).inventoryExt.coin);
         int wigth = ClientProxy.mc.fontRenderer.getStringWidth(coin);
         ScaledResolution scaledresolution = new ScaledResolution(ClientProxy.mc.gameSettings, ClientProxy.mc.displayWidth, ClientProxy.mc.displayHeight);
         int k = scaledresolution.getScaledWidth();
         int l = scaledresolution.getScaledHeight();
         GL11.glPushMatrix();
         int x = event.resolution.getScaledWidth() / 2;
         int y = event.resolution.getScaledHeight() / 2;
         ClientProxy.mc.fontRenderer.drawStringWithShadow(coin, k - wigth - 28, l - 15, 10092288);
         RenderHelper.enableStandardItemLighting();
         Utils.renderItemStack(ClientProxy.mc.fontRenderer, DonateGui.itemCoupon, k - 24, l - 19);
         RenderHelper.disableStandardItemLighting();
         GL11.glPopMatrix();
      }

   }

   @SideOnly(Side.CLIENT)
   @ForgeSubscribe
   public void OverlayEvent2(Post event) {
      if(event.type == ElementType.HOTBAR && Minecraft.getMinecraft().thePlayer.inventory.armorInventory[3] != null && Minecraft.getMinecraft().thePlayer.inventory.armorInventory[3].itemID == LavaChestPlate.lavaPredatorPlateHelmet.itemID) {
         int coin = LavaChestPlateItem.getChargeInvis(Minecraft.getMinecraft().thePlayer.inventory.armorInventory[3]).intValue();
         int wigth = ClientProxy.mc.fontRenderer.getStringWidth("" + coin);
         ScaledResolution scaledresolution = new ScaledResolution(ClientProxy.mc.gameSettings, ClientProxy.mc.displayWidth, ClientProxy.mc.displayHeight);
         int k = scaledresolution.getScaledWidth();
         int l = scaledresolution.getScaledHeight();
         GL11.glPushMatrix();
         int x = event.resolution.getScaledWidth() / 2;
         int y = event.resolution.getScaledHeight() / 2;
         ClientProxy.mc.fontRenderer.drawStringWithShadow("" + coin, k - wigth - 28, l - 105, coin <= 50?Color.red.getRGB():10092288);
         RenderHelper.enableStandardItemLighting();
         GL11.glEnable(3042);
         GL11.glBlendFunc(1, 1);
         Utils.renderItemStack(ClientProxy.mc.fontRenderer, new ItemStack(LavaChestPlate.lavaPredatorPlateHelmet), k - 24, l - 110);
         GL11.glDisable(3042);
         RenderHelper.disableStandardItemLighting();
         GL11.glPopMatrix();
      }

   }

   public Integer getKDCharge(ItemStack armor) {
      if(armor.stackSize > 1) {
         return null;
      } else {
         NBTTagCompound nbtData = Utils.getOrCreateNbtData(armor);
         return Integer.valueOf(nbtData.getByte("kdi"));
      }
   }

   @SideOnly(Side.CLIENT)
   private ModelBiped getmodelArmor(RenderPlayer paramRenderPlayer) {
      return (ModelBiped)ObfuscationReflectionHelper.getPrivateValue(RenderPlayer.class, paramRenderPlayer, this.getModelArmor);
   }

   @SideOnly(Side.CLIENT)
   private ModelBiped getmodelArmorChestplate(RenderPlayer paramRenderPlayer) {
      return (ModelBiped)ObfuscationReflectionHelper.getPrivateValue(RenderPlayer.class, paramRenderPlayer, this.getModelArmorChestplate);
   }

   @SideOnly(Side.CLIENT)
   private ModelBiped getmainModel(RenderPlayer paramRenderPlayer) {
      return (ModelBiped)ObfuscationReflectionHelper.getPrivateValue(RenderLiving.class, paramRenderPlayer, this.getMainModel);
   }

   @SideOnly(Side.CLIENT)
 @ForgeSubscribe
 public void OverlayEvent3(Post event) {
    if(event.type == ElementType.HOTBAR && Minecraft.getMinecraft().thePlayer.inventory.armorInventory[3] != null && Minecraft.getMinecraft().thePlayer.inventory.armorInventory[3].getTagCompound() != null && this.getKDCharge(Minecraft.getMinecraft().thePlayer.inventory.armorInventory[3]).intValue() > 0) {
       for(int i = 0; i < 9; ++i) {
          if(Minecraft.getMinecraft().thePlayer.inventory.mainInventory[i] != null && Minecraft.getMinecraft().thePlayer.inventory.mainInventory[i].getTagCompound() != null && !(Minecraft.getMinecraft().thePlayer.inventory.mainInventory[i].getItem() instanceof ItemArmor)) {
             int coin = 0;
             int coin2 = 0;
             if(Minecraft.getMinecraft().thePlayer.inventory.mainInventory[i].getItem() instanceof BaseEnergyItem) {
                coin = ((BaseEnergyItem)Minecraft.getMinecraft().thePlayer.inventory.mainInventory[i].getItem()).getCharge(Minecraft.getMinecraft().thePlayer.inventory.mainInventory[i]).intValue();
             } else if(Minecraft.getMinecraft().thePlayer.inventory.mainInventory[i].getItem() instanceof BaseEnergyOilItem) {
                coin2 = ((BaseEnergyOilItem)Minecraft.getMinecraft().thePlayer.inventory.mainInventory[i].getItem()).getCharge(Minecraft.getMinecraft().thePlayer.inventory.mainInventory[i]).intValue();
             }

             ScaledResolution scaledresolution = new ScaledResolution(ClientProxy.mc.gameSettings, ClientProxy.mc.displayWidth, ClientProxy.mc.displayHeight);
             int k = scaledresolution.getScaledWidth();
             int l = scaledresolution.getScaledHeight();
             int x2 = k / 2 - 82 + i * 20 + 2;
             int z3 = l - 11 - 3;
             if(Minecraft.getMinecraft().thePlayer.inventory.mainInventory[i].getItem() instanceof BaseEnergyItem) {
                if(coin >= 100) {
                   GL11.glPushMatrix();
                   float scale = 0.5F;
                   GL11.glScalef(scale, scale, scale);
                   (new Gui()).drawCenteredString(Minecraft.getMinecraft().fontRenderer, "" + coin, (int)((float)x2 / scale), (int)((float)z3 / scale) + 4, 10092288);
                   GL11.glPopMatrix();
                } else {
                   (new Gui()).drawCenteredString(Minecraft.getMinecraft().fontRenderer, "" + coin, x2, z3, 10092288);
                }
             } else if(Minecraft.getMinecraft().thePlayer.inventory.mainInventory[i].getItem() instanceof BaseEnergyOilItem) {
                (new Gui()).drawCenteredString(Minecraft.getMinecraft().fontRenderer, "" + coin2, x2, z3, 10092288);
             }
          }
       }
    }

 }

   @SideOnly(Side.CLIENT)
   @ForgeSubscribe
   public void renderPlayerEvent(SetArmorModel paramSetArmorModel) {
      EntityPlayer localEntityPlayer1 = paramSetArmorModel.entityPlayer;
      if(localEntityPlayer1 != null) {
         ItemStack localItemStack1;
         if(localEntityPlayer1.inventory != null && (localItemStack1 = localEntityPlayer1.inventory.getCurrentItem()) != null && localItemStack1.getItem().itemID == 8541) {
            ItemStack localObject1 = paramSetArmorModel.stack;
            int localItemStack2 = paramSetArmorModel.slot;
            if(localItemStack2 < 5) {
               Object localObject2 = localItemStack2 == 2?this.getmodelArmor(paramSetArmorModel.renderer):this.getmodelArmorChestplate(paramSetArmorModel.renderer);
               ((ModelBiped)localObject2).bipedHead.showModel = localItemStack2 == 0;
               ((ModelBiped)localObject2).bipedHeadwear.showModel = localItemStack2 == 0;
               ((ModelBiped)localObject2).bipedBody.showModel = localItemStack2 == 1 || localItemStack2 == 2;
               ((ModelBiped)localObject2).bipedRightArm.showModel = localItemStack2 == 1;
               ((ModelBiped)localObject2).bipedLeftArm.showModel = localItemStack2 == 1;
               ((ModelBiped)localObject2).bipedRightLeg.showModel = localItemStack2 == 2 || localItemStack2 == 3;
               ((ModelBiped)localObject2).bipedLeftLeg.showModel = localItemStack2 == 2 || localItemStack2 == 3;
               if(localItemStack2 == 0) {
                  localObject2 = (ModelBaseLavaArmor)ClientProxy.Models[79];
               } else if(localItemStack2 == 1) {
                  localObject2 = (ModelBaseLavaArmor)ClientProxy.Models[80];
               } else if(localItemStack2 == 2) {
                  localObject2 = (ModelBaseLavaArmor)ClientProxy.Models[81];
               } else if(localItemStack2 == 3) {
                  localObject2 = (ModelBaseLavaArmor)ClientProxy.Models[82];
               }

               if(localObject2 != null) {
                  RenderManager.instance.renderEngine.bindTexture(((ModelBaseLavaArmor)localObject2).getTexture());
                  ((ModelBiped)localObject2).bipedHeadwear.showModel = false;
                  ((ModelBiped)localObject2).bipedHead.showModel = localItemStack2 == 0;
                  ((ModelBiped)localObject2).bipedBody.showModel = localItemStack2 == 1 || localItemStack2 == 2;
                  ((ModelBiped)localObject2).bipedRightArm.showModel = localItemStack2 == 1;
                  ((ModelBiped)localObject2).bipedLeftArm.showModel = localItemStack2 == 1;
                  ((ModelBiped)localObject2).bipedRightLeg.showModel = localItemStack2 == 2 || localItemStack2 == 3;
                  ((ModelBiped)localObject2).bipedLeftLeg.showModel = localItemStack2 == 2 || localItemStack2 == 3;
                  ((ModelBiped)localObject2).isSneak = localEntityPlayer1.isSneaking();
                  ((ModelBase)localObject2).isRiding = localEntityPlayer1.isRiding();
                  ((ModelBase)localObject2).isChild = localEntityPlayer1.isChild();
                  ((ModelBiped)localObject2).heldItemRight = 0;
                  ((ModelBiped)localObject2).aimedBow = false;
                  ItemStack localObject31 = localEntityPlayer1.getCurrentItemOrArmor(0);
                  if(localObject31 != null) {
                     ((ModelBiped)localObject2).heldItemRight = 1;
                     if(localEntityPlayer1.getItemInUseCount() > 0) {
                        EnumAction localObject4 = ((ItemStack)localObject31).getItemUseAction();
                        if(localObject4 == EnumAction.bow) {
                           ((ModelBiped)localObject2).aimedBow = true;
                        } else if(localObject4 == EnumAction.block) {
                           ((ModelBiped)localObject2).heldItemRight = 3;
                        }
                     }
                  }

                  paramSetArmorModel.renderer.setRenderPassModel((ModelBase)localObject2);
                  if(localObject2 != null) {
                     ((ModelBase)localObject2).onGround = this.getmainModel(paramSetArmorModel.renderer).onGround;
                  }

                  if(localObject2 != null) {
                     ((ModelBase)localObject2).isRiding = this.getmainModel(paramSetArmorModel.renderer).isRiding;
                  }

                  if(localObject2 != null) {
                     ((ModelBase)localObject2).isChild = this.getmainModel(paramSetArmorModel.renderer).isChild;
                  }

                  paramSetArmorModel.result = 1;
                  return;
               }
            }
         }

         ExtendedPlayer localObject11 = ExtendedPlayer.get(localEntityPlayer1);
         ItemStack localItemStack21 = localObject11.inventoryExt.inventory[3];
         if(localItemStack21 == null) {
            paramSetArmorModel.result = -1;
         } else {
            if(localItemStack21.itemID == 2804 && localItemStack21.getItemDamage() == 8) {
               ItemStack localObject21 = paramSetArmorModel.stack;
               int j = paramSetArmorModel.slot;
               if(j < 5) {
                  Object localObject3 = j == 2?this.getmodelArmor(paramSetArmorModel.renderer):this.getmodelArmorChestplate(paramSetArmorModel.renderer);
                  ((ModelBiped)localObject3).bipedHead.showModel = j == 0;
                  ((ModelBiped)localObject3).bipedHeadwear.showModel = j == 0;
                  ((ModelBiped)localObject3).bipedBody.showModel = j == 1 || j == 2;
                  ((ModelBiped)localObject3).bipedRightArm.showModel = j == 1;
                  ((ModelBiped)localObject3).bipedLeftArm.showModel = j == 1;
                  ((ModelBiped)localObject3).bipedRightLeg.showModel = j == 2 || j == 3;
                  ((ModelBiped)localObject3).bipedLeftLeg.showModel = j == 2 || j == 3;
                  if(j == 0) {
                     localObject3 = (ModelBaseLavaArmor)ClientProxy.Models[63];
                  } else if(j == 1) {
                     localObject3 = (ModelBaseLavaArmor)ClientProxy.Models[59];
                  } else if(j == 2) {
                     localObject3 = (ModelBaseLavaArmor)ClientProxy.Models[61];
                  } else if(j == 3) {
                     localObject3 = (ModelBaseLavaArmor)ClientProxy.Models[62];
                  }

                  if(localObject3 != null) {
                     if(localObject11.PredatorMode == 50) {
                    	 Minecraft.getMinecraft().renderEngine.bindTexture("/mods/underqoder/Armors/Predator/predatorRBT2.png");
                    	  GL11.glEnable(3042);
                    	  GL11.glBlendFunc(1, 1);
                     } else {
                        ((ModelBaseLavaArmor)localObject3).color = LavaChestPlateItem.getColor(Utils.getOrCreateNbtData(localItemStack21));
                        if(((ModelBaseLavaArmor)localObject3).color > -1) {
                           if(((ModelBaseLavaArmor)localObject3).getTexture() != null) {
                              LavaChestPlateItem.loadTexture(((ModelBaseLavaArmor)localObject3).getTexture(), 2.0F);
                           }
                        } else {
                           ClientProxy.mc.renderEngine.bindTexture(((ModelBaseLavaArmor)localObject3).getTexture());
                        }
                     }

                     ((ModelBiped)localObject3).bipedHeadwear.showModel = false;
                     ((ModelBiped)localObject3).bipedHead.showModel = j == 0;
                     ((ModelBiped)localObject3).bipedBody.showModel = j == 1 || j == 2;
                     ((ModelBiped)localObject3).bipedRightArm.showModel = j == 1;
                     ((ModelBiped)localObject3).bipedLeftArm.showModel = j == 1;
                     ((ModelBiped)localObject3).bipedRightLeg.showModel = j == 2 || j == 3;
                     ((ModelBiped)localObject3).bipedLeftLeg.showModel = j == 2 || j == 3;
                     ((ModelBiped)localObject3).isSneak = localEntityPlayer1.isSneaking();
                     ((ModelBase)localObject3).isRiding = localEntityPlayer1.isRiding();
                     ((ModelBase)localObject3).isChild = localEntityPlayer1.isChild();
                     ((ModelBiped)localObject3).heldItemRight = 0;
                     ((ModelBiped)localObject3).aimedBow = false;
                     ItemStack localItemStack3 = ((EntityPlayer)localEntityPlayer1).getCurrentItemOrArmor(0);
                     if(localItemStack3 != null) {
                        ((ModelBiped)localObject3).heldItemRight = 1;
                        if(((EntityPlayer)localEntityPlayer1).getItemInUseCount() > 0) {
                           EnumAction localEnumAction = localItemStack3.getItemUseAction();
                           if(localEnumAction == EnumAction.bow) {
                              ((ModelBiped)localObject3).aimedBow = true;
                           } else if(localEnumAction == EnumAction.block) {
                              ((ModelBiped)localObject3).heldItemRight = 3;
                           }
                        }
                     }
                  }

                  paramSetArmorModel.renderer.setRenderPassModel((ModelBase)localObject3);
                  if(localObject3 != null) {
                     ((ModelBase)localObject3).onGround = this.getmainModel(paramSetArmorModel.renderer).onGround;
                  }

                  if(localObject3 != null) {
                     ((ModelBase)localObject3).isRiding = this.getmainModel(paramSetArmorModel.renderer).isRiding;
                  }

                  if(localObject3 != null) {
                     ((ModelBase)localObject3).isChild = this.getmainModel(paramSetArmorModel.renderer).isChild;
                  }

                  paramSetArmorModel.result = localItemStack21.isItemEnchanted()?15:1;
                  return;
               }
            }

            paramSetArmorModel.result = -1;
         }
      }
   }

   	   @SideOnly(Side.CLIENT)
	   @ForgeSubscribe
	   public void OverlayEvent4(Post event) {
	      ExtendedPlayer player = ExtendedPlayer.get(ClientProxy.mc.thePlayer);
	      if(event.type == ElementType.HOTBAR && player.inventoryExt.inventory[3] != null && player.inventoryExt.inventory[3].getItemDamage() == 8 && player.inventoryExt.inventory[3].stackTagCompound != null) {
	         int coin = player.inventoryExt.inventory[3].stackTagCompound.getInteger("openclade");
	         int wigth = ClientProxy.mc.fontRenderer.getStringWidth("" + coin);
	         ScaledResolution scaledresolution = new ScaledResolution(ClientProxy.mc.gameSettings, ClientProxy.mc.displayWidth, ClientProxy.mc.displayHeight);
	         int k = scaledresolution.getScaledWidth();
	         int l = scaledresolution.getScaledHeight();
	         GL11.glPushMatrix();
	         int x = event.resolution.getScaledWidth() / 2;
	         int y = event.resolution.getScaledHeight() / 2;
	         ClientProxy.mc.fontRenderer.drawStringWithShadow("" + coin, k - wigth - 28, l - 123, coin <= 69?Color.red.getRGB():10092288);
	         Utils.renderItemStack(ClientProxy.mc.fontRenderer, new ItemStack(LavaChestPlate.itemAmulets, 1, 8), k - 24, l - 128);
	         GL11.glDisable(3042);
	         RenderHelper.disableStandardItemLighting();
	         GL11.glPopMatrix();
	      }

	   }
}
