package net.minecraft.client.addon.tchestplate.items.renders.models.bracelets;

import net.minecraft.client.addon.tchestplate.items.renders.models.BaseItemModel;
import net.minecraft.client.addon.tchestplate.items.renders.models.BodyPart;
import net.minecraft.client.addon.tchestplate.items.renders.models.RightHand;
import net.minecraft.client.addon.tchestplate.player.ExtendedPlayer;
import org.lwjgl.opengl.GL11;

public class HunterBraslet extends RightHand
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

    public HunterBraslet(String texS)
    {
        super(BaseItemModel.ModelType.BRACELETS);
        super.textureWidth = 16;
        super.textureHeight = 16;
        this.Shape1 = new BodyPart(this, 1, 0);
        this.Shape1.addBox(-4.1F, 5.2F, -1.5F, 1, 2, 3);
        this.Shape1.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.Shape1.setTextureSize(16, 16);
        this.Shape1.mirror = true;
        this.setRotation(this.Shape1, 0.0F, 0.0F, 0.0F);
        this.Shape2 = new BodyPart(this, 0, 2);
        this.Shape2.addBox(-2.5F, 5.2F, -3.1F, 3, 2, 1);
        this.Shape2.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.Shape2.setTextureSize(16, 16);
        this.Shape2.mirror = true;
        this.setRotation(this.Shape2, 0.0F, 0.0F, 0.0F);
        this.Shape3 = new BodyPart(this, 0, 2);
        this.Shape3.addBox(-3.7F, 5.2F, -2.2F, 1, 2, 1);
        this.Shape3.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.Shape3.setTextureSize(16, 16);
        this.Shape3.mirror = true;
        this.setRotation(this.Shape3, 0.0F, 0.0F, 0.0F);
        this.Shape4 = new BodyPart(this, 0, 2);
        this.Shape4.addBox(-2.5F, 5.2F, 2.1F, 3, 2, 1);
        this.Shape4.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.Shape4.setTextureSize(16, 16);
        this.Shape4.mirror = true;
        this.setRotation(this.Shape4, 0.0F, 0.0F, 0.0F);
        this.Shape5 = new BodyPart(this, 0, 2);
        this.Shape5.addBox(-3.2F, 5.2F, 1.7F, 1, 2, 1);
        this.Shape5.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.Shape5.setTextureSize(16, 16);
        this.Shape5.mirror = true;
        this.setRotation(this.Shape5, 0.0F, 0.0F, 0.0F);
        this.Shape6 = new BodyPart(this, 0, 0);
        this.Shape6.addBox(1.1F, 5.2F, -1.5F, 1, 2, 3);
        this.Shape6.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.Shape6.setTextureSize(16, 16);
        this.Shape6.mirror = true;
        this.setRotation(this.Shape6, 0.0F, 0.0F, 0.0F);
        this.Shape7 = new BodyPart(this, 0, 2);
        this.Shape7.addBox(0.2F, 5.2F, -2.7F, 1, 2, 1);
        this.Shape7.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.Shape7.setTextureSize(16, 16);
        this.Shape7.mirror = true;
        this.setRotation(this.Shape7, 0.0F, 0.0F, 0.0F);
        this.Shape8 = new BodyPart(this, 0, 2);
        this.Shape8.addBox(0.2F, 5.2F, 1.7F, 1, 2, 1);
        this.Shape8.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.Shape8.setTextureSize(16, 16);
        this.Shape8.mirror = true;
        this.setRotation(this.Shape8, 0.0F, 0.0F, 0.0F);
        this.Shape9 = new BodyPart(this, 0, 2);
        this.Shape9.addBox(-3.2F, 5.2F, -2.7F, 1, 2, 1);
        this.Shape9.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.Shape9.setTextureSize(16, 16);
        this.Shape9.mirror = true;
        this.setRotation(this.Shape9, 0.0F, 0.0F, 0.0F);
        this.Shape10 = new BodyPart(this, 0, 2);
        this.Shape10.addBox(0.7F, 5.2F, -2.2F, 1, 2, 1);
        this.Shape10.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.Shape10.setTextureSize(16, 16);
        this.Shape10.mirror = true;
        this.setRotation(this.Shape10, 0.0F, 0.0F, 0.0F);
        this.Shape11 = new BodyPart(this, 0, 2);
        this.Shape11.addBox(-3.7F, 5.2F, 1.2F, 1, 2, 1);
        this.Shape11.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.Shape11.setTextureSize(16, 16);
        this.Shape11.mirror = true;
        this.setRotation(this.Shape11, 0.0F, 0.0F, 0.0F);
        this.Shape12 = new BodyPart(this, 0, 2);
        this.Shape12.addBox(0.7F, 5.2F, 1.2F, 1, 2, 1);
        this.Shape12.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.Shape12.setTextureSize(16, 16);
        this.Shape12.mirror = true;
        this.setRotation(this.Shape12, 0.0F, 0.0F, 0.0F);
        this.setTexture(texS);
    }

    protected boolean preRender(ExtendedPlayer pi)
    {
        if (pi.PredatorMode == 50)
        {
            this.setTexture("/mods/provider/ual/hunter_black_braclet.png");
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
        }
        else
        {
            this.setTexture("/mods/provider/ual/hunter_braclet.png");
        }

        return true;
    }

    protected boolean postRender(ExtendedPlayer pi)
    {
        GL11.glDisable(GL11.GL_BLEND);
        return true;
    }

    public String getName()
    {
        return "Хантер браслет";
    }
}
