package codechicken.core.asm;

import codechicken.core.featurehack.LiquidTextures;
import codechicken.core.internal.ClientTickHandler;
import codechicken.packager.Packager;
import codechicken.packager.SrcPackager;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import cpw.mods.fml.common.DummyModContainer;
import cpw.mods.fml.common.LoadController;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.common.versioning.VersionParser;
import cpw.mods.fml.common.versioning.VersionRange;
import cpw.mods.fml.relauncher.Side;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Packager(
   getBaseDirectories = {"CodeChickenCore"},
   getName = "CodeChickenCore",
   getVersion = "0.8.7.3"
)
@SrcPackager
public class CodeChickenCoreModContainer extends DummyModContainer {

   public CodeChickenCoreModContainer() {
      super(new ModMetadata());
      ModMetadata meta = this.getMetadata();
      meta.modId = "CodeChickenCore";
      meta.name = "CodeChicken Core";
      meta.version = ((Packager)this.getClass().getAnnotation(Packager.class)).getVersion();
      meta.authorList = Arrays.asList(new String[]{"ChickenBones"});
      meta.description = "Base common code for all chickenbones mods.";
      meta.url = "http://www.minecraftforum.net/topic/909223-";
   }

   public List getDependants() {
      LinkedList deps = new LinkedList();
      deps.add(VersionParser.parseVersionReference("NotEnoughItems@[1.5.2.26,)"));
      deps.add(VersionParser.parseVersionReference("EnderStorage@[1.4.2.15,)"));
      deps.add(VersionParser.parseVersionReference("ChickenChunks@[1.3.2.12,)"));
      deps.add(VersionParser.parseVersionReference("Translocator@[1.1.0.3,)"));
      return deps;
   }

   public boolean registerBus(EventBus bus, LoadController controller) {
      bus.register(this);
      return true;
   }

   @Subscribe
   public void preInit(FMLPreInitializationEvent event) {
      if(event.getSide() == Side.CLIENT) {
         LiquidTextures.init();
      }

   }

   @Subscribe
   public void init(FMLInitializationEvent event) {
      TickRegistry.registerTickHandler(new ClientTickHandler(), Side.CLIENT);
   }

   public VersionRange acceptableMinecraftVersionRange() {
      return VersionParser.parseRange("[1.5.2]");
   }
}
