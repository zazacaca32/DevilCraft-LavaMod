package net.minecraft.client.addon.tchestplate;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.ReflectionHelper;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.addon.tchestplate.EventHandler;
import net.minecraft.client.addon.tchestplate.IUpdateItemExtPlayer;
import net.minecraft.client.addon.tchestplate.LavaChestPlate;
import net.minecraft.client.addon.tchestplate.MoveF;
import net.minecraft.client.addon.tchestplate.RayTrace;
import net.minecraft.client.addon.tchestplate.Utils;
import net.minecraft.client.addon.tchestplate.armors.items.LavaChestPlateChest;
import net.minecraft.client.addon.tchestplate.armors.items.LavaChestPlateHelmet;
import net.minecraft.client.addon.tchestplate.fx.B1;
import net.minecraft.client.addon.tchestplate.fx.manager.FX_base;
import net.minecraft.client.addon.tchestplate.overlaygui.InventoryTabGui;
import net.minecraft.client.addon.tchestplate.packets.PacketInvisible;
import net.minecraft.client.addon.tchestplate.player.ExtendedPlayer;
import net.minecraft.client.addon.tchestplate.util.KeyboardClient;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.src.ModLoader;
import net.minecraft.util.MovementInput;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;
import org.lwjgl.input.Keyboard;

public class ClientTickHandler implements ITickHandler {

   private boolean keyDown;
   public static Minecraft mc = ModLoader.getMinecraftInstance();
   public static boolean isFlyActiveByMod = false;
   public static boolean NightKeyKey = false;
   public static boolean isInvis = false;
   public static boolean Pantera = false;
   public static boolean isFirstLoad = false;
   public static boolean isLastUndressed = false;
   public static boolean isLastCreativeState = false;
   private static boolean isPressUse = false;
   public static double x;
   public static double y;
   public static double z;
   public static boolean fx = false;
   final String[] getGuiLeft = new String[]{"field_74198_m", "guiLeft"};
   final String[] getGuiTop = new String[]{"field_74197_n", "guiTop"};
   final String[] mapFieldsGuiScreen = ObfuscationReflectionHelper.remapFieldNames(GuiScreen.class.getSimpleName(), new String[]{"buttonList", "k"});
   public static MovementInput mmm = new MoveF(Minecraft.getMinecraft().gameSettings);
   public static MovementInput mmmTemp;
   private boolean isInitFly = false;
   public static long timer = 0L;
   private boolean[] keyStates = new boolean[256];
   public static int swinglance;
   String[] isImmuneToFire = new String[]{"field_70178_ae", "isImmuneToFire"};
   public static float jumpChargeMap = 0.0F;
   public static int i = 300;


   public boolean checkKey(int i) {
      return Minecraft.getMinecraft().currentScreen != null?false:(Keyboard.isKeyDown(i) != this.keyStates[i]?(this.keyStates[i] = !this.keyStates[i]):false);
   }

   private int getGuiLeft(GuiInventory clazz) {
      return ((Integer)ObfuscationReflectionHelper.getPrivateValue(GuiContainer.class, clazz, this.getGuiLeft)).intValue();
   }

   private int getGuiTop(GuiInventory clazz) {
      return ((Integer)ObfuscationReflectionHelper.getPrivateValue(GuiContainer.class, clazz, this.getGuiTop)).intValue();
   }

   public void tickUseItem(EntityPlayer player) {}

   public void tickStart(EnumSet var1, Object ... var2) {
      int var3;
      if(var1.contains(TickType.RENDER)) {
         try {
            GuiScreen var241 = Minecraft.getMinecraft().currentScreen;
            if(var241 != null && var241 instanceof GuiInventory) {
               Field var251 = ReflectionHelper.findField(GuiScreen.class, (String[])ObfuscationReflectionHelper.remapFieldNames(GuiScreen.class.getName(), (String[])(new String[]{"buttonList", "k", "field_73887_h"})));
               var251.setAccessible(true);
               List var261 = (List)var251.get(var241);
               boolean var281 = true;
               Iterator var301 = var261.iterator();

               while(var301.hasNext()) {
                  Object var321 = var301.next();
                  if(var321 instanceof InventoryTabGui) {
                     var281 = false;
                     break;
                  }
               }

               if(var281) {
                  var3 = this.getGuiLeft((GuiInventory)var241);
                  int var31 = this.getGuiTop((GuiInventory)var241);
                  InventoryTabGui var331 = new InventoryTabGui(2000, var3 - 28, var31, new ItemStack(Block.workbench), 0);
                  var331.enabled = false;
                  var261.add(var331);
                  var331 = new InventoryTabGui(3000, var3 - 28, var31 + 27, new ItemStack(Item.plateDiamond), 1);
                  var261.add(var331);
               }
            }
         } catch (Exception var221) {
            var221.printStackTrace();
         }

         if(swinglance > 0) {
            --swinglance;
         }
      }

      if(var1.contains(TickType.CLIENT) && mc.theWorld != null && !isFirstLoad) {
         isFirstLoad = true;

         for(int var23 = 0; var23 < mc.gameSettings.keyBindings.length; ++var23) {
            if(mc.gameSettings.keyBindings[var23].keyDescription == "Boost Key") {
               KeyboardClient.icBoostKeyID = var23;
            }

            if(mc.gameSettings.keyBindings[var23].keyDescription == "ALT Key") {
               KeyboardClient.icAltKeyID = var23;
            }

            if(mc.gameSettings.keyBindings[var23].keyDescription == "Mode Switch Key") {
               KeyboardClient.icModeKeyID = var23;
            }

            if(mc.gameSettings.keyBindings[var23].keyDescription == "Gravi Fly Key") {
               KeyboardClient.GraviFlyKey = var23;
            }
         }
      }

      if(var1.contains(TickType.PLAYER)) {
         EntityPlayer var24 = (EntityPlayer)var2[0];
         if(var24 == null) {
            return;
         }

         ExtendedPlayer var25 = ExtendedPlayer.get(var24);
         ArrayList var26 = var25.activepotions;
         if(var26 != null && !var26.isEmpty()) {
            Iterator var27 = var26.iterator();

            while(var27.hasNext()) {
               FX_base var29 = (FX_base)var27.next();
               if(!var29.UpdateClient(var25, var24)) {
                  var27.remove();
               }
            }
         }

         var25.DecSwing();
         InventoryPlayer var28 = var24.inventory;

         for(var3 = 0; var3 < var28.mainInventory.length; ++var3) {
            if(var28.mainInventory[var3] != null && var28.mainInventory[var3].getItem() != null && var28.mainInventory[var3].getItem() instanceof IUpdateItemExtPlayer) {
               ((IUpdateItemExtPlayer)var28.mainInventory[var3].getItem()).onUpdate(var28.mainInventory[var3], var28.player.worldObj, var25, var3, var28.currentItem == var3, timer, IUpdateItemExtPlayer.SideExt.INV);
            }
         }

         for(var3 = 0; var3 < var28.armorInventory.length; ++var3) {
            if(var28.armorInventory[var3] != null && var28.armorInventory[var3].getItem() != null && var28.armorInventory[var3].getItem() instanceof IUpdateItemExtPlayer) {
               ((IUpdateItemExtPlayer)var28.armorInventory[var3].getItem()).onUpdate(var28.armorInventory[var3], var28.player.worldObj, var25, var3, var28.currentItem == var3, timer, IUpdateItemExtPlayer.SideExt.ARM);
            }
         }

         for(var3 = 0; var3 < var25.inventoryExt.inventory.length; ++var3) {
            if(var25.inventoryExt.inventory[var3] != null && var25.inventoryExt.inventory[var3].getItem() != null && var25.inventoryExt.inventory[var3].getItem() instanceof IUpdateItemExtPlayer) {
               ((IUpdateItemExtPlayer)var25.inventoryExt.inventory[var3].getItem()).onUpdate(var25.inventoryExt.inventory[var3], var28.player.worldObj, var25, var3, var28.currentItem == var3, timer, IUpdateItemExtPlayer.SideExt.EXT);
            }
         }

         this.tickUseItem(var24);
         if(var24 != mc.thePlayer) {
            return;
         }

         LavaChestPlate.keyboard.setisLavaPanterKeyUpKeyDown();
         ++timer;
         var25.dist();
         if(LavaChestPlate.keyboard.isKeyLavaPotionKeyDown() && timer % 14L == 0L && (var3 = EventHandler.getInventorySlotContainItemAndDamage(var24, LavaChestPlate.itemCP.itemID, 5)) > -1) {
            EventHandler.playerInteractFastClick(var24, Action.RIGHT_CLICK_AIR, var3);
         }

         ItemStack var30 = var24.inventory.armorInventory[2];
         ItemStack var32 = var24.inventory.armorInventory[3];
         if(!var24.isPotionActive(Potion.invisibility.id) && var24.isInvisible()) {
            isInvis = true;
         } else if(!var24.isPotionActive(Potion.invisibility.id) && !var24.isInvisible()) {
            isInvis = false;
         }

         EntityPlayer var33 = (EntityPlayer)var2[0];
         ExtendedPlayer p = ExtendedPlayer.get(var24);
         Vec3 vector = Vec3.createVectorHelper(var24.posX, var24.posY, var24.posZ);
         B1 b = new B1();
         boolean var22;
         if(p.inventoryExt.inventory[3] != null && mc.currentScreen == null && mc.inGameHasFocus && timer % 15L == 0L && p.inventoryExt.inventory[3].getItemDamage() == 8 && p.inventoryExt.inventory[3].stackTagCompound != null && this.checkKey(KeyboardClient.LavaPanter.keyCode)) {
            var22 = true;
            Vec3 vec3 = var33.getPosition(0.0F);
            Vec3 vec31 = var33.getLook(0.0F);
            Vec3 vec32 = vec3.addVector(vec31.xCoord * 7.0D, Math.max(0.0D, -vec31.yCoord), vec31.zCoord * 7.0D);
            MovingObjectPosition trace = RayTrace.traceBlinkPath(Minecraft.getMinecraft().theWorld, vec3, vec32, false, false, true);
            if(trace != null && trace.hitVec != null) {
               Vec3 look = var33.getLookVec();
               Vec3 rv = trace.hitVec.addVector(-look.xCoord, -look.yCoord, -look.zCoord);
               b.render(vector, var24.rotationYaw, (byte)2);
               var33.setPositionAndUpdate(rv.xCoord, rv.yCoord - 0.10000000149011612D, rv.zCoord);
            }
         }

         if(var24.inventory.armorInventory[0] != null && var24.inventory.armorInventory[1] != null && var24.inventory.armorInventory[2] != null && var24.inventory.armorInventory[3] != null) {
            if(var24.inventory.armorInventory[0].itemID == LavaChestPlate.lavaPredatorPlateBoots.itemID && var24.inventory.armorInventory[1].itemID == LavaChestPlate.lavaPredatorPlateLeggins.itemID && var24.inventory.armorInventory[2].itemID == LavaChestPlate.lavaPredatorPlateChest.itemID && var24.inventory.armorInventory[3].itemID == LavaChestPlate.lavaPredatorPlateHelmet.itemID && this.checkKey(KeyboardClient.Invisible.keyCode)) {
               isInvis = !isInvis;
               LavaChestPlate.proxy.setInvisible(var24, isInvis);
               PacketDispatcher.sendPacketToServer((new PacketInvisible(isInvis)).makePacket());
            }
         } else {
            isInvis = false;
            LavaChestPlate.proxy.setInvisible(var24, false);
            PacketDispatcher.sendPacketToServer((new PacketInvisible(false)).makePacket());
         }

         if(var32 != null && var32.getItem() instanceof LavaChestPlateHelmet) {
            if(LavaChestPlate.keyboard.isNightUpKeyDown()) {
               LavaChestPlate.keyboard.setisNightUpKeyDown();
               Minecraft.getMinecraft().gameSettings.gammaSetting = Minecraft.getMinecraft().gameSettings.gammaSetting < 1000.0F?1000.0F:1.0F;
            }
         } else if(Minecraft.getMinecraft().gameSettings.gammaSetting > 1.0F) {
            LavaChestPlate.keyboard.setisNightUpKeyDown();
            Minecraft.getMinecraft().gameSettings.gammaSetting = 1.0F;
         }

         var22 = checkFlyActiveByMode();
         if(var30 != null && var30.getItem() instanceof LavaChestPlateChest) {
            if(!var22 && !var24.capabilities.isFlying) {
               var24.capabilities.allowFlying = true;
               var24.capabilities.isFlying = true;
               isFlyActiveByMod = true;
            }
         } else if(var22) {
            var24.capabilities.allowFlying = false;
            var24.capabilities.isFlying = false;
            isFlyActiveByMod = false;
         }
      }

   }

   public static boolean checkNightKeyKeyByMode() {
      return NightKeyKey;
   }

   public static void setNightKeyKeyByMode(boolean vision) {
      NightKeyKey = vision;
   }

   public static boolean checkFlyActiveByMode() {
      return isFlyActiveByMod;
   }

   private void setImmuneToFire(Entity entity, boolean value) {
      if(entity.isImmuneToFire() != value) {
         ObfuscationReflectionHelper.setPrivateValue(Entity.class, entity, Boolean.valueOf(value), this.isImmuneToFire);
      }

   }

   public void tickEnd(EnumSet type, Object ... tickData) {
      if(type.contains(TickType.PLAYER)) {
         EntityPlayer player = (EntityPlayer)tickData[0];
         if(player == null) {
            return;
         }

         ItemStack bracelet = ExtendedPlayer.get(player).inventoryExt.inventory[4];
         if(bracelet != null && bracelet.getItemDamage() == 5) {
            player.extinguish();
            this.setImmuneToFire(player, true);
         } else {
            this.setImmuneToFire(player, false);
         }
      }

   }

   public EnumSet ticks() {
      return EnumSet.of(TickType.WORLDLOAD, TickType.CLIENT, TickType.PLAYER, TickType.RENDER);
   }

   public String getLabel() {
      return "tchestplate";
   }

   public static int getMaxChargePantera() {
      return 300;
   }

}
