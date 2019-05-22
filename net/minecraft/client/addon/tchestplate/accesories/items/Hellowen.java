package net.minecraft.client.addon.tchestplate.accesories.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.addon.tchestplate.ClientProxy;
import net.minecraft.client.addon.tchestplate.LavaChestPlate;
import net.minecraft.client.addon.tchestplate.Utils;
import net.minecraft.client.addon.tchestplate.accesories.models.ModelBipedAccesories;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;

public class Hellowen extends ItemArmor
{
    @SideOnly(Side.CLIENT)
    private Icon[] icons;
    int count = 0;
    final int deltatexture;

    public Hellowen(int par1, EnumArmorMaterial par2EnumArmorMaterial, int par3, int par4, int count, int deltatexture)
    {
        super(par1, par2EnumArmorMaterial, par3, par4);
        this.deltatexture = deltatexture;
        this.count = count;
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
        this.setMaxStackSize(1);
        this.setCreativeTab(LavaChestPlate.tabArmor);
    }

    @SideOnly(Side.CLIENT)
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, int layer)
    {
        return ClientProxy.HellowenModels[stack.getItemDamage() + this.deltatexture].getTexture();
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List, boolean par4)
    {
        if (par1ItemStack.getItemDamage() + this.deltatexture < 4)
        {
            par2List.add("§8Хеллоуин 2016-2017");
        }
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.icons = new Icon[this.count];

        for (int i = 0; i < this.icons.length; ++i)
        {
            this.icons[i] = par1IconRegister.registerIcon("LavaChestPlate:" + this.getUnlocalizedName().substring(5) + i);
        }
    }

    @SideOnly(Side.CLIENT)
    public Icon getIconFromDamage(int par1)
    {
        return par1 >= this.icons.length ? this.icons[0] : this.icons[par1];
    }

    @SideOnly(Side.CLIENT)
    public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        for (int x = 0; x < this.count; ++x)
        {
            par3List.add(new ItemStack(this, 1, x));
        }
    }

    @SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel(EntityLiving entityLiving, ItemStack itemstack, int armorSlot)
    {
        ModelBipedAccesories armorModel = ClientProxy.HellowenModels[itemstack.getItemDamage() + this.deltatexture];

        if (armorModel != null)
        {
            armorModel.bipedHeadwear.showModel = false;
            armorModel.bipedHead.showModel = armorSlot == 0;
            armorModel.bipedBody.showModel = armorSlot == 1 || armorSlot == 2;
            armorModel.bipedRightArm.showModel = armorSlot == 1;
            armorModel.bipedLeftArm.showModel = armorSlot == 1;
            armorModel.bipedRightLeg.showModel = armorSlot == 2 || armorSlot == 3;
            armorModel.bipedLeftLeg.showModel = armorSlot == 2 || armorSlot == 3;
            armorModel.isSneak = entityLiving.isSneaking();
            armorModel.isRiding = entityLiving.isRiding();
            armorModel.isChild = entityLiving.isChild();
            armorModel.heldItemRight = 0;
            armorModel.aimedBow = false;
            EntityPlayer player = (EntityPlayer)entityLiving;
            ItemStack held_item = player.getCurrentItemOrArmor(0);

            if (held_item != null)
            {
                armorModel.heldItemRight = 1;

                if (player.getItemInUseCount() > 0)
                {
                    EnumAction enumaction = held_item.getItemUseAction();

                    if (enumaction == EnumAction.bow)
                    {
                        armorModel.aimedBow = true;
                    }
                    else if (enumaction == EnumAction.block)
                    {
                        armorModel.heldItemRight = 3;
                    }
                }
            }

            armorModel.invise = this.getInvise(Utils.getOrCreateNbtData(itemstack));
        }

        return armorModel;
    }

    public String getUnlocalizedName(ItemStack par1ItemStack)
    {
        int i = MathHelper.clamp_int(par1ItemStack.getItemDamage(), 0, 15);
        return super.getUnlocalizedName() + ".LavaChestPlate" + i;
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

    public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack)
    {
        return false;
    }

    public int getInvise(NBTTagCompound nbtData)
    {
        return nbtData.hasKey("inv") ? nbtData.getByte("inv") : -1;
    }
}
