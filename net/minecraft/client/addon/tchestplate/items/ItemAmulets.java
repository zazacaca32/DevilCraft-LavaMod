package net.minecraft.client.addon.tchestplate.items;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.addon.tchestplate.IUpdateItemExtPlayer;
import net.minecraft.client.addon.tchestplate.LavaChestPlate;
import net.minecraft.client.addon.tchestplate.Utils;
import net.minecraft.client.addon.tchestplate.items.renders.models.BaseItemModel;
import net.minecraft.client.addon.tchestplate.items.renders.models.amulets.DiamondAmulet;
import net.minecraft.client.addon.tchestplate.items.renders.models.amulets.EnderAmulet;
import net.minecraft.client.addon.tchestplate.items.renders.models.amulets.HunterAmulet;
import net.minecraft.client.addon.tchestplate.items.renders.models.amulets.LavaAmulet;
import net.minecraft.client.addon.tchestplate.items.renders.models.amulets.LavaRBAmulet;
import net.minecraft.client.addon.tchestplate.items.renders.models.amulets.LavaUltimaAmulet;
import net.minecraft.client.addon.tchestplate.items.renders.models.amulets.LavaVedmaAmulet;
import net.minecraft.client.addon.tchestplate.items.renders.models.amulets.LavaaAmulet;
import net.minecraft.client.addon.tchestplate.items.renders.models.amulets.StillAmulet;
import net.minecraft.client.addon.tchestplate.items.renders.models.amulets.WoodAmulet;
import net.minecraft.client.addon.tchestplate.overlaygui.IArmorExt;
import net.minecraft.client.addon.tchestplate.player.ExtendedPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ItemAmulets extends MultiItemBase implements IArmorExt, IRenderItemChestPlate, IUpdateItemExtPlayer
{
    @SideOnly(Side.CLIENT)
    public static BaseItemModel[] item;
    public Long tick = Long.valueOf(0L);

    public ItemAmulets(int i, int count)
    {
        super(i, count);
        super.maxStackSize = 1;
        this.setCreativeTab(LavaChestPlate.tabItemss);
    }

    public void onCreated(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List, boolean par4)
    {
        switch (par1ItemStack.getItemDamage())
        {
            case 0:
                par2List.add("§6 Амулет дает защиту 1%");

            case 5:
                par2List.add("§6 Амулет дает дополнительный урон " + this.getProcofDamage(par1ItemStack.getItemDamage()) + "%");
                par2List.add("§8 Амулет дает иммунитет к Шоку 30%");
                break;

            case 8:
                par2List.add("§8 Амулет дает иммунитет к Шоку 30%");
                par2List.add("§8 Амулет дает шанс выжить,при угрозе смерти");
                par2List.add("§8  восстанавливая 30%L на лава сетах");
                par2List.add("§8 Амулет дает сделать рывок на ~");
                par2List.add("§8 Амулет дает облик черной пантеры");

            default:
                par2List.add("§6 Амулет дает дополнительный урон " + this.getProcofDamage(par1ItemStack.getItemDamage()) + "%");

                if (par1ItemStack.getItemDamage() >= 3)
                {
                    par2List.add("§6 Амулет дает иммунитет к Шоку 30%");
                }
        }
    }

    public int getSlot()
    {
        return 3;
    }

    @SideOnly(Side.CLIENT)
    public BaseItemModel getItemModel(int Damage)
    {
        return item[Damage];
    }

    @SideOnly(Side.CLIENT)
    public BaseItemModel getItemModelStatic(int Damage)
    {
        return null;
    }

    public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack)
    {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack par1ItemStack)
    {
        return false;
    }

    public boolean isBookEnchantable(ItemStack itemstack1, ItemStack itemstack2)
    {
        return false;
    }

    public int getCalculateProcDamage(ItemStack it, int damage_)
    {
        return it.getItemDamage() == 0 ? (int)((float)damage_ - (float)damage_ * 1.0F / 100.0F) : damage_;
    }

    public int getCalculateAttackDamage(ItemStack it, int damage_)
    {
        return damage_ + damage_ * this.getProcofDamage(it.getItemDamage()) / 100;
    }

    public int getProcofDamage(int damage)
    {
        byte d = 0;

        switch (damage)
        {
            case 1:
                d = 1;
                break;

            case 2:
                d = 2;
                break;

            case 3:
                d = 3;
                break;

            case 4:
                d = 10;
                break;

            case 5:
                d = 20;
                break;

            case 6:
                d = 22;
                break;

            case 7:
                boolean var3 = true;

            case 8:
                d = 22;
                break;

            case 9:
                d = 10;
                break;

            case 10:
                d = 20;
        }

        return d;
    }

    public boolean isCanceledShok(ItemStack it)
    {
        return it.getItemDamage() >= 3 && Utils.getRandomProc(30.0D);
    }

    public void onUpdate(ItemStack var1, World var2, ExtendedPlayer var3, int var4, boolean var5, long var6, IUpdateItemExtPlayer.SideExt var8)
    {
        if (var8 == IUpdateItemExtPlayer.SideExt.EXT && var3.inventoryExt.inventory[3] != null && var3.inventoryExt.inventory[3].getItemDamage() == 8)
        {
            NBTTagCompound nbt = Utils.getOrCreateNbtData(var3.inventoryExt.inventory[3]);

            if (nbt.getInteger("openclade") <= 299)
            {
                int i = nbt.getInteger("iii");
                nbt.setInteger("iii", nbt.getInteger("iii") + 1);

                if (nbt.getInteger("iii") >= 20)
                {
                    nbt.setInteger("openclade", nbt.getInteger("openclade") + 1);
                    nbt.setInteger("iii", 0);
                }
            }
        }
    }

    static
    {
        if (FMLCommonHandler.instance().getSide() == Side.CLIENT)
        {
            (item = new BaseItemModel[16])[0] = new WoodAmulet("/mods/LavaChestPlate/textures/items/amulet/lava.png");
            item[1] = new StillAmulet("/mods/LavaChestPlate/textures/items/amulet/lava.png");
            item[2] = new DiamondAmulet("/mods/LavaChestPlate/textures/items/amulet/lava.png");
            item[3] = new LavaAmulet("/mods/LavaChestPlate/textures/items/amulet/lava.png");
            item[4] = new LavaRBAmulet("/mods/LavaChestPlate/textures/items/amulet/lava.png");
            item[5] = new LavaUltimaAmulet("/mods/LavaChestPlate/textures/items/amulet/lava.png");
            item[6] = new HunterAmulet("/mods/provider/ual/hunter_amulet.png");
            item[7] = new EnderAmulet("/mods/underqoder/amulets/ender_amulet.png");
            item[9] = new LavaaAmulet("/mods/LavaChestPlate/textures/items/amulet/army.png");
            item[10] = new LavaVedmaAmulet("/mods/LavaChestPlate/textures/items/amulet/vedma_a.png");
        }
    }
}
