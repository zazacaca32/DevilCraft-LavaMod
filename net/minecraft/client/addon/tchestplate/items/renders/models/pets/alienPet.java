package net.minecraft.client.addon.tchestplate.items.renders.models.pets;

import net.minecraft.client.addon.tchestplate.items.renders.models.BasePet;
import net.minecraft.client.addon.tchestplate.items.renders.models.BodyPart;
import net.minecraft.client.addon.tchestplate.player.ExtendedPlayer;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.src.ModelPlayer;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

public class alienPet extends BasePet
{
    BodyPart head;
    BodyPart head1;
    BodyPart head3;
    BodyPart body;
    BodyPart body1;
    BodyPart body2;
    BodyPart rightarm;
    BodyPart leftarm;
    BodyPart rightleg;
    BodyPart leftleg;
    BodyPart hvost;
    BodyPart hvost1;
    BodyPart hvost2;
    BodyPart hvost3;
    private static final String tex = "/mods/LavaChestPlate/textures/items/pet/alien.png";
    boolean flagInvise = false;

    public alienPet(boolean b)
    {
        super.textureWidth = 64;
        super.textureHeight = 32;
        (this.head = new BodyPart(this, 36, 16)).addBox(-2.0F, -6.0F, -6.0F, 4, 6, 10);
        this.head.setRotationPoint(0.0F, 0.0F, 1.0F);
        this.head.setTextureSize(64, 32);
        this.head.mirror = true;
        this.setRotation(this.head, 0.2094395F, 0.0F, 0.0F);
        (this.head1 = new BodyPart(this, 54, 22)).addBox(-1.0F, 0.0F, -6.0F, 2, 1, 3);
        this.head1.setRotationPoint(0.0F, 0.0F, 1.0F);
        this.head1.setTextureSize(64, 32);
        this.head1.mirror = true;
        this.setRotation(this.head1, 0.2094395F, 0.0F, 0.0F);
        (this.head3 = new BodyPart(this, 20, 25)).addBox(-2.0F, -6.0F, 4.0F, 4, 3, 4);
        this.head3.setRotationPoint(0.0F, 0.0F, 1.0F);
        this.head3.setTextureSize(64, 32);
        this.head3.mirror = true;
        this.setRotation(this.head3, 0.2094395F, 0.0F, 0.0F);
        (this.body = new BodyPart(this, 24, 20)).addBox(-2.0F, 0.0F, 0.0F, 4, 3, 2);
        this.body.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.body.setTextureSize(64, 32);
        this.body.mirror = true;
        this.setRotation(this.body, 0.0F, 0.0F, 0.0F);
        (this.body1 = new BodyPart(this, 0, 1)).addBox(-0.5F, 1.0F, 1.0F, 1, 1, 5);
        this.body1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.body1.setTextureSize(64, 32);
        this.body1.mirror = true;
        this.setRotation(this.body1, 0.2443461F, 0.0F, 0.0F);
        (this.body2 = new BodyPart(this, 0, 0)).addBox(-0.5F, 1.5F, 1.0F, 1, 1, 4);
        this.body2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.body2.setTextureSize(64, 32);
        this.body2.mirror = true;
        this.setRotation(this.body2, 0.0349066F, 0.0F, 0.0F);
        (this.rightarm = new BodyPart(this, 8, 18)).addBox(-3.0F, -0.8F, -0.8F, 2, 5, 2);
        this.rightarm.setRotationPoint(-1.0F, 1.0F, 1.0F);
        this.rightarm.setTextureSize(64, 32);
        this.rightarm.mirror = true;
        this.setRotation(this.rightarm, -0.3490659F, -0.2617994F, 0.2792527F);
        (this.leftarm = new BodyPart(this, 8, 25)).addBox(0.0F, -0.8F, -0.8F, 2, 5, 2);
        this.leftarm.setRotationPoint(2.0F, 1.0F, 1.0F);
        this.leftarm.setTextureSize(64, 32);
        this.leftarm.mirror = true;
        this.setRotation(this.leftarm, -0.3490659F, 0.2617994F, -0.2792527F);
        (this.rightleg = new BodyPart(this, 0, 25)).addBox(-1.0F, 0.0F, -1.0F, 2, 5, 2);
        this.rightleg.setRotationPoint(-1.3F, 3.0F, 1.0F);
        this.rightleg.setTextureSize(64, 32);
        this.rightleg.mirror = true;
        this.setRotation(this.rightleg, 0.0F, 0.0F, 0.0F);
        (this.leftleg = new BodyPart(this, 0, 25)).addBox(-1.0F, 0.0F, -1.0F, 2, 5, 2);
        this.leftleg.setRotationPoint(1.3F, 3.0F, 1.0F);
        this.leftleg.setTextureSize(64, 32);
        this.leftleg.mirror = true;
        this.setRotation(this.leftleg, 0.0F, 0.0F, 0.0F);
        (this.hvost = new BodyPart(this, 0, 0)).addBox(-1.0F, -1.0F, 0.0F, 2, 2, 4);
        this.hvost.setRotationPoint(0.0F, 3.0F, 2.0F);
        this.hvost.setTextureSize(64, 32);
        this.hvost.mirror = true;
        this.setRotation(this.hvost, -0.3316126F, 0.0F, 0.0F);
        (this.hvost1 = new BodyPart(this, 0, 0)).addBox(-0.5F, -2.0F, 3.5F, 1, 2, 3);
        this.hvost1.setRotationPoint(0.0F, 3.0F, 2.0F);
        this.hvost1.setTextureSize(64, 32);
        this.hvost1.mirror = true;
        this.setRotation(this.hvost1, -0.5934119F, 0.0F, 0.0F);
        (this.hvost2 = new BodyPart(this, 3, 0)).addBox(-0.5F, -2.3F, 6.3F, 1, 1, 5);
        this.hvost2.setRotationPoint(0.0F, 3.0F, 2.0F);
        this.hvost2.setTextureSize(64, 32);
        this.hvost2.mirror = true;
        this.setRotation(this.hvost2, -0.6457718F, 0.0F, 0.0F);
        (this.hvost3 = new BodyPart(this, 3, 0)).addBox(-0.5F, 0.2F, 6.4F, 1, 1, 5);
        this.hvost3.setRotationPoint(0.0F, 3.0F, 2.0F);
        this.hvost3.setTextureSize(64, 32);
        this.hvost3.mirror = true;
        this.setRotation(this.hvost3, -0.3839724F, 0.0F, 0.0F);
        this.setTexture("/mods/LavaChestPlate/textures/items/pet/alien.png");
    }

    protected void renderModels()
    {
        if (this.flagInvise)
        {
            this.setTexture("/mods/LavaChestPlate/textures/items/renders/predatorBlack.png");
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
        }
        else
        {
            this.setTexture("/mods/LavaChestPlate/textures/items/pet/alien.png");
        }

        super.renderModels();

        if (this.flagInvise)
        {
            GL11.glDisable(GL11.GL_BLEND);
        }
    }

    protected void smoothRotation(EntityPlayer player, float time, ExtendedPlayer pi)
    {
        if (pi.PredatorMode == 50)
        {
            this.flagInvise = true;
        }
        else
        {
            this.flagInvise = false;
        }

        super.smoothRotation(player, time, pi);
    }

    protected void processMotion(ModelPlayer modelPlayer, EntityPlayer player, float time)
    {
        float sin = MathHelper.sin(time * 0.16F) * 0.02F;
        float sin2 = MathHelper.sin(time * 0.26F) * 0.05F;
        float cos = MathHelper.cos(time * 0.16F);
        GL11.glTranslatef(0.6F, -0.5F + sin, 0.3F);
        GL11.glRotatef(cos * 4.0F, 0.0F, 0.6F, 0.0F);
        this.head.rotationY = modelPlayer.bipedHead.rotateAngleY + 0.2F;
        this.head.rotationX = modelPlayer.bipedHead.rotateAngleX / 2.0F;
        this.head.rotationZ = modelPlayer.bipedHead.rotateAngleZ;
        this.head1.rotationY = this.head.rotationY;
        this.head1.rotationX = this.head.rotationX;
        this.head1.rotationZ = this.head.rotationZ;
        this.head3.rotationY = this.head.rotationY;
        this.head3.rotationX = this.head.rotationX;
        this.head3.rotationZ = this.head.rotationZ;
        this.rightleg.rotationX = modelPlayer.bipedRightLeg.rotateAngleX / 2.5F + sin2 * 3.0F;
        this.leftleg.rotationX = modelPlayer.bipedLeftLeg.rotateAngleX / 2.5F + -sin2 * 3.0F;
        this.leftleg.rotationZ = sin * 5.0F;
        this.leftarm.rotationX = cos * 0.1F;
        this.leftarm.rotationY = -cos * sin * 2.0F;
        this.leftarm.rotationZ = cos * sin * 4.0F;
        this.rightarm.rotationX = -this.leftarm.rotationX;
        this.rightarm.rotationY = -this.leftarm.rotationY;
        this.rightarm.rotationZ = this.leftarm.rotationZ;
        this.hvost1.setRotationChield((ModelRenderer)this.hvost);
        this.hvost2.setRotationChield((ModelRenderer)this.hvost);
        this.hvost3.setRotationChield((ModelRenderer)this.hvost);
        BodyPart hvost9 = this.hvost;
        BodyPart hvost = this.hvost;
        hvost9.rotationY += sin * 0.7F;
        BodyPart hvost10 = this.hvost1;
        BodyPart hvost2 = this.hvost1;
        hvost10.rotationY += sin * 4.6F;
        BodyPart hvost11 = this.hvost2;
        BodyPart hvost3 = this.hvost2;
        hvost11.rotationY += sin * 8.6F;
        BodyPart hvost12 = this.hvost3;
        BodyPart hvost4 = this.hvost3;
        hvost12.rotationY += sin * 8.6F;
        this.hvost.rotationX = 0.0F;
        BodyPart hvost13 = this.hvost;
        BodyPart hvost5 = this.hvost;
        hvost13.rotationX -= cos * 0.21F;
        this.hvost1.rotationX = 0.0F;
        BodyPart hvost14 = this.hvost1;
        BodyPart hvost6 = this.hvost1;
        hvost14.rotationX -= cos * 0.21F;
        this.hvost2.rotationX = 0.0F;
        BodyPart hvost15 = this.hvost2;
        BodyPart hvost7 = this.hvost2;
        hvost15.rotationX -= cos * 0.21F;
        this.hvost3.rotationX = 0.0F;
        BodyPart hvost16 = this.hvost3;
        BodyPart hvost8 = this.hvost3;
        hvost16.rotationX -= cos * 0.21F;
    }

    public String getName()
    {
        return "Чужои";
    }
}
