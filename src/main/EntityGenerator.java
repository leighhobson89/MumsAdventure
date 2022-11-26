package main;

import entity.Entity;
import object.*;

public class EntityGenerator {

    GamePanel gp;

    public EntityGenerator(GamePanel gp) {
        this.gp = gp;
    }

    public Entity getObject(String itemName) {
        Entity obj = null;

        switch(itemName) {
            case OBJ_PipsBone.OBJ_NAME: obj = new OBJ_PipsBone(gp); break;
            case OBJ_FrontBackDoorKey.OBJ_NAME: obj = new OBJ_FrontBackDoorKey(gp); break;
            case OBJ_GrandmasCardigan.OBJ_NAME: obj = new OBJ_GrandmasCardigan(gp); break;
            case OBJ_Guitar1.OBJ_NAME: obj = new OBJ_Guitar1(gp); break;
            case OBJ_Guitar2.OBJ_NAME: obj = new OBJ_Guitar2(gp); break;
            case OBJ_Lavendar_Crocs.OBJ_NAME: obj = new OBJ_Lavendar_Crocs(gp); break;
            case OBJ_LightPills.OBJ_NAME: obj = new OBJ_LightPills(gp); break;
            case OBJ_Pills.OBJ_NAME: obj = new OBJ_Pills(gp); break;
            case OBJ_Shovel.OBJ_NAME: obj = new OBJ_Shovel(gp); break;
            case OBJ_Spatula.OBJ_NAME: obj = new OBJ_Spatula(gp); break;
            case OBJ_InsideDoor.OBJ_NAME: obj = new OBJ_InsideDoor(gp); break;
            case OBJ_InsideDoorSideways.OBJ_NAME: obj = new OBJ_InsideDoorSideways(gp); break;
            case OBJ_Cupboard1.OBJ_NAME: obj = new OBJ_Cupboard1(gp); break;
            case OBJ_Cupboard2.OBJ_NAME: obj = new OBJ_Cupboard2(gp); break;
            case OBJ_Cupboard3.OBJ_NAME: obj = new OBJ_Cupboard3(gp); break;
            case OBJ_Bin_Blue.OBJ_NAME: obj = new OBJ_Bin_Blue(gp); break;
            case OBJ_Bin_Green.OBJ_NAME: obj = new OBJ_Bin_Green(gp); break;
            case OBJ_Bin_Grey.OBJ_NAME: obj = new OBJ_Bin_Grey(gp); break;
            case OBJ_BackGateSideways.OBJ_NAME: obj = new OBJ_BackGateSideways(gp); break;
            case OBJ_BackGate.OBJ_NAME: obj = new OBJ_BackGate(gp); break;
            case OBJ_MumsChair.OBJ_NAME: obj = new OBJ_MumsChair(gp); break;
            case OBJ_FrontDoor.OBJ_NAME: obj = new OBJ_FrontDoor(gp); break;
            case OBJ_BackDoor.OBJ_NAME: obj = new OBJ_BackDoor(gp); break;
            case OBJ_BedMumDadBL.OBJ_NAME: obj = new OBJ_BedMumDadBL(gp); break;
            case OBJ_BedMumDadBR.OBJ_NAME: obj = new OBJ_BedMumDadBR(gp); break;
            case OBJ_BedMumDadTL.OBJ_NAME: obj = new OBJ_BedMumDadTL(gp); break;
            case OBJ_BedMumDadTR.OBJ_NAME: obj = new OBJ_BedMumDadTR(gp); break;
            case OBJ_FrontBackDoorOpen.OBJ_NAME: obj = new OBJ_FrontBackDoorOpen(gp); break;
            case OBJ_Coin.OBJ_NAME: obj = new OBJ_Coin(gp); break;
            case OBJ_LightningBoltStress.OBJ_NAME: obj = new OBJ_LightningBoltStress(gp); break;
            case OBJ_SuperCoin.OBJ_NAME: obj = new OBJ_SuperCoin(gp); break;
            case OBJ_TelephoneHall.OBJ_NAME: obj = new OBJ_TelephoneHall(gp); break;
            case OBJ_AmandaCoat.OBJ_NAME: obj = new OBJ_AmandaCoat(gp); break;
            case OBJ_FortyQuidForAndrea.OBJ_NAME: obj = new OBJ_FortyQuidForAndrea(gp); break;
            case OBJ_RedBoots.OBJ_NAME: obj = new OBJ_RedBoots(gp); break;
            case OBJ_BlockOfWood.OBJ_NAME: obj = new OBJ_BlockOfWood(gp); break;
            case OBJ_Hatchet.OBJ_NAME: obj = new OBJ_Hatchet(gp); break;
            case OBJ_Chicken.OBJ_NAME: obj = new OBJ_Chicken(gp); break;
            case OBJ_ChoppedChicken.OBJ_NAME: obj = new OBJ_ChoppedChicken(gp); break;
            case OBJ_Fridge.OBJ_NAME: obj = new OBJ_Fridge(gp); break;
            case OBJ_ChoppedChickenPhoebe.OBJ_NAME: obj = new OBJ_ChoppedChickenPhoebe(gp); break;
            case OBJ_ChoppedChickenPip.OBJ_NAME: obj = new OBJ_ChoppedChickenPip(gp); break;
            case OBJ_KitchenCupboard1.OBJ_NAME: obj = new OBJ_KitchenCupboard1(gp); break;
            case OBJ_Mop.OBJ_NAME: obj = new OBJ_Mop(gp); break;
            case OBJ_BathLeft.OBJ_NAME: obj = new OBJ_BathLeft(gp); break;
            case OBJ_BathRight.OBJ_NAME: obj = new OBJ_BathRight(gp); break;
            case OBJ_Camper1.OBJ_NAME: obj = new OBJ_Camper1(gp); break;
            case OBJ_Camper2.OBJ_NAME: obj = new OBJ_Camper2(gp); break;
            case OBJ_Camper3.OBJ_NAME: obj = new OBJ_Camper3(gp); break;
            case OBJ_Camper4.OBJ_NAME: obj = new OBJ_Camper4(gp); break;
            case OBJ_Camper5.OBJ_NAME: obj = new OBJ_Camper5(gp); break;
            case OBJ_Camper6.OBJ_NAME: obj = new OBJ_Camper6(gp); break;
            case OBJ_Camper7.OBJ_NAME: obj = new OBJ_Camper7(gp); break;
            case OBJ_Camper8.OBJ_NAME: obj = new OBJ_Camper8(gp); break;
            case OBJ_Camper9.OBJ_NAME: obj = new OBJ_Camper9(gp); break;
            case OBJ_Camper10.OBJ_NAME: obj = new OBJ_Camper10(gp); break;
            case OBJ_Camper11.OBJ_NAME: obj = new OBJ_Camper11(gp); break;
            case OBJ_Camper12.OBJ_NAME: obj = new OBJ_Camper12(gp); break;
            case OBJ_Bookhut1_Left.OBJ_NAME: obj = new OBJ_Bookhut1_Left(gp); break;
            case OBJ_Bookhut1_Center.OBJ_NAME: obj = new OBJ_Bookhut1_Center(gp); break;
            case OBJ_Bookhut1_Right.OBJ_NAME: obj = new OBJ_Bookhut1_Right(gp); break;
            case OBJ_Bookhut2_Left.OBJ_NAME: obj = new OBJ_Bookhut2_Left(gp); break;
            case OBJ_Bookhut2_Center.OBJ_NAME: obj = new OBJ_Bookhut2_Center(gp); break;
            case OBJ_Bookhut2_Right.OBJ_NAME: obj = new OBJ_Bookhut2_Right(gp); break;
            case OBJ_Bookhut3_Left.OBJ_NAME: obj = new OBJ_Bookhut3_Left(gp); break;
            case OBJ_Bookhut3_Center.OBJ_NAME: obj = new OBJ_Bookhut3_Center(gp); break;
            case OBJ_Bookhut3_Right.OBJ_NAME: obj = new OBJ_Bookhut3_Right(gp); break;
            case OBJ_BookHutKey.OBJ_NAME: obj = new OBJ_BookHutKey(gp); break;
        }
        return obj;
    }
}
