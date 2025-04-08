package erek.content;

import arc.Core;
import mindustry.content.Planets;
import mindustry.gen.Icon;
import mindustry.type.SectorPreset;

import static mindustry.content.Planets.erekir;

public class ErekirSectors {
    public static SectorPreset
    //Main sectors
    connection, coresTrap, substruction, omegaOutpost, abandonedFactory,
                                        bladeBase, TRF,
    rubidiumFortress, rumblingGreatWall,
    unstableSector, fort, controlTrap, lostCode,
    //Encore sectors
    onsetB, aegisB, intersectB;
    public static void load(){
        onsetB = new SectorPreset("onset-b", erekir, 82){{
            difficulty = 2;
        }
            public void loadIcon() {
                if (Icon.terrain != null) this.uiIcon = this.fullIcon = Core.atlas.find("erek-terrain-b");
            }
        };
        aegisB = new SectorPreset("aegis-b", erekir, 26){{
            difficulty = 4;
        }
            public void loadIcon() {
                if (Icon.terrain != null) this.uiIcon = this.fullIcon = Core.atlas.find("erek-terrain-b");
            }
        };
        intersectB = new SectorPreset("intersect-b", erekir, 0){{
            difficulty = 5;
            captureWave = 9;
            attackAfterWaves = true;
        }
            public void loadIcon() {
                if (Icon.terrain != null) this.uiIcon = this.fullIcon = Core.atlas.find("erek-terrain-b");
            }
        };
    }
}
