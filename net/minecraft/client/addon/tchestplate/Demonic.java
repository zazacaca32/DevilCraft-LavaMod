package net.minecraft.client.addon.tchestplate;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;

import net.minecraft.client.addon.tchestplate.aaamodule.fx.ILavaItemd;
import net.minecraft.client.addon.tchestplate.armors.items.LavaChestPlateItem;
import net.minecraft.client.addon.tchestplate.donate.api.IOffertCompletteItem;
import net.minecraft.client.addon.tchestplate.player.ExtendedPlayer;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.common.ISpecialArmor.ArmorProperties;
import net.minecraftforge.event.ForgeSubscribe;

public class Demonic extends Item {
	

	  static Integer maxCharge = Integer.valueOf(0);
	  final short damage = 1600;
	  public Long tick = Long.valueOf(0L);
	  public static final long timeCreate = 86400000L;
	
	
   public Demonic(int par1) {
      super(par1);
      this.setMaxDamage(5000);
      this.setMaxStackSize(1);
      this.setCreativeTab(LavaChestPlate.tabArmor);
      this.maxCharge = Integer.valueOf(100000);
      
      
   }
   Integer dam = 2500;
   @SideOnly(Side.CLIENT)
   public void registerIcons(IconRegister par1IconRegister) {
      super.registerIcons(par1IconRegister);
      super.itemIcon = par1IconRegister.registerIcon("Adafik:1315");
   }
   public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List, boolean par4) {
	   NBTTagCompound localNBTTagCompound = Utils.getOrCreateNbtData(par1ItemStack);
      if(par1ItemStack.stackTagCompound != null && par1ItemStack.stackTagCompound.hasKey("lawar")) {
         par2List.add("§cУрон от меча: " + dam + "!");        
         par2List.add("§7Заряд: " + localNBTTagCompound.getInteger("charge"));
         par2List.add("§7Итем Принадлежит: " + par1ItemStack.stackTagCompound.getString("lawar"));
      } else {
          par2List.add("§cУрон от меча: " + dam + "!");
         par2List.add("§7Заряд: " + localNBTTagCompound.getInteger("charge") );
         par2List.add("§7Итем Принадлежит: Никому");
      }

   }

   @ForgeSubscribe
   public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
      if(!par2World.isRemote) {
         if(par1ItemStack.stackTagCompound != null) {
            if(!par1ItemStack.stackTagCompound.hasKey("lawar")) {
               par1ItemStack.stackTagCompound.setString("lawar", par3EntityPlayer.getEntityName());
               par3EntityPlayer.sendChatToPlayer("" + EnumChatFormatting.GRAY + EnumChatFormatting.DARK_GREEN + "Владелец установлен " + par3EntityPlayer.getEntityName() + " Менять нельзя!");
               return par1ItemStack;
            }

            par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.nightVision.id, dam));
         } else {
            par1ItemStack.stackTagCompound = new NBTTagCompound();
            par1ItemStack.stackTagCompound.setString("lawar", par3EntityPlayer.getEntityName());
            par3EntityPlayer.sendChatToPlayer("" + EnumChatFormatting.GRAY + EnumChatFormatting.DARK_GREEN + "Владелец " + par3EntityPlayer.getEntityName() + " Менять нельзя!");
         }
      }

      return par1ItemStack;
   }

   @ForgeSubscribe
   public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
      boolean flag = entity instanceof EntityPlayer;
      if(!player.worldObj.isRemote) {
         if(stack.stackTagCompound != null && stack.stackTagCompound.hasKey("lawar") && stack.stackTagCompound.getString("lawar").equals(player.getEntityName())) {
            if(entity.attackEntityFrom(DamageSource.causePlayerDamage(player), 2500)) {
               Iterator i = MinecraftServer.getServer().getConfigurationManager().playerEntityList.iterator();
               if(flag) {
                  return true;
               }

               while(i.hasNext()) {
                  Object ii = i.next();
                  if(!(ii instanceof EntityPlayerMP)) {
                     return true;
                  }
               }
            }

            return true;
         }

         player.addPotionEffect(new PotionEffect(Potion.poison.id, 200));
      }

      return true;
   }
   
   @SideOnly(Side.CLIENT)
   public static boolean addInformationDemonItem(NBTTagCompound paramNBTTagCompound, List paramList, boolean paramBoolean)
   {
     long l = paramNBTTagCompound.getLong("nubtime");
     if (System.currentTimeMillis() < l)
     {
       if (paramBoolean) {
         paramList.add("§7 Итем будет не активен через " + secondsToString((int)((l - LavaChestPlate.syncTimeServer) / 1000L)) + " сек");
       }
       return true;
     }
     if (paramBoolean) {
       paramList.add("§7 У итема истёк срок службы он исчезнет.");
     }
     return false;
   }

   @SideOnly(Side.CLIENT)
   private static String secondsToString(int paramInt)
   {
     return String.format("%02d:%02d:%02d", new Object[] { Integer.valueOf(paramInt / 3600), Integer.valueOf(paramInt / 60 % 60), Integer.valueOf(paramInt % 60) });
   }
   
   
   public Item setNoRepair() {
      super.canRepair = false;
      return this;
   }

   public int getDamageVsEntity(Entity par1Entity, ItemStack itemStack) {
      return par1Entity instanceof EntityPlayer?2400:2400;
   }

   public boolean isBookEnchantable(ItemStack itemstack1, ItemStack itemstack2) {
      return false;
   }

   public boolean hitEntity(ItemStack par1ItemStack, EntityLiving par2EntityLiving, EntityLiving par3EntityLiving) {
      return true;
   }

   
   public boolean full3d() {
      super.bFull3D = true;
      return true;
   }
  
 }
