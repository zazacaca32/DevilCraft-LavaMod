package codechicken.packager;


public @interface SrcPackager {

   String getName() default "@Packager";

   String[] getClasses() default {"@Packager"};

   String[] getMappedDirectories() default {"@Packager"};
}
