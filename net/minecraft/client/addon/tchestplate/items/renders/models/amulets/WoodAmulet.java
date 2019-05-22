package net.minecraft.client.addon.tchestplate.items.renders.models.amulets;

import net.minecraft.client.addon.tchestplate.items.renders.models.BaseItemModel;
import net.minecraft.client.addon.tchestplate.items.renders.models.BodyPart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.src.ModelPlayer;

public class WoodAmulet extends BaseItemModel
{
    BodyPart Shape1;
    BodyPart Shape2;
    BodyPart Shape3;
    BodyPart Shape4;
    private static String tex;

    public WoodAmulet(String tex)
    {
        super(BaseItemModel.ModelType.BODY);
        super.textureWidth = 32;
        super.textureHeight = 32;
        (this.Shape1 = new BodyPart(this, 20, 0)).addBox(2.0F, 2.0F, -3.5F, 3, 3, 1);
        this.Shape1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape1.setTextureSize(32, 32);
        this.Shape1.mirror = true;
        this.setRotation(this.Shape1, 0.0F, 0.0F, ((float)Math.PI / 4F));
        (this.Shape2 = new BodyPart(this, 20, 4)).addBox(2.5F, 2.5F, -3.9F, 2, 2, 1);
        this.Shape2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape2.setTextureSize(32, 32);
        this.Shape2.mirror = true;
        this.setRotation(this.Shape2, 0.0F, 0.0F, ((float)Math.PI / 4F));
        (this.Shape3 = new BodyPart(this, 28, 0)).addBox(2.5F, -1.0F, -2.0F, 1, 5, 1);
        this.Shape3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape3.setTextureSize(32, 32);
        this.Shape3.mirror = true;
        this.setRotation(this.Shape3, -0.4014257F, 0.0F, ((float)Math.PI / 5F));
        (this.Shape4 = new BodyPart(this, 28, 0)).addBox(-3.5F, -0.9F, -2.0F, 1, 5, 1);
        this.Shape4.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape4.setTextureSize(32, 32);
        this.Shape4.mirror = true;
        this.setRotation(this.Shape4, -0.418879F, 0.0F, -0.6108652F);
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
