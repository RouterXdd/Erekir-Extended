package erek.classes.blocks.storage;

import arc.util.Nullable;
import erek.content.ErekirBlocks;
import mindustry.world.Block;
import mindustry.world.blocks.payloads.Constructor;

public class BombConstructor extends Constructor {
    public BombConstructor(String name) {
        super(name);
    }

    public class BombConstructorBuild extends ConstructorBuild{
        @Override
        public @Nullable Block recipe(){
            return ErekirBlocks.bomb;
        }
    }
}
