package net.minecraft.client.addon.tchestplate.items.renders.models.amulets;

import net.minecraft.client.addon.tchestplate.items.renders.models.BaseItemModel;
import net.minecraft.client.addon.tchestplate.items.renders.models.BodyPart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.src.ModelPlayer;

public class LavaaAmulet extends BaseItemModel
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
    private static String tex;

    public LavaaAmulet(String var1)
    {
        super(BaseItemModel.ModelType.BODY);
        tex = "/mods/LavaChestPlate/textures/items/amulet/army.png";
        super.textureWidth = 16;
        super.textureHeight = 16;
        this.Shape1 = new BodyPart(this, 0, 10);
        this.Shape1.addBox(2.8F, 0.0F, -3.1F, 2, 2, 1);
        this.Shape1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape1.setTextureSize(16, 16);
        this.Shape1.mirror = true;
        this.setRotation(this.Shape1, 0.0F, 0.0F, ((float)Math.PI / 4F));
        this.Shape2 = new BodyPart(this, 4, 0);
        this.Shape2.addBox(1.5F, 5.3F, -3.2F, 1, 1, 1);
        this.Shape2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape2.setTextureSize(16, 16);
        this.Shape2.mirror = true;
        this.setRotation(this.Shape2, 0.0F, 0.0F, 0.0F);
        this.Shape3 = new BodyPart(this, 0, 0);
        this.Shape3.addBox(4.5F, 1.7F, -3.0F, 1, 1, 1);
        this.Shape3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape3.setTextureSize(16, 16);
        this.Shape3.mirror = true;
        this.setRotation(this.Shape3, 0.0F, 0.0F, ((float)Math.PI / 4F));
        this.Shape4 = new BodyPart(this, 0, 0);
        this.Shape4.addBox(4.3F, 2.7F, -3.0F, 1, 1, 1);
        this.Shape4.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape4.setTextureSize(16, 16);
        this.Shape4.mirror = true;
        this.setRotation(this.Shape4, 0.0F, 0.0F, ((float)Math.PI / 4F));
        this.Shape5 = new BodyPart(this, 0, 0);
        this.Shape5.addBox(5.5F, 1.5F, -3.0F, 1, 1, 1);
        this.Shape5.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape5.setTextureSize(16, 16);
        this.Shape5.mirror = true;
        this.setRotation(this.Shape5, 0.0F, 0.0F, ((float)Math.PI / 4F));
        this.Shape6 = new BodyPart(this, 0, 0);
        this.Shape6.addBox(5.2F, 3.1F, -3.0F, 1, 1, 1);
        this.Shape6.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape6.setTextureSize(16, 16);
        this.Shape6.mirror = true;
        this.setRotation(this.Shape6, 0.0F, 0.0F, ((float)Math.PI / 4F));
        this.Shape7 = new BodyPart(this, 0, 0);
        this.Shape7.addBox(5.9F, 2.3F, -3.0F, 1, 1, 1);
        this.Shape7.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape7.setTextureSize(16, 16);
        this.Shape7.mirror = true;
        this.setRotation(this.Shape7, 0.0F, 0.0F, ((float)Math.PI / 4F));
        this.Shape8 = new BodyPart(this, 0, 7);
        this.Shape8.addBox(1.7F, 1.0F, -2.9F, 1, 2, 1);
        this.Shape8.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape8.setTextureSize(16, 16);
        this.Shape8.mirror = true;
        this.setRotation(this.Shape8, 0.0F, 0.0F, 0.3490659F);
        this.Shape9 = new BodyPart(this, 0, 7);
        this.Shape9.addBox(0.8F, 2.3F, -2.9F, 1, 2, 1);
        this.Shape9.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape9.setTextureSize(16, 16);
        this.Shape9.mirror = true;
        this.setRotation(this.Shape9, 0.0F, 0.0F, -0.4014257F);
        this.Shape10 = new BodyPart(this, 8, 0);
        this.Shape10.addBox(1.5F, 3.9F, -2.5F, 1, 1, 1);
        this.Shape10.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape10.setTextureSize(16, 16);
        this.Shape10.mirror = true;
        this.setRotation(this.Shape10, 0.0F, 0.0F, 0.0F);
        this.Shape11 = new BodyPart(this, 0, 4);
        this.Shape11.addBox(0.4F, 2.4F, -3.2F, 1, 2, 1);
        this.Shape11.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape11.setTextureSize(16, 16);
        this.Shape11.mirror = true;
        this.setRotation(this.Shape11, 0.0F, 0.0F, -0.4014257F);
        this.Shape12 = new BodyPart(this, 0, 4);
        this.Shape12.addBox(2.0F, 1.1F, -3.2F, 1, 2, 1);
        this.Shape12.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape12.setTextureSize(16, 16);
        this.Shape12.mirror = true;
        this.setRotation(this.Shape12, 0.0F, 0.0F, 0.3490659F);
        this.Shape13 = new BodyPart(this, 0, 4);
        this.Shape13.addBox(3.5F, 0.7F, -3.4F, 1, 1, 1);
        this.Shape13.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape13.setTextureSize(16, 16);
        this.Shape13.mirror = true;
        this.setRotation(this.Shape13, 0.0F, 0.0F, ((float)Math.PI / 4F));
        this.Shape14 = new BodyPart(this, 0, 8);
        this.Shape14.addBox(3.1F, 0.3F, -3.3F, 1, 1, 1);
        this.Shape14.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape14.setTextureSize(16, 16);
        this.Shape14.mirror = true;
        this.setRotation(this.Shape14, 0.0F, 0.0F, ((float)Math.PI / 4F));
        this.Shape15 = new BodyPart(this, 4, 0);
        this.Shape15.addBox(5.0F, 2.2F, -3.2F, 1, 1, 1);
        this.Shape15.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape15.setTextureSize(16, 16);
        this.Shape15.mirror = true;
        this.setRotation(this.Shape15, 0.0F, 0.0F, ((float)Math.PI / 4F));
        this.setTexture(tex);
    }

    protected void setRotation(BodyPart var1, float var2, float var3, float var4)
    {
        var1.origRotationX = var2;
        var1.origRotationY = var3;
        var1.origRotationZ = var4;
    }

    protected void processMotion(ModelPlayer var1, EntityPlayer var2, float var3)
    {
    }

    public String getName()
    {
        return "ава амулет";
    }
}
