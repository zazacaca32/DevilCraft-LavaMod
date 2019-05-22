package net.minecraft.client.addon.tchestplate.items.renders.models.pets;

import net.minecraft.client.addon.tchestplate.items.renders.models.BasePet;
import net.minecraft.client.addon.tchestplate.items.renders.models.BodyPart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.src.ModelPlayer;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

public class mikePet extends BasePet
{
    BodyPart head;
    BodyPart head2;
    BodyPart head3;
    BodyPart head4;
    BodyPart head5;
    BodyPart head6;
    BodyPart head7;
    BodyPart head8;
    BodyPart head9;
    BodyPart rightarm;
    BodyPart rightarm2;
    BodyPart leftarm;
    BodyPart leftarm2;
    BodyPart rightleg;
    BodyPart rightleg2;
    BodyPart leftleg;
    BodyPart leftleg2;
    private static final String tex = "/mods/LavaChestPlate/textures/items/pet/mike.png";

    public mikePet(boolean b)
    {
        super.textureWidth = 64;
        super.textureHeight = 32;
        (this.head = new BodyPart(this, 1, 4)).addBox(-3.0F, -8.0F, -3.0F, 6, 7, 6);
        this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.head.setTextureSize(64, 32);
        this.head.mirror = true;
        this.setRotation(this.head, 0.0F, 0.0F, 0.0F);
        (this.head2 = new BodyPart(this, 8, 15)).addBox(-2.5F, -7.5F, -3.5F, 5, 6, 7);
        this.head2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.head2.setTextureSize(64, 32);
        this.head2.mirror = true;
        this.setRotation(this.head2, 0.0F, 0.0F, 0.0F);
        (this.head3 = new BodyPart(this, 0, 9)).addBox(-2.0F, -7.0F, -4.0F, 4, 5, 8);
        this.head3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.head3.setTextureSize(64, 32);
        this.head3.mirror = true;
        this.setRotation(this.head3, 0.0F, 0.0F, 0.0F);
        (this.head4 = new BodyPart(this, 8, 3)).addBox(-2.0F, -9.0F, -2.0F, 4, 9, 4);
        this.head4.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.head4.setTextureSize(64, 32);
        this.head4.mirror = true;
        this.setRotation(this.head4, 0.0F, 0.0F, 0.0F);
        (this.head5 = new BodyPart(this, 4, 4)).addBox(-4.0F, -7.0F, -2.0F, 8, 5, 4);
        this.head5.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.head5.setTextureSize(64, 32);
        this.head5.mirror = true;
        this.setRotation(this.head5, 0.0F, 0.0F, 0.0F);
        (this.head6 = new BodyPart(this, 4, 3)).addBox(-3.5F, -7.5F, -2.5F, 7, 6, 5);
        this.head6.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.head6.setTextureSize(64, 32);
        this.head6.mirror = true;
        this.setRotation(this.head6, 0.0F, 0.0F, 0.0F);
        (this.head7 = new BodyPart(this, 4, 3)).addBox(-2.5F, -8.5F, -2.5F, 5, 8, 5);
        this.head7.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.head7.setTextureSize(64, 32);
        this.head7.mirror = true;
        this.setRotation(this.head7, 0.0F, 0.0F, 0.0F);
        (this.head8 = new BodyPart(this, 0, 0)).addBox(2.9F, -8.0F, -1.0F, 2, 2, 1);
        this.head8.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.head8.setTextureSize(64, 32);
        this.head8.mirror = true;
        this.setRotation(this.head8, 0.0F, 0.0F, -0.2268928F);
        (this.head9 = new BodyPart(this, 0, 0)).addBox(-4.9F, -8.0F, -1.0F, 2, 2, 1);
        this.head9.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.head9.setTextureSize(64, 32);
        this.head9.mirror = true;
        this.setRotation(this.head9, 0.0F, 0.0F, 0.2268928F);
        (this.rightarm = new BodyPart(this, 19, 0)).addBox(-1.0F, -0.5F, -0.5F, 1, 7, 1);
        this.rightarm.setRotationPoint(-4.0F, -5.0F, 0.0F);
        this.rightarm.setTextureSize(64, 32);
        this.rightarm.mirror = true;
        this.setRotation(this.rightarm, -0.0349066F, 0.0F, 0.0F);
        (this.rightarm2 = new BodyPart(this, 2, 9)).addBox(-1.0F, 6.0F, 1.0F, 2, 2, 1);
        this.rightarm2.setRotationPoint(-4.0F, -5.0F, 0.0F);
        this.rightarm2.setTextureSize(64, 32);
        this.rightarm2.mirror = true;
        this.setRotation(this.rightarm2, -0.2792527F, 0.0F, 0.0F);
        (this.leftarm = new BodyPart(this, 19, 0)).addBox(0.0F, -0.5F, -0.5F, 1, 7, 1);
        this.leftarm.setRotationPoint(4.0F, -5.0F, 0.0F);
        this.leftarm.setTextureSize(64, 32);
        this.leftarm.mirror = true;
        this.setRotation(this.leftarm, -0.1396263F, 0.0F, 0.0F);
        (this.leftarm2 = new BodyPart(this, 2, 9)).addBox(-1.0F, 6.0F, 0.5F, 2, 2, 1);
        this.leftarm2.setRotationPoint(4.0F, -5.0F, 0.0F);
        this.leftarm2.setTextureSize(64, 32);
        this.leftarm2.mirror = true;
        this.setRotation(this.leftarm2, -0.296706F, 0.0F, 0.0F);
        (this.rightleg = new BodyPart(this, 20, 0)).addBox(-1.0F, 0.0F, -0.5F, 1, 5, 1);
        this.rightleg.setRotationPoint(-1.0F, 0.0F, 0.0F);
        this.rightleg.setTextureSize(64, 32);
        this.rightleg.mirror = true;
        this.setRotation(this.rightleg, 0.0F, 0.0F, 0.0F);
        (this.rightleg2 = new BodyPart(this, 11, 0)).addBox(-1.5F, 5.0F, -2.0F, 2, 1, 3);
        this.rightleg2.setRotationPoint(-1.0F, 0.0F, 0.0F);
        this.rightleg2.setTextureSize(64, 32);
        this.rightleg2.mirror = true;
        this.setRotation(this.rightleg2, 0.0F, 0.0F, 0.0F);
        (this.leftleg = new BodyPart(this, 20, 1)).addBox(0.0F, 0.0F, -0.5F, 1, 5, 1);
        this.leftleg.setRotationPoint(1.0F, 0.0F, 0.0F);
        this.leftleg.setTextureSize(64, 32);
        this.leftleg.mirror = true;
        this.setRotation(this.leftleg, 0.0F, 0.0F, 0.0F);
        (this.leftleg2 = new BodyPart(this, 11, 0)).addBox(-0.5F, 5.0F, -2.0F, 2, 1, 3);
        this.leftleg2.setRotationPoint(1.0F, 0.0F, 0.0F);
        this.leftleg2.setTextureSize(64, 32);
        this.leftleg2.mirror = true;
        this.setRotation(this.leftleg2, 0.0F, 0.0F, 0.0F);
        this.setTexture("/mods/LavaChestPlate/textures/items/pet/mike.png");
    }

    protected void processMotion(ModelPlayer modelPlayer, EntityPlayer entityPlayer, float n)
    {
        float n2 = MathHelper.cos(n * 0.16F) * 0.02F;
        float n3 = MathHelper.cos(n * 0.26F) * 0.05F;
        float func_76134_b = MathHelper.sin(n * 0.16F);
        GL11.glTranslatef(0.6F, -0.5F + n2, 0.3F);
        GL11.glRotatef(func_76134_b * 4.0F, 0.0F, 0.6F, 0.0F);
        this.rightleg.rotationX = modelPlayer.bipedLeftLeg.rotateAngleX / 2.5F + n3 * 3.0F;
        this.leftleg.rotationX = modelPlayer.bipedRightLeg.rotateAngleX / 2.5F + -n3 * 3.0F;
        this.leftleg.rotationZ = n2 * 5.0F;
        this.leftarm.rotationX = func_76134_b * 0.1F;
        this.leftarm.rotationY = -func_76134_b * n2 * 2.0F;
        this.leftarm.rotationZ = func_76134_b * n2 * 4.0F;
        this.rightarm.rotationX = -this.leftarm.rotationX;
        this.rightarm.rotationY = -this.leftarm.rotationY;
        this.rightarm.rotationZ = this.leftarm.rotationZ;
        this.head.setRotationChield(modelPlayer.bipedBody);
        this.head2.setRotationChield(modelPlayer.bipedBody);
        this.head3.setRotationChield(this.head2);
        this.head4.setRotationChield(this.head2);
        this.head5.setRotationChield(this.head2);
        this.head6.setRotationChield(this.head2);
        this.head7.setRotationChield(this.head2);
        this.head8.setRotationChield(this.head2);
        this.head9.setRotationChield(this.head2);
        this.rightarm2.setRotationChield(this.rightarm);
        this.leftarm2.setRotationChield(this.leftarm);
        this.rightleg2.setRotationChield(this.rightleg);
        this.leftleg2.setRotationChield(this.leftleg);
    }

    public String getName()
    {
        return "Демон";
    }
}
