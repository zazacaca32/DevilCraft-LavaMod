package assets;

import net.minecraft.client.addon.tchestplate.armors.models.ModelBaseLavaArmor;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class zzd extends ModelBaseLavaArmor
{
    ModelRenderer Shape1L;
    ModelRenderer Shape2L;
    ModelRenderer Shape3L;
    ModelRenderer Shape4L;
    ModelRenderer Shape5L;
    ModelRenderer Shape6L;
    ModelRenderer Shape7L;
    ModelRenderer Shape8L;
    ModelRenderer Shape1R;
    ModelRenderer Shape2R;
    ModelRenderer Shape3R;
    ModelRenderer Shape4R;
    ModelRenderer Shape5R;
    ModelRenderer Shape6R;
    ModelRenderer Shape7R;
    ModelRenderer Shape8R;

    public zzd()
    {
        textureWidth = 64;
        textureHeight = 64;
        Shape1R = new ModelRenderer(this, 35, 52);
        Shape1R.addBox(0F, 0F, 0F, 1, 1, 1);
        Shape1R.setRotationPoint(-2.5F, 11F, -3.8F);
        Shape1R.setTextureSize(64, 64);
        Shape1R.mirror = true;
        setRotation(Shape1R, 0F, 0.0523599F, 0F);
        Shape2R = new ModelRenderer(this, 0, 0);
        Shape2R.addBox(0F, 0F, 0F, 1, 2, 5);
        Shape2R.setRotationPoint(1F, 10F, -2.5F);
        Shape2R.setTextureSize(64, 64);
        Shape2R.mirror = true;
        setRotation(Shape2R, 0F, 0F, 0F);
        Shape3R = new ModelRenderer(this, 0, 0);
        Shape3R.addBox(0F, 0F, 0F, 1, 2, 2);
        Shape3R.setRotationPoint(-1.3F, 10.1F, -3.6F);
        Shape3R.setTextureSize(64, 64);
        Shape3R.mirror = true;
        setRotation(Shape3R, 0F, 0.0523599F, 0F);
        Shape4R = new ModelRenderer(this, 0, 0);
        Shape4R.addBox(0F, 0F, 0F, 2, 2, 2);
        Shape4R.setRotationPoint(0F, 10F, -4F);
        Shape4R.setTextureSize(64, 64);
        Shape4R.mirror = true;
        setRotation(Shape4R, 0F, 0F, 0F);
        Shape5R = new ModelRenderer(this, 35, 52);
        Shape5R.addBox(0F, 0F, 0F, 1, 1, 1);
        Shape5R.setRotationPoint(0.5F, 11F, -4.5F);
        Shape5R.setTextureSize(64, 64);
        Shape5R.mirror = true;
        setRotation(Shape5R, 0F, 0F, 0F);
        Shape6R = new ModelRenderer(this, 35, 52);
        Shape6R.addBox(0F, 0F, 0F, 1, 1, 1);
        Shape6R.setRotationPoint(-1.2F, 11F, -3.8F);
        Shape6R.setTextureSize(64, 64);
        Shape6R.mirror = true;
        setRotation(Shape6R, 0F, 0.0349066F, 0F);
        Shape7R = new ModelRenderer(this, 0, 0);
        Shape7R.addBox(0F, 0F, 0F, 4, 2, 5);
        Shape7R.setRotationPoint(-2.5F, 10F, -2.5F);
        Shape7R.setTextureSize(64, 64);
        Shape7R.mirror = true;
        setRotation(Shape7R, 0F, 0F, 0F);
        Shape8R = new ModelRenderer(this, 0, 0);
        Shape8R.addBox(0F, 0F, 0F, 1, 2, 2);
        Shape8R.setRotationPoint(-2.6F, 10.1F, -3.6F);
        Shape8R.setTextureSize(64, 64);
        Shape8R.mirror = true;
        setRotation(Shape8R, 0F, 0.0872665F, 0F);
        Shape1L = new ModelRenderer(this, 0, 0);
        Shape1L.addBox(0F, 0F, 0F, 1, 2, 5);
        Shape1L.setRotationPoint(-2F, 10F, -2.5F);
        Shape1L.setTextureSize(64, 64);
        Shape1L.mirror = true;
        setRotation(Shape1L, 0F, 0F, 0F);
        Shape2L = new ModelRenderer(this, 0, 0);
        Shape2L.addBox(0F, 0F, 0F, 4, 2, 5);
        Shape2L.setRotationPoint(-2.5F, 10F, -2.5F);
        Shape2L.setTextureSize(64, 64);
        Shape2L.mirror = true;
        setRotation(Shape2L, 0F, 0F, 0F);
        Shape3L = new ModelRenderer(this, 0, 0);
        Shape3L.addBox(0F, 0F, 0F, 2, 2, 2);
        Shape3L.setRotationPoint(-2F, 10F, -4F);
        Shape3L.setTextureSize(64, 64);
        Shape3L.mirror = true;
        setRotation(Shape3L, 0F, 0F, 0F);
        Shape4L = new ModelRenderer(this, 35, 52);
        Shape4L.addBox(0F, 0F, 0F, 1, 1, 1);
        Shape4L.setRotationPoint(-1.5F, 11F, -4.5F);
        Shape4L.setTextureSize(64, 64);
        Shape4L.mirror = true;
        setRotation(Shape4L, 0F, 0F, 0F);
        Shape5L = new ModelRenderer(this, 0, 0);
        Shape5L.addBox(0F, 0F, 0F, 1, 2, 2);
        Shape5L.setRotationPoint(0.3F, 10.1F, -3.6F);
        Shape5L.setTextureSize(64, 64);
        Shape5L.mirror = true;
        setRotation(Shape5L, 0F, -0.0523599F, 0F);
        Shape6L = new ModelRenderer(this, 0, 0);
        Shape6L.addBox(0F, 0F, 0F, 1, 2, 2);
        Shape6L.setRotationPoint(1.6F, 10.1F, -3.6F);
        Shape6L.setTextureSize(64, 64);
        Shape6L.mirror = true;
        setRotation(Shape6L, 0F, -0.0872665F, 0F);
        Shape7L = new ModelRenderer(this, 35, 52);
        Shape7L.addBox(0F, 0F, 0F, 1, 1, 1);
        Shape7L.setRotationPoint(0.2F, 11F, -3.8F);
        Shape7L.setTextureSize(64, 64);
        Shape7L.mirror = true;
        setRotation(Shape7L, 0F, -0.0349066F, 0F);
        Shape8L = new ModelRenderer(this, 35, 52);
        Shape8L.addBox(0F, 0F, 0F, 1, 1, 1);
        Shape8L.setRotationPoint(1.5F, 11F, -3.8F);
        Shape8L.setTextureSize(64, 64);
        Shape8L.mirror = true;
        setRotation(Shape8L, 0F, -0.0523599F, 0F);
        super.bipedRightLeg.cubeList.clear();
        super.bipedRightLeg.addChild(this.Shape1R);
        super.bipedRightLeg.addChild(this.Shape2R);
        super.bipedRightLeg.addChild(this.Shape3R);
        super.bipedRightLeg.addChild(this.Shape4R);
        super.bipedRightLeg.addChild(this.Shape5R);
        super.bipedRightLeg.addChild(this.Shape6R);
        super.bipedRightLeg.addChild(this.Shape7R);
        super.bipedRightLeg.addChild(this.Shape8R);
        super.bipedLeftLeg.cubeList.clear();
        super.bipedLeftLeg.addChild(this.Shape1L);
        super.bipedLeftLeg.addChild(this.Shape2L);
        super.bipedLeftLeg.addChild(this.Shape3L);
        super.bipedLeftLeg.addChild(this.Shape4L);
        super.bipedLeftLeg.addChild(this.Shape5L);
        super.bipedLeftLeg.addChild(this.Shape6L);
        super.bipedLeftLeg.addChild(this.Shape7L);
        super.bipedLeftLeg.addChild(this.Shape8L);
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

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
    {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    }

    public String getTexture()
    {
        return "/mods/models/accesories/pantera1.png";
    }
}
