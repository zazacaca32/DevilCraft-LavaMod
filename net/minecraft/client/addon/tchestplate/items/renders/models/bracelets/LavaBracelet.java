package net.minecraft.client.addon.tchestplate.items.renders.models.bracelets;

import net.minecraft.client.addon.tchestplate.items.renders.models.BaseItemModel;
import net.minecraft.client.addon.tchestplate.items.renders.models.BodyPart;
import net.minecraft.client.addon.tchestplate.items.renders.models.RightHand;

public class LavaBracelet extends RightHand
{
    BodyPart Shape1;
    BodyPart Shape2;
    BodyPart Shape3;
    BodyPart Shape4;
    BodyPart Shape5;
    BodyPart Shape6;
    BodyPart Shape7;
    BodyPart Shape8;
    BodyPart Shape9;
    BodyPart Shape10;
    BodyPart Shape11;
    BodyPart Shape12;

    public LavaBracelet(String tex, int offsettexture)
    {
        super(BaseItemModel.ModelType.BRACELETS);
        super.textureWidth = 32;
        super.textureHeight = 32;
        (this.Shape1 = new BodyPart(this, 1, 0 + offsettexture)).addBox(-4.1F, 6.2F, -1.5F, 1, 2, 3);
        this.Shape1.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.Shape1.setTextureSize(32, 32);
        this.Shape1.mirror = true;
        this.setRotation(this.Shape1, 0.0F, 0.0F, 0.0F);
        (this.Shape2 = new BodyPart(this, 0, 2 + offsettexture)).addBox(-2.5F, 6.2F, -3.1F, 3, 2, 1);
        this.Shape2.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.Shape2.setTextureSize(32, 32);
        this.Shape2.mirror = true;
        this.setRotation(this.Shape2, 0.0F, 0.0F, 0.0F);
        (this.Shape3 = new BodyPart(this, 0, 2 + offsettexture)).addBox(-3.7F, 6.2F, -2.2F, 1, 2, 1);
        this.Shape3.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.Shape3.setTextureSize(32, 32);
        this.Shape3.mirror = true;
        this.setRotation(this.Shape3, 0.0F, 0.0F, 0.0F);
        (this.Shape4 = new BodyPart(this, 0, 2 + offsettexture)).addBox(-2.5F, 6.2F, 2.1F, 3, 2, 1);
        this.Shape4.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.Shape4.setTextureSize(32, 32);
        this.Shape4.mirror = true;
        this.setRotation(this.Shape4, 0.0F, 0.0F, 0.0F);
        (this.Shape5 = new BodyPart(this, 0, 2 + offsettexture)).addBox(-3.2F, 6.2F, 1.7F, 1, 2, 1);
        this.Shape5.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.Shape5.setTextureSize(32, 32);
        this.Shape5.mirror = true;
        this.setRotation(this.Shape5, 0.0F, 0.0F, 0.0F);
        (this.Shape6 = new BodyPart(this, 0, 0 + offsettexture)).addBox(1.1F, 6.2F, -1.5F, 1, 2, 3);
        this.Shape6.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.Shape6.setTextureSize(32, 32);
        this.Shape6.mirror = true;
        this.setRotation(this.Shape6, 0.0F, 0.0F, 0.0F);
        (this.Shape7 = new BodyPart(this, 0, 2 + offsettexture)).addBox(0.2F, 6.2F, -2.7F, 1, 2, 1);
        this.Shape7.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.Shape7.setTextureSize(32, 32);
        this.Shape7.mirror = true;
        this.setRotation(this.Shape7, 0.0F, 0.0F, 0.0F);
        (this.Shape8 = new BodyPart(this, 0, 2 + offsettexture)).addBox(0.2F, 6.2F, 1.7F, 1, 2, 1);
        this.Shape8.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.Shape8.setTextureSize(32, 32);
        this.Shape8.mirror = true;
        this.setRotation(this.Shape8, 0.0F, 0.0F, 0.0F);
        (this.Shape9 = new BodyPart(this, 0, 2 + offsettexture)).addBox(-3.2F, 6.2F, -2.7F, 1, 2, 1);
        this.Shape9.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.Shape9.setTextureSize(32, 32);
        this.Shape9.mirror = true;
        this.setRotation(this.Shape9, 0.0F, 0.0F, 0.0F);
        (this.Shape10 = new BodyPart(this, 0, 2 + offsettexture)).addBox(0.7F, 6.2F, -2.2F, 1, 2, 1);
        this.Shape10.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.Shape10.setTextureSize(32, 32);
        this.Shape10.mirror = true;
        this.setRotation(this.Shape10, 0.0F, 0.0F, 0.0F);
        (this.Shape11 = new BodyPart(this, 0, 2 + offsettexture)).addBox(-3.7F, 6.2F, 1.2F, 1, 2, 1);
        this.Shape11.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.Shape11.setTextureSize(32, 32);
        this.Shape11.mirror = true;
        this.setRotation(this.Shape11, 0.0F, 0.0F, 0.0F);
        (this.Shape12 = new BodyPart(this, 0, 2 + offsettexture)).addBox(0.7F, 6.2F, 1.2F, 1, 2, 1);
        this.Shape12.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.Shape12.setTextureSize(32, 32);
        this.Shape12.mirror = true;
        this.setRotation(this.Shape12, 0.0F, 0.0F, 0.0F);
        this.setTexture(tex);
    }

    public String getName()
    {
        return "Лава браслет";
    }
}
