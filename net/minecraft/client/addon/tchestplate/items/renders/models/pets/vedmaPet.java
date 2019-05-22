package net.minecraft.client.addon.tchestplate.items.renders.models.pets;

import net.minecraft.client.addon.tchestplate.items.renders.models.BasePet;
import net.minecraft.client.addon.tchestplate.items.renders.models.BodyPart;
import net.minecraft.client.addon.tchestplate.player.ExtendedPlayer;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.src.ModelPlayer;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

public class vedmaPet extends BasePet
{
    BodyPart head;
    BodyPart head1;
    BodyPart head2;
    BodyPart head3;
    BodyPart head4;
    BodyPart head5;
    BodyPart head6;
    BodyPart body;
    BodyPart rightarm;
    BodyPart leftarm;
    BodyPart rightleg;
    BodyPart leftleg;
    BodyPart met1;
    BodyPart met2;
    BodyPart met3;
    BodyPart met4;
    BodyPart met5;
    BodyPart met6;
    BodyPart met7;
    BodyPart met8;
    BodyPart met14;
    BodyPart met9;
    BodyPart met10;
    BodyPart met11;
    BodyPart met12;
    BodyPart met13;
    private static final String tex = "/mods/LavaChestPlate/textures/items/pet/vedma.png";
    boolean flagInvise = false;

    public vedmaPet(boolean b)
    {
        super.textureWidth = 64;
        super.textureHeight = 32;
        (this.head = new BodyPart(this, 0, 0)).addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8);
        this.head.setRotationPoint(0.0F, 0.0F, 1.0F);
        this.head.setTextureSize(64, 32);
        this.head.mirror = true;
        this.setRotation(this.head, 0.0F, 0.0F, 0.0F);
        (this.head1 = new BodyPart(this, 24, 16)).addBox(-4.0F, -7.0F, -5.0F, 8, 1, 12);
        this.head1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.head1.setTextureSize(64, 32);
        this.head1.mirror = true;
        this.setRotation(this.head1, 0.0F, 0.0F, 0.0F);
        (this.head2 = new BodyPart(this, 24, 20)).addBox(-6.0F, -7.0F, -3.0F, 12, 1, 8);
        this.head2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.head2.setTextureSize(64, 32);
        this.head2.mirror = true;
        this.setRotation(this.head2, 0.0F, 0.0F, 0.0F);
        (this.head3 = new BodyPart(this, 28, 20)).addBox(-4.5F, -9.0F, -3.5F, 9, 3, 9);
        this.head3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.head3.setTextureSize(64, 32);
        this.head3.mirror = true;
        this.setRotation(this.head3, 0.0F, 0.0F, 0.0F);
        (this.head4 = new BodyPart(this, 34, 22)).addBox(-3.0F, -10.0F, -2.0F, 6, 1, 6);
        this.head4.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.head4.setTextureSize(64, 32);
        this.head4.mirror = true;
        this.setRotation(this.head4, -0.0523599F, 0.0F, 0.0F);
        (this.head5 = new BodyPart(this, 48, 24)).addBox(-2.0F, -12.0F, -1.0F, 4, 2, 4);
        this.head5.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.head5.setTextureSize(64, 32);
        this.head5.mirror = true;
        this.setRotation(this.head5, -0.1047198F, 0.0F, 0.0F);
        (this.head6 = new BodyPart(this, 47, 24)).addBox(-1.0F, -14.0F, -1.0F, 2, 2, 2);
        this.head6.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.head6.setTextureSize(64, 32);
        this.head6.mirror = true;
        this.setRotation(this.head6, -0.2094395F, 0.0F, 0.0F);
        (this.body = new BodyPart(this, 0, 21)).addBox(-2.0F, 0.0F, 0.0F, 4, 5, 2);
        this.body.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.body.setTextureSize(64, 32);
        this.body.mirror = true;
        this.setRotation(this.body, 0.0F, 0.0F, 0.0F);
        (this.rightarm = new BodyPart(this, 11, 26)).addBox(-2.0F, -0.8F, -0.8F, 2, 4, 2);
        this.rightarm.setRotationPoint(-2.0F, 1.0F, 1.0F);
        this.rightarm.setTextureSize(64, 32);
        this.rightarm.mirror = true;
        this.setRotation(this.rightarm, -0.6719518F, 0.0F, -0.4886922F);
        (this.leftarm = new BodyPart(this, 10, 26)).addBox(0.0F, -0.8F, -0.8F, 2, 4, 2);
        this.leftarm.setRotationPoint(2.0F, 1.0F, 1.0F);
        this.leftarm.setTextureSize(64, 32);
        this.leftarm.mirror = true;
        this.setRotation(this.leftarm, -0.0261799F, 0.0F, 0.0F);
        (this.rightleg = new BodyPart(this, 0, 16)).addBox(-1.0F, 0.0F, -1.0F, 2, 3, 2);
        this.rightleg.setRotationPoint(-1.0F, 5.0F, 1.0F);
        this.rightleg.setTextureSize(64, 32);
        this.rightleg.mirror = true;
        this.setRotation(this.rightleg, -0.2792527F, 0.0F, 0.2094395F);
        (this.leftleg = new BodyPart(this, 8, 16)).addBox(-1.0F, 0.0F, -1.0F, 2, 3, 2);
        this.leftleg.setRotationPoint(1.0F, 5.0F, 1.0F);
        this.leftleg.setTextureSize(64, 32);
        this.leftleg.mirror = true;
        this.setRotation(this.leftleg, -0.2792527F, 0.0F, -0.2094395F);
        (this.met1 = new BodyPart(this, 39, 4)).addBox(-0.5F, 4.0F, -4.0F, 1, 1, 11);
        this.met1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.met1.setTextureSize(64, 32);
        this.met1.mirror = true;
        this.setRotation(this.met1, -0.2443461F, 0.0F, 0.0F);
        (this.met2 = new BodyPart(this, 44, 12)).addBox(-0.5F, 0.0F, -1.0F, 1, 1, 2);
        this.met2.setRotationPoint(0.0F, 6.0F, 6.0F);
        this.met2.setTextureSize(64, 32);
        this.met2.mirror = true;
        this.setRotation(this.met2, -0.0872665F, 0.0F, 0.0F);
        (this.met3 = new BodyPart(this, 44, 8)).addBox(-0.5F, -1.0F, -1.0F, 1, 1, 2);
        this.met3.setRotationPoint(0.0F, 6.0F, 6.0F);
        this.met3.setTextureSize(64, 32);
        this.met3.mirror = true;
        this.setRotation(this.met3, -0.4712389F, 0.0F, 0.0F);
        (this.met4 = new BodyPart(this, 44, 12)).addBox(-1.5F, -0.6F, 0.0F, 1, 1, 2);
        this.met4.setRotationPoint(0.0F, 6.0F, 5.0F);
        this.met4.setTextureSize(64, 32);
        this.met4.mirror = true;
        this.setRotation(this.met4, -0.2792527F, 0.1919862F, 0.0F);
        (this.met5 = new BodyPart(this, 44, 12)).addBox(0.5F, -0.6F, 0.0F, 1, 1, 2);
        this.met5.setRotationPoint(0.0F, 6.0F, 5.0F);
        this.met5.setTextureSize(64, 32);
        this.met5.mirror = true;
        this.setRotation(this.met5, -0.2792527F, -0.1919862F, 0.0F);
        (this.met6 = new BodyPart(this, 38, 8)).addBox(-0.5F, -0.5F, 1.0F, 1, 1, 5);
        this.met6.setRotationPoint(0.0F, 6.0F, 6.0F);
        this.met6.setTextureSize(64, 32);
        this.met6.mirror = true;
        this.setRotation(this.met6, 0.0698132F, 0.0F, 0.0F);
        (this.met7 = new BodyPart(this, 38, 9)).addBox(-0.5F, -0.5F, 1.0F, 1, 1, 5);
        this.met7.setRotationPoint(0.0F, 6.0F, 6.0F);
        this.met7.setTextureSize(64, 32);
        this.met7.mirror = true;
        this.setRotation(this.met7, -0.5759587F, 0.0F, 0.0F);
        (this.met8 = new BodyPart(this, 38, 9)).addBox(-0.5F, -0.5F, 1.0F, 1, 1, 5);
        this.met8.setRotationPoint(0.0F, 6.0F, 6.0F);
        this.met8.setTextureSize(64, 32);
        this.met8.mirror = true;
        this.setRotation(this.met8, -0.2268928F, 0.3316126F, 0.0F);
        (this.met14 = new BodyPart(this, 38, 9)).addBox(-0.5F, -0.5F, 1.0F, 1, 1, 5);
        this.met14.setRotationPoint(0.0F, 6.0F, 6.0F);
        this.met14.setTextureSize(64, 32);
        this.met14.mirror = true;
        this.setRotation(this.met14, -0.2268928F, -0.3316126F, 0.0F);
        (this.met9 = new BodyPart(this, 38, 10)).addBox(-1.0F, 0.0F, 2.5F, 1, 1, 4);
        this.met9.setRotationPoint(0.0F, 6.0F, 6.0F);
        this.met9.setTextureSize(64, 32);
        this.met9.mirror = true;
        this.setRotation(this.met9, 0.0F, 0.2617994F, 0.0F);
        (this.met10 = new BodyPart(this, 38, 9)).addBox(0.0F, 0.0F, 2.5F, 1, 1, 4);
        this.met10.setRotationPoint(0.0F, 6.0F, 6.0F);
        this.met10.setTextureSize(64, 32);
        this.met10.mirror = true;
        this.setRotation(this.met10, 0.0F, -0.2617994F, 0.0F);
        (this.met11 = new BodyPart(this, 38, 10)).addBox(0.0F, -1.0F, 2.5F, 1, 1, 4);
        this.met11.setRotationPoint(0.0F, 6.0F, 6.0F);
        this.met11.setTextureSize(64, 32);
        this.met11.mirror = true;
        this.setRotation(this.met11, -0.4886922F, -0.2617994F, 0.0F);
        (this.met12 = new BodyPart(this, 38, 9)).addBox(-1.0F, -1.0F, 2.5F, 1, 1, 4);
        this.met12.setRotationPoint(0.0F, 6.0F, 6.0F);
        this.met12.setTextureSize(64, 32);
        this.met12.mirror = true;
        this.setRotation(this.met12, -0.4886922F, 0.2617994F, 0.0F);
        (this.met13 = new BodyPart(this, 38, 10)).addBox(-0.5F, -0.5F, 3.0F, 1, 1, 4);
        this.met13.setRotationPoint(0.0F, 6.0F, 6.0F);
        this.met13.setTextureSize(64, 32);
        this.met13.mirror = true;
        this.setRotation(this.met13, -0.2617994F, 0.0F, 0.0F);
        this.setTexture("/mods/LavaChestPlate/textures/items/pet/vedma.png");
    }

    protected void smoothRotation(EntityPlayer entityPlayer, float n, ExtendedPlayer extendedPlayer)
    {
        super.smoothRotation(entityPlayer, n, extendedPlayer);
    }

    protected void processMotion(ModelPlayer modelPlayer, EntityPlayer entityPlayer, float n)
    {
        float n2 = MathHelper.sin(n * 0.16F) * 0.02F;
        float n3 = MathHelper.sin(n * 0.26F) * 0.05F;
        float cos = MathHelper.cos(n * 0.16F);
        GL11.glTranslatef(0.6F, -0.5F + n2, 0.3F);
        GL11.glRotatef(cos * 4.0F, 0.0F, 0.6F, 0.0F);
        this.head.rotationY = modelPlayer.bipedHead.rotateAngleY + 0.2F;
        this.head.rotationX = modelPlayer.bipedHead.rotateAngleX / 2.0F;
        this.head.rotationZ = modelPlayer.bipedHead.rotateAngleZ;
        this.head1.rotationY = modelPlayer.bipedHead.rotateAngleY + 0.2F;
        this.head1.rotationX = modelPlayer.bipedHead.rotateAngleX / 2.0F;
        this.head1.rotationZ = modelPlayer.bipedHead.rotateAngleZ;
        this.head2.rotationY = modelPlayer.bipedHead.rotateAngleY + 0.2F;
        this.head2.rotationX = modelPlayer.bipedHead.rotateAngleX / 2.0F;
        this.head2.rotationZ = modelPlayer.bipedHead.rotateAngleZ;
        this.head3.rotationY = modelPlayer.bipedHead.rotateAngleY + 0.2F;
        this.head3.rotationX = modelPlayer.bipedHead.rotateAngleX / 2.0F;
        this.head3.rotationZ = modelPlayer.bipedHead.rotateAngleZ;
        this.head4.rotationY = modelPlayer.bipedHead.rotateAngleY + 0.2F;
        this.head4.rotationX = modelPlayer.bipedHead.rotateAngleX / 2.0F;
        this.head4.rotationZ = modelPlayer.bipedHead.rotateAngleZ;
        this.head5.rotationY = modelPlayer.bipedHead.rotateAngleY + 0.2F;
        this.head5.rotationX = modelPlayer.bipedHead.rotateAngleX / 2.0F;
        this.head5.rotationZ = modelPlayer.bipedHead.rotateAngleZ;
        this.head6.rotationY = modelPlayer.bipedHead.rotateAngleY + 0.2F;
        this.head6.rotationX = modelPlayer.bipedHead.rotateAngleX / 2.0F;
        this.head6.rotationZ = modelPlayer.bipedHead.rotateAngleZ;
        this.rightleg.rotationX = modelPlayer.bipedLeftLeg.rotateAngleX / 2.5F + n3 * 3.0F;
        this.leftleg.rotationX = modelPlayer.bipedRightLeg.rotateAngleX / 2.5F + -n3 * 3.0F;
        this.leftleg.rotationZ = n2 * 5.0F;
        this.leftarm.rotationX = cos * 0.1F;
        this.leftarm.rotationY = -cos * n2 * 2.0F;
        this.leftarm.rotationZ = cos * n2 * 4.0F;
        float n4 = n2 * 10.0F;
        this.met1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.setRotation(this.met1, n4);
        this.met2.setRotationPoint(0.0F, 6.0F, 6.0F);
        this.setRotation(this.met2, n4);
        this.met3.setRotationPoint(0.0F, 6.0F, 6.0F);
        this.setRotation(this.met3, n4);
        this.met4.setRotationPoint(0.0F, 6.0F, 5.0F);
        this.setRotation(this.met4, n4);
        this.met5.setRotationPoint(0.0F, 6.0F, 5.0F);
        this.setRotation(this.met5, n4);
        this.met6.setRotationPoint(0.0F, 6.0F, 6.0F);
        this.setRotation(this.met6, n4);
        this.met7.setRotationPoint(0.0F, 6.0F, 6.0F);
        this.setRotation(this.met7, n4);
        this.met8.setRotationPoint(0.0F, 6.0F, 6.0F);
        this.setRotation(this.met8, n4);
        this.met9.setRotationPoint(0.0F, 6.0F, 6.0F);
        this.setRotation(this.met9, n4);
        this.met10.setRotationPoint(0.0F, 6.0F, 6.0F);
        this.setRotation(this.met10, n4);
        this.met11.setRotationPoint(0.0F, 6.0F, 6.0F);
        this.setRotation(this.met11, n4);
        this.met12.setRotationPoint(0.0F, 6.0F, 6.0F);
        this.setRotation(this.met12, n4);
        this.met13.setRotationPoint(0.0F, 6.0F, 6.0F);
        this.setRotation(this.met13, n4);
        this.met14.setRotationPoint(0.0F, 6.0F, 6.0F);
        this.setRotation(this.met14, n4);
    }

    protected void setRotation(ModelRenderer modelRenderer, float rotateAngleX, float rotateAngleY, float rotateAngleZ)
    {
        modelRenderer.rotateAngleX = rotateAngleX;
        modelRenderer.rotateAngleY = rotateAngleY;
        modelRenderer.rotateAngleZ = rotateAngleZ;
    }

    protected void setRotation(ModelRenderer modelRenderer, float n)
    {
        modelRenderer.rotationPointY += n;
    }

    public String getName()
    {
        return "Ведьма";
    }
}
