package net.minecraft.client.addon.tchestplate.items.renders.models.amulets;

import net.minecraft.client.addon.tchestplate.items.renders.models.BaseItemModel;
import net.minecraft.client.addon.tchestplate.items.renders.models.BodyPart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.src.ModelPlayer;

public class EnderAmulet extends BaseItemModel
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

    public EnderAmulet(String paramString)
    {
        super(BaseItemModel.ModelType.BODY);
        tex = "/mods/underqoder/amulets/ender_amulet.png";
        super.textureWidth = 16;
        super.textureHeight = 16;
        this.Shape1 = new BodyPart(this, 7, 7);
        this.Shape1.addBox(-1.0F, 6.5F, -4.3F, 2, 1, 2);
        this.Shape1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape1.setTextureSize(16, 16);
        this.Shape1.mirror = true;
        this.setRotation(this.Shape1, 0.0F, 0.0F, 0.0F);
        this.Shape2 = new BodyPart(this, 12, 0);
        this.Shape2.addBox(2.0F, 1.3F, -5.0F, 1, 2, 1);
        this.Shape2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape2.setTextureSize(16, 16);
        this.Shape2.mirror = true;
        this.setRotation(this.Shape2, 0.296706F, 0.0F, 0.017453F);
        this.Shape3 = new BodyPart(this, 12, 0);
        this.Shape3.addBox(-3.0F, 1.3F, -5.0F, 1, 2, 1);
        this.Shape3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape3.setTextureSize(16, 16);
        this.Shape3.mirror = true;
        this.setRotation(this.Shape3, 0.296706F, 0.0F, -0.017453F);
        this.Shape4 = new BodyPart(this, 10, 2);
        this.Shape4.addBox(-0.5F, 4.0F, -4.0F, 2, 1, 1);
        this.Shape4.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape4.setTextureSize(16, 16);
        this.Shape4.mirror = true;
        this.setRotation(this.Shape4, 0.0F, 0.0F, 0.5235988F);
        this.Shape5 = new BodyPart(this, 10, 2);
        this.Shape5.addBox(-1.5F, 4.0F, -4.0F, 2, 1, 1);
        this.Shape5.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape5.setTextureSize(16, 16);
        this.Shape5.mirror = true;
        this.setRotation(this.Shape5, 0.0F, 0.0F, -0.5235988F);
        this.Shape6 = new BodyPart(this, 6, 10);
        this.Shape6.addBox(-1.5F, 4.0F, -4.1F, 3, 2, 2);
        this.Shape6.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape6.setTextureSize(16, 16);
        this.Shape6.mirror = true;
        this.setRotation(this.Shape6, 0.0F, 0.0F, 0.0F);
        this.Shape7 = new BodyPart(this, 10, 8);
        this.Shape7.addBox(-1.3F, 5.0F, -3.2F, 2, 2, 1);
        this.Shape7.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape7.setTextureSize(16, 16);
        this.Shape7.mirror = true;
        this.setRotation(this.Shape7, -0.0523599F, 0.0F, 0.0F);
        this.Shape8 = new BodyPart(this, 10, 8);
        this.Shape8.addBox(0.3F, 5.0F, -3.2F, 1, 2, 1);
        this.Shape8.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape8.setTextureSize(16, 16);
        this.Shape8.mirror = true;
        this.setRotation(this.Shape8, -0.0523599F, 0.0F, 0.0F);
        this.Shape9 = new BodyPart(this, 12, 5);
        this.Shape9.addBox(0.2F, 5.7F, 0.5F, 1, 1, 1);
        this.Shape9.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape9.setTextureSize(16, 16);
        this.Shape9.mirror = true;
        this.setRotation(this.Shape9, -((float)Math.PI / 4F), 0.0F, 0.0F);
        this.Shape10 = new BodyPart(this, 12, 5);
        this.Shape10.addBox(-1.2F, 5.7F, 0.5F, 1, 1, 1);
        this.Shape10.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape10.setTextureSize(16, 16);
        this.Shape10.mirror = true;
        this.setRotation(this.Shape10, -((float)Math.PI / 4F), 0.0F, 0.0F);
        this.Shape11 = new BodyPart(this, 8, 5);
        this.Shape11.addBox(4.1F, 4.5F, -4.2F, 1, 1, 1);
        this.Shape11.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape11.setTextureSize(16, 16);
        this.Shape11.mirror = true;
        this.setRotation(this.Shape11, 0.0F, 0.0F, 0.837758F);
        this.Shape12 = new BodyPart(this, 8, 5);
        this.Shape12.addBox(4.5F, 4.1F, -4.2F, 1, 1, 1);
        this.Shape12.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape12.setTextureSize(16, 16);
        this.Shape12.mirror = true;
        this.setRotation(this.Shape12, 0.0F, 0.0F, 0.7504916F);
        this.Shape13 = new BodyPart(this, 0, 9);
        this.Shape13.addBox(-4.4F, -1.4F, -1.8F, 1, 6, 1);
        this.Shape13.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape13.setTextureSize(16, 16);
        this.Shape13.mirror = true;
        this.setRotation(this.Shape13, -0.418879F, 0.0F, -0.6108652F);
        this.Shape14 = new BodyPart(this, 0, 9);
        this.Shape14.addBox(3.4F, -1.5F, -1.8F, 1, 6, 1);
        this.Shape14.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape14.setTextureSize(16, 16);
        this.Shape14.mirror = true;
        this.setRotation(this.Shape14, -0.4014257F, 0.0F, ((float)Math.PI / 5F));
        this.Shape15 = new BodyPart(this, 8, 7);
        this.Shape15.addBox(-1.0F, 3.8F, -4.2F, 2, 2, 2);
        this.Shape15.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape15.setTextureSize(16, 16);
        this.Shape15.mirror = true;
        this.setRotation(this.Shape15, 0.0F, 0.0F, 0.0F);
        this.setTexture(tex);
    }

    protected void setRotation(BodyPart paramBodyPart, float paramFloat1, float paramFloat2, float paramFloat3)
    {
        paramBodyPart.origRotationX = paramFloat1;
        paramBodyPart.origRotationY = paramFloat2;
        paramBodyPart.origRotationZ = paramFloat3;
    }

    public String getName()
    {
        return "демон амулет";
    }

    protected void processMotion(ModelPlayer paramModelPlayer, EntityPlayer paramEntityPlayer, float paramFloat)
    {
    }
}
