package net.minecraft.client.addon.tchestplate.armors.models;

import net.minecraft.client.addon.tchestplate.Utils;
import net.minecraft.client.addon.tchestplate.armors.items.LavaChestPlateItem;
import net.minecraft.client.addon.tchestplate.armors.models.ModelBaseLavaArmor;
import net.minecraft.client.addon.tchestplate.player.ExtendedPlayer;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.player.EntityPlayer;

public class ModelShadowLavaPlateUHelmet extends ModelBaseLavaArmor {

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
   int y = 0;


   public ModelShadowLavaPlateUHelmet() {
      super.textureWidth = 48;
      super.textureHeight = 64;
      this.Shape1 = new ModelRenderer(this, 0, 0);
      this.Shape1.addBox(-3.0F, -5.5F, -5.5F, 6, 1, 1);
      this.Shape1.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape1.setTextureSize(32, 64);
      this.Shape1.mirror = true;
      this.setRotation(this.Shape1, 0.0F, 0.0F, 0.0F);
      this.Shape2 = new ModelRenderer(this, 0, 21);
      this.Shape2.addBox(-3.0F, -9.5F, -5.0F, 6, 4, 1);
      this.Shape2.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape2.setTextureSize(32, 64);
      this.Shape2.mirror = true;
      this.setRotation(this.Shape2, 0.0F, 0.0F, 0.0F);
      this.Shape3 = new ModelRenderer(this, 0, 0);
      this.Shape3.addBox(5.0F, -8.0F, 0.2F, 1, 3, 3);
      this.Shape3.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape3.setTextureSize(32, 64);
      this.Shape3.mirror = true;
      this.setRotation(this.Shape3, 0.0F, 0.0F, 0.0F);
      this.Shape4 = new ModelRenderer(this, 0, 0);
      this.Shape4.addBox(-2.0F, -2.7F, -6.0F, 1, 5, 2);
      this.Shape4.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape4.setTextureSize(32, 64);
      this.Shape4.mirror = true;
      this.setRotation(this.Shape4, -0.0872665F, 0.0F, -0.1745329F);
      this.Shape5 = new ModelRenderer(this, 0, 0);
      this.Shape5.addBox(1.0F, -2.7F, -6.0F, 1, 5, 2);
      this.Shape5.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape5.setTextureSize(32, 64);
      this.Shape5.mirror = true;
      this.setRotation(this.Shape5, -0.0872665F, 0.0F, 0.1745329F);
      this.Shape6 = new ModelRenderer(this, 0, 17);
      this.Shape6.addBox(3.5F, -9.5F, -4.5F, 1, 10, 1);
      this.Shape6.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape6.setTextureSize(32, 64);
      this.Shape6.mirror = true;
      this.setRotation(this.Shape6, 0.0F, 0.0F, 0.0F);
      this.Shape7 = new ModelRenderer(this, 0, 17);
      this.Shape7.addBox(2.7F, -10.0F, -4.7F, 1, 11, 1);
      this.Shape7.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape7.setTextureSize(32, 64);
      this.Shape7.mirror = true;
      this.setRotation(this.Shape7, 0.0F, 0.0F, 0.0F);
      this.Shape8 = new ModelRenderer(this, 0, 17);
      this.Shape8.addBox(1.8F, -2.5F, -5.2F, 1, 4, 1);
      this.Shape8.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape8.setTextureSize(32, 64);
      this.Shape8.mirror = true;
      this.setRotation(this.Shape8, -0.0523599F, 0.0F, 0.1047198F);
      this.Shape9 = new ModelRenderer(this, 0, 0);
      this.Shape9.addBox(1.5F, -3.0F, -5.5F, 2, 1, 1);
      this.Shape9.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape9.setTextureSize(32, 64);
      this.Shape9.mirror = true;
      this.setRotation(this.Shape9, 0.0F, 0.0F, 0.0F);
      this.Shape10 = new ModelRenderer(this, 0, 0);
      this.Shape10.addBox(-4.8F, -13.2F, -3.0F, 1, 5, 1);
      this.Shape10.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape10.setTextureSize(32, 64);
      this.Shape10.mirror = true;
      this.setRotation(this.Shape10, -0.4677482F, 0.0F, -0.2617994F);
      this.Shape11 = new ModelRenderer(this, 0, 0);
      this.Shape11.addBox(-0.5F, -10.0F, -5.6F, 1, 7, 2);
      this.Shape11.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape11.setTextureSize(32, 64);
      this.Shape11.mirror = true;
      this.setRotation(this.Shape11, 0.0698132F, 0.0F, 0.0F);
      this.Shape12 = new ModelRenderer(this, 0, 17);
      this.Shape12.addBox(-2.5F, -10.5F, -4.5F, 5, 1, 9);
      this.Shape12.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape12.setTextureSize(32, 64);
      this.Shape12.mirror = true;
      this.setRotation(this.Shape12, 0.0F, 0.0F, 0.0F);
      this.Shape13 = new ModelRenderer(this, 0, 0);
      this.Shape13.addBox(-0.5F, -11.5F, -4.5F, 1, 1, 9);
      this.Shape13.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape13.setTextureSize(32, 64);
      this.Shape13.mirror = true;
      this.setRotation(this.Shape13, 0.0F, 0.0F, 0.0F);
      this.Shape14 = new ModelRenderer(this, 0, 3);
      this.Shape14.addBox(-0.5F, -10.8F, -5.5F, 1, 2, 1);
      this.Shape14.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape14.setTextureSize(32, 64);
      this.Shape14.mirror = true;
      this.setRotation(this.Shape14, 0.0F, 0.0F, 0.0F);
      this.Shape15 = new ModelRenderer(this, 0, 17);
      this.Shape15.addBox(4.0F, -1.0F, -4.0F, 1, 1, 2);
      this.Shape15.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape15.setTextureSize(32, 64);
      this.Shape15.mirror = true;
      this.setRotation(this.Shape15, 0.0F, 0.0F, 0.0F);
      this.Shape16 = new ModelRenderer(this, 4, 17);
      this.Shape16.addBox(2.0F, -10.5F, -4.0F, 2, 1, 8);
      this.Shape16.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape16.setTextureSize(32, 64);
      this.Shape16.mirror = true;
      this.setRotation(this.Shape16, 0.0F, 0.0F, 0.0F);
      this.Shape17 = new ModelRenderer(this, 0, 17);
      this.Shape17.addBox(3.5F, -10.0F, -3.5F, 1, 1, 7);
      this.Shape17.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape17.setTextureSize(32, 64);
      this.Shape17.mirror = true;
      this.setRotation(this.Shape17, 0.0F, 0.0F, 0.0F);
      this.Shape18 = new ModelRenderer(this, 0, 17);
      this.Shape18.addBox(4.0F, -9.0F, -4.0F, 1, 8, 8);
      this.Shape18.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape18.setTextureSize(32, 64);
      this.Shape18.mirror = true;
      this.setRotation(this.Shape18, 0.0F, 0.0F, 0.0F);
      this.Shape19 = new ModelRenderer(this, 0, 17);
      this.Shape19.addBox(4.0F, -1.0F, 2.0F, 1, 1, 2);
      this.Shape19.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape19.setTextureSize(32, 64);
      this.Shape19.mirror = true;
      this.setRotation(this.Shape19, 0.0F, 0.0F, 0.0F);
      this.Shape20 = new ModelRenderer(this, 0, 17);
      this.Shape20.addBox(-3.0F, -10.0F, -4.7F, 6, 1, 1);
      this.Shape20.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape20.setTextureSize(32, 64);
      this.Shape20.mirror = true;
      this.setRotation(this.Shape20, 0.0F, 0.0F, 0.0F);
      this.Shape21 = new ModelRenderer(this, 0, 17);
      this.Shape21.addBox(-4.5F, -9.5F, 3.5F, 9, 10, 1);
      this.Shape21.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape21.setTextureSize(32, 64);
      this.Shape21.mirror = true;
      this.setRotation(this.Shape21, 0.0F, 0.0F, 0.0F);
      this.Shape22 = new ModelRenderer(this, 0, 17);
      this.Shape22.addBox(-4.0F, -9.5F, 4.0F, 8, 6, 1);
      this.Shape22.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape22.setTextureSize(32, 64);
      this.Shape22.mirror = true;
      this.setRotation(this.Shape22, 0.0F, 0.0F, 0.0F);
      this.Shape23 = new ModelRenderer(this, 0, 17);
      this.Shape23.addBox(-3.0F, -3.5F, 4.0F, 6, 3, 1);
      this.Shape23.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape23.setTextureSize(32, 64);
      this.Shape23.mirror = true;
      this.setRotation(this.Shape23, 0.0F, 0.0F, 0.0F);
      this.Shape24 = new ModelRenderer(this, 0, 0);
      this.Shape24.addBox(5.5F, -8.3F, 1.0F, 1, 2, 2);
      this.Shape24.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape24.setTextureSize(32, 64);
      this.Shape24.mirror = true;
      this.setRotation(this.Shape24, 0.0F, 0.0F, 0.0F);
      this.Shape25 = new ModelRenderer(this, 0, 0);
      this.Shape25.addBox(5.9F, -11.0F, 6.0F, 1, 1, 7);
      this.Shape25.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape25.setTextureSize(32, 64);
      this.Shape25.mirror = true;
      this.setRotation(this.Shape25, 0.2443461F, 0.0F, 0.0872665F);
      this.Shape26 = new ModelRenderer(this, 0, 0);
      this.Shape26.addBox(6.0F, -7.0F, 4.0F, 1, 1, 5);
      this.Shape26.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape26.setTextureSize(32, 64);
      this.Shape26.mirror = true;
      this.setRotation(this.Shape26, 0.1919862F, 0.0F, -0.0698132F);
      this.Shape27 = new ModelRenderer(this, 0, 0);
      this.Shape27.addBox(-3.5F, -3.0F, -5.5F, 2, 1, 1);
      this.Shape27.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape27.setTextureSize(32, 64);
      this.Shape27.mirror = true;
      this.setRotation(this.Shape27, 0.0F, 0.0F, 0.0F);
      this.Shape28 = new ModelRenderer(this, 0, 0);
      this.Shape28.addBox(6.0F, -7.0F, 4.5F, 1, 1, 6);
      this.Shape28.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape28.setTextureSize(32, 64);
      this.Shape28.mirror = true;
      this.setRotation(this.Shape28, 0.3141593F, 0.0F, -0.0349066F);
      this.Shape29 = new ModelRenderer(this, 0, 17);
      this.Shape29.addBox(-3.7F, -10.0F, -4.7F, 1, 11, 1);
      this.Shape29.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape29.setTextureSize(32, 64);
      this.Shape29.mirror = true;
      this.setRotation(this.Shape29, 0.0F, 0.0F, 0.0F);
      this.Shape30 = new ModelRenderer(this, 0, 17);
      this.Shape30.addBox(-2.8F, -2.5F, -5.2F, 1, 4, 1);
      this.Shape30.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape30.setTextureSize(32, 64);
      this.Shape30.mirror = true;
      this.setRotation(this.Shape30, -0.0523599F, 0.0F, -0.1047198F);
      this.Shape31 = new ModelRenderer(this, 0, 17);
      this.Shape31.addBox(-4.5F, -9.5F, -4.5F, 1, 10, 1);
      this.Shape31.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape31.setTextureSize(32, 64);
      this.Shape31.mirror = true;
      this.setRotation(this.Shape31, 0.0F, 0.0F, 0.0F);
      this.Shape32 = new ModelRenderer(this, 0, 17);
      this.Shape32.addBox(-5.0F, -9.0F, -4.0F, 1, 8, 8);
      this.Shape32.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape32.setTextureSize(32, 64);
      this.Shape32.mirror = true;
      this.setRotation(this.Shape32, 0.0F, 0.0F, 0.0F);
      this.Shape33 = new ModelRenderer(this, 0, 17);
      this.Shape33.addBox(-4.0F, -10.5F, -4.0F, 2, 1, 8);
      this.Shape33.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape33.setTextureSize(32, 64);
      this.Shape33.mirror = true;
      this.setRotation(this.Shape33, 0.0F, 0.0F, 0.0F);
      this.Shape34 = new ModelRenderer(this, 0, 17);
      this.Shape34.addBox(-4.5F, -10.0F, -3.5F, 1, 1, 7);
      this.Shape34.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape34.setTextureSize(32, 64);
      this.Shape34.mirror = true;
      this.setRotation(this.Shape34, 0.0F, 0.0F, 0.0F);
      this.Shape35 = new ModelRenderer(this, 0, 19);
      this.Shape35.addBox(-5.0F, -1.0F, -4.0F, 1, 1, 2);
      this.Shape35.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape35.setTextureSize(32, 64);
      this.Shape35.mirror = true;
      this.setRotation(this.Shape35, 0.0F, 0.0F, 0.0F);
      this.Shape36 = new ModelRenderer(this, 0, 19);
      this.Shape36.addBox(-5.0F, -1.0F, 2.0F, 1, 1, 2);
      this.Shape36.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape36.setTextureSize(32, 64);
      this.Shape36.mirror = true;
      this.setRotation(this.Shape36, 0.0F, 0.0F, 0.0F);
      this.Shape37 = new ModelRenderer(this, 0, 0);
      this.Shape37.addBox(-6.0F, -8.0F, 0.2F, 1, 3, 3);
      this.Shape37.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape37.setTextureSize(32, 64);
      this.Shape37.mirror = true;
      this.setRotation(this.Shape37, 0.0F, 0.0F, 0.0F);
      this.Shape38 = new ModelRenderer(this, 0, 0);
      this.Shape38.addBox(6.0F, -7.0F, 6.0F, 1, 1, 6);
      this.Shape38.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape38.setTextureSize(32, 64);
      this.Shape38.mirror = true;
      this.setRotation(this.Shape38, 0.4276057F, 0.0F, 0.0F);
      this.Shape38.mirror = false;
      this.Shape39 = new ModelRenderer(this, 0, 0);
      this.Shape39.addBox(6.0F, -6.933333F, 7.0F, 1, 1, 7);
      this.Shape39.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape39.setTextureSize(32, 64);
      this.Shape39.mirror = true;
      this.setRotation(this.Shape39, 0.5323254F, 0.0F, 0.0349066F);
      this.Shape40 = new ModelRenderer(this, 0, 0);
      this.Shape40.addBox(-0.5F, -10.5F, 3.9F, 1, 1, 1);
      this.Shape40.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape40.setTextureSize(32, 64);
      this.Shape40.mirror = true;
      this.setRotation(this.Shape40, 0.0F, 0.0F, 0.0F);
      this.Shape41 = new ModelRenderer(this, 0, 0);
      this.Shape41.addBox(3.8F, -13.2F, -3.0F, 1, 5, 1);
      this.Shape41.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape41.setTextureSize(32, 64);
      this.Shape41.mirror = true;
      this.setRotation(this.Shape41, -0.4677482F, 0.0F, 0.2617994F);
      this.Shape42 = new ModelRenderer(this, 0, 0);
      this.Shape42.addBox(6.0F, -6.1F, 9.0F, 1, 1, 3);
      this.Shape42.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape42.setTextureSize(32, 64);
      this.Shape42.mirror = true;
      this.setRotation(this.Shape42, 0.6981317F, 0.0F, 0.0523599F);
      this.Shape43 = new ModelRenderer(this, 0, 0);
      this.Shape43.addBox(2.6F, -5.0F, -5.1F, 1, 1, 1);
      this.Shape43.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape43.setTextureSize(32, 64);
      this.Shape43.mirror = true;
      this.setRotation(this.Shape43, 0.0F, 0.0F, 0.0F);
      this.Shape44 = new ModelRenderer(this, 0, 0);
      this.Shape44.addBox(-3.6F, -5.0F, -5.2F, 1, 1, 1);
      this.Shape44.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape44.setTextureSize(32, 64);
      this.Shape44.mirror = true;
      this.setRotation(this.Shape44, 0.0F, 0.0F, 0.0F);
      this.Shape45 = new ModelRenderer(this, 0, 0);
      this.Shape45.addBox(-6.5F, -8.3F, 1.0F, 1, 2, 2);
      this.Shape45.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape45.setTextureSize(32, 64);
      this.Shape45.mirror = true;
      this.setRotation(this.Shape45, 0.0F, 0.0F, 0.0F);
      this.Shape46 = new ModelRenderer(this, 0, 0);
      this.Shape46.addBox(-7.0F, -11.0F, 6.0F, 1, 1, 7);
      this.Shape46.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape46.setTextureSize(32, 64);
      this.Shape46.mirror = true;
      this.setRotation(this.Shape46, 0.2443461F, 0.0F, -0.0872665F);
      this.Shape47 = new ModelRenderer(this, 0, 0);
      this.Shape47.addBox(-7.0F, -7.0F, 4.0F, 1, 1, 5);
      this.Shape47.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape47.setTextureSize(32, 64);
      this.Shape47.mirror = true;
      this.setRotation(this.Shape47, 0.1919862F, 0.0F, 0.0698132F);
      this.Shape48 = new ModelRenderer(this, 0, 0);
      this.Shape48.addBox(-7.0F, -7.0F, 4.5F, 1, 1, 6);
      this.Shape48.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape48.setTextureSize(32, 64);
      this.Shape48.mirror = true;
      this.setRotation(this.Shape48, 0.3141593F, 0.0F, 0.0349066F);
      this.Shape49 = new ModelRenderer(this, 0, 0);
      this.Shape49.addBox(-7.0F, -7.0F, 6.0F, 1, 1, 6);
      this.Shape49.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape49.setTextureSize(32, 64);
      this.Shape49.mirror = true;
      this.setRotation(this.Shape49, 0.4276057F, 0.0F, 0.0F);
      this.Shape50 = new ModelRenderer(this, 0, 0);
      this.Shape50.addBox(-7.0F, -6.9F, 7.0F, 1, 1, 7);
      this.Shape50.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape50.setTextureSize(32, 64);
      this.Shape50.mirror = true;
      this.setRotation(this.Shape50, 0.5323254F, 0.0F, -0.0349066F);
      this.Shape51 = new ModelRenderer(this, 0, 0);
      this.Shape51.addBox(-7.0F, -6.1F, 9.0F, 1, 1, 3);
      this.Shape51.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape51.setTextureSize(32, 64);
      this.Shape51.mirror = true;
      this.setRotation(this.Shape51, 0.6981317F, 0.0F, -0.0523599F);
      this.Shape52 = new ModelRenderer(this, 12, 11);
      this.Shape52.addBox(-4.0F, -13.0F, -4.0F, 8, 2, 4);
      this.Shape52.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape52.setTextureSize(32, 64);
      this.Shape52.mirror = true;
      this.setRotation(this.Shape52, 0.0F, 0.0F, 0.0F);
      this.Shape53 = new ModelRenderer(this, 0, 11);
      this.Shape53.addBox(-4.0F, -13.0F, 0.0F, 8, 2, 4);
      this.Shape53.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape53.setTextureSize(32, 64);
      this.Shape53.mirror = true;
      this.setRotation(this.Shape53, 0.0F, 0.0F, 0.0F);
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
      super.bipedHead.addChild(this.Shape40);
      super.bipedHead.addChild(this.Shape41);
      super.bipedHead.addChild(this.Shape42);
      super.bipedHead.addChild(this.Shape43);
      super.bipedHead.addChild(this.Shape44);
      super.bipedHead.addChild(this.Shape45);
      super.bipedHead.addChild(this.Shape46);
      super.bipedHead.addChild(this.Shape47);
      super.bipedHead.addChild(this.Shape48);
      super.bipedHead.addChild(this.Shape49);
      super.bipedHead.addChild(this.Shape50);
      super.bipedHead.addChild(this.Shape51);
      super.bipedHead.addChild(this.Shape52);
      super.bipedHead.addChild(this.Shape53);
      this.Shape52.isHidden = true;
      this.Shape53.isHidden = true;
   }
   
   protected void ExtRender(EntityPlayer paramEntityPlayer, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, ExtendedPlayer paramExtendedPlayer)
   {
     if ((paramEntityPlayer.inventory.armorItemInSlot(3) != null) && (LavaChestPlateItem.getItKorona(Utils.getOrCreateNbtData(paramEntityPlayer.inventory.armorItemInSlot(3)))))
     {
       this.Shape52.isHidden = false;
       this.Shape53.isHidden = false;
     }
     else
     {
       this.Shape52.isHidden = true;
       this.Shape53.isHidden = true;
     }
   }


   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }
}
