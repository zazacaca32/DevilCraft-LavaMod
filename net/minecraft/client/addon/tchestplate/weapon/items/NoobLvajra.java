package net.minecraft.client.addon.tchestplate.weapon.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class NoobLvajra extends BaseHammerWeapon
{
    protected EnumToolMaterial toolMaterial;
    static long time = 0L;

    public NoobLvajra(int par1, EnumToolMaterial par2EnumToolMaterial)
    {
        super(par1, par2EnumToolMaterial);
        this.toolMaterial = par2EnumToolMaterial;
        super.maxStackSize = 1;
        this.setMaxDamage(par2EnumToolMaterial.getMaxUses());
        super.maxCharge = Integer.valueOf(5000);
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        super.registerIcons(par1IconRegister);
        super.itemIcon = par1IconRegister.registerIcon("LavaChestPlate:nooblvajra");
    }

    public float getStrVsBlock(ItemStack par1ItemStack, Block par2Block)
    {
        return this.getCharge(par1ItemStack).intValue() > 0 ? 20000.0F : 1.0F;
    }

    @SideOnly(Side.CLIENT)
    public EnumRarity getRarity(ItemStack var1)
    {
        return EnumRarity.epic;
    }

    public boolean onBlockDestroyed(ItemStack par1ItemStack, World par2World, int par3, int par4, int par5, int par6, EntityLiving par7EntityLiving)
    {
        this.discharge(par1ItemStack, 1);
        return false;
    }

    public int getDamageVsEntity(Entity par1Entity)
    {
        return 1;
    }

    public boolean canHarvestBlock(Block block)
    {
        return block != Block.bedrock;
    }

    public float getStrVsBlock(ItemStack stack, Block block, int meta)
    {
        return this.getStrVsBlock(stack, block);
    }

    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
    {
        if (!player.worldObj.isRemote)
        {
            entity.attackEntityFrom(DamageSource.causePlayerDamage(player), 32);
            return true;
        }
        else
        {
            return true;
        }
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List, boolean par4)
    {
        super.addInformation(par1ItemStack, par2EntityPlayer, par2List, par4);
    }

    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack par1ItemStack)
    {
        return false;
    }
}
