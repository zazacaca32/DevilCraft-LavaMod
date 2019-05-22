package net.minecraft.client.addon.tchestplate.items.renders.models;

import java.lang.reflect.Field;
import java.util.LinkedList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.addon.tchestplate.player.ExtendedPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.src.ModelPlayer;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

public abstract class LeftHand extends BaseItemModel
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

                    if (BodyPart.class.isAssignableFrom(f.getType()))
                    {
                        Object model = f.get(this);

                        if (model != null)
                        {
                            var61.add(model);
                        }
                    }
                }

                this.LeftHand = (BodyPart[])((BodyPart[])((BodyPart[])((BodyPart[])((BodyPart[])((BodyPart[])var61.toArray(new BodyPart[0]))))));
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

    public LeftHand(BaseItemModel.ModelType type)
    {
        super(type);
    }

    public void render(ModelPlayer modelPlayer, EntityPlayer player, float scale, float time, ExtendedPlayer pi, float var2, float var3, float var5, float var6, ItemStack item)
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
        float HeadrotateAngleY = par4 / (180F / (float)Math.PI);
        float HeadrotateAngleX = par5 / (180F / (float)Math.PI);
        float LeftArmrotateAngleX = MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.5F;
        float LeftArmrotateAngleZ = 0.0F;
        int var20;

        for (var20 = 0; var20 < this.LeftHand.length; ++var20)
        {
            this.LeftHand[var20].rotationX = LeftArmrotateAngleX;
            this.LeftHand[var20].rotationZ = LeftArmrotateAngleZ;
        }

        if (super.isRiding)
        {
            LeftArmrotateAngleX -= ((float)Math.PI / 5F);

            for (var20 = 0; var20 < this.LeftHand.length; ++var20)
            {
                this.LeftHand[var20].rotationX = LeftArmrotateAngleX;
            }
        }

        if (modelPlayer.heldItemLeft != 0)
        {
            LeftArmrotateAngleX = LeftArmrotateAngleX * 0.5F - ((float)Math.PI / 10F) * (float)modelPlayer.heldItemLeft;

            for (var20 = 0; var20 < this.LeftHand.length; ++var20)
            {
                this.LeftHand[var20].rotationX = LeftArmrotateAngleX;
            }
        }

        float var19 = 0.0F;
        int var21;

        for (var21 = 0; var21 < this.LeftHand.length; ++var21)
        {
            this.LeftHand[var21].rotationY = var19;
        }

        float f7;
        float var18;

        if (modelPlayer.onGround > -9990.0F)
        {
            var18 = modelPlayer.onGround;
            f7 = MathHelper.sin(MathHelper.sqrt_float(var18) * (float)Math.PI * 2.0F) * 0.2F;
            float var201 = -MathHelper.sin(f7) * 5.0F;
            float LeftArmrotationPointX = MathHelper.cos(f7) * 5.0F;
            var19 += f7;
            LeftArmrotateAngleX += f7;

            for (int i2 = 0; i2 < this.LeftHand.length; ++i2)
            {
                this.LeftHand[i2].rotationPointZ = var201;
                this.LeftHand[i2].rotationPointX = LeftArmrotationPointX;
                this.LeftHand[i2].rotationY = var19;
                this.LeftHand[i2].rotationX = LeftArmrotateAngleX;
            }
        }

        if (modelPlayer.isSneak)
        {
            LeftArmrotateAngleX += 0.4F;

            for (var21 = 0; var21 < this.LeftHand.length; ++var21)
            {
                this.LeftHand[var21].rotationX = LeftArmrotateAngleX;
            }
        }

        LeftArmrotateAngleZ -= MathHelper.cos(par3 * 0.09F) * 0.05F + 0.05F;
        LeftArmrotateAngleX -= MathHelper.sin(par3 * 0.067F) * 0.05F;

        for (var21 = 0; var21 < this.LeftHand.length; ++var21)
        {
            this.LeftHand[var21].rotationZ = LeftArmrotateAngleZ;
            this.LeftHand[var21].rotationX = LeftArmrotateAngleX;
        }

        if (modelPlayer.aimedBow)
        {
            var18 = 0.0F;
            f7 = 0.0F;
            LeftArmrotateAngleZ = 0.0F;
            var19 = 0.1F + HeadrotateAngleY + 0.4F;
            int var201;

            for (var201 = 0; var201 < this.LeftHand.length; ++var201)
            {
                this.LeftHand[var201].rotationZ = LeftArmrotateAngleZ;
                this.LeftHand[var201].rotationY = var19;
            }

            LeftArmrotateAngleX = -((float)Math.PI / 2F) + HeadrotateAngleX;

            for (var201 = 0; var201 < this.LeftHand.length; ++var201)
            {
                this.LeftHand[var201].rotationX = LeftArmrotateAngleX;
            }

            LeftArmrotateAngleX -= 0.0F;

            for (var201 = 0; var201 < this.LeftHand.length; ++var201)
            {
                this.LeftHand[var201].rotationX = LeftArmrotateAngleX;
            }

            LeftArmrotateAngleZ -= MathHelper.cos(par3 * 0.09F) * 0.05F + 0.05F;
            LeftArmrotateAngleX -= MathHelper.sin(par3 * 0.067F) * 0.05F;

            for (var201 = 0; var201 < this.LeftHand.length; ++var201)
            {
                this.LeftHand[var201].rotationZ = LeftArmrotateAngleZ;
                this.LeftHand[var201].rotationX = LeftArmrotateAngleX;
            }
        }
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
