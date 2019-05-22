package net.minecraft.client.addon.tchestplate.donate;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.io.File;
import java.io.IOException;
import net.minecraft.client.addon.tchestplate.LavaChestPlate;
import net.minecraft.client.addon.tchestplate.Utils;
import net.minecraft.client.addon.tchestplate.Bukkit.CallEventBukkit;
import net.minecraft.client.addon.tchestplate.donate.api.IDonateUser;
import net.minecraft.client.addon.tchestplate.donate.api.IOffert;
import net.minecraft.client.addon.tchestplate.overlaygui.ArmorExtInventory;
import net.minecraft.client.addon.tchestplate.packets.PacketMAUpdateInt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;

public class DonateManagerInv implements IOffert
{
    File file;
    public Container donateAdmContainer;
    public DonateStack[] inv = new DonateStack[this.GetStackSize()];
    public boolean enabled = true;

    private void writeToNBT(NBTTagCompound var1)
    {
        for (int var2 = 0; var2 < this.GetStackSize(); ++var2)
        {
            NBTTagCompound var3 = new NBTTagCompound();

            if (this.inv[var2] != null)
            {
                this.inv[var2].writeToNBT(var3);
            }

            var1.setCompoundTag("#" + var2, var3);
        }
    }

    private void readFromNBT(NBTTagCompound var1)
    {
        for (int var2 = 0; var2 < this.GetStackSize(); ++var2)
        {
            NBTTagCompound var3 = var1.getCompoundTag("#" + var2);

            if (var3 != null)
            {
                this.inv[var2] = DonateStack.loadItemStackFromNBT(var3);
            }
        }
    }

    public DonateManagerInv()
    {
    }

    public int findfirstEmpyslot()
    {
        for (int var1 = 0; var1 < this.inv.length; ++var1)
        {
            if (this.inv[var1] == null)
            {
                return var1;
            }
        }

        return 95;
    }

    public int GetStackSize()
    {
        return 96;
    }

    public void load()
    {
        try
        {
            if (this.file.getParentFile() != null)
            {
                this.file.getParentFile().mkdirs();
            }

            if (!this.file.exists() && !this.file.createNewFile())
            {
                return;
            }

            NBTTagCompound nbt = CompressedStreamTools.read(this.file);

            if (nbt != null)
            {
                this.readFromNBT(nbt);
            }
        }
        catch (IOException var2)
        {
            ;
        }
    }

    public void save()
    {
        try
        {
            NBTTagCompound nbt = new NBTTagCompound();
            this.writeToNBT(nbt);

            if (nbt != null)
            {
                CompressedStreamTools.write(nbt, this.file);
            }
        }
        catch (IOException var2)
        {
            ;
        }
    }

    public void setInventorySlotContents(int var1, ItemStack var2)
    {
        if (var2 == null)
        {
            this.inv[var1] = null;
        }
        else
        {
            DonateStack var3 = this.inv[var1];

            if (var3 == null)
            {
                this.inv[var1] = new DonateStack(var2);
            }
            else
            {
                this.inv[var1].setStack(var2);
            }
        }
    }

    public void onInventoryChanged()
    {
        this.save();
    }

    public ItemStack getStackInSlot(int var1)
    {
        DonateStack var2 = this.inv[var1];
        return var2 != null ? var2.source : null;
    }

    public int getInventoryStackLimit()
    {
        return 64;
    }

    public DonateManagerInv(File file)
    {
        this.file = file;
    }

    @SideOnly(Side.SERVER)
    public void StartOffers(ArmorExtInventory armor, EntityPlayer player)
    {
        boolean price = false;
        int var8 = this.checkPrice(armor);

        if (armor.coin > 0 && armor.coin >= var8)
        {
            ItemStack[] arr$ = armor.invOfferts;
            int len$ = arr$.length;

            for (int i$ = 0; i$ < len$; ++i$)
            {
                ItemStack ofst = arr$[i$];

                if (ofst != null && ofst.stackSize > this.findSizeStackInSlotInvFree(ofst, player))
                {
                    armor.setStatusDonate(0);
                    return;
                }
            }

            armor.setStatusDonate(2);

            if (!armor.startOffert(var8, this))
            {
                armor.setStatusDonate(12);
                armor.setStatusDonate(0);
            }
        }
    }

    public ItemStack decrStackSize(int var1, int var2)
    {
        if (this.inv[var1] != null)
        {
            ItemStack var3 = this.getStackInSlot(var1);
            ItemStack var4 = null;

            if (var3.stackSize <= var2)
            {
                var4 = var3;
                this.inv[var1] = null;
            }
            else
            {
                var4 = var3.splitStack(var2);
            }

            return var4;
        }
        else
        {
            return null;
        }
    }

    public ItemStack getFixedStackInSlot(int var1)
    {
        DonateStack var2 = this.inv[var1];
        return var2 != null ? var2.fixed : null;
    }

    public int getRows()
    {
        return 2;
    }

    public void setContainer(Container var1)
    {
        this.donateAdmContainer = var1;
    }

    public int checkPrice(ArmorExtInventory var1)
    {
        int var2 = 0;
        ItemStack[] var3 = var1.invOfferts;
        int var4 = var3.length;

        for (int var5 = 0; var5 < var4; ++var5)
        {
            ItemStack var6 = var3[var5];

            if (var6 != null)
            {
                NBTTagCompound var7 = Utils.getOrCreateNbtData(var6);
                NBTTagCompound var8 = var7.getCompoundTag("display");
                int var9 = var8.getInteger("price") * var6.stackSize;
                var2 += var9 - var9 * var8.getInteger("discount") / 100;
            }
        }

        return var2;
    }

    public int findSizeStackInSlotInvFree(ItemStack var1, EntityPlayer var2)
    {
        if (var1 == null)
        {
            return 0;
        }
        else
        {
            int var3 = 0;
            ItemStack[] var4 = var2.inventory.mainInventory;
            int var5 = var4.length;

            for (int var6 = 0; var6 < var5; ++var6)
            {
                ItemStack var7 = var4[var6];

                if (var7 != null)
                {
                    if (var7.isItemEqual(var1))
                    {
                        var3 += var1.getMaxStackSize() - var7.stackSize;
                    }
                }
                else
                {
                    var3 += var1.getMaxStackSize();
                }
            }

            return var3;
        }
    }

    public int findIndexForDonateInv(ItemStack var1)
    {
        for (int var2 = 0; var2 < this.inv.length; ++var2)
        {
            DonateStack var3 = this.inv[var2];

            if (var3 != null && var3.source != null && var3.source.isItemEqual(var1))
            {
                return var2;
            }
        }

        return 95;
    }

    public void sendUpdateStatusDonate(int var1, EntityPlayer var2)
    {
        PacketDispatcher.sendPacketToPlayer((new PacketMAUpdateInt((byte)3, var1)).makePacket(), (Player)var2);
    }

    @SideOnly(Side.SERVER)
    public void JobOffers(ArmorExtInventory armor, int donateBack, int donate)
    {
        armor.setStatusDonate(3);

        if (donateBack == 0)
        {
            int complete = 0;

            for (int i = 0; i < armor.invOfferts.length; ++i)
            {
                if (armor.invOfferts[i] != null)
                {
                    int slot = this.findIndexForDonateInv(armor.invOfferts[i]);

                    if (slot == -1)
                    {
                        LavaChestPlate.log.info("[LavaChestPlate] Ошибка не наиден стак в донат инвентаре ->" + armor.player.getEntityName() + " ->" + armor.invOfferts[i].itemID + ":" + armor.invOfferts[i].getItemDamage() + "=" + armor.invOfferts[i].stackSize);
                    }
                    else
                    {
                        ItemStack add = this.getFixedStackInSlot(slot).copy();

                        if (add == null)
                        {
                            LavaChestPlate.log.info("[LavaChestPlate] Ошибка не наиден Fixed стак в донат инвентаре ->" + armor.player.getEntityName() + " ->" + armor.invOfferts[i].itemID + ":" + armor.invOfferts[i].getItemDamage() + "=" + armor.invOfferts[i].stackSize);
                        }
                        else if (add.itemID >= 20971 && add.itemID <= 20973)
                        {
                            armor.invOfferts[i] = null;
                            CallEventBukkit.CallDonatePrivelegeskEvent(armor.player.getEntityName(), add.itemID, add.getItemDamage());
                            LavaChestPlate.log.info("[LavaChestPlate] Был вызван донат эвент привелегии ->" + armor.player.getEntityName() + " ->" + add.itemID + ":" + add.getItemDamage() + "=" + add.stackSize);
                            add = null;
                            ++complete;
                        }
                        else
                        {
                            if (armor.invOfferts[i].stackSize > add.getMaxStackSize())
                            {
                                LavaChestPlate.log.info("[LavaChestPlate] Создан новый размер стака ->" + armor.player.getEntityName() + " ->" + armor.invOfferts[i].itemID + ":" + armor.invOfferts[i].getItemDamage() + " был=" + armor.invOfferts[i].stackSize + "стал" + add.getMaxStackSize());
                                add.stackSize = add.getMaxStackSize();
                            }
                            else
                            {
                                add.stackSize = armor.invOfferts[i].stackSize;
                            }

                            if (!armor.player.inventory.addItemStackToInventory(add))
                            {
                                armor.setStatusDonate(10);
                                armor.setStatusDonate(0);
                                LavaChestPlate.log.info("[LavaChestPlate] не вложился стак до конца ->" + armor.player.getEntityName() + " ->" + add.itemID + ":" + add.getItemDamage() + "=" + add.stackSize);
                            }
                            else
                            {
                                LavaChestPlate.log.info("[LavaChestPlate] Успешно стак ->" + armor.player.getEntityName() + " ->" + armor.invOfferts[i].itemID + ":" + armor.invOfferts[i].getItemDamage() + "=" + armor.invOfferts[i].stackSize);
                                armor.invOfferts[i] = null;
                                ++complete;
                            }
                        }
                    }
                }
                else
                {
                    ++complete;
                }
            }

            if (complete == 4)
            {
                armor.setStatusDonate(4);
                armor.setStatusDonate(0);
                LavaChestPlate.log.info("[LavaChestPlate] Успешная сделка ->" + armor.player.getEntityName());
            }
            else
            {
                armor.setStatusDonate(11);
                armor.setStatusDonate(0);
                LavaChestPlate.log.info("[LavaChestPlate] Не Успешная сделка ->" + armor.player.getEntityName() + " complete->" + complete);
            }
        }
        else
        {
            LavaChestPlate.log.info("[LavaChestPlate] Cписания коинов не до конца осталось ->" + armor.player.getEntityName() + " k->" + donateBack);
        }

        armor.clearinvOfferts();
    }

    public void JobOffer(IDonateUser var1, int var2, int var3)
    {
        this.JobOffers((ArmorExtInventory)var1, var2, var3);
    }
}
