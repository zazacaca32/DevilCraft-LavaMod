package net.minecraft.client.addon.tchestplate.armors.items;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.addon.tchestplate.ClientProxy;
import net.minecraft.client.addon.tchestplate.ILavaItem;
import net.minecraft.client.addon.tchestplate.LavaChestPlate;
import net.minecraft.client.addon.tchestplate.Utils;
import net.minecraft.client.addon.tchestplate.armors.models.ModelBaseLavaArmor;
import net.minecraft.client.addon.tchestplate.packets.PacketInvisible;
import net.minecraft.client.addon.tchestplate.player.ExtendedPlayer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.particle.EntityFlameFX;
import net.minecraft.client.renderer.RenderEngine;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;

public abstract class LavaChestPlateItem extends ItemArmor implements ISpecialArmor, ILavaItem {

public static final HashMap texture = new HashMap();
   protected double absorbation = 1.0D;
   private Integer maxCharge = Integer.valueOf(0);
   public Long tick = Long.valueOf(0L);
   int zLevel = -300;
   static long arrSlotTime = 0L;
   static int t = 0;
   int size = 1;


   public LavaChestPlateItem(int par1, EnumArmorMaterial par2EnumArmorMaterial, int par3, int par4, int maxCharge, double absorbation) {
      super(par1, par2EnumArmorMaterial, par3, par4);
      this.maxCharge = Integer.valueOf(maxCharge);
      this.setCreativeTab(LavaChestPlate.tabArmor);
      this.absorbation = absorbation;
      this.setMaxDamage(5000);
      this.setMaxStackSize(1);
   }

   public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List) {
      ItemStack j = new ItemStack(par1, 1, 0);
      this.charge(j, this.maxCharge.intValue());
      if(j.itemID == LavaChestPlate.lavaPredatorPlateBoots.itemID || j.itemID == LavaChestPlate.lavaPredatorPlateChest.itemID || j.itemID == LavaChestPlate.lavaChestPlateHelmet.itemID || j.itemID == LavaChestPlate.lavaPredatorPlateLeggins.itemID) {
         this.chargeInvis(j, 300);
      }

      par3List.add(j);
   }

   public abstract int getSlot();

   public void drawItemStack(ItemStack stack, int x, int y) {}

   @SideOnly(Side.CLIENT)
   public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List, boolean par4) {
      Minecraft mc = Minecraft.getMinecraft();
      String[] info = this.getInfo(par1ItemStack).split(":");
      NBTTagCompound r = par1ItemStack.stackTagCompound;
      if(!GuiScreen.isShiftKeyDown()) {
         par2List.add("§a" + info[0] + "L");
         par2List.add("§e Удерживайте шифт для просмотра модулей");
      } else {
         par2List.add("§a" + info[0] + "L");
      }

   }

   public void onArmorTickUpdate(World world, EntityPlayer player, ItemStack itemStack) {
      if(this.tick.longValue() % 40L == 0L) {
         this.charge(itemStack, 10);
      }

      if(player.inventory.armorInventory[0] != null && player.inventory.armorInventory[1] != null && player.inventory.armorInventory[2] != null && player.inventory.armorInventory[3] != null) {
         if(player.inventory.armorInventory[0].itemID == LavaChestPlate.lavaPredatorPlateBoots.itemID && player.inventory.armorInventory[1].itemID == LavaChestPlate.lavaPredatorPlateLeggins.itemID && player.inventory.armorInventory[2].itemID == LavaChestPlate.lavaPredatorPlateChest.itemID && player.inventory.armorInventory[3].itemID == LavaChestPlate.lavaPredatorPlateHelmet.itemID) {
            if(!player.isInvisible()) {
               if(this.tick.longValue() % 20L == 0L) {
                  this.chargeInvis(itemStack, 1);
               }

               if(player.isPotionActive(Potion.invisibility.id)) {
                  player.removePotionEffect(Potion.invisibility.id);
                  LavaChestPlate.proxy.setInvisible(player, false);
                  PacketDispatcher.sendPacketToPlayer((new PacketInvisible(false)).makePacket(), (Player)player);
               }
            }

            if(player.isInvisible()) {
               if(getChargeInvis(player.inventory.armorInventory[3]).intValue() <= 0) {
                  LavaChestPlate.proxy.setInvisible(player, false);
                  PacketDispatcher.sendPacketToPlayer((new PacketInvisible(false)).makePacket(), (Player)player);
               }

               if(player.isPotionActive(Potion.invisibility.id)) {
                  player.removePotionEffect(Potion.invisibility.id);
                  LavaChestPlate.proxy.setInvisible(player, true);
                  PacketDispatcher.sendPacketToPlayer((new PacketInvisible(true)).makePacket(), (Player)player);
               }

               if(this.tick.longValue() % 20L == 0L) {
                  this.dischargeInvis(itemStack, 1);
               }
            }
         } else if(!player.isPotionActive(Potion.invisibility.id) && player.isInvisible()) {
            player.removePotionEffect(Potion.invisibility.id);
            player.setInvisible(false);
         }
      } else if(!player.isPotionActive(Potion.invisibility.id) && player.isInvisible()) {
         player.removePotionEffect(Potion.invisibility.id);
         player.setInvisible(false);
      }

      Long var42 = this.tick;
      Long var52 = this.tick = Long.valueOf(this.tick.longValue() + 1L);
   }

   public ArmorProperties getProperties(EntityLiving player, ItemStack armor, DamageSource source, double damage, int slot) {
      return new ArmorProperties(0, 0.0D, 0);
   }
   
   public static BufferedImage newBrightness(BufferedImage paramBufferedImage, float paramFloat)
   {
     BufferedImage localBufferedImage = new BufferedImage(paramBufferedImage.getWidth(null), paramBufferedImage.getHeight(null), 2);
     
     int[] arrayOfInt = { 0, 0, 0, 0 };
     float[] arrayOfFloat = { 0.0F, 0.0F, 0.0F };
     
     localBufferedImage.getGraphics().drawImage(paramBufferedImage, 0, 0, null);
     for (int i = 0; i < localBufferedImage.getHeight(); i++) {
       for (int j = 0; j < localBufferedImage.getWidth(); j++)
       {
         localBufferedImage.getRaster().getPixel(j, i, arrayOfInt);
         
         Color.RGBtoHSB(arrayOfInt[0], arrayOfInt[1], arrayOfInt[2], arrayOfFloat);
         
         float f1 = arrayOfFloat[2] * paramFloat;
         if (f1 > 1.0F) {
           f1 = 1.0F;
         }
         float f2 = arrayOfFloat[1] / 2.0F;
         if (f2 > 1.0F) {
           f2 = 1.0F;
         }
         Color localColor = new Color(Color.HSBtoRGB(arrayOfFloat[0], f2, f1));
         
         localBufferedImage.getRaster().setPixel(j, i, new int[] { localColor.getRed(), localColor.getGreen(), localColor.getBlue(), arrayOfInt[3] });
       }
     }
     return localBufferedImage;
   }
   
   
   private static BufferedImage readTextureImage(InputStream paramInputStream)
		    throws IOException
		  {
		    BufferedImage localBufferedImage = ImageIO.read(paramInputStream);
		    paramInputStream.close();
		    return localBufferedImage;
		  }
   
   public static void loadTexture(String paramString, float paramFloat)
   {
     int i = 0;
     Integer localInteger = (Integer)texture.get(paramString + paramFloat);
     if (localInteger == null) {
       try
       {
         InputStream localInputStream = ClientProxy.mc.renderEngine.texturePack.getSelectedTexturePack().getResourceAsStream(paramString);
         if (localInputStream != null)
         {
           BufferedImage localBufferedImage1 = readTextureImage(localInputStream);
           
           BufferedImage localBufferedImage2 = newBrightness(localBufferedImage1, paramFloat);
           
           localInteger = Integer.valueOf(ClientProxy.mc.renderEngine.allocateAndSetupTexture(localBufferedImage2));
           applyChanges(paramString + paramFloat, localInteger);
           texture.put(paramString + paramFloat, localInteger);
         }
         else
         {
           texture.put(paramString + paramFloat, localInteger = Integer.valueOf(-1));
         }
       }
       catch (Exception localException)
       {
         localException.printStackTrace();
         texture.put(paramString + paramFloat, localInteger = Integer.valueOf(-1));
       }
     } else {
       ClientProxy.mc.renderEngine.bindTexture(paramString + paramFloat);
     }
   }
   
   public static final boolean applyChanges(String paramString, Integer paramInteger)
   {
     boolean bool = false;
     Field[] arrayOfField = RenderEngine.class.getDeclaredFields();
     int i = arrayOfField.length;
     for (int j = 0; j < i; j++)
     {
       Field localField1 = arrayOfField[j];
       try
       {
         if (((localField1.getName().contains("textureMap")) || (localField1.getName().contains("c")) || (localField1.getName().contains("field_78362_c"))) && (localField1.getType().isAssignableFrom(HashMap.class)))
         {
           Field localField2 = Field.class.getDeclaredField("modifiers");
           localField2.setAccessible(true);
           localField2.setInt(localField1, 1);
           HashMap localHashMap = (HashMap)localField1.get(ClientProxy.mc.renderEngine);
           
           localHashMap.put(paramString, paramInteger);
           localField1.set(ClientProxy.mc.renderEngine, localHashMap);
           bool = true;
           break;
         }
       }
       catch (Exception localException)
       {
         bool = false;
       }
     }
     return bool;
   }

   @SideOnly(Side.CLIENT)
   public void drawTexturedModalRect(int par1, int par2, int par3, int par4, int par5, int par6) {
      float f = 0.00390625F;
      float f1 = 0.00390625F;
      Tessellator tessellator = Tessellator.instance;
      tessellator.startDrawingQuads();
      tessellator.addVertexWithUV((double)(par1 + 0), (double)(par2 + par6), (double)this.zLevel, (double)((float)(par3 + 0) * f), (double)((float)(par4 + par6) * f1));
      tessellator.addVertexWithUV((double)(par1 + par5), (double)(par2 + par6), (double)this.zLevel, (double)((float)(par3 + par5) * f), (double)((float)(par4 + par6) * f1));
      tessellator.addVertexWithUV((double)(par1 + par5), (double)(par2 + 0), (double)this.zLevel, (double)((float)(par3 + par5) * f), (double)((float)(par4 + 0) * f1));
      tessellator.addVertexWithUV((double)(par1 + 0), (double)(par2 + 0), (double)this.zLevel, (double)((float)(par3 + 0) * f), (double)((float)(par4 + 0) * f1));
      tessellator.draw();
   }

   @SideOnly(Side.CLIENT)
   protected void renderInventorySlot(int x, int y, float par4, int slot) {
      ItemStack itemstack = ClientProxy.mc.thePlayer.inventory.armorItemInSlot(slot);
      if(itemstack != null) {
         float f1 = (float)itemstack.animationsToGo - par4;
         if(f1 > 0.0F) {
            GL11.glPushMatrix();
            float f2 = 1.0F + f1 / 5.0F;
            GL11.glTranslatef((float)(x + 8), (float)(y + 12), 0.0F);
            GL11.glScalef(1.0F / f2, (f2 + 1.0F) / 2.0F, 1.0F);
            GL11.glTranslatef((float)(-(x + 8)), (float)(-(y + 12)), 0.0F);
         }

         ClientProxy.itemRenderer.renderItemAndEffectIntoGUI(ClientProxy.mc.fontRenderer, ClientProxy.mc.renderEngine, itemstack, x, y);
         if(f1 > 0.0F) {
            GL11.glPopMatrix();
         }

         ClientProxy.itemRenderer.renderItemOverlayIntoGUI(ClientProxy.mc.fontRenderer, ClientProxy.mc.renderEngine, itemstack, x, y);
      }

   }

   @SideOnly(Side.CLIENT)
   public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot) {
      ScaledResolution scaledresolution = new ScaledResolution(ClientProxy.mc.gameSettings, ClientProxy.mc.displayWidth, ClientProxy.mc.displayHeight);
      int k = scaledresolution.getScaledWidth();
      int l = scaledresolution.getScaledHeight();
      RenderHelper.enableGUIStandardItemLighting();
      int y = l - 24 - 12;
      y -= 18 * slot;
      this.renderInventorySlot(k - 24, y, 0.0F, slot);
      RenderHelper.disableStandardItemLighting();
      int car = this.getCharge(armor).intValue();
      ClientProxy.mc.fontRenderer.drawStringWithShadow(String.valueOf(car), k - 52, y + 4, 10092288);
      long time = System.currentTimeMillis();
      if(arrSlotTime < time) {
         arrSlotTime = time + 300L;
         t = 0;
         ItemStack[] arr$ = player.inventory.armorInventory;
         int len$ = arr$.length;

         for(int i$ = 0; i$ < len$; ++i$) {
            ItemStack s = arr$[i$];
            if(s != null && s.getItem() instanceof LavaChestPlateItem) {
               t += this.getCharge(s).intValue();
            }
         }
      }

      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      ClientProxy.mc.renderEngine.bindTexture("/gui/icons.png");
      return 0;
   }

   @SideOnly(Side.CLIENT)
   public int getArmorDisplayInvis(EntityPlayer player, ItemStack armor, int slot) {
      return 0;
   }

   public void damageArmor(EntityLiving entity, ItemStack stack, DamageSource source, int damage, int slot) {}

   public Integer charge(ItemStack armor, int amount) {
      LavaChestPlateItem item = (LavaChestPlateItem)armor.getItem();
      if(amount >= 0 && armor.stackSize <= 1) {
         NBTTagCompound nbtData = Utils.getOrCreateNbtData(armor);
         int charge = nbtData.getInteger("charge");
         int maxCharge = item.getMaxCharge(nbtData).intValue();
         if(amount > maxCharge - charge) {
            amount = maxCharge - charge;
         }

         if(this.getLitres(Utils.getOrCreateNbtData(armor)).intValue() != 0 && this.getLitres(Utils.getOrCreateNbtData(armor)).intValue() >= 0) {
            charge += amount + 5 * this.getLitres(Utils.getOrCreateNbtData(armor)).intValue();
         } else if(this.getLitres1(Utils.getOrCreateNbtData(armor)).intValue() != 0 && this.getLitres1(Utils.getOrCreateNbtData(armor)).intValue() >= 0) {
             charge += amount + 5 * this.getLitres1(Utils.getOrCreateNbtData(armor)).intValue();
         } else if(this.getLitres2(Utils.getOrCreateNbtData(armor)).intValue() != 0 && this.getLitres2(Utils.getOrCreateNbtData(armor)).intValue() >= 0) {
             charge += amount + 5 * this.getLitres2(Utils.getOrCreateNbtData(armor)).intValue();
         } else if(this.getLitres3(Utils.getOrCreateNbtData(armor)).intValue() != 0 && this.getLitres3(Utils.getOrCreateNbtData(armor)).intValue() >= 0) {
             charge += amount + 5 * this.getLitres3(Utils.getOrCreateNbtData(armor)).intValue();
         } else if(this.getLitres4(Utils.getOrCreateNbtData(armor)).intValue() != 0 && this.getLitres4(Utils.getOrCreateNbtData(armor)).intValue() >= 0) {
             charge += amount + 5 * this.getLitres4(Utils.getOrCreateNbtData(armor)).intValue();
         } else if(this.getLitres5(Utils.getOrCreateNbtData(armor)).intValue() != 0 && this.getLitres5(Utils.getOrCreateNbtData(armor)).intValue() >= 0) {
             charge += amount + 5 * this.getLitres5(Utils.getOrCreateNbtData(armor)).intValue();
         } else if(this.getLitres6(Utils.getOrCreateNbtData(armor)).intValue() != 0 && this.getLitres6(Utils.getOrCreateNbtData(armor)).intValue() >= 0) {
             charge += amount + 5 * this.getLitres6(Utils.getOrCreateNbtData(armor)).intValue();
         } else if(this.getLitres7(Utils.getOrCreateNbtData(armor)).intValue() != 0 && this.getLitres7(Utils.getOrCreateNbtData(armor)).intValue() >= 0) {
             charge += amount + 5 * this.getLitres7(Utils.getOrCreateNbtData(armor)).intValue();
         } else if(this.getLitres7(Utils.getOrCreateNbtData(armor)).intValue() != 0 && this.getLitres8(Utils.getOrCreateNbtData(armor)).intValue() >= 0) {
             charge += amount + 5 * this.getLitres8(Utils.getOrCreateNbtData(armor)).intValue();
         } else if(this.getLitres7(Utils.getOrCreateNbtData(armor)).intValue() != 0 && this.getLitres9(Utils.getOrCreateNbtData(armor)).intValue() >= 0) {
             charge += amount + 5 * this.getLitres9(Utils.getOrCreateNbtData(armor)).intValue();
         } else if(this.getLitres7(Utils.getOrCreateNbtData(armor)).intValue() != 0 && this.getLitres10(Utils.getOrCreateNbtData(armor)).intValue() >= 0) {
             charge += amount + 5 * this.getLitres10(Utils.getOrCreateNbtData(armor)).intValue();
         } else if(this.getLitres7(Utils.getOrCreateNbtData(armor)).intValue() != 0 && this.getLitres11(Utils.getOrCreateNbtData(armor)).intValue() >= 0) {
             charge += amount + 5 * this.getLitres11(Utils.getOrCreateNbtData(armor)).intValue();
         } else {
            charge += amount;
         }

         if(charge > maxCharge) {
            return Integer.valueOf(maxCharge);
         } else {
            nbtData.setInteger("charge", charge);
            return Integer.valueOf(charge);
         }
      } else {
         return Integer.valueOf(0);
      }
   }

   public Integer chargeInvis(ItemStack armor, int amount) {
      LavaChestPlateItem item = (LavaChestPlateItem)armor.getItem();
      if(amount >= 0 && armor.stackSize <= 1) {
         NBTTagCompound nbtData = Utils.getOrCreateNbtData(armor);
         int charge = nbtData.getInteger("chargeinvis");
         int maxCharge = item.getMaxChargeInvis(nbtData).intValue();
         if(amount > maxCharge - charge) {
            amount = maxCharge - charge;
         }

         if(this.getLitres(Utils.getOrCreateNbtData(armor)).intValue() != 0 && this.getLitres(Utils.getOrCreateNbtData(armor)).intValue() >= 0) {
             charge += amount + 5 * this.getLitres(Utils.getOrCreateNbtData(armor)).intValue();
          } else if(this.getLitres1(Utils.getOrCreateNbtData(armor)).intValue() != 0 && this.getLitres1(Utils.getOrCreateNbtData(armor)).intValue() >= 0) {
              charge += amount + 5 * this.getLitres1(Utils.getOrCreateNbtData(armor)).intValue();
          } else if(this.getLitres2(Utils.getOrCreateNbtData(armor)).intValue() != 0 && this.getLitres2(Utils.getOrCreateNbtData(armor)).intValue() >= 0) {
              charge += amount + 5 * this.getLitres2(Utils.getOrCreateNbtData(armor)).intValue();
          } else if(this.getLitres3(Utils.getOrCreateNbtData(armor)).intValue() != 0 && this.getLitres3(Utils.getOrCreateNbtData(armor)).intValue() >= 0) {
              charge += amount + 5 * this.getLitres3(Utils.getOrCreateNbtData(armor)).intValue();
          } else if(this.getLitres4(Utils.getOrCreateNbtData(armor)).intValue() != 0 && this.getLitres4(Utils.getOrCreateNbtData(armor)).intValue() >= 0) {
              charge += amount + 5 * this.getLitres4(Utils.getOrCreateNbtData(armor)).intValue();
          } else if(this.getLitres5(Utils.getOrCreateNbtData(armor)).intValue() != 0 && this.getLitres5(Utils.getOrCreateNbtData(armor)).intValue() >= 0) {
              charge += amount + 5 * this.getLitres5(Utils.getOrCreateNbtData(armor)).intValue();
          } else if(this.getLitres6(Utils.getOrCreateNbtData(armor)).intValue() != 0 && this.getLitres6(Utils.getOrCreateNbtData(armor)).intValue() >= 0) {
              charge += amount + 5 * this.getLitres6(Utils.getOrCreateNbtData(armor)).intValue();
          } else if(this.getLitres7(Utils.getOrCreateNbtData(armor)).intValue() != 0 && this.getLitres7(Utils.getOrCreateNbtData(armor)).intValue() >= 0) {
              charge += amount + 5 * this.getLitres7(Utils.getOrCreateNbtData(armor)).intValue();
          } else if(this.getLitres7(Utils.getOrCreateNbtData(armor)).intValue() != 0 && this.getLitres8(Utils.getOrCreateNbtData(armor)).intValue() >= 0) {
              charge += amount + 5 * this.getLitres8(Utils.getOrCreateNbtData(armor)).intValue();
          } else if(this.getLitres7(Utils.getOrCreateNbtData(armor)).intValue() != 0 && this.getLitres9(Utils.getOrCreateNbtData(armor)).intValue() >= 0) {
              charge += amount + 5 * this.getLitres9(Utils.getOrCreateNbtData(armor)).intValue();
          } else if(this.getLitres7(Utils.getOrCreateNbtData(armor)).intValue() != 0 && this.getLitres10(Utils.getOrCreateNbtData(armor)).intValue() >= 0) {
              charge += amount + 5 * this.getLitres10(Utils.getOrCreateNbtData(armor)).intValue();
          } else if(this.getLitres7(Utils.getOrCreateNbtData(armor)).intValue() != 0 && this.getLitres11(Utils.getOrCreateNbtData(armor)).intValue() >= 0) {
              charge += amount + 5 * this.getLitres11(Utils.getOrCreateNbtData(armor)).intValue();
         } else {
            charge += amount;
         }

         if(charge > maxCharge) {
            return Integer.valueOf(maxCharge);
         } else {
            nbtData.setInteger("chargeinvis", charge);
            return Integer.valueOf(charge);
         }
      } else {
         return Integer.valueOf(0);
      }
   }

   public Integer getMaxCharge(NBTTagCompound nbtData) {
      return Integer.valueOf(this.maxCharge.intValue() < 24001?this.maxCharge.intValue()  +nbtData.getInteger("wither") * 500 + nbtData.getInteger("amp") + nbtData.getByte("dss") * 500 + nbtData.getByte("exo") * 5000 + nbtData.getByte("hell") * 2000 + nbtData.getInteger("yc") * 500 + nbtData.getInteger("shi") + nbtData.getInteger("lpm") + nbtData.getInteger("lptm") + nbtData.getInteger("wither"):24001);
   }

   public Integer getMaxChargeInvis(NBTTagCompound nbtData) {
      return Integer.valueOf(300);
   }

   public Integer getAmp(NBTTagCompound nbtData) {
      return Integer.valueOf(nbtData.getInteger("amp"));
   }

   public int getDarkShield(NBTTagCompound nbtData) {
      return nbtData.getByte("dss");
   }

   public int getExoItem(NBTTagCompound nbtData) {
      return nbtData.getByte("exo");
   }

   public int getHell(NBTTagCompound nbtData) {
      return nbtData.getByte("hell");
   }

   public int getKD(NBTTagCompound nbtData) {
      return nbtData.getByte("kdi");
   }

   public int getHebItem2(NBTTagCompound nbtData) {
      return nbtData.getByte("heb2");
   }

   public Integer getYcItem(NBTTagCompound nbtData) {
      return Integer.valueOf(nbtData.getInteger("yc"));
   }

   public Integer getShitItem(NBTTagCompound nbtData) {
      return Integer.valueOf(nbtData.getInteger("shi"));
   }

   public Integer getLPM(NBTTagCompound nbtData) {
      return Integer.valueOf(nbtData.getInteger("lpm"));
   }

   public Integer getLPTM(NBTTagCompound nbtData) {
      return Integer.valueOf(nbtData.getInteger("lptm"));
   }

   public static int getInviseP(NBTTagCompound nbtData) {
      return nbtData.getByte("inv");
   }

   public int getInvise(NBTTagCompound nbtData) {
      return nbtData.hasKey("inv")?nbtData.getByte("inv"):-1;
   }

   public static Integer getColors(NBTTagCompound nbtData) {
      return Integer.valueOf(nbtData.hasKey("cole")?nbtData.getInteger("cole"):-1);
   }

   public static Integer getColor(NBTTagCompound nbtData) {
      return Integer.valueOf(nbtData.hasKey("colorsa")?nbtData.getInteger("colorsa"):-1);
   }

   public int getHebItem(NBTTagCompound nbtData) {
      return nbtData.getByte("heb");
   }
   public Integer getWither(NBTTagCompound n) {
	  return Integer.valueOf(n.getInteger("wither"));
   }

   public Integer getLitres(NBTTagCompound n) {
      return Integer.valueOf(n.getInteger("lavalitres$1"));
   }
   public Integer getLitres1(NBTTagCompound n) {
	      return Integer.valueOf(n.getInteger("lavalitres$2"));
	   } 
   public Integer getLitres2(NBTTagCompound n) {
	      return Integer.valueOf(n.getInteger("lavalitres$3"));
	   }
   public Integer getLitres3(NBTTagCompound n) {
	      return Integer.valueOf(n.getInteger("lavalitres$4"));
	   }
   public Integer getLitres4(NBTTagCompound n) {
	      return Integer.valueOf(n.getInteger("lavalitres$5"));
	   }
   public Integer getLitres5(NBTTagCompound n) {
	      return Integer.valueOf(n.getInteger("lavalitres$6"));
	   }
   public Integer getLitres6(NBTTagCompound n) {
	      return Integer.valueOf(n.getInteger("lavalitres$7"));
	   }
   public Integer getLitres7(NBTTagCompound n) {
	      return Integer.valueOf(n.getInteger("lavalitres$8"));
	   }
   
   public Integer getLitres8(NBTTagCompound n) {
	      return Integer.valueOf(n.getInteger("lavalitres$9"));
	   }
   
   public Integer getLitres9(NBTTagCompound n) {
	      return Integer.valueOf(n.getInteger("lavalitres$10"));
	   }
   public Integer getLitres10(NBTTagCompound n) {
	      return Integer.valueOf(n.getInteger("lavalitres$11"));
	   }
   public Integer getLitres11(NBTTagCompound n) {
	      return Integer.valueOf(n.getInteger("lavalitres$12"));
	   }
   public static boolean getItKorona(NBTTagCompound nbtData)
   {
	      return Boolean.valueOf(nbtData.getBoolean("itKorona"));
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

   public static Integer getChargeInvis(ItemStack armor) {
      if(armor != null) {
         LavaChestPlateItem item = (LavaChestPlateItem)armor.getItem();
         if(armor.stackSize > 1) {
            return Integer.valueOf(0);
         } else {
            NBTTagCompound nbtData = Utils.getOrCreateNbtData(armor);
            int charge = nbtData.getInteger("chargeinvis");
            int maxCharge = item.getMaxChargeInvis(nbtData).intValue();
            if(charge > maxCharge) {
               charge = maxCharge;
            }

            return Integer.valueOf(charge);
         }
      } else {
         return Integer.valueOf(0);
      }
   }

   public String getInfo(ItemStack armor) {
      LavaChestPlateItem item = (LavaChestPlateItem)armor.getItem();
      if(armor.stackSize > 1) {
         return "0/0";
      } else {
         NBTTagCompound nbtData = Utils.getOrCreateNbtData(armor);
         int charge = nbtData.getInteger("charge");
         int amp = item.getAmp(nbtData).intValue();
         int dss = item.getDarkShield(nbtData);
         int exo = item.getExoItem(nbtData);
         int heb = item.getHebItem(nbtData);
         int wither = item.getWither(nbtData);
         int heba = item.getHell(nbtData);
         int heb2 = item.getHebItem2(nbtData);
         int yc = item.getYcItem(nbtData).intValue();
         int shi = item.getShitItem(nbtData).intValue();
         int lpm = item.getLPM(nbtData).intValue();
         int lptm = item.getLPTM(nbtData).intValue();
         return charge + "/" + (this.maxCharge.intValue() + wither * 500 + amp + dss * 500 + exo * 5000 + heba * 2000 + heb * 1000   + yc * 500 + shi + lpm + lptm) + ":" + wither + ":" + amp + ":" + dss + ":" + exo + ":" + heba + ":" + heb2 + ":" + heb + ":" + yc + ":" + shi + ":" + lpm + ":" + lptm;
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
            return temp_charge > 0?(long)charge << 32 | (long)temp_charge & 4294967295L:(long)charge << 32 | 0L;
         }
      }
   }

   public long dischargeInvis(ItemStack armor, int value) {
      LavaChestPlateItem item = (LavaChestPlateItem)armor.getItem();
      if(armor.stackSize > 1) {
         return 0L;
      } else {
         NBTTagCompound nbtData = Utils.getOrCreateNbtData(armor);
         int charge = nbtData.getInteger("chargeinvis");
         int maxCharge = item.getMaxChargeInvis(nbtData).intValue();
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

            nbtData.setInteger("chargeinvis", charge);
            temp_charge = value - temp_charge;
            return temp_charge > 0?(long)charge << 32 | (long)temp_charge & 4294967295L:(long)charge << 32 | 0L;
         }
      }
   }

   public double getAbsorbation() {
      return this.absorbation;
   }

   @SideOnly(Side.CLIENT)
   public boolean hasEffect(ItemStack par1ItemStack) {
      return false;
   }

   public boolean isBookEnchantable(ItemStack itemstack1, ItemStack itemstack2) {
      return false;
   }

   @SideOnly(Side.CLIENT)
   public boolean onEntityItemUpdate(EntityItem entityItem) {
      Minecraft.getMinecraft().effectRenderer.addEffect(new EntityFlameFX(entityItem.worldObj, entityItem.posX, entityItem.posY, entityItem.posZ, 0.0D, 0.0D, 0.0D));
      return super.onEntityItemUpdate(entityItem);
   }

   public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack) {
      return false;
   }

   protected abstract int getModel(ItemStack var1);

   public float scale() {
      return 1.0F;
   }

   public long getShildDamage(EntityPlayer player, ItemStack armor, int damage, int armorslot) {
      long damage_ = this.discharge(armor, damage);
      armor.damageItem(damage / 10, player);
      return damage_;
   }
   
   @SideOnly(Side.CLIENT)
   public ModelBiped getArmorModel(EntityLiving entityLiving, ItemStack itemstack, int armorSlot) {
      ModelBaseLavaArmor armorModel = (ModelBaseLavaArmor)ClientProxy.armorModels.get(this);
      ExtendedPlayer var5 = ExtendedPlayer.get((EntityPlayer)entityLiving);
      ItemStack held_item;
      if(armorModel != null) {
         armorModel.bipedHeadwear.showModel = false;
         armorModel.bipedHead.showModel = armorSlot == 0;
         armorModel.bipedBody.showModel = armorSlot == 1 || armorSlot == 2;
         armorModel.bipedRightArm.showModel = armorSlot == 1;
         armorModel.bipedLeftArm.showModel = armorSlot == 1;
         armorModel.bipedRightLeg.showModel = armorSlot == 2 || armorSlot == 3;
         armorModel.bipedLeftLeg.showModel = armorSlot == 2 || armorSlot == 3;
         armorModel.isSneak = entityLiving.isSneaking();
         armorModel.isRiding = entityLiving.isRiding();
         armorModel.isChild = entityLiving.isChild();
         armorModel.heldItemRight = 0;
         armorModel.aimedBow = false;
         EntityPlayer player111111 = (EntityPlayer)entityLiving;
         held_item = player111111.getCurrentItemOrArmor(0);
         if(held_item != null) {
            armorModel.heldItemRight = 1;
            if(player111111.getItemInUseCount() > 0) {
               EnumAction enumaction1111 = held_item.getItemUseAction();
               if(enumaction1111 == EnumAction.bow) {
                  armorModel.aimedBow = true;
               } else if(enumaction1111 == EnumAction.block) {
                  armorModel.heldItemRight = 3;
               }
            }
         }

         if(entityLiving.isInvisible()) {
            Minecraft.getMinecraft().renderEngine.bindTexture("/mods/underqoder/Armors/Predator/predatorRBT2.png");
            GL11.glEnable(3042);
            GL11.glBlendFunc(1, 1);
            armorModel.color = 0;
         } else {
            armorModel.invise = this.getInvise(Utils.getOrCreateNbtData(itemstack));
            armorModel.color = getColor(Utils.getOrCreateNbtData(itemstack)).intValue();

         }
      }

      return armorModel;
   }

}
