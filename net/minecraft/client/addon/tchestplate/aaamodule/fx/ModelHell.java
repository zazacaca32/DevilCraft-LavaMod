package net.minecraft.client.addon.tchestplate.aaamodule.fx;

import net.minecraft.client.addon.tchestplate.armors.models.ModelBaseLavaArmor;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

public class ModelHell extends ModelBaseLavaArmor {

   float rtd;
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
   long tim = 0L;
   int ren = 1;
   boolean t = true;


   public ModelHell() {
      super.textureWidth = 128;
      super.textureHeight = 64;
      this.Shape20 = new ModelRenderer(this, 0, 35);
      this.Shape20.addBox(-14.0F, 24.0F, -14.0F, 28, 0, 28);
      this.Shape20.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape20.setTextureSize(128, 64);
      this.Shape20.mirror = true;
      this.setRotation(this.Shape20, 0.0F, 0.0F, 0.0F);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }

   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity ent) {
      super.setRotationAngles(f, f1, f2, f3, f4, f5, ent);
   }

   public void renders() {
      float f5 = 0.0625F;
      GL11.glPushMatrix();
      this.Shape20.render(f5);
      GL11.glPopMatrix();
   }
}
