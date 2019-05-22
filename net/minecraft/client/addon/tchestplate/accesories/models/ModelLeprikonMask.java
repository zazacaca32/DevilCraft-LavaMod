package net.minecraft.client.addon.tchestplate.accesories.models;

import net.minecraft.client.Minecraft;
import net.minecraft.client.addon.tchestplate.armors.models.ModelBaseLavaArmor;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.opengl.GL11;

public class ModelLeprikonMask extends ModelBaseLavaArmor
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
    ModelRenderer Shape13;
    ModelRenderer Shape14;
    ModelRenderer Shape15;
    ModelRenderer Shape16;
    ModelRenderer Shape17;
    ModelRenderer Shape18;
    ModelRenderer Shape19;
    ModelRenderer Shape20;
    ModelRenderer Shape21;
    ModelRenderer Shape22;
    ModelRenderer Shape23;

    public String getTexture()
    {
        return "/mods/LavaChestPlate/textures/items/itemMaskHelmet4.png";
    }

    public ModelLeprikonMask()
    {
        super.textureWidth = 64;
        super.textureHeight = 64;
        this.Shape1 = new ModelRenderer(this, 0, 0);
        this.Shape1.addBox(0.0F, 0.0F, 0.0F, 4, 2, 3);
        this.Shape1.setRotationPoint(-2.0F, -8.0F, -7.0F);
        this.Shape1.setTextureSize(64, 64);
        this.Shape1.mirror = true;
        this.setRotation(this.Shape1, 0.0F, 0.0F, 0.0F);
        this.Shape2 = new ModelRenderer(this, 0, 0);
        this.Shape2.addBox(0.0F, 0.0F, 0.0F, 2, 2, 1);
        this.Shape2.setRotationPoint(-4.0F, -9.0F, -6.0F);
        this.Shape2.setTextureSize(64, 64);
        this.Shape2.mirror = true;
        this.setRotation(this.Shape2, 0.0F, 0.0F, 0.0F);
        this.Shape3 = new ModelRenderer(this, 0, 0);
        this.Shape3.addBox(0.0F, 0.0F, 0.0F, 2, 2, 1);
        this.Shape3.setRotationPoint(2.0F, -9.0F, -6.0F);
        this.Shape3.setTextureSize(64, 64);
        this.Shape3.mirror = true;
        this.setRotation(this.Shape3, 0.0F, 0.0F, 0.0F);
        this.Shape4 = new ModelRenderer(this, 0, 0);
        this.Shape4.addBox(0.0F, 0.0F, 0.0F, 10, 2, 4);
        this.Shape4.setRotationPoint(-5.0F, -9.0F, -5.0F);
        this.Shape4.setTextureSize(64, 64);
        this.Shape4.mirror = true;
        this.setRotation(this.Shape4, 0.0F, 0.0F, 0.0F);
        this.Shape5 = new ModelRenderer(this, 0, 0);
        this.Shape5.addBox(0.0F, 0.0F, 0.0F, 8, 2, 7);
        this.Shape5.setRotationPoint(-4.0F, -9.0F, -1.0F);
        this.Shape5.setTextureSize(64, 64);
        this.Shape5.mirror = true;
        this.setRotation(this.Shape5, 0.0F, 0.0F, 0.0F);
        this.Shape6 = new ModelRenderer(this, 0, 0);
        this.Shape6.addBox(0.0F, 0.0F, 0.0F, 4, 1, 1);
        this.Shape6.setRotationPoint(-2.0F, -8.0F, 6.0F);
        this.Shape6.setTextureSize(64, 64);
        this.Shape6.mirror = true;
        this.setRotation(this.Shape6, 0.0F, 0.0F, 0.0F);
        this.Shape7 = new ModelRenderer(this, 41, 13);
        this.Shape7.addBox(0.0F, 0.0F, 0.0F, 2, 2, 1);
        this.Shape7.setRotationPoint(-1.0F, -11.0F, -5.0F);
        this.Shape7.setTextureSize(64, 64);
        this.Shape7.mirror = true;
        this.setRotation(this.Shape7, 0.0F, 0.0F, 0.0F);
        this.Shape8 = new ModelRenderer(this, 0, 0);
        this.Shape8.addBox(0.0F, 0.0F, 0.0F, 4, 2, 1);
        this.Shape8.setRotationPoint(-2.0F, -14.0F, -5.0F);
        this.Shape8.setTextureSize(64, 64);
        this.Shape8.mirror = true;
        this.setRotation(this.Shape8, 0.0F, 0.0F, 0.0F);
        this.Shape9 = new ModelRenderer(this, 9, -1);
        this.Shape9.addBox(0.0F, 0.0F, 0.0F, 1, 2, 9);
        this.Shape9.setRotationPoint(-5.0F, -10.0F, -4.0F);
        this.Shape9.setTextureSize(64, 64);
        this.Shape9.mirror = true;
        this.setRotation(this.Shape9, 0.0F, 0.0F, 0.0F);
        this.Shape10 = new ModelRenderer(this, 0, 0);
        this.Shape10.addBox(0.0F, 0.0F, 0.0F, 1, 2, 8);
        this.Shape10.setRotationPoint(-6.0F, -10.0F, -4.0F);
        this.Shape10.setTextureSize(64, 64);
        this.Shape10.mirror = true;
        this.setRotation(this.Shape10, 0.0F, 0.0F, 0.0F);
        this.Shape11 = new ModelRenderer(this, 0, 9);
        this.Shape11.addBox(0.0F, 0.0F, 0.0F, 6, 2, 1);
        this.Shape11.setRotationPoint(-3.0F, -11.0F, -4.0F);
        this.Shape11.setTextureSize(64, 64);
        this.Shape11.mirror = true;
        this.setRotation(this.Shape11, 0.0F, 0.0F, 0.0F);
        this.Shape12 = new ModelRenderer(this, 0, 4);
        this.Shape12.addBox(0.0F, 0.0F, 0.0F, 8, 2, 6);
        this.Shape12.setRotationPoint(-4.0F, -11.0F, -3.0F);
        this.Shape12.setTextureSize(64, 64);
        this.Shape12.mirror = true;
        this.setRotation(this.Shape12, 0.0F, 0.0F, 0.0F);
        this.Shape13 = new ModelRenderer(this, 0, 0);
        this.Shape13.addBox(0.0F, 0.0F, 0.0F, 8, 3, 6);
        this.Shape13.setRotationPoint(-4.0F, -14.0F, -4.0F);
        this.Shape13.setTextureSize(64, 64);
        this.Shape13.mirror = true;
        this.setRotation(this.Shape13, 0.0F, 0.0F, 0.0F);
        this.Shape14 = new ModelRenderer(this, 0, 0);
        this.Shape14.addBox(0.0F, 0.0F, 0.0F, 10, 2, 4);
        this.Shape14.setRotationPoint(-5.0F, -14.0F, -3.0F);
        this.Shape14.setTextureSize(64, 64);
        this.Shape14.mirror = true;
        this.setRotation(this.Shape14, 0.0F, 0.0F, 0.0F);
        this.Shape15 = new ModelRenderer(this, 0, -1);
        this.Shape15.addBox(0.0F, 0.0F, 0.0F, 1, 2, 9);
        this.Shape15.setRotationPoint(4.0F, -10.0F, -4.0F);
        this.Shape15.setTextureSize(64, 64);
        this.Shape15.mirror = true;
        this.setRotation(this.Shape15, 0.0F, 0.0F, 0.0F);
        this.Shape16 = new ModelRenderer(this, 0, 0);
        this.Shape16.addBox(0.0F, 0.0F, 0.0F, 1, 2, 8);
        this.Shape16.setRotationPoint(5.0F, -10.0F, -4.0F);
        this.Shape16.setTextureSize(64, 64);
        this.Shape16.mirror = true;
        this.setRotation(this.Shape16, 0.0F, 0.0F, 0.0F);
        this.Shape17 = new ModelRenderer(this, 0, 0);
        this.Shape17.addBox(0.0F, 0.0F, 0.0F, 2, 2, 1);
        this.Shape17.setRotationPoint(-1.0F, -14.0F, 3.0F);
        this.Shape17.setTextureSize(64, 64);
        this.Shape17.mirror = true;
        this.setRotation(this.Shape17, 0.0F, 0.0F, 0.0F);
        this.Shape18 = new ModelRenderer(this, 0, 0);
        this.Shape18.addBox(0.0F, 0.0F, 0.0F, 6, 3, 1);
        this.Shape18.setRotationPoint(-3.0F, -14.0F, 2.0F);
        this.Shape18.setTextureSize(64, 64);
        this.Shape18.mirror = true;
        this.setRotation(this.Shape18, 0.0F, 0.0F, 0.0F);
        this.Shape19 = new ModelRenderer(this, 0, 0);
        this.Shape19.addBox(0.0F, 0.0F, 0.0F, 8, 1, 1);
        this.Shape19.setRotationPoint(-4.0F, -14.0F, 2.0F);
        this.Shape19.setTextureSize(64, 64);
        this.Shape19.mirror = true;
        this.setRotation(this.Shape19, 0.0F, 0.0F, 0.0F);
        this.Shape20 = new ModelRenderer(this, 0, 10);
        this.Shape20.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1);
        this.Shape20.setRotationPoint(-1.0F, -11.0F, 3.0F);
        this.Shape20.setTextureSize(64, 64);
        this.Shape20.mirror = true;
        this.setRotation(this.Shape20, 0.0F, 0.0F, 0.0F);
        this.Shape21 = new ModelRenderer(this, 0, 10);
        this.Shape21.addBox(0.0F, 0.0F, 0.0F, 4, 1, 1);
        this.Shape21.setRotationPoint(-2.0F, -10.0F, 3.0F);
        this.Shape21.setTextureSize(64, 64);
        this.Shape21.mirror = true;
        this.setRotation(this.Shape21, 0.0F, 0.0F, 0.0F);
        this.Shape22 = new ModelRenderer(this, 0, 0);
        this.Shape22.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
        this.Shape22.setRotationPoint(-4.0F, -10.0F, 3.0F);
        this.Shape22.setTextureSize(64, 64);
        this.Shape22.mirror = true;
        this.Shape23 = new ModelRenderer(this, 0, 0);
        this.Shape23.addBox(3.0F, -10.0F, 3.0F, 1, 1, 1);
        this.Shape23.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape23.setTextureSize(64, 64);
        this.Shape23.mirror = true;
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
        super.bipedHead.addChild(this.Shape16);
        super.bipedHead.addChild(this.Shape17);
        super.bipedHead.addChild(this.Shape18);
        super.bipedHead.addChild(this.Shape19);
        super.bipedHead.addChild(this.Shape20);
        super.bipedHead.addChild(this.Shape21);
        super.bipedHead.addChild(this.Shape22);
        super.bipedHead.addChild(this.Shape23);
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
            Minecraft.getMinecraft().renderEngine.bindTexture("/mods/qodermelony/textures/items/leprikon.png.png");
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
        }
        else
        {
            Minecraft.getMinecraft().renderEngine.bindTexture("/mods/qodermelony/textures/items/leprikon.png");
        }

        GL11.glPushMatrix();
        GL11.glScalef(1.0F, 1.0F, 1.0F);
        super.render(entity, f, f1, f2, f3, f4, f5);
        GL11.glPopMatrix();
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
