package net.minecraft.client.addon.tchestplate.items.renders.models.pets;

import net.minecraft.client.addon.tchestplate.items.renders.models.BasePet;
import net.minecraft.client.addon.tchestplate.items.renders.models.BodyPart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.src.ModelPlayer;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

public class ZombiePet extends BasePet
{
    protected BodyPart head;
    protected BodyPart rightHand;
    protected BodyPart leftHand;
    protected BodyPart body;
    protected BodyPart rightLeg;
    protected BodyPart leftLeg;
    private static final String tex = "/mods/LavaChestPlate/textures/items/pet/zombie.png";

    public ZombiePet(boolean b)
    {
        (this.body = new BodyPart(this, 32, 0)).addBox(-2.0F, 0.0F, 0.0F, 4, 5, 2);
        this.body.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.body.setTextureSize(64, 32);
        this.body.mirror = true;
        (this.leftLeg = new BodyPart(this, 8, 22)).addBox(-1.0F, 0.0F, -1.0F, 2, 3, 2);
        this.leftLeg.setRotationPoint(1.0F, 5.0F, 1.0F);
        this.leftLeg.setTextureSize(64, 32);
        this.leftLeg.mirror = true;
        (this.rightLeg = new BodyPart(this, 0, 22)).addBox(-1.0F, 0.0F, -1.0F, 2, 3, 2);
        this.rightLeg.setRotationPoint(-1.0F, 5.0F, 1.0F);
        this.rightLeg.setTextureSize(64, 32);
        this.rightLeg.mirror = true;
        (this.head = new BodyPart(this, 0, 0)).addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8);
        this.head.setRotationPoint(0.0F, 0.0F, 1.0F);
        this.head.setTextureSize(64, 32);
        this.head.mirror = true;
        (this.leftHand = new BodyPart(this, 46, 0)).addBox(0.0F, -0.8F, -0.8F, 2, 4, 2);
        this.leftHand.setRotationPoint(2.0F, 1.0F, 1.0F);
        this.leftHand.setTextureSize(64, 32);
        this.leftHand.mirror = true;
        this.setRotation(this.leftHand, -1.466077F, 0.0F, 0.0F);
        (this.rightHand = new BodyPart(this, 46, 0)).addBox(-2.0F, -0.8F, -0.8F, 2, 4, 2);
        this.rightHand.setRotationPoint(-2.0F, 1.0F, 1.0F);
        this.rightHand.setTextureSize(64, 32);
        this.rightHand.mirror = true;
        this.setRotation(this.rightHand, -1.466077F, 0.0F, 0.0F);
        this.setTexture("/mods/LavaChestPlate/textures/items/pet/zombie.png");
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
        this.rightLeg.rotationX = modelPlayer.bipedLeftLeg.rotateAngleX / 2.5F + n3 * 3.0F;
        this.leftLeg.rotationX = modelPlayer.bipedRightLeg.rotateAngleX / 2.5F + -n3 * 3.0F;
        this.leftLeg.rotationZ = n2 * 5.0F;
        this.leftHand.rotationX = cos * 0.1F;
        this.leftHand.rotationY = -cos * n2 * 2.0F;
        this.leftHand.rotationZ = cos * n2 * 4.0F;
        this.rightHand.rotationX = -this.leftHand.rotationX;
        this.rightHand.rotationY = -this.leftHand.rotationY;
        this.rightHand.rotationZ = this.leftHand.rotationZ;
    }

    public String getName()
    {
        return "Зомби";
    }
}
