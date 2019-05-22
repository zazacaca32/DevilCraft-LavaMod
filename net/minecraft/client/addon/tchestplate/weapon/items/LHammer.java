package net.minecraft.client.addon.tchestplate.weapon.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;

public class LHammer extends BaseHammerWeapon
{
    public LHammer(int par1, EnumToolMaterial par2EnumToolMaterial)
    {
        super(par1, par2EnumToolMaterial);
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        super.registerIcons(par1IconRegister);
        super.itemIcon = par1IconRegister.registerIcon("LavaChestPlate:lhammer");
    }

    public int getDamageVsEntity(Entity par1Entity, ItemStack itemStack)
    {
        return 1;
    }

    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
    {
        if (!player.worldObj.isRemote)
        {
            if (super.maxCharge.intValue() > this.getCharge(stack).intValue())
            {
                return true;
            }

            if (entity.attackEntityFrom(DamageSource.causePlayerDamage(player), 1000))
            {
                stack.damageItem(20, player);
                entity.worldObj.playSoundEffect(entity.posX, entity.posY, entity.posZ, "tchestplate.shock1", 2.0F, 1.1F);
            }

            this.discharge(stack, 30);
        }

        return true;
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List, boolean par4)
    {
        super.addInformation(par1ItemStack, par2EntityPlayer, par2List, par4);
        par2List.add("§7 Наносит сокрушающий удар");
        par2List.add("§8 Урон от которого в 1000!");
        par2List.add("§8 Регенирируется за 60 секунд");
        par2List.add("§8 Бонус:  В руке за 30 секунд");
    }
}
