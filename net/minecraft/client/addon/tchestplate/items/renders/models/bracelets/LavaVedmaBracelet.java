package net.minecraft.client.addon.tchestplate.items.renders.models.bracelets;

import net.minecraft.client.addon.tchestplate.items.renders.models.BaseItemModel;
import net.minecraft.client.addon.tchestplate.items.renders.models.BodyPart;
import net.minecraft.client.addon.tchestplate.items.renders.models.RightHand;
import net.minecraft.client.addon.tchestplate.player.ExtendedPlayer;
import org.lwjgl.opengl.GL11;

public class LavaVedmaBracelet extends RightHand
{
    BodyPart Shape19;
    BodyPart Shape29;
    BodyPart Shape30;
    BodyPart Shape31;
    BodyPart Shape32;
    BodyPart Shape33;
    BodyPart Shape34;
    BodyPart Shape39;
    BodyPart Shape40;

    public LavaVedmaBracelet(String var1, int var2)
    {
        super(BaseItemModel.ModelType.BRACELETS);
        super.textureWidth = 16;
        super.textureHeight = 16;
        this.Shape19 = new BodyPart(this, 1, 0);
        this.Shape19.addBox(-4.1F, 10.5F, -0.5F, 1, 2, 1);
        this.Shape19.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.Shape19.setTextureSize(16, 16);
        this.Shape19.mirror = true;
        this.setRotation(this.Shape19, 0.0F, 0.0F, 0.0F);
        this.Shape29 = new BodyPart(this, 0, 0);
        this.Shape29.addBox(-1.0F, 11.0F, 0.0F, 0, 0, 0);
        this.Shape29.setTextureSize(16, 16);
        this.Shape29.mirror = true;
        this.setRotation(this.Shape29, 0.0F, 0.0F, 0.0F);
        this.Shape30 = new BodyPart(this, 0, 9);
        this.Shape30.addBox(-4.2F, -2.0F, -9.0F, 1, 4, 1);
        this.Shape30.setTextureSize(16, 16);
        this.Shape30.mirror = true;
        this.setRotation(this.Shape30, ((float)Math.PI / 2F), 0.0F, 0.0F);
        this.Shape31 = new BodyPart(this, 0, 9);
        this.Shape31.mirror = true;
        this.Shape31.addBox(-4.2F, 6.6F, -3.0F, 1, 4, 1);
        this.Shape31.setTextureSize(16, 16);
        this.setRotation(this.Shape31, 0.3490659F, 0.0F, 0.0F);
        this.Shape32 = new BodyPart(this, 0, 9);
        this.Shape32.mirror = true;
        this.Shape32.addBox(-4.2F, 6.5F, 2.0F, 1, 4, 1);
        this.Shape32.setTextureSize(16, 16);
        this.setRotation(this.Shape32, -0.3490659F, 0.0F, 0.0F);
        this.Shape33 = new BodyPart(this, 0, 9);
        this.Shape33.mirror = true;
        this.Shape33.addBox(-4.2F, 3.6F, 7.1F, 1, 4, 1);
        this.Shape33.setTextureSize(16, 16);
        this.setRotation(this.Shape33, -0.9250245F, 0.0F, 0.0F);
        this.Shape34 = new BodyPart(this, 0, 9);
        this.Shape34.mirror = true;
        this.Shape34.addBox(-4.2F, 3.5F, -8.2F, 1, 4, 1);
        this.Shape34.setTextureSize(16, 16);
        this.setRotation(this.Shape34, 0.9424778F, 0.0F, 0.0F);
        this.Shape39 = new BodyPart(this, 0, 0);
        this.Shape39.mirror = true;
        this.Shape39.addBox(-4.1F, 5.5F, 2.7F, 1, 2, 1);
        this.Shape39.setTextureSize(16, 16);
        this.setRotation(this.Shape39, -0.2617994F, 0.0F, 0.0F);
        this.Shape40 = new BodyPart(this, 0, 0);
        this.Shape40.mirror = true;
        this.Shape40.addBox(-4.1F, 5.5F, -3.7F, 1, 2, 1);
        this.Shape40.setTextureSize(16, 16);
        this.setRotation(this.Shape40, 0.2617994F, 0.0F, 0.0F);
        this.setTexture("/mods/LavaChestPlate/textures/items/bracelets/vedma_b.png");
    }

    protected boolean preRender(ExtendedPlayer var1)
    {
        GL11.glDisable(GL11.GL_CULL_FACE);
        GL11.glEnable(GL11.GL_BLEND);
        return true;
    }

    protected boolean postRender(ExtendedPlayer var1)
    {
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_CULL_FACE);
        return true;
    }

    public String getName()
    {
        return "Лава ведьма браслет";
    }
}
