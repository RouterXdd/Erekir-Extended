package erek.content;

import mindustry.world.meta.*;

public class ErekirAttributes {
    public static Attribute slag;

    public static void load() {
        slag = Attribute.add("golden");
    }
}
