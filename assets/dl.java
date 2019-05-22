package assets;

import java.util.List;
import net.minecraft.client.addon.tchestplate.armors.models.ModelBaseLavaArmor;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class dl
  extends ModelBaseLavaArmor
{
  ModelRenderer Shape1L;
  ModelRenderer Shape2L;
  ModelRenderer Shape3L;
  ModelRenderer Shape1R;
  ModelRenderer Shape2R;
  ModelRenderer Shape4R;
  ModelRenderer Shape5L;
  ModelRenderer Shape6R;
  ModelRenderer Shape60L;
  ModelRenderer Shape61L;
  ModelRenderer Shape61R;
  ModelRenderer Shape60R;
  ModelRenderer Shape20L;
  ModelRenderer Shape20R;
  ModelRenderer Shape51L;
  ModelRenderer Shape51R;
  ModelRenderer Shape50L;
  ModelRenderer Shape50R;
  ModelRenderer Shape52L;
  ModelRenderer Shape52R;
  ModelRenderer Shape53L;
  ModelRenderer Shape53R;
  ModelRenderer Shape54L;
  ModelRenderer Shape54R;
  ModelRenderer Shape55R;
  ModelRenderer Shape55L;
  ModelRenderer Shape56L;
  ModelRenderer Shape56R;
  
  public dl()
  {
    this.textureWidth = 64;
    this.textureHeight = 32;
    this.Shape1L = new ModelRenderer(this, 45, 13);
    this.Shape1L.addBox(-1.8F, 0.0F, -2.0F, 4, 12, 4);
    this.Shape1L.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.Shape1L.setTextureSize(64, 32);
    this.Shape1L.mirror = true;
    setRotation(this.Shape1L, 0.0F, 0.0F, 0.0F);
    this.Shape2L = new ModelRenderer(this, 45, 18);
    this.Shape2L.addBox(-2.0F, 0.0F, -2.2F, 4, 12, 4);
    this.Shape2L.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.Shape2L.setTextureSize(64, 32);
    this.Shape2L.mirror = true;
    setRotation(this.Shape2L, 0.0F, 0.0F, 0.0F);
    this.Shape3L = new ModelRenderer(this, 45, 18);
    this.Shape3L.addBox(-2.0F, 0.0F, 1.1F, 4, 12, 1);
    this.Shape3L.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.Shape3L.setTextureSize(64, 32);
    this.Shape3L.mirror = true;
    setRotation(this.Shape3L, 0.0F, 0.0F, 0.0F);
    this.Shape1R = new ModelRenderer(this, 45, 15);
    this.Shape1R.addBox(-2.2F, 0.0F, -2.0F, 4, 12, 4);
    this.Shape1R.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.Shape1R.setTextureSize(64, 32);
    this.Shape1R.mirror = true;
    setRotation(this.Shape1R, 0.0F, 0.0F, 0.0F);
    this.Shape2R = new ModelRenderer(this, 45, 18);
    this.Shape2R.addBox(-2.0F, 0.0F, -1.8F, 4, 12, 4);
    this.Shape2R.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.Shape2R.setTextureSize(64, 32);
    this.Shape2R.mirror = true;
    setRotation(this.Shape2R, 0.0F, 0.0F, 0.0F);
    this.Shape4R = new ModelRenderer(this, 45, 18);
    this.Shape4R.addBox(-2.2F, 0.0F, -2.2F, 4, 12, 4);
    this.Shape4R.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.Shape4R.setTextureSize(64, 32);
    this.Shape4R.mirror = true;
    setRotation(this.Shape4R, 0.0F, 0.0F, 0.0F);
    
    this.Shape5L = new ModelRenderer(this, 45, 18);
    this.Shape5L.addBox(-2.0F, 8.1F, -2.0F, 4, 1, 4);
    this.Shape5L.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.Shape5L.setTextureSize(64, 32);
    this.Shape5L.mirror = true;
    setRotation(this.Shape5L, 0.0F, 0.0F, 0.0F);
    this.Shape6R = new ModelRenderer(this, 45, 18);
    this.Shape6R.addBox(-2.0F, 8.1F, -2.0F, 4, 1, 4);
    this.Shape6R.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.Shape6R.setTextureSize(64, 32);
    this.Shape6R.mirror = true;
    setRotation(this.Shape6R, 0.0F, 0.0F, 0.0F);
    this.Shape60L = new ModelRenderer(this, 0, 0);
    this.Shape60L.addBox(-2.0F, 0.0F, -3.6F, 4, 1, 2);
    this.Shape60L.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.Shape60L.setTextureSize(64, 32);
    this.Shape60L.mirror = true;
    setRotation(this.Shape60L, 0.0F, 0.0F, 0.0F);
    this.Shape61L = new ModelRenderer(this, 0, 0);
    this.Shape61L.addBox(-1.0F, 1.0F, -3.6F, 2, 2, 2);
    this.Shape61L.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.Shape61L.setTextureSize(64, 32);
    this.Shape61L.mirror = true;
    setRotation(this.Shape61L, 0.0F, 0.0F, 0.0F);
    this.Shape61R = new ModelRenderer(this, 0, 0);
    this.Shape61R.addBox(-1.0F, 1.0F, -3.6F, 2, 2, 2);
    this.Shape61R.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.Shape61R.setTextureSize(64, 32);
    this.Shape61R.mirror = true;
    setRotation(this.Shape61R, 0.0F, 0.0F, 0.0F);
    this.Shape60R = new ModelRenderer(this, 0, 0);
    this.Shape60R.addBox(-2.0F, 0.0F, -3.6F, 4, 1, 2);
    this.Shape60R.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.Shape60R.setTextureSize(64, 32);
    this.Shape60R.mirror = true;
    setRotation(this.Shape60R, 0.0F, 0.0F, 0.0F);
    this.Shape20L = new ModelRenderer(this, 48, 16);
    this.Shape20L.addBox(-2.3F, 0.0F, -2.0F, 4, 12, 4);
    this.Shape20L.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.Shape20L.setTextureSize(64, 32);
    this.Shape20L.mirror = true;
    setRotation(this.Shape20L, 0.0F, 0.0F, 0.0F);
    this.Shape20R = new ModelRenderer(this, 48, 16);
    this.Shape20R.addBox(-2.3F, 0.0F, -2.0F, 4, 12, 4);
    this.Shape20R.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.Shape20R.setTextureSize(64, 32);
    this.Shape20R.mirror = true;
    setRotation(this.Shape20R, 0.0F, 0.0F, 0.0F);
    this.Shape51L = new ModelRenderer(this, 0, 0);
    this.Shape51L.addBox(-2.0F, 0.0F, 1.5F, 4, 2, 2);
    this.Shape51L.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.Shape51L.setTextureSize(64, 32);
    this.Shape51L.mirror = true;
    setRotation(this.Shape51L, 0.0F, 0.0F, 0.0F);
    this.Shape51R = new ModelRenderer(this, 0, 0);
    this.Shape51R.addBox(-2.0F, 0.0F, 1.5F, 4, 2, 2);
    this.Shape51R.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.Shape51R.setTextureSize(64, 32);
    this.Shape51R.mirror = true;
    setRotation(this.Shape51R, 0.0F, 0.0F, 0.0F);
    this.Shape50L = new ModelRenderer(this, 0, 0);
    this.Shape50L.addBox(-0.5F, 2.0F, 1.5F, 2, 1, 2);
    this.Shape50L.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.Shape50L.setTextureSize(64, 32);
    this.Shape50L.mirror = true;
    setRotation(this.Shape50L, 0.0F, 0.0F, 0.0F);
    this.Shape50R = new ModelRenderer(this, 0, 0);
    this.Shape50R.addBox(-1.5F, 2.0F, 2.5F, 2, 1, 1);
    this.Shape50R.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.Shape50R.setTextureSize(64, 32);
    this.Shape50R.mirror = true;
    setRotation(this.Shape50R, 0.0F, 0.0F, 0.0F);
    this.Shape52L = new ModelRenderer(this, 0, 0);
    this.Shape52L.addBox(-1.5F, 3.0F, 1.8F, 3, 1, 2);
    this.Shape52L.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.Shape52L.setTextureSize(64, 32);
    this.Shape52L.mirror = true;
    setRotation(this.Shape52L, 0.0F, 0.0F, 0.0F);
    this.Shape52R = new ModelRenderer(this, 0, 0);
    this.Shape52R.addBox(-1.5F, 3.0F, 1.8F, 3, 1, 2);
    this.Shape52R.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.Shape52R.setTextureSize(64, 32);
    this.Shape52R.mirror = true;
    setRotation(this.Shape52R, 0.0F, 0.0F, 0.0F);
    this.Shape53L = new ModelRenderer(this, 0, 0);
    this.Shape53L.addBox(-1.0F, 3.5F, 2.0F, 3, 2, 2);
    this.Shape53L.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.Shape53L.setTextureSize(64, 32);
    this.Shape53L.mirror = true;
    setRotation(this.Shape53L, 0.0F, 0.0F, 0.0F);
    this.Shape53R = new ModelRenderer(this, 0, 0);
    this.Shape53R.addBox(-2.0F, 3.5F, 2.0F, 3, 2, 2);
    this.Shape53R.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.Shape53R.setTextureSize(64, 32);
    this.Shape53R.mirror = true;
    setRotation(this.Shape53R, 0.0F, 0.0F, 0.0F);
    this.Shape54L = new ModelRenderer(this, 0, 0);
    this.Shape54L.addBox(-1.5F, 5.0F, 2.0F, 3, 1, 2);
    this.Shape54L.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.Shape54L.setTextureSize(64, 32);
    this.Shape54L.mirror = true;
    setRotation(this.Shape54L, 0.0F, 0.0F, 0.0F);
    this.Shape54R = new ModelRenderer(this, 0, 0);
    this.Shape54R.addBox(-1.5F, 5.0F, 2.0F, 3, 1, 2);
    this.Shape54R.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.Shape54R.setTextureSize(64, 32);
    this.Shape54R.mirror = true;
    setRotation(this.Shape54R, 0.0F, 0.0F, 0.0F);
    this.Shape55R = new ModelRenderer(this, 0, 0);
    this.Shape55R.addBox(-2.5F, 3.6F, 2.0F, 1, 2, 1);
    this.Shape55R.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.Shape55R.setTextureSize(64, 32);
    this.Shape55R.mirror = true;
    setRotation(this.Shape55R, 0.0F, 0.0F, 0.0F);
    this.Shape55L = new ModelRenderer(this, 0, 0);
    this.Shape55L.addBox(1.5F, 3.6F, 2.0F, 1, 2, 1);
    this.Shape55L.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.Shape55L.setTextureSize(64, 32);
    this.Shape55L.mirror = true;
    setRotation(this.Shape55L, 0.0F, 0.0F, 0.0F);
    this.Shape56L = new ModelRenderer(this, 0, 0);
    this.Shape56L.addBox(1.5F, 2.5F, 1.0F, 1, 3, 1);
    this.Shape56L.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.Shape56L.setTextureSize(64, 32);
    this.Shape56L.mirror = true;
    setRotation(this.Shape56L, 0.0F, 0.0F, 0.0F);
    this.Shape56R = new ModelRenderer(this, 0, 0);
    this.Shape56R.addBox(-2.5F, 2.5F, 1.0F, 1, 3, 1);
    this.Shape56R.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.Shape56R.setTextureSize(64, 32);
    this.Shape56R.mirror = true;
    setRotation(this.Shape56R, 0.0F, 0.0F, 0.0F);
    this.bipedRightLeg.cubeList.clear();
    this.bipedRightLeg.addChild(this.Shape1R);
    this.bipedRightLeg.addChild(this.Shape2R);
    this.bipedRightLeg.addChild(this.Shape4R);
    this.bipedRightLeg.addChild(this.Shape6R);
    this.bipedRightLeg.addChild(this.Shape52R);
    this.bipedRightLeg.addChild(this.Shape53R);
    this.bipedRightLeg.addChild(this.Shape54R);
    this.bipedRightLeg.addChild(this.Shape55R);
    this.bipedRightLeg.addChild(this.Shape56R);
    this.bipedRightLeg.addChild(this.Shape60R);
    this.bipedRightLeg.addChild(this.Shape61R);
    this.bipedRightLeg.addChild(this.Shape51R);
    this.bipedRightLeg.addChild(this.Shape50R);
    this.bipedLeftLeg.addChild(this.Shape20L);
    this.bipedLeftLeg.cubeList.clear();
    this.bipedLeftLeg.addChild(this.Shape1L);
    this.bipedLeftLeg.addChild(this.Shape2L);
    this.bipedLeftLeg.addChild(this.Shape3L);
    this.bipedLeftLeg.addChild(this.Shape5L);
    this.bipedLeftLeg.addChild(this.Shape20R);
    this.bipedLeftLeg.addChild(this.Shape20L);
    this.bipedLeftLeg.addChild(this.Shape52L);
    this.bipedLeftLeg.addChild(this.Shape53L);
    this.bipedLeftLeg.addChild(this.Shape54L);
    this.bipedLeftLeg.addChild(this.Shape55L);
    this.bipedLeftLeg.addChild(this.Shape56L);
    this.bipedLeftLeg.addChild(this.Shape50L);
    this.bipedLeftLeg.addChild(this.Shape51L);
    this.bipedLeftLeg.addChild(this.Shape60L);
    this.bipedLeftLeg.addChild(this.Shape61L);
    this.bipedBody.cubeList.clear();
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
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
  
  public String getTexture()
  {
    return "/mods/Alo/renders/demonAlo.png";
  }
}
