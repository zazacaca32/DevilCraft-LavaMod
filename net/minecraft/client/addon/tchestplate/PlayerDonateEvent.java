package net.minecraft.client.addon.tchestplate;

import com.google.common.io.Files;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;

import java.io.*;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import net.CFlag;
import net.minecraft.client.addon.tchestplate.overlaygui.ArmorExtInventory;
import net.minecraft.client.addon.tchestplate.packets.PacketMAUpdateInt;
import net.minecraft.client.addon.tchestplate.player.ExtendedPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class PlayerDonateEvent
{
    private static Connection conn = null;
    private static SecretKeySpec hKey;
    private static String tKey;
    private static String host = "localhost";
    private static String bd = "dle";
    private static String user = "root";
    private static String pass = "tytpassword";

    @ForgeSubscribe
    public void JoinToWorldE(EntityJoinWorldEvent e)
    {
        if (e.entity instanceof EntityPlayer)
        {
            if (Utils.isClient())
            {
                LavaChestPlate.log.info("Player " + e.entity.getEntityName() + " join to world " + e.world.provider.getDimensionName() + " at client");
            }
            else
            {
                LavaChestPlate.log.info("Player " + e.entity.getEntityName() + " join to world " + e.world.provider.getDimensionName() + " at server");
                CheckIncoming();
                createNewDatabase("stack.db");
                int balance = GetBalance(e.entity.getEntityName());
                LavaChestPlate.log.info("Remote balance is " + String.valueOf(balance));
                ArmorExtInventory xi = ExtendedPlayer.get((EntityPlayer)e.entity).inventoryExt;
                int old = xi.getCoin();
                xi.setCoin(balance);
                PacketDispatcher.sendPacketToPlayer((new PacketMAUpdateInt((byte)1, xi.getCoin())).makePacket(), (Player)e.entity);
                LavaChestPlate.log.info("Balance corrected, " + String.valueOf(old) + "->" + balance);

                if (conn != null)
                {
                    try
                    {
                        conn.close();
                        conn = null;
                    }
                    catch (SQLException var6)
                    {
                        LavaChestPlate.log.warning("SQL error:" + var6.getMessage());
                        var6.printStackTrace();
                    }
                }
            }
        }
    }

    public static void LoadDBConfig() {
        if (CFlag.isSERVER) {
            String path = "config/lava/db.json";
            File fb = new File(path);
            if (fb.length() == 0){
                JSONObject object = new JSONObject();
                object.put("server", "localhost");
                object.put("name", "dle");
                object.put("username", "root");
                object.put("password", "pass");
                try {
                    FileWriter writer = new FileWriter(fb);
                    writer.write(object.toJSONString());
                    writer.flush();
                    writer.close();
                } catch (IOException ex) {
                    LavaChestPlate.log.warning("[DevilCraft.ru][zazacaca32] Writer error:" + ex.getMessage());
                }
            }
            if (!fb.exists() && !fb.mkdirs()) {
                LavaChestPlate.log.warning("Cannot create directory " + fb.getAbsolutePath());
            } else {
                try {

                    FileReader reader = new FileReader(fb);

                    JSONParser jsonParser = new JSONParser();

                    JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);

                    host = (String) jsonObject.get("server");
                    bd = (String) jsonObject.get("name");
                    user = (String) jsonObject.get("username");
                    pass = (String) jsonObject.get("password");
                } catch (FileNotFoundException e) {
                    LavaChestPlate.log.warning("[DevilCraft.ru][zazacaca32] DB File error:" + e.getMessage());
                    e.printStackTrace();
                } catch (ParseException e) {
                    LavaChestPlate.log.warning("[DevilCraft.ru][zazacaca32] DB File Parse error:" + e.getMessage());
                    e.printStackTrace();
                } catch (IOException e) {
                    LavaChestPlate.log.warning("[DevilCraft.ru][zazacaca32] IO error:" + e.getMessage());
                    e.printStackTrace();
                }
            }
        }
    }

    public static boolean createNewDatabase(String fileName)
    {
        String path = "config/lava/";
        File fb = new File(path);

        if (!fb.exists() && !fb.mkdirs())
        {
            LavaChestPlate.log.warning("Cannot create directory " + fb.getAbsolutePath());
            return false;
        }
        else
        {
            try
            {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                conn = DriverManager.getConnection("jdbc:mysql://" + host + "/" + bd + "?user=" + user + "&password=" + pass);

                if (conn != null)
                {
                    DatabaseMetaData var81 = conn.getMetaData();
                    LavaChestPlate.log.info("Database connected:" + conn + "; version:" + var81.getDatabaseProductVersion());
                    Statement stmt = conn.createStatement();
                    stmt.close();
                }
            }
            catch (InstantiationException var5)
            {
                LavaChestPlate.log.warning("InstantiationException:" + var5.getMessage());
                var5.printStackTrace();
            }
            catch (IllegalAccessException var6)
            {
                LavaChestPlate.log.warning("IllegalAccessException:" + var6.getMessage());
                var6.printStackTrace();
            }
            catch (ClassNotFoundException var7)
            {
                LavaChestPlate.log.warning("SQLite lib not found:" + var7.getMessage());
                var7.printStackTrace();
            }
            catch (SQLException var8)
            {
                LavaChestPlate.log.warning("SQL error:" + var8.getMessage());
                var8.printStackTrace();
            }

            return conn != null;
        }
    }

    private static boolean saveTransactionToDb(String login, long amount, long source, long ttime, String md5)
    {
        try
        {
            if (conn == null)
            {
                return false;
            }
            else
            {
                PreparedStatement var91 = conn.prepareStatement("UPDATE `dle_users` SET `money`= `money`" + amount + " WHERE `name` = \'" + login + "\'");
                var91.executeUpdate();
                var91.close();
                conn.close();
                conn = null;
                return true;
            }
        }
        catch (Exception var9)
        {
            LavaChestPlate.log.warning("SQL error:" + var9.getMessage() + " on line 143");
            return false;
        }
    }

    public static int GetBalance(String name)
    {
        try
        {
            conn = DriverManager.getConnection("jdbc:mysql://" + host + "/" + bd + "?user=" + user + "&password=" + pass);

            if (conn == null)
            {
                return 0;
            }
            else
            {
                PreparedStatement var41 = conn.prepareStatement("SELECT sum(money) AS balance FROM dle_users WHERE name=?");
                var41.setString(1, name);
                ResultSet rez = var41.executeQuery();

                if (!rez.next())
                {
                    return 0;
                }
                else
                {
                    int bal = rez.getInt("balance");
                    rez.close();
                    var41.close();
                    return bal;
                }
            }
        }
        catch (Exception var4)
        {
            LavaChestPlate.log.warning("SQL error:" + var4.getMessage());
            var4.printStackTrace();
            return 0;
        }
    }

    public static boolean setBalance(String name, int amount)
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://" + host + "/" + bd + "?user=" + user + "&password=" + pass);

            if (conn == null)
            {
                return false;
            }
            else
            {
                PreparedStatement var31 = conn.prepareStatement("UPDATE `dle_users` SET `money`= " + amount + " WHERE `name` = \'" + name + "\'");
                var31.executeUpdate();
                var31.close();
                conn.close();
                conn = null;
                return true;
            }
        }
        catch (Exception var3)
        {
            LavaChestPlate.log.warning("SQL error:" + var3.getMessage() + " on line 143");
            return false;
        }
    }

    public static boolean CheckIncoming()
    {
        File incDir = new File("config" + File.separator + "lava" + File.separator + "incoming");

        if (!incDir.exists() && !incDir.mkdirs())
        {
            return false;
        }
        else
        {
            File[] msgs = incDir.listFiles();

            if (msgs.length == 0)
            {
                return false;
            }
            else
            {
                File[] var5 = msgs;
                int var4 = msgs.length;

                for (int var3 = 0; var3 < var4; ++var3)
                {
                    File msgf = var5[var3];
                    String text = decodeMsg(msgf);

                    if (text == null)
                    {
                        LavaChestPlate.log.warning("decodeMsg() result is null, skipping...");
                    }
                    else
                    {
                        JSONParser parser = new JSONParser();
                        String hash = getSaltyMD5(text);

                        if (hash.compareTo(msgf.getName()) != 0)
                        {
                            LavaChestPlate.log.warning("TransactionVerify fault:" + hash + " is not " + msgf.getName());
                            File var19 = new File("config" + File.separator + "lava" + File.separator + "rejected");

                            if (!var19.exists() && !var19.mkdirs())
                            {
                                LavaChestPlate.log.warning("Cannot create directory " + var19.getAbsolutePath());
                            }
                            else
                            {
                                try
                                {
                                    Files.move(msgf, new File(var19, msgf.getName()));
                                }
                                catch (IOException var18)
                                {
                                    LavaChestPlate.log.warning("Cannot move " + msgf.getName() + ":" + var18.getMessage());
                                }
                            }
                        }
                        else
                        {
                            try
                            {
                                LavaChestPlate.log.warning("Transaction " + hash + " verify OK!");
                                JSONObject var171 = (JSONObject)parser.parse(text);
                                String uname = (String)var171.get("username");
                                long amount = ((Long)var171.get("amount")).longValue();
                                long ttime = ((Long)var171.get("ttime")).longValue();
                                long source = ((Long)var171.get("source")).longValue();
                                createNewDatabase("stack.db");

                                if (saveTransactionToDb(uname, amount, source, ttime, msgf.getName()))
                                {
                                    msgf.delete();
                                }
                                else
                                {
                                    LavaChestPlate.log.warning("Error while saving " + msgf.getName() + " to Db");
                                }
                            }
                            catch (ParseException var17)
                            {
                                LavaChestPlate.log.warning("JSON parse error:" + var17.getMessage());
                            }
                        }
                    }
                }

                return false;
            }
        }
    }

    private static String decodeMsg(File msg)
    {
        String line = "";

        if (!msg.canRead())
        {
            return null;
        }
        else
        {
            try
            {
                String var41;
                BufferedReader e;

                for (e = new BufferedReader(new FileReader(msg.getAbsolutePath())); (var41 = e.readLine()) != null; line = line.concat(var41))
                {
                    ;
                }

                e.close();
                return AESdecrypt(line);
            }
            catch (IOException var4)
            {
                var4.printStackTrace();
                return null;
            }
        }
    }

    private static String AESdecrypt(String line)
    {
        try
        {
            SecretKeySpec var31 = new SecretKeySpec(tKey.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(2, var31);
            return new String(cipher.doFinal(DatatypeConverter.parseBase64Binary(line)), StandardCharsets.UTF_8);
        }
        catch (Exception var3)
        {
            System.out.println("Error while decrypting: " + var3.getMessage());
            return null;
        }
    }

    private static String AESencrypt(String jsonString)
    {
        try
        {
            SecretKeySpec var41 = new SecretKeySpec(tKey.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(1, var41);
            byte[] buf = cipher.doFinal(jsonString.getBytes(StandardCharsets.UTF_8));
            return DatatypeConverter.printBase64Binary(buf);
        }
        catch (Exception var4)
        {
            LavaChestPlate.log.severe(var4.getMessage());
            return null;
        }
    }

    public static void setKey(String sk)
    {
        tKey = new String(sk);
    }

    private static String getSaltyMD5(String data)
    {
        try
        {
            String var41 = data.concat(":").concat(tKey);
            MessageDigest e1 = MessageDigest.getInstance("MD5");
            e1.update(var41.getBytes(), 0, var41.length());
            BigInteger i = new BigInteger(1, e1.digest());
            return String.format("%1$032x", new Object[] {i});
        }
        catch (NoSuchAlgorithmException var4)
        {
            System.out.println("MD5 trouble: " + var4.getMessage());
            return null;
        }
    }

    public static void createTransaction(String login, int amount, int source)
    {
        if (conn == null)
        {
            createNewDatabase("stack.db");
        }

        JSONObject j = new JSONObject();
        j.put("username", login);
        j.put("amount", Integer.valueOf(amount));
        j.put("source", Integer.valueOf(source));
        long ttime = System.currentTimeMillis() / 1000L;
        j.put("ttime", Long.valueOf(ttime));
        String md5 = getSaltyMD5(j.toJSONString());
        String encoded = AESencrypt(j.toJSONString());
        LavaChestPlate.log.fine("encrypted string:[" + encoded + "]");

        try
        {
            PreparedStatement var91 = conn.prepareStatement("UPDATE `dle_users` SET `money`= `money`" + amount + " WHERE `name` = \'" + login + "\'");
            var91.executeUpdate();
            var91.close();
            conn.close();
            conn = null;
        }
        catch (SQLException var9)
        {
            LavaChestPlate.log.warning("SQL error:" + var9.getMessage() + " on line 293");
        }
    }
}
