package net.minecraft.client.addon.tchestplate.items.renders.models.amulets;

import net.minecraft.client.addon.tchestplate.items.renders.models.BaseItemModel;
import net.minecraft.client.addon.tchestplate.items.renders.models.BodyPart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.src.ModelPlayer;

public class HunterAmulet extends BaseItemModel
{
    BodyPart Shape13;
    BodyPart Shape14;
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
    private static String texS;
    boolean flagInvise = false;

    public HunterAmulet(String texS)
    {
        super(BaseItemModel.ModelType.BODY);
        texS = "/mods/provider/ual/hunter_amulet.png";
        super.textureWidth = 16;
        super.textureHeight = 16;
        this.Shape13 = new BodyPart(this, 12, 9);
        this.Shape13.addBox(-4.4F, -1.4F, -1.8F, 1, 6, 1);
        this.Shape13.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape13.setTextureSize(16, 16);
        this.Shape13.mirror = true;
        this.setRotation(this.Shape13, -0.418879F, 0.0F, -0.6108652F);
        this.Shape14 = new BodyPart(this, 12, 9);
        this.Shape14.addBox(3.4F, -1.5F, -1.8F, 1, 6, 1);
        this.Shape14.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape14.setTextureSize(16, 16);
        this.Shape14.mirror = true;
        this.setRotation(this.Shape14, -0.4014257F, 0.0F, ((float)Math.PI / 5F));
        this.Shape1 = new BodyPart(this, 0, 12);
        this.Shape1.addBox(-1.0F, 5.2F, -4.0F, 2, 2, 2);
        this.Shape1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape1.setTextureSize(16, 16);
        this.Shape1.mirror = true;
        this.setRotation(this.Shape1, 0.0F, 0.0F, 0.0F);
        this.Shape2 = new BodyPart(this, 0, 12);
        this.Shape2.addBox(-1.0F, 5.0F, -3.9F, 1, 1, 2);
        this.Shape2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape2.setTextureSize(16, 16);
        this.Shape2.mirror = true;
        this.setRotation(this.Shape2, 0.0F, 0.0F, ((float)Math.PI * 2F / 9F));
        this.Shape3 = new BodyPart(this, 0, 12);
        this.Shape3.addBox(0.0F, 5.0F, -3.9F, 1, 1, 2);
        this.Shape3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape3.setTextureSize(16, 16);
        this.Shape3.mirror = true;
        this.setRotation(this.Shape3, 0.0F, 0.0F, -((float)Math.PI * 2F / 9F));
        this.Shape4 = new BodyPart(this, 1, 12);
        this.Shape4.addBox(-1.5F, 3.3F, -4.0F, 2, 2, 2);
        this.Shape4.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape4.setTextureSize(16, 16);
        this.Shape4.mirror = true;
        this.setRotation(this.Shape4, 0.0F, 0.0F, ((float)Math.PI * 2F / 9F));
        this.Shape5 = new BodyPart(this, 1, 12);
        this.Shape5.addBox(-0.5F, 3.3F, -4.0F, 2, 2, 2);
        this.Shape5.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape5.setTextureSize(16, 16);
        this.Shape5.mirror = true;
        this.setRotation(this.Shape5, 0.0F, 0.0F, -((float)Math.PI * 2F / 9F));
        this.Shape6 = new BodyPart(this, 0, 12);
        this.Shape6.addBox(-1.5F, 4.0F, -4.1F, 3, 2, 2);
        this.Shape6.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape6.setTextureSize(16, 16);
        this.Shape6.mirror = true;
        this.setRotation(this.Shape6, 0.0F, 0.0F, 0.0F);
        this.Shape7 = new BodyPart(this, 0, 9);
        this.Shape7.addBox(-1.3F, 6.0F, 0.0F, 1, 1, 2);
        this.Shape7.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape7.setTextureSize(16, 16);
        this.Shape7.mirror = true;
        this.setRotation(this.Shape7, -0.6806784F, 0.0F, 0.0F);
        this.Shape8 = new BodyPart(this, 0, 9);
        this.Shape8.addBox(0.3F, 6.0F, 0.0F, 1, 1, 2);
        this.Shape8.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape8.setTextureSize(16, 16);
        this.Shape8.mirror = true;
        this.setRotation(this.Shape8, -0.6806784F, 0.0F, 0.0F);
        this.Shape9 = new BodyPart(this, 0, 9);
        this.Shape9.addBox(0.4F, 5.2F, -1.0F, 1, 1, 2);
        this.Shape9.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape9.setTextureSize(16, 16);
        this.Shape9.mirror = true;
        this.setRotation(this.Shape9, -0.5759587F, 0.0F, -((float)Math.PI * 2F / 9F));
        this.Shape10 = new BodyPart(this, 0, 9);
        this.Shape10.addBox(-0.4F, 5.4F, -1.0F, 1, 1, 2);
        this.Shape10.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape10.setTextureSize(16, 16);
        this.Shape10.mirror = true;
        this.setRotation(this.Shape10, -0.5585054F, 0.0F, -((float)Math.PI * 2F / 9F));
        this.Shape11 = new BodyPart(this, 0, 9);
        this.Shape11.addBox(-1.4F, 5.2F, -1.0F, 1, 1, 2);
        this.Shape11.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape11.setTextureSize(16, 16);
        this.Shape11.mirror = true;
        this.setRotation(this.Shape11, -0.5759587F, 0.0F, ((float)Math.PI * 2F / 9F));
        this.Shape12 = new BodyPart(this, 0, 9);
        this.Shape12.addBox(-0.6F, 5.4F, -1.0F, 1, 1, 2);
        this.Shape12.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape12.setTextureSize(16, 16);
        this.Shape12.mirror = true;
        this.setRotation(this.Shape12, -0.5585054F, 0.0F, ((float)Math.PI * 2F / 9F));
        this.setTexture(texS);
    }

    protected void setRotation(BodyPart model, float x, float y, float z)
    {
        model.origRotationX = x;
        model.origRotationY = y;
        model.origRotationZ = z;
    }

    protected void processMotion(ModelPlayer modelPlayer, EntityPlayer player, float time)
    {
    }

    public String getName()
    {
        return "Хищник амулет";
    }
}
