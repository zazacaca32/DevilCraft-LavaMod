package assets;

import java.util.List;
import net.minecraft.client.addon.tchestplate.armors.models.ModelBaseLavaArmor;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;

public class db
  extends ModelBaseLavaArmor
{
  ModelRenderer Shape1R;
  ModelRenderer Shape1L;
  ModelRenderer Shape2R;
  ModelRenderer Shape3R;
  ModelRenderer Shape2L;
  ModelRenderer Shape3L;
  ModelRenderer Shape4R;
  ModelRenderer Shape16L;
  ModelRenderer Shape5R;
  ModelRenderer Shape4L;
  ModelRenderer Shape6R;
  ModelRenderer Shape5L;
  ModelRenderer Shape7R;
  ModelRenderer Shape6L;
  ModelRenderer Shape8R;
  ModelRenderer Shape9R;
  ModelRenderer Shape7L;
  ModelRenderer Shape10R;
  ModelRenderer Shape11R;
  ModelRenderer Shape12R;
  ModelRenderer Shape8L;
  ModelRenderer Shape9L;
  ModelRenderer Shape10L;
  ModelRenderer Shape11L;
  ModelRenderer Shape12L;
  ModelRenderer Shape13R;
  ModelRenderer Shape13L;
  ModelRenderer Shape14R;
  ModelRenderer Shape15R;
  ModelRenderer Shape14L;
  ModelRenderer Shape16R;
  ModelRenderer Shape15L;
  ModelRenderer Shape17R;
  ModelRenderer Shape17L;
  
  public db()
  {
    this.textureWidth = 64;
    this.textureHeight = 32;
    this.Shape1R = new ModelRenderer(this, 31, 20);
    this.Shape1R.addBox(-2.0F, 10.1F, -5.0F, 4, 2, 3);
    this.Shape1R.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.Shape1R.setTextureSize(64, 32);
    this.Shape1R.mirror = true;
    setRotation(this.Shape1R, 0.0F, 0.0F, 0.0F);
    this.Shape1L = new ModelRenderer(this, 0, 0);
    this.Shape1L.addBox(1.0F, 10.1F, -6.0F, 1, 2, 1);
    this.Shape1L.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.Shape1L.setTextureSize(64, 32);
    this.Shape1L.mirror = true;
    setRotation(this.Shape1L, 0.0F, 0.0F, 0.0F);
    this.Shape2R = new ModelRenderer(this, 0, 0);
    this.Shape2R.addBox(-2.0F, 10.1F, -6.0F, 1, 2, 1);
    this.Shape2R.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.Shape2R.setTextureSize(64, 32);
    this.Shape2R.mirror = true;
    setRotation(this.Shape2R, 0.0F, 0.0F, 0.0F);
    this.Shape3R = new ModelRenderer(this, 0, 0);
    this.Shape3R.addBox(-0.5F, 10.1F, -6.0F, 2, 2, 1);
    this.Shape3R.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.Shape3R.setTextureSize(64, 32);
    this.Shape3R.mirror = true;
    setRotation(this.Shape3R, 0.0F, 0.0F, 0.0F);
    this.Shape2L = new ModelRenderer(this, 0, 0);
    this.Shape2L.addBox(-1.5F, 10.1F, -6.0F, 2, 2, 1);
    this.Shape2L.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.Shape2L.setTextureSize(64, 32);
    this.Shape2L.mirror = true;
    setRotation(this.Shape2L, 0.0F, 0.0F, 0.0F);
    this.Shape3L = new ModelRenderer(this, 0, 0);
    this.Shape3L.addBox(-1.0F, 8.1F, -4.0F, 3, 2, 2);
    this.Shape3L.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.Shape3L.setTextureSize(64, 32);
    this.Shape3L.mirror = true;
    setRotation(this.Shape3L, 0.0F, 0.0F, 0.0F);
    this.Shape4R = new ModelRenderer(this, 0, 0);
    this.Shape4R.addBox(-2.0F, 8.1F, -4.0F, 3, 2, 2);
    this.Shape4R.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.Shape4R.setTextureSize(64, 32);
    this.Shape4R.mirror = true;
    setRotation(this.Shape4R, 0.0F, 0.0F, 0.0F);
    this.Shape16L = new ModelRenderer(this, 0, 0);
    this.Shape16L.addBox(-1.0F, 9.1F, -5.0F, 3, 1, 1);
    this.Shape16L.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.Shape16L.setTextureSize(64, 32);
    this.Shape16L.mirror = true;
    setRotation(this.Shape16L, 0.0F, 0.0F, 0.0F);
    this.Shape5R = new ModelRenderer(this, 0, 0);
    this.Shape5R.addBox(-2.0F, 9.1F, -5.0F, 3, 1, 1);
    this.Shape5R.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.Shape5R.setTextureSize(64, 32);
    this.Shape5R.mirror = true;
    setRotation(this.Shape5R, 0.0F, 0.0F, 0.0F);
    this.Shape4L = new ModelRenderer(this, 0, 0);
    this.Shape4L.addBox(-1.0F, 6.1F, -3.0F, 2, 2, 1);
    this.Shape4L.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.Shape4L.setTextureSize(64, 32);
    this.Shape4L.mirror = true;
    setRotation(this.Shape4L, 0.0F, 0.0F, 0.0F);
    this.Shape6R = new ModelRenderer(this, 0, 0);
    this.Shape6R.addBox(-1.0F, 6.1F, -3.0F, 2, 2, 1);
    this.Shape6R.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.Shape6R.setTextureSize(64, 32);
    this.Shape6R.mirror = true;
    setRotation(this.Shape6R, 0.0F, 0.0F, 0.0F);
    this.Shape5L = new ModelRenderer(this, 0, 0);
    this.Shape5L.addBox(-2.0F, 8.1F, -2.3F, 1, 2, 1);
    this.Shape5L.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.Shape5L.setTextureSize(64, 32);
    this.Shape5L.mirror = true;
    setRotation(this.Shape5L, 0.0F, 0.0F, 0.0F);
    this.Shape7R = new ModelRenderer(this, 0, 0);
    this.Shape7R.addBox(1.0F, 8.1F, -2.3F, 1, 2, 1);
    this.Shape7R.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.Shape7R.setTextureSize(64, 32);
    this.Shape7R.mirror = true;
    setRotation(this.Shape7R, 0.0F, 0.0F, 0.0F);
    this.Shape6L = new ModelRenderer(this, 0, 0);
    this.Shape6L.addBox(-2.0F, 9.1F, -3.3F, 1, 1, 1);
    this.Shape6L.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.Shape6L.setTextureSize(64, 32);
    this.Shape6L.mirror = true;
    setRotation(this.Shape6L, 0.0F, 0.0F, 0.0F);
    this.Shape8R = new ModelRenderer(this, 0, 0);
    this.Shape8R.addBox(1.0F, 9.1F, -3.3F, 1, 1, 1);
    this.Shape8R.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.Shape8R.setTextureSize(64, 32);
    this.Shape8R.mirror = true;
    setRotation(this.Shape8R, 0.0F, 0.0F, 0.0F);
    this.Shape9R = new ModelRenderer(this, 0, 0);
    this.Shape9R.addBox(-3.0F, 11.1F, -4.0F, 1, 1, 6);
    this.Shape9R.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.Shape9R.setTextureSize(64, 32);
    this.Shape9R.mirror = true;
    setRotation(this.Shape9R, 0.0F, 0.0F, 0.0F);
    this.Shape7L = new ModelRenderer(this, 34, 21);
    this.Shape7L.addBox(-2.0F, 10.1F, -5.0F, 4, 2, 3);
    this.Shape7L.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.Shape7L.setTextureSize(64, 32);
    this.Shape7L.mirror = true;
    setRotation(this.Shape7L, 0.0F, 0.0F, 0.0F);
    this.Shape10R = new ModelRenderer(this, 0, 0);
    this.Shape10R.addBox(-3.0F, 10.1F, -3.0F, 1, 1, 5);
    this.Shape10R.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.Shape10R.setTextureSize(64, 32);
    this.Shape10R.mirror = true;
    setRotation(this.Shape10R, 0.0F, 0.0F, 0.0F);
    this.Shape11R = new ModelRenderer(this, 0, 0);
    this.Shape11R.addBox(-3.0F, 9.1F, -2.0F, 1, 1, 4);
    this.Shape11R.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.Shape11R.setTextureSize(64, 32);
    this.Shape11R.mirror = true;
    setRotation(this.Shape11R, 0.0F, 0.0F, 0.0F);
    this.Shape12R = new ModelRenderer(this, 0, 0);
    this.Shape12R.addBox(-2.5F, 8.1F, -1.0F, 1, 1, 2);
    this.Shape12R.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.Shape12R.setTextureSize(64, 32);
    this.Shape12R.mirror = true;
    setRotation(this.Shape12R, 0.0F, 0.0F, 0.0F);
    this.Shape8L = new ModelRenderer(this, 0, 0);
    this.Shape8L.addBox(2.0F, 11.1F, -4.0F, 1, 1, 6);
    this.Shape8L.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.Shape8L.setTextureSize(64, 32);
    this.Shape8L.mirror = true;
    setRotation(this.Shape8L, 0.0F, 0.0F, 0.0F);
    this.Shape9L = new ModelRenderer(this, 0, 0);
    this.Shape9L.addBox(2.0F, 10.1F, -3.0F, 1, 1, 5);
    this.Shape9L.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.Shape9L.setTextureSize(64, 32);
    this.Shape9L.mirror = true;
    setRotation(this.Shape9L, 0.0F, 0.0F, 0.0F);
    this.Shape10L = new ModelRenderer(this, 0, 0);
    this.Shape10L.addBox(2.0F, 9.1F, -2.0F, 1, 1, 4);
    this.Shape10L.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.Shape10L.setTextureSize(64, 32);
    this.Shape10L.mirror = true;
    setRotation(this.Shape10L, 0.0F, 0.0F, 0.0F);
    this.Shape11L = new ModelRenderer(this, 0, 0);
    this.Shape11L.addBox(1.5F, 8.1F, -1.0F, 1, 1, 2);
    this.Shape11L.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.Shape11L.setTextureSize(64, 32);
    this.Shape11L.mirror = true;
    setRotation(this.Shape11L, 0.0F, 0.0F, 0.0F);
    this.Shape12L = new ModelRenderer(this, 0, 0);
    this.Shape12L.addBox(-2.0F, 9.1F, 2.0F, 4, 3, 1);
    this.Shape12L.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.Shape12L.setTextureSize(64, 32);
    this.Shape12L.mirror = true;
    setRotation(this.Shape12L, 0.0F, 0.0F, 0.0F);
    this.Shape13R = new ModelRenderer(this, 0, 0);
    this.Shape13R.addBox(-2.0F, 9.1F, 2.0F, 4, 3, 1);
    this.Shape13R.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.Shape13R.setTextureSize(64, 32);
    this.Shape13R.mirror = true;
    setRotation(this.Shape13R, 0.0F, 0.0F, 0.0F);
    this.Shape13L = new ModelRenderer(this, 0, 0);
    this.Shape13L.addBox(1.5F, 9.1F, 2.0F, 1, 3, 1);
    this.Shape13L.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.Shape13L.setTextureSize(64, 32);
    this.Shape13L.mirror = true;
    setRotation(this.Shape13L, 0.0F, 0.0F, 0.0F);
    this.Shape14R = new ModelRenderer(this, 0, 0);
    this.Shape14R.addBox(-2.5F, 9.1F, 2.0F, 1, 3, 1);
    this.Shape14R.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.Shape14R.setTextureSize(64, 32);
    this.Shape14R.mirror = true;
    setRotation(this.Shape14R, 0.0F, 0.0F, 0.0F);
    this.Shape15R = new ModelRenderer(this, 0, 0);
    this.Shape15R.addBox(-1.0F, 8.1F, 2.0F, 2, 1, 1);
    this.Shape15R.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.Shape15R.setTextureSize(64, 32);
    this.Shape15R.mirror = true;
    setRotation(this.Shape15R, 0.0F, 0.0F, 0.0F);
    this.Shape14L = new ModelRenderer(this, 0, 0);
    this.Shape14L.addBox(-0.5F, 7.1F, 2.0F, 1, 1, 1);
    this.Shape14L.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.Shape14L.setTextureSize(64, 32);
    this.Shape14L.mirror = true;
    setRotation(this.Shape14L, 0.0F, 0.0F, 0.0F);
    this.Shape16R = new ModelRenderer(this, 0, 0);
    this.Shape16R.addBox(-0.5F, 7.1F, 2.0F, 1, 1, 1);
    this.Shape16R.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.Shape16R.setTextureSize(64, 32);
    this.Shape16R.mirror = true;
    setRotation(this.Shape16R, 0.0F, 0.0F, 0.0F);
    this.Shape15L = new ModelRenderer(this, 0, 0);
    this.Shape15L.addBox(-1.0F, 8.1F, 2.0F, 2, 1, 1);
    this.Shape15L.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.Shape15L.setTextureSize(64, 32);
    this.Shape15L.mirror = true;
    setRotation(this.Shape15L, 0.0F, 0.0F, 0.0F);
    this.Shape17L = new ModelRenderer(this, 0, 0);
    this.Shape17L.addBox(-2.0F, 11.1F, -2.0F, 4, 1, 4);
    this.Shape17L.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.Shape17L.setTextureSize(64, 32);
    this.Shape17L.mirror = true;
    setRotation(this.Shape17L, 0.0F, 0.0F, 0.0F);
    this.Shape17R = new ModelRenderer(this, 0, 0);
    this.Shape17R.addBox(-2.0F, 11.1F, -2.0F, 4, 1, 4);
    this.Shape17R.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.Shape17R.setTextureSize(64, 32);
    this.Shape17R.mirror = true;
    setRotation(this.Shape17R, 0.0F, 0.0F, 0.0F);
    this.bipedRightLeg.cubeList.clear();
    this.bipedRightLeg.addChild(this.Shape1R);
    this.bipedRightLeg.addChild(this.Shape2R);
    this.bipedRightLeg.addChild(this.Shape3R);
    this.bipedRightLeg.addChild(this.Shape4R);
    this.bipedRightLeg.addChild(this.Shape5R);
    this.bipedRightLeg.addChild(this.Shape6R);
    this.bipedRightLeg.addChild(this.Shape7R);
    this.bipedRightLeg.addChild(this.Shape8R);
    this.bipedRightLeg.addChild(this.Shape9R);
    this.bipedRightLeg.addChild(this.Shape10R);
    this.bipedRightLeg.addChild(this.Shape11R);
    this.bipedRightLeg.addChild(this.Shape12R);
    this.bipedRightLeg.addChild(this.Shape13R);
    this.bipedRightLeg.addChild(this.Shape14R);
    this.bipedRightLeg.addChild(this.Shape15R);
    this.bipedRightLeg.addChild(this.Shape16R);
    this.bipedRightLeg.addChild(this.Shape17R);
    this.bipedLeftLeg.cubeList.clear();
    this.bipedLeftLeg.addChild(this.Shape1L);
    this.bipedLeftLeg.addChild(this.Shape2L);
    this.bipedLeftLeg.addChild(this.Shape3L);
    this.bipedLeftLeg.addChild(this.Shape4L);
    this.bipedLeftLeg.addChild(this.Shape5L);
    this.bipedLeftLeg.addChild(this.Shape6L);
    this.bipedLeftLeg.addChild(this.Shape7L);
    this.bipedLeftLeg.addChild(this.Shape8L);
    this.bipedLeftLeg.addChild(this.Shape9L);
    this.bipedLeftLeg.addChild(this.Shape10L);
    this.bipedLeftLeg.addChild(this.Shape11L);
    this.bipedLeftLeg.addChild(this.Shape12L);
    this.bipedLeftLeg.addChild(this.Shape13L);
    this.bipedLeftLeg.addChild(this.Shape14L);
    this.bipedLeftLeg.addChild(this.Shape15L);
    this.bipedLeftLeg.addChild(this.Shape16L);
    this.bipedLeftLeg.addChild(this.Shape17L);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public String getTexture()
  {
    return "/mods/Alo/renders/demonAlo.png";
  }
}
