package net.minecraft.client.addon.tchestplate;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.common.TickType;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Iterator;
import net.minecraft.client.addon.tchestplate.fx.manager.FX_base;
import net.minecraft.client.addon.tchestplate.player.ExtendedPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerDestroyItemEvent;

public class ServerTickHandler implements ITickHandler
{
    String[] isImmuneToFire = new String[] {"field_70178_ae", "isImmuneToFire"};
    private long timer = 0L;
    boolean initl = true;

    private void setImmuneToFire(Entity var1, boolean var2)
    {
        if (var1.isImmuneToFire() != var2)
        {
            ObfuscationReflectionHelper.setPrivateValue(Entity.class, var1, Boolean.valueOf(var2), (String[])this.isImmuneToFire);
        }
    }

    public void tickUseItem(EntityPlayer var1)
    {
    }

    public void tickStart(EnumSet var1, Object... var2)
    {
        if (var1.contains(TickType.PLAYER))
        {
            EntityPlayer var3 = (EntityPlayer)var2[0];

            if (var3 == null)
            {
                return;
            }

            ExtendedPlayer var4 = ExtendedPlayer.get(var3);
            ArrayList var5 = var4.activepotions;

            if (var5 != null && !var5.isEmpty())
            {
                Iterator var101 = var5.iterator();

                while (var101.hasNext())
                {
                    FX_base var11 = (FX_base)var101.next();

                    if (!var11.UpdateServer(var4, var3))
                    {
                        var101.remove();
                    }
                }
            }

            if (this.timer % 20L == 0L)
            {
                InventoryPlayer var101 = var3.inventory;
                boolean var111 = false;
                int var10;

                for (var10 = 0; var10 < var101.mainInventory.length; ++var10)
                {
                    if (var101.mainInventory[var10] != null && var101.mainInventory[var10].getItem() != null && var101.mainInventory[var10].getItem() instanceof IUpdateItemExtPlayer)
                    {
                        ((IUpdateItemExtPlayer)var101.mainInventory[var10].getItem()).onUpdate(var101.mainInventory[var10], var101.player.worldObj, var4, var10, var101.currentItem == var10, this.timer, IUpdateItemExtPlayer.SideExt.INV);
                    }
                }

                for (var10 = 0; var10 < var101.armorInventory.length; ++var10)
                {
                    if (var101.armorInventory[var10] != null && var101.armorInventory[var10].getItem() != null && var101.armorInventory[var10].getItem() instanceof IUpdateItemExtPlayer)
                    {
                        ((IUpdateItemExtPlayer)var101.armorInventory[var10].getItem()).onUpdate(var101.armorInventory[var10], var101.player.worldObj, var4, var10, var101.currentItem == var10, this.timer, IUpdateItemExtPlayer.SideExt.ARM);

                        if (var101.armorInventory[var10] != null && var101.armorInventory[var10].stackSize <= 0)
                        {
                            ItemStack var9 = var101.armorInventory[var10];
                            var101.armorInventory[var10] = null;
                            MinecraftForge.EVENT_BUS.post(new PlayerDestroyItemEvent(var3, var9));
                            var111 = true;
                        }
                    }
                }

                for (var10 = 0; var10 < var4.inventoryExt.inventory.length; ++var10)
                {
                    if (var4.inventoryExt.inventory[var10] != null && var4.inventoryExt.inventory[var10].getItem() != null && var4.inventoryExt.inventory[var10].getItem() instanceof IUpdateItemExtPlayer)
                    {
                        ((IUpdateItemExtPlayer)var4.inventoryExt.inventory[var10].getItem()).onUpdate(var4.inventoryExt.inventory[var10], var101.player.worldObj, var4, var10, var101.currentItem == var10, this.timer, IUpdateItemExtPlayer.SideExt.EXT);
                    }
                }

                if (var111)
                {
                    var3.inventory.inventoryChanged = true;
                }
            }

            ++this.timer;
            this.tickUseItem(var3);
        }

        if (var1.contains(TickType.WORLDLOAD))
        {
            ;
        }
    }

    public void decrementUpdateExt(InventoryPlayer var1)
    {
    }

    public void tickEnd(EnumSet var1, Object... var2)
    {
        if (var1.contains(TickType.PLAYER))
        {
            EntityPlayer var3 = (EntityPlayer)var2[0];

            if (var3 == null)
            {
                return;
            }

            ItemStack var4 = ExtendedPlayer.get(var3).inventoryExt.inventory[4];

            if (var4 != null && var4.getItemDamage() == 5)
            {
                this.setImmuneToFire(var3, true);
                var3.extinguish();
            }
            else if (var4 != null && var4.getItemDamage() == 3)
            {
                this.setImmuneToFire(var3, true);
            }
            else
            {
                this.setImmuneToFire(var3, false);
            }
        }
    }

    public EnumSet ticks()
    {
        return EnumSet.of(TickType.WORLDLOAD, TickType.PLAYER);
    }

    public String getLabel()
    {
        return "tchestplate";
    }
}
