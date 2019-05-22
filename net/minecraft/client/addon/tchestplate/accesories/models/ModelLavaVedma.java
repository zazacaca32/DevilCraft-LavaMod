package net.minecraft.client.addon.tchestplate.accesories.models;

import net.minecraft.client.Minecraft;
import net.minecraft.client.addon.tchestplate.armors.models.ModelBaseLavaArmor;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.opengl.GL11;

public class ModelLavaVedma extends ModelBaseLavaArmor
{
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer Shape5;
    ModelRenderer Shape6;
    ModelRenderer Shape7;
    ModelRenderer Shape8;
    ModelRenderer Shape9;
    ModelRenderer Shape10;
    ModelRenderer Shape11;
    ModelRenderer Shape12;
    ModelRenderer Shape29;
    ModelRenderer Shape30;
    ModelRenderer Shape31;
    ModelRenderer Shape32;
    ModelRenderer Shape33;
    ModelRenderer Shape13;
    ModelRenderer Shape14;
    ModelRenderer Shape17;
    ModelRenderer Shape18;
    ModelRenderer Shape19;
    ModelRenderer Shape20;
    ModelRenderer Shape21;
    ModelRenderer Shape22;
    ModelRenderer Shape23;
    ModelRenderer Shape24;
    ModelRenderer Shape25;
    ModelRenderer Shape26;
    ModelRenderer Shape27;
    ModelRenderer Shape28;
    ModelRenderer Shape34;
    ModelRenderer Shape35;
    ModelRenderer Shape36;
    ModelRenderer Shape37;
    ModelRenderer Shape38;
    ModelRenderer Shape39;
    ModelRenderer Shape15;

    public String getTexture()
    {
        return "/mods/models/accesories/vedma.png";
    }

    public ModelLavaVedma()
    {
        super.textureWidth = 32;
        super.textureHeight = 32;
        this.Shape1 = new ModelRenderer(this, 0, 0);
        this.Shape1.addBox(-4.5F, -4.8F, -7.0F, 9, 1, 1);
        this.Shape1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape1.setTextureSize(32, 32);
        this.Shape1.mirror = true;
        this.setRotation(this.Shape1, 0.0F, 0.0F, 0.0F);
        this.Shape2 = new ModelRenderer(this, 0, 0);
        this.Shape2.addBox(-7.0F, -5.2F, -4.5F, 1, 1, 9);
        this.Shape2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape2.setTextureSize(32, 32);
        this.Shape2.mirror = true;
        this.setRotation(this.Shape2, 0.0F, 0.0F, 0.0F);
        this.Shape3 = new ModelRenderer(this, 0, 0);
        this.Shape3.addBox(6.0F, -5.2F, -4.5F, 1, 1, 9);
        this.Shape3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape3.setTextureSize(32, 32);
        this.Shape3.mirror = true;
        this.setRotation(this.Shape3, 0.0F, 0.0F, 0.0F);
        this.Shape4 = new ModelRenderer(this, 0, 0);
        this.Shape4.addBox(-4.5F, -4.8F, 6.0F, 9, 1, 1);
        this.Shape4.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape4.setTextureSize(32, 32);
        this.Shape4.mirror = true;
        this.setRotation(this.Shape4, 0.0F, 0.0F, 0.0F);
        this.Shape5 = new ModelRenderer(this, 0, 0);
        this.Shape5.addBox(-5.0F, -5.0F, -6.0F, 10, 1, 1);
        this.Shape5.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape5.setTextureSize(32, 32);
        this.Shape5.mirror = true;
        this.setRotation(this.Shape5, 0.0F, 0.0F, 0.0F);
        this.Shape6 = new ModelRenderer(this, 0, 0);
        this.Shape6.addBox(-6.0F, -5.0F, -5.0F, 1, 1, 10);
        this.Shape6.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape6.setTextureSize(32, 32);
        this.Shape6.mirror = true;
        this.setRotation(this.Shape6, 0.0F, 0.0F, 0.0F);
        this.Shape7 = new ModelRenderer(this, 0, 0);
        this.Shape7.addBox(5.0F, -5.0F, -5.0F, 1, 1, 10);
        this.Shape7.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape7.setTextureSize(32, 32);
        this.Shape7.mirror = true;
        this.setRotation(this.Shape7, 0.0F, 0.0F, 0.0F);
        this.Shape8 = new ModelRenderer(this, 0, 0);
        this.Shape8.addBox(-5.0F, -5.0F, 5.0F, 10, 1, 1);
        this.Shape8.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape8.setTextureSize(32, 32);
        this.Shape8.mirror = true;
        this.setRotation(this.Shape8, 0.0F, 0.0F, 0.0F);
        this.Shape9 = new ModelRenderer(this, 0, 18);
        this.Shape9.addBox(-4.0F, -8.0F, -5.0F, 8, 2, 1);
        this.Shape9.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape9.setTextureSize(32, 32);
        this.Shape9.mirror = true;
        this.setRotation(this.Shape9, 0.0F, 0.0F, 0.0F);
        this.Shape10 = new ModelRenderer(this, 0, 11);
        this.Shape10.addBox(4.0F, -8.0F, -4.0F, 1, 2, 8);
        this.Shape10.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape10.setTextureSize(32, 32);
        this.Shape10.mirror = true;
        this.setRotation(this.Shape10, 0.0F, 0.0F, 0.0F);
        this.Shape11 = new ModelRenderer(this, 0, 11);
        this.Shape11.addBox(-5.0F, -8.0F, -4.0F, 1, 2, 8);
        this.Shape11.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape11.setTextureSize(32, 32);
        this.Shape11.mirror = true;
        this.setRotation(this.Shape11, 0.0F, 0.0F, 0.0F);
        this.Shape12 = new ModelRenderer(this, 0, 18);
        this.Shape12.addBox(-4.0F, -8.0F, 4.0F, 8, 2, 1);
        this.Shape12.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape12.setTextureSize(32, 32);
        this.Shape12.mirror = true;
        this.setRotation(this.Shape12, 0.0F, 0.0F, 0.0F);
        this.Shape29 = new ModelRenderer(this, 0, 0);
        this.Shape29.addBox(0.5F, -18.0F, 0.5F, 3, 2, 2);
        this.Shape29.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape29.setTextureSize(32, 32);
        this.Shape29.mirror = true;
        this.setRotation(this.Shape29, 0.0F, 0.0F, 0.0F);
        this.Shape30 = new ModelRenderer(this, 0, 0);
        this.Shape30.addBox(-3.0F, -4.5F, -8.0F, 6, 1, 1);
        this.Shape30.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape30.setTextureSize(32, 32);
        this.Shape30.mirror = true;
        this.setRotation(this.Shape30, 0.0F, 0.0F, 0.0F);
        this.Shape31 = new ModelRenderer(this, 0, 0);
        this.Shape31.addBox(-8.0F, -5.5F, -3.0F, 1, 1, 6);
        this.Shape31.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape31.setTextureSize(32, 32);
        this.Shape31.mirror = true;
        this.setRotation(this.Shape31, 0.0F, 0.0F, 0.0F);
        this.Shape32 = new ModelRenderer(this, 0, 0);
        this.Shape32.addBox(7.0F, -5.5F, -3.0F, 1, 1, 6);
        this.Shape32.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape32.setTextureSize(32, 32);
        this.Shape32.mirror = true;
        this.setRotation(this.Shape32, 0.0F, 0.0F, 0.0F);
        this.Shape33 = new ModelRenderer(this, 0, 0);
        this.Shape33.addBox(-3.0F, -4.5F, 7.0F, 6, 1, 1);
        this.Shape33.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape33.setTextureSize(32, 32);
        this.Shape33.mirror = true;
        this.setRotation(this.Shape33, 0.0F, 0.0F, 0.0F);
        this.Shape13 = new ModelRenderer(this, 0, 0);
        this.Shape13.addBox(-5.0F, -5.0F, -5.0F, 1, 1, 1);
        this.Shape13.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape13.setTextureSize(32, 32);
        this.Shape13.mirror = true;
        this.setRotation(this.Shape13, 0.0F, 0.0F, 0.0F);
        this.Shape14 = new ModelRenderer(this, 0, 0);
        this.Shape14.addBox(-5.0F, -6.0F, -5.0F, 10, 1, 5);
        this.Shape14.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape14.setTextureSize(32, 32);
        this.Shape14.mirror = true;
        this.setRotation(this.Shape14, 0.0F, 0.0F, 0.0F);
        this.Shape17 = new ModelRenderer(this, 0, 0);
        this.Shape17.addBox(-3.0F, -10.0F, -3.5F, 6, 1, 1);
        this.Shape17.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape17.setTextureSize(32, 32);
        this.Shape17.mirror = true;
        this.setRotation(this.Shape17, 0.0F, 0.0F, 0.0F);
        this.Shape18 = new ModelRenderer(this, 0, 0);
        this.Shape18.addBox(-3.0F, -10.0F, 2.5F, 6, 1, 1);
        this.Shape18.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape18.setTextureSize(32, 32);
        this.Shape18.mirror = true;
        this.setRotation(this.Shape18, 0.0F, 0.0F, 0.0F);
        this.Shape19 = new ModelRenderer(this, 0, 0);
        this.Shape19.addBox(-3.5F, -10.0F, -3.0F, 1, 1, 6);
        this.Shape19.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape19.setTextureSize(32, 32);
        this.Shape19.mirror = true;
        this.setRotation(this.Shape19, 0.0F, 0.0F, 0.0F);
        this.Shape20 = new ModelRenderer(this, 0, 0);
        this.Shape20.addBox(2.5F, -10.0F, -3.0F, 1, 1, 6);
        this.Shape20.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape20.setTextureSize(32, 32);
        this.Shape20.mirror = true;
        this.setRotation(this.Shape20, 0.0F, 0.0F, 0.0F);
        this.Shape21 = new ModelRenderer(this, 0, 0);
        this.Shape21.addBox(-4.0F, -9.0F, -4.0F, 8, 1, 8);
        this.Shape21.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape21.setTextureSize(32, 32);
        this.Shape21.mirror = true;
        this.setRotation(this.Shape21, 0.0F, 0.0F, 0.0F);
        this.Shape22 = new ModelRenderer(this, 0, 0);
        this.Shape22.addBox(-3.0F, -11.0F, -3.0F, 6, 1, 6);
        this.Shape22.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape22.setTextureSize(32, 32);
        this.Shape22.mirror = true;
        this.setRotation(this.Shape22, 0.0F, 0.0F, 0.0F);
        this.Shape23 = new ModelRenderer(this, 0, 0);
        this.Shape23.addBox(-2.5F, -12.0F, -2.0F, 5, 1, 4);
        this.Shape23.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape23.setTextureSize(32, 32);
        this.Shape23.mirror = true;
        this.setRotation(this.Shape23, 0.0F, 0.0F, 0.0F);
        this.Shape24 = new ModelRenderer(this, 0, 0);
        this.Shape24.addBox(-2.0F, -12.0F, -2.5F, 4, 1, 5);
        this.Shape24.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape24.setTextureSize(32, 32);
        this.Shape24.mirror = true;
        this.setRotation(this.Shape24, 0.0F, 0.0F, 0.0F);
        this.Shape25 = new ModelRenderer(this, 0, 0);
        this.Shape25.addBox(-2.0F, -13.0F, -2.0F, 4, 1, 4);
        this.Shape25.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape25.setTextureSize(32, 32);
        this.Shape25.mirror = true;
        this.setRotation(this.Shape25, 0.0F, 0.0F, 0.0F);
        this.Shape26 = new ModelRenderer(this, 0, 0);
        this.Shape26.addBox(-2.0F, -14.0F, -1.0F, 4, 1, 3);
        this.Shape26.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape26.setTextureSize(32, 32);
        this.Shape26.mirror = true;
        this.setRotation(this.Shape26, 0.0F, 0.0F, 0.0F);
        this.Shape27 = new ModelRenderer(this, 0, 0);
        this.Shape27.addBox(-1.5F, -14.0F, -1.5F, 3, 1, 4);
        this.Shape27.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape27.setTextureSize(32, 32);
        this.Shape27.mirror = true;
        this.setRotation(this.Shape27, 0.0F, 0.0F, 0.0F);
        this.Shape28 = new ModelRenderer(this, 0, 0);
        this.Shape28.addBox(-1.5F, -15.0F, -1.0F, 3, 1, 3);
        this.Shape28.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape28.setTextureSize(32, 32);
        this.Shape28.mirror = true;
        this.setRotation(this.Shape28, 0.0F, 0.0F, 0.0F);
        this.Shape34 = new ModelRenderer(this, 0, 0);
        this.Shape34.addBox(-1.0F, -16.0F, -0.5F, 3, 2, 3);
        this.Shape34.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape34.setTextureSize(32, 32);
        this.Shape34.mirror = true;
        this.setRotation(this.Shape34, 0.0F, 0.0F, 0.0F);
        this.Shape35 = new ModelRenderer(this, 0, 0);
        this.Shape35.addBox(-0.5F, -17.0F, 0.0F, 3, 2, 2);
        this.Shape35.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape35.setTextureSize(32, 32);
        this.Shape35.mirror = true;
        this.setRotation(this.Shape35, 0.0F, 0.0F, 0.0F);
        this.Shape36 = new ModelRenderer(this, 0, 0);
        this.Shape36.addBox(1.0F, -18.5F, 1.0F, 3, 2, 1);
        this.Shape36.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape36.setTextureSize(32, 32);
        this.Shape36.mirror = true;
        this.setRotation(this.Shape36, 0.0F, 0.0F, 0.0F);
        this.Shape37 = new ModelRenderer(this, 0, 0);
        this.Shape37.addBox(4.0F, -18.0F, 0.5F, 1, 2, 1);
        this.Shape37.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape37.setTextureSize(32, 32);
        this.Shape37.mirror = true;
        this.setRotation(this.Shape37, 0.0F, 0.0F, 0.0F);
        this.Shape38 = new ModelRenderer(this, 0, 0);
        this.Shape38.addBox(0.0F, -17.0F, 2.0F, 2, 2, 1);
        this.Shape38.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape38.setTextureSize(32, 32);
        this.Shape38.mirror = true;
        this.setRotation(this.Shape38, 0.0F, 0.0F, 0.0F);
        this.Shape39 = new ModelRenderer(this, 18, 11);
        this.Shape39.addBox(-1.5F, -9.0F, -5.3F, 3, 4, 1);
        this.Shape39.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape39.setTextureSize(32, 32);
        this.Shape39.mirror = true;
        this.setRotation(this.Shape39, 0.0F, 0.0F, 0.0F);
        this.Shape15 = new ModelRenderer(this, 0, 0);
        this.Shape15.addBox(-5.0F, -6.0F, 0.0F, 10, 1, 5);
        this.Shape15.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape15.setTextureSize(32, 32);
        this.Shape15.mirror = true;
        this.setRotation(this.Shape15, 0.0F, 0.0F, 0.0F);
        super.bipedHead.cubeList.clear();
        super.bipedHead.addChild(this.Shape1);
        super.bipedHead.addChild(this.Shape2);
        super.bipedHead.addChild(this.Shape3);
        super.bipedHead.addChild(this.Shape4);
        super.bipedHead.addChild(this.Shape5);
        super.bipedHead.addChild(this.Shape6);
        super.bipedHead.addChild(this.Shape7);
        super.bipedHead.addChild(this.Shape8);
        super.bipedHead.addChild(this.Shape9);
        super.bipedHead.addChild(this.Shape10);
        super.bipedHead.addChild(this.Shape11);
        super.bipedHead.addChild(this.Shape12);
        super.bipedHead.addChild(this.Shape13);
        super.bipedHead.addChild(this.Shape14);
        super.bipedHead.addChild(this.Shape15);
        super.bipedHead.addChild(this.Shape17);
        super.bipedHead.addChild(this.Shape18);
        super.bipedHead.addChild(this.Shape19);
        super.bipedHead.addChild(this.Shape20);
        super.bipedHead.addChild(this.Shape21);
        super.bipedHead.addChild(this.Shape22);
        super.bipedHead.addChild(this.Shape23);
        super.bipedHead.addChild(this.Shape24);
        super.bipedHead.addChild(this.Shape25);
        super.bipedHead.addChild(this.Shape26);
        super.bipedHead.addChild(this.Shape27);
        super.bipedHead.addChild(this.Shape28);
        super.bipedHead.addChild(this.Shape29);
        super.bipedHead.addChild(this.Shape30);
        super.bipedHead.addChild(this.Shape31);
        super.bipedHead.addChild(this.Shape32);
        super.bipedHead.addChild(this.Shape33);
        super.bipedHead.addChild(this.Shape34);
        super.bipedHead.addChild(this.Shape35);
        super.bipedHead.addChild(this.Shape36);
        super.bipedHead.addChild(this.Shape37);
        super.bipedHead.addChild(this.Shape38);
        super.bipedHead.addChild(this.Shape39);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        boolean flag = false;

        if (entity != null && entity instanceof EntityPlayer)
        {
            flag = true;
        }

        if (entity.isInvisible())
        {
            Minecraft.getMinecraft().renderEngine.bindTexture("/mods/models/accesories/vedma.png.png");
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
        }
        else
        {
            Minecraft.getMinecraft().renderEngine.bindTexture("/mods/models/accesories/vedma.png");
        }

        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);

        if (entity.isInvisible())
        {
            GL11.glDisable(GL11.GL_BLEND);
        }
    }

    private void setRotation(ModelRenderer var1, float var2, float var3, float var4)
    {
        var1.rotateAngleX = var2;
        var1.rotateAngleY = var3;
        var1.rotateAngleZ = var4;
    }
}
