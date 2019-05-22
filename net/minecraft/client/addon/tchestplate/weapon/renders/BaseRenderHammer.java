package net.minecraft.client.addon.tchestplate.weapon.renders;

import net.minecraft.client.Minecraft;
import net.minecraft.client.addon.tchestplate.overlaygui.ArmorExtGui;
import net.minecraft.client.addon.tchestplate.weapon.items.BaseHammerWeapon;
import net.minecraft.client.addon.tchestplate.weapon.renders.models.BaseModelHammer;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;
import org.lwjgl.opengl.GL11;

public class BaseRenderHammer implements IItemRenderer
{
    protected BaseModelHammer baseModel;
    String texture;
    byte TypeModel = 0;
    private static float oo = 0.0F;

    public BaseRenderHammer(BaseModelHammer baseModel, String texture, byte TypeModel)
    {
        this.baseModel = baseModel;
        this.texture = texture;
        this.TypeModel = TypeModel;
    }

    private void renderItemShadowSword(ItemRenderType type, ItemStack item, Object[] data)
    {
        GL11.glPushMatrix();
        GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
        GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
        GL11.glRotatef(180.0F, 1.0F, 1.0F, 0.0F);
        GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
        GL11.glRotatef(-45.0F, 0.0F, 0.0F, 1.0F);
        GL11.glTranslatef(-1.45F, 0.13F, -0.03F);

        switch (BaseRenderHammer.NamelessClass923090959.$SwitchMap$net$minecraftforge$client$ItemRenderType[type.ordinal()])
        {
            case 1:
                GL11.glTranslatef(0.8F, -0.19F, 0.0F);
                break;

            case 2:
                GL11.glTranslatef(0.7F, -0.1F, 0.2F);
        }

        float scale = 0.5F;
        GL11.glScalef(scale, scale, scale);
        Minecraft.getMinecraft().renderEngine.bindTexture(this.texture);
        this.baseModel.render((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
        GL11.glPopMatrix();
    }

    public void renderItemTypeLSwordPredator(ItemRenderType type, ItemStack item, Object... data)
    {
        GL11.glPushMatrix();
        GL11.glRotatef(182.0F, 1.0F, 0.0F, 0.0F);
        GL11.glRotatef(185.0F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(306.0F, 0.0F, 0.0F, 1.0F);
        GL11.glTranslatef(-0.92F, -1.05F, -0.07F);
        Entity ent = null;

        if (((Entity)data[1]).isInvisible())
        {
            Minecraft.getMinecraft().renderEngine.bindTexture("/mods/LavaChestPlate/textures/items/renders/predatorBlack.png");
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
        }
        else
        {
            Minecraft.getMinecraft().renderEngine.bindTexture("/mods/LavaChestPlate/textures/items/renders/predatorSword.png");
        }

        switch (BaseRenderHammer.NamelessClass923090959.$SwitchMap$net$minecraftforge$client$ItemRenderType[type.ordinal()])
        {
            case 1:
                if (data[1] != null)
                {
                    ent = (Entity)data[1];
                }

                GL11.glTranslatef(0.7F, 0.08F, 0.0F);
                float scale = 0.5F;
                GL11.glScalef(scale, scale, scale);
                break;

            case 2:
                GL11.glRotatef(-145.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(65.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(-35.0F, 0.0F, 0.0F, 1.0F);
                GL11.glTranslatef(-0.9F, -0.6F, -0.7F);
                GL11.glScalef(0.5F, 0.7F, 0.5F);
        }

        this.baseModel.render(ent, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);

        if (((Entity)data[1]).isInvisible())
        {
            GL11.glDisable(GL11.GL_BLEND);
        }

        GL11.glPopMatrix();
    }

    public void renderItemTypeGucci(ItemRenderType type, ItemStack item, Object... data)
    {
        GL11.glPushMatrix();
        GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
        GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
        GL11.glRotatef(180.0F, 1.0F, 1.0F, 0.0F);
        GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
        GL11.glRotatef(-45.0F, 0.0F, 0.0F, 1.0F);
        GL11.glTranslatef(-1.45F, 0.75F, -0.08F);
        boolean isFirstPerson = false;

        if (data[1] != null & data[1] instanceof EntityPlayer)
        {
            if (data[1] == Minecraft.getMinecraft().renderViewEntity && Minecraft.getMinecraft().gameSettings.thirdPersonView == 0 && (!(Minecraft.getMinecraft().currentScreen instanceof GuiInventory) && !(Minecraft.getMinecraft().currentScreen instanceof GuiContainerCreative) && !(Minecraft.getMinecraft().currentScreen instanceof ArmorExtGui) || RenderManager.instance.playerViewY != 180.0F))
            {
                isFirstPerson = true;
                GL11.glTranslatef(0.7F, -0.1F, 0.2F);
            }
            else
            {
                GL11.glTranslatef(0.8F, -0.14F, 0.0F);
            }
        }
        else
        {
            GL11.glTranslatef(0.65F, -1.2F, 0.0F);
        }

        float scale = 0.3F;
        GL11.glScalef(scale, scale, scale);
        Minecraft.getMinecraft().renderEngine.bindTexture(this.texture);
        this.baseModel.render((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
        GL11.glPopMatrix();
    }

    public void renderItemTypeGucciAxe(ItemRenderType type, ItemStack item, Object... data)
    {
        GL11.glPushMatrix();
        GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
        GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
        GL11.glRotatef(180.0F, 1.0F, 1.0F, 0.0F);
        GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
        GL11.glRotatef(-50.0F, -0.1F, 0.0F, 1.0F);
        GL11.glTranslatef(-1.53F, 1.1F, -0.08F);
        boolean isFirstPerson = false;

        if (data[1] != null & data[1] instanceof EntityPlayer)
        {
            if (data[1] == Minecraft.getMinecraft().renderViewEntity && Minecraft.getMinecraft().gameSettings.thirdPersonView == 0 && (!(Minecraft.getMinecraft().currentScreen instanceof GuiInventory) && !(Minecraft.getMinecraft().currentScreen instanceof GuiContainerCreative) && !(Minecraft.getMinecraft().currentScreen instanceof ArmorExtGui) || RenderManager.instance.playerViewY != 180.0F))
            {
                isFirstPerson = true;
                GL11.glTranslatef(0.7F, -0.1F, 0.2F);
            }
            else
            {
                GL11.glTranslatef(0.8F, -0.14F, 0.0F);
            }
        }
        else
        {
            GL11.glTranslatef(0.65F, -1.2F, 0.0F);
        }
    }

    public void renderItemNoobSword(ItemRenderType type, ItemStack item, Object... data)
    {
        GL11.glPushMatrix();
        GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
        GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
        GL11.glRotatef(180.0F, 1.0F, 1.0F, 0.0F);
        GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
        GL11.glRotatef(-45.0F, 0.0F, 0.0F, 1.0F);
        GL11.glTranslatef(-1.45F, 0.08F, -0.03F);

        switch (BaseRenderHammer.NamelessClass923090959.$SwitchMap$net$minecraftforge$client$ItemRenderType[type.ordinal()])
        {
            case 1:
                GL11.glTranslatef(0.8F, -0.14F, 0.0F);
                break;

            case 2:
                GL11.glTranslatef(0.7F, -0.1F, 0.2F);
        }

        float scale = 0.4F;
        GL11.glScalef(scale, scale, scale);
        Minecraft.getMinecraft().renderEngine.bindTexture(this.texture);
        this.baseModel.render((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
        GL11.glPopMatrix();
    }

    public void renderItemTypeLStaffFrize(ItemRenderType type, ItemStack item, Object... data)
    {
        GL11.glPushMatrix();
        GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
        GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
        GL11.glRotatef(180.0F, 1.0F, 1.0F, 0.0F);
        GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
        GL11.glRotatef(-45.0F, 0.0F, 0.0F, 1.0F);
        GL11.glTranslatef(-1.45F, 0.08F, -0.03F);

        switch (BaseRenderHammer.NamelessClass923090959.$SwitchMap$net$minecraftforge$client$ItemRenderType[type.ordinal()])
        {
            case 1:
                GL11.glTranslatef(0.7F, 0.08F, 0.0F);
                break;

            case 2:
                GL11.glTranslatef(0.7F, -0.1F, 0.2F);
        }

        float scale = 0.5F;
        GL11.glScalef(scale, scale, scale);
        Minecraft.getMinecraft().renderEngine.bindTexture(this.texture);
        this.baseModel.render((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
        GL11.glPopMatrix();
    }

    public void renderItemTypeLvajra(ItemRenderType type, ItemStack item, Object... data)
    {
        GL11.glPushMatrix();
        GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
        GL11.glRotatef(185.0F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(305.0F, 0.0F, 0.0F, 1.0F);
        GL11.glTranslatef(-0.92F, -1.06F, -0.07F);

        switch (BaseRenderHammer.NamelessClass923090959.$SwitchMap$net$minecraftforge$client$ItemRenderType[type.ordinal()])
        {
            case 1:
                GL11.glTranslatef(0.7F, 0.08F, 0.0F);
                float scale = 0.5F;
                GL11.glScalef(scale, scale, scale);
                break;

            case 2:
                GL11.glRotatef(-145.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(65.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(-35.0F, 0.0F, 0.0F, 1.0F);
                GL11.glTranslatef(-0.9F, -0.6F, -0.7F);
                GL11.glScalef(0.5F, 0.7F, 0.5F);
        }

        Minecraft.getMinecraft().renderEngine.bindTexture(this.texture);
        this.baseModel.render((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
        GL11.glPopMatrix();
    }

    public boolean handleRenderType(ItemStack item, ItemRenderType type)
    {
        return type == ItemRenderType.EQUIPPED || type == ItemRenderType.EQUIPPED_FIRST_PERSON;
    }

    public void renderItem(ItemRenderType type, ItemStack item, Object... data)
    {
        if (this.TypeModel == 0)
        {
            this.renderItemType(type, item, data);
        }
        else if (this.TypeModel == 1)
        {
            this.renderItemTypeLvajra(type, item, data);
        }
        else if (this.TypeModel == 2)
        {
            this.renderItemTypeLStaffFrize(type, item, data);
        }
        else if (this.TypeModel == 3)
        {
            this.renderItemNoobSword(type, item, data);
        }
        else if (this.TypeModel == 4)
        {
            this.renderItemShadowSword(type, item, data);
        }
        else if (this.TypeModel == 5)
        {
            this.renderItemTypeLSwordPredator(type, item, data);
        }
    }

    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper)
    {
        return false;
    }

    public void renderItemType(ItemRenderType type, ItemStack item, Object... data)
    {
        GL11.glPushMatrix();
        GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
        GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
        GL11.glRotatef(180.0F, 1.0F, 1.0F, 0.0F);
        GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
        GL11.glRotatef(-45.0F, 0.0F, 0.0F, 1.0F);
        GL11.glTranslatef(-1.45F, 0.08F, -0.03F);

        switch (BaseRenderHammer.NamelessClass923090959.$SwitchMap$net$minecraftforge$client$ItemRenderType[type.ordinal()])
        {
            case 1:
                GL11.glTranslatef(0.7F, 0.08F, 0.0F);
                break;

            case 2:
                GL11.glTranslatef(0.7F, -0.1F, 0.2F);
        }

        float scale = 0.6F;
        GL11.glScalef(scale, scale, scale);
        Minecraft.getMinecraft().renderEngine.bindTexture(this.texture);

        if (item.getItem() instanceof BaseHammerWeapon)
        {
            this.baseModel.enchant = ((BaseHammerWeapon)item.getItem()).getEnchantHammer(item);
        }

        this.baseModel.render((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
        GL11.glPopMatrix();
    }

    static class NamelessClass923090959
    {
        static final int[] $SwitchMap$net$minecraftforge$client$ItemRenderType = new int[ItemRenderType.values().length];

        static
        {
            try
            {
                $SwitchMap$net$minecraftforge$client$ItemRenderType[ItemRenderType.EQUIPPED.ordinal()] = 1;
            }
            catch (NoSuchFieldError var2)
            {
                ;
            }

            try
            {
                $SwitchMap$net$minecraftforge$client$ItemRenderType[ItemRenderType.EQUIPPED_FIRST_PERSON.ordinal()] = 2;
            }
            catch (NoSuchFieldError var1)
            {
                ;
            }
        }
    }
}
