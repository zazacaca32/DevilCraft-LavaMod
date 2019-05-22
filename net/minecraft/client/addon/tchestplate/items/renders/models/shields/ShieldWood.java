package net.minecraft.client.addon.tchestplate.items.renders.models.shields;

import net.minecraft.client.addon.tchestplate.items.renders.models.BaseItemModel;
import net.minecraft.client.addon.tchestplate.items.renders.models.BodyPart;
import net.minecraft.client.addon.tchestplate.items.renders.models.LeftHand;

public class ShieldWood extends LeftHand
{
    private static final String tex = "/mods/LavaChestPlate/textures/items/shields/wood.png";
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
    BodyPart Shape13;
    BodyPart Shape14;
    BodyPart Shape15;
    BodyPart Shape16;
    BodyPart Shape17;
    BodyPart Shape18;
    BodyPart Shape19;

    public ShieldWood()
    {
        super(BaseItemModel.ModelType.SHIELD);
        super.textureWidth = 32;
        super.textureHeight = 32;
        (this.Shape1 = new BodyPart(this, 22, 28)).addBox(2.5F, 6.0F, -1.0F, 3, 2, 2);
        this.Shape1.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.Shape1.setTextureSize(32, 32);
        this.Shape1.mirror = true;
        this.setRotation(this.Shape1, 0.0F, 0.0F, 0.0F);
        (this.Shape2 = new BodyPart(this, 0, 0)).addBox(3.5F, 0.0F, -2.0F, 1, 14, 4);
        this.Shape2.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.Shape2.setTextureSize(32, 32);
        this.Shape2.mirror = true;
        this.setRotation(this.Shape2, 0.0F, 0.0F, 0.0F);
        (this.Shape3 = new BodyPart(this, 0, 3)).addBox(3.5F, 1.0F, 2.0F, 1, 12, 2);
        this.Shape3.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.Shape3.setTextureSize(32, 32);
        this.Shape3.mirror = true;
        this.setRotation(this.Shape3, 0.0F, 0.0F, 0.0F);
        (this.Shape4 = new BodyPart(this, 0, 3)).addBox(3.5F, 1.0F, -4.0F, 1, 12, 2);
        this.Shape4.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.Shape4.setTextureSize(32, 32);
        this.Shape4.mirror = true;
        this.setRotation(this.Shape4, 0.0F, 0.0F, 0.0F);
        (this.Shape5 = new BodyPart(this, 0, 1)).addBox(3.5F, 2.0F, 4.0F, 1, 10, 1);
        this.Shape5.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.Shape5.setTextureSize(32, 32);
        this.Shape5.mirror = true;
        this.setRotation(this.Shape5, 0.0F, 0.0F, 0.0F);
        (this.Shape6 = new BodyPart(this, 0, 1)).addBox(3.5F, 2.0F, -5.0F, 1, 10, 1);
        this.Shape6.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.Shape6.setTextureSize(32, 32);
        this.Shape6.mirror = true;
        this.setRotation(this.Shape6, 0.0F, 0.0F, 0.0F);
        (this.Shape7 = new BodyPart(this, 0, 2)).addBox(3.5F, 3.0F, 5.0F, 1, 8, 1);
        this.Shape7.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.Shape7.setTextureSize(32, 32);
        this.Shape7.mirror = true;
        this.setRotation(this.Shape7, 0.0F, 0.0F, 0.0F);
        (this.Shape8 = new BodyPart(this, 0, 0)).addBox(3.5F, 5.0F, 6.0F, 1, 4, 1);
        this.Shape8.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.Shape8.setTextureSize(32, 32);
        this.Shape8.mirror = true;
        this.setRotation(this.Shape8, 0.0F, 0.0F, 0.0F);
        (this.Shape9 = new BodyPart(this, 0, 2)).addBox(3.5F, 3.0F, -6.0F, 1, 8, 1);
        this.Shape9.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.Shape9.setTextureSize(32, 32);
        this.Shape9.mirror = true;
        this.setRotation(this.Shape9, 0.0F, 0.0F, 0.0F);
        (this.Shape10 = new BodyPart(this, 0, 0)).addBox(3.5F, 5.0F, -7.0F, 1, 4, 1);
        this.Shape10.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.Shape10.setTextureSize(32, 32);
        this.Shape10.mirror = true;
        this.setRotation(this.Shape10, 0.0F, 0.0F, 0.0F);
        (this.Shape11 = new BodyPart(this, 14, 20)).addBox(4.0F, 4.0F, -3.0F, 1, 6, 6);
        this.Shape11.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.Shape11.setTextureSize(32, 32);
        this.Shape11.mirror = true;
        this.setRotation(this.Shape11, 0.0F, 0.0F, 0.0F);
        (this.Shape12 = new BodyPart(this, 26, 29)).addBox(4.0F, 3.0F, -1.0F, 1, 1, 2);
        this.Shape12.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.Shape12.setTextureSize(32, 32);
        this.Shape12.mirror = true;
        this.setRotation(this.Shape12, 0.0F, 0.0F, 0.0F);
        (this.Shape13 = new BodyPart(this, 26, 29)).addBox(4.0F, 10.0F, -1.0F, 1, 1, 2);
        this.Shape13.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.Shape13.setTextureSize(32, 32);
        this.Shape13.mirror = true;
        this.setRotation(this.Shape13, 0.0F, 0.0F, 0.0F);
        (this.Shape14 = new BodyPart(this, 28, 29)).addBox(4.0F, 6.0F, -4.0F, 1, 2, 1);
        this.Shape14.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.Shape14.setTextureSize(32, 32);
        this.Shape14.mirror = true;
        this.setRotation(this.Shape14, 0.0F, 0.0F, 0.0F);
        (this.Shape15 = new BodyPart(this, 28, 29)).addBox(4.0F, 6.0F, 3.0F, 1, 2, 1);
        this.Shape15.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.Shape15.setTextureSize(32, 32);
        this.Shape15.mirror = true;
        this.setRotation(this.Shape15, 0.0F, 0.0F, 0.0F);
        (this.Shape16 = new BodyPart(this, 0, 1)).addBox(4.0F, 1.0F, -0.5F, 1, 6, 1);
        this.Shape16.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.Shape16.setTextureSize(32, 32);
        this.Shape16.mirror = true;
        this.setRotation(this.Shape16, 0.0F, 0.0F, -0.122173F);
        (this.Shape17 = new BodyPart(this, 0, 0)).addBox(5.7F, 7.0F, -0.5F, 1, 6, 1);
        this.Shape17.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.Shape17.setTextureSize(32, 32);
        this.Shape17.mirror = true;
        this.setRotation(this.Shape17, 0.0F, 0.0F, 0.122173F);
        (this.Shape18 = new BodyPart(this, 0, 0)).addBox(4.8F, 6.5F, 0.0F, 1, 1, 6);
        this.Shape18.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.Shape18.setTextureSize(32, 32);
        this.Shape18.mirror = true;
        this.setRotation(this.Shape18, 0.0F, -0.122173F, 0.0F);
        (this.Shape19 = new BodyPart(this, 0, 0)).addBox(4.8F, 6.5F, -6.0F, 1, 1, 6);
        this.Shape19.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.Shape19.setTextureSize(32, 32);
        this.Shape19.mirror = true;
        this.setRotation(this.Shape19, 0.0F, 0.122173F, 0.0F);
        this.setTexture("/mods/LavaChestPlate/textures/items/shields/wood.png");
    }

    public String getName()
    {
        return "Деревянный Щит";
    }
}
