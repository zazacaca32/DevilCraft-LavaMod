package net.minecraft.client.addon.tchestplate.items.renders.models.amulets;

import net.minecraft.client.addon.tchestplate.items.renders.models.BaseItemModel;
import net.minecraft.client.addon.tchestplate.items.renders.models.BodyPart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.src.ModelPlayer;

public class LavaRBAmulet extends BaseItemModel
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

    public LavaRBAmulet(String tex)
    {
        super(BaseItemModel.ModelType.BODY);
        super.textureWidth = 32;
        super.textureHeight = 32;
        (this.Shape1 = new BodyPart(this, 1, 10)).addBox(-3.0F, 2.5F, -4.7F, 1, 1, 1);
        this.Shape1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape1.setTextureSize(32, 32);
        this.Shape1.mirror = true;
        this.setRotation(this.Shape1, 0.2443461F, -0.2443461F, 0.0F);
        (this.Shape2 = new BodyPart(this, 0, 6)).addBox(2.0F, 2.0F, -3.5F, 3, 3, 1);
        this.Shape2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape2.setTextureSize(32, 32);
        this.Shape2.mirror = true;
        this.setRotation(this.Shape2, 0.0F, 0.0F, ((float)Math.PI / 4F));
        (this.Shape3 = new BodyPart(this, 8, 6)).addBox(2.5F, -1.0F, -2.0F, 1, 5, 1);
        this.Shape3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape3.setTextureSize(32, 32);
        this.Shape3.mirror = true;
        this.setRotation(this.Shape3, -0.4014257F, 0.0F, ((float)Math.PI / 5F));
        (this.Shape4 = new BodyPart(this, 8, 6)).addBox(-3.5F, -0.9F, -1.9F, 1, 5, 1);
        this.Shape4.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape4.setTextureSize(32, 32);
        this.Shape4.mirror = true;
        this.setRotation(this.Shape4, -0.418879F, 0.0F, -0.6108652F);
        (this.Shape5 = new BodyPart(this, 12, 6)).addBox(-3.9F, -2.0F, -1.8F, 1, 6, 1);
        this.Shape5.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape5.setTextureSize(32, 32);
        this.Shape5.mirror = true;
        this.setRotation(this.Shape5, -0.418879F, 0.0F, -((float)Math.PI / 4F));
        (this.Shape6 = new BodyPart(this, 12, 6)).addBox(3.0F, -2.0F, -1.8F, 1, 6, 1);
        this.Shape6.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape6.setTextureSize(32, 32);
        this.Shape6.mirror = true;
        this.setRotation(this.Shape6, -0.4014257F, 0.0F, 0.8203047F);
        (this.Shape7 = new BodyPart(this, 1, 10)).addBox(2.0F, 2.5F, -4.7F, 1, 1, 1);
        this.Shape7.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape7.setTextureSize(32, 32);
        this.Shape7.mirror = true;
        this.setRotation(this.Shape7, 0.2443461F, 0.2443461F, 0.0F);
        (this.Shape8 = new BodyPart(this, 1, 10)).addBox(-0.5F, 3.0F, -4.3F, 1, 1, 1);
        this.Shape8.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape8.setTextureSize(32, 32);
        this.Shape8.mirror = true;
        this.setRotation(this.Shape8, 0.0F, 0.0F, 0.0F);
        (this.Shape9 = new BodyPart(this, 4, 10)).addBox(-0.5F, 4.3F, -4.0F, 1, 2, 1);
        this.Shape9.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape9.setTextureSize(32, 32);
        this.Shape9.mirror = true;
        this.setRotation(this.Shape9, 0.0523599F, 0.0F, 0.0F);
        (this.Shape10 = new BodyPart(this, 0, 11)).addBox(-1.5F, 3.8F, -4.0F, 3, 1, 1);
        this.Shape10.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape10.setTextureSize(32, 32);
        this.Shape10.mirror = true;
        this.setRotation(this.Shape10, 0.0F, 0.0F, 0.0F);
        this.setTexture(tex);
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
        return "Деревянный амулет";
    }
}
