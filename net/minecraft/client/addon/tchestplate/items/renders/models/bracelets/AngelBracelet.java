package net.minecraft.client.addon.tchestplate.items.renders.models.bracelets;

import net.minecraft.client.addon.tchestplate.items.renders.models.BaseItemModel;
import net.minecraft.client.addon.tchestplate.items.renders.models.BodyPart;
import net.minecraft.client.addon.tchestplate.items.renders.models.RightHand;
import net.minecraft.client.model.ModelRenderer;

public class AngelBracelet extends RightHand
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
    BodyPart Shape13;
    BodyPart Shape14;
    BodyPart Shape15;
    BodyPart Shape16;
    BodyPart Shape17;
    BodyPart Shape18;
    BodyPart Shape19;
    BodyPart Shape20;
    BodyPart Shape21;
    BodyPart Shape22;
    BodyPart Shape23;
    BodyPart Shape24;
    ModelRenderer Shape25;
    ModelRenderer Shape26;
    ModelRenderer Shape27;
    ModelRenderer Shape28;
    ModelRenderer Shape29;
    ModelRenderer Shape30;
    ModelRenderer Shape31;
    ModelRenderer Shape32;

    public AngelBracelet(String s, int n)
    {
        super(BaseItemModel.ModelType.BRACELETS);
        super.textureWidth = 32;
        super.textureHeight = 32;
        (this.Shape1 = new BodyPart(this, 10, 10)).addBox(-4.1F, 7.2F, -1.5F, 1, 1, 3);
        this.Shape1.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.Shape1.setTextureSize(32, 32);
        this.Shape1.mirror = true;
        this.setRotation(this.Shape1, 0.0F, 0.0F, 0.0F);
        (this.Shape2 = new BodyPart(this, 9, 12)).addBox(-2.5F, 7.2F, -3.1F, 3, 1, 1);
        this.Shape2.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.Shape2.setTextureSize(32, 32);
        this.Shape2.mirror = true;
        this.setRotation(this.Shape2, 0.0F, 0.0F, 0.0F);
        (this.Shape3 = new BodyPart(this, 9, 12)).addBox(-3.7F, 7.2F, -2.2F, 1, 1, 1);
        this.Shape3.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.Shape3.setTextureSize(32, 32);
        this.Shape3.mirror = true;
        this.setRotation(this.Shape3, 0.0F, 0.0F, 0.0F);
        (this.Shape4 = new BodyPart(this, 9, 12)).addBox(-2.5F, 7.2F, 2.1F, 3, 1, 1);
        this.Shape4.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.Shape4.setTextureSize(32, 32);
        this.Shape4.mirror = true;
        this.setRotation(this.Shape4, 0.0F, 0.0F, 0.0F);
        (this.Shape5 = new BodyPart(this, 9, 12)).addBox(-3.2F, 7.2F, 1.7F, 1, 1, 1);
        this.Shape5.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.Shape5.setTextureSize(32, 32);
        this.Shape5.mirror = true;
        this.setRotation(this.Shape5, 0.0F, 0.0F, 0.0F);
        (this.Shape6 = new BodyPart(this, 9, 10)).addBox(1.1F, 7.2F, -1.5F, 1, 1, 3);
        this.Shape6.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.Shape6.setTextureSize(32, 32);
        this.Shape6.mirror = true;
        this.setRotation(this.Shape6, 0.0F, 0.0F, 0.0F);
        (this.Shape7 = new BodyPart(this, 9, 12)).addBox(0.2F, 7.2F, -2.7F, 1, 1, 1);
        this.Shape7.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.Shape7.setTextureSize(32, 32);
        this.Shape7.mirror = true;
        this.setRotation(this.Shape7, 0.0F, 0.0F, 0.0F);
        (this.Shape8 = new BodyPart(this, 9, 12)).addBox(0.2F, 7.2F, 1.7F, 1, 1, 1);
        this.Shape8.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.Shape8.setTextureSize(32, 32);
        this.Shape8.mirror = true;
        this.setRotation(this.Shape8, 0.0F, 0.0F, 0.0F);
        (this.Shape9 = new BodyPart(this, 9, 12)).addBox(-3.2F, 7.2F, -2.7F, 1, 1, 1);
        this.Shape9.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.Shape9.setTextureSize(32, 32);
        this.Shape9.mirror = true;
        this.setRotation(this.Shape9, 0.0F, 0.0F, 0.0F);
        (this.Shape10 = new BodyPart(this, 9, 12)).addBox(0.7F, 7.2F, -2.2F, 1, 1, 1);
        this.Shape10.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.Shape10.setTextureSize(32, 32);
        this.Shape10.mirror = true;
        this.setRotation(this.Shape10, 0.0F, 0.0F, 0.0F);
        (this.Shape11 = new BodyPart(this, 9, 12)).addBox(-3.7F, 7.2F, 1.2F, 1, 1, 1);
        this.Shape11.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.Shape11.setTextureSize(32, 32);
        this.Shape11.mirror = true;
        this.setRotation(this.Shape11, 0.0F, 0.0F, 0.0F);
        (this.Shape12 = new BodyPart(this, 9, 12)).addBox(0.7F, 7.2F, 1.2F, 1, 1, 1);
        this.Shape12.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.Shape12.setTextureSize(32, 32);
        this.Shape12.mirror = true;
        this.setRotation(this.Shape12, 0.0F, 0.0F, 0.0F);
        (this.Shape13 = new BodyPart(this, 10, 10)).addBox(-4.1F, 5.7F, -1.5F, 1, 1, 3);
        this.Shape13.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.Shape13.setTextureSize(32, 32);
        this.Shape13.mirror = true;
        this.setRotation(this.Shape13, 0.0F, 0.0F, 0.0F);
        (this.Shape14 = new BodyPart(this, 9, 12)).addBox(-2.5F, 5.7F, -3.1F, 3, 1, 1);
        this.Shape14.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.Shape14.setTextureSize(32, 32);
        this.Shape14.mirror = true;
        this.setRotation(this.Shape14, 0.0F, 0.0F, 0.0F);
        (this.Shape15 = new BodyPart(this, 9, 12)).addBox(-3.7F, 5.7F, -2.2F, 1, 1, 1);
        this.Shape15.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.Shape15.setTextureSize(32, 32);
        this.Shape15.mirror = true;
        this.setRotation(this.Shape15, 0.0F, 0.0F, 0.0F);
        (this.Shape16 = new BodyPart(this, 9, 12)).addBox(-2.5F, 5.7F, 2.1F, 3, 1, 1);
        this.Shape16.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.Shape16.setTextureSize(32, 32);
        this.Shape16.mirror = true;
        this.setRotation(this.Shape16, 0.0F, 0.0F, 0.0F);
        (this.Shape17 = new BodyPart(this, 9, 12)).addBox(-3.2F, 5.7F, 1.7F, 1, 1, 1);
        this.Shape17.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.Shape17.setTextureSize(32, 32);
        this.Shape17.mirror = true;
        this.setRotation(this.Shape17, 0.0F, 0.0F, 0.0F);
        (this.Shape18 = new BodyPart(this, 9, 10)).addBox(1.1F, 5.7F, -1.5F, 1, 1, 3);
        this.Shape18.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.Shape18.setTextureSize(32, 32);
        this.Shape18.mirror = true;
        this.setRotation(this.Shape18, 0.0F, 0.0F, 0.0F);
        (this.Shape19 = new BodyPart(this, 9, 12)).addBox(0.2F, 5.7F, -2.7F, 1, 1, 1);
        this.Shape19.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.Shape19.setTextureSize(32, 32);
        this.Shape19.mirror = true;
        this.setRotation(this.Shape19, 0.0F, 0.0F, 0.0F);
        (this.Shape20 = new BodyPart(this, 9, 12)).addBox(0.2F, 5.7F, 1.7F, 1, 1, 1);
        this.Shape20.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.Shape20.setTextureSize(32, 32);
        this.Shape20.mirror = true;
        this.setRotation(this.Shape20, 0.0F, 0.0F, 0.0F);
        (this.Shape21 = new BodyPart(this, 9, 12)).addBox(-3.2F, 5.7F, -2.7F, 1, 1, 1);
        this.Shape21.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.Shape21.setTextureSize(32, 32);
        this.Shape21.mirror = true;
        this.setRotation(this.Shape21, 0.0F, 0.0F, 0.0F);
        (this.Shape22 = new BodyPart(this, 9, 12)).addBox(0.7F, 5.7F, -2.2F, 1, 1, 1);
        this.Shape22.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.Shape22.setTextureSize(32, 32);
        this.Shape22.mirror = true;
        this.setRotation(this.Shape22, 0.0F, 0.0F, 0.0F);
        (this.Shape23 = new BodyPart(this, 9, 12)).addBox(-3.7F, 5.7F, 1.2F, 1, 1, 1);
        this.Shape23.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.Shape23.setTextureSize(32, 32);
        this.Shape23.mirror = true;
        this.setRotation(this.Shape23, 0.0F, 0.0F, 0.0F);
        (this.Shape24 = new BodyPart(this, 9, 12)).addBox(0.7F, 5.7F, 1.2F, 1, 1, 1);
        this.Shape24.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.Shape24.setTextureSize(32, 32);
        this.Shape24.mirror = true;
        this.setRotation(this.Shape24, 0.0F, 0.0F, 0.0F);
        (this.Shape25 = new ModelRenderer(this, 10, 14)).addBox(3.7F, 5.1F, -3.5F, 1, 1, 1);
        this.Shape25.setTextureSize(32, 32);
        this.Shape25.mirror = true;
        this.setRotation(this.Shape25, 0.0F, 0.0F, ((float)Math.PI / 4F));
        (this.Shape26 = new ModelRenderer(this, 12, 14)).addBox(3.7F, 5.1F, 2.5F, 1, 1, 1);
        this.Shape26.setTextureSize(32, 32);
        this.Shape26.mirror = true;
        this.setRotation(this.Shape26, 0.0F, 0.0F, ((float)Math.PI / 4F));
        (this.Shape27 = new ModelRenderer(this, 11, 14)).addBox(-4.6F, 4.5F, -5.3F, 1, 1, 1);
        this.Shape27.setTextureSize(32, 32);
        this.Shape27.mirror = true;
        this.setRotation(this.Shape27, ((float)Math.PI / 4F), 0.0F, 0.0F);
        (this.Shape28 = new ModelRenderer(this, 13, 14)).addBox(1.5F, 4.5F, -5.3F, 1, 1, 1);
        this.Shape28.setTextureSize(32, 32);
        this.Shape28.mirror = true;
        this.setRotation(this.Shape28, ((float)Math.PI / 4F), 0.0F, 0.0F);
        (this.Shape29 = new ModelRenderer(this, 10, 14)).addBox(0.9F, 6.4F, 1.0F, 1, 1, 1);
        this.Shape29.setTextureSize(32, 32);
        this.Shape29.mirror = true;
        this.setRotation(this.Shape29, 0.0F, ((float)Math.PI / 4F), 0.0F);
        (this.Shape30 = new ModelRenderer(this, 10, 14)).addBox(-3.5F, 6.4F, -3.3F, 1, 1, 1);
        this.Shape30.setTextureSize(32, 32);
        this.Shape30.mirror = true;
        this.setRotation(this.Shape30, 0.0F, ((float)Math.PI / 4F), 0.0F);
        (this.Shape31 = new ModelRenderer(this, 10, 14)).addBox(-3.4F, 6.4F, 1.0F, 1, 1, 1);
        this.Shape31.setTextureSize(32, 32);
        this.Shape31.mirror = true;
        this.setRotation(this.Shape31, 0.0F, ((float)Math.PI / 4F), 0.0F);
        (this.Shape32 = new ModelRenderer(this, 9, 14)).addBox(1.0F, 6.4F, -3.4F, 1, 1, 1);
        this.Shape32.setTextureSize(32, 32);
        this.Shape32.mirror = true;
        this.setRotation(this.Shape32, 0.0F, ((float)Math.PI / 4F), 0.0F);
        this.Shape24.addChild(this.Shape25);
        this.Shape24.addChild(this.Shape26);
        this.Shape24.addChild(this.Shape27);
        this.Shape24.addChild(this.Shape28);
        this.Shape24.addChild(this.Shape29);
        this.Shape24.addChild(this.Shape30);
        this.Shape24.addChild(this.Shape31);
        this.Shape24.addChild(this.Shape32);
        this.setTexture("/mods/LavaChestPlate/textures/items/bracelets/angel_b.png");
    }

    public String getName()
    {
        return "Лава демон браслет";
    }
}
