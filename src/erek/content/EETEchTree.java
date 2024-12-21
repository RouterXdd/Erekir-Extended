package erek.content;

import arc.struct.Seq;
import mindustry.content.Planets;
import mindustry.content.TechTree;
import mindustry.ctype.*;
import mindustry.game.*;
import mindustry.type.*;

import static mindustry.content.Blocks.*;
import static mindustry.content.Items.*;
import static mindustry.content.Liquids.*;
import static mindustry.content.SectorPresets.*;
import static mindustry.game.Objectives.*;
import static erek.content.ErekirBlocks.*;
import static erek.content.ErekirItems.*;
import static mindustry.content.ErekirTechTree.*;

public class EETEchTree {
    static TechTree.TechNode context = null;
    public static void load() {
        vanillaNode(tungsten, () -> {
            nodeProduce(rubidium, () -> {

            });
            nodeProduce(toxide, () -> {
                nodeProduce(bperill, () -> {

                });
            });
        });
        vanillaNode(hydrogen, () -> {
            nodeProduce(radon, () -> {

            });
            nodeProduce(tungllium, () -> {

            });
        });
        vanillaNode(tungstenWall, () -> {
            node(rubidiumWall, () -> {
                node(rubidiumWallLarge, () -> {

                });
            });
        });
        vanillaNode(coreBastion, () ->{
            node(berylliumProjector, () ->{
                node(tungstenBarrier, () ->{

                });
                node(strikeProjector, () ->{

                });
            });
            node(coreStation, () ->{

            });
        });
        vanillaNode(beamNode, () ->{
            node(rubidiumNode, () ->{

            });
            node(carbideNode, () ->{

            });
            node(cryliteReactor, () ->{

            });
        });
        vanillaNode(reinforcedPump, () ->{
                node(heatPump, () ->{

                });
        });
        vanillaNode(radar, () ->{
            node(rubRadar, () ->{

            });
        });
        vanillaNode(electricHeater, () ->{
            node(hydrogenHeater, () ->{
                node(ozoneHeater, () ->{
                });
            });
            node(toxideKiln, () ->{
                node(bperillExtractor, () ->{
                });
            });
        });
        node(oxidationChamber, () ->{
            node(replacer, () ->{
            });
            node(neoplasmFurnace, () ->{
            });
        });
        vanillaNode(heatRedirector, () ->{
            node(smallRedirector, () ->{
            });
        });
        vanillaNode(plasmaBore, () ->{
            node(plasmaDrill, () ->{
                node(largePlasmaDrill, () ->{

                });
            });
        });
        vanillaNode(breach, () ->{
            node(ecription, () ->{
                node(blaster, () ->{

                });
                node(crossbow, () ->{

                });
            });
        });
        vanillaNode(sublimate, () ->{
            node(peweless, () ->{
                node(conclusion, () ->{
                });
                node(abyssHunter, () ->{
                    node(bonfire, Seq.with(new SectorComplete(karst)), () ->{
                    });
                });
            });
        });
    }
    private static void vanillaNode(UnlockableContent parent, Runnable children){
        context = TechTree.all.find(t -> t.content == parent);
        children.run();
    }

    private static void node(UnlockableContent content, ItemStack[] requirements, Seq<Objectives.Objective> objectives, Runnable children){
        TechTree.TechNode node = new TechTree.TechNode(context, content, requirements);
        if(objectives != null) node.objectives = objectives;

        TechTree.TechNode prev = context;
        context = node;
        children.run();
        context = prev;
    }

    private static void node(UnlockableContent content, ItemStack[] requirements, Runnable children){
        node(content, requirements, null, children);
    }

    private static void node(UnlockableContent content, Seq<Objectives.Objective> objectives, Runnable children){
        node(content, content.researchRequirements(), objectives, children);
    }

    private static void node(UnlockableContent content, Runnable children){
        node(content, content.researchRequirements(), children);
    }

    private static void node(UnlockableContent block){
        node(block, () -> {});
    }

    private static void nodeProduce(UnlockableContent content, Seq<Objectives.Objective> objectives, Runnable children){
        node(content, content.researchRequirements(), objectives.add(new Objectives.Produce(content)), children);
    }

    private static void nodeProduce(UnlockableContent content, Runnable children){
        nodeProduce(content, Seq.with(), children);
    }

    private static void nodeProduce(UnlockableContent content){
        nodeProduce(content, Seq.with(), () -> {});
    }
}
