package erek.content;

import arc.graphics.*;
import arc.struct.*;
import mindustry.type.*;

import static mindustry.content.Items.*;

public class ErekirItems {
    public static Item rubidium;

    public static final Seq<Item> newErekirItems = new Seq<>();

    public static void load() {
        rubidium = new Item("rubidium", Color.valueOf("b5b5b5")) {{
            flammability = 1.5f;
            hardness = 3;
            cost = 2f;
            healthScaling = 1.4f;
        }};
        newErekirItems.addAll(
                graphite, thorium, silicon, phaseFabric, surgeAlloy, sand,
                beryllium, tungsten, oxide, carbide, fissileMatter, dormantCyst, rubidium
        );
    }
}
