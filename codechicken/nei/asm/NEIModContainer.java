package codechicken.nei.asm;

import codechicken.core.CommonUtils;
import codechicken.nei.ClientHandler;
import codechicken.nei.ServerHandler;
import codechicken.nei.api.IConfigureNEI;
import codechicken.packager.Packager;
import codechicken.packager.SrcPackager;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import cpw.mods.fml.common.DummyModContainer;
import cpw.mods.fml.common.LoadController;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.versioning.VersionParser;
import cpw.mods.fml.common.versioning.VersionRange;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Packager(
   getName = "NotEnoughItems",
   getClasses = {""},
   getBaseDirectories = {"NotEnoughItems"},
   getVersion = "1.5.2.28"
)
@SrcPackager(
   getName = "NotEnoughItems",
   getClasses = {""},
   getMappedDirectories = {"NotEnoughItems"}
)
public class NEIModContainer extends DummyModContainer {

   public static LinkedList plugins = new LinkedList();


   public NEIModContainer() {
      super(new ModMetadata());
      this.getMetadata();
   }

   public Set getRequirements() {
      HashSet deps = new HashSet();
      deps.add(VersionParser.parseVersionReference("CodeChickenCore@[0.8.7,)"));
      return deps;
   }

   public List getDependencies() {
      return new LinkedList(this.getRequirements());
   }

   public ModMetadata getMetadata() {
      ModMetadata meta = super.getMetadata();
      meta.modId = "NotEnoughItems";
      meta.name = "Not Enough Items";
      meta.version = ((Packager)NEIModContainer.class.getAnnotation(Packager.class)).getVersion();
      meta.authorList = Arrays.asList(new String[]{"§aChickenBones. §fПеревод: §atrespasser"});
      meta.description = "Книга Рецептов, Менеджер Инвентаря, Спавнеры, Читы и другое.\n§f\n";
      meta.url = "http://www.minecraftforum.net/topic/909223-";
      meta.credits = "Alexandria - Original Idea";
      if(plugins.size() == 0) {
         meta.description = meta.description + "§cНет установленных плагинов.";
      } else {
         meta.description = meta.description + "§aУстановленные плагины: ";

         for(int i = 0; i < plugins.size(); ++i) {
            if(i > 0) {
               meta.description = meta.description + ", ";
            }

            IConfigureNEI plugin = (IConfigureNEI)plugins.get(i);
            meta.description = meta.description + plugin.getName() + " " + plugin.getVersion();
         }

         meta.description = meta.description + ".";
      }

      return meta;
   }

   public boolean registerBus(EventBus bus, LoadController controller) {
      bus.register(this);
      return true;
   }

   @Subscribe
   public void init(FMLInitializationEvent event) {
      if(CommonUtils.isClient()) {
         ClientHandler.load();
      }

      ServerHandler.load();
   }

   public VersionRange acceptableMinecraftVersionRange() {
      return VersionParser.parseRange("[1.5.2]");
   }
}
