package net.minecraft.client.addon.tchestplate.items.renders.models.pets;

import net.minecraft.client.addon.tchestplate.items.renders.models.BasePet;
import net.minecraft.client.addon.tchestplate.items.renders.models.BodyPart;
import net.minecraft.client.addon.tchestplate.player.ExtendedPlayer;
import net.minecraft.client.model.*;
import net.minecraft.entity.player.*;
import net.minecraft.src.ModelPlayer;
import net.minecraft.util.*;
import org.lwjgl.opengl.*;

public class leprechaunPet extends BasePet
{
    BodyPart head;
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
    BodyPart body;
    BodyPart rightarm;
    BodyPart leftarm;
    BodyPart rightleg;
    BodyPart leftleg;

    private static final String tex = "/mods/LavaChestPlate/textures/items/pet/vedma.png";
    boolean flagInvise;

    public leprechaunPet(final boolean b)
    {
        this.flagInvise = false;
        this.textureWidth = 64;
        this.textureHeight = 64;
        (this.head = new BodyPart((ModelBase)this, 30, 30)).addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8);
        this.head.setRotationPoint(0.0f, 0.0f, 1.0f);
        this.head.setTextureSize(64, 32);
        this.head.mirror = true;
        this.setRotation(this.head, 0.0f, 0.0f, 0.0f);
        Shape1 = new BodyPart(this, 0, 0);
        Shape1.addBox(0F, 0F, 0F, 4, 2, 3);
        Shape1.setRotationPoint(-2F, -8F, -7F);
        Shape1.setTextureSize(64, 64);
        Shape1.mirror = true;
        setRotation(Shape1, 0F, 0F, 0F);
        Shape2 = new BodyPart(this, 0, 0);
        Shape2.addBox(0F, 0F, 0F, 2, 2, 1);
        Shape2.setRotationPoint(-4F, -9F, -6F);
        Shape2.setTextureSize(64, 64);
        Shape2.mirror = true;
        setRotation(Shape2, 0F, 0F, 0F);
        Shape3 = new BodyPart(this, 0, 0);
        Shape3.addBox(0F, 0F, 0F, 2, 2, 1);
        Shape3.setRotationPoint(2F, -9F, -6F);
        Shape3.setTextureSize(64, 64);
        Shape3.mirror = true;
        setRotation(Shape3, 0F, 0F, 0F);
        Shape4 = new BodyPart(this, 0, 0);
        Shape4.addBox(0F, 0F, 0F, 10, 2, 4);
        Shape4.setRotationPoint(-5F, -9F, -5F);
        Shape4.setTextureSize(64, 64);
        Shape4.mirror = true;
        setRotation(Shape4, 0F, 0F, 0F);
        Shape5 = new BodyPart(this, 0, 0);
        Shape5.addBox(0F, 0F, 0F, 8, 2, 7);
        Shape5.setRotationPoint(-4F, -9F, -1F);
        Shape5.setTextureSize(64, 64);
        Shape5.mirror = true;
        setRotation(Shape5, 0F, 0F, 0F);
        Shape6 = new BodyPart(this, 0, 0);
        Shape6.addBox(0F, 0F, 0F, 4, 1, 1);
        Shape6.setRotationPoint(-2F, -8F, 6F);
        Shape6.setTextureSize(64, 64);
        Shape6.mirror = true;
        setRotation(Shape6, 0F, 0F, 0F);
        Shape7 = new BodyPart(this, 41, 13);
        Shape7.addBox(0F, 0F, 0F, 2, 2, 1);
        Shape7.setRotationPoint(-1F, -11F, -5F);
        Shape7.setTextureSize(64, 64);
        Shape7.mirror = true;
        setRotation(Shape7, 0F, 0F, 0F);
        Shape8 = new BodyPart(this, 0, 0);
        Shape8.addBox(0F, 0F, 0F, 4, 2, 1);
        Shape8.setRotationPoint(-2F, -14F, -5F);
        Shape8.setTextureSize(64, 64);
        Shape8.mirror = true;
        setRotation(Shape8, 0F, 0F, 0F);
        Shape9 = new BodyPart(this, 9, 0);
        Shape9.addBox(0F, 0F, 8F, 1, 2, 1);
        Shape9.setRotationPoint(-5F, -10F, -4F);
        Shape9.setTextureSize(64, 64);
        Shape9.mirror = true;
        setRotation(Shape9, 0F, 0F, 0F);
        Shape10 = new BodyPart(this, 0, 0);
        Shape10.addBox(0F, 0F, 0F, 2, 2, 8);
        Shape10.setRotationPoint(-6F, -10F, -4F);
        Shape10.setTextureSize(64, 64);
        Shape10.mirror = true;
        setRotation(Shape10, 0F, 0F, 0F);
        Shape11 = new BodyPart(this, 0, 9);
        Shape11.addBox(0F, 0F, 0F, 6, 2, 1);
        Shape11.setRotationPoint(-3F, -11F, -4F);
        Shape11.setTextureSize(64, 64);
        Shape11.mirror = true;
        setRotation(Shape11, 0F, 0F, 0F);
        Shape12 = new BodyPart(this, 0, 4);
        Shape12.addBox(0F, 0F, 0F, 8, 2, 6);
        Shape12.setRotationPoint(-4F, -11F, -3F);
        Shape12.setTextureSize(64, 64);
        Shape12.mirror = true;
        setRotation(Shape12, 0F, 0F, 0F);
        Shape13 = new BodyPart(this, 0, 0);
        Shape13.addBox(0F, 0F, 0F, 8, 3, 6);
        Shape13.setRotationPoint(-4F, -14F, -4F);
        Shape13.setTextureSize(64, 64);
        Shape13.mirror = true;
        setRotation(Shape13, 0F, 0F, 0F);
        Shape14 = new BodyPart(this, 0, 0);
        Shape14.addBox(0F, 0F, 0F, 10, 2, 4);
        Shape14.setRotationPoint(-5F, -14F, -3F);
        Shape14.setTextureSize(64, 64);
        Shape14.mirror = true;
        setRotation(Shape14, 0F, 0F, 0F);
        Shape15 = new BodyPart(this, 0, 0);
        Shape15.addBox(0F, 0F, 8F, 1, 2, 1);
        Shape15.setRotationPoint(4F, -10F, -4F);
        Shape15.setTextureSize(64, 64);
        Shape15.mirror = true;
        setRotation(Shape15, 0F, 0F, 0F);
        Shape16 = new BodyPart(this, 0, 0);
        Shape16.addBox(-1F, 0F, 0F, 2, 2, 8);
        Shape16.setRotationPoint(5F, -10F, -4F);
        Shape16.setTextureSize(64, 64);
        Shape16.mirror = true;
        setRotation(Shape16, 0F, 0F, 0F);
        Shape17 = new BodyPart(this, 0, 0);
        Shape17.addBox(0F, 0F, 0F, 2, 2, 1);
        Shape17.setRotationPoint(-1F, -14F, 3F);
        Shape17.setTextureSize(64, 64);
        Shape17.mirror = true;
        setRotation(Shape17, 0F, 0F, 0F);
        Shape18 = new BodyPart(this, 0, 0);
        Shape18.addBox(0F, 0F, 0F, 6, 3, 1);
        Shape18.setRotationPoint(-3F, -14F, 2F);
        Shape18.setTextureSize(64, 64);
        Shape18.mirror = true;
        setRotation(Shape18, 0F, 0F, 0F);
        Shape19 = new BodyPart(this, 0, 0);
        Shape19.addBox(0F, 0F, 0F, 8, 1, 1);
        Shape19.setRotationPoint(-4F, -14F, 2F);
        Shape19.setTextureSize(64, 64);
        Shape19.mirror = true;
        setRotation(Shape19, 0F, 0F, 0F);
        Shape20 = new BodyPart(this, 0, 10);
        Shape20.addBox(0F, 0F, 0F, 2, 1, 1);
        Shape20.setRotationPoint(-1F, -11F, 3F);
        Shape20.setTextureSize(64, 64);
        Shape20.mirror = true;
        setRotation(Shape20, 0F, 0F, 0F);
        Shape21 = new BodyPart(this, 0, 10);
        Shape21.addBox(0F, 0F, 0F, 4, 1, 1);
        Shape21.setRotationPoint(-2F, -10F, 3F);
        Shape21.setTextureSize(64, 64);
        Shape21.mirror = true;
        setRotation(Shape21, 0F, 0F, 0F);
        Shape22 = new BodyPart(this, 0, 0);
        Shape22.addBox(0F, 0F, 0F, 1, 1, 1);
        Shape22.setRotationPoint(-4F, -10F, 3F);
        Shape22.setTextureSize(64, 64);
        Shape22.mirror = true;
        setRotation(Shape22, 0F, 0F, 0F);
        Shape23 = new BodyPart(this, 0, 0);
        Shape23.addBox(3F, -10F, 3F, 1, 1, 1);
        Shape23.setRotationPoint(0F, 0F, 0F);
        Shape23.setTextureSize(64, 64);
        Shape23.mirror = true;
        setRotation(Shape23, 0F, 0F, 0F);
        (this.body = new BodyPart((ModelBase)this, 30, 51)).addBox(-2.0f, 0.0f, 0.0f, 4, 5, 2);
        this.body.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.body.setTextureSize(64, 32);
        this.body.mirror = true;
        this.setRotation(this.body, 0.0f, 0.0f, 0.0f);
        (this.rightarm = new BodyPart((ModelBase)this, 30, 45)).addBox(-2.0f, -0.8f, -0.8f, 2, 4, 2);
        this.rightarm.setRotationPoint(-2.0f, 1.0f, 1.0f);
        this.rightarm.setTextureSize(64, 32);
        this.rightarm.mirror = true;
        this.setRotation(this.rightarm, -0.2094395f, 0.0f, 0.0f);
        (this.leftarm = new BodyPart((ModelBase)this, 30, 45)).addBox(0.0f, -0.8f, -0.8f, 2, 4, 2);
        this.leftarm.setRotationPoint(2.0f, 1.0f, 1.0f);
        this.leftarm.setTextureSize(64, 32);
        this.leftarm.mirror = true;
        this.setRotation(this.leftarm, -0.0261799f, 0.0f, 0.0f);
        (this.rightleg = new BodyPart((ModelBase)this, 40, 57)).addBox(-1.0f, 0.0f, -1.0f, 2, 3, 2);
        this.rightleg.setRotationPoint(-1.0f, 5.0f, 1.0f);
        this.rightleg.setTextureSize(64, 32);
        this.rightleg.mirror = true;
        this.setRotation(this.rightleg, -0.2792527f, 0.0f, 0.2094395f);
        (this.leftleg = new BodyPart((ModelBase)this, 40, 57)).addBox(-1.0f, 0.0f, -1.0f, 2, 3, 2);
        this.leftleg.setRotationPoint(1.0f, 5.0f, 1.0f);
        this.leftleg.setTextureSize(64, 32);
        this.leftleg.mirror = true;
        this.head.addChild((ModelRenderer)this.Shape1);
        this.head.addChild((ModelRenderer)this.Shape2);
        this.head.addChild((ModelRenderer)this.Shape3);
        this.head.addChild((ModelRenderer)this.Shape4);
        this.head.addChild((ModelRenderer)this.Shape5);
        this.head.addChild((ModelRenderer)this.Shape6);
        this.head.addChild((ModelRenderer)this.Shape7);
        this.head.addChild((ModelRenderer)this.Shape8);
        this.head.addChild((ModelRenderer)this.Shape9);
        this.head.addChild((ModelRenderer)this.Shape10);
        this.head.addChild((ModelRenderer)this.Shape11);
        this.head.addChild((ModelRenderer)this.Shape12);
        this.head.addChild((ModelRenderer)this.Shape13);
        this.head.addChild((ModelRenderer)this.Shape14);
        this.head.addChild((ModelRenderer)this.Shape15);
        this.head.addChild((ModelRenderer)this.Shape16);
        this.head.addChild((ModelRenderer)this.Shape17);
        this.head.addChild((ModelRenderer)this.Shape18);
        this.head.addChild((ModelRenderer)this.Shape19);
        this.head.addChild((ModelRenderer)this.Shape20);
        this.head.addChild((ModelRenderer)this.Shape21);
        this.head.addChild((ModelRenderer)this.Shape22);
        this.head.addChild((ModelRenderer)this.Shape23);
        this.setRotation(this.leftleg, -0.2792527f, 0.0f, -0.2094395f);
        this.setTexture("/mods/LavaChestPlate/textures/items/pet/leprechaun.png");
    }

    protected void smoothRotation(final EntityPlayer entityPlayer, final float n, final ExtendedPlayer extendedPlayer)
    {
        super.smoothRotation(entityPlayer, n, extendedPlayer);
    }

    protected void processMotion(final ModelPlayer modelPlayer, final EntityPlayer entityPlayer, final float n)
    {
        final float n2 = MathHelper.sin(n * 0.16f) * 0.02f;
        final float n3 = MathHelper.sin(n * 0.26f) * 0.05f;
        final float cos = MathHelper.cos(n * 0.16f);
        GL11.glTranslatef(0.6f, -0.5f + n2, 0.3f);
        GL11.glRotatef(cos * 4.0f, 0.0f, 0.6f, 0.0f);
//        this.head.rotationY = modelPlayer.bipedHead.rotateAngleY + 0.2f;
//        this.head.rotationX = modelPlayer.bipedHead.rotateAngleX / 2.0f;
//        this.head.rotationZ = modelPlayer.bipedHead.rotateAngleZ;
        this.rightleg.rotationX = modelPlayer.bipedLeftLeg.rotateAngleX / 2.5f + n3 * 3.0f;
        this.leftleg.rotationX = modelPlayer.bipedRightLeg.rotateAngleX / 2.5f + -n3 * 3.0f;
        this.leftleg.rotationZ = n2 * 5.0f;
        this.leftarm.rotationX = cos * 0.1f;
        this.leftarm.rotationY = -cos * n2 * 2.0f;
        this.leftarm.rotationZ = cos * n2 * 4.0f;
        final float n4 = n2 * 10.0f;
    }

    protected void setRotation(final ModelRenderer modelRenderer, final float rotateAngleX, final float rotateAngleY, final float rotateAngleZ)
    {
        modelRenderer.rotateAngleX = rotateAngleX;
        modelRenderer.rotateAngleY = rotateAngleY;
        modelRenderer.rotateAngleZ = rotateAngleZ;
    }

    protected void setRotation(final ModelRenderer modelRenderer, final float n)
    {
        modelRenderer.rotationPointY += n;
    }

    public String getName()
    {
        return "\u0412\u0435\u0434\u044c\u043c\u0430";
    }
}
