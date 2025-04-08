package erek;

import arc.*;
import arc.util.*;
import erek.content.*;
import mindustry.game.EventType.*;
import mindustry.mod.*;
import mindustry.ui.dialogs.*;

public class ErekirExtended extends Mod{

    public ErekirExtended(){
        //listen for game load event
        Events.on(ClientLoadEvent.class, e -> {
        });
    }

    @Override
    public void loadContent(){
        ErekirAttributes.load();
        ErekirStatusEffects.load();
        ErekirItems.load();
        ErekirUnitTypes.load();
        ErekirBlocks.load();
        ErekirSectors.load();
        EETEchTree.load();
    }

}
