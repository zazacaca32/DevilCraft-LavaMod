package net.minecraft.client.addon.tco.tiny.entity;

import java.io.File;
import org.bukkit.configuration.file.FileConfiguration;

public class TraderServerConfiguration {

   File configFile;
   File dataFolder;
   String configName;
   private FileConfiguration newConfig;
   public boolean isSaveTick;
   public boolean isSaveForce;
   private int savetime;


   public void saveRecipies(String entityName) {}
}
