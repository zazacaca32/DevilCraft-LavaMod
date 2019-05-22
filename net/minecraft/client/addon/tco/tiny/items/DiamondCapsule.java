package net.minecraft.client.addon.tco.tiny.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.addon.tco.tiny.items.MultiItemBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class DiamondCapsule extends MultiItemBase {

   final boolean isGregTech;


   public DiamondCapsule(int i, int j, int count, boolean isGregTech) {
      super(i, count);
      super.maxStackSize = j;
      this.isGregTech = isGregTech;
   }

   @SideOnly(Side.CLIENT)
   public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List, boolean par4) {
      this.addMoreInformation(par1ItemStack, par2EntityPlayer, par2List, par4);
      switch(par1ItemStack.getItemDamage()) {
      case 1:
         par2List.add("§a Сжатая материя: 1кг/см2");
         break;
      case 2:
         if(this.isGregTech) {
            par2List.add("§a Не используется");
         } else {
            par2List.add("§a Сжатая материя: 8кг/см2");
         }
         break;
      case 3:
         if(this.isGregTech) {
            par2List.add("§a Сжатая усиленная материя: 1кг/см2");
         } else {
            par2List.add("§a Сжатая материя: 64кг/см2");
         }

         par2List.add("§8 Модуль: Letal Shot Attack");
         par2List.add("§8 Модуль: Шанс прохождения +0.08% за уровень");
         par2List.add("§8 Модуль: Снимает 30% лава энергии с брони противника");
         par2List.add("§8 Модуль: шанс вставки 65%");
         break;
      case 4:
         if(this.isGregTech) {
            par2List.add("§a Сжатая материя: 8кг/см2");
         } else {
            par2List.add("§a Сжатая материя: 512кг/см2");
         }
         break;
      case 5:
         if(this.isGregTech) {
            par2List.add("§a Сжатая материя: 48кг/см2");
         } else {
            par2List.add("§a Сжатая материя: 4096кг/см2");
         }
         break;
      case 6:
         if(this.isGregTech) {
            par2List.add("§a Сжатая материя: 384кг/см2");
         } else {
            par2List.add("§a Сжатая материя: 36т/см2");
         }
         break;
      case 7:
         par2List.add("§a Сжатая темная материя: 1кг/см2");
         break;
      case 8:
         par2List.add("§a Сжатая темная материя: 8кг/см2");
         break;
      case 9:
         par2List.add("§a Обработанная материей под давлением");
         break;
      case 10:
         par2List.add("§a Обработанная темной материей под давлением");
         break;
      case 11:
         par2List.add("§a Экзоскелет Припаевается к броне");
      }

   }

   public boolean isPasteModule(int damage) {
      return damage == 3 || damage >= 9 && damage <= 11;
   }

   public int PasteModule(int damage, ItemStack container, int lvl) {
      switch(damage) {
      case 3:
         return lvl + 1;
      case 4:
      case 5:
      case 6:
      case 7:
      case 8:
      default:
         break;
      case 9:
         if(lvl == 0) {
            return 1;
         }
         break;
      case 10:
         if(lvl == 0) {
            return 1;
         }
         break;
      case 11:
         if(lvl == 0) {
            return 1;
         }
      }

      return 0;
   }

   public int getModifierModule(int damage, int lvl) {
      switch(damage) {
      case 3:
         return lvl;
      case 4:
      case 5:
      case 6:
      case 7:
      case 8:
      default:
         return 0;
      case 9:
         return 1000;
      case 10:
         return 1000;
      case 11:
         return 5000;
      }
   }

   public int getModuleChance(int damage) {
      return damage == 3?65:(damage >= 9 && damage <= 11?100:0);
   }

   public boolean isNotUpdateModuleChanceClear(int damage) {
      return false;
   }

   public int isUpdateModule(int damage, int lvl, int damageMConatainer) {
      if(damage == 3) {
         if(damage != damageMConatainer) {
            return 2;
         }

         if(lvl < 10) {
            return 1;
         }
      }

      return damage >= 9 && damage <= 11 && damage != damageMConatainer?2:0;
   }

   public int[] OverrideItem(int damage) {
      return null;
   }

   @SideOnly(Side.CLIENT)
   public void addMoreInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List, boolean par4) {
      switch(par1ItemStack.getItemDamage()) {
      case 9:
         par2List.add("§8 Модуль: +1000L");
         par2List.add("§8 Модуль: шанс вставки 100%");
         break;
      case 10:
         par2List.add("§8 Модуль: +1000L");
         par2List.add("§8 Модуль: шанс вставки 100%");
         break;
      case 11:
         par2List.add("§8 Модуль: +5000L");
         par2List.add("§8 Модуль: шанс вставки 100%");
      }

   }
}
