package net.minecraft.client.addon.tchestplate.items.renders.models.pets;

import net.minecraft.client.addon.tchestplate.items.renders.models.BasePet;
import net.minecraft.client.addon.tchestplate.items.renders.models.BodyPart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.src.ModelPlayer;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

public class pikachuPet extends BasePet
{
    protected BodyPart head;
    protected BodyPart rightarm;
    protected BodyPart leftarm;
    protected BodyPart body;
    protected BodyPart rightleg;
    protected BodyPart leftleg;
    protected BodyPart Shape1;
    protected BodyPart Shape2;
    protected BodyPart Shape3;
    protected BodyPart Shape4;
    protected BodyPart Shape5;
    private static final String tex = "/mods/LavaChestPlate/textures/items/pet/pikachu.png";

    public pikachuPet(boolean b)
    {
        (this.head = new BodyPart(this, 0, 0)).addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8);
        this.head.setRotationPoint(0.0F, 0.0F, 1.0F);
        this.head.setTextureSize(64, 32);
        this.head.mirror = true;
        this.setRotation(this.head, 0.0F, 0.0F, 0.0F);
        (this.body = new BodyPart(this, 32, 0)).addBox(-2.0F, 0.0F, 0.0F, 4, 6, 2);
        this.body.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.body.setTextureSize(64, 32);
        this.body.mirror = true;
        this.setRotation(this.body, 0.0F, 0.0F, 0.0F);
        (this.rightarm = new BodyPart(this, 46, 0)).addBox(-2.0F, -0.8F, -0.8F, 2, 3, 2);
        this.rightarm.setRotationPoint(-2.0F, 1.0F, 1.0F);
        this.rightarm.setTextureSize(64, 32);
        this.rightarm.mirror = true;
        this.setRotation(this.rightarm, -1.055924F, -0.2617994F, 0.0F);
        (this.leftarm = new BodyPart(this, 46, 6)).addBox(0.0F, -0.8F, -0.8F, 2, 3, 2);
        this.leftarm.setRotationPoint(2.0F, 1.0F, 1.0F);
        this.leftarm.setTextureSize(64, 32);
        this.leftarm.mirror = true;
        this.setRotation(this.leftarm, -1.055924F, 0.2617994F, 0.0F);
        (this.rightleg = new BodyPart(this, 0, 22)).addBox(-1.0F, 0.0F, -2.0F, 2, 2, 3);
        this.rightleg.setRotationPoint(-1.0F, 6.0F, 1.0F);
        this.rightleg.setTextureSize(64, 32);
        this.rightleg.mirror = true;
        this.setRotation(this.rightleg, 0.0F, 0.0F, 0.0F);
        (this.leftleg = new BodyPart(this, 0, 22)).addBox(-1.0F, 0.0F, -2.0F, 2, 2, 3);
        this.leftleg.setRotationPoint(1.0F, 6.0F, 1.0F);
        this.leftleg.setTextureSize(64, 32);
        this.leftleg.mirror = true;
        this.setRotation(this.leftleg, 0.0F, 0.0F, 0.0F);
        (this.Shape1 = new BodyPart(this, 0, 0)).addBox(2.0F, -11.0F, -2.0F, 1, 5, 2);
        this.Shape1.setRotationPoint(0.0F, 0.0F, 1.0F);
        this.Shape1.setTextureSize(64, 32);
        this.Shape1.mirror = true;
        this.setRotation(this.Shape1, -0.4363323F, 0.3839724F, 0.1745329F);
        (this.Shape2 = new BodyPart(this, 0, 0)).addBox(-3.0F, -11.0F, -2.0F, 1, 5, 2);
        this.Shape2.setRotationPoint(0.0F, 0.0F, 1.0F);
        this.Shape2.setTextureSize(64, 32);
        this.Shape2.mirror = true;
        this.setRotation(this.Shape2, -0.4363323F, -0.3839724F, -0.1745329F);
        (this.Shape3 = new BodyPart(this, 0, 18)).addBox(-0.5F, 3.5F, 1.0F, 1, 1, 2);
        this.Shape3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape3.setTextureSize(64, 32);
        this.Shape3.mirror = true;
        this.setRotation(this.Shape3, 0.1396263F, 0.0F, 0.0F);
        (this.Shape4 = new BodyPart(this, 3, 16)).addBox(-0.5F, 1.5F, 4.0F, 1, 2, 4);
        this.Shape4.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape4.setTextureSize(64, 32);
        this.Shape4.mirror = true;
        this.setRotation(this.Shape4, 0.1396263F, 0.0F, 0.0F);
        (this.Shape5 = new BodyPart(this, 0, 17)).addBox(-0.5F, 2.5F, 3.0F, 1, 2, 1);
        this.Shape5.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape5.setTextureSize(64, 32);
        this.Shape5.mirror = true;
        this.setRotation(this.Shape5, 0.1396263F, 0.0F, 0.0F);
        this.setTexture("/mods/LavaChestPlate/textures/items/pet/pikachu.png");
    }

    protected void processMotion(ModelPlayer modelPlayer, EntityPlayer entityPlayer, float n)
    {
        float n2 = MathHelper.sin(n * 0.16F) * 0.02F;
        float n3 = MathHelper.sin(n * 0.26F) * 0.05F;
        float func_76134_b = MathHelper.cos(n * 0.16F);
        GL11.glTranslatef(0.6F, -0.5F + n2, 0.3F);
        GL11.glRotatef(func_76134_b * 4.0F, 0.0F, 0.6F, 0.0F);
        this.head.rotationY = modelPlayer.bipedHead.rotateAngleY + 0.2F;
        this.head.rotationX = modelPlayer.bipedHead.rotateAngleX / 2.0F;
        this.head.rotationZ = modelPlayer.bipedHead.rotateAngleZ;
        this.rightleg.rotationX = modelPlayer.bipedLeftLeg.rotateAngleX / 2.5F + n3 * 3.0F;
        this.leftleg.rotationX = modelPlayer.bipedRightLeg.rotateAngleX / 2.5F + -n3 * 3.0F;
        this.leftleg.rotationZ = n2 * 5.0F;
        this.leftarm.rotationX = func_76134_b * 0.1F;
        this.leftarm.rotationY = -func_76134_b * n2 * 2.0F;
        this.leftarm.rotationZ = func_76134_b * n2 * 4.0F;
        this.rightarm.rotationX = -this.leftarm.rotationX;
        this.rightarm.rotationY = -this.leftarm.rotationY;
        this.rightarm.rotationZ = this.leftarm.rotationZ;
        this.Shape1.setRotationChield(this.head);
        this.Shape2.setRotationChield(this.head);
        this.Shape3.setRotationChield(this.body);
        this.Shape4.setRotationChield(this.body);
        this.Shape5.setRotationChield(this.body);
        BodyPart shape3 = this.Shape3;
        shape3.rotationY += func_76134_b * n2 * 10.0F;
        BodyPart shape4 = this.Shape3;
        shape4.rotationX += func_76134_b * 0.1F;
        BodyPart shape5 = this.Shape4;
        shape5.rotationY += func_76134_b * n2 * 10.0F;
        BodyPart shape6 = this.Shape4;
        shape6.rotationX += func_76134_b * 0.1F;
        BodyPart shape7 = this.Shape5;
        shape7.rotationY += func_76134_b * n2 * 10.0F;
        BodyPart shape8 = this.Shape5;
        shape8.rotationX += func_76134_b * 0.1F;
    }

    public String getName()
    {
        return "Пикачу";
    }
}
