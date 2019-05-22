package net.minecraft.client.addon.tchestplate.weapon.renders.models;

import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.src.ModLoader;
import org.lwjgl.opengl.GL11;

public class ModelLStaffSlowe extends BaseModelHammer
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

    public ModelLStaffSlowe()
    {
        super.textureWidth = 64;
        super.textureHeight = 64;
        (this.Shape1 = new ModelRenderer(this, 48, 30)).addBox(0.0F, 0.0F, 0.0F, 5, 6, 3);
        this.Shape1.setRotationPoint(-1.0F, 12.0F, -1.0F);
        this.Shape1.setTextureSize(64, 64);
        this.Shape1.mirror = true;
        this.setRotation(this.Shape1, 0.0F, 0.0F, 0.0F);
        (this.Shape2 = new ModelRenderer(this, 50, 27)).addBox(0.0F, 0.0F, 0.0F, 3, 8, 4);
        this.Shape2.setRotationPoint(0.0F, -17.0F, -1.5F);
        this.Shape2.setTextureSize(64, 64);
        this.Shape2.mirror = true;
        this.setRotation(this.Shape2, 0.0F, 0.0F, 0.0F);
        (this.Shape3 = new ModelRenderer(this, 50, 28)).addBox(0.0F, 0.0F, 0.0F, 4, 7, 3);
        this.Shape3.setRotationPoint(-0.5F, -16.1F, -1.0F);
        this.Shape3.setTextureSize(64, 64);
        this.Shape3.mirror = true;
        this.setRotation(this.Shape3, 0.0F, 0.0F, 0.0F);
        (this.Shape4 = new ModelRenderer(this, 52, 32)).addBox(0.0F, 0.0F, 0.0F, 3, 6, 1);
        this.Shape4.setRotationPoint(0.0F, 12.0F, -2.0F);
        this.Shape4.setTextureSize(64, 64);
        this.Shape4.mirror = true;
        this.setRotation(this.Shape4, 0.0F, 0.0F, 0.0F);
        (this.Shape5 = new ModelRenderer(this, 52, 32)).addBox(0.0F, 0.0F, 2.0F, 3, 6, 1);
        this.Shape5.setRotationPoint(0.0F, 12.0F, 0.0F);
        this.Shape5.setTextureSize(64, 64);
        this.Shape5.mirror = true;
        this.setRotation(this.Shape5, 0.0F, 0.0F, 0.0F);
        (this.Shape6 = new ModelRenderer(this, 40, 27)).addBox(0.0F, 0.0F, 0.0F, 5, 4, 6);
        this.Shape6.setRotationPoint(-1.0F, 13.0F, -2.5F);
        this.Shape6.setTextureSize(64, 64);
        this.Shape6.mirror = true;
        this.setRotation(this.Shape6, 0.0F, 0.0F, 0.0F);
        (this.Shape7 = new ModelRenderer(this, 40, 29)).addBox(0.0F, 0.0F, 0.0F, 1, 4, 5);
        this.Shape7.setRotationPoint(-1.5F, 13.0F, -2.0F);
        this.Shape7.setTextureSize(64, 64);
        this.Shape7.mirror = true;
        this.setRotation(this.Shape7, 0.0F, 0.0F, 0.0F);
        (this.Shape8 = new ModelRenderer(this, 40, 29)).addBox(0.0F, 0.0F, 0.0F, 1, 4, 5);
        this.Shape8.setRotationPoint(3.5F, 13.0F, -2.0F);
        this.Shape8.setTextureSize(64, 64);
        this.Shape8.mirror = true;
        this.setRotation(this.Shape8, 0.0F, 0.0F, 0.0F);
        (this.Shape9 = new ModelRenderer(this, 54, 39)).addBox(0.0F, 0.0F, 0.0F, 2, 22, 3);
        this.Shape9.setRotationPoint(0.5F, -10.0F, -1.0F);
        this.Shape9.setTextureSize(64, 64);
        this.Shape9.mirror = true;
        this.setRotation(this.Shape9, 0.0F, 0.0F, 0.0F);
        (this.Shape10 = new ModelRenderer(this, 46, 41)).addBox(0.0F, 0.0F, 0.0F, 3, 22, 1);
        this.Shape10.setRotationPoint(0.0F, -10.0F, 0.0F);
        this.Shape10.setTextureSize(64, 64);
        this.Shape10.mirror = true;
        this.setRotation(this.Shape10, 0.0F, 0.0F, 0.0F);
        (this.Shape11 = new ModelRenderer(this, 40, 0)).addBox(0.0F, 0.0F, 0.0F, 6, 6, 6);
        this.Shape11.setRotationPoint(-1.5F, -24.5F, -2.5F);
        this.Shape11.setTextureSize(64, 64);
        this.Shape11.mirror = true;
        this.setRotation(this.Shape11, 0.0F, 0.0F, 0.0F);
        (this.Shape12 = new ModelRenderer(this, 44, 0)).addBox(0.0F, 0.0F, 0.0F, 5, 7, 5);
        this.Shape12.setRotationPoint(-1.0F, -25.0F, -2.0F);
        this.Shape12.setTextureSize(64, 64);
        this.Shape12.mirror = true;
        this.setRotation(this.Shape12, 0.0F, 0.0F, 0.0F);
        (this.Shape13 = new ModelRenderer(this, 52, 0)).addBox(0.0F, 0.0F, 0.0F, 3, 9, 3);
        this.Shape13.setRotationPoint(0.0F, -26.0F, -1.0F);
        this.Shape13.setTextureSize(64, 64);
        this.Shape13.mirror = true;
        this.setRotation(this.Shape13, 0.0F, 0.0F, 0.0F);
        (this.Shape14 = new ModelRenderer(this, 40, 0)).addBox(0.0F, 0.0F, 0.0F, 5, 5, 7);
        this.Shape14.setRotationPoint(-1.0F, -24.0F, -3.0F);
        this.Shape14.setTextureSize(64, 64);
        this.Shape14.mirror = true;
        this.setRotation(this.Shape14, 0.0F, 0.0F, 0.0F);
        (this.Shape15 = new ModelRenderer(this, 41, 0)).addBox(0.0F, 0.0F, 0.0F, 7, 5, 5);
        this.Shape15.setRotationPoint(-2.0F, -24.0F, -2.0F);
        this.Shape15.setTextureSize(64, 64);
        this.Shape15.mirror = true;
        this.setRotation(this.Shape15, 0.0F, 0.0F, 0.0F);
        (this.Shape16 = new ModelRenderer(this, 40, 0)).addBox(0.0F, 0.0F, 0.0F, 9, 3, 3);
        this.Shape16.setRotationPoint(-3.0F, -23.0F, -1.0F);
        this.Shape16.setTextureSize(64, 64);
        this.Shape16.mirror = true;
        this.setRotation(this.Shape16, 0.0F, 0.0F, 0.0F);
        (this.Shape17 = new ModelRenderer(this, 40, 0)).addBox(0.0F, 0.0F, 0.0F, 3, 3, 9);
        this.Shape17.setRotationPoint(0.0F, -23.0F, -4.0F);
        this.Shape17.setTextureSize(64, 64);
        this.Shape17.mirror = true;
        this.setRotation(this.Shape17, 0.0F, 0.0F, 0.0F);
        (this.Shape18 = new ModelRenderer(this, 44, 29)).addBox(0.0F, 0.0F, 0.0F, 3, 9, 1);
        this.Shape18.setRotationPoint(-3.0F, -20.0F, 0.0F);
        this.Shape18.setTextureSize(64, 64);
        this.Shape18.mirror = true;
        this.setRotation(this.Shape18, 0.0F, 0.0F, -0.2792527F);
        (this.Shape19 = new ModelRenderer(this, 44, 29)).addBox(0.0F, 0.0F, 0.0F, 3, 9, 1);
        this.Shape19.setRotationPoint(3.0F, -21.0F, 0.0F);
        this.Shape19.setTextureSize(64, 64);
        this.Shape19.mirror = true;
        this.setRotation(this.Shape19, 0.0F, 0.0F, 0.2792527F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.Shape1.render(f5);
        this.Shape2.render(f5);
        this.Shape3.render(f5);
        this.Shape4.render(f5);
        this.Shape5.render(f5);
        this.Shape6.render(f5);
        this.Shape7.render(f5);
        this.Shape8.render(f5);
        this.Shape9.render(f5);
        this.Shape10.render(f5);
        this.Shape11.render(f5);
        this.Shape12.render(f5);
        this.Shape13.render(f5);
        this.Shape14.render(f5);
        this.Shape15.render(f5);
        this.Shape16.render(f5);
        this.Shape17.render(f5);
        this.Shape18.render(f5);
        this.Shape19.render(f5);
        this.reb();
    }

    public void reb()
    {
        GL11.glPushMatrix();
        GL11.glTranslatef(0.08F, -1.398F, 0.02F);
        float scale = 0.6F;
        GL11.glScalef(0.6F, 0.6F, 0.6F);
        boolean col = false;
        int age = Minecraft.getMinecraft().renderViewEntity.ticksExisted;
        GL11.glPushMatrix();
        float f1 = 0.0F;

        if (!ModLoader.getMinecraftInstance().gameSettings.fancyGraphics)
        {
            ;
        }

        boolean q = true;
        Tessellator var22 = Tessellator.instance;
        f1 = (float)age / 500.0F;
        float f2 = 0.9F;
        float f3 = 0.0F;
        Random random = new Random(245L);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glShadeModel(GL11.GL_SMOOTH);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
        GL11.glDisable(GL11.GL_ALPHA_TEST);
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glDepthMask(false);
        GL11.glPushMatrix();
        boolean var23 = true;

        for (int i = 0; i < 50; ++i)
        {
            GL11.glRotatef(random.nextFloat() * 360.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(random.nextFloat() * 360.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(random.nextFloat() * 360.0F, 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(random.nextFloat() * 360.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(random.nextFloat() * 360.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(random.nextFloat() * 360.0F + f1 * 360.0F, 0.0F, 0.0F, 1.0F);
            var22.startDrawing(6);
            float fa = random.nextFloat() * 20.0F + 5.0F + 0.0F;
            float f4 = random.nextFloat() * 2.0F + 1.0F + 0.0F;
            fa /= 30.0F / ((float)Math.min(age, 10) / 10.0F);
            f4 /= 30.0F / ((float)Math.min(age, 10) / 10.0F);
            var22.setColorRGBA_I(16796215, 255);
            var22.addVertex(0.0D, 0.0D, 0.0D);
            var22.setColorRGBA_I(16798159, 0);
            var22.addVertex(-0.866D * (double)f4, (double)fa, (double)(-0.5F * f4));
            var22.addVertex(0.866D * (double)f4, (double)fa, (double)(-0.5F * f4));
            var22.addVertex(0.0D, (double)fa, (double)(1.0F * f4));
            var22.addVertex(-0.866D * (double)f4, (double)fa, (double)(-0.5F * f4));
            var22.draw();
        }

        GL11.glPopMatrix();
        GL11.glDepthMask(true);
        GL11.glDisable(GL11.GL_CULL_FACE);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glShadeModel(GL11.GL_FLAT);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glPopMatrix();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glPopMatrix();
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity ent)
    {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, ent);
    }
}
