package codechicken.core.config;

import codechicken.core.colour.Colour;
import codechicken.core.colour.ColourRGBA;
import codechicken.core.config.ConfigFile;
import codechicken.core.config.ConfigTagParent;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConfigTag extends ConfigTagParent {

   private static final Pattern patternRGB = Pattern.compile("(\\d+),(\\d+),(\\d+)");
   public ConfigTagParent parent;
   public String name;
   public String qualifiedname;
   public String value;
   public boolean brace;
   public boolean newline;
   public int position = Integer.MAX_VALUE;
   private int IDBase;


   public ConfigTag(ConfigTagParent parent, String name) {
      this.parent = parent;
      this.name = name;
      this.qualifiedname = parent.getNameQualifier() + name;
      this.newline = parent.newlinemode == 2;
      parent.addChild(this);
   }

   public String getNameQualifier() {
      return this.qualifiedname + ".";
   }

   public void saveConfig() {
      this.parent.saveConfig();
   }

   public ConfigTag onLoaded() {
      return this;
   }

   public void setValue(String value) {
      this.value = value;
      this.saveConfig();
   }

   public void setDefaultValue(String defaultvalue) {
      if(this.value == null) {
         this.value = defaultvalue;
         this.saveConfig();
      }

   }

   public void setIntValue(int i) {
      this.setValue(Integer.toString(i));
   }

   public void setBooleanValue(boolean b) {
      this.setValue(Boolean.toString(b));
   }

   public void setHexValue(int i) {
      this.setValue("0x" + Long.toString((long)i << 32 >>> 32, 16));
   }

   public void setColourRGB(Colour c) {
      String s;
      for(s = Long.toString((long)c.rgb() << 32 >>> 32, 16); s.length() < 6; s = "0" + s) {
         ;
      }

      this.setValue("0x" + s.toUpperCase());
   }

   public String getValue() {
      return this.value;
   }

   public String getValue(String defaultvalue) {
      this.setDefaultValue(defaultvalue);
      return this.value;
   }

   public int getIntValue() {
      return Integer.parseInt(this.getValue());
   }

   public int getIntValue(int defaultvalue) {
      if(this.value == null) {
         this.setIntValue(defaultvalue);
      }

      try {
         return this.getIntValue();
      } catch (NumberFormatException var3) {
         this.setIntValue(defaultvalue);
         return this.getIntValue();
      }
   }

   public boolean getBooleanValue() {
      String value = this.getValue();
      if(value != null && (value.equalsIgnoreCase("true") || value.equalsIgnoreCase("yes"))) {
         return true;
      } else if(value != null && (value.equalsIgnoreCase("false") || value.equalsIgnoreCase("no"))) {
         return false;
      } else {
         throw new NumberFormatException(this.qualifiedname + ".value=" + value);
      }
   }

   public boolean getBooleanValue(boolean defaultvalue) {
      if(this.value == null) {
         this.setBooleanValue(defaultvalue);
      }

      try {
         return this.getBooleanValue();
      } catch (NumberFormatException var3) {
         this.setBooleanValue(defaultvalue);
         return this.getBooleanValue();
      }
   }

   public int getHexValue() {
      return (int)Long.parseLong(this.getValue().replace("0x", ""), 16);
   }

   public int getHexValue(int defaultvalue) {
      if(this.value == null) {
         this.setHexValue(defaultvalue);
      }

      try {
         return this.getHexValue();
      } catch (NumberFormatException var3) {
         this.setHexValue(defaultvalue);
         return this.getHexValue();
      }
   }

   public Colour getColourRGB() {
      Matcher matcherRGB = patternRGB.matcher(this.getValue().replaceAll("\\s", ""));
      return matcherRGB.matches()?new ColourRGBA(Integer.parseInt(matcherRGB.group(1)), Integer.parseInt(matcherRGB.group(2)), Integer.parseInt(matcherRGB.group(3)), 255):new ColourRGBA(this.getHexValue() << 8 | 255);
   }

   public Colour getColourRGB(Colour defaultvalue) {
      if(this.value == null) {
         this.setColourRGB(defaultvalue);
      }

      try {
         return this.getColourRGB();
      } catch (NumberFormatException var3) {
         this.setColourRGB(defaultvalue);
         return this.getColourRGB();
      }
   }

   public void save(PrintWriter writer, int tabs, String bracequalifier, boolean first) {
      String vname;
      if(this.qualifiedname.contains(".") && bracequalifier.length() > 0) {
         vname = this.qualifiedname.substring(bracequalifier.length() + 1).replace(' ', '_');
      } else {
         vname = this.qualifiedname;
      }

      vname.replace(' ', '_');
      if(this.newline && !first) {
         ConfigFile.writeLine(writer, "", tabs);
      }

      this.writeComment(writer, tabs);
      if(this.value != null) {
         ConfigFile.writeLine(writer, vname + "=" + this.value, tabs);
      }

      if(this.hasChildTags()) {
         if(this.brace) {
            if(this.value == null) {
               ConfigFile.writeLine(writer, vname, tabs);
            }

            ConfigFile.writeLine(writer, "{", tabs);
            this.saveTagTree(writer, tabs + 1, this.qualifiedname.replace(' ', '_'));
            ConfigFile.writeLine(writer, "}", tabs);
         } else {
            this.saveTagTree(writer, tabs, bracequalifier);
         }

      }
   }

   public ConfigTag setComment(String comment) {
      super.setComment(comment);
      return this;
   }

   public ConfigTag setSortMode(int mode) {
      super.setSortMode(mode);
      return this;
   }

   public ConfigTag setNewLine(boolean b) {
      this.newline = b;
      this.saveConfig();
      return this;
   }

   public ConfigTag useBraces() {
      this.brace = true;
      if(this.parent.newlinemode == 1) {
         this.newline = true;
      }

      this.saveConfig();
      return this;
   }

   public ConfigTag setPosition(int pos) {
      this.position = pos;
      this.saveConfig();
      return this;
   }

   public boolean containsTag(String tagname) {
      return this.getTag(tagname, false) != null;
   }

   public int getId(String name, int defaultValue) {
      return this.getTag(name).getIntValue(defaultValue);
   }

   public int getId(String name) {
      int ret = this.getId(name, this.IDBase);
      this.IDBase = ret + 1;
      return ret;
   }

   public int getAcheivementId(String name, int defaultValue) {
      return this.getTag(name).getIntValue(defaultValue);
   }

   public ConfigTag setBaseID(int i) {
      this.IDBase = i;
      return this;
   }
}
