package net.minecraft.client.addon.tchestplate.weapon.renders.models;

import net.minecraft.client.Minecraft;
import net.minecraft.client.addon.tchestplate.player.ExtendedPlayer;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.opengl.GL11;

public class ModelLSwordPredator extends BaseModelHammer
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
    ModelRenderer Shape24;
    ModelRenderer Shape25;
    ModelRenderer Shape26;
    ModelRenderer Shape27;
    ModelRenderer Shape28;
    ModelRenderer Shape29;
    ModelRenderer Shape30;
    ModelRenderer Shape31;
    ModelRenderer Shape32;
    ModelRenderer Shape33;
    ModelRenderer Shape34;
    ModelRenderer Shape35;
    ModelRenderer Shape36;
    ModelRenderer Shape37;
    ModelRenderer Shape38;
    ModelRenderer Shape39;
    ModelRenderer Shape40;
    ModelRenderer Shape41;
    ModelRenderer Shape42;
    ModelRenderer Shape43;
    ModelRenderer Shape44;
    ModelRenderer Shape50;
    ModelRenderer Shape51;
    ModelRenderer Shape52;
    ModelRenderer Shape53;
    ModelRenderer Shape54;
    ModelRenderer Shape55;
    ModelRenderer Shape56;
    ModelRenderer Shape57;

    public ModelLSwordPredator()
    {
        super.textureWidth = 64;
        super.textureHeight = 32;
        (this.Shape1 = new ModelRenderer(this, 0, 0)).addBox(0.0F, 0.0F, 1.0F, 1, 5, 8);
        this.Shape1.setRotationPoint(-5.0F, 7.0F, -5.0F);
        this.Shape1.setTextureSize(64, 32);
        this.Shape1.mirror = true;
        this.setRotation(this.Shape1, 0.0F, 0.0F, 0.0F);
        (this.Shape2 = new ModelRenderer(this, 0, 0)).addBox(0.0F, 0.0F, 0.0F, 8, 5, 2);
        this.Shape2.setRotationPoint(-4.0F, 7.0F, 3.0F);
        this.Shape2.setTextureSize(64, 32);
        this.Shape2.mirror = true;
        this.setRotation(this.Shape2, 0.0F, 0.0F, 0.0F);
        (this.Shape3 = new ModelRenderer(this, 0, 0)).addBox(0.0F, 0.0F, 0.0F, 1, 5, 8);
        this.Shape3.setRotationPoint(4.0F, 7.0F, -4.0F);
        this.Shape3.setTextureSize(64, 32);
        this.Shape3.mirror = true;
        this.setRotation(this.Shape3, 0.0F, 0.0F, 0.0F);
        (this.Shape4 = new ModelRenderer(this, 0, 0)).addBox(0.0F, 0.0F, 0.0F, 8, 6, 2);
        this.Shape4.setRotationPoint(-4.0F, 6.0F, -5.0F);
        this.Shape4.setTextureSize(64, 32);
        this.Shape4.mirror = true;
        this.setRotation(this.Shape4, 0.0F, 0.0F, 0.0F);
        (this.Shape5 = new ModelRenderer(this, 44, 17)).addBox(-3.5F, 0.0F, -0.5F, 7, 12, 3);
        this.Shape5.setRotationPoint(0.0F, 6.0F, -6.0F);
        this.Shape5.setTextureSize(64, 32);
        this.Shape5.mirror = true;
        this.setRotation(this.Shape5, -0.0698132F, 0.0F, 0.0F);
        (this.Shape6 = new ModelRenderer(this, 16, 12)).addBox(0.0F, 0.0F, 0.0F, 7, 13, 7);
        this.Shape6.setRotationPoint(-3.5F, 5.5F, -3.5F);
        this.Shape6.setTextureSize(64, 32);
        this.Shape6.mirror = true;
        this.setRotation(this.Shape6, 0.0F, 0.0F, 0.0F);
        (this.Shape7 = new ModelRenderer(this, 10, 0)).addBox(0.0F, 0.0F, 0.0F, 2, 3, 7);
        this.Shape7.setRotationPoint(-5.5F, 15.0F, -3.5F);
        this.Shape7.setTextureSize(64, 32);
        this.Shape7.mirror = true;
        this.setRotation(this.Shape7, 0.0F, 0.0F, 0.0F);
        (this.Shape8 = new ModelRenderer(this, 0, 0)).addBox(0.0F, 0.0F, 0.0F, 9, 3, 9);
        this.Shape8.setRotationPoint(-4.5F, 15.1F, -4.5F);
        this.Shape8.setTextureSize(64, 32);
        this.Shape8.mirror = true;
        this.setRotation(this.Shape8, 0.0F, 0.0F, 0.0F);
        (this.Shape9 = new ModelRenderer(this, 10, 0)).addBox(0.0F, 0.0F, 0.0F, 2, 3, 7);
        this.Shape9.setRotationPoint(3.5F, 15.0F, -3.5F);
        this.Shape9.setTextureSize(64, 32);
        this.Shape9.mirror = true;
        this.setRotation(this.Shape9, 0.0F, 0.0F, 0.0F);
        (this.Shape10 = new ModelRenderer(this, 0, 19)).addBox(-2.0F, 0.0F, -2.0F, 2, 11, 2);
        this.Shape10.setRotationPoint(0.0F, 6.0F, -6.0F);
        this.Shape10.setTextureSize(64, 32);
        this.Shape10.mirror = true;
        this.setRotation(this.Shape10, 0.0698132F, 0.0F, 0.122173F);
        (this.Shape11 = new ModelRenderer(this, 0, 19)).addBox(1.0F, 0.0F, -2.0F, 2, 11, 2);
        this.Shape11.setRotationPoint(0.0F, 6.0F, -6.0F);
        this.Shape11.setTextureSize(64, 32);
        this.Shape11.mirror = true;
        this.setRotation(this.Shape11, 0.0698132F, 0.0F, 0.122173F);
        (this.Shape12 = new ModelRenderer(this, 0, 0)).addBox(0.0F, 0.0F, 0.0F, 7, 3, 2);
        this.Shape12.setRotationPoint(-3.5F, 15.0F, 3.5F);
        this.Shape12.setTextureSize(64, 32);
        this.Shape12.mirror = true;
        this.setRotation(this.Shape12, 0.0F, 0.0F, 0.0F);
        (this.Shape13 = new ModelRenderer(this, 56, 0)).addBox(1.5F, -7.0F, -1.0F, 1, 8, 2);
        this.Shape13.setRotationPoint(0.0F, 6.0F, -6.0F);
        this.Shape13.setTextureSize(64, 32);
        this.Shape13.mirror = true;
        this.setRotation(this.Shape13, 0.0F, 0.0F, 0.122173F);
        (this.Shape14 = new ModelRenderer(this, 56, 0)).addBox(1.5F, -13.0F, -3.0F, 1, 8, 2);
        this.Shape14.setRotationPoint(0.0F, 6.0F, -6.0F);
        this.Shape14.setTextureSize(64, 32);
        this.Shape14.mirror = true;
        this.setRotation(this.Shape14, -0.1396263F, 0.0F, 0.122173F);
        (this.Shape15 = new ModelRenderer(this, 56, 0)).addBox(1.5F, -17.0F, -6.0F, 1, 7, 2);
        this.Shape15.setRotationPoint(0.0F, 6.0F, -6.0F);
        this.Shape15.setTextureSize(64, 32);
        this.Shape15.mirror = true;
        this.setRotation(this.Shape15, -((float)Math.PI / 10F), 0.0F, 0.122173F);
        (this.Shape16 = new ModelRenderer(this, 56, 0)).addBox(1.6F, -21.0F, -8.9F, 1, 7, 2);
        this.Shape16.setRotationPoint(0.0F, 6.0F, -6.0F);
        this.Shape16.setTextureSize(64, 32);
        this.Shape16.mirror = true;
        this.setRotation(this.Shape16, -0.4537856F, 0.0F, 0.122173F);
        (this.Shape17 = new ModelRenderer(this, 40, 0)).addBox(1.5F, -26.0F, -14.0F, 1, 9, 1);
        this.Shape17.setRotationPoint(0.0F, 6.0F, -6.0F);
        this.Shape17.setTextureSize(64, 32);
        this.Shape17.mirror = true;
        this.setRotation(this.Shape17, -0.6806784F, 0.0F, 0.122173F);
        (this.Shape18 = new ModelRenderer(this, 44, 0)).addBox(1.4F, -24.0F, -11.0F, 1, 5, 1);
        this.Shape18.setRotationPoint(0.0F, 6.0F, -6.0F);
        this.Shape18.setTextureSize(64, 32);
        this.Shape18.mirror = true;
        this.setRotation(this.Shape18, -0.5759587F, 0.0F, 0.122173F);
        (this.Shape19 = new ModelRenderer(this, 48, 0)).addBox(0.9F, -7.2F, -0.7F, 2, 8, 1);
        this.Shape19.setRotationPoint(0.0F, 6.0F, -6.0F);
        this.Shape19.setTextureSize(64, 32);
        this.Shape19.mirror = true;
        this.setRotation(this.Shape19, 0.0F, 0.0F, 0.122173F);
        (this.Shape20 = new ModelRenderer(this, 48, 1)).addBox(1.0F, -13.1F, -2.2F, 2, 7, 1);
        this.Shape20.setRotationPoint(0.0F, 6.0F, -6.0F);
        this.Shape20.setTextureSize(64, 32);
        this.Shape20.mirror = true;
        this.setRotation(this.Shape20, -0.0872665F, 0.0F, 0.122173F);
        (this.Shape21 = new ModelRenderer(this, 48, 1)).addBox(1.0F, -18.0F, -5.7F, 2, 7, 1);
        this.Shape21.setRotationPoint(0.0F, 6.0F, -6.0F);
        this.Shape21.setTextureSize(64, 32);
        this.Shape21.mirror = true;
        this.setRotation(this.Shape21, -((float)Math.PI / 10F), 0.0F, 0.122173F);
        (this.Shape22 = new ModelRenderer(this, 48, 0)).addBox(1.0F, -23.0F, -8.7F, 2, 7, 1);
        this.Shape22.setRotationPoint(0.0F, 6.0F, -6.0F);
        this.Shape22.setTextureSize(64, 32);
        this.Shape22.mirror = true;
        this.setRotation(this.Shape22, -0.4537856F, 0.0F, 0.122173F);
        (this.Shape23 = new ModelRenderer(this, 62, 0)).addBox(2.0F, -7.0F, 1.0F, 0, 8, 1);
        this.Shape23.setRotationPoint(0.0F, 6.0F, -6.0F);
        this.Shape23.setTextureSize(64, 32);
        this.Shape23.mirror = true;
        this.setRotation(this.Shape23, 0.0F, 0.0F, 0.122173F);
        (this.Shape24 = new ModelRenderer(this, 62, 0)).addBox(2.0F, -13.0F, -1.0F, 0, 6, 1);
        this.Shape24.setRotationPoint(0.0F, 6.0F, -6.0F);
        this.Shape24.setTextureSize(64, 32);
        this.Shape24.mirror = true;
        this.setRotation(this.Shape24, -0.1396263F, 0.0F, 0.122173F);
        (this.Shape25 = new ModelRenderer(this, 62, 0)).addBox(2.0F, -17.0F, -4.0F, 0, 6, 1);
        this.Shape25.setRotationPoint(0.0F, 6.0F, -6.0F);
        this.Shape25.setTextureSize(64, 32);
        this.Shape25.mirror = true;
        this.setRotation(this.Shape25, -0.296706F, 0.0F, 0.122173F);
        (this.Shape26 = new ModelRenderer(this, 62, 1)).addBox(2.0F, -28.0F, -8.0F, 0, 8, 1);
        this.Shape26.setRotationPoint(0.0F, 6.0F, -6.0F);
        this.Shape26.setTextureSize(64, 32);
        this.Shape26.mirror = true;
        this.setRotation(this.Shape26, -0.4712389F, 0.0F, 0.122173F);
        (this.Shape27 = new ModelRenderer(this, 62, 1)).addBox(2.0F, -21.0F, -7.0F, 0, 5, 1);
        this.Shape27.setRotationPoint(0.0F, 6.0F, -6.0F);
        this.Shape27.setTextureSize(64, 32);
        this.Shape27.mirror = true;
        this.setRotation(this.Shape27, -0.4363323F, 0.0F, 0.122173F);
        (this.Shape28 = new ModelRenderer(this, 56, 0)).addBox(-1.5F, -7.0F, -1.0F, 1, 8, 2);
        this.Shape28.setRotationPoint(0.0F, 6.0F, -6.0F);
        this.Shape28.setTextureSize(64, 32);
        this.Shape28.mirror = true;
        this.setRotation(this.Shape28, 0.0F, 0.0F, 0.122173F);
        (this.Shape29 = new ModelRenderer(this, 56, 0)).addBox(-1.5F, -13.0F, -3.0F, 1, 8, 2);
        this.Shape29.setRotationPoint(0.0F, 6.0F, -6.0F);
        this.Shape29.setTextureSize(64, 32);
        this.Shape29.mirror = true;
        this.setRotation(this.Shape29, -0.1396263F, 0.0F, 0.122173F);
        (this.Shape30 = new ModelRenderer(this, 56, 0)).addBox(-1.5F, -17.0F, -6.0F, 1, 7, 2);
        this.Shape30.setRotationPoint(0.0F, 6.0F, -6.0F);
        this.Shape30.setTextureSize(64, 32);
        this.Shape30.mirror = true;
        this.setRotation(this.Shape30, -((float)Math.PI / 10F), 0.0F, 0.122173F);
        (this.Shape31 = new ModelRenderer(this, 56, 0)).addBox(-1.6F, -21.0F, -8.9F, 1, 7, 2);
        this.Shape31.setRotationPoint(0.0F, 6.0F, -6.0F);
        this.Shape31.setTextureSize(64, 32);
        this.Shape31.mirror = true;
        this.setRotation(this.Shape31, -0.4537856F, 0.0F, 0.122173F);
        (this.Shape32 = new ModelRenderer(this, 40, 0)).addBox(-1.5F, -26.0F, -14.0F, 1, 9, 1);
        this.Shape32.setRotationPoint(0.0F, 6.0F, -6.0F);
        this.Shape32.setTextureSize(64, 32);
        this.Shape32.mirror = true;
        this.setRotation(this.Shape32, -0.6806784F, 0.0F, 0.122173F);
        (this.Shape33 = new ModelRenderer(this, 44, 0)).addBox(-1.4F, -24.0F, -11.0F, 1, 5, 1);
        this.Shape33.setRotationPoint(0.0F, 6.0F, -6.0F);
        this.Shape33.setTextureSize(64, 32);
        this.Shape33.mirror = true;
        this.setRotation(this.Shape33, -0.5759587F, 0.0F, 0.122173F);
        (this.Shape34 = new ModelRenderer(this, 50, 0)).addBox(-1.9F, -7.2F, -0.7F, 2, 8, 1);
        this.Shape34.setRotationPoint(0.0F, 6.0F, -6.0F);
        this.Shape34.setTextureSize(64, 32);
        this.Shape34.mirror = true;
        this.setRotation(this.Shape34, 0.0F, 0.0F, 0.122173F);
        (this.Shape35 = new ModelRenderer(this, 48, 1)).addBox(-2.0F, -13.1F, -2.2F, 2, 7, 1);
        this.Shape35.setRotationPoint(0.0F, 6.0F, -6.0F);
        this.Shape35.setTextureSize(64, 32);
        this.Shape35.mirror = true;
        this.setRotation(this.Shape35, -0.0872665F, 0.0F, 0.122173F);
        (this.Shape36 = new ModelRenderer(this, 48, 1)).addBox(-2.0F, -18.0F, -5.7F, 2, 7, 1);
        this.Shape36.setRotationPoint(0.0F, 6.0F, -6.0F);
        this.Shape36.setTextureSize(64, 32);
        this.Shape36.mirror = true;
        this.setRotation(this.Shape36, -((float)Math.PI / 10F), 0.0F, 0.122173F);
        (this.Shape37 = new ModelRenderer(this, 48, 0)).addBox(-2.0F, -23.0F, -8.7F, 2, 7, 1);
        this.Shape37.setRotationPoint(0.0F, 6.0F, -6.0F);
        this.Shape37.setTextureSize(64, 32);
        this.Shape37.mirror = true;
        this.setRotation(this.Shape37, -0.4537856F, 0.0F, 0.122173F);
        (this.Shape38 = new ModelRenderer(this, 62, 0)).addBox(-1.0F, -7.0F, 1.0F, 0, 8, 1);
        this.Shape38.setRotationPoint(0.0F, 6.0F, -6.0F);
        this.Shape38.setTextureSize(64, 32);
        this.Shape38.mirror = true;
        this.setRotation(this.Shape38, 0.0F, 0.0F, 0.122173F);
        (this.Shape39 = new ModelRenderer(this, 62, 0)).addBox(-1.0F, -13.0F, -1.0F, 0, 6, 1);
        this.Shape39.setRotationPoint(0.0F, 6.0F, -6.0F);
        this.Shape39.setTextureSize(64, 32);
        this.Shape39.mirror = true;
        this.setRotation(this.Shape39, -0.1396263F, 0.0F, 0.122173F);
        (this.Shape40 = new ModelRenderer(this, 62, 0)).addBox(-1.0F, -17.0F, -4.0F, 0, 6, 1);
        this.Shape40.setRotationPoint(0.0F, 6.0F, -6.0F);
        this.Shape40.setTextureSize(64, 32);
        this.Shape40.mirror = true;
        this.setRotation(this.Shape40, -0.296706F, 0.0F, 0.122173F);
        (this.Shape41 = new ModelRenderer(this, 62, 0)).addBox(-1.0F, -28.0F, -8.0F, 0, 8, 1);
        this.Shape41.setRotationPoint(0.0F, 6.0F, -6.0F);
        this.Shape41.setTextureSize(64, 32);
        this.Shape41.mirror = true;
        this.setRotation(this.Shape41, -0.4712389F, 0.0F, 0.122173F);
        (this.Shape42 = new ModelRenderer(this, 62, 1)).addBox(-1.0F, -21.0F, -7.0F, 0, 5, 1);
        this.Shape42.setRotationPoint(0.0F, 6.0F, -6.0F);
        this.Shape42.setTextureSize(64, 32);
        this.Shape42.mirror = true;
        this.setRotation(this.Shape42, -0.4363323F, 0.0F, 0.122173F);
        (this.Shape43 = new ModelRenderer(this, 0, 0)).addBox(0.0F, 0.0F, 0.0F, 12, 3, 4);
        this.Shape43.setRotationPoint(-6.0F, 15.2F, -2.0F);
        this.Shape43.setTextureSize(64, 32);
        this.Shape43.mirror = true;
        this.setRotation(this.Shape43, 0.0F, 0.0F, 0.0F);
        (this.Shape44 = new ModelRenderer(this, 0, 0)).addBox(0.0F, 0.0F, 0.0F, 11, 4, 6);
        this.Shape44.setRotationPoint(-5.5F, 7.5F, -3.0F);
        this.Shape44.setTextureSize(64, 32);
        this.Shape44.mirror = true;
        this.setRotation(this.Shape44, 0.0F, 0.0F, 0.0F);
        (this.Shape50 = new ModelRenderer(this, 44, 0)).addBox(1.5F, -7.0F, -0.5F, 1, 8, 1);
        this.Shape50.setRotationPoint(0.0F, 6.0F, -6.0F);
        this.Shape50.setTextureSize(64, 32);
        this.Shape50.mirror = true;
        this.setRotation(this.Shape50, -0.0523599F, 0.0F, 0.122173F);
        (this.Shape51 = new ModelRenderer(this, 44, 0)).addBox(-1.5F, -7.0F, -0.5F, 1, 8, 1);
        this.Shape51.setRotationPoint(0.0F, 6.0F, -6.0F);
        this.Shape51.setTextureSize(64, 32);
        this.Shape51.mirror = true;
        this.setRotation(this.Shape51, -0.0523599F, 0.0F, 0.122173F);
        (this.Shape52 = new ModelRenderer(this, 48, 1)).addBox(1.0F, -5.0F, -0.7F, 2, 6, 1);
        this.Shape52.setRotationPoint(0.0F, 6.0F, -6.0F);
        this.Shape52.setTextureSize(64, 32);
        this.Shape52.mirror = true;
        this.setRotation(this.Shape52, -0.0698132F, 0.0F, 0.122173F);
        (this.Shape53 = new ModelRenderer(this, 50, 1)).addBox(-2.0F, -5.0F, -0.7F, 2, 6, 1);
        this.Shape53.setRotationPoint(0.0F, 6.0F, -6.0F);
        this.Shape53.setTextureSize(64, 32);
        this.Shape53.mirror = true;
        this.setRotation(this.Shape53, -0.0698132F, 0.0F, 0.122173F);
        (this.Shape54 = new ModelRenderer(this, 40, 1)).addBox(1.5F, -7.0F, -1.5F, 1, 7, 1);
        this.Shape54.setRotationPoint(0.0F, 6.0F, -6.0F);
        this.Shape54.setTextureSize(64, 32);
        this.Shape54.mirror = true;
        this.setRotation(this.Shape54, -0.1919862F, 0.0F, 0.122173F);
        (this.Shape55 = new ModelRenderer(this, 40, 1)).addBox(-1.5F, -7.0F, -1.5F, 1, 7, 1);
        this.Shape55.setRotationPoint(0.0F, 6.0F, -6.0F);
        this.Shape55.setTextureSize(64, 32);
        this.Shape55.mirror = true;
        this.setRotation(this.Shape55, -0.1919862F, 0.0F, 0.122173F);
        (this.Shape56 = new ModelRenderer(this, 62, 0)).addBox(-1.0F, -7.0F, 0.0F, 0, 8, 1);
        this.Shape56.setRotationPoint(0.0F, 6.0F, -6.0F);
        this.Shape56.setTextureSize(64, 32);
        this.Shape56.mirror = true;
        this.setRotation(this.Shape56, 0.0F, 0.0F, 0.122173F);
        (this.Shape57 = new ModelRenderer(this, 62, 0)).addBox(2.0F, -7.0F, 0.0F, 0, 8, 1);
        this.Shape57.setRotationPoint(0.0F, 6.0F, -6.0F);
        this.Shape57.setTextureSize(64, 32);
        this.Shape57.mirror = true;
        this.setRotation(this.Shape57, 0.0F, 0.0F, 0.122173F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        boolean flag = false;
        boolean invise = false;

        if (entity instanceof EntityPlayer)
        {
            ExtendedPlayer pi = ExtendedPlayer.get((EntityPlayer)entity);

            if (pi.RightSwingCustom > 0)
            {
                flag = true;
            }

            invise = pi.PredatorMode == 50;
        }

        if (invise)
        {
            Minecraft.getMinecraft().renderEngine.bindTexture("/mods/LavaChestPlate/textures/items/renders/predatorBlack.png");
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
        }
        else
        {
            Minecraft.getMinecraft().renderEngine.bindTexture("/mods/LavaChestPlate/textures/items/renders/predatorSword.png");
        }

        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.Shape1.render(f5);
        this.Shape2.render(f5);
        this.Shape3.render(f5);
        this.Shape4.render(f5);
        this.Shape5.render(f5);
        this.Shape7.render(f5);
        this.Shape8.render(f5);
        this.Shape9.render(f5);
        this.Shape10.render(f5);
        this.Shape11.render(f5);
        this.Shape12.render(f5);
        this.Shape43.render(f5);
        this.Shape44.render(f5);

        if (flag)
        {
            this.Shape13.render(f5);
            this.Shape14.render(f5);
            this.Shape15.render(f5);
            this.Shape16.render(f5);
            this.Shape17.render(f5);
            this.Shape18.render(f5);
            this.Shape19.render(f5);
            this.Shape20.render(f5);
            this.Shape21.render(f5);
            this.Shape22.render(f5);
            this.Shape23.render(f5);
            this.Shape24.render(f5);
            this.Shape25.render(f5);
            this.Shape26.render(f5);
            this.Shape27.render(f5);
            this.Shape28.render(f5);
            this.Shape29.render(f5);
            this.Shape30.render(f5);
            this.Shape31.render(f5);
            this.Shape32.render(f5);
            this.Shape33.render(f5);
            this.Shape34.render(f5);
            this.Shape35.render(f5);
            this.Shape36.render(f5);
            this.Shape37.render(f5);
            this.Shape38.render(f5);
            this.Shape39.render(f5);
            this.Shape40.render(f5);
            this.Shape41.render(f5);
            this.Shape42.render(f5);
        }
        else
        {
            this.Shape50.render(f5);
            this.Shape51.render(f5);
            this.Shape52.render(f5);
            this.Shape53.render(f5);
            this.Shape54.render(f5);
            this.Shape55.render(f5);
            this.Shape56.render(f5);
            this.Shape57.render(f5);
        }

        if (invise)
        {
            GL11.glDisable(GL11.GL_BLEND);
        }
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