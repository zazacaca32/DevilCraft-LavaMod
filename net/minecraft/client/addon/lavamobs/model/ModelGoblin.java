package net.minecraft.client.addon.lavamobs.model;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.addon.lavamobs.entity.EntityMobGoblin;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

@SideOnly(Side.CLIENT)
public class ModelGoblin extends ModelBase
{
    ModelRenderer head;
    ModelRenderer head2;
    ModelRenderer head3;
    ModelRenderer head4;
    ModelRenderer head5;
    ModelRenderer head6;
    ModelRenderer head7;
    ModelRenderer head8;
    ModelRenderer head9;
    ModelRenderer body;
    ModelRenderer body1;
    ModelRenderer rightarm;
    ModelRenderer rightarm1;
    ModelRenderer rightarm2;
    ModelRenderer leftarm;
    ModelRenderer leftarm1;
    ModelRenderer rightleg;
    ModelRenderer rightleg1;
    ModelRenderer leftleg;
    ModelRenderer leftleg1;
    ModelRenderer leftarm2;
    ModelRenderer leftarm3;
    ModelRenderer leftarm4;
    ModelRenderer leftarm5;
    ModelRenderer leftarm6;
    ModelRenderer leftarm7;

    public ModelGoblin()
    {
        super.textureWidth = 64;
        super.textureHeight = 64;
        this.head = new ModelRenderer(this, 38, 7);
        this.head.addBox(-3.5F, -7.0F, -1.0F, 7, 7, 6);
        this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.head.setTextureSize(64, 64);
        this.head.mirror = true;
        this.setRotation(this.head, 0.0F, 0.0F, 0.0F);
        this.head2 = new ModelRenderer(this, 0, 0);
        this.head2.addBox(1.0F, -3.5F, -3.5F, 1, 1, 1);
        this.head2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.head2.setTextureSize(64, 64);
        this.head2.mirror = true;
        this.setRotation(this.head2, 0.0F, 0.0F, 0.0F);
        this.head.addChild(this.head2);
        this.head3 = new ModelRenderer(this, 56, 27);
        this.head3.addBox(-2.0F, -9.0F, 0.0F, 2, 3, 2);
        this.head3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.head3.setTextureSize(64, 64);
        this.head3.mirror = true;
        this.setRotation(this.head3, 0.0F, 0.0F, ((float)Math.PI / 5F));
        this.head.addChild(this.head3);
        this.head4 = new ModelRenderer(this, 56, 27);
        this.head4.addBox(0.0F, -9.0F, 0.0F, 2, 3, 2);
        this.head4.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.head4.setTextureSize(64, 64);
        this.head4.mirror = true;
        this.setRotation(this.head4, 0.0F, 0.0F, -((float)Math.PI / 5F));
        this.head.addChild(this.head4);
        this.head5 = new ModelRenderer(this, 60, 23);
        this.head5.addBox(-3.0F, -11.0F, 0.5F, 1, 3, 1);
        this.head5.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.head5.setTextureSize(64, 64);
        this.head5.mirror = true;
        this.setRotation(this.head5, 0.0F, 0.0F, -0.2268928F);
        this.head.addChild(this.head5);
        this.head6 = new ModelRenderer(this, 0, 0);
        this.head6.addBox(-2.0F, -3.0F, -3.5F, 1, 2, 1);
        this.head6.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.head6.setTextureSize(64, 64);
        this.head6.mirror = true;
        this.setRotation(this.head6, 0.0F, 0.0F, 0.0F);
        this.head.addChild(this.head6);
        this.head7 = new ModelRenderer(this, 60, 23);
        this.head7.addBox(2.0F, -11.0F, 0.5F, 1, 3, 1);
        this.head7.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.head7.setTextureSize(64, 64);
        this.head7.mirror = true;
        this.setRotation(this.head7, 0.0F, 0.0F, 0.2268928F);
        this.head.addChild(this.head7);
        this.head8 = new ModelRenderer(this, 44, 0);
        this.head8.addBox(-3.5F, -7.5F, -4.0F, 7, 4, 3);
        this.head8.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.head8.setTextureSize(64, 64);
        this.head8.mirror = true;
        this.setRotation(this.head8, 0.0F, 0.0F, 0.0F);
        this.head.addChild(this.head8);
        this.head9 = new ModelRenderer(this, 44, 0);
        this.head9.addBox(-3.5F, -2.0F, -4.0F, 7, 2, 3);
        this.head9.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.head9.setTextureSize(64, 64);
        this.head9.mirror = true;
        this.setRotation(this.head9, 0.0F, 0.0F, -0.0872665F);
        this.head.addChild(this.head9);
        this.body = new ModelRenderer(this, 0, 8);
        this.body.addBox(-4.0F, 0.0F, -3.0F, 8, 7, 6);
        this.body.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.body.setTextureSize(64, 64);
        this.body.mirror = true;
        this.setRotation(this.body, 0.0F, 0.0F, 0.0F);
        this.body1 = new ModelRenderer(this, 42, 12);
        this.body1.addBox(-2.5F, 5.0F, -2.0F, 5, 4, 4);
        this.body1.setRotationPoint(0.0F, 2.0F, 0.0F);
        this.body1.setTextureSize(64, 64);
        this.body1.mirror = true;
        this.setRotation(this.body1, 0.0F, 0.0F, 0.0F);
        this.body.addChild(this.body1);
        this.rightarm = new ModelRenderer(this, 24, 0);
        this.rightarm.addBox(-2.5F, -2.0F, -2.5F, 5, 3, 5);
        this.rightarm.setRotationPoint(-6.5F, 2.0F, 0.0F);
        this.rightarm.setTextureSize(64, 64);
        this.rightarm.mirror = true;
        this.setRotation(this.rightarm, -0.0872665F, 0.0F, 0.0872665F);
        this.rightarm1 = new ModelRenderer(this, 44, 22);
        this.rightarm1.addBox(-1.5F, 0.0F, -1.5F, 3, 7, 3);
        this.rightarm1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.rightarm1.setTextureSize(64, 64);
        this.rightarm1.mirror = true;
        this.setRotation(this.rightarm1, 0.0F, 0.0F, 0.0698132F);
        this.rightarm.addChild(this.rightarm1);
        this.rightarm2 = new ModelRenderer(this, 0, 21);
        this.rightarm2.addBox(-2.5F, 6.0F, -1.0F, 4, 7, 4);
        this.rightarm2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.rightarm2.setTextureSize(64, 64);
        this.rightarm2.mirror = true;
        this.setRotation(this.rightarm2, -0.1570796F, 0.0F, 0.0F);
        this.rightarm.addChild(this.rightarm2);
        this.leftarm = new ModelRenderer(this, 4, 0);
        this.leftarm.addBox(-2.5F, -2.0F, -2.5F, 5, 3, 5);
        this.leftarm.setRotationPoint(6.5F, 2.0F, 0.0F);
        this.leftarm.setTextureSize(64, 64);
        this.leftarm.mirror = true;
        this.setRotation(this.leftarm, -0.0872665F, 0.0F, -0.0872665F);
        this.leftarm1 = new ModelRenderer(this, 44, 22);
        this.leftarm1.addBox(-1.5F, 0.0F, -1.5F, 3, 7, 3);
        this.leftarm1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.leftarm1.setTextureSize(64, 64);
        this.leftarm1.mirror = true;
        this.setRotation(this.leftarm1, 0.0F, 0.0F, -0.0698132F);
        this.leftarm.addChild(this.leftarm1);
        this.rightleg = new ModelRenderer(this, 44, 22);
        this.rightleg.addBox(-2.5F, 0.0F, -1.5F, 3, 7, 3);
        this.rightleg.setRotationPoint(-2.0F, 10.0F, 0.0F);
        this.rightleg.setTextureSize(64, 64);
        this.rightleg.mirror = true;
        this.setRotation(this.rightleg, -0.0872665F, 0.0F, 0.122173F);
        this.rightleg1 = new ModelRenderer(this, 0, 32);
        this.rightleg1.addBox(-4.0F, 4.0F, -3.5F, 5, 8, 5);
        this.rightleg1.setRotationPoint(0.0F, 2.0F, 0.0F);
        this.rightleg1.setTextureSize(64, 64);
        this.rightleg1.mirror = true;
        this.setRotation(this.rightleg1, 0.0872665F, 0.0F, -0.122173F);
        this.rightleg.addChild(this.rightleg1);
        this.leftleg = new ModelRenderer(this, 44, 22);
        this.leftleg.addBox(-0.5F, 0.0F, -1.5F, 3, 7, 3);
        this.leftleg.setRotationPoint(2.0F, 10.0F, 0.0F);
        this.leftleg.setTextureSize(64, 64);
        this.leftleg.mirror = true;
        this.setRotation(this.leftleg, -0.0872665F, 0.0F, -0.122173F);
        this.leftleg1 = new ModelRenderer(this, 0, 32);
        this.leftleg1.addBox(-1.0F, 4.0F, -3.5F, 5, 8, 5);
        this.leftleg1.setRotationPoint(0.0F, 2.0F, 0.0F);
        this.leftleg1.setTextureSize(64, 64);
        this.leftleg1.mirror = true;
        this.setRotation(this.leftleg1, 0.0872665F, 0.0F, 0.122173F);
        this.leftleg.addChild(this.leftleg1);
        this.leftarm2 = new ModelRenderer(this, 16, 21);
        this.leftarm2.addBox(-1.5F, 6.0F, -1.0F, 4, 7, 4);
        this.leftarm2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.leftarm2.setTextureSize(64, 64);
        this.leftarm2.mirror = true;
        this.setRotation(this.leftarm2, -0.1570796F, 0.0F, 0.0F);
        this.leftarm.addChild(this.leftarm2);
        this.leftarm3 = new ModelRenderer(this, 34, 35);
        this.leftarm3.addBox(0.0F, 11.0F, -5.0F, 2, 2, 9);
        this.leftarm3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.leftarm3.setTextureSize(64, 64);
        this.leftarm3.mirror = true;
        this.setRotation(this.leftarm3, -0.1570796F, 0.0F, 0.0F);
        this.leftarm.addChild(this.leftarm3);
        this.leftarm4 = new ModelRenderer(this, 52, 32);
        this.leftarm4.addBox(-0.5F, 10.5F, 4.0F, 3, 3, 3);
        this.leftarm4.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.leftarm4.setTextureSize(64, 64);
        this.leftarm4.mirror = true;
        this.setRotation(this.leftarm4, -0.1570796F, 0.0F, 0.0F);
        this.leftarm.addChild(this.leftarm4);
        this.leftarm5 = new ModelRenderer(this, 20, 40);
        this.leftarm5.addBox(-0.5F, 10.5F, -7.0F, 3, 3, 4);
        this.leftarm5.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.leftarm5.setTextureSize(64, 64);
        this.leftarm5.mirror = true;
        this.setRotation(this.leftarm5, -0.1570796F, 0.0F, 0.0F);
        this.leftarm.addChild(this.leftarm5);
        this.leftarm6 = new ModelRenderer(this, 20, 32);
        this.leftarm6.addBox(-1.0F, 10.0F, -11.0F, 4, 4, 4);
        this.leftarm6.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.leftarm6.setTextureSize(64, 64);
        this.leftarm6.mirror = true;
        this.setRotation(this.leftarm6, -0.1570796F, 0.0F, 0.0F);
        this.leftarm.addChild(this.leftarm6);
        this.leftarm7 = new ModelRenderer(this, 20, 32);
        this.leftarm7.addBox(-6.0F, 12.0F, -2.0F, 4, 4, 4);
        this.leftarm7.setRotationPoint(-2.5F, -0.7F, -2.3F);
        this.leftarm7.setTextureSize(64, 64);
        this.leftarm7.mirror = true;
        this.setRotation(this.leftarm7, -((float)Math.PI / 4F), 0.2792527F, -0.8726646F);
        this.leftarm.addChild(this.leftarm7);
    }

    public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7)
    {
        super.render(par1Entity, par2, par3, par4, par5, par6, par7);
        this.setRotationAngles(par1Entity, par2, par3, par4, par5, par6, par7);
        this.head.render(par7);
        this.body.render(par7);
        this.rightarm.render(par7);
        this.leftarm.render(par7);
        this.rightleg.render(par7);
        this.leftleg.render(par7);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setLivingAnimations(EntityMobGoblin par1EntityLiving, float par2, float par3, float par4)
    {
        super.setLivingAnimations(par1EntityLiving, par2, par3, par4);
        int attack = par1EntityLiving.smashCounter / 10;
        this.rightarm.rotateAngleX = (float)attack;
        this.leftarm.rotateAngleX = (float)attack;
    }

    public void setRotationAngles(Entity par1Entity, float f1, float f2, float f3, float f4, float f5, float f6)
    {
        float flag = ((EntityMobGoblin)par1Entity).getSwingProgressq(f6);
        this.head.rotateAngleY = f4 / (180F / (float)Math.PI);
        this.head.rotateAngleX = f5 / (180F / (float)Math.PI);
        this.rightarm.rotateAngleX = MathHelper.cos(f1 * 0.6662F + (float)Math.PI) * 2.0F * f2 * 0.5F;
        this.rightarm.rotateAngleZ = 0.0F;
        this.rightarm.rotateAngleZ += MathHelper.cos(f3 * 0.09F) * 0.05F + 0.05F;
        this.rightarm.rotateAngleX += MathHelper.sin(f3 * 0.067F) * 0.05F;

        if (flag > 0.0F)
        {
            this.leftarm.rotateAngleX = -0.7F;
            this.leftarm.rotateAngleZ = -0.2F;
            this.leftarm.rotateAngleY = 0.5F;
            this.leftarm.rotateAngleY -= MathHelper.cos(f3 * 0.6F) * 0.05F * 5.0F;
            this.leftarm.rotateAngleZ -= MathHelper.cos(f3 * 0.6F) * 0.05F * 5.0F;
            this.leftarm.rotateAngleX -= MathHelper.sin(f3 * 0.6F) * 0.05F * 18.0F;
        }
        else
        {
            this.leftarm.rotateAngleX = MathHelper.cos(f1 * 0.6662F) * 2.0F * f2 * 0.5F;
            this.leftarm.rotateAngleZ = 0.0F;
            this.leftarm.rotateAngleZ -= MathHelper.cos(f3 * 0.09F) * 0.05F + 0.05F;
            this.leftarm.rotateAngleX -= MathHelper.sin(f3 * 0.067F) * 0.05F;
            this.leftarm.rotateAngleY = 0.0F;
        }

        this.rightleg.rotateAngleX = MathHelper.cos(f1 * 0.6662F) * 1.4F * f2;
        this.leftleg.rotateAngleX = MathHelper.cos(f1 * 0.6662F + (float)Math.PI) * 1.4F * f2;
        this.rightleg.rotateAngleY = 0.0F;
        this.leftleg.rotateAngleY = 0.0F;
        this.rightarm.rotateAngleY = 0.0F;
    }
}
