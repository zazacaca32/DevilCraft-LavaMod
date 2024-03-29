package net.minecraft.client.addon.tchestplate.armors.models;

import net.minecraft.client.addon.tchestplate.armors.models.ModelBaseLavaArmor;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelAngelLeggins extends ModelBaseLavaArmor {

   ModelRenderer ShapeBody1;
   ModelRenderer ShapeBody2;
   ModelRenderer ShapeBody3;
   ModelRenderer ShapeBody4;
   ModelRenderer ShapeBody5;
   ModelRenderer ShapeBody6;
   ModelRenderer ShapeBody7;
   ModelRenderer ShapeBody8;
   ModelRenderer ShapeBody9;
   ModelRenderer ShapeBody11;
   ModelRenderer ShapeBody10;
   ModelRenderer ShapeBody12;
   ModelRenderer ShapeBody13;
   ModelRenderer Shape21;
   ModelRenderer Shape23;
   ModelRenderer Shape1;
   ModelRenderer Shapea13;
   ModelRenderer Shapea12;
   ModelRenderer Shapeall;
   ModelRenderer Shapea10;
   ModelRenderer Shape22;
   ModelRenderer Shapea11;
   ModelRenderer Shape24;
   ModelRenderer Shapea8;
   ModelRenderer Shapea4;
   ModelRenderer Shape3;
   ModelRenderer Shapea7;
   ModelRenderer Shapea6;
   ModelRenderer Shapea5;
   ModelRenderer Shapea1;
   ModelRenderer Shapea2;
   ModelRenderer Shapea3;
   ModelRenderer Shape2;
   ModelRenderer Shape4;
   ModelRenderer Shape5;
   ModelRenderer Shape6;
   ModelRenderer Shape7;
   ModelRenderer Shape8;
   ModelRenderer Shapea33;
   ModelRenderer Shape33;
   ModelRenderer Shapeaa34;
   ModelRenderer Shape34;
   ModelRenderer Shapea35;
   ModelRenderer Shape36;
   ModelRenderer Shape44;
   ModelRenderer Shapea39;
   ModelRenderer Shape39;
   ModelRenderer Shapea40;
   ModelRenderer Shapeleg1;
   ModelRenderer Shapeleg2;


   public ModelAngelLeggins() {
      super.textureWidth = 32;
      super.textureHeight = 64;
      this.Shapeleg2 = new ModelRenderer(this, 25, 0);
      this.Shapeleg2.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
      this.Shapeleg2.setRotationPoint(-0.5F, 10.2F, -3.7F);
      this.Shapeleg2.setTextureSize(32, 64);
      this.Shapeleg2.mirror = true;
      this.setRotation(this.Shapeleg2, 0.7679449F, 0.0F, 0.0F);
      this.Shapeleg1 = new ModelRenderer(this, 26, 0);
      this.Shapeleg1.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
      this.Shapeleg1.setRotationPoint(0.0F, 9.5F, -3.5F);
      this.Shapeleg1.setTextureSize(32, 64);
      this.Shapeleg1.mirror = true;
      this.setRotation(this.Shapeleg1, 0.0F, 0.0F, 0.8028515F);
      this.Shape21 = new ModelRenderer(this, 0, 39);
      this.Shape21.addBox(0.0F, 0.0F, 0.0F, 4, 9, 1);
      this.Shape21.setRotationPoint(-2.0F, 0.0F, -2.3F);
      this.Shape21.setTextureSize(64, 32);
      this.Shape21.mirror = true;
      this.setRotation(this.Shape21, 0.0F, 0.0F, 0.0F);
      this.Shape23 = new ModelRenderer(this, 0, 36);
      this.Shape23.addBox(0.0F, 0.0F, 0.0F, 1, 9, 4);
      this.Shape23.setRotationPoint(-2.3F, 0.0F, -2.0F);
      this.Shape23.setTextureSize(64, 32);
      this.Shape23.mirror = true;
      this.setRotation(this.Shape23, 0.0F, 0.0F, 0.0F);
      this.Shape1 = new ModelRenderer(this, 0, 0);
      this.Shape1.addBox(0.0F, 0.0F, 0.0F, 2, 2, 1);
      this.Shape1.setRotationPoint(-1.0F, 6.0F, -2.8F);
      this.Shape1.setTextureSize(64, 32);
      this.Shape1.mirror = true;
      this.setRotation(this.Shape1, 0.0F, 0.0F, 0.0F);
      this.Shapea13 = new ModelRenderer(this, 0, 36);
      this.Shapea13.addBox(0.0F, 0.0F, 0.0F, 1, 9, 4);
      this.Shapea13.setRotationPoint(1.3F, 0.0F, -2.0F);
      this.Shapea13.setTextureSize(64, 32);
      this.Shapea13.mirror = true;
      this.setRotation(this.Shapea13, 0.0F, 0.0F, 0.0F);
      this.Shapea12 = new ModelRenderer(this, 0, 39);
      this.Shapea12.addBox(0.0F, 0.0F, 0.0F, 4, 9, 1);
      this.Shapea12.setRotationPoint(-2.0F, 0.0F, 1.3F);
      this.Shapea12.setTextureSize(64, 32);
      this.Shapea12.mirror = true;
      this.setRotation(this.Shapea12, 0.0F, 0.0F, 0.0F);
      this.Shapeall = new ModelRenderer(this, 0, 44);
      this.Shapeall.addBox(0.0F, 0.0F, 0.0F, 4, 1, 4);
      this.Shapeall.setRotationPoint(-2.0F, -0.1F, -2.0F);
      this.Shapeall.setTextureSize(64, 32);
      this.Shapeall.mirror = true;
      this.setRotation(this.Shapeall, 0.0F, 0.0F, 0.0F);
      this.Shapea10 = new ModelRenderer(this, 0, 39);
      this.Shapea10.addBox(0.0F, 0.0F, 0.0F, 4, 9, 1);
      this.Shapea10.setRotationPoint(-2.0F, 0.0F, -2.3F);
      this.Shapea10.setTextureSize(64, 32);
      this.Shapea10.mirror = true;
      this.setRotation(this.Shapea10, 0.0F, 0.0F, 0.0F);
      this.Shape22 = new ModelRenderer(this, 0, 36);
      this.Shape22.addBox(0.0F, 0.0F, 0.0F, 1, 9, 4);
      this.Shape22.setRotationPoint(1.3F, 0.0F, -2.0F);
      this.Shape22.setTextureSize(64, 32);
      this.Shape22.mirror = true;
      this.setRotation(this.Shape22, 0.0F, 0.0F, 0.0F);
      this.Shapea11 = new ModelRenderer(this, 0, 36);
      this.Shapea11.addBox(0.0F, 0.0F, 0.0F, 1, 9, 4);
      this.Shapea11.setRotationPoint(-2.3F, 0.0F, -2.0F);
      this.Shapea11.setTextureSize(64, 32);
      this.Shapea11.mirror = true;
      this.setRotation(this.Shapea11, 0.0F, 0.0F, 0.0F);
      this.Shape24 = new ModelRenderer(this, 0, 39);
      this.Shape24.addBox(0.0F, 0.0F, 0.0F, 4, 9, 1);
      this.Shape24.setRotationPoint(-2.0F, 0.0F, 1.3F);
      this.Shape24.setTextureSize(64, 32);
      this.Shape24.mirror = true;
      this.setRotation(this.Shape24, 0.0F, 0.0F, 0.0F);
      this.Shapea4 = new ModelRenderer(this, 0, 0);
      this.Shapea4.addBox(0.0F, 0.0F, 0.0F, 3, 6, 1);
      this.Shapea4.setRotationPoint(-2.0F, 0.0F, -2.5F);
      this.Shapea4.setTextureSize(64, 32);
      this.Shapea4.mirror = true;
      this.setRotation(this.Shapea4, 0.0F, 0.0F, 0.0F);
      this.Shape3 = new ModelRenderer(this, 0, 0);
      this.Shape3.addBox(0.0F, 0.0F, 0.0F, 3, 6, 1);
      this.Shape3.setRotationPoint(-1.0F, 0.0F, -2.5F);
      this.Shape3.setTextureSize(64, 32);
      this.Shape3.mirror = true;
      this.setRotation(this.Shape3, 0.0F, 0.0F, 0.0F);
      this.Shapea5 = new ModelRenderer(this, 0, 0);
      this.Shapea5.addBox(0.0F, 0.0F, 0.0F, 2, 2, 1);
      this.Shapea5.setRotationPoint(-0.7F, 1.0F, -2.5F);
      this.Shapea5.setTextureSize(64, 32);
      this.Shapea5.mirror = true;
      this.setRotation(this.Shapea5, 0.0F, 0.0F, 0.0F);
      this.Shape5 = new ModelRenderer(this, 0, 0);
      this.Shape5.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1);
      this.Shape5.setRotationPoint(-1.3F, 1.0F, -2.5F);
      this.Shape5.setTextureSize(64, 32);
      this.Shape5.mirror = true;
      this.setRotation(this.Shape5, 0.0F, 0.0F, 0.0F);
      this.Shapea7 = new ModelRenderer(this, 0, 0);
      this.Shapea7.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1);
      this.Shapea7.setRotationPoint(-0.7F, 5.0F, -2.5F);
      this.Shapea7.setTextureSize(64, 32);
      this.Shapea7.mirror = true;
      this.setRotation(this.Shapea7, 0.0F, 0.0F, 0.0F);
      this.Shapea6 = new ModelRenderer(this, 0, 0);
      this.Shapea6.addBox(0.0F, 0.0F, 0.0F, 2, 2, 1);
      this.Shapea6.setRotationPoint(-0.6F, 3.0F, -2.5F);
      this.Shapea6.setTextureSize(64, 32);
      this.Shapea6.mirror = true;
      this.setRotation(this.Shapea6, 0.0F, 0.0F, 0.0F);
      this.Shape2 = new ModelRenderer(this, 0, 0);
      this.Shape2.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1);
      this.Shape2.setRotationPoint(-1.3F, 5.0F, -2.5F);
      this.Shape2.setTextureSize(64, 32);
      this.Shape2.mirror = true;
      this.setRotation(this.Shape2, 0.0F, 0.0F, 0.0F);
      this.Shapea1 = new ModelRenderer(this, 0, 0);
      this.Shapea1.addBox(0.0F, 0.0F, 0.0F, 1, 4, 5);
      this.Shapea1.setRotationPoint(-2.6F, 2.0F, -2.5F);
      this.Shapea1.setTextureSize(64, 32);
      this.Shapea1.mirror = true;
      this.setRotation(this.Shapea1, 0.0F, 0.0F, 0.0F);
      this.Shapea2 = new ModelRenderer(this, 0, 0);
      this.Shapea2.addBox(0.0F, 0.0F, 0.0F, 1, 1, 5);
      this.Shapea2.setRotationPoint(-2.5F, 1.0F, -2.5F);
      this.Shapea2.setTextureSize(64, 32);
      this.Shapea2.mirror = true;
      this.setRotation(this.Shapea2, 0.0F, 0.0F, 0.0F);
      this.Shapea3 = new ModelRenderer(this, 0, 0);
      this.Shapea3.addBox(0.0F, 0.0F, 0.0F, 1, 1, 5);
      this.Shapea3.setRotationPoint(-2.4F, 0.0F, -2.5F);
      this.Shapea3.setTextureSize(64, 32);
      this.Shapea3.mirror = true;
      this.setRotation(this.Shapea3, 0.0F, 0.0F, 0.0F);
      this.Shapea8 = new ModelRenderer(this, 0, 0);
      this.Shapea8.addBox(0.0F, 0.0F, 0.0F, 2, 2, 1);
      this.Shapea8.setRotationPoint(-1.0F, 6.0F, -2.5F);
      this.Shapea8.setTextureSize(64, 32);
      this.Shapea8.mirror = true;
      this.setRotation(this.Shapea8, 0.0F, 0.0F, 0.0F);
      this.Shape1 = new ModelRenderer(this, 0, 0);
      this.Shape1.addBox(0.0F, 0.0F, 0.0F, 2, 2, 1);
      this.Shape1.setRotationPoint(-1.0F, 6.0F, -2.5F);
      this.Shape1.setTextureSize(64, 32);
      this.Shape1.mirror = true;
      this.setRotation(this.Shape1, 0.0F, 0.0F, 0.0F);
      this.Shape4 = new ModelRenderer(this, 0, 0);
      this.Shape4.addBox(0.0F, 0.0F, 0.0F, 2, 2, 1);
      this.Shape4.setRotationPoint(-1.4F, 3.0F, -2.5F);
      this.Shape4.setTextureSize(64, 32);
      this.Shape4.mirror = true;
      this.setRotation(this.Shape4, 0.0F, 0.0F, 0.0F);
      this.Shape6 = new ModelRenderer(this, 0, 0);
      this.Shape6.addBox(0.0F, 0.0F, 0.0F, 1, 1, 5);
      this.Shape6.setRotationPoint(1.4F, 0.0F, -2.5F);
      this.Shape6.setTextureSize(64, 32);
      this.Shape6.mirror = true;
      this.setRotation(this.Shape6, 0.0F, 0.0F, 0.0F);
      this.Shape7 = new ModelRenderer(this, 0, 0);
      this.Shape7.addBox(0.0F, 0.0F, 0.0F, 1, 1, 5);
      this.Shape7.setRotationPoint(1.5F, 1.0F, -2.5F);
      this.Shape7.setTextureSize(64, 32);
      this.Shape7.mirror = true;
      this.setRotation(this.Shape7, 0.0F, 0.0F, 0.0F);
      this.Shape8 = new ModelRenderer(this, 0, 0);
      this.Shape8.addBox(0.0F, 0.0F, 0.0F, 1, 4, 5);
      this.Shape8.setRotationPoint(1.6F, 2.0F, -2.5F);
      this.Shape8.setTextureSize(64, 32);
      this.Shape8.mirror = true;
      this.setRotation(this.Shape8, 0.0F, 0.0F, 0.0F);
      this.ShapeBody1 = new ModelRenderer(this, 0, 41);
      this.ShapeBody1.addBox(0.0F, 0.0F, 0.0F, 9, 3, 5);
      this.ShapeBody1.setRotationPoint(-4.5F, 9.0F, -2.5F);
      this.ShapeBody1.setTextureSize(64, 32);
      this.ShapeBody1.mirror = true;
      this.setRotation(this.ShapeBody1, 0.0F, 0.0F, 0.0F);
      this.ShapeBody2 = new ModelRenderer(this, 0, 0);
      this.ShapeBody2.addBox(0.0F, 0.0F, 0.0F, 8, 1, 1);
      this.ShapeBody2.setRotationPoint(-4.0F, 8.9F, -3.1F);
      this.ShapeBody2.setTextureSize(64, 32);
      this.ShapeBody2.mirror = true;
      this.setRotation(this.ShapeBody2, 0.0F, 0.0029671F, 0.0F);
      this.ShapeBody3 = new ModelRenderer(this, 0, 0);
      this.ShapeBody3.addBox(0.0F, 0.0F, 0.0F, 5, 1, 1);
      this.ShapeBody3.setRotationPoint(-2.5F, 9.9F, -3.1F);
      this.ShapeBody3.setTextureSize(64, 32);
      this.ShapeBody3.mirror = true;
      this.setRotation(this.ShapeBody3, 0.0F, 0.0F, 0.0F);
      this.ShapeBody4 = new ModelRenderer(this, 0, 0);
      this.ShapeBody4.addBox(0.0F, 0.0F, 0.0F, 3, 1, 1);
      this.ShapeBody4.setRotationPoint(-1.5F, 11.0F, -3.1F);
      this.ShapeBody4.setTextureSize(64, 32);
      this.ShapeBody4.mirror = true;
      this.ShapeBody5 = new ModelRenderer(this, 0, 0);
      this.ShapeBody5.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1);
      this.ShapeBody5.setRotationPoint(-1.0F, 9.3F, -3.2F);
      this.ShapeBody5.setTextureSize(64, 32);
      this.ShapeBody5.mirror = true;
      this.setRotation(this.ShapeBody5, 0.0F, 0.0F, 0.0F);
      this.ShapeBody6 = new ModelRenderer(this, 0, 0);
      this.ShapeBody6.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1);
      this.ShapeBody6.setRotationPoint(-1.0F, 9.8F, -3.2F);
      this.ShapeBody6.setTextureSize(64, 32);
      this.ShapeBody6.mirror = true;
      this.setRotation(this.ShapeBody6, 0.0F, 0.0F, 0.0F);
      this.ShapeBody8 = new ModelRenderer(this, 0, 46);
      this.ShapeBody8.addBox(0.0F, 0.0F, 0.0F, 1, 3, 5);
      this.ShapeBody8.setRotationPoint(-4.6F, 9.0F, -2.5F);
      this.ShapeBody8.setTextureSize(32, 64);
      this.ShapeBody8.mirror = true;
      this.setRotation(this.ShapeBody8, 0.0F, 0.0F, 0.0F);
      this.ShapeBody7 = new ModelRenderer(this, 0, 41);
      this.ShapeBody7.addBox(0.0F, 0.0F, 0.0F, 1, 3, 5);
      this.ShapeBody7.setRotationPoint(3.6F, 9.0F, -2.5F);
      this.ShapeBody7.setTextureSize(32, 64);
      this.ShapeBody7.mirror = true;
      this.setRotation(this.ShapeBody7, 0.0F, 0.0F, 0.0F);
      this.ShapeBody9 = new ModelRenderer(this, 0, 0);
      this.ShapeBody9.addBox(0.0F, 0.0F, 0.0F, 4, 1, 1);
      this.ShapeBody9.setRotationPoint(-2.0F, 10.4F, -3.1F);
      this.ShapeBody9.setTextureSize(32, 64);
      this.ShapeBody9.mirror = true;
      this.setRotation(this.ShapeBody9, 0.0F, 0.0F, 0.0F);
      this.ShapeBody10 = new ModelRenderer(this, 0, 0);
      this.ShapeBody10.addBox(0.0F, 0.0F, 0.0F, 3, 1, 1);
      this.ShapeBody10.setRotationPoint(-1.5F, 11.0F, 2.1F);
      this.ShapeBody10.setTextureSize(32, 64);
      this.ShapeBody10.mirror = true;
      this.setRotation(this.ShapeBody10, 0.0F, 0.0F, 0.0F);
      this.ShapeBody13 = new ModelRenderer(this, 0, 0);
      this.ShapeBody13.addBox(0.0F, 0.0F, 0.0F, 9, 1, 1);
      this.ShapeBody13.setRotationPoint(-4.5F, 9.0F, 2.1F);
      this.ShapeBody13.setTextureSize(32, 64);
      this.ShapeBody13.mirror = true;
      this.setRotation(this.ShapeBody13, 0.0F, 0.0F, 0.0F);
      this.ShapeBody12 = new ModelRenderer(this, 0, 0);
      this.ShapeBody12.addBox(0.0F, 0.0F, 0.0F, 6, 1, 1);
      this.ShapeBody12.setRotationPoint(-3.0F, 10.0F, 2.1F);
      this.ShapeBody12.setTextureSize(32, 64);
      this.ShapeBody12.mirror = true;
      this.setRotation(this.ShapeBody12, 0.0F, 0.0F, 0.0F);
      this.ShapeBody11 = new ModelRenderer(this, 0, 0);
      this.ShapeBody11.addBox(0.0F, 0.0F, 0.0F, 4, 1, 1);
      this.ShapeBody11.setRotationPoint(-2.0F, 10.4F, 2.1F);
      this.ShapeBody11.setTextureSize(32, 64);
      this.ShapeBody11.mirror = true;
      this.setRotation(this.ShapeBody11, 0.0F, 0.0F, 0.0F);
      this.Shapea33 = new ModelRenderer(this, 0, 0);
      this.Shapea33.addBox(0.0F, 0.0F, 0.0F, 2, 2, 1);
      this.Shapea33.setRotationPoint(-1.0F, 6.0F, 1.5F);
      this.Shapea33.setTextureSize(32, 64);
      this.Shapea33.mirror = true;
      this.setRotation(this.Shapea33, 0.0F, 0.0F, 0.0F);
      this.Shape33 = new ModelRenderer(this, 0, 0);
      this.Shape33.addBox(0.0F, 0.0F, 0.0F, 2, 2, 1);
      this.Shape33.setRotationPoint(-1.0F, 6.0F, 1.5F);
      this.Shape33.setTextureSize(32, 64);
      this.Shape33.mirror = true;
      this.setRotation(this.Shape33, 0.0F, 0.0F, 0.0F);
      this.Shapeaa34 = new ModelRenderer(this, 0, 0);
      this.Shapeaa34.addBox(0.0F, 0.0F, 0.0F, 3, 6, 1);
      this.Shapeaa34.setRotationPoint(-2.0F, 0.0F, 1.5F);
      this.Shapeaa34.setTextureSize(64, 32);
      this.Shapeaa34.mirror = true;
      this.setRotation(this.Shapeaa34, 0.0F, 0.0F, 0.0F);
      this.Shape34 = new ModelRenderer(this, 0, 0);
      this.Shape34.addBox(0.0F, 0.0F, 0.0F, 3, 6, 1);
      this.Shape34.setRotationPoint(-1.0F, 0.0F, 1.5F);
      this.Shape34.setTextureSize(64, 32);
      this.Shape34.mirror = true;
      this.setRotation(this.Shape34, 0.0F, 0.0F, 0.0F);
      this.Shapea35 = new ModelRenderer(this, 0, 0);
      this.Shapea35.addBox(0.0F, 0.0F, 0.0F, 2, 2, 1);
      this.Shapea35.setRotationPoint(-0.7F, 1.0F, 1.5F);
      this.Shapea35.setTextureSize(64, 32);
      this.Shapea35.mirror = true;
      this.setRotation(this.Shapea35, 0.0F, 0.0F, 0.0F);
      this.Shape36 = new ModelRenderer(this, 0, 0);
      this.Shape36.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1);
      this.Shape36.setRotationPoint(-1.3F, 1.0F, 1.5F);
      this.Shape36.setTextureSize(32, 64);
      this.Shape36.mirror = true;
      this.setRotation(this.Shape36, 0.0F, 0.0F, 0.0F);
      this.Shape44 = new ModelRenderer(this, 0, 0);
      this.Shape44.addBox(0.0F, 0.0F, 0.0F, 2, 2, 1);
      this.Shape44.setRotationPoint(-1.4F, 3.0F, 1.5F);
      this.Shape44.setTextureSize(32, 64);
      this.Shape44.mirror = true;
      this.setRotation(this.Shape44, 0.0F, 0.0F, 0.0F);
      this.Shapea39 = new ModelRenderer(this, 0, 0);
      this.Shapea39.addBox(0.0F, 0.0F, 0.0F, 2, 2, 1);
      this.Shapea39.setRotationPoint(-0.6F, 3.0F, 1.5F);
      this.Shapea39.setTextureSize(32, 64);
      this.Shapea39.mirror = true;
      this.setRotation(this.Shapea39, 0.0F, 0.0F, 0.0F);
      this.Shape39 = new ModelRenderer(this, 0, 0);
      this.Shape39.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1);
      this.Shape39.setRotationPoint(-1.3F, 5.0F, 1.5F);
      this.Shape39.setTextureSize(32, 64);
      this.Shape39.mirror = true;
      this.setRotation(this.Shape39, 0.0F, 0.0F, 0.0F);
      this.Shapea40 = new ModelRenderer(this, 0, 0);
      this.Shapea40.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1);
      this.Shapea40.setRotationPoint(-0.7F, 5.0F, 1.5F);
      this.Shapea40.setTextureSize(32, 64);
      this.Shapea40.mirror = true;
      this.setRotation(this.Shapea40, 0.0F, 0.0F, 0.0F);
      super.bipedLeftLeg.cubeList.clear();
      super.bipedLeftLeg.addChild(this.Shape1);
      super.bipedLeftLeg.addChild(this.Shape2);
      super.bipedLeftLeg.addChild(this.Shape3);
      super.bipedLeftLeg.addChild(this.Shape4);
      super.bipedLeftLeg.addChild(this.Shape5);
      super.bipedLeftLeg.addChild(this.Shape6);
      super.bipedLeftLeg.addChild(this.Shape7);
      super.bipedLeftLeg.addChild(this.Shape8);
      super.bipedLeftLeg.addChild(this.Shapeall);
      super.bipedLeftLeg.addChild(this.Shape21);
      super.bipedLeftLeg.addChild(this.Shape22);
      super.bipedLeftLeg.addChild(this.Shape23);
      super.bipedLeftLeg.addChild(this.Shape24);
      super.bipedLeftLeg.addChild(this.Shape8);
      super.bipedLeftLeg.addChild(this.Shape33);
      super.bipedLeftLeg.addChild(this.Shape34);
      super.bipedLeftLeg.addChild(this.Shape36);
      super.bipedLeftLeg.addChild(this.Shape39);
      super.bipedLeftLeg.addChild(this.Shape44);
      super.bipedRightLeg.cubeList.clear();
      super.bipedRightLeg.addChild(this.Shapea1);
      super.bipedRightLeg.addChild(this.Shapea2);
      super.bipedRightLeg.addChild(this.Shapea3);
      super.bipedRightLeg.addChild(this.Shapea4);
      super.bipedRightLeg.addChild(this.Shapea5);
      super.bipedRightLeg.addChild(this.Shapea6);
      super.bipedRightLeg.addChild(this.Shapea7);
      super.bipedRightLeg.addChild(this.Shapea8);
      super.bipedRightLeg.addChild(this.Shapea10);
      super.bipedRightLeg.addChild(this.Shapea11);
      super.bipedRightLeg.addChild(this.Shapea12);
      super.bipedRightLeg.addChild(this.Shapea13);
      super.bipedRightLeg.addChild(this.Shapeall);
      super.bipedRightLeg.addChild(this.Shapea33);
      super.bipedRightLeg.addChild(this.Shapeaa34);
      super.bipedRightLeg.addChild(this.Shapea35);
      super.bipedRightLeg.addChild(this.Shapea39);
      super.bipedRightLeg.addChild(this.Shapea40);
      super.bipedBody.cubeList.clear();
      super.bipedBody.addChild(this.ShapeBody1);
      super.bipedBody.addChild(this.ShapeBody2);
      super.bipedBody.addChild(this.ShapeBody3);
      super.bipedBody.addChild(this.ShapeBody4);
      super.bipedBody.addChild(this.ShapeBody5);
      super.bipedBody.addChild(this.ShapeBody6);
      super.bipedBody.addChild(this.ShapeBody7);
      super.bipedBody.addChild(this.ShapeBody8);
      super.bipedBody.addChild(this.ShapeBody9);
      super.bipedBody.addChild(this.ShapeBody10);
      super.bipedBody.addChild(this.ShapeBody11);
      super.bipedBody.addChild(this.ShapeBody12);
      super.bipedBody.addChild(this.ShapeBody13);
      super.bipedBody.addChild(this.Shapeleg1);
      super.bipedBody.addChild(this.Shapeleg2);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }

   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity ent) {
      super.setRotationAngles(f, f1, f2, f3, f4, f5, ent);
   }
}
