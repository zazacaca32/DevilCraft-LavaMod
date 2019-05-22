package net.minecraft.client.addon.tchestplate.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.addon.tchestplate.LavaChestPlate;
import net.minecraft.client.addon.tchestplate.Utils;
import net.minecraft.client.addon.tchestplate.items.renders.models.BaseItemModel;
import net.minecraft.client.addon.tchestplate.overlaygui.IArmorExt;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class ItemRunes extends MultiItemBase implements IArmorExt
{
    public ItemRunes(int i, int count)
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
                par2List.add("§8Эффекты руны:");
                par2List.add("§8 Обратный урон 10%");
                break;

            case 1:
                par2List.add("§8Эффекты руны:");
                par2List.add("§8 10% защиты");
                par2List.add("§8 15% дополнительный урон");
                break;

            case 2:
                par2List.add("§8Эффекты руны:");
                par2List.add("§8 Обратный урон 10%");
                par2List.add("§8 Иммунитет к горению");
                par2List.add("§8 Дополнительный урон в воде 200%");
                break;

            case 3:
                par2List.add("§8Эффекты руны:");
                par2List.add("§8 Обратный урон 5%");
                par2List.add("§8 Дополнительный урон 10%");
                par2List.add("§8 Иммунитет к шоку 5%");
                break;

            case 4:
                par2List.add("§8Эффекты руны:");
                par2List.add("§8 Обратный урон 10%");
                par2List.add("§8 Дополнительный урон 5%");
                break;

            case 5:
                par2List.add("§8Эффекты руны:");
                par2List.add("§8 Иммунитет к горению");
                par2List.add("§8 Иммунитет к шоку 10%");
                par2List.add("§8 Если заряд брони меньше 1000L");
                par2List.add("§8 шанс выжить 20%");
        }
    }

    public int getSlot()
    {
        return 1;
    }

    @SideOnly(Side.CLIENT)
    public BaseItemModel getItemModel(int Damage)
    {
        return null;
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

    public int getCalculateProcDamage(ItemStack a, int b)
    {
        return a.getItemDamage() == 1 ? (int)((float)b - (float)b * 10.0F / 100.0F) : (a.getItemDamage() == 2 ? (int)((float)b - (float)b * 10.0F / 100.0F) : b);
    }

    public int getCalculateAttackDamage(ItemStack b, int a)
    {
        return a + a * this.getProcofDamage(b.getItemDamage()) / 100;
    }

    public int getProcofDamage(int damage)
    {
        Minecraft mc = Minecraft.getMinecraft();
        byte d = 0;

        switch (damage)
        {
            case 1:
                d = 15;
                break;

            case 2:
                if (mc.thePlayer.isInWater())
                {
                    d = 100;
                }
                else
                {
                    d = 0;
                }

                break;

            case 3:
                d = 10;
                break;

            case 4:
                d = 5;
        }

        return d;
    }

    public int get1(int damage)
    {
        byte d = 0;

        switch (damage)
        {
            case 0:
                d = 10;

            case 1:
            case 2:
            default:
                break;

            case 3:
                d = 5;
                break;

            case 4:
                d = 10;
        }

        return d;
    }

    public int getDamage(ItemStack it, int damage_, EntityPlayer player, Entity attacker)
    {
        int d = it.getItemDamage();

        if (d == 0 && d == 3 && d == 4)
        {
            d = this.get1(it.getItemDamage());

            if (attacker != null && attacker instanceof EntityPlayer && d > 0)
            {
                attacker.attackEntityFrom(DamageSource.causePlayerDamage(player), damage_ * d / 100);
            }

            return damage_;
        }
        else
        {
            return (int)((float)damage_ - (float)damage_ * 1.0F / 100.0F);
        }
    }

    public boolean isCanceledShok(ItemStack it)
    {
        return it.getItemDamage() == 3 && Utils.getRandomProc(5.0D) || it.getItemDamage() == 5 && Utils.getRandomProc(10.0D);
    }

    public boolean isAlived(ItemStack i)
    {
        return Utils.getRandomProc(20.0D);
    }
}
