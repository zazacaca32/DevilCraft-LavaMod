package net.minecraft.client.addon.tchestplate.weapon.renders.models;

import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.src.ModLoader;
import org.lwjgl.opengl.GL11;

public class ModelHeroHammer extends BaseModelHammer
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
    ModelRenderer Shape45;
    ModelRenderer Shape46;
    ModelRenderer Shape47;
    ModelRenderer Shape48;
    ModelRenderer Shape49;
    ModelRenderer Shape50;
    ModelRenderer Shape51;
    ModelRenderer Shape52;
    ModelRenderer Shape53;
    ModelRenderer Shape54;
    ModelRenderer Shape55;
    ModelRenderer Shape56;
    ModelRenderer Shape57;
    ModelRenderer Shape58;
    ModelRenderer Shape59;
    ModelRenderer Shape60;
    ModelRenderer Shape61;
    ModelRenderer Shape62;
    ModelRenderer Shape63;
    ModelRenderer Shape64;
    ModelRenderer Shape65;
    ModelRenderer Shape66;
    ModelRenderer Shape67;
    ModelRenderer Shape68;
    ModelRenderer Shape69;
    ModelRenderer Shape70;
    ModelRenderer Shape71;
    ModelRenderer Shape72;
    ModelRenderer Shape73;
    ModelRenderer Shape74;
    ModelRenderer Shape75;
    ModelRenderer Shape76;
    ModelRenderer Shape77;
    ModelRenderer Shape78;

    public ModelHeroHammer()
    {
        super.textureWidth = 64;
        super.textureHeight = 64;
        this.Shape1 = new ModelRenderer(this, 55, 15);
        this.Shape1.addBox(0.0F, 0.0F, 0.0F, 2, 12, 2);
        this.Shape1.setRotationPoint(-1.0F, 0.0F, -1.0F);
        this.Shape1.setTextureSize(64, 64);
        this.Shape1.mirror = true;
        this.setRotation(this.Shape1, 0.0F, 0.0F, 0.0F);
        this.Shape2 = new ModelRenderer(this, 0, 0);
        this.Shape2.addBox(0.0F, 0.0F, 0.0F, 3, 11, 2);
        this.Shape2.setRotationPoint(-1.0F, 12.0F, -1.0F);
        this.Shape2.setTextureSize(64, 64);
        this.Shape2.mirror = true;
        this.setRotation(this.Shape2, 0.0F, 0.0F, 0.0F);
        this.Shape3 = new ModelRenderer(this, 0, 0);
        this.Shape3.addBox(0.0F, 0.0F, 0.0F, 1, 10, 2);
        this.Shape3.setRotationPoint(-2.0F, 12.0F, -1.0F);
        this.Shape3.setTextureSize(64, 64);
        this.Shape3.mirror = true;
        this.setRotation(this.Shape3, 0.0F, 0.0F, 0.0F);
        this.Shape4 = new ModelRenderer(this, 0, 0);
        this.Shape4.addBox(0.0F, 13.0F, 0.0F, 1, 9, 1);
        this.Shape4.setRotationPoint(-1.0F, 0.0F, -2.0F);
        this.Shape4.setTextureSize(64, 64);
        this.Shape4.mirror = true;
        this.setRotation(this.Shape4, 0.0F, 0.0F, 0.0F);
        this.Shape5 = new ModelRenderer(this, 0, 0);
        this.Shape5.addBox(0.0F, 0.0F, 0.0F, 1, 7, 1);
        this.Shape5.setRotationPoint(0.0F, 15.0F, 1.0F);
        this.Shape5.setTextureSize(64, 64);
        this.Shape5.mirror = true;
        this.setRotation(this.Shape5, 0.0F, 0.0F, 0.0F);
        this.Shape6 = new ModelRenderer(this, 0, 0);
        this.Shape6.addBox(0.0F, 13.0F, 0.0F, 1, 9, 1);
        this.Shape6.setRotationPoint(-1.0F, 0.0F, 1.0F);
        this.Shape6.setTextureSize(64, 64);
        this.Shape6.mirror = true;
        this.setRotation(this.Shape6, 0.0F, 0.0F, 0.0F);
        this.Shape7 = new ModelRenderer(this, 0, 0);
        this.Shape7.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1);
        this.Shape7.setRotationPoint(1.0F, 19.0F, 1.0F);
        this.Shape7.setTextureSize(64, 64);
        this.Shape7.mirror = true;
        this.setRotation(this.Shape7, 0.0F, 0.0F, 0.0F);
        this.Shape8 = new ModelRenderer(this, 0, 0);
        this.Shape8.addBox(0.0F, 0.0F, 0.0F, 1, 7, 1);
        this.Shape8.setRotationPoint(0.0F, 15.0F, -2.0F);
        this.Shape8.setTextureSize(64, 64);
        this.Shape8.mirror = true;
        this.setRotation(this.Shape8, 0.0F, 0.0F, 0.0F);
        this.Shape9 = new ModelRenderer(this, 0, 0);
        this.Shape9.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1);
        this.Shape9.setRotationPoint(1.0F, 19.0F, -2.0F);
        this.Shape9.setTextureSize(64, 64);
        this.Shape9.mirror = true;
        this.setRotation(this.Shape9, 0.0F, 0.0F, 0.0F);
        this.Shape10 = new ModelRenderer(this, 0, 0);
        this.Shape10.addBox(0.0F, 0.0F, 0.0F, 1, 5, 1);
        this.Shape10.setRotationPoint(-2.0F, 17.0F, -2.0F);
        this.Shape10.setTextureSize(64, 64);
        this.Shape10.mirror = true;
        this.setRotation(this.Shape10, 0.0F, 0.0F, 0.0F);
        this.Shape11 = new ModelRenderer(this, 0, 0);
        this.Shape11.addBox(0.0F, 0.0F, 0.0F, 1, 5, 1);
        this.Shape11.setRotationPoint(-2.0F, 17.0F, 1.0F);
        this.Shape11.setTextureSize(64, 64);
        this.Shape11.mirror = true;
        this.setRotation(this.Shape11, 0.0F, 0.0F, 0.0F);
        this.Shape12 = new ModelRenderer(this, 0, 0);
        this.Shape12.addBox(0.0F, 0.0F, 0.0F, 1, 5, 2);
        this.Shape12.setRotationPoint(2.0F, -7.0F, -1.0F);
        this.Shape12.setTextureSize(64, 64);
        this.Shape12.mirror = true;
        this.setRotation(this.Shape12, 0.0F, 0.0F, 0.0F);
        this.Shape13 = new ModelRenderer(this, 0, 0);
        this.Shape13.addBox(0.0F, 0.0F, 0.0F, 6, 4, 2);
        this.Shape13.setRotationPoint(-2.0F, -11.0F, -1.0F);
        this.Shape13.setTextureSize(64, 64);
        this.Shape13.mirror = true;
        this.setRotation(this.Shape13, 0.0F, 0.0F, 0.0F);
        this.Shape14 = new ModelRenderer(this, 0, 0);
        this.Shape14.addBox(0.0F, 0.0F, 0.0F, 3, 4, 2);
        this.Shape14.setRotationPoint(-1.0F, -4.0F, -1.0F);
        this.Shape14.setTextureSize(64, 64);
        this.Shape14.mirror = true;
        this.setRotation(this.Shape14, 0.0F, 0.0F, 0.0F);
        this.Shape15 = new ModelRenderer(this, 0, 10);
        this.Shape15.addBox(0.0F, 0.0F, 0.0F, 2, 1, 2);
        this.Shape15.setRotationPoint(3.0F, -16.0F, -1.0F);
        this.Shape15.setTextureSize(64, 64);
        this.Shape15.mirror = true;
        this.setRotation(this.Shape15, 0.0F, 0.0F, 0.0F);
        this.Shape16 = new ModelRenderer(this, 0, 0);
        this.Shape16.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
        this.Shape16.setRotationPoint(-2.0F, -15.0F, -0.5F);
        this.Shape16.setTextureSize(64, 64);
        this.Shape16.mirror = true;
        this.setRotation(this.Shape16, 0.0F, 0.0F, 0.0F);
        this.Shape17 = new ModelRenderer(this, 0, 8);
        this.Shape17.addBox(0.0F, 0.0F, 0.0F, 1, 4, 4);
        this.Shape17.setRotationPoint(1.0F, -6.0F, -2.0F);
        this.Shape17.setTextureSize(64, 64);
        this.Shape17.mirror = true;
        this.setRotation(this.Shape17, 0.0F, 0.0F, 0.0F);
        this.Shape18 = new ModelRenderer(this, 0, 12);
        this.Shape18.addBox(0.0F, 0.0F, 0.0F, 4, 3, 1);
        this.Shape18.setRotationPoint(-1.0F, -12.0F, -2.0F);
        this.Shape18.setTextureSize(64, 64);
        this.Shape18.mirror = true;
        this.setRotation(this.Shape18, 0.0F, 0.0F, 0.0F);
        this.Shape19 = new ModelRenderer(this, 0, 0);
        this.Shape19.addBox(0.0F, 0.0F, 0.0F, 3, 2, 2);
        this.Shape19.setRotationPoint(-1.0F, -7.0F, -1.0F);
        this.Shape19.setTextureSize(64, 64);
        this.Shape19.mirror = true;
        this.setRotation(this.Shape19, 0.0F, 0.0F, 0.0F);
        this.Shape20 = new ModelRenderer(this, 0, 12);
        this.Shape20.addBox(0.0F, 0.0F, 0.0F, 4, 3, 1);
        this.Shape20.setRotationPoint(-1.0F, -12.0F, 1.0F);
        this.Shape20.setTextureSize(64, 64);
        this.Shape20.mirror = true;
        this.setRotation(this.Shape20, 0.0F, 0.0F, 0.0F);
        this.Shape21 = new ModelRenderer(this, 0, 8);
        this.Shape21.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
        this.Shape21.setRotationPoint(3.0F, -15.0F, -0.5F);
        this.Shape21.setTextureSize(64, 64);
        this.Shape21.mirror = true;
        this.setRotation(this.Shape21, 0.0F, 0.0F, 0.0F);
        this.Shape22 = new ModelRenderer(this, 0, 12);
        this.Shape22.addBox(0.0F, 0.0F, 0.0F, 4, 4, 2);
        this.Shape22.setRotationPoint(-1.0F, -15.0F, -1.0F);
        this.Shape22.setTextureSize(64, 64);
        this.Shape22.mirror = true;
        this.setRotation(this.Shape22, 0.0F, 0.0F, 0.0F);
        this.Shape23 = new ModelRenderer(this, 0, 8);
        this.Shape23.addBox(0.0F, 0.0F, 0.0F, 5, 3, 4);
        this.Shape23.setRotationPoint(0.0F, -23.0F, -2.0F);
        this.Shape23.setTextureSize(64, 64);
        this.Shape23.mirror = true;
        this.setRotation(this.Shape23, 0.0F, 0.0F, 0.0F);
        this.Shape24 = new ModelRenderer(this, 6, 5);
        this.Shape24.addBox(-2.0F, 0.0F, 0.0F, 4, 1, 2);
        this.Shape24.setRotationPoint(0.0F, -26.0F, -1.0F);
        this.Shape24.setTextureSize(64, 64);
        this.Shape24.mirror = true;
        this.setRotation(this.Shape24, 0.0F, 0.0F, 0.0F);
        this.Shape25 = new ModelRenderer(this, 0, 2);
        this.Shape25.addBox(0.0F, 0.0F, 0.0F, 2, 1, 2);
        this.Shape25.setRotationPoint(-3.0F, -16.0F, -1.0F);
        this.Shape25.setTextureSize(64, 64);
        this.Shape25.mirror = true;
        this.setRotation(this.Shape25, 0.0F, 0.0F, 0.0F);
        this.Shape26 = new ModelRenderer(this, 0, 3);
        this.Shape26.addBox(0.0F, 0.0F, 0.0F, 2, 2, 2);
        this.Shape26.setRotationPoint(-4.0F, -20.0F, -1.0F);
        this.Shape26.setTextureSize(64, 64);
        this.Shape26.mirror = true;
        this.setRotation(this.Shape26, 0.0F, 0.0F, 0.0F);
        this.Shape27 = new ModelRenderer(this, 0, 5);
        this.Shape27.addBox(0.0F, 0.0F, 0.0F, 1, 1, 2);
        this.Shape27.setRotationPoint(-3.0F, -21.0F, -1.0F);
        this.Shape27.setTextureSize(64, 64);
        this.Shape27.mirror = true;
        this.setRotation(this.Shape27, 0.0F, 0.0F, 0.0F);
        this.Shape28 = new ModelRenderer(this, 0, 7);
        this.Shape28.addBox(0.0F, 0.0F, 0.0F, 1, 1, 2);
        this.Shape28.setRotationPoint(-2.0F, -22.0F, -1.0F);
        this.Shape28.setTextureSize(64, 64);
        this.Shape28.mirror = true;
        this.setRotation(this.Shape28, 0.0F, 0.0F, 0.0F);
        this.Shape29 = new ModelRenderer(this, 0, 10);
        this.Shape29.addBox(0.0F, 0.0F, 0.0F, 1, 1, 2);
        this.Shape29.setRotationPoint(-1.0F, -23.0F, -1.0F);
        this.Shape29.setTextureSize(64, 64);
        this.Shape29.mirror = true;
        this.setRotation(this.Shape29, 0.0F, 0.0F, 0.0F);
        this.Shape30 = new ModelRenderer(this, 0, 6);
        this.Shape30.addBox(0.0F, -0.5F, 0.0F, 3, 2, 1);
        this.Shape30.setRotationPoint(-2.0F, -27.0F, 1.0F);
        this.Shape30.setTextureSize(64, 64);
        this.Shape30.mirror = true;
        this.setRotation(this.Shape30, 0.0F, 0.0F, 0.0F);
        this.Shape31 = new ModelRenderer(this, 0, 10);
        this.Shape31.addBox(0.0F, 0.0F, 0.0F, 1, 2, 2);
        this.Shape31.setRotationPoint(0.0F, -25.0F, -1.0F);
        this.Shape31.setTextureSize(64, 64);
        this.Shape31.mirror = true;
        this.setRotation(this.Shape31, 0.0F, 0.0F, 0.0F);
        this.Shape32 = new ModelRenderer(this, 0, 10);
        this.Shape32.addBox(0.0F, 0.0F, 0.0F, 1, 1, 2);
        this.Shape32.setRotationPoint(1.0F, -25.0F, -1.0F);
        this.Shape32.setTextureSize(64, 64);
        this.Shape32.mirror = true;
        this.setRotation(this.Shape32, 0.0F, 0.0F, 0.0F);
        this.Shape33 = new ModelRenderer(this, 0, 7);
        this.Shape33.addBox(0.0F, 0.0F, 0.0F, 1, 2, 2);
        this.Shape33.setRotationPoint(-1.0F, -26.0F, -1.0F);
        this.Shape33.setTextureSize(64, 64);
        this.Shape33.mirror = true;
        this.setRotation(this.Shape33, 0.0F, 0.0F, 0.0F);
        this.Shape34 = new ModelRenderer(this, 0, 6);
        this.Shape34.addBox(0.0F, 0.0F, 0.0F, 2, 1, 4);
        this.Shape34.setRotationPoint(-1.0F, -26.0F, -2.0F);
        this.Shape34.setTextureSize(64, 64);
        this.Shape34.mirror = true;
        this.setRotation(this.Shape34, 0.0F, 0.0F, 0.0F);
        this.Shape35 = new ModelRenderer(this, 0, 6);
        this.Shape35.addBox(0.0F, -0.5F, 0.0F, 3, 2, 1);
        this.Shape35.setRotationPoint(-2.0F, -27.0F, -2.0F);
        this.Shape35.setTextureSize(64, 64);
        this.Shape35.mirror = true;
        this.setRotation(this.Shape35, 0.0F, 0.0F, 0.0F);
        this.Shape36 = new ModelRenderer(this, 0, 4);
        this.Shape36.addBox(0.0F, 0.0F, 0.0F, 4, 2, 2);
        this.Shape36.setRotationPoint(-5.0F, -18.0F, -1.0F);
        this.Shape36.setTextureSize(64, 64);
        this.Shape36.mirror = true;
        this.setRotation(this.Shape36, 0.0F, 0.0F, 0.0F);
        this.Shape37 = new ModelRenderer(this, 0, 0);
        this.Shape37.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1);
        this.Shape37.setRotationPoint(9.0F, -7.0F, -0.5F);
        this.Shape37.setTextureSize(64, 64);
        this.Shape37.mirror = true;
        this.setRotation(this.Shape37, 0.0F, 0.0F, 0.0F);
        this.Shape38 = new ModelRenderer(this, 0, 8);
        this.Shape38.addBox(0.0F, 0.0F, 0.0F, 1, 3, 4);
        this.Shape38.setRotationPoint(-1.0F, -22.0F, -2.0F);
        this.Shape38.setTextureSize(64, 64);
        this.Shape38.mirror = true;
        this.setRotation(this.Shape38, 0.0F, 0.0F, 0.0F);
        this.Shape39 = new ModelRenderer(this, 0, 8);
        this.Shape39.addBox(0.0F, 0.0F, 0.0F, 1, 4, 4);
        this.Shape39.setRotationPoint(-2.0F, -21.0F, -2.0F);
        this.Shape39.setTextureSize(64, 64);
        this.Shape39.mirror = true;
        this.setRotation(this.Shape39, 0.0F, 0.0F, 0.0F);
        this.Shape40 = new ModelRenderer(this, 0, 8);
        this.Shape40.addBox(0.0F, 0.0F, 0.0F, 1, 5, 2);
        this.Shape40.setRotationPoint(4.0F, -21.0F, -1.0F);
        this.Shape40.setTextureSize(64, 64);
        this.Shape40.mirror = true;
        this.setRotation(this.Shape40, 0.0F, 0.0F, 0.0F);
        this.Shape41 = new ModelRenderer(this, 0, 8);
        this.Shape41.addBox(0.0F, 0.0F, 0.0F, 3, 1, 2);
        this.Shape41.setRotationPoint(4.0F, -20.0F, -1.0F);
        this.Shape41.setTextureSize(64, 64);
        this.Shape41.mirror = true;
        this.setRotation(this.Shape41, 0.0F, 0.0F, 0.0F);
        this.Shape42 = new ModelRenderer(this, 0, 8);
        this.Shape42.addBox(0.0F, 0.0F, 0.0F, 3, 1, 2);
        this.Shape42.setRotationPoint(6.0F, -21.0F, -1.0F);
        this.Shape42.setTextureSize(64, 64);
        this.Shape42.mirror = true;
        this.setRotation(this.Shape42, 0.0F, 0.0F, 0.0F);
        this.Shape43 = new ModelRenderer(this, 0, 8);
        this.Shape43.addBox(0.0F, 0.0F, 0.0F, 1, 1, 2);
        this.Shape43.setRotationPoint(13.0F, -22.0F, -1.0F);
        this.Shape43.setTextureSize(64, 64);
        this.Shape43.mirror = true;
        this.setRotation(this.Shape43, 0.0F, 0.0F, 0.0F);
        this.Shape44 = new ModelRenderer(this, 0, 8);
        this.Shape44.addBox(0.0F, 0.0F, 0.0F, 4, 3, 2);
        this.Shape44.setRotationPoint(10.0F, -19.0F, -1.0F);
        this.Shape44.setTextureSize(64, 64);
        this.Shape44.mirror = true;
        this.setRotation(this.Shape44, 0.0F, 0.0F, 0.0F);
        this.Shape45 = new ModelRenderer(this, 0, 8);
        this.Shape45.addBox(0.0F, 0.0F, 0.0F, 2, 5, 2);
        this.Shape45.setRotationPoint(11.0F, -16.0F, -1.0F);
        this.Shape45.setTextureSize(64, 64);
        this.Shape45.mirror = true;
        this.setRotation(this.Shape45, 0.0F, 0.0F, 0.0F);
        this.Shape46 = new ModelRenderer(this, 0, 8);
        this.Shape46.addBox(0.0F, 0.0F, 0.0F, 2, 3, 2);
        this.Shape46.setRotationPoint(10.0F, -13.0F, -1.0F);
        this.Shape46.setTextureSize(64, 64);
        this.Shape46.mirror = true;
        this.setRotation(this.Shape46, 0.0F, 0.0F, 0.0F);
        this.Shape47 = new ModelRenderer(this, 0, 8);
        this.Shape47.addBox(0.0F, 0.0F, 0.0F, 7, 1, 2);
        this.Shape47.setRotationPoint(8.0F, -20.0F, -1.0F);
        this.Shape47.setTextureSize(64, 64);
        this.Shape47.mirror = true;
        this.setRotation(this.Shape47, 0.0F, 0.0F, 0.0F);
        this.Shape48 = new ModelRenderer(this, 0, 8);
        this.Shape48.addBox(0.0F, 0.0F, 0.0F, 3, 4, 2);
        this.Shape48.setRotationPoint(10.0F, -24.0F, -1.0F);
        this.Shape48.setTextureSize(64, 64);
        this.Shape48.mirror = true;
        this.setRotation(this.Shape48, 0.0F, 0.0F, 0.0F);
        this.Shape49 = new ModelRenderer(this, 0, 8);
        this.Shape49.addBox(0.0F, 0.0F, 0.0F, 4, 2, 2);
        this.Shape49.setRotationPoint(13.0F, -25.0F, -1.0F);
        this.Shape49.setTextureSize(64, 64);
        this.Shape49.mirror = true;
        this.setRotation(this.Shape49, 0.0F, 0.0F, 0.0F);
        this.Shape50 = new ModelRenderer(this, 0, 8);
        this.Shape50.addBox(0.0F, 0.0F, 0.0F, 1, 1, 2);
        this.Shape50.setRotationPoint(4.0F, -22.0F, -1.0F);
        this.Shape50.setTextureSize(64, 64);
        this.Shape50.mirror = true;
        this.setRotation(this.Shape50, 0.0F, 0.0F, 0.0F);
        this.Shape51 = new ModelRenderer(this, 0, 8);
        this.Shape51.addBox(0.0F, 0.0F, 0.0F, 2, 1, 2);
        this.Shape51.setRotationPoint(16.0F, -26.0F, -1.0F);
        this.Shape51.setTextureSize(64, 64);
        this.Shape51.mirror = true;
        this.setRotation(this.Shape51, 0.0F, 0.0F, 0.0F);
        this.Shape52 = new ModelRenderer(this, 0, 8);
        this.Shape52.addBox(0.0F, 0.0F, 0.0F, 8, 1, 2);
        this.Shape52.setRotationPoint(4.0F, -26.0F, -1.0F);
        this.Shape52.setTextureSize(64, 64);
        this.Shape52.mirror = true;
        this.setRotation(this.Shape52, 0.0F, 0.0F, 0.0F);
        this.Shape53 = new ModelRenderer(this, 0, 8);
        this.Shape53.addBox(0.0F, 0.0F, 0.0F, 6, 2, 4);
        this.Shape53.setRotationPoint(6.0F, -25.0F, -2.0F);
        this.Shape53.setTextureSize(64, 64);
        this.Shape53.mirror = true;
        this.setRotation(this.Shape53, 0.0F, 0.0F, 0.0F);
        this.Shape54 = new ModelRenderer(this, 0, 8);
        this.Shape54.addBox(0.0F, 0.0F, 0.0F, 4, 3, 2);
        this.Shape54.setRotationPoint(3.0F, -25.0F, -1.0F);
        this.Shape54.setTextureSize(64, 64);
        this.Shape54.mirror = true;
        this.setRotation(this.Shape54, 0.0F, 0.0F, 0.0F);
        this.Shape55 = new ModelRenderer(this, 0, 8);
        this.Shape55.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4);
        this.Shape55.setRotationPoint(6.0F, -23.0F, -2.0F);
        this.Shape55.setTextureSize(64, 64);
        this.Shape55.mirror = true;
        this.setRotation(this.Shape55, 0.0F, 0.0F, 0.0F);
        this.Shape56 = new ModelRenderer(this, 0, 8);
        this.Shape56.addBox(0.0F, 0.0F, 0.0F, 4, 3, 4);
        this.Shape56.setRotationPoint(1.0F, -24.0F, -2.0F);
        this.Shape56.setTextureSize(64, 64);
        this.Shape56.mirror = true;
        this.setRotation(this.Shape56, 0.0F, 0.0F, 0.0F);
        this.Shape57 = new ModelRenderer(this, 0, 8);
        this.Shape57.addBox(0.0F, 0.0F, 0.0F, 6, 4, 2);
        this.Shape57.setRotationPoint(7.0F, -25.0F, -1.0F);
        this.Shape57.setTextureSize(64, 64);
        this.Shape57.mirror = true;
        this.setRotation(this.Shape57, 0.0F, 0.0F, 0.0F);
        this.Shape58 = new ModelRenderer(this, 40, 6);
        this.Shape58.addBox(0.0F, 0.0F, 0.0F, 3, 3, 3);
        this.Shape58.setRotationPoint(0.0F, -17.3F, -2.0F);
        this.Shape58.setTextureSize(64, 64);
        this.Shape58.mirror = true;
        this.setRotation(this.Shape58, 0.8726646F, 0.0F, 0.0F);
        this.Shape59 = new ModelRenderer(this, 0, 8);
        this.Shape59.addBox(0.0F, 0.0F, 0.0F, 1, 2, 2);
        this.Shape59.setRotationPoint(10.0F, -10.0F, -1.0F);
        this.Shape59.setTextureSize(64, 64);
        this.Shape59.mirror = true;
        this.setRotation(this.Shape59, 0.0F, 0.0F, 0.0F);
        this.Shape60 = new ModelRenderer(this, 0, 0);
        this.Shape60.addBox(0.0F, 0.0F, 0.0F, 1, 5, 1);
        this.Shape60.setRotationPoint(18.0F, -28.0F, -0.5F);
        this.Shape60.setTextureSize(64, 64);
        this.Shape60.mirror = true;
        this.setRotation(this.Shape60, 0.0F, 0.0F, 0.0F);
        this.Shape61 = new ModelRenderer(this, 0, 0);
        this.Shape61.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1);
        this.Shape61.setRotationPoint(10.0F, -8.0F, -0.5F);
        this.Shape61.setTextureSize(64, 64);
        this.Shape61.mirror = true;
        this.setRotation(this.Shape61, 0.0F, 0.0F, 0.0F);
        this.Shape62 = new ModelRenderer(this, 0, 0);
        this.Shape62.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1);
        this.Shape62.setRotationPoint(11.0F, -10.0F, -0.5F);
        this.Shape62.setTextureSize(64, 64);
        this.Shape62.mirror = true;
        this.setRotation(this.Shape62, 0.0F, 0.0F, 0.0F);
        this.Shape63 = new ModelRenderer(this, 0, 0);
        this.Shape63.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1);
        this.Shape63.setRotationPoint(12.0F, -11.0F, -0.5F);
        this.Shape63.setTextureSize(64, 64);
        this.Shape63.mirror = true;
        this.setRotation(this.Shape63, 0.0F, 0.0F, 0.0F);
        this.Shape64 = new ModelRenderer(this, 0, 0);
        this.Shape64.addBox(0.0F, 0.0F, 0.0F, 1, 6, 1);
        this.Shape64.setRotationPoint(13.0F, -16.0F, -0.5F);
        this.Shape64.setTextureSize(64, 64);
        this.Shape64.mirror = true;
        this.setRotation(this.Shape64, 0.0F, 0.0F, 0.0F);
        this.Shape65 = new ModelRenderer(this, 0, 0);
        this.Shape65.addBox(0.0F, 0.0F, 0.0F, 1, 5, 1);
        this.Shape65.setRotationPoint(14.0F, -19.0F, -0.5F);
        this.Shape65.setTextureSize(64, 64);
        this.Shape65.mirror = true;
        this.setRotation(this.Shape65, 0.0F, 0.0F, 0.0F);
        this.Shape66 = new ModelRenderer(this, 0, 0);
        this.Shape66.addBox(0.0F, 0.0F, 0.0F, 1, 5, 1);
        this.Shape66.setRotationPoint(15.0F, -21.0F, -0.5F);
        this.Shape66.setTextureSize(64, 64);
        this.Shape66.mirror = true;
        this.setRotation(this.Shape66, 0.0F, 0.0F, 0.0F);
        this.Shape67 = new ModelRenderer(this, 0, 0);
        this.Shape67.addBox(0.0F, 0.0F, 0.0F, 1, 4, 1);
        this.Shape67.setRotationPoint(16.0F, -23.0F, -0.5F);
        this.Shape67.setTextureSize(64, 64);
        this.Shape67.mirror = true;
        this.setRotation(this.Shape67, 0.0F, 0.0F, 0.0F);
        this.Shape68 = new ModelRenderer(this, 0, 0);
        this.Shape68.addBox(0.0F, 0.0F, 0.0F, 1, 6, 1);
        this.Shape68.setRotationPoint(17.0F, -27.0F, -0.5F);
        this.Shape68.setTextureSize(64, 64);
        this.Shape68.mirror = true;
        this.setRotation(this.Shape68, 0.0F, 0.0F, 0.0F);
        this.Shape69 = new ModelRenderer(this, 0, 8);
        this.Shape69.addBox(0.0F, 0.0F, 0.0F, 4, 4, 4);
        this.Shape69.setRotationPoint(2.0F, -25.0F, -2.0F);
        this.Shape69.setTextureSize(64, 64);
        this.Shape69.mirror = true;
        this.setRotation(this.Shape69, 0.0F, 0.0F, 0.0F);
        this.Shape70 = new ModelRenderer(this, 40, 6);
        this.Shape70.addBox(0.0F, 0.0F, 0.0F, 3, 3, 3);
        this.Shape70.setRotationPoint(1.5F, -19.8F, -1.5F);
        this.Shape70.setTextureSize(64, 64);
        this.Shape70.mirror = true;
        this.setRotation(this.Shape70, 0.0F, 0.0F, 0.8726646F);
        this.Shape71 = new ModelRenderer(this, 0, 2);
        this.Shape71.addBox(-6.0F, -17.0F, -1.0F, 2, 2, 2);
        this.Shape71.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape71.setTextureSize(64, 64);
        this.Shape71.mirror = true;
        this.setRotation(this.Shape71, 0.0F, 0.0F, 0.0F);
        this.Shape72 = new ModelRenderer(this, 0, 1);
        this.Shape72.addBox(-7.0F, -16.0F, -1.0F, 2, 2, 2);
        this.Shape72.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape72.setTextureSize(64, 64);
        this.Shape72.mirror = true;
        this.setRotation(this.Shape72, 0.0F, 0.0F, 0.0F);
        this.Shape73 = new ModelRenderer(this, 0, 0);
        this.Shape73.addBox(-8.5F, -14.0F, -1.0F, 3, 1, 2);
        this.Shape73.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape73.setTextureSize(64, 64);
        this.Shape73.mirror = true;
        this.setRotation(this.Shape73, 0.0F, 0.0F, 0.0F);
        this.Shape74 = new ModelRenderer(this, 0, 0);
        this.Shape74.addBox(-9.0F, -14.0F, -0.5F, 1, 1, 1);
        this.Shape74.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape74.setTextureSize(64, 64);
        this.Shape74.mirror = true;
        this.setRotation(this.Shape74, 0.0F, 0.0F, 0.0F);
        this.Shape75 = new ModelRenderer(this, 0, 0);
        this.Shape75.addBox(-7.5F, -15.0F, -1.0F, 1, 1, 2);
        this.Shape75.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape75.setTextureSize(64, 64);
        this.Shape75.mirror = true;
        this.setRotation(this.Shape75, 0.0F, 0.0F, 0.0F);
        this.Shape76 = new ModelRenderer(this, 0, 0);
        this.Shape76.addBox(-8.0F, -0.5F, -0.5F, 1, 1, 1);
        this.Shape76.setRotationPoint(0.0F, -14.0F, 0.0F);
        this.Shape76.setTextureSize(64, 64);
        this.Shape76.mirror = true;
        this.setRotation(this.Shape76, 0.0F, 0.0F, 0.0F);
        this.Shape77 = new ModelRenderer(this, 0, 4);
        this.Shape77.addBox(-5.0F, -18.5F, -1.0F, 1, 1, 2);
        this.Shape77.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape77.setTextureSize(64, 64);
        this.Shape77.mirror = true;
        this.setRotation(this.Shape77, 0.0F, 0.0F, 0.0F);
        this.Shape78 = new ModelRenderer(this, 0, 4);
        this.Shape78.addBox(-4.5F, -19.0F, -0.5F, 1, 1, 1);
        this.Shape78.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape78.setTextureSize(64, 64);
        this.Shape78.mirror = true;
        this.setRotation(this.Shape78, 0.0F, 0.0F, 0.0F);
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
        this.Shape43.render(f5);
        this.Shape44.render(f5);
        this.Shape45.render(f5);
        this.Shape46.render(f5);
        this.Shape47.render(f5);
        this.Shape48.render(f5);
        this.Shape49.render(f5);
        this.Shape50.render(f5);
        this.Shape51.render(f5);
        this.Shape52.render(f5);
        this.Shape53.render(f5);
        this.Shape54.render(f5);
        this.Shape55.render(f5);
        this.Shape56.render(f5);
        this.Shape57.render(f5);
        this.Shape58.render(f5);
        this.Shape59.render(f5);
        this.Shape60.render(f5);
        this.Shape61.render(f5);
        this.Shape62.render(f5);
        this.Shape63.render(f5);
        this.Shape64.render(f5);
        this.Shape65.render(f5);
        this.Shape66.render(f5);
        this.Shape67.render(f5);
        this.Shape68.render(f5);
        this.Shape69.render(f5);
        this.Shape70.render(f5);
        this.Shape71.render(f5);
        this.Shape72.render(f5);
        this.Shape73.render(f5);
        this.Shape74.render(f5);
        this.Shape75.render(f5);
        this.Shape76.render(f5);
        this.Shape77.render(f5);
        this.Shape78.render(f5);
        this.rebro();
        this.addLvL(204, 107, 0, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
    {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    }

    public void rebro()
    {
        GL11.glPushMatrix();
        GL11.glTranslatef(0.05F, -1.25F, 0.0F);
        float scale = 0.15F;
        GL11.glScalef(0.2F, 0.2F, 0.2F);
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

        for (int i = 0; i < 100; ++i)
        {
            GL11.glRotatef(random.nextFloat() * 360.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(random.nextFloat() * 360.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(random.nextFloat() * 360.0F, 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(random.nextFloat() * 360.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(random.nextFloat() * 360.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(random.nextFloat() * 360.0F + f1 * 360.0F, 0.0F, 0.0F, 1.0F);
            var22.startDrawing(6);
            float fa = random.nextFloat() * 30.0F + 3.0F + 0.0F;
            float f4 = random.nextFloat() * 2.0F + 6.0F + 0.0F;
            fa /= 10.0F / ((float)Math.min(age, 10) / 10.0F);
            f4 /= 10.0F / ((float)Math.min(age, 10) / 10.0F);
            var22.setColorRGBA_I(16196215, 255);
            var22.addVertex(0.6D, 0.0D, 0.0D);
            var22.setColorRGBA_I(16198159, 0);
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
}