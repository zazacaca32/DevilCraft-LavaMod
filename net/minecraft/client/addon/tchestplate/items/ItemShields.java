package net.minecraft.client.addon.tchestplate.items;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.addon.tchestplate.LavaChestPlate;
import net.minecraft.client.addon.tchestplate.items.renders.models.BaseItemModel;
import net.minecraft.client.addon.tchestplate.items.renders.models.shields.ShieldAngel;
import net.minecraft.client.addon.tchestplate.items.renders.models.shields.ShieldDemon;
import net.minecraft.client.addon.tchestplate.items.renders.models.shields.ShieldDiamond;
import net.minecraft.client.addon.tchestplate.items.renders.models.shields.ShieldHunter;
import net.minecraft.client.addon.tchestplate.items.renders.models.shields.ShieldLava;
import net.minecraft.client.addon.tchestplate.items.renders.models.shields.ShieldLavaRB;
import net.minecraft.client.addon.tchestplate.items.renders.models.shields.ShieldLavaUltima;
import net.minecraft.client.addon.tchestplate.items.renders.models.shields.ShieldStil;
import net.minecraft.client.addon.tchestplate.items.renders.models.shields.ShieldWood;
import net.minecraft.client.addon.tchestplate.overlaygui.IArmorExt;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class ItemShields extends Item implements IArmorExt, IRenderItemChestPlate
{
    @SideOnly(Side.CLIENT)
    public static BaseItemModel[] item;
    private float proc;
    private int RenderID;
    @SideOnly(Side.CLIENT)
    private Icon icon;

    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.icon = par1IconRegister.registerIcon("LavaChestPlate:" + this.getUnlocalizedName().substring(5));
    }

    public ItemShields(int i, float proc, int maxDamage, int RenderID)
    {
        super(i);
        super.maxStackSize = 1;
        this.setMaxDamage(maxDamage);
        this.proc = proc;
        this.RenderID = RenderID;
        this.setCreativeTab(LavaChestPlate.tabItemss);
    }

    public boolean hitEntity(ItemStack par1ItemStack, EntityLiving par2EntityLiving, EntityLiving par3EntityLiving)
    {
        par1ItemStack.damageItem(par1ItemStack.itemID == LavaChestPlate.litemShieldsDemon.itemID ? 0 : (par1ItemStack.itemID == LavaChestPlate.litemShieldsLavaUltima.itemID ? 0 : (par1ItemStack.itemID == LavaChestPlate.litemShieldsHunter.itemID ? 0 : 1)), par3EntityLiving);
        return true;
    }

    public void onCreated(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List, boolean par4)
    {
        par2List.add("§6 Защита " + this.proc + "% от урона");

        if (this.RenderID < 5)
        {
            par2List.add("§2 Ремонтируется в ремонтной станции");
        }
        else
        {
            par2List.add("§2 Не имеет износа");
        }
    }

    public Icon getIconFromDamage(int par1)
    {
        return this.icon;
    }

    public int getSlot()
    {
        return 5;
    }

    @SideOnly(Side.CLIENT)
    public BaseItemModel getItemModel(int Damage)
    {
        return item[this.RenderID];
    }

    @SideOnly(Side.CLIENT)
    public BaseItemModel getItemModelStatic(int Damage)
    {
        return null;
    }

    public int getCalculateProcDamage(ItemStack it, int damage_)
    {
        System.out.println(it + ":" + it.getItemDamage());
        it.setItemDamage(it.getItemDamage() + (it.itemID == LavaChestPlate.litemShieldsDemon.itemID ? 0 : (it.itemID == LavaChestPlate.litemShieldsLavaUltima.itemID ? 0 : (it.itemID == LavaChestPlate.litemShieldsHunter.itemID ? 0 : 1))));
        return (int)((float)damage_ - (float)damage_ * this.proc / 100.0F);
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

    static
    {
        if (FMLCommonHandler.instance().getSide() == Side.CLIENT)
        {
            (item = new BaseItemModel[16])[0] = new ShieldWood();
            item[1] = new ShieldStil();
            item[2] = new ShieldDiamond();
            item[3] = new ShieldLava();
            item[4] = new ShieldLavaRB();
            item[5] = new ShieldLavaUltima();
            item[6] = new ShieldHunter();
            item[7] = new ShieldDemon();
            item[8] = new ShieldAngel();
        }
    }
}
