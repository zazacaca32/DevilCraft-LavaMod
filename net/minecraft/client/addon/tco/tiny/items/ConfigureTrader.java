package net.minecraft.client.addon.tco.tiny.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.addon.tco.tiny.Utils.Utils;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockTradeBase;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockTraderController;
import net.minecraft.client.addon.tco.tiny.items.MultiItemBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ConfigureTrader extends MultiItemBase {

   public ConfigureTrader(int i, int j, int count) {
      super(i, count);
      super.maxStackSize = j;
   }

   public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int X, int Y, int Z, int side, float hitX, float hitY, float hitZ) {
      if(world.isRemote) {
         return false;
      } else {
         if(!world.isRemote) {
            TileEntity t = world.getBlockTileEntity(X, Y, Z);
            if(t == null) {
               player.sendChatToPlayer("[Настройщик] Кликать можно только по Контроллеру торговли и пр. Торговому аппарату");
               return true;
            }

            NBTTagCompound nbt;
            if(t instanceof TileEntityBlockTradeBase) {
               if(!player.getEntityName().equals(((TileEntityBlockTradeBase)t).Owner)) {
                  player.sendChatToPlayer("[Настройщик] Вы не владелец Торгового аппарата");
                  return true;
               }

               nbt = Utils.getOrCreateNbtData(stack);
               String owner = nbt.getString("Cowner");
               if(player.getEntityName().equals(owner)) {
                  int[] xyz = nbt.getIntArray("Cxyz");
                  if(xyz.length > 2) {
                     TileEntity t2 = world.getBlockTileEntity(xyz[0], xyz[1], xyz[2]);
                     if(t2 != null && t2 instanceof TileEntityBlockTraderController) {
                        if(!this.isDistance(t, t2)) {
                           player.sendChatToPlayer("[Настройщик] Торговый аппарат слишком далеко от контроллера");
                           return true;
                        }

                        if(((TileEntityBlockTradeBase)t).rewriteController(t2.xCoord, t2.yCoord, t2.zCoord)) {
                           player.sendChatToPlayer("[Настройщик] Сброшен с контроллера Торговый аппарат");
                           return true;
                        }

                        if(((TileEntityBlockTraderController)t2).addTorgApparat(X, Y, Z)) {
                           player.sendChatToPlayer("[Настройщик] Торговый аппарат добавлен к контроллеру");
                        } else {
                           player.sendChatToPlayer("[Настройщик] Торговый аппарат, уже привязан или не добавлен к контроллеру");
                        }
                     } else {
                        player.sendChatToPlayer("[Настройщик] Контроллер не наиден кликните по контроллеру еще раз");
                     }
                  } else {
                     player.sendChatToPlayer("[Настройщик] Кликните по контроллеру он не сохранен");
                  }
               } else {
                  player.sendChatToPlayer("[Настройщик] Вы не владелец настроищика, Кликните по контроллеру чтобы перенастроить его");
               }
            } else {
               if(!(t instanceof TileEntityBlockTraderController)) {
                  player.sendChatToPlayer("[Настройщик] Кликать можно только по Контроллеру торговли и пр. Торговому аппарату");
                  return true;
               }

               if(!player.getEntityName().equals(((TileEntityBlockTraderController)t).Owner)) {
                  player.sendChatToPlayer("[Настройщик] Вы не владелец Контроллера");
                  return true;
               }

               nbt = Utils.getOrCreateNbtData(stack);
               nbt.setIntArray("Cxyz", new int[]{X, Y, Z});
               nbt.setString("Cowner", player.getEntityName());
               player.sendChatToPlayer("[Настройщик] Контроллер торговли сохранен в настроищике");
            }
         }

         return true;
      }
   }

   @SideOnly(Side.CLIENT)
   public void addInformation(ItemStack var1, EntityPlayer var2, List var3, boolean var4) {
      switch(var1.getItemDamage()) {
      case 0:
         NBTTagCompound var5 = Utils.getOrCreateNbtData(var1);
         String var6 = var5.getString("Cowner");
         var3.add("§a Настройщик: ".concat(var6.isEmpty()?"Нет":var6));
         int[] var7 = var5.getIntArray("Cxyz");
         if(var7.length > 2) {
            var3.add("§a Контроллер: ".concat("x:").concat(String.valueOf(var7[0])).concat(" y:").concat(String.valueOf(var7[1])).concat(" z:").concat(String.valueOf(var7[2])));
         }
      default:
      }
   }

   public boolean isDistance(TileEntity t, TileEntity controller) {
      boolean radius = true;
      int x = t.xCoord >> 4;
      int z = t.zCoord >> 4;
      int x2 = controller.xCoord >> 4;
      int z2 = controller.zCoord >> 4;
      return x > x2 - 4 && x < x2 + 4 && z > z2 - 4 && z < z2 + 4;
   }
}
