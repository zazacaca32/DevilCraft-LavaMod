package net.minecraft.client.addon.tchestplate.accesories.models;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelFineryLeggens extends ModelBipedAccesories
{
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer Shape5;
    ModelRenderer Shape100;
    ModelRenderer Shape101;
    ModelRenderer Shape102;
    ModelRenderer Shape103;
    ModelRenderer Shape104;
    ModelRenderer Shape105;
    ModelRenderer Shape106;
    ModelRenderer Shape107;
    ModelRenderer Shape108;
    ModelRenderer Shape109;
    ModelRenderer Shape110;
    ModelRenderer Shape111;
    ModelRenderer Shape112;
    ModelRenderer Shape113;
    ModelRenderer Shape200;
    ModelRenderer Shape201;
    ModelRenderer Shape203;
    ModelRenderer Shape204;
    ModelRenderer Shape205;
    ModelRenderer Shape206;
    ModelRenderer Shape207;
    ModelRenderer Shape208;
    ModelRenderer Shape209;
    ModelRenderer Shape210;
    ModelRenderer Shape211;
    ModelRenderer Shape212;
    final String texture = "/mods/models/accesories/finerygirl.png";

    public ModelFineryLeggens()
    {
        super.textureWidth = 32;
        super.textureHeight = 32;
        (this.Shape1 = new ModelRenderer(this, 0, 0)).addBox(-4.0F, 11.0F, -2.1F, 8, 1, 1);
        this.Shape1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape1.setTextureSize(32, 32);
        this.Shape1.mirror = true;
        this.setRotation(this.Shape1, 0.0F, 0.0F, 0.0F);
        (this.Shape2 = new ModelRenderer(this, 0, 0)).addBox(-4.0F, 11.0F, 1.1F, 8, 1, 1);
        this.Shape2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape2.setTextureSize(32, 32);
        this.Shape2.mirror = true;
        this.setRotation(this.Shape2, 0.0F, 0.0F, 0.0F);
        (this.Shape3 = new ModelRenderer(this, 0, 0)).addBox(-4.1F, 11.0F, -2.0F, 1, 1, 4);
        this.Shape3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape3.setTextureSize(32, 32);
        this.Shape3.mirror = true;
        this.setRotation(this.Shape3, 0.0F, 0.0F, 0.0F);
        (this.Shape4 = new ModelRenderer(this, 0, 0)).addBox(3.1F, 11.0F, -2.0F, 1, 1, 4);
        this.Shape4.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape4.setTextureSize(32, 32);
        this.Shape4.mirror = true;
        this.setRotation(this.Shape4, 0.0F, 0.0F, 0.0F);
        (this.Shape5 = new ModelRenderer(this, 0, 0)).addBox(-4.0F, 11.1F, -2.0F, 8, 1, 4);
        this.Shape5.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape5.setTextureSize(32, 32);
        this.Shape5.mirror = true;
        this.setRotation(this.Shape5, 0.0F, 0.0F, 0.0F);
        (this.Shape100 = new ModelRenderer(this, 0, 0)).addBox(-2.0F, 0.0F, -2.3F, 4, 2, 1);
        this.Shape100.setTextureSize(32, 32);
        this.Shape100.mirror = true;
        this.setRotation(this.Shape100, 0.0F, 0.0F, 0.0F);
        (this.Shape101 = new ModelRenderer(this, 0, 0)).addBox(-2.0F, 2.0F, -2.5F, 2, 2, 1);
        this.Shape101.setTextureSize(32, 32);
        this.Shape101.mirror = true;
        this.setRotation(this.Shape101, 0.0F, 0.0F, 0.0F);
        (this.Shape102 = new ModelRenderer(this, 0, 0)).addBox(-2.2F, 0.0F, -2.2F, 1, 4, 1);
        this.Shape102.setTextureSize(32, 32);
        this.Shape102.mirror = true;
        this.setRotation(this.Shape102, 0.0F, 0.0F, 0.0F);
        (this.Shape103 = new ModelRenderer(this, 0, 0)).addBox(-2.3F, 0.0F, -2.0F, 1, 2, 4);
        this.Shape103.setTextureSize(32, 32);
        this.Shape103.mirror = true;
        this.setRotation(this.Shape103, 0.0F, 0.0F, 0.0F);
        (this.Shape104 = new ModelRenderer(this, 0, 0)).addBox(-2.5F, 2.0F, -2.0F, 1, 2, 4);
        this.Shape104.setTextureSize(32, 32);
        this.Shape104.mirror = true;
        this.setRotation(this.Shape104, 0.0F, 0.0F, 0.0F);
        (this.Shape105 = new ModelRenderer(this, 0, 0)).addBox(-2.2F, 0.0F, 1.2F, 1, 4, 1);
        this.Shape105.setTextureSize(32, 32);
        this.Shape105.mirror = true;
        this.setRotation(this.Shape105, 0.0F, 0.0F, 0.0F);
        (this.Shape106 = new ModelRenderer(this, 0, 0)).addBox(-2.0F, 0.0F, 1.3F, 4, 2, 1);
        this.Shape106.setTextureSize(32, 32);
        this.Shape106.mirror = true;
        this.setRotation(this.Shape106, 0.0F, 0.0F, 0.0F);
        (this.Shape107 = new ModelRenderer(this, 0, 0)).addBox(-2.0F, 2.0F, 1.5F, 4, 2, 1);
        this.Shape107.setTextureSize(32, 32);
        this.Shape107.mirror = true;
        this.setRotation(this.Shape107, 0.0F, 0.0F, 0.0F);
        (this.Shape108 = new ModelRenderer(this, 0, 0)).addBox(1.2F, 0.0F, -2.0F, 1, 2, 4);
        this.Shape108.setTextureSize(32, 32);
        this.Shape108.mirror = true;
        this.setRotation(this.Shape108, 0.0F, 0.0F, 0.0F);
        (this.Shape109 = new ModelRenderer(this, 0, 16)).addBox(-2.0F, 0.0F, -2.1F, 4, 10, 1);
        this.Shape109.setTextureSize(32, 32);
        this.Shape109.mirror = true;
        this.setRotation(this.Shape109, 0.0F, 0.0F, 0.0F);
        (this.Shape110 = new ModelRenderer(this, 0, 17)).addBox(-2.1F, 0.0F, -2.0F, 1, 10, 4);
        this.Shape110.setTextureSize(32, 32);
        this.Shape110.mirror = true;
        this.setRotation(this.Shape110, 0.0F, 0.0F, 0.0F);
        (this.Shape111 = new ModelRenderer(this, 0, 16)).addBox(-2.0F, 0.0F, 1.1F, 4, 10, 1);
        this.Shape111.setTextureSize(32, 32);
        this.Shape111.mirror = true;
        this.setRotation(this.Shape111, 0.0F, 0.0F, 0.0F);
        (this.Shape112 = new ModelRenderer(this, 0, 16)).addBox(1.1F, 0.0F, -2.0F, 1, 10, 4);
        this.Shape112.setTextureSize(32, 32);
        this.Shape112.mirror = true;
        this.setRotation(this.Shape112, 0.0F, 0.0F, 0.0F);
        (this.Shape113 = new ModelRenderer(this, 0, 0)).addBox(-2.0F, -0.1F, -2.0F, 4, 1, 4);
        this.Shape113.setTextureSize(32, 32);
        this.Shape113.mirror = true;
        this.setRotation(this.Shape113, 0.0F, 0.0F, 0.0F);
        (this.Shape200 = new ModelRenderer(this, 0, 0)).addBox(-2.0F, 0.0F, -2.3F, 4, 1, 1);
        this.Shape200.setTextureSize(32, 32);
        this.Shape200.mirror = true;
        this.setRotation(this.Shape200, 0.0F, 0.0F, 0.0F);
        (this.Shape201 = new ModelRenderer(this, 0, 0)).addBox(1.2F, 0.0F, -2.2F, 1, 1, 1);
        this.Shape201.setTextureSize(32, 32);
        this.Shape201.mirror = true;
        this.setRotation(this.Shape201, 0.0F, 0.0F, 0.0F);
        (this.Shape203 = new ModelRenderer(this, 0, 0)).addBox(1.3F, 0.0F, -2.0F, 1, 1, 4);
        this.Shape203.setTextureSize(32, 32);
        this.Shape203.mirror = true;
        this.setRotation(this.Shape203, 0.0F, 0.0F, 0.0F);
        (this.Shape204 = new ModelRenderer(this, 0, 0)).addBox(1.2F, 0.0F, 1.2F, 1, 1, 1);
        this.Shape204.setTextureSize(32, 32);
        this.Shape204.mirror = true;
        this.setRotation(this.Shape204, 0.0F, 0.0F, 0.0F);
        (this.Shape205 = new ModelRenderer(this, 0, 0)).addBox(1.0F, 0.0F, 1.3F, 1, 1, 1);
        this.Shape205.setTextureSize(32, 32);
        this.Shape205.mirror = true;
        this.setRotation(this.Shape205, 0.0F, 0.0F, 0.0F);
        (this.Shape206 = new ModelRenderer(this, 0, 0)).addBox(-2.0F, 0.0F, 1.3F, 3, 2, 1);
        this.Shape206.setTextureSize(32, 32);
        this.Shape206.mirror = true;
        this.setRotation(this.Shape206, 0.0F, 0.0F, 0.0F);
        (this.Shape207 = new ModelRenderer(this, 0, 0)).addBox(-2.2F, 0.0F, -2.0F, 1, 1, 4);
        this.Shape207.setTextureSize(32, 32);
        this.Shape207.mirror = true;
        this.setRotation(this.Shape207, 0.0F, 0.0F, 0.0F);
        (this.Shape208 = new ModelRenderer(this, 0, 16)).addBox(-2.0F, 0.0F, -2.1F, 4, 10, 1);
        this.Shape208.setTextureSize(32, 32);
        this.Shape208.mirror = true;
        this.setRotation(this.Shape208, 0.0F, 0.0F, 0.0F);
        (this.Shape209 = new ModelRenderer(this, 0, 16)).addBox(1.1F, 0.0F, -2.0F, 1, 10, 4);
        this.Shape209.setTextureSize(32, 32);
        this.Shape209.mirror = true;
        this.setRotation(this.Shape209, 0.0F, 0.0F, 0.0F);
        (this.Shape210 = new ModelRenderer(this, 0, 16)).addBox(-2.0F, 0.0F, 1.1F, 4, 10, 1);
        this.Shape210.setTextureSize(32, 32);
        this.Shape210.mirror = true;
        this.setRotation(this.Shape210, 0.0F, 0.0F, 0.0F);
        (this.Shape211 = new ModelRenderer(this, 0, 16)).addBox(-2.1F, 0.0F, -2.0F, 1, 10, 4);
        this.Shape211.setTextureSize(32, 32);
        this.Shape211.mirror = true;
        this.setRotation(this.Shape211, 0.0F, 0.0F, 0.0F);
        (this.Shape212 = new ModelRenderer(this, 0, 0)).addBox(-2.0F, -0.1F, -2.0F, 4, 1, 4);
        this.Shape212.setTextureSize(32, 32);
        this.Shape212.mirror = true;
        this.setRotation(this.Shape212, 0.0F, 0.0F, 0.0F);
        super.bipedRightLeg.cubeList.clear();
        super.bipedRightLeg.addChild(this.Shape100);
        super.bipedRightLeg.addChild(this.Shape101);
        super.bipedRightLeg.addChild(this.Shape102);
        super.bipedRightLeg.addChild(this.Shape103);
        super.bipedRightLeg.addChild(this.Shape104);
        super.bipedRightLeg.addChild(this.Shape105);
        super.bipedRightLeg.addChild(this.Shape106);
        super.bipedRightLeg.addChild(this.Shape107);
        super.bipedRightLeg.addChild(this.Shape108);
        super.bipedRightLeg.addChild(this.Shape109);
        super.bipedRightLeg.addChild(this.Shape110);
        super.bipedRightLeg.addChild(this.Shape111);
        super.bipedRightLeg.addChild(this.Shape111);
        super.bipedRightLeg.addChild(this.Shape112);
        super.bipedRightLeg.addChild(this.Shape113);
        super.bipedLeftLeg.cubeList.clear();
        super.bipedLeftLeg.addChild(this.Shape200);
        super.bipedLeftLeg.addChild(this.Shape201);
        super.bipedLeftLeg.addChild(this.Shape203);
        super.bipedLeftLeg.addChild(this.Shape204);
        super.bipedLeftLeg.addChild(this.Shape205);
        super.bipedLeftLeg.addChild(this.Shape206);
        super.bipedLeftLeg.addChild(this.Shape207);
        super.bipedLeftLeg.addChild(this.Shape208);
        super.bipedLeftLeg.addChild(this.Shape209);
        super.bipedLeftLeg.addChild(this.Shape210);
        super.bipedLeftLeg.addChild(this.Shape211);
        super.bipedLeftLeg.addChild(this.Shape212);
        super.bipedBody.cubeList.clear();
        super.bipedBody.addChild(this.Shape1);
        super.bipedBody.addChild(this.Shape2);
        super.bipedBody.addChild(this.Shape3);
        super.bipedBody.addChild(this.Shape4);
        super.bipedBody.addChild(this.Shape5);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
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
        return "/mods/models/accesories/finerygirl.png";
    }
}
