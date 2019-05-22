package net.minecraft.client.addon.tchestplate;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.addon.tchestplate.accesories.models.ModelBant;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;

public class RenderPlayerLava extends RenderPlayer
{
    public static ModelBant b = new ModelBant();

    public RenderPlayerLava(RenderPlayer player)
    {
    }

    @SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel(EntityLiving entityLiving, ItemStack itemstack, int armorSlot)
    {
        ModelBiped armorModel = (ModelBiped)ClientProxy.armorModels.get(this);

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
        }

        return armorModel;
    }
}
