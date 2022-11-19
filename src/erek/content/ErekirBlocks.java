package erek.content;

import arc.graphics.Color;
import arc.math.geom.*;
import mindustry.entities.*;
import mindustry.entities.bullet.*;
import mindustry.entities.part.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.content.*;
import mindustry.world.blocks.defense.*;
import mindustry.world.blocks.distribution.*;
import mindustry.world.blocks.power.*;
import mindustry.world.blocks.storage.*;
import mindustry.world.draw.*;
import mindustry.world.blocks.production.*;
import mindustry.world.blocks.environment.*;
import mindustry.world.blocks.defense.turrets.*;
import mindustry.world.meta.*;

import static arc.graphics.g2d.Draw.*;
import static arc.graphics.g2d.Lines.*;
import static mindustry.type.ItemStack.*;

public class ErekirBlocks {
    public static Block
    //environment
    metalVent, pipe, rubidiumWallOre

    ;
    public static void load(){
        Blocks.regolithWall.attributes.set(ErekirAttributes.slag, 0.1f);
        Blocks.yellowStoneWall.attributes.set(ErekirAttributes.slag, 0.3f);
        metalVent = new SteamVent("metal-vent"){{
            parent = Blocks.metalFloor;
            blendGroup = Blocks.metalFloor;
            attributes.set(Attribute.steam, 1.5f);
        }};
        pipe = new StaticWall("pipe-wall"){{
            variants = 1;
            attributes.set(ErekirAttributes.slag, 1f);
        }};
        rubidiumWallOre = new OreBlock("rubidium-wall-ore"){{
            wallOre = true;
            variants = 2;
            itemDrop = ErekirItems.rubidium;
        }};
    }
}
