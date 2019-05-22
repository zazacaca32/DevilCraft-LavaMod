package net.minecraft.client.addon.tchestplate.items.renders.models;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.src.ModelPlayer;

public class demonicPet extends BasePet
{
    protected BodyPart body;
    protected BodyPart leftLeg;
    protected BodyPart rightLeg;
    protected BodyPart head;
    protected BodyPart leftHand;
    protected BodyPart rightHand;
    protected BodyPart Shape7;
    protected BodyPart Shape8;
    protected BodyPart Shape9;
    protected BodyPart Shape10;
    private static final String tex = "/mods/provider/ual/demonic.png";

    public demonicPet(boolean b)
    {
        super.textureWidth = 64;
        super.textureHeight = 32;
        this.body = new BodyPart(this, 32, 0);
        this.body.addBox(-2.0F, 0.0F, 0.0F, 4, 5, 2);
        this.body.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.body.setTextureSize(64, 32);
        this.body.mirror = true;
        this.setRotation(this.body, 0.0F, 0.0F, 0.0F);
        this.leftLeg = new BodyPart(this, 8, 16);
        this.leftLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 3, 2);
        this.leftLeg.setRotationPoint(1.0F, 5.0F, 1.0F);
        this.leftLeg.setTextureSize(64, 32);
        this.leftLeg.mirror = true;
        this.setRotation(this.leftLeg, 0.0F, 0.0F, 0.0F);
        this.rightLeg = new BodyPart(this, 0, 16);
        this.rightLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 3, 2);
        this.rightLeg.setRotationPoint(-1.0F, 5.0F, 1.0F);
        this.rightLeg.setTextureSize(64, 32);
        this.rightLeg.mirror = true;
        this.setRotation(this.rightLeg, 0.0F, 0.0F, 0.0F);
        this.head = new BodyPart(this, 0, 0);
        this.head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8);
        this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.head.setTextureSize(64, 32);
        this.head.mirror = true;
        this.setRotation(this.head, 0.0F, 0.0F, 0.0F);
        this.leftHand = new BodyPart(this, 46, 0);
        this.leftHand.addBox(0.0F, -0.8F, -0.8F, 2, 4, 2);
        this.leftHand.setRotationPoint(2.0F, 1.0F, 1.0F);
        this.leftHand.setTextureSize(64, 32);
        this.leftHand.mirror = true;
        this.setRotation(this.leftHand, 0.0F, 0.0F, 0.0F);
        this.rightHand = new BodyPart(this, 54, 0);
        this.rightHand.addBox(-2.0F, -0.8F, -0.8F, 2, 4, 2);
        this.rightHand.setRotationPoint(-2.0F, 1.0F, 1.0F);
        this.rightHand.setTextureSize(64, 32);
        this.rightHand.mirror = true;
        this.setRotation(this.rightHand, 0.0F, 0.0F, 0.0F);
        this.Shape7 = new BodyPart(this, 0, 22);
        this.Shape7.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1);
        this.Shape7.setRotationPoint(-3.0F, -9.5F, -2.0F);
        this.Shape7.setTextureSize(64, 32);
        this.Shape7.mirror = true;
        this.setRotation(this.Shape7, 0.2268928F, 0.0F, -0.1745329F);
        this.Shape8 = new BodyPart(this, 0, 22);
        this.Shape8.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1);
        this.Shape8.setRotationPoint(2.0F, -9.5F, -2.0F);
        this.Shape8.setTextureSize(64, 32);
        this.Shape8.mirror = true;
        this.setRotation(this.Shape8, 0.2268928F, 0.0F, 0.1745329F);
        this.Shape9 = new BodyPart(this, 0, 28);
        this.Shape9.addBox(0.0F, 0.0F, 0.0F, 1, 1, 3);
        this.Shape9.setRotationPoint(2.5F, -10.5F, -4.0F);
        this.Shape9.setTextureSize(64, 32);
        this.Shape9.mirror = true;
        this.setRotation(this.Shape9, -0.296706F, -0.1745329F, 0.0F);
        this.Shape10 = new BodyPart(this, 0, 28);
        this.Shape10.addBox(0.0F, 0.0F, 0.0F, 1, 1, 3);
        this.Shape10.setRotationPoint(-3.5F, -10.5F, -4.0F);
        this.Shape10.setTextureSize(64, 32);
        this.Shape10.mirror = true;
        this.setRotation(this.Shape10, -0.2094395F, 0.1745329F, 0.0F);
        this.setTexture("/mods/provider/ual/demonic.png");
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.body.render(f5);
        this.leftLeg.render(f5);
        this.rightLeg.render(f5);
        this.head.render(f5);
        this.leftHand.render(f5);
        this.rightHand.render(f5);
    }

    protected void processMotion(ModelPlayer modelPlayer, EntityPlayer player, float time)
    {
    }

    public String getName()
    {
        return "Демон";
    }
}
