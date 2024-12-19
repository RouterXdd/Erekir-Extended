package erek;

import arc.*;
import arc.util.*;
import erek.content.*;
import mindustry.game.EventType.*;
import mindustry.mod.*;
import mindustry.ui.dialogs.*;

public class ErekirExtended extends Mod{

    public ErekirExtended(){
        Log.info("Loaded ExampleJavaMod constructor.");

        //listen for game load event
        Events.on(ClientLoadEvent.class, e -> {
        });
    }

    @Override
    public void loadContent(){
        Log.info("Erekir moment");
        ErekirAttributes.load();
        ErekirStatusEffects.load();
        ErekirItems.load();
        ErekirUnitTypes.load();
        ErekirBlocks.load();
        EETEchTree.load();
    }

}
