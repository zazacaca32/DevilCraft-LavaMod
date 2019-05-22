package codechicken.core.config;

import codechicken.core.config.ConfigFile;
import codechicken.core.config.ConfigTag;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

public abstract class ConfigTagParent {

   private TreeMap childtags = new TreeMap();
   public String comment;
   public int sortMode = 0;
   public int newlinemode = 1;


   public abstract void saveConfig();

   public abstract String getNameQualifier();

   public ConfigTagParent setComment(String comment) {
      this.comment = comment;
      this.saveConfig();
      return this;
   }

   public ConfigTagParent setSortMode(int mode) {
      this.sortMode = mode;
      this.saveConfig();
      return this;
   }

   public ConfigTagParent setNewLineMode(int mode) {
      this.newlinemode = mode;
      Iterator var3 = this.childtags.entrySet().iterator();

      while(var3.hasNext()) {
         Entry entry = (Entry)var3.next();
         ConfigTag tag = (ConfigTag)entry.getValue();
         if(this.newlinemode == 0) {
            tag.newline = false;
         } else if(this.newlinemode == 1) {
            tag.newline = tag.brace;
         } else if(this.newlinemode == 2) {
            tag.newline = true;
         }
      }

      this.saveConfig();
      return this;
   }

   public Map childTagMap() {
      return this.childtags;
   }

   public boolean hasChildTags() {
      return !this.childtags.isEmpty();
   }

   public boolean containsTag(String tagname) {
      return this.getTag(tagname, false) != null;
   }

   public ConfigTag getNewTag(String tagname) {
      return new ConfigTag(this, tagname);
   }

   public ConfigTag getTag(String tagname, boolean create) {
      tagname = tagname.replace('_', ' ');
      int dotpos = tagname.indexOf(".");
      String basetagname = dotpos == -1?tagname:tagname.substring(0, dotpos);
      ConfigTag basetag = (ConfigTag)this.childtags.get(basetagname);
      if(basetag == null) {
         if(!create) {
            return null;
         }

         basetag = this.getNewTag(basetagname);
         this.saveConfig();
      }

      return dotpos == -1?basetag:basetag.getTag(tagname.substring(dotpos + 1), create);
   }

   public ConfigTag getTag(String tagname) {
      return this.getTag(tagname, true);
   }

   public boolean removeTag(String tagname) {
      ConfigTag tag = this.getTag(tagname, false);
      if(tag == null) {
         return false;
      } else {
         int dotpos = tagname.lastIndexOf(".");
         String lastpart = dotpos == -1?tagname:tagname.substring(dotpos + 1, tagname.length());
         return tag.parent != null?tag.parent.childtags.remove(lastpart) != null:false;
      }
   }

   public void addChild(ConfigTag tag) {
      this.childtags.put(tag.name, tag);
   }

   public ArrayList getSortedTagList() {
      ArrayList taglist = new ArrayList(this.childtags.size());
      Iterator var3 = this.childtags.entrySet().iterator();

      while(var3.hasNext()) {
         Entry tag = (Entry)var3.next();
         taglist.add((ConfigTag)tag.getValue());
      }

      Collections.sort(taglist, new ConfigTagParent.TagOrderComparator(this.sortMode));
      return taglist;
   }

   public void loadChildren(BufferedReader reader) {
      String comment = "";
      String bracequalifier = "";

      try {
         while(true) {
            String e = ConfigFile.readLine(reader);
            if(e == null) {
               break;
            }

            if(e.startsWith("#")) {
               if(comment != null && !comment.equals("")) {
                  comment = comment + "\n" + e.substring(1);
               } else {
                  comment = e.substring(1);
               }
            } else if(e.contains("=")) {
               String qualifiedname = e.substring(0, e.indexOf("="));
               this.getTag(qualifiedname).onLoaded().setComment(comment).setValue(e.substring(e.indexOf("=") + 1));
               comment = "";
               bracequalifier = qualifiedname;
            } else if(e.equals("{")) {
               this.getTag(bracequalifier).setComment(comment).useBraces().loadChildren(reader);
               comment = "";
               bracequalifier = "";
            } else {
               if(e.equals("}")) {
                  break;
               }

               bracequalifier = e;
            }
         }

      } catch (IOException var6) {
         throw new RuntimeException(var6);
      }
   }

   public void saveTagTree(PrintWriter writer, int tabs, String bracequalifier) {
      boolean first = true;

      for(Iterator var6 = this.getSortedTagList().iterator(); var6.hasNext(); first = false) {
         ConfigTag tag = (ConfigTag)var6.next();
         tag.save(writer, tabs, bracequalifier, first);
      }

   }

   public void writeComment(PrintWriter writer, int tabs) {
      if(this.comment != null && !this.comment.equals("")) {
         String[] comments = this.comment.split("\n");

         for(int i = 0; i < comments.length; ++i) {
            ConfigFile.writeLine(writer, "#" + comments[i], tabs);
         }
      }

   }

   public static class TagOrderComparator implements Comparator {

      int sortMode;


      public TagOrderComparator(int sortMode) {
         this.sortMode = sortMode;
      }

      public int compare(ConfigTag o1, ConfigTag o2) {
         if(o1.position != o2.position) {
            return this.compareInt(o1.position, o2.position);
         } else if(o1.brace != o2.brace) {
            return o1.brace?1:-1;
         } else {
            switch(this.sortMode) {
            case 1:
               if(o1.value == o2.value) {
                  return 0;
               } else if(o1.value == null) {
                  return 1;
               } else {
                  if(o2.value == null) {
                     return -1;
                  }

                  return o1.value.compareTo(o2.value);
               }
            default:
               return o1.name.compareTo(o2.name);
            }
         }
      }

      private int compareInt(int a, int b) {
         return a == b?0:(a < b?-1:1);
      }

	@Override
	public int compare(Object arg0, Object arg1) {
		// TODO Auto-generated method stub
		return 0;
	}
   }
}
