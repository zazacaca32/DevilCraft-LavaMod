package net.minecraft.client.addon.tchestplate.items;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.addon.tchestplate.LavaChestPlate;
import net.minecraft.client.addon.tchestplate.Utils;
import net.minecraft.client.addon.tchestplate.items.renders.models.BaseItemModel;
import net.minecraft.client.addon.tchestplate.items.renders.models.demonicPet;
import net.minecraft.client.addon.tchestplate.items.renders.models.pets.ZombiePet;
import net.minecraft.client.addon.tchestplate.items.renders.models.pets.alienPet;
import net.minecraft.client.addon.tchestplate.items.renders.models.pets.angelPet;
import net.minecraft.client.addon.tchestplate.items.renders.models.pets.demonPet;
import net.minecraft.client.addon.tchestplate.items.renders.models.pets.leprechaunPet;
import net.minecraft.client.addon.tchestplate.items.renders.models.pets.mikePet;
import net.minecraft.client.addon.tchestplate.items.renders.models.pets.minerPet;
import net.minecraft.client.addon.tchestplate.items.renders.models.pets.minonyPet;
import net.minecraft.client.addon.tchestplate.items.renders.models.pets.pikachuPet;
import net.minecraft.client.addon.tchestplate.items.renders.models.pets.vedmaPet;
import net.minecraft.client.addon.tchestplate.items.renders.models.pets.zaikaPet;
import net.minecraft.client.addon.tchestplate.overlaygui.ArmorExtInventory;
import net.minecraft.client.addon.tchestplate.overlaygui.IArmorExt;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeSubscribe;

public class ItemPets extends MultiItemBase implements IArmorExt, IRenderItemChestPlate
{
    @SideOnly(Side.CLIENT)
    public static BaseItemModel[] items;
    @SideOnly(Side.CLIENT)
    public static BaseItemModel[] itemsstatic;
    public ArmorExtInventory inventoryExt;

    public ItemPets(int i, int j, int count)
    {
        super(i, count);
        super.maxStackSize = j;
        this.setCreativeTab(LavaChestPlate.tabItemss);
    }

    @ForgeSubscribe
    public ItemStack onItemRightClick(ItemStack var1, World var2, EntityPlayer var3)
    {
        if (var2.isRemote)
        {
            LavaChestPlate var10000 = LavaChestPlate.instance;

            if (var1.getItemDamage() == 8)
            {
                LavaChestPlate.proxy.openCustomGui(12, var3);
            }
            else
            {
                LavaChestPlate.proxy.openCustomGui(11, var3);
            }
        }

        return var1;
    }

    @SideOnly(Side.CLIENT)
    public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        for (int x = 0; x < super.count; ++x)
        {
            par3List.add(new ItemStack(this, 1, x));
        }
    }

    public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5)
    {
        if (!par2World.isRemote)
        {
            ;
        }
    }

    public void onCreated(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List, boolean par4)
    {
        NBTTagCompound nbt = Utils.getOrCreateNbtData(par1ItemStack);
        par2List.add("§6 Опыт " + nbt.getInteger("psp"));
        byte lvl = nbt.getByte("plvl");
        par2List.add("§6 Уровень " + lvl);
        int h = lvl - 0;

        if (h > 0)
        {
            par2List.add("§6 Защита " + h * 5);
        }

        switch (par1ItemStack.getItemDamage())
        {
            case 8:
                par2List.add("§5 Добытая кобальтовые кристалы §6" + nbt.getInteger("tag1"));
                par2List.add("§5 Добывает ниже 50 координаты");
                par2List.add("§5 Для добычи 1 кристала требуется 2 минуты");
                par2List.add("§5 Используйте в Сундуке смурфика");

            default:
                par2List.add("§6  Правой кнопкои открыть меню");
        }
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
    }

    public Icon getIconFromDamage(int par1)
    {
        return null;
    }

    public int getSlot()
    {
        return 2;
    }

    @SideOnly(Side.CLIENT)
    public BaseItemModel getItemModel(int var1)
    {
        return items[var1] == null ? items[0] : items[var1];
    }

    @SideOnly(Side.CLIENT)
    public BaseItemModel getItemModelStatic(int var1)
    {
        return itemsstatic[var1] == null ? itemsstatic[0] : itemsstatic[var1];
    }

    static
    {
        if (FMLCommonHandler.instance().getSide() == Side.CLIENT)
        {
            (items = new BaseItemModel[16])[0] = new ZombiePet(false);
            items[1] = new angelPet(false);
            items[2] = new demonPet(false);
            items[3] = new minonyPet(false);
            items[4] = new pikachuPet(false);
            items[5] = new mikePet(false);
            items[6] = new zaikaPet(false);
            items[7] = new demonicPet(false);
            items[8] = new minerPet(false);
            items[9] = new vedmaPet(false);
            items[10] = new alienPet(false);
            items[11] = new leprechaunPet(false);
            (itemsstatic = new BaseItemModel[16])[0] = new ZombiePet(true);
            itemsstatic[1] = new angelPet(true);
            itemsstatic[2] = new demonPet(true);
            itemsstatic[3] = new minonyPet(true);
            itemsstatic[4] = new pikachuPet(true);
            itemsstatic[5] = new mikePet(true);
            itemsstatic[6] = new zaikaPet(true);
            itemsstatic[7] = new demonicPet(true);
            itemsstatic[8] = new minerPet(true);
            itemsstatic[9] = new vedmaPet(true);
            itemsstatic[10] = new alienPet(true);
            itemsstatic[11] = new leprechaunPet(true);
        }
    }
}
