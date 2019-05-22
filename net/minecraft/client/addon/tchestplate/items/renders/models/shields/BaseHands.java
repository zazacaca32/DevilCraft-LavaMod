package net.minecraft.client.addon.tchestplate.items.renders.models.shields;

import java.lang.reflect.Field;
import java.util.LinkedList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.addon.tchestplate.items.renders.models.BaseItemModel;
import net.minecraft.client.addon.tchestplate.items.renders.models.BodyPart;
import net.minecraft.client.addon.tchestplate.player.ExtendedPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.src.ModelPlayer;
import org.lwjgl.opengl.GL11;

public abstract class BaseHands extends BaseItemModel
{
    BodyPart[] LeftHand;

    protected final BodyPart[] getModelsLeftHand()
    {
        if (this.LeftHand == null)
        {
            try
            {
                LinkedList var61 = new LinkedList();
                Field[] fields = this.getClass().getDeclaredFields();

                for (int i = 0; i < fields.length; ++i)
                {
                    Field f = fields[i];
                    f.setAccessible(true);
                    Object model;

                    if (BodyPart.class.isAssignableFrom(f.getType()) && (model = f.get(this)) != null)
                    {
                        var61.add((BodyPart)model);
                    }
                }

                this.LeftHand = (BodyPart[])((BodyPart[])((BodyPart[])((BodyPart[])var61.toArray((BodyPart[])(new BodyPart[0])))));
            }
            catch (Exception var6)
            {
                var6.printStackTrace();
                this.LeftHand = new BodyPart[0];
            }
        }

        return this.LeftHand;
    }

    protected void setRotation(BodyPart model, float x, float y, float z)
    {
        model.origRotationX = x;
        model.origRotationY = y;
        model.origRotationZ = z;
    }

    public BaseHands(BaseItemModel.ModelType type)
    {
        super(type);
    }

    public void render(ModelPlayer modelPlayer, EntityPlayer player, float scale, float time, ExtendedPlayer pi, float var2, float var3, float var5, float var6)
    {
        ++super.renderCalled;
        BodyPart[] models = this.getModelsLeftHand();
        Minecraft.getMinecraft().renderEngine.bindTexture(super.tex);
        GL11.glPushMatrix();
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glPushMatrix();
        this.setRotationAngles(modelPlayer, var2, var3, time, var5, var6, scale);

        for (int i = 0; i < models.length; ++i)
        {
            models[i].render(scale);
        }

        GL11.glPopMatrix();
        GL11.glDisable(GL11.GL_CULL_FACE);
        GL11.glPopMatrix();
    }

    public void setRotationAngles(ModelPlayer modelPlayer, float par1, float par2, float par3, float par4, float par5, float par6)
    {
    }

    public void renderAsItem(float scale, float time)
    {
        BodyPart[] models = this.getModelsLeftHand();
        Minecraft.getMinecraft().renderEngine.bindTexture(super.tex);
        GL11.glPushMatrix();
        GL11.glTranslatef(-0.08F, -0.35F, 0.7F);
        GL11.glRotatef(25.0F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-55.0F, 1.0F, 0.0F, 0.0F);
        GL11.glRotatef(3.0F, 0.0F, 0.0F, 1.0F);
        GL11.glRotatef(20.0F, 0.0F, 1.0F, 1.0F);

        for (int i = 0; i < models.length; ++i)
        {
            models[i].render(scale);
        }

        GL11.glPopMatrix();
    }
}
