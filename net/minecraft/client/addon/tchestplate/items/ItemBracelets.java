package net.minecraft.client.addon.tchestplate.items;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.addon.tchestplate.LavaChestPlate;
import net.minecraft.client.addon.tchestplate.items.renders.models.BaseItemModel;
import net.minecraft.client.addon.tchestplate.items.renders.models.bracelets.AngelBracelet;
import net.minecraft.client.addon.tchestplate.items.renders.models.bracelets.EnderBraslet;
import net.minecraft.client.addon.tchestplate.items.renders.models.bracelets.HunterBraslet;
import net.minecraft.client.addon.tchestplate.items.renders.models.bracelets.LavaBracelet;
import net.minecraft.client.addon.tchestplate.items.renders.models.bracelets.LavaVedmaBracelet;
import net.minecraft.client.addon.tchestplate.overlaygui.IArmorExt;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class ItemBracelets extends MultiItemBase implements IArmorExt, IRenderItemChestPlate
{
    @SideOnly(Side.CLIENT)
    public static BaseItemModel[] item;

    public ItemBracelets(int i, int count)
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
                par2List.add("§6 Браслет дает защиту 1%");
                return;

            case 1:
            case 2:
            case 4:
            case 6:
            case 7:
            case 8:
            default:
                par2List.add("§6 Браслет дает обратный урон " + this.getProcofDamage(par1ItemStack.getItemDamage()) + "%");
                break;

            case 3:
                par2List.add("§6 Браслет дает обратный урон " + this.getProcofDamage(par1ItemStack.getItemDamage()) + "%");
                par2List.add("§8 Браслет дает иммунитет к лаве");
                break;

            case 5:
                par2List.add("§6 Браслет дает обратный урон " + this.getProcofDamage(par1ItemStack.getItemDamage()) + "%");
                par2List.add("§8 Браслет дает иммунитет ко всему горению");
                break;

            case 9:
                par2List.add("§6 Браслет дает обратный урон " + this.getProcofDamage(par1ItemStack.getItemDamage()) + "%");
                par2List.add("§8 Даёт бесконечный запас питания.");
        }
    }

    public int getSlot()
    {
        return 4;
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
        return true;
    }

    public boolean isBookEnchantable(ItemStack itemstack1, ItemStack itemstack2)
    {
        return false;
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
                d = 5;
                break;

            case 5:
                d = 20;
                break;

            case 6:
                d = 25;
                break;

            case 7:
                d = 20;
                break;

            case 8:
                d = 20;
                break;

            case 9:
                d = 5;
                break;

            case 10:
                d = 20;
        }

        return d;
    }

    public int getCalculateProcDamage(ItemStack it, int damage_, EntityPlayer player, Entity attacker)
    {
        int d = it.getItemDamage();

        if (d == 0)
        {
            return (int)((float)damage_ - (float)damage_ * 1.0F / 100.0F);
        }
        else
        {
            d = this.getProcofDamage(it.getItemDamage());

            if (attacker != null && attacker instanceof EntityPlayer && d > 0)
            {
                attacker.attackEntityFrom(DamageSource.causePlayerDamage(player), damage_ * d / 100);
            }

            return damage_;
        }
    }

    static
    {
        if (FMLCommonHandler.instance().getSide() == Side.CLIENT)
        {
            item = new BaseItemModel[16];
            String tex = "/mods/LavaChestPlate/textures/items/bracelets/lava.png";
            String texS = "/mods/provider/ual/hunter_amulet.png";
            String texS1 = "/mods/underqoder/braslets/ender_braclet.png";
            item[0] = new LavaBracelet("/mods/LavaChestPlate/textures/items/bracelets/lava.png", 0);
            item[1] = new LavaBracelet("/mods/LavaChestPlate/textures/items/bracelets/lava.png", 5);
            item[2] = new LavaBracelet("/mods/LavaChestPlate/textures/items/bracelets/lava.png", 10);
            item[3] = new LavaBracelet("/mods/LavaChestPlate/textures/items/bracelets/lava.png", 15);
            item[4] = new LavaBracelet("/mods/LavaChestPlate/textures/items/bracelets/lava.png", 20);
            item[5] = new LavaBracelet("/mods/LavaChestPlate/textures/items/bracelets/lava.png", 25);
            item[6] = new HunterBraslet("/mods/provider/ual/hunter_braclet.png");
            item[7] = new EnderBraslet("", 0);
            item[8] = new AngelBracelet("", 0);
            item[9] = new LavaBracelet("/mods/LavaChestPlate/textures/items/bracelets/angel_s.png", 0);
            item[10] = new LavaVedmaBracelet("/mods/LavaChestPlate/textures/items/bracelets/vedma_b.png", 0);
        }
    }
}
