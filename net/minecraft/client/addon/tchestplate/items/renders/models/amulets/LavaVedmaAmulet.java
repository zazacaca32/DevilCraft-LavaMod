package net.minecraft.client.addon.tchestplate.items.renders.models.amulets;

import net.minecraft.client.addon.tchestplate.items.renders.models.BaseItemModel;
import net.minecraft.client.addon.tchestplate.items.renders.models.BodyPart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.src.ModelPlayer;

public class LavaVedmaAmulet extends BaseItemModel
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
    private static String tex;

    public LavaVedmaAmulet(String var1)
    {
        super(BaseItemModel.ModelType.BODY);
        tex = "/mods/LavaChestPlate/textures/items/amulet/vedma_a.png";
        super.textureWidth = 16;
        super.textureHeight = 16;
        this.Shape1 = new BodyPart(this, 1, 9);
        this.Shape1.addBox(-1.3F, 2.0F, -3.5F, 1, 4, 1);
        this.Shape1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape1.setTextureSize(16, 16);
        this.Shape1.mirror = true;
        this.setRotation(this.Shape1, 0.0F, 0.0F, -((float)Math.PI / 10F));
        this.Shape2 = new BodyPart(this, 1, 9);
        this.Shape2.addBox(0.3F, 2.0F, -3.5F, 1, 4, 1);
        this.Shape2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape2.setTextureSize(16, 16);
        this.Shape2.mirror = true;
        this.setRotation(this.Shape2, 0.0F, 0.0F, ((float)Math.PI / 10F));
        this.Shape3 = new BodyPart(this, 1, 9);
        this.Shape3.addBox(3.0F, 0.7F, -3.5F, 1, 4, 1);
        this.Shape3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape3.setTextureSize(16, 16);
        this.Shape3.mirror = true;
        this.setRotation(this.Shape3, 0.0F, 0.0F, 0.8901179F);
        this.Shape4 = new BodyPart(this, 1, 9);
        this.Shape4.addBox(-4.0F, 0.7F, -3.5F, 1, 4, 1);
        this.Shape4.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape4.setTextureSize(16, 16);
        this.Shape4.mirror = true;
        this.setRotation(this.Shape4, 0.0F, 0.0F, -0.8726646F);
        this.Shape5 = new BodyPart(this, 0, 6);
        this.Shape5.addBox(-0.5F, 0.2F, -5.9F, 1, 1, 1);
        this.Shape5.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape5.setTextureSize(16, 16);
        this.Shape5.mirror = true;
        this.setRotation(this.Shape5, ((float)Math.PI / 4F), 0.0F, 0.0F);
        this.Shape6 = new BodyPart(this, 1, 9);
        this.Shape6.addBox(3.0F, -2.0F, -3.5F, 1, 4, 1);
        this.Shape6.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape6.setTextureSize(16, 16);
        this.Shape6.mirror = true;
        this.setRotation(this.Shape6, 0.0F, 0.0F, ((float)Math.PI / 2F));
        this.Shape7 = new BodyPart(this, 0, 6);
        this.Shape7.addBox(2.5F, 2.5F, -3.7F, 1, 1, 1);
        this.Shape7.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape7.setTextureSize(16, 16);
        this.Shape7.mirror = true;
        this.setRotation(this.Shape7, 0.0F, 0.0F, ((float)Math.PI / 4F));
        this.Shape8 = new BodyPart(this, 0, 6);
        this.Shape8.addBox(1.9F, 3.8F, -2.9F, 1, 1, 1);
        this.Shape8.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape8.setTextureSize(16, 16);
        this.Shape8.mirror = true;
        this.setRotation(this.Shape8, 0.0F, ((float)Math.PI / 4F), 0.0F);
        this.Shape9 = new BodyPart(this, 0, 0);
        this.Shape9.addBox(2.5F, -1.0F, -2.0F, 1, 4, 1);
        this.Shape9.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape9.setTextureSize(16, 16);
        this.Shape9.mirror = true;
        this.setRotation(this.Shape9, -0.4014257F, 0.0F, ((float)Math.PI / 5F));
        this.Shape10 = new BodyPart(this, 0, 0);
        this.Shape10.addBox(-3.5F, -0.9F, -2.0F, 1, 4, 1);
        this.Shape10.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape10.setTextureSize(16, 16);
        this.Shape10.mirror = true;
        this.setRotation(this.Shape10, -0.418879F, 0.0F, -0.6108652F);
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
        return "Vedma амулет";
    }
}
