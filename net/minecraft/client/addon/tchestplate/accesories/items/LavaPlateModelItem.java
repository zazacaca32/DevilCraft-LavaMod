package net.minecraft.client.addon.tchestplate.accesories.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.addon.tchestplate.ClientProxy;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.common.ISpecialArmor.ArmorProperties;
import org.lwjgl.opengl.GL11;

public abstract class LavaPlateModelItem extends ItemArmor implements ISpecialArmor
{
    public LavaPlateModelItem(int par1, EnumArmorMaterial par2EnumArmorMaterial, int par3, int par4)
    {
        super(par1, par2EnumArmorMaterial, par3, par4);
        this.setCreativeTab(CreativeTabs.tabCombat);
        this.setMaxDamage(5000);
        this.setMaxStackSize(1);
    }

    public ArmorProperties getProperties(EntityLiving player, ItemStack armor, DamageSource source, double damage, int slot)
    {
        return new ArmorProperties(0, 0.0D, 0);
    }

    @SideOnly(Side.CLIENT)
    public void drawTexturedModalRect(int par1, int par2, int par3, int par4, int par5, int par6)
    {
        boolean zLevel = true;
        float f = 0.00390625F;
        float f2 = 0.00390625F;
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV((double)(par1 + 0), (double)(par2 + par6), -300.0D, (double)((float)(par3 + 0) * 0.00390625F), (double)((float)(par4 + par6) * 0.00390625F));
        tessellator.addVertexWithUV((double)(par1 + par5), (double)(par2 + par6), -300.0D, (double)((float)(par3 + par5) * 0.00390625F), (double)((float)(par4 + par6) * 0.00390625F));
        tessellator.addVertexWithUV((double)(par1 + par5), (double)(par2 + 0), -300.0D, (double)((float)(par3 + par5) * 0.00390625F), (double)((float)(par4 + 0) * 0.00390625F));
        tessellator.addVertexWithUV((double)(par1 + 0), (double)(par2 + 0), -300.0D, (double)((float)(par3 + 0) * 0.00390625F), (double)((float)(par4 + 0) * 0.00390625F));
        tessellator.draw();
    }

    @SideOnly(Side.CLIENT)
    protected void renderInventorySlot(int x, int y, float par4, int slot)
    {
        ItemStack itemstack = ClientProxy.mc.thePlayer.inventory.armorItemInSlot(slot);

        if (itemstack != null)
        {
            float f1 = (float)itemstack.animationsToGo - par4;

            if (f1 > 0.0F)
            {
                GL11.glPushMatrix();
                float f2 = 1.0F + f1 / 5.0F;
                GL11.glTranslatef((float)(x + 8), (float)(y + 12), 0.0F);
                GL11.glScalef(1.0F / f2, (f2 + 1.0F) / 2.0F, 1.0F);
                GL11.glTranslatef((float)(-(x + 8)), (float)(-(y + 12)), 0.0F);
            }

            ClientProxy.itemRenderer.renderItemAndEffectIntoGUI(ClientProxy.mc.fontRenderer, ClientProxy.mc.renderEngine, itemstack, x, y);

            if (f1 > 0.0F)
            {
                GL11.glPopMatrix();
            }

            ClientProxy.itemRenderer.renderItemOverlayIntoGUI(ClientProxy.mc.fontRenderer, ClientProxy.mc.renderEngine, itemstack, x, y);
        }
    }

    @SideOnly(Side.CLIENT)
    public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot)
    {
        ScaledResolution scaledresolution = new ScaledResolution(ClientProxy.mc.gameSettings, ClientProxy.mc.displayWidth, ClientProxy.mc.displayHeight);
        int k = scaledresolution.getScaledWidth();
        int l = scaledresolution.getScaledHeight();
        GL11.glEnable('耺');
        RenderHelper.enableGUIStandardItemLighting();
        int y = l - 24;
        y -= 18 * slot;
        this.renderInventorySlot(k - 24, y, 0.0F, slot);
        RenderHelper.disableStandardItemLighting();
        GL11.glEnable('耺');
        boolean car = false;
        ClientProxy.mc.fontRenderer.drawStringWithShadow(String.valueOf(0), k - 52, y + 4, 10092288);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        ClientProxy.mc.renderEngine.bindTexture("/gui/icons.png");
        return 0;
    }

    public void damageArmor(EntityLiving entity, ItemStack stack, DamageSource source, int damage, int slot)
    {
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
