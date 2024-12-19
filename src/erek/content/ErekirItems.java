package erek.content;

import arc.graphics.*;
import arc.struct.*;
import mindustry.Vars;
import mindustry.content.Planets;
import mindustry.type.*;

import static mindustry.content.Items.*;
import static mindustry.content.Liquids.*;
import static mindustry.content.Planets.*;

public class ErekirItems {
    public static Item rubidium, bperill, toxide, flameAlloy;
    public static Liquid radon, tungllium;

    public static void load() {
        rubidium = new Item("rubidium", Color.valueOf("ba9191")) {{
            flammability = 1.5f;
            explosiveness = 0.8f;
            hardness = 3;
            cost = 2f;
            healthScaling = 1.4f;
            hiddenOnPlanets = new Planet[]{Planets.serpulo};
        }};
        bperill = new Item("bperill", Color.valueOf("18c99f")) {{
            charge = 1.5f;
            hardness = 4;
            hiddenOnPlanets = new Planet[]{Planets.serpulo};
        }};
        toxide = new Item("toxide", Color.valueOf("cb3874")) {{
            cost = 1.5f;
            healthScaling = 0.75f;
            hiddenOnPlanets = new Planet[]{Planets.serpulo};
        }};
        radon = new Liquid("radon", Color.valueOf("63a94c")){{
            gas = true;
        }};
        tungllium = new Liquid("tungllium", Color.valueOf("628c88")){{
            flammability = 0.4f;
            viscosity = 0.7f;
            this.canStayOn.add(arkycite);
            this.canStayOn.add(neoplasm);
        }};
    }
}
