package net.minecraft.client.addon.tchestplate;
import java.util.HashMap;
import java.util.Map;

import org.lwjgl.opengl.GL11;

import assets.db;
import assets.dc;
import assets.dh;
import assets.dl;
import assets.zza;
import assets.zzb;
import assets.zzc;
import assets.zzd;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.addon.tchestplate.accesories.models.ModelBant;
import net.minecraft.client.addon.tchestplate.accesories.models.ModelBipedAccesories;
import net.minecraft.client.addon.tchestplate.accesories.models.ModelFineryBoots;
import net.minecraft.client.addon.tchestplate.accesories.models.ModelFineryBootsM;
import net.minecraft.client.addon.tchestplate.accesories.models.ModelFineryChest;
import net.minecraft.client.addon.tchestplate.accesories.models.ModelFineryChestM;
import net.minecraft.client.addon.tchestplate.accesories.models.ModelFineryHelmet;
import net.minecraft.client.addon.tchestplate.accesories.models.ModelFineryHelmetM;
import net.minecraft.client.addon.tchestplate.accesories.models.ModelFineryLeggens;
import net.minecraft.client.addon.tchestplate.accesories.models.ModelFineryLeggensM;
import net.minecraft.client.addon.tchestplate.accesories.models.ModelGirlHair;
import net.minecraft.client.addon.tchestplate.accesories.models.ModelHellowen0;
import net.minecraft.client.addon.tchestplate.accesories.models.ModelHellowen1;
import net.minecraft.client.addon.tchestplate.accesories.models.ModelHellowen2;
import net.minecraft.client.addon.tchestplate.accesories.models.ModelHellowen3;
import net.minecraft.client.addon.tchestplate.accesories.models.ModelLavaArmy;
import net.minecraft.client.addon.tchestplate.accesories.models.ModelLavaVedma;
import net.minecraft.client.addon.tchestplate.accesories.models.ModelLeprikonMask;
import net.minecraft.client.addon.tchestplate.accesories.models.ModelMaskaSanta;
import net.minecraft.client.addon.tchestplate.accesories.models.ModelSanta;
import net.minecraft.client.addon.tchestplate.accesories.models.ModelShcool;
import net.minecraft.client.addon.tchestplate.accesories.models.ModelShut;
import net.minecraft.client.addon.tchestplate.accesories.models.ModelSkull;
import net.minecraft.client.addon.tchestplate.accesories.models.ModelVolk;
import net.minecraft.client.addon.tchestplate.aoc.ModelUham;
import net.minecraft.client.addon.tchestplate.armors.items.LavaChestPlateItem;
import net.minecraft.client.addon.tchestplate.armors.models.ModelAngelBoots;
import net.minecraft.client.addon.tchestplate.armors.models.ModelAngelChestPlate;
import net.minecraft.client.addon.tchestplate.armors.models.ModelAngelHelmet;
import net.minecraft.client.addon.tchestplate.armors.models.ModelAngelLeggins;
import net.minecraft.client.addon.tchestplate.armors.models.ModelDemonBot;
import net.minecraft.client.addon.tchestplate.armors.models.ModelDemonGrud;
import net.minecraft.client.addon.tchestplate.armors.models.ModelDemonLeg;
import net.minecraft.client.addon.tchestplate.armors.models.ModelDemonShelm;
import net.minecraft.client.addon.tchestplate.armors.models.ModelHc;
import net.minecraft.client.addon.tchestplate.armors.models.ModelLavaPlate;
import net.minecraft.client.addon.tchestplate.armors.models.ModelLavaPlateBoots;
import net.minecraft.client.addon.tchestplate.armors.models.ModelLavaPlateHelmet;
import net.minecraft.client.addon.tchestplate.armors.models.ModelLavaPlateLeggins;
import net.minecraft.client.addon.tchestplate.armors.models.ModelNoobPlate;
import net.minecraft.client.addon.tchestplate.armors.models.ModelNoobPlateBoots;
import net.minecraft.client.addon.tchestplate.armors.models.ModelNoobPlateHelmet;
import net.minecraft.client.addon.tchestplate.armors.models.ModelNoobPlateLeggins;
import net.minecraft.client.addon.tchestplate.armors.models.ModelOGNBoots;
import net.minecraft.client.addon.tchestplate.armors.models.ModelOGNHelmet;
import net.minecraft.client.addon.tchestplate.armors.models.ModelOGNLeggins;
import net.minecraft.client.addon.tchestplate.armors.models.ModelOGNPlate;
import net.minecraft.client.addon.tchestplate.armors.models.ModelPredatorLavaPlate;
import net.minecraft.client.addon.tchestplate.armors.models.ModelPredatorLavaPlateBoots;
import net.minecraft.client.addon.tchestplate.armors.models.ModelPredatorLavaPlateHelmet;
import net.minecraft.client.addon.tchestplate.armors.models.ModelPredatorLavaPlateLeggins;
import net.minecraft.client.addon.tchestplate.armors.models.ModelRBLavaPlate;
import net.minecraft.client.addon.tchestplate.armors.models.ModelRBLavaPlateBoots;
import net.minecraft.client.addon.tchestplate.armors.models.ModelRBLavaPlateHelmet;
import net.minecraft.client.addon.tchestplate.armors.models.ModelRBLavaPlateLeggins;
import net.minecraft.client.addon.tchestplate.armors.models.ModelShadowLavaPlate;
import net.minecraft.client.addon.tchestplate.armors.models.ModelShadowLavaPlateBoots;
import net.minecraft.client.addon.tchestplate.armors.models.ModelShadowLavaPlateHelmet;
import net.minecraft.client.addon.tchestplate.armors.models.ModelShadowLavaPlateLeggins;
import net.minecraft.client.addon.tchestplate.armors.models.ModelShadowLavaPlateUBoots;
import net.minecraft.client.addon.tchestplate.armors.models.ModelShadowLavaPlateUHelmet;
import net.minecraft.client.addon.tchestplate.armors.models.ModelShadowLavaPlateULeggins;
import net.minecraft.client.addon.tchestplate.armors.models.ModelShadowLavaUPlate;
import net.minecraft.client.addon.tchestplate.armors.models.ModelUltimaLavaPlate;
import net.minecraft.client.addon.tchestplate.armors.models.ModelUltimaLavaPlateBoots;
import net.minecraft.client.addon.tchestplate.armors.models.ModelUltimaLavaPlateHelmet;
import net.minecraft.client.addon.tchestplate.armors.models.ModelUltimaLavaPlateLeggins;
import net.minecraft.client.addon.tchestplate.donate.gui.AddDonateGui;
import net.minecraft.client.addon.tchestplate.donate.gui.DonateAdmConfigGui;
import net.minecraft.client.addon.tchestplate.donate.gui.DonateAdmGuiScroll;
import net.minecraft.client.addon.tchestplate.donate.gui.DonateGuiScroll;
import net.minecraft.client.addon.tchestplate.entities.monster.EntityWisp;
import net.minecraft.client.addon.tchestplate.entities.projectile.EntityFrostBlue;
import net.minecraft.client.addon.tchestplate.entities.projectile.EntityFrostRed;
import net.minecraft.client.addon.tchestplate.entities.projectile.EntityGateCastle;
import net.minecraft.client.addon.tchestplate.entities.projectile.render.RenderFrostShard;
import net.minecraft.client.addon.tchestplate.entities.projectile.render.RenderGateCastle;
import net.minecraft.client.addon.tchestplate.entities.projectile.render.RenderWisp;
import net.minecraft.client.addon.tchestplate.fx.B1;
import net.minecraft.client.addon.tchestplate.fx.BaseModelBox;
import net.minecraft.client.addon.tchestplate.fx.EntityFlameFX;
import net.minecraft.client.addon.tchestplate.fx.FXBurst;
import net.minecraft.client.addon.tchestplate.fx.FXLightningBolt;
import net.minecraft.client.addon.tchestplate.fx.FXSmokeSpiral;
import net.minecraft.client.addon.tchestplate.fx.FXSparkle;
import net.minecraft.client.addon.tchestplate.fx.FXWisp;
import net.minecraft.client.addon.tchestplate.items.gui.GuiItemPetograph;
import net.minecraft.client.addon.tchestplate.items.gui.GuiItemPets;
import net.minecraft.client.addon.tchestplate.items.gui.GuiItemPetsMiner;
import net.minecraft.client.addon.tchestplate.items.renders.BaseItemRenderModel;
import net.minecraft.client.addon.tchestplate.items.renders.PetographItemRenderer;
import net.minecraft.client.addon.tchestplate.items.renders.models.backpack.QuiverModel;
import net.minecraft.client.addon.tchestplate.overlaygui.ArmorExtGui;
import net.minecraft.client.addon.tchestplate.overlaygui.InventoryTabGui;
import net.minecraft.client.addon.tchestplate.packets.PacketInvisible;
import net.minecraft.client.addon.tchestplate.player.ExtendedPlayer;
import net.minecraft.client.addon.tchestplate.weapon.renders.BaseRenderHammer;
import net.minecraft.client.addon.tchestplate.weapon.renders.RenderHeroAxe;
import net.minecraft.client.addon.tchestplate.weapon.renders.RenderHeroSword;
import net.minecraft.client.addon.tchestplate.weapon.renders.RenderLFireSword;
import net.minecraft.client.addon.tchestplate.weapon.renders.RenderLStaffFriz;
import net.minecraft.client.addon.tchestplate.weapon.renders.RenderLStaffVedma;
import net.minecraft.client.addon.tchestplate.weapon.renders.RenderLTomahawk;
import net.minecraft.client.addon.tchestplate.weapon.renders.RenderpickAxe3x3;
import net.minecraft.client.addon.tchestplate.weapon.renders.models.ModelLHammer;
import net.minecraft.client.addon.tchestplate.weapon.renders.models.ModelLStaffFriz;
import net.minecraft.client.addon.tchestplate.weapon.renders.models.ModelLStaffFrize;
import net.minecraft.client.addon.tchestplate.weapon.renders.models.ModelLStaffSlowe;
import net.minecraft.client.addon.tchestplate.weapon.renders.models.ModelLStaffVedma;
import net.minecraft.client.addon.tchestplate.weapon.renders.models.ModelLStaffrbSlowe;
import net.minecraft.client.addon.tchestplate.weapon.renders.models.ModelLSwordPredator;
import net.minecraft.client.addon.tchestplate.weapon.renders.models.ModelLrbHammer;
import net.minecraft.client.addon.tchestplate.weapon.renders.models.ModelLultimaHammer;
import net.minecraft.client.addon.tchestplate.weapon.renders.models.ModelLvajra;
import net.minecraft.client.addon.tchestplate.weapon.renders.models.ModelNoobLsword;
import net.minecraft.client.addon.tchestplate.weapon.renders.models.ModelNoobLvajra;
import net.minecraft.client.addon.tchestplate.weapon.renders.models.ModelShadowLMachete;
import net.minecraft.client.addon.tchestplate.weapon.renders.models.ModelShadowLMacheteU;
import net.minecraft.client.addon.tchestplate.weapon.renders.models.ModelShadowLancePika;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.packet.Packet;
import net.minecraft.src.ModLoader;
import net.minecraft.src.ModelPlayerAPI;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.world.WorldEvent.Load;

@SideOnly(Side.CLIENT)
public class ClientProxy extends ServerProxy
{
    public static ModelBiped[] Modals = new ModelBiped[] {new zza(), new zzb(), new zzd(), new zzc()};
    public static Minecraft mc = ModLoader.getMinecraftInstance();
    public static final RenderItem itemRenderer = new RenderItem();
    static RenderBlocks renderBlocksInstance = new RenderBlocks();
    public static final Map armorModels = new HashMap();
    public static final ModelBipedAccesories[] HellowenModels = new ModelBipedAccesories[16];
    public static final ModelBase[] Models = new ModelBase[340];
    private ClientTickHandler ClientTickHandler;
    private float t;
    public static String st = "";
    @SideOnly(Side.CLIENT)
    public static ClientProxy.Class[] render;
    public static boolean invis;
    long tick;
    public static boolean isShock = false;

    public void registerCommands()
    {
    }

    public static final short[] intToShortArray(int value)
    {
        return new short[] {(short)(value >>> 16), (short)value};
    }

    public static final int shortArrayToInt(short[] value)
    {
        return value[1] & 65535 | (value[0] & 65535) << 16;
    }

    public void setInvisible(EntityPlayer player, boolean invis)
    {
        if (player.inventory.armorInventory[0] != null && player.inventory.armorInventory[1] != null && player.inventory.armorInventory[2] != null && player.inventory.armorInventory[3] != null)
        {
            if (player.inventory.armorInventory[0].itemID == LavaChestPlate.lavaPredatorPlateBoots.itemID && player.inventory.armorInventory[1].itemID == LavaChestPlate.lavaPredatorPlateLeggins.itemID && player.inventory.armorInventory[2].itemID == LavaChestPlate.lavaPredatorPlateChest.itemID && player.inventory.armorInventory[3].itemID == LavaChestPlate.lavaPredatorPlateHelmet.itemID)
            {
                if (LavaChestPlateItem.getChargeInvis(player.inventory.armorInventory[3]).intValue() >= 50)
                {
                    player.setInvisible(invis);
                    PacketDispatcher.sendPacketToPlayer((new PacketInvisible(invis)).makePacket(), (Player)player);
                }
                else if (LavaChestPlateItem.getChargeInvis(player.inventory.armorInventory[3]).intValue() < 50)
                {
                    player.setInvisible(false);
                    PacketDispatcher.sendPacketToPlayer((new PacketInvisible(false)).makePacket(), (Player)player);
                }
            }
        }
        else
        {
            player.setInvisible(false);
            PacketDispatcher.sendPacketToPlayer((new PacketInvisible(false)).makePacket(), (Player)player);
        }
    }

    public void register_renderers()
    {
        armorModels.put(LavaChestPlate.DemonChest, new ModelDemonGrud());
        armorModels.put(LavaChestPlate.DemonLeggins, new ModelDemonLeg());
        armorModels.put(LavaChestPlate.DemonBoots, new ModelDemonBot());
        armorModels.put(LavaChestPlate.DemonHelmet, new ModelDemonShelm());
        armorModels.put(LavaChestPlate.AngelBoots, new ModelAngelBoots());
        armorModels.put(LavaChestPlate.AngelLeggins, new ModelAngelLeggins());
        armorModels.put(LavaChestPlate.AngelChestPlate, new ModelAngelChestPlate());
        armorModels.put(LavaChestPlate.AngelHelm, new ModelAngelHelmet());
        armorModels.put(LavaChestPlate.hc, new ModelHc());
        MinecraftForgeClient.registerItemRenderer(LavaChestPlate.LFireSword.itemID, new RenderLFireSword());
        MinecraftForgeClient.registerItemRenderer(LavaChestPlate.laitemvedma.itemID, new RenderLStaffVedma(new ModelLStaffVedma(), "/mods/LavaChestPlate/textures/items/renders/lstaffvedma.png"));
        MinecraftForgeClient.registerItemRenderer(LavaChestPlate.heroaxe.itemID, new RenderHeroAxe());
        MinecraftForgeClient.registerItemRenderer(LavaChestPlate.LTomahawk.itemID, new RenderLTomahawk());
        MinecraftForgeClient.registerItemRenderer(LavaChestPlate.pickAxe3x3.itemID, new RenderpickAxe3x3());
        MinecraftForgeClient.registerItemRenderer(LavaChestPlate.herosword.itemID, new RenderHeroSword());
        MinecraftForgeClient.registerItemRenderer(LavaChestPlate.uham.itemID, new BaseRenderHammer(new ModelUham(), "/mods/provider/ual/uham.png", (byte)0));
        MinecraftForgeClient.registerItemRenderer(LavaChestPlate.lHammer.itemID, new BaseRenderHammer(new ModelLHammer(), "/mods/LavaChestPlate/textures/items/renders/lhammer.png", (byte)0));
        MinecraftForgeClient.registerItemRenderer(LavaChestPlate.lrbHammer.itemID, new BaseRenderHammer(new ModelLrbHammer(), "/mods/LavaChestPlate/textures/items/renders/lrbhammer.png", (byte)0));
        MinecraftForgeClient.registerItemRenderer(LavaChestPlate.laitemfriz.itemID, new RenderLStaffFriz(new ModelLStaffFriz(), "/mods/LavaChestPlate/textures/items/renders/lastaff.png"));
        MinecraftForgeClient.registerItemRenderer(LavaChestPlate.lultimaHammer.itemID, new BaseRenderHammer(new ModelLultimaHammer(), "/mods/LavaChestPlate/textures/items/renders/lultimahammer.png", (byte)0));
        MinecraftForgeClient.registerItemRenderer(LavaChestPlate.lvajra.itemID, new BaseRenderHammer(new ModelLvajra(), "/mods/LavaChestPlate/textures/items/renders/lvajra.png", (byte)1));
        MinecraftForgeClient.registerItemRenderer(LavaChestPlate.lStaffFrize.itemID, new BaseRenderHammer(new ModelLStaffFrize(), "/mods/LavaChestPlate/textures/items/renders/lstafffrize.png", (byte)2));
        MinecraftForgeClient.registerItemRenderer(LavaChestPlate.lStaffSlowe.itemID, new BaseRenderHammer(new ModelLStaffSlowe(), "/mods/LavaChestPlate/textures/items/renders/lstaffslowe.png", (byte)2));
        MinecraftForgeClient.registerItemRenderer(LavaChestPlate.lStaffrbSlowe.itemID, new BaseRenderHammer(new ModelLStaffrbSlowe(), "/mods/LavaChestPlate/textures/items/renders/lstaffrbslowe.png", (byte)2));
        MinecraftForgeClient.registerItemRenderer(LavaChestPlate.nooblvajra.itemID, new BaseRenderHammer(new ModelNoobLvajra(), "/mods/LavaChestPlate/textures/items/renders/nooblvajra.png", (byte)1));
        MinecraftForgeClient.registerItemRenderer(LavaChestPlate.lSwordPredator.itemID, new BaseRenderHammer(new ModelLSwordPredator(), "/mods/LavaChestPlate/textures/items/renders/lavachest.png", (byte)5));
        MinecraftForgeClient.registerItemRenderer(LavaChestPlate.nooblsword.itemID, new BaseRenderHammer(new ModelNoobLsword(), "/mods/LavaChestPlate/textures/items/renders/nooblsword.png", (byte)3));
        MinecraftForgeClient.registerItemRenderer(LavaChestPlate.litemPets.itemID, new BaseItemRenderModel());
        MinecraftForgeClient.registerItemRenderer(LavaChestPlate.litemPetograph.itemID, new PetographItemRenderer());
        MinecraftForgeClient.registerItemRenderer(LavaChestPlate.lShadowLMachete.itemID, new BaseRenderHammer(new ModelShadowLMachete(), "/mods/LavaChestPlate/textures/items/renders/shadowmachete.png", (byte)4));
        MinecraftForgeClient.registerItemRenderer(LavaChestPlate.lShadowLancePika.itemID, new BaseRenderHammer(new ModelShadowLancePika(), "/mods/LavaChestPlate/textures/items/renders/nooblsword.png", (byte)3));
        MinecraftForgeClient.registerItemRenderer(LavaChestPlate.lShadowLMacheteU.itemID, new BaseRenderHammer(new ModelShadowLMacheteU(), "/mods/LavaChestPlate/textures/items/renders/shadowmacheteU.png", (byte)4));
        MinecraftForgeClient.registerItemRenderer(LavaChestPlate.demon.itemID, new RenderDemonic());
        armorModels.put(LavaChestPlate.AccesoriasBant, new ModelBant());
        armorModels.put(LavaChestPlate.AccesoriasShut, new ModelShut());
        armorModels.put(LavaChestPlate.AccesoriasVolk, new ModelVolk());
        armorModels.put(LavaChestPlate.AccesoriasGirlHair, new ModelGirlHair());
        armorModels.put(LavaChestPlate.AccesoriasSkull, new ModelSkull());
        armorModels.put(LavaChestPlate.AccesoriasSanta, new ModelSanta());
        armorModels.put(LavaChestPlate.lavaChestPlateChest, new ModelLavaPlate());
        armorModels.put(LavaChestPlate.lavaChestPlateLeggins, new ModelLavaPlateLeggins());
        armorModels.put(LavaChestPlate.lavaChestPlateBoots, new ModelLavaPlateBoots());
        armorModels.put(LavaChestPlate.lavaChestPlateHelmet, new ModelLavaPlateHelmet());
        armorModels.put(LavaChestPlate.lavaRBPlateChest, new ModelRBLavaPlate());
        armorModels.put(LavaChestPlate.lavaRBPlateLeggins, new ModelRBLavaPlateLeggins());
        armorModels.put(LavaChestPlate.lavaRBPlateBoots, new ModelRBLavaPlateBoots());
        armorModels.put(LavaChestPlate.lavaRBPlateHelmet, new ModelRBLavaPlateHelmet());
        armorModels.put(LavaChestPlate.lavaUltimaPlateChest, new ModelUltimaLavaPlate());
        armorModels.put(LavaChestPlate.lavaUltimaPlateLeggins, new ModelUltimaLavaPlateLeggins());
        armorModels.put(LavaChestPlate.lavaUltimaPlateBoots, new ModelUltimaLavaPlateBoots());
        armorModels.put(LavaChestPlate.lavaUltimaPlateHelmet, new ModelUltimaLavaPlateHelmet());
        armorModels.put(LavaChestPlate.noobPlateChest, new ModelNoobPlate());
        armorModels.put(LavaChestPlate.noobPlateLeggins, new ModelNoobPlateLeggins());
        armorModels.put(LavaChestPlate.noobPlateHelmet, new ModelNoobPlateHelmet());
        armorModels.put(LavaChestPlate.noobPlateBoots, new ModelNoobPlateBoots());
        armorModels.put(LavaChestPlate.lavaShadowPlateChest, new ModelShadowLavaPlate());
        armorModels.put(LavaChestPlate.lavaShadowPlateLeggins, new ModelShadowLavaPlateLeggins());
        armorModels.put(LavaChestPlate.lavaShadowPlateBoots, new ModelShadowLavaPlateBoots());
        armorModels.put(LavaChestPlate.lavaShadowPlateHelmet, new ModelShadowLavaPlateHelmet());
        armorModels.put(LavaChestPlate.lavaPredatorPlateHelmet, new ModelPredatorLavaPlateHelmet());
        armorModels.put(LavaChestPlate.lavaPredatorPlateChest, new ModelPredatorLavaPlate());
        armorModels.put(LavaChestPlate.lavaPredatorPlateLeggins, new ModelPredatorLavaPlateLeggins());
        armorModels.put(LavaChestPlate.lavaPredatorPlateBoots, new ModelPredatorLavaPlateBoots());
        armorModels.put(LavaChestPlate.lavaShadowPlateUHelmet, new ModelShadowLavaPlateUHelmet());
        armorModels.put(LavaChestPlate.lavaShadowPlateUChest, new ModelShadowLavaUPlate());
        armorModels.put(LavaChestPlate.lavaShadowPlateULeggins, new ModelShadowLavaPlateULeggins());
        armorModels.put(LavaChestPlate.lavaShadowPlateUBoots, new ModelShadowLavaPlateUBoots());
        armorModels.put(LavaChestPlate.OGN1, new ModelOGNBoots());
        armorModels.put(LavaChestPlate.OGN2, new ModelOGNLeggins());
        armorModels.put(LavaChestPlate.OGN3, new ModelOGNPlate());
        armorModels.put(LavaChestPlate.OGN4, new ModelOGNHelmet());
        HellowenModels[0] = new ModelHellowen0();
        HellowenModels[1] = new ModelHellowen1();
        HellowenModels[2] = new ModelHellowen2();
        HellowenModels[3] = new ModelHellowen3();
        HellowenModels[4] = new ModelFineryHelmet();
        HellowenModels[5] = new ModelFineryHelmetM();
        HellowenModels[6] = new ModelFineryChest();
        HellowenModels[7] = new ModelFineryChestM();
        HellowenModels[8] = new ModelFineryLeggens();
        HellowenModels[9] = new ModelFineryLeggensM();
        HellowenModels[10] = new ModelFineryBoots();
        HellowenModels[11] = new ModelFineryBootsM();
        Models[12] = new ModelLavaVedma();
        Models[13] = new ModelLavaArmy();
        Models[15] = new ModelMaskaSanta();
        Models[16] = new ModelLeprikonMask();
        Models[17] = new ModelShcool();
        Models[59] = Modals[1];
        Models[61] = Modals[2];
        Models[62] = Modals[3];
        Models[63] = Modals[0];
        Models[92] = new QuiverModel();
        Models[82] = new db();
        Models[81] = new dl();
        Models[80] = new dc();
        Models[79] = new dh();
        Models[64] = new B1();
        RenderingRegistry.registerEntityRenderingHandler(EntityFrostBlue.class, new RenderFrostShard("/mods/LavaChestPlate/textures/models/frostshard.png"));
        RenderingRegistry.registerEntityRenderingHandler(EntityFrostRed.class, new RenderFrostShard("/mods/LavaChestPlate/textures/models/frostshard1.png"));
        RenderingRegistry.registerEntityRenderingHandler(EntityGateCastle.class, new RenderGateCastle());
        RenderingRegistry.registerEntityRenderingHandler(EntityWisp.class, new RenderWisp());
    }

    public void initCore()
    {
        ModelPlayerAPI.register("LavaChestPlate", PlayerModelManager.class);
        TickRegistry.registerTickHandler(new ClientTickHandler(), Side.CLIENT);
        MinecraftForge.EVENT_BUS.register(this);
    }

    public void burst(World worldObj, double sx, double sy, double sz, float size)
    {
        FXBurst ef = new FXBurst(worldObj, sx, sy, sz, size);
        ModLoader.getMinecraftInstance().effectRenderer.addEffect(ef);
    }

    public static void sparkleB1(Vec3 var0, float var1, byte var2)
    {
        ((BaseModelBox)Models[64]).render(var0, var1, var2);
    }

    public void runSpawnParticle(Entity var1, Entity var2, int var3)
    {
        if (var1 != null && var2 != null)
        {
            FXLightningBolt var10;

            if (var3 == 1)
            {
                var10 = new FXLightningBolt(var1.worldObj, var1, var2, var1.worldObj.rand.nextLong());
                var10.defaultFractal();
                var10.setType(2);
                var10.setWidth(0.125F);
                var10.finalizeBolt();
            }

            double var4;
            double var6;
            double var8;

            if (var3 == 2)
            {
                var8 = var1.posX;
                var6 = var1.posY;
                var4 = var1.posZ;
                var10 = new FXLightningBolt(var2.worldObj, var8, var6 + 7.0D, var4, var8, var6 + 1.0D, var4, var2.worldObj.rand.nextLong(), 6, 2.5F, 3);
                var10.defaultFractal();
                var10.setType(4);
                var10.setWidth(0.125F);
                var10.main.particleMaxAge = 35;
                var10.finalizeBolt();
            }

            if (var3 == 3)
            {
                var8 = var1.posX;
                var6 = var1.posY;
                var4 = var1.posZ;

                for (int var11 = 0; var11 < 50; ++var11)
                {
                    if (var1.worldObj.rand.nextInt(5) == 0)
                    {
                        this.wispFX3(var1.worldObj, var8 + 0.5D, var6 + 0.5D, var4 + 0.5D, var8 + 0.4000000059604645D + (double)(var1.worldObj.rand.nextFloat() * 0.2F), var6 + 0.5D, var4 + 0.4000000059604645D + (double)(var1.worldObj.rand.nextFloat() * 0.2F), 0.25F, 1, true, -0.02F);
                    }
                }
            }

            if (var3 == 4)
            {
                this.bolt(var1.worldObj, var1, var2);
            }

            if (var3 == 5)
            {
                this.bolt1(var1.worldObj, var1, var2);
            }

            if (var3 == 6)
            {
                this.boltBlue(var1.worldObj, var1, var2);
            }
        }
    }

    public void bolt1(World var1, Entity var2, Entity var3)
    {
        for (int var4 = 0; var4 < 5; ++var4)
        {
            FXLightningBolt var5 = new FXLightningBolt(var1, var3.posX, var3.posY + 4.0D, var3.posZ, var3.posX, var3.posY + 2.0D, var3.posZ, var1.rand.nextLong(), 5, 5.1F, 2);
            var5.defaultFractal();
            var5.setType(0);
            var5.finalizeBolt();
        }

        var1.playSound(var3.posX, var3.posY, var3.posZ, "tchestplate.shock1", 1.0F, 1.0F, false);
    }

    public void boltBlue(World var1, Entity var2, Entity var3)
    {
        FXLightningBolt var4 = new FXLightningBolt(var1, var2, var3, var1.rand.nextLong(), 4);
        var4.defaultFractal();
        var4.setType(0);
        var4.finalizeBolt();
    }

    public void bolt(World worldObj, Entity sourceEntity, Entity targetedEntity)
    {
        FXLightningBolt bolt = new FXLightningBolt(worldObj, sourceEntity, targetedEntity, worldObj.rand.nextLong(), 4);
        bolt.defaultFractal();
        bolt.setType(0);
        bolt.finalizeBolt();
    }

    public void sendCustomPacketToAllNear(Packet packet, double range, Entity enity)
    {
    }

    public void wispFX(World worldObj, double posX, double posY, double posZ, float f, float g, float h, float i)
    {
        FXWisp ef = new FXWisp(worldObj, posX, posY, posZ, f, g, h, i);
        ef.setGravity(0.02F);
        ModLoader.getMinecraftInstance().effectRenderer.addEffect(ef);
    }

    public void wispFX2(World worldObj, double posX, double posY, double posZ, float size, int type, boolean shrink, float gravity)
    {
        FXWisp ef = new FXWisp(worldObj, posX, posY, posZ, size, type);
        ef.setGravity(gravity);
        ef.shrink = shrink;
        ModLoader.getMinecraftInstance().effectRenderer.addEffect(ef);
    }

    public void wispFX3(World worldObj, double posX, double posY, double posZ, double posX2, double posY2, double posZ2, float size, int type, boolean shrink, float gravity)
    {
        FXWisp ef = new FXWisp(worldObj, posX, posY, posZ, posX2, posY2, posZ2, size, type);
        ef.setGravity(gravity);
        ef.shrink = shrink;
        ModLoader.getMinecraftInstance().effectRenderer.addEffect(ef);
    }

    public void spawnParticleFlame(double par2, double par4, double par6, double par8, double par10, double par12, boolean invise)
    {
        EntityFlameFX fx = new EntityFlameFX(this.getClientWorld(), par2, par4, par6, par8, par10, par12);
        fx.invise = invise;
        ModLoader.getMinecraftInstance().effectRenderer.addEffect(fx);
    }

    public void blockSparkle(World world, int x, int y, int z, int i, int count)
    {
        blockSparkle1(world, (double)x, (double)y, (double)z, i, count);
    }

    public void sparkle(float var1, float var2, float var3, float var4, int var5, float var6)
    {
        if (this.getClientWorld() != null && this.getClientWorld().rand.nextInt(6) < particleCount(2))
        {
            FXSparkle var7 = new FXSparkle(this.getClientWorld(), (double)var1, (double)var2, (double)var3, var4, var5, 6);
            var7.noClip = true;
            var7.setGravity(var6);
            ModLoader.getMinecraftInstance().effectRenderer.addEffect(var7);
        }
    }

    public void sparkle(float var1, float var2, float var3, float var4, int var5, float var6, int var7, boolean var8)
    {
        if (this.getClientWorld() != null && this.getClientWorld().rand.nextInt(6) < particleCount(2))
        {
            FXSparkle var9 = new FXSparkle(this.getClientWorld(), (double)var1, (double)var2, (double)var3, var4, var5, var7);
            var9.invise = var8;
            var9.noClip = true;
            var9.setGravity(var6);
            ModLoader.getMinecraftInstance().effectRenderer.addEffect(var9);
        }
    }

    public void sparkle(float var1, float var2, float var3, float var4, int var5, float var6, int var7)
    {
        if (this.getClientWorld() != null && this.getClientWorld().rand.nextInt(6) < particleCount(2))
        {
            FXSparkle var8 = new FXSparkle(this.getClientWorld(), (double)var1, (double)var2, (double)var3, var4, var5, var7);
            var8.noClip = true;
            var8.setGravity(var6);
            ModLoader.getMinecraftInstance().effectRenderer.addEffect(var8);
        }
    }

    public void sparkle(float var1, float var2, float var3, int var4)
    {
        if (this.getClientWorld() != null && this.getClientWorld().rand.nextInt(6) < particleCount(2))
        {
            FXSparkle var5 = new FXSparkle(this.getClientWorld(), (double)var1, (double)var2, (double)var3, 1.5F, var4, 6);
            var5.noClip = true;
            ModLoader.getMinecraftInstance().effectRenderer.addEffect(var5);
        }
    }

    public static int particleCount(int var0)
    {
        return ModLoader.getMinecraftInstance().gameSettings.particleSetting == 2 ? 0 : (ModLoader.getMinecraftInstance().gameSettings.particleSetting == 1 ? var0 * 1 : var0 * 2);
    }

    public static void blockSparkle1(World world, double x, double y, double z, int color, int count)
    {
        if (world.isRemote)
        {
            for (int a = 0; a < particleCount(count); ++a)
            {
                FXSparkle fx = new FXSparkle(world, (double)((float)x + world.rand.nextFloat()), (double)((float)y + world.rand.nextFloat()), (double)((float)z + world.rand.nextFloat()), 1.75F, color == -1 ? world.rand.nextInt(5) : color, 3 + world.rand.nextInt(3));
                fx.setGravity(0.2F);
                ModLoader.getMinecraftInstance().effectRenderer.addEffect(fx);
            }
        }
    }

    public void smokeSpiral(World world, double x, double y, double z, float rad, int start, int miny)
    {
        FXSmokeSpiral fx = new FXSmokeSpiral(this.getClientWorld(), x, y, z, rad, start, miny);
        ModLoader.getMinecraftInstance().effectRenderer.addEffect(fx);
    }

    public World getClientWorld()
    {
        return FMLClientHandler.instance().getClient().theWorld;
    }

    public EntityPlayer getPlayerInstance()
    {
        return Minecraft.getMinecraft().thePlayer;
    }

    public static void sendPlayerMessage(EntityPlayer player, String message)
    {
        if (!mc.theWorld.isRemote)
        {
            player.addChatMessage(message);
        }
    }

    public void sparkle(float var1, float var2, float var3, float var4, int var5, float var6, int var7, boolean var8, boolean var9, boolean var10)
    {
        if (this.getClientWorld() != null && (var9 || this.getClientWorld().rand.nextInt(6) < particleCount(2)))
        {
            FXSparkle var11 = new FXSparkle(this.getClientWorld(), (double)var1, (double)var2, (double)var3, var4, var5, var7);
            var11.invise = var8;
            var11.noClip = true;
            var11.inverse = var10;
            var11.setGravity(var6);
            ModLoader.getMinecraftInstance().effectRenderer.addEffect(var11);
        }
    }

    public boolean movementEnabled(EntityPlayer player, float ForwardDelta, float StrafeDelta, boolean jump, boolean sneak)
    {
        if (player.worldObj.isRemote && player instanceof EntityPlayerSP)
        {
            EntityPlayerSP psp = (EntityPlayerSP)player;

            if (!(psp.movementInput instanceof MoveF))
            {
                ClientTickHandler var10000 = this.ClientTickHandler;
                ClientTickHandler var100001 = this.ClientTickHandler;
                ClientTickHandler sav = this.ClientTickHandler;
                ClientTickHandler ebalai = this.ClientTickHandler;
                ClientTickHandler var1000011 = this.ClientTickHandler;
                ClientTickHandler.mmmTemp = psp.movementInput;
                sav = this.ClientTickHandler;
                var1000011 = this.ClientTickHandler;
                sav = this.ClientTickHandler;
                sav = this.ClientTickHandler;
                sav = this.ClientTickHandler;
                ebalai = this.ClientTickHandler;
                var1000011 = this.ClientTickHandler;
                ((MoveF)ClientTickHandler.mmm).ForwardDelta = ForwardDelta;
                sav = this.ClientTickHandler;
                var1000011 = this.ClientTickHandler;
                sav = this.ClientTickHandler;
                sav = this.ClientTickHandler;
                sav = this.ClientTickHandler;
                ebalai = this.ClientTickHandler;
                var1000011 = this.ClientTickHandler;
                ((MoveF)ClientTickHandler.mmm).StrafeDelta = StrafeDelta;
                sav = this.ClientTickHandler;
                var1000011 = this.ClientTickHandler;
                sav = this.ClientTickHandler;
                sav = this.ClientTickHandler;
                sav = this.ClientTickHandler;
                ebalai = this.ClientTickHandler;
                var1000011 = this.ClientTickHandler;
                ((MoveF)ClientTickHandler.mmm).JumpDelta = jump;
                sav = this.ClientTickHandler;
                var1000011 = this.ClientTickHandler;
                sav = this.ClientTickHandler;
                sav = this.ClientTickHandler;
                sav = this.ClientTickHandler;
                ebalai = this.ClientTickHandler;
                var1000011 = this.ClientTickHandler;
                ((MoveF)ClientTickHandler.mmm).SneakDelta = sneak;
                ClientTickHandler var10001 = this.ClientTickHandler;
                ClientTickHandler var100011 = this.ClientTickHandler;
                ClientTickHandler ad = this.ClientTickHandler;
                ClientTickHandler mudila = this.ClientTickHandler;
                ClientTickHandler var1000111 = this.ClientTickHandler;
                psp.movementInput = ClientTickHandler.mmm;

                if (ForwardDelta == 0.0F && StrafeDelta == 0.0F)
                {
                    isShock = true;
                }
                else
                {
                    isShock = false;
                }

                return true;
            }
        }

        return false;
    }

    public boolean movementDisabled(EntityPlayer player)
    {
        if (player.worldObj.isRemote && player instanceof EntityPlayerSP)
        {
            EntityPlayerSP psp = (EntityPlayerSP)player;

            if (psp.movementInput instanceof MoveF)
            {
                ClientTickHandler var10001 = this.ClientTickHandler;
                ClientTickHandler var100011 = this.ClientTickHandler;
                ClientTickHandler csa = this.ClientTickHandler;
                ClientTickHandler durak = this.ClientTickHandler;
                ClientTickHandler var1000111 = this.ClientTickHandler;
                psp.movementInput = ClientTickHandler.mmmTemp;
                isShock = false;
                return true;
            }
        }

        return false;
    }

    public void openCustomGui(int iDGui, EntityPlayer player)
    {
        player.openGui(LavaChestPlate.instance, iDGui, player.worldObj, (int)player.posX, (int)player.posY, (int)player.posZ);
    }

    public void openExtGui(int iDGui, int iDparam, EntityPlayer player)
    {
        if (iDGui == 3004)
        {
            mc.displayGuiScreen(new AddDonateGui(player.inventory, ExtendedPlayer.get(player).inventoryExt));
        }
        else
        {
            InventoryTabGui.isOpenGui = false;

            if (iDGui == 2000)
            {
                mc.displayGuiScreen(new GuiInventory(mc.thePlayer));
            }
        }
    }

    @ForgeSubscribe
    public void onWorldLoad(Load event)
    {
    }

    public Object getClientGuiElement(int var1, EntityPlayer var2, World var3, int var4, int var5, int var6)
    {
        if (render == null)
        {
            ;
        }

        return var1 == 10 ? new GuiItemPetograph(var2) : (var1 == 11 ? new GuiItemPets(var2.inventory, var3, var4, var5, var6, ExtendedPlayer.get(var2)) : (var1 == 12 ? new GuiItemPetsMiner(var2.inventory, var3, var4, var5, var6, ExtendedPlayer.get(var2)) : (var1 == 3000 ? new ArmorExtGui(var2.inventory, ExtendedPlayer.get(var2).inventoryExt)  : (var1 == 3001 ? new DonateAdmGuiScroll(var2.inventory, ExtendedPlayer.get(var2).inventoryExt) : (var1 == 3002 ? new DonateAdmConfigGui(var2.inventory, ExtendedPlayer.get(var2).inventoryExt, var4) : (var1 == 3003 ? new DonateGuiScroll(var2.inventory, ExtendedPlayer.get(var2).inventoryExt) : (var1 == 3005 ? new DonateGuiScroll(var2.inventory, ExtendedPlayer.get(var2).inventoryExt) : null)))))));
    }

    public void showLvLweapon(boolean var1, byte var2, float var3, float var4, float var5, float var6, float var7, float var8)
    {
        long var9 = Minecraft.getMinecraft().theWorld.getWorldTime();

        if (render == null)
        {
            ;
        }

        GL11.glPushMatrix();
        float var11 = render[0].a4;
        GL11.glScalef(var11, var11, var11);
        Tessellator var12 = Tessellator.instance;
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glShadeModel(GL11.GL_SMOOTH);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
        GL11.glDisable(GL11.GL_ALPHA_TEST);
        GL11.glDepthMask(false);
        GL11.glPushMatrix();

        if (var9 - this.tick > 0L)
        {
            this.tick = var9;

            if ((this.t += render[0].a7) > 2.0F)
            {
                ClientProxy.Class var13 = render[0];
                this.t = ClientProxy.Class.a5;
            }
        }

        var11 = (float)(Math.sin((double)this.t) * (double)render[0].a6);
        int var17 = 0;
        int var14 = 0;
        int var15 = 0;

        if (var2 <= 5)
        {
            var15 = var2 * render[0].a1;
        }
        else if (var2 <= 10)
        {
            var17 = (var2 - 4) * render[0].a1;
        }
        else if (var2 <= 15)
        {
            var14 = (int)((float)(var17 = (var2 - 9) * render[0].a1) * render[0].a11);
        }
        else if (var2 > 15)
        {
            var14 = ((var2 > 20 ? 20 : var2) - 13) * render[0].a1;
        }

        for (int var16 = 0; var16 < render[0].a2; ++var16)
        {
            var11 *= render[0].a3;
            GL11.glScalef(var11, var11, var11);
            this.sharpe(var12, var11, var1, var3, var4 + var11 * render[0].a9, var5, var6, var7, var8, (int)(255.0F * (render[0].a10 - render[0].a8)), var17, var14, var15);
        }

        GL11.glPopMatrix();
        GL11.glDepthMask(true);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glShadeModel(GL11.GL_FLAT);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glPopMatrix();
    }

    void sharpe(Tessellator var1, float var2, boolean var3, float var4, float var5, float var6, float var7, float var8, float var9, int var10, int var11, int var12, int var13)
    {
        var1.startDrawing(render[0].a);
        byte var17 = 0;
        var1.setColorRGBA(var11, var12, var13, var10);
        var1.addVertex((double)var4, (double)var8, (double)var9);
        var1.addVertex((double)var7, (double)var8, (double)var9);
        var1.setColorRGBA(var11, var12, var13, var17);
        var1.addVertex((double)var7, (double)var5, (double)var9);
        var1.addVertex((double)var4, (double)var5, (double)var9);
        var1.setColorRGBA(var11, var12, var13, var10);
        var1.addVertex((double)var7, (double)var8, (double)var6);
        var1.addVertex((double)var4, (double)var8, (double)var6);
        var1.setColorRGBA(var11, var12, var13, var17);
        var1.addVertex((double)var4, (double)var5, (double)var6);
        var1.addVertex((double)var7, (double)var5, (double)var6);

        if (var3)
        {
            var1.setColorRGBA(var11, var12, var13, var10);
            var1.addVertex((double)var7, (double)var8, (double)var9);
            var1.addVertex((double)var4, (double)var8, (double)var9);
            var1.addVertex((double)var4, (double)var8, (double)var6);
            var1.addVertex((double)var7, (double)var8, (double)var6);
        }

        var1.setColorRGBA(var11, var12, var13, var10);
        var1.addVertex((double)var7, (double)var8, (double)var6);
        var1.setColorRGBA(var11, var12, var13, var17);
        var1.addVertex((double)var7, (double)var5, (double)var6);
        var1.addVertex((double)var7, (double)var5, (double)var9);
        var1.setColorRGBA(var11, var12, var13, var10);
        var1.addVertex((double)var7, (double)var8, (double)var9);
        var1.setColorRGBA(var11, var12, var13, var17);
        var1.addVertex((double)var4, (double)var5, (double)var9);
        var1.addVertex((double)var4, (double)var5, (double)var6);
        var1.setColorRGBA(var11, var12, var13, var10);
        var1.addVertex((double)var4, (double)var8, (double)var6);
        var1.addVertex((double)var4, (double)var8, (double)var9);
        var1.setColorRGBA(var11, var12, var13, var10);
        var1.addVertex((double)var4, (double)var8, (double)var9);
        var1.addVertex((double)var7, (double)var8, (double)var9);
        var1.setColorRGBA(var11, var12, var13, var17);
        var1.addVertex((double)var7, (double)(-var5), (double)var9);
        var1.addVertex((double)var4, (double)(-var5), (double)var9);
        var1.setColorRGBA(var11, var12, var13, var10);
        var1.addVertex((double)var7, (double)var8, (double)var6);
        var1.addVertex((double)var4, (double)var8, (double)var6);
        var1.setColorRGBA(var11, var12, var13, var17);
        var1.addVertex((double)var4, (double)(-var5), (double)var6);
        var1.addVertex((double)var7, (double)(-var5), (double)var6);
        var1.addVertex((double)var7, (double)(-var5), (double)var9);
        var1.setColorRGBA(var11, var12, var13, var10);
        var1.addVertex((double)var7, (double)var8, (double)var9);
        var1.addVertex((double)var7, (double)var8, (double)var6);
        var1.setColorRGBA(var11, var12, var13, var17);
        var1.addVertex((double)var7, (double)(-var5), (double)var6);
        var1.addVertex((double)var4, (double)(-var5), (double)var9);
        var1.addVertex((double)var4, (double)(-var5), (double)var6);
        var1.setColorRGBA(var11, var12, var13, var10);
        var1.addVertex((double)var4, (double)var8, (double)var6);
        var1.addVertex((double)var4, (double)var8, (double)var9);
        var1.draw();
    }

    @SideOnly(Side.CLIENT)
    public static class Class
    {
        public int a;
        public int a1;
        public int a2;
        public float a3;
        public float a4;
        public static float a5;
        public float a6;
        public float a7;
        public float a8;
        public float a9;
        public float a10;
        public float a11;
    }
}
