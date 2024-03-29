/*
 * Forge Mod Loader
 * Copyright (c) 2012-2013 cpw.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v2.1
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 *
 * Contributors:
 *     cpw - implementation
 */

package cpw.mods.fml.common;

import java.security.cert.Certificate;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.storage.SaveHandler;
import net.minecraft.world.storage.WorldInfo;

import com.google.common.eventbus.EventBus;

import cpw.mods.fml.common.registry.GameData;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.ItemData;

/**
 * @author cpw
 *
 */
public class FMLDummyContainer extends DummyModContainer implements WorldAccessContainer
{
    public FMLDummyContainer()
    {
        super(new ModMetadata());
        ModMetadata meta = getMetadata();
        meta.modId = "FML";
        meta.name = "Forge Mod Loader";
        meta.version = Loader.instance().getFMLVersionString();
        meta.credits = "Made possible with help from many people";
        meta.authorList = Arrays.asList("cpw, LexManos");
        meta.description = "The Forge Mod Loader provides the ability for systems to load mods " +
                           "from the file system. It also provides key capabilities for mods to be able " +
                           "to cooperate and provide a good modding environment. " +
                           "The mod loading system is compatible with ModLoader, all your ModLoader " +
                           "mods should work.";
        meta.url = "https://github.com/MinecraftForge/FML/wiki";
        meta.updateUrl = "https://github.com/MinecraftForge/FML/wiki";
        meta.screenshots = new String[0];
        meta.logoFile = "";
    }

    @Override
    public boolean registerBus(EventBus bus, LoadController controller)
    {
        return true;
    }

    @Override
    public NBTTagCompound getDataForWriting(SaveHandler handler, WorldInfo info)
    {
        NBTTagCompound fmlData = new NBTTagCompound();
        NBTTagList list = new NBTTagList();

        for (ModContainer mc : Loader.instance().getActiveModList())
        {
            NBTTagCompound mod = new NBTTagCompound();
            mod.setString("ModId", mc.getModId());
            mod.setString("ModVersion", mc.getVersion());
            list.appendTag(mod);
        }

        fmlData.setTag("ModList", list);
        NBTTagList itemList = new NBTTagList();
        GameData.writeItemData(itemList);
        fmlData.setTag("ModItemData", itemList);
        return fmlData;
    }

    @Override
    public void readData(SaveHandler handler, WorldInfo info, Map<String, NBTBase> propertyMap, NBTTagCompound tag)
    {
        if (tag.hasKey("ModList"))
        {
            NBTTagList modList = tag.getTagList("ModList");

            for (int i = 0; i < modList.tagCount(); i++)
            {
                NBTTagCompound mod = (NBTTagCompound) modList.tagAt(i);
                String modId = mod.getString("ModId");
                String modVersion = mod.getString("ModVersion");
                ModContainer container = Loader.instance().getIndexedModList().get(modId);

                if (container == null)
                {
                    FMLLog.log("fml.ModTracker", Level.SEVERE, "This world was saved with mod %s which appears to be missing, things may not work well", modId);
                    continue;
                }

                if (!modVersion.equals(container.getVersion()))
                {
                    FMLLog.log("fml.ModTracker", Level.INFO, "This world was saved with mod %s version %s and it is now at version %s, things may not work well", modId, modVersion, container.getVersion());
                }
            }
        }

        if (tag.hasKey("ModItemData"))
        {
            NBTTagList modList = tag.getTagList("ModItemData");
            Set<ItemData> worldSaveItems = GameData.buildWorldItemData(modList);
            GameData.validateWorldSave(worldSaveItems);
        }
        else
        {
            GameData.validateWorldSave(null);
        }
    }

    @Override
    public Certificate getSigningCertificate()
    {
        Certificate[] certificates = getClass().getProtectionDomain().getCodeSource().getCertificates();
        return certificates != null ? certificates[0] : null;
    }
}
