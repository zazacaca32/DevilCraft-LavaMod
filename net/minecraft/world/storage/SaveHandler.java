package net.minecraft.world.storage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.MinecraftException;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.storage.IChunkLoader;

public class SaveHandler implements ISaveHandler, IPlayerFileData
{
    /** The directory in which to save world data. */
    private final File worldDirectory;

    /** The directory in which to save player data. */
    private final File playersDirectory;
    private final File mapDataDir;

    /**
     * The time in milliseconds when this field was initialized. Stored in the session lock file.
     */
    private final long initializationTime = System.currentTimeMillis();

    /** The directory name of the world */
    private final String saveDirectoryName;

    public SaveHandler(File par1File, String par2Str, boolean par3)
    {
        this.worldDirectory = new File(par1File, par2Str);
        this.worldDirectory.mkdirs();
        this.playersDirectory = new File(this.worldDirectory, "players");
        this.mapDataDir = new File(this.worldDirectory, "data");
        this.mapDataDir.mkdirs();
        this.saveDirectoryName = par2Str;

        if (par3)
        {
            this.playersDirectory.mkdirs();
        }

        this.setSessionLock();
    }

    /**
     * Creates a session lock file for this process
     */
    private void setSessionLock()
    {
        try
        {
            File file1 = new File(this.worldDirectory, "session.lock");
            DataOutputStream dataoutputstream = new DataOutputStream(new FileOutputStream(file1));

            try
            {
                dataoutputstream.writeLong(this.initializationTime);
            }
            finally
            {
                dataoutputstream.close();
            }
        }
        catch (IOException ioexception)
        {
            ioexception.printStackTrace();
            throw new RuntimeException("Failed to check session lock, aborting");
        }
    }

    /**
     * Gets the File object corresponding to the base directory of this world.
     */
    public File getWorldDirectory()
    {
        return this.worldDirectory;
    }

    /**
     * Checks the session lock to prevent save collisions
     */
    public void checkSessionLock() throws MinecraftException
    {
        try
        {
            File file1 = new File(this.worldDirectory, "session.lock");
            DataInputStream datainputstream = new DataInputStream(new FileInputStream(file1));

            try
            {
                if (datainputstream.readLong() != this.initializationTime)
                {
                    throw new MinecraftException("The save is being accessed from another location, aborting");
                }
            }
            finally
            {
                datainputstream.close();
            }
        }
        catch (IOException ioexception)
        {
            throw new MinecraftException("Failed to check session lock, aborting");
        }
    }

    /**
     * Returns the chunk loader with the provided world provider
     */
    public IChunkLoader getChunkLoader(WorldProvider par1WorldProvider)
    {
        throw new RuntimeException("Old Chunk Storage is no longer supported.");
    }

    /**
     * Loads and returns the world info
     */
    public WorldInfo loadWorldInfo()
    {
        File file1 = new File(this.worldDirectory, "level.dat");
        NBTTagCompound nbttagcompound;
        NBTTagCompound nbttagcompound1;
        WorldInfo worldInfo = null;

        if (file1.exists())
        {
            try
            {
                nbttagcompound = CompressedStreamTools.readCompressed(new FileInputStream(file1));
                nbttagcompound1 = nbttagcompound.getCompoundTag("Data");
                worldInfo = new WorldInfo(nbttagcompound1);
                FMLCommonHandler.instance().handleWorldDataLoad(this, worldInfo, nbttagcompound);
                return worldInfo;
            }
            catch (Exception exception)
            {
                if (FMLCommonHandler.instance().shouldServerBeKilledQuietly())
                {
                    throw(RuntimeException)exception;
                }

                exception.printStackTrace();
            }
        }

        file1 = new File(this.worldDirectory, "level.dat_old");

        if (file1.exists())
        {
            try
            {
                nbttagcompound = CompressedStreamTools.readCompressed(new FileInputStream(file1));
                nbttagcompound1 = nbttagcompound.getCompoundTag("Data");
                worldInfo = new WorldInfo(nbttagcompound1);
                FMLCommonHandler.instance().handleWorldDataLoad(this, worldInfo, nbttagcompound);
                return worldInfo;
            }
            catch (Exception exception1)
            {
                exception1.printStackTrace();
            }
        }

        return null;
    }

    /**
     * Saves the given World Info with the given NBTTagCompound as the Player.
     */
    public void saveWorldInfoWithPlayer(WorldInfo par1WorldInfo, NBTTagCompound par2NBTTagCompound)
    {
        NBTTagCompound nbttagcompound1 = par1WorldInfo.cloneNBTCompound(par2NBTTagCompound);
        NBTTagCompound nbttagcompound2 = new NBTTagCompound();
        nbttagcompound2.setTag("Data", nbttagcompound1);
        FMLCommonHandler.instance().handleWorldDataSave(this, par1WorldInfo, nbttagcompound2);

        try
        {
            File file1 = new File(this.worldDirectory, "level.dat_new");
            File file2 = new File(this.worldDirectory, "level.dat_old");
            File file3 = new File(this.worldDirectory, "level.dat");
            CompressedStreamTools.writeCompressed(nbttagcompound2, new FileOutputStream(file1));

            if (file2.exists())
            {
                file2.delete();
            }

            file3.renameTo(file2);

            if (file3.exists())
            {
                file3.delete();
            }

            file1.renameTo(file3);

            if (file1.exists())
            {
                file1.delete();
            }
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }

    /**
     * Saves the passed in world info.
     */
    public void saveWorldInfo(WorldInfo par1WorldInfo)
    {
        NBTTagCompound nbttagcompound = par1WorldInfo.getNBTTagCompound();
        NBTTagCompound nbttagcompound1 = new NBTTagCompound();
        nbttagcompound1.setTag("Data", nbttagcompound);
        FMLCommonHandler.instance().handleWorldDataSave(this, par1WorldInfo, nbttagcompound1);

        try
        {
            File file1 = new File(this.worldDirectory, "level.dat_new");
            File file2 = new File(this.worldDirectory, "level.dat_old");
            File file3 = new File(this.worldDirectory, "level.dat");
            CompressedStreamTools.writeCompressed(nbttagcompound1, new FileOutputStream(file1));

            if (file2.exists())
            {
                file2.delete();
            }

            file3.renameTo(file2);

            if (file3.exists())
            {
                file3.delete();
            }

            file1.renameTo(file3);

            if (file1.exists())
            {
                file1.delete();
            }
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }

    /**
     * Writes the player data to disk from the specified PlayerEntityMP.
     */
    public void writePlayerData(EntityPlayer par1EntityPlayer)
    {
        try
        {
            NBTTagCompound nbttagcompound = new NBTTagCompound();
            par1EntityPlayer.writeToNBT(nbttagcompound);
            File file1 = new File(this.playersDirectory, par1EntityPlayer.username + ".dat.tmp");
            File file2 = new File(this.playersDirectory, par1EntityPlayer.username + ".dat");
            CompressedStreamTools.writeCompressed(nbttagcompound, new FileOutputStream(file1));

            if (file2.exists())
            {
                file2.delete();
            }

            file1.renameTo(file2);
        }
        catch (Exception exception)
        {
            MinecraftServer.getServer().getLogAgent().logWarning("Failed to save player data for " + par1EntityPlayer.username);
        }
    }

    /**
     * Reads the player data from disk into the specified PlayerEntityMP.
     */
    public NBTTagCompound readPlayerData(EntityPlayer par1EntityPlayer)
    {
        NBTTagCompound nbttagcompound = this.getPlayerData(par1EntityPlayer.username);

        if (nbttagcompound != null)
        {
            par1EntityPlayer.readFromNBT(nbttagcompound);
        }

        return nbttagcompound;
    }

    /**
     * Gets the player data for the given playername as a NBTTagCompound.
     */
    public NBTTagCompound getPlayerData(String par1Str)
    {
        try
        {
            File file1 = new File(this.playersDirectory, par1Str + ".dat");

            if (file1.exists())
            {
                return CompressedStreamTools.readCompressed(new FileInputStream(file1));
            }
        }
        catch (Exception exception)
        {
            MinecraftServer.getServer().getLogAgent().logWarning("Failed to load player data for " + par1Str);
        }

        return null;
    }

    /**
     * returns null if no saveHandler is relevent (eg. SMP)
     */
    public IPlayerFileData getSaveHandler()
    {
        return this;
    }

    /**
     * Returns an array of usernames for which player.dat exists for.
     */
    public String[] getAvailablePlayerDat()
    {
        String[] astring = this.playersDirectory.list();

        for (int i = 0; i < astring.length; ++i)
        {
            if (astring[i].endsWith(".dat"))
            {
                astring[i] = astring[i].substring(0, astring[i].length() - 4);
            }
        }

        return astring;
    }

    /**
     * Called to flush all changes to disk, waiting for them to complete.
     */
    public void flush() {}

    /**
     * Gets the file location of the given map
     */
    public File getMapFileFromName(String par1Str)
    {
        return new File(this.mapDataDir, par1Str + ".dat");
    }

    /**
     * Returns the name of the directory where world information is saved.
     */
    public String getWorldDirectoryName()
    {
        return this.saveDirectoryName;
    }
}
