package main;

import entity.Entity;
import object.*;

public class EntityGenerator {

    final GamePanel gp;

    public EntityGenerator(GamePanel gp) {
        this.gp = gp;
    }

    public Entity getObject(String itemName) {

        return switch (itemName) {
            case OBJ_PipsBone.OBJ_NAME -> new OBJ_PipsBone(gp);
            case OBJ_FrontBackDoorKey.OBJ_NAME -> new OBJ_FrontBackDoorKey(gp);
            case OBJ_GrandmasCardigan.OBJ_NAME -> new OBJ_GrandmasCardigan(gp);
            case OBJ_GuitarAcoustic.OBJ_NAME -> new OBJ_GuitarAcoustic(gp);
            case OBJ_GuitarElectric.OBJ_NAME -> new OBJ_GuitarElectric(gp);
            case OBJ_Lavender_Crocs.OBJ_NAME -> new OBJ_Lavender_Crocs(gp);
            case OBJ_LightPills.OBJ_NAME -> new OBJ_LightPills(gp);
            case OBJ_Pills.OBJ_NAME -> new OBJ_Pills(gp);
            case OBJ_Shovel.OBJ_NAME -> new OBJ_Shovel(gp);
            case OBJ_Spatula.OBJ_NAME -> new OBJ_Spatula(gp);
            case OBJ_InsideDoor.OBJ_NAME -> new OBJ_InsideDoor(gp);
            case OBJ_InsideDoorSideways.OBJ_NAME -> new OBJ_InsideDoorSideways(gp);
            case OBJ_Cupboard1.OBJ_NAME -> new OBJ_Cupboard1(gp);
            case OBJ_Cupboard2.OBJ_NAME -> new OBJ_Cupboard2(gp);
            case OBJ_Music_Center.OBJ_NAME -> new OBJ_Music_Center(gp);
            case OBJ_Bin_Blue.OBJ_NAME -> new OBJ_Bin_Blue(gp);
            case OBJ_Bin_Green.OBJ_NAME -> new OBJ_Bin_Green(gp);
            case OBJ_Bin_Grey.OBJ_NAME -> new OBJ_Bin_Grey(gp);
            case OBJ_BackGateSideways.OBJ_NAME -> new OBJ_BackGateSideways(gp);
            case OBJ_BackGate.OBJ_NAME -> new OBJ_BackGate(gp);
            case OBJ_MumsChair.OBJ_NAME -> new OBJ_MumsChair(gp);
            case OBJ_FrontDoor.OBJ_NAME -> new OBJ_FrontDoor(gp);
            case OBJ_BackDoor.OBJ_NAME -> new OBJ_BackDoor(gp);
            case OBJ_BedMumDadBL.OBJ_NAME -> new OBJ_BedMumDadBL(gp);
            case OBJ_BedMumDadBR.OBJ_NAME -> new OBJ_BedMumDadBR(gp);
            case OBJ_BedMumDadTL.OBJ_NAME -> new OBJ_BedMumDadTL(gp);
            case OBJ_BedMumDadTR.OBJ_NAME -> new OBJ_BedMumDadTR(gp);
            case OBJ_Coin.OBJ_NAME -> new OBJ_Coin(gp);
            case OBJ_LightningBoltStress.OBJ_NAME -> new OBJ_LightningBoltStress(gp);
            case OBJ_SuperCoin.OBJ_NAME -> new OBJ_SuperCoin(gp);
            case OBJ_TelephoneHall.OBJ_NAME -> new OBJ_TelephoneHall(gp);
            case OBJ_AmandaCoat.OBJ_NAME -> new OBJ_AmandaCoat(gp);
            case OBJ_FortyQuidForAndrea.OBJ_NAME -> new OBJ_FortyQuidForAndrea(gp);
            case OBJ_RedBoots.OBJ_NAME -> new OBJ_RedBoots(gp);
            case OBJ_BlockOfWood.OBJ_NAME -> new OBJ_BlockOfWood(gp);
            case OBJ_Hatchet.OBJ_NAME -> new OBJ_Hatchet(gp);
            case OBJ_Chicken.OBJ_NAME -> new OBJ_Chicken(gp);
            case OBJ_ChoppedChicken.OBJ_NAME -> new OBJ_ChoppedChicken(gp);
            case OBJ_Fridge.OBJ_NAME -> new OBJ_Fridge(gp);
            case OBJ_ChoppedChickenPhoebe.OBJ_NAME -> new OBJ_ChoppedChickenPhoebe(gp);
            case OBJ_ChoppedChickenPip.OBJ_NAME -> new OBJ_ChoppedChickenPip(gp);
            case OBJ_KitchenCupboard1.OBJ_NAME -> new OBJ_KitchenCupboard1(gp);
            case OBJ_Mop.OBJ_NAME -> new OBJ_Mop(gp);
            case OBJ_BathLeft.OBJ_NAME -> new OBJ_BathLeft(gp);
            case OBJ_BathRight.OBJ_NAME -> new OBJ_BathRight(gp);
            case OBJ_Camper1.OBJ_NAME -> new OBJ_Camper1(gp);
            case OBJ_Camper2.OBJ_NAME -> new OBJ_Camper2(gp);
            case OBJ_Camper3.OBJ_NAME -> new OBJ_Camper3(gp);
            case OBJ_Camper4.OBJ_NAME -> new OBJ_Camper4(gp);
            case OBJ_Camper5.OBJ_NAME -> new OBJ_Camper5(gp);
            case OBJ_Camper6.OBJ_NAME -> new OBJ_Camper6(gp);
            case OBJ_Camper7.OBJ_NAME -> new OBJ_Camper7(gp);
            case OBJ_Camper8.OBJ_NAME -> new OBJ_Camper8(gp);
            case OBJ_Camper9.OBJ_NAME -> new OBJ_Camper9(gp);
            case OBJ_Camper10.OBJ_NAME -> new OBJ_Camper10(gp);
            case OBJ_Camper11.OBJ_NAME -> new OBJ_Camper11(gp);
            case OBJ_Camper12.OBJ_NAME -> new OBJ_Camper12(gp);
            case OBJ_Bookhut1_Left.OBJ_NAME -> new OBJ_Bookhut1_Left(gp);
            case OBJ_Bookhut1_Center.OBJ_NAME -> new OBJ_Bookhut1_Center(gp);
            case OBJ_Bookhut1_Right.OBJ_NAME -> new OBJ_Bookhut1_Right(gp);
            case OBJ_Bookhut2_Left.OBJ_NAME -> new OBJ_Bookhut2_Left(gp);
            case OBJ_Bookhut2_Center.OBJ_NAME -> new OBJ_Bookhut2_Center(gp);
            case OBJ_Bookhut2_Right.OBJ_NAME -> new OBJ_Bookhut2_Right(gp);
            case OBJ_Bookhut3_Left.OBJ_NAME -> new OBJ_Bookhut3_Left(gp);
            case OBJ_Bookhut3_Center.OBJ_NAME -> new OBJ_Bookhut3_Center(gp);
            case OBJ_Bookhut3_Right.OBJ_NAME -> new OBJ_Bookhut3_Right(gp);
            case OBJ_BookHutKey.OBJ_NAME -> new OBJ_BookHutKey(gp);
            case OBJ_MagicQuizBook.OBJ_NAME -> new OBJ_MagicQuizBook(gp);
            case OBJ_StainRemover.OBJ_NAME -> new OBJ_StainRemover(gp);
            case OBJ_Toolhut1_Left.OBJ_NAME -> new OBJ_Toolhut1_Left(gp);
            case OBJ_Toolhut1_Center.OBJ_NAME -> new OBJ_Toolhut1_Center(gp);
            case OBJ_Toolhut1_Right.OBJ_NAME -> new OBJ_Toolhut1_Right(gp);
            case OBJ_Toolhut2_Left.OBJ_NAME -> new OBJ_Toolhut2_Left(gp);
            case OBJ_Toolhut2_Center.OBJ_NAME -> new OBJ_Toolhut2_Center(gp);
            case OBJ_Toolhut2_Right.OBJ_NAME -> new OBJ_Toolhut2_Right(gp);
            case OBJ_Toolhut3_Left.OBJ_NAME -> new OBJ_Toolhut3_Left(gp);
            case OBJ_Toolhut3_Center.OBJ_NAME -> new OBJ_Toolhut3_Center(gp);
            case OBJ_Toolhut3_Right.OBJ_NAME -> new OBJ_Toolhut3_Right(gp);
            case OBJ_ToolHutKey.OBJ_NAME -> new OBJ_ToolHutKey(gp);
            case OBJ_TV_Remote.OBJ_NAME -> new OBJ_TV_Remote(gp);
            case OBJ_TV_Lounge.OBJ_NAME -> new OBJ_TV_Lounge(gp);
            case OBJ_Lighter.OBJ_NAME -> new OBJ_Lighter(gp);
            case OBJ_Flammable_Spray.OBJ_NAME -> new OBJ_Flammable_Spray(gp);
            case OBJ_WaspNest.OBJ_NAME -> new OBJ_WaspNest(gp);
            case OBJ_FishTankDrawer.OBJ_NAME -> new OBJ_FishTankDrawer(gp);
            case OBJ_KitchenSink.OBJ_NAME -> new OBJ_KitchenSink(gp);
            case OBJ_Bucket.OBJ_NAME -> new OBJ_Bucket(gp);
            case OBJ_Cooker.OBJ_NAME -> new OBJ_Cooker(gp);
            case OBJ_OutdoorGateVertical.OBJ_NAME -> new OBJ_OutdoorGateVertical(gp);
            case OBJ_GarageDoorWhite.OBJ_NAME -> new OBJ_GarageDoorWhite(gp);
            case OBJ_Music_Notes.OBJ_NAME -> new OBJ_Music_Notes(gp);
            case OBJ_CarTopDrive.OBJ_NAME -> new OBJ_CarTopDrive(gp);
            case OBJ_Trampoline.OBJ_NAME -> new OBJ_Trampoline(gp);
            case OBJ_Tutorial_TileSelectorMarker.OBJ_NAME -> new OBJ_Tutorial_TileSelectorMarker(gp);
            case OBJ_Tutorial_Arrow_Right.OBJ_NAME -> new OBJ_Tutorial_Arrow_Right(gp);
            case OBJ_TruckTipCooker.OBJ_NAME -> new OBJ_TruckTipCooker(gp);
            case OBJ_TreeTopGarden_1.OBJ_NAME -> new OBJ_TreeTopGarden_1(gp);
            case OBJ_TreeTopGarden_2.OBJ_NAME -> new OBJ_TreeTopGarden_2(gp);
            case OBJ_TreeTopGarden_3.OBJ_NAME -> new OBJ_TreeTopGarden_3(gp);
            case OBJ_TreeTopGarden_4.OBJ_NAME -> new OBJ_TreeTopGarden_4(gp);
            case OBJ_TreeTopGarden_5.OBJ_NAME -> new OBJ_TreeTopGarden_5(gp);
            default -> null;
        };
    }
}
