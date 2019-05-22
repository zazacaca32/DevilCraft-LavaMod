package net.minecraft.client.addon.tchestplate.items.renders.models.shields;

import net.minecraft.client.addon.tchestplate.items.renders.models.BaseItemModel;
import net.minecraft.client.addon.tchestplate.items.renders.models.BodyPart;
import net.minecraft.client.addon.tchestplate.items.renders.models.LeftHand;
import net.minecraft.client.addon.tchestplate.player.ExtendedPlayer;
import org.lwjgl.opengl.GL11;

public class ShieldHunter extends LeftHand
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
    BodyPart Shape25;
    BodyPart Shape27;
    BodyPart Shape28;
    BodyPart Shape29;
    BodyPart Shape30;
    BodyPart Shape31;

    public ShieldHunter()
    {
        super(BaseItemModel.ModelType.SHIELD);
        super.textureWidth = 16;
        super.textureHeight = 16;
        this.Shape1 = new BodyPart(this, 0, 0);
        this.Shape1.addBox(3.0F, 6.0F, -1.0F, 1, 2, 2);
        this.Shape1.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.Shape1.setTextureSize(16, 16);
        this.Shape1.mirror = true;
        this.setRotation(this.Shape1, 0.0F, 0.0F, 0.0F);
        this.Shape2 = new BodyPart(this, 0, 0);
        this.Shape2.addBox(4.1F, 4.5F, -6.0F, 1, 3, 5);
        this.Shape2.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.Shape2.setTextureSize(16, 16);
        this.Shape2.mirror = true;
        this.setRotation(this.Shape2, 0.5235988F, 0.0F, 0.0F);
        this.Shape3 = new BodyPart(this, 0, 0);
        this.Shape3.addBox(4.1F, 3.5F, -5.0F, 1, 5, 3);
        this.Shape3.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.Shape3.setTextureSize(16, 16);
        this.Shape3.mirror = true;
        this.setRotation(this.Shape3, 0.5235988F, 0.0F, 0.0F);
        this.Shape4 = new BodyPart(this, 0, 8);
        this.Shape4.addBox(4.0F, 2.4F, -4.0F, 1, 2, 1);
        this.Shape4.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.Shape4.setTextureSize(16, 16);
        this.Shape4.mirror = true;
        this.setRotation(this.Shape4, ((float)Math.PI / 10F), 0.0F, 0.0F);
        this.Shape5 = new BodyPart(this, 0, 8);
        this.Shape5.addBox(4.0F, 2.2F, 1.5F, 1, 2, 1);
        this.Shape5.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.Shape5.setTextureSize(16, 16);
        this.Shape5.mirror = true;
        this.setRotation(this.Shape5, -0.3316126F, 0.0F, 0.0F);
        this.Shape6 = new BodyPart(this, 0, 8);
        this.Shape6.addBox(4.0F, 9.1F, 2.0F, 1, 2, 1);
        this.Shape6.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.Shape6.setTextureSize(16, 16);
        this.Shape6.mirror = true;
        this.setRotation(this.Shape6, 0.6806784F, 0.0F, 0.0F);
        this.Shape7 = new BodyPart(this, 0, 8);
        this.Shape7.addBox(4.0F, 7.2F, -7.0F, 1, 1, 3);
        this.Shape7.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.Shape7.setTextureSize(16, 16);
        this.Shape7.mirror = true;
        this.setRotation(this.Shape7, 0.296706F, 0.0F, 0.0F);
        this.Shape8 = new BodyPart(this, 0, 8);
        this.Shape8.addBox(4.0F, 1.0F, -2.6F, 1, 3, 1);
        this.Shape8.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.Shape8.setTextureSize(16, 16);
        this.Shape8.mirror = true;
        this.setRotation(this.Shape8, -0.122173F, 0.0F, 0.0F);
        this.Shape9 = new BodyPart(this, 0, 8);
        this.Shape9.addBox(4.0F, 1.0F, -3.5F, 1, 3, 1);
        this.Shape9.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.Shape9.setTextureSize(16, 16);
        this.Shape9.mirror = true;
        this.setRotation(this.Shape9, 0.3839724F, 0.0F, 0.0F);
        this.Shape10 = new BodyPart(this, 0, 8);
        this.Shape10.addBox(4.0F, -2.0F, -2.0F, 1, 1, 2);
        this.Shape10.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.Shape10.setTextureSize(16, 16);
        this.Shape10.mirror = true;
        this.setRotation(this.Shape10, 1.064651F, 0.0F, 0.0F);
        this.Shape11 = new BodyPart(this, 0, 8);
        this.Shape11.addBox(4.0F, 1.0F, -3.0F, 1, 2, 1);
        this.Shape11.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.Shape11.setTextureSize(16, 16);
        this.Shape11.mirror = true;
        this.setRotation(this.Shape11, 0.1919862F, 0.0F, 0.0F);
        this.Shape12 = new BodyPart(this, 0, 9);
        this.Shape12.addBox(4.0F, -1.8F, -0.7F, 1, 1, 2);
        this.Shape12.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.Shape12.setTextureSize(16, 16);
        this.Shape12.mirror = true;
        this.setRotation(this.Shape12, 0.6806784F, 0.0F, 0.0F);
        this.Shape13 = new BodyPart(this, 0, 8);
        this.Shape13.addBox(4.0F, 9.3F, -6.0F, 1, 1, 3);
        this.Shape13.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.Shape13.setTextureSize(16, 16);
        this.Shape13.mirror = true;
        this.setRotation(this.Shape13, -0.122173F, 0.0F, 0.0F);
        this.Shape14 = new BodyPart(this, 0, 10);
        this.Shape14.addBox(4.0F, 5.0F, 0.0F, 1, 1, 2);
        this.Shape14.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.Shape14.setTextureSize(16, 16);
        this.Shape14.mirror = true;
        this.setRotation(this.Shape14, ((float)Math.PI / 10F), 0.0F, 0.0F);
        this.Shape15 = new BodyPart(this, 0, 8);
        this.Shape15.addBox(4.0F, -4.7F, -11.5F, 1, 2, 1);
        this.Shape15.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.Shape15.setTextureSize(16, 16);
        this.Shape15.mirror = true;
        this.setRotation(this.Shape15, 1.169371F, 0.0F, 0.0F);
        this.Shape16 = new BodyPart(this, 0, 10);
        this.Shape16.addBox(4.0F, 3.7F, 3.9F, 1, 1, 3);
        this.Shape16.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.Shape16.setTextureSize(16, 16);
        this.Shape16.mirror = true;
        this.setRotation(this.Shape16, -0.122173F, 0.0F, 0.0F);
        this.Shape17 = new BodyPart(this, 0, 9);
        this.Shape17.addBox(4.0F, -0.8F, -12.3F, 1, 2, 1);
        this.Shape17.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.Shape17.setTextureSize(16, 16);
        this.Shape17.mirror = true;
        this.setRotation(this.Shape17, 0.6806784F, 0.0F, 0.0F);
        this.Shape18 = new BodyPart(this, 0, 10);
        this.Shape18.addBox(4.0F, 7.5F, -2.6F, 1, 2, 1);
        this.Shape18.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.Shape18.setTextureSize(16, 16);
        this.Shape18.mirror = true;
        this.setRotation(this.Shape18, 1.169371F, 0.0F, 0.0F);
        this.Shape19 = new BodyPart(this, 0, 8);
        this.Shape19.addBox(4.0F, 6.0F, -8.0F, 1, 1, 3);
        this.Shape19.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.Shape19.setTextureSize(16, 16);
        this.Shape19.mirror = true;
        this.setRotation(this.Shape19, 0.4014257F, 0.0F, 0.0F);
        this.Shape20 = new BodyPart(this, 0, 10);
        this.Shape20.addBox(4.0F, 5.8F, -0.2F, 1, 1, 3);
        this.Shape20.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.Shape20.setTextureSize(16, 16);
        this.Shape20.mirror = true;
        this.setRotation(this.Shape20, 0.3316126F, 0.0F, 0.0F);
        this.Shape21 = new BodyPart(this, 0, 8);
        this.Shape21.addBox(4.0F, 5.5F, 3.0F, 1, 1, 2);
        this.Shape21.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.Shape21.setTextureSize(16, 16);
        this.Shape21.mirror = true;
        this.setRotation(this.Shape21, 0.1919862F, 0.0F, 0.0F);
        this.Shape22 = new BodyPart(this, 0, 0);
        this.Shape22.addBox(3.9F, 4.0F, -5.5F, 2, 4, 4);
        this.Shape22.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.Shape22.setTextureSize(16, 16);
        this.Shape22.mirror = true;
        this.setRotation(this.Shape22, 0.5235988F, 0.0F, 0.0F);
        this.Shape23 = new BodyPart(this, 2, 1);
        this.Shape23.addBox(5.5F, 4.5F, -5.0F, 1, 3, 3);
        this.Shape23.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.Shape23.setTextureSize(16, 16);
        this.Shape23.mirror = true;
        this.setRotation(this.Shape23, 0.5235988F, 0.0F, 0.0F);
        this.Shape24 = new BodyPart(this, 0, 8);
        this.Shape24.addBox(4.0F, 2.5F, -1.0F, 1, 1, 2);
        this.Shape24.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.Shape24.setTextureSize(16, 16);
        this.Shape24.mirror = true;
        this.setRotation(this.Shape24, 0.6457718F, 0.0F, 0.0F);
        this.Shape25 = new BodyPart(this, 0, 8);
        this.Shape25.addBox(4.0F, 6.2F, -2.0F, 1, 1, 2);
        this.Shape25.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.Shape25.setTextureSize(16, 16);
        this.Shape25.mirror = true;
        this.setRotation(this.Shape25, -0.3316126F, 0.0F, 0.0F);
        this.Shape27 = new BodyPart(this, 0, 8);
        this.Shape27.addBox(4.0F, 0.5F, -7.0F, 1, 2, 1);
        this.Shape27.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.Shape27.setTextureSize(16, 16);
        this.Shape27.mirror = true;
        this.setRotation(this.Shape27, ((float)Math.PI / 5F), 0.0F, 0.0F);
        this.Shape28 = new BodyPart(this, 0, 8);
        this.Shape28.addBox(4.0F, 6.0F, 4.5F, 1, 1, 2);
        this.Shape28.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.Shape28.setTextureSize(16, 16);
        this.Shape28.mirror = true;
        this.setRotation(this.Shape28, -0.3316126F, 0.0F, 0.0F);
        this.Shape29 = new BodyPart(this, 0, 8);
        this.Shape29.addBox(4.0F, 8.8F, -2.3F, 1, 2, 1);
        this.Shape29.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.Shape29.setTextureSize(16, 16);
        this.Shape29.mirror = true;
        this.setRotation(this.Shape29, 0.6457718F, 0.0F, 0.0F);
        this.Shape30 = new BodyPart(this, 0, 8);
        this.Shape30.addBox(4.0F, 7.0F, -8.0F, 1, 1, 2);
        this.Shape30.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.Shape30.setTextureSize(16, 16);
        this.Shape30.mirror = true;
        this.setRotation(this.Shape30, 0.2443461F, 0.0F, 0.0F);
        this.Shape31 = new BodyPart(this, 8, 8);
        this.Shape31.addBox(6.0F, 6.5F, -0.5F, 1, 1, 1);
        this.Shape31.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.Shape31.setTextureSize(16, 16);
        this.Shape31.mirror = true;
        this.setRotation(this.Shape31, 0.0F, 0.0F, 0.0F);
        this.setTexture("/mods/provider/ual/hunter_shield.png");
    }

    protected boolean preRender(ExtendedPlayer pi)
    {
        if (pi.PredatorMode == 50)
        {
            this.setTexture("/mods/provider/ual/hunter_black_shield.png");
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
        }
        else
        {
            this.setTexture("/mods/provider/ual/hunter_shield.png");
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
        return "Лава Хантер Щит";
    }
}
