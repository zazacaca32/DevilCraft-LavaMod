package net.minecraft.client.addon.tchestplate.items.renders.models;

import java.lang.reflect.Field;
import java.util.LinkedList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.addon.tchestplate.player.ExtendedPlayer;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.src.ModelPlayer;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

public abstract class RightHand extends BaseItemModel
{
    BodyPart[] RightHand;

    protected final BodyPart[] getModelsLeftHand()
    {
        if (this.RightHand == null)
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

                this.RightHand = (BodyPart[])((BodyPart[])((BodyPart[])((BodyPart[])((BodyPart[])((BodyPart[])var61.toArray(new BodyPart[0]))))));
            }
            catch (Exception var6)
            {
                var6.printStackTrace();
                this.RightHand = new BodyPart[0];
            }
        }

        return this.RightHand;
    }

    protected void setRotation(BodyPart bodyPart, float origRotationX, float origRotationY, float origRotationZ)
    {
        bodyPart.origRotationX = origRotationX;
        bodyPart.origRotationY = origRotationY;
        bodyPart.origRotationZ = origRotationZ;
    }

    protected void setRotation(ModelRenderer modelRenderer, float field_78795_f, float field_78796_g, float field_78808_h)
    {
        modelRenderer.rotateAngleX = field_78795_f;
        modelRenderer.rotateAngleY = field_78796_g;
        modelRenderer.rotateAngleZ = field_78808_h;
    }

    public RightHand(BaseItemModel.ModelType type)
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

        for (int var161 = 0; var161 < models.length; ++var161)
        {
            models[var161].render(scale);
        }

        if (item.hasEffect())
        {
            GL11.glDepthFunc(GL11.GL_EQUAL);
            GL11.glDisable(GL11.GL_LIGHTING);
            Minecraft.getMinecraft().renderEngine.bindTexture("%blur%/misc/glint.png");
            GL11.glBlendFunc(GL11.GL_SRC_COLOR, GL11.GL_ONE);
            float var16 = 0.7F;
            GL11.glColor4f(0.35F, 0.175F, 0.56F, 1.0F);
            GL11.glMatrixMode(GL11.GL_TEXTURE);
            GL11.glPushMatrix();
            float f8 = 0.4125F;
            GL11.glScalef(f8, f8, f8);
            float f9 = (float)(Minecraft.getSystemTime() % 3000L) / 3000.0F * 2.0F;
            GL11.glTranslatef(f9, 0.0F, 0.0F);
            GL11.glRotatef(-10.0F, 0.0F, 0.0F, 1.0F);
            GL11.glMatrixMode(GL11.GL_MODELVIEW);
            int i;

            for (i = 0; i < models.length; ++i)
            {
                models[i].render(scale);
            }

            GL11.glMatrixMode(GL11.GL_TEXTURE);
            GL11.glPopMatrix();
            f8 = 0.9125F;
            GL11.glPushMatrix();
            GL11.glScalef(f8, f8, f8);
            f9 = (float)(Minecraft.getSystemTime() % 4873L) / 4873.0F * 1.2F;
            GL11.glTranslatef(-f9, 0.0F, 0.0F);
            GL11.glRotatef(-10.0F, 0.0F, 0.0F, 1.0F);
            GL11.glMatrixMode(GL11.GL_MODELVIEW);

            for (i = 0; i < models.length; ++i)
            {
                models[i].render(scale);
            }

            GL11.glMatrixMode(GL11.GL_TEXTURE);
            GL11.glPopMatrix();
            GL11.glMatrixMode(GL11.GL_MODELVIEW);
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glDepthFunc(GL11.GL_LEQUAL);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        }

        GL11.glPopMatrix();
        GL11.glDisable(GL11.GL_CULL_FACE);
        GL11.glPopMatrix();
    }

    public void setRotationAngles(ModelPlayer modelPlayer, float par1, float par2, float par3, float par4, float par5, float par6)
    {
        float HeadrotateAngleY = par4 / (180F / (float)Math.PI);
        float HeadrotateAngleX = par5 / (180F / (float)Math.PI);
        float RightArmrotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 2.0F * par2 * 0.5F;
        float RightArmrotateAngleZ = 0.0F;
        int var21;

        for (var21 = 0; var21 < this.RightHand.length; ++var21)
        {
            this.RightHand[var21].rotationX = RightArmrotateAngleX;
            this.RightHand[var21].rotationZ = RightArmrotateAngleZ;
        }

        if (modelPlayer.isRiding)
        {
            RightArmrotateAngleX -= ((float)Math.PI / 5F);

            for (var21 = 0; var21 < this.RightHand.length; ++var21)
            {
                this.RightHand[var21].rotationX = RightArmrotateAngleX;
            }
        }

        if (modelPlayer.heldItemRight != 0)
        {
            RightArmrotateAngleX = RightArmrotateAngleX * 0.5F - ((float)Math.PI / 10F) * (float)modelPlayer.heldItemRight;

            for (var21 = 0; var21 < this.RightHand.length; ++var21)
            {
                this.RightHand[var21].rotationX = RightArmrotateAngleX;
            }
        }

        float var211 = 0.0F;
        int var22;

        for (var22 = 0; var22 < this.RightHand.length; ++var22)
        {
            this.RightHand[var22].rotationY = var211;
        }

        float f8;
        float var20;

        if (modelPlayer.onGround > -9990.0F)
        {
            var20 = modelPlayer.onGround;
            f8 = MathHelper.sin(MathHelper.sqrt_float(var20) * (float)Math.PI * 2.0F) * 0.2F;
            float var221 = MathHelper.sin(f8) * 5.0F;
            float RightArmrotationPointX = -MathHelper.cos(f8) * 5.0F;
            var211 += f8;

            for (int var231 = 0; var231 < this.RightHand.length; ++var231)
            {
                this.RightHand[var231].rotationPointZ = var221;
                this.RightHand[var231].rotationPointX = RightArmrotationPointX;
                this.RightHand[var231].rotationY = var211;
            }

            var20 = 1.0F - modelPlayer.onGround;
            var20 *= var20;
            var20 *= var20;
            var20 = 1.0F - var20;
            float var231 = MathHelper.sin(var20 * (float)Math.PI);
            float var23 = MathHelper.sin(modelPlayer.onGround * (float)Math.PI) * -(HeadrotateAngleX - 0.7F) * 0.75F;
            RightArmrotateAngleX -= (float)((double)var231 * 1.2D + (double)var23);
            var211 += f8 * 2.0F;
            RightArmrotateAngleZ = MathHelper.sin(modelPlayer.onGround * (float)Math.PI) * -0.4F;

            for (int i2 = 0; i2 < this.RightHand.length; ++i2)
            {
                this.RightHand[i2].rotationX = RightArmrotateAngleX;
                this.RightHand[i2].rotationY = var211;
                this.RightHand[i2].rotationZ = RightArmrotateAngleZ;
            }
        }

        if (modelPlayer.isSneak)
        {
            RightArmrotateAngleX += 0.4F;

            for (var22 = 0; var22 < this.RightHand.length; ++var22)
            {
                this.RightHand[var22].rotationX = RightArmrotateAngleX;
            }
        }

        RightArmrotateAngleZ += MathHelper.cos(par3 * 0.09F) * 0.05F + 0.05F;
        RightArmrotateAngleX += MathHelper.sin(par3 * 0.067F) * 0.05F;

        for (var22 = 0; var22 < this.RightHand.length; ++var22)
        {
            this.RightHand[var22].rotationZ = RightArmrotateAngleZ;
            this.RightHand[var22].rotationX = RightArmrotateAngleX;
        }

        if (modelPlayer.aimedBow)
        {
            var20 = 0.0F;
            f8 = 0.0F;
            RightArmrotateAngleZ = 0.0F;
            var211 = -0.1F + HeadrotateAngleY;
            int var221;

            for (var221 = 0; var221 < this.RightHand.length; ++var221)
            {
                this.RightHand[var221].rotationZ = RightArmrotateAngleZ;
                this.RightHand[var221].rotationY = var211;
            }

            RightArmrotateAngleX = -((float)Math.PI / 2F) + HeadrotateAngleX;

            for (var221 = 0; var221 < this.RightHand.length; ++var221)
            {
                this.RightHand[var221].rotationX = RightArmrotateAngleX;
            }

            RightArmrotateAngleX -= 0.0F;

            for (var221 = 0; var221 < this.RightHand.length; ++var221)
            {
                this.RightHand[var221].rotationX = RightArmrotateAngleX;
            }

            RightArmrotateAngleZ += MathHelper.cos(par3 * 0.09F) * 0.05F + 0.05F;
            RightArmrotateAngleX += MathHelper.sin(par3 * 0.067F) * 0.05F;

            for (var221 = 0; var221 < this.RightHand.length; ++var221)
            {
                this.RightHand[var221].rotationZ = RightArmrotateAngleZ;
                this.RightHand[var221].rotationX = RightArmrotateAngleX;
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
