package entity;

import main.GamePanel;
import main.MissionStates;

import java.awt.*;
import java.util.Random;

public class NPC_Dad extends Entity {
    public NPC_Dad(GamePanel gp) {
        super(gp);

        name = "Dad";
        direction = "right";
        speed = 1;
        type = type_npc;

//        //comment for not throw bone
//        projectile = new OBJ_DogsBone_NotMagic(gp);
//        //end bone throwing

        getImage();
        setDialogue();

        solidArea = new Rectangle(8, 16,32,32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }

    public void getImage() {

        up1 = setup("/NPC/dad_up1", gp.tileSize, gp.tileSize);
        up2 = setup("/NPC/dad_up2", gp.tileSize, gp.tileSize);
        down1 = setup("/NPC/dad_down1", gp.tileSize, gp.tileSize);
        down2 = setup("/NPC/dad_down2", gp.tileSize, gp.tileSize);
        left1 = setup("/NPC/dad_left1", gp.tileSize, gp.tileSize);
        left2 = setup("/NPC/dad_left2", gp.tileSize, gp.tileSize);
        right1 = setup("/NPC/dad_right1", gp.tileSize, gp.tileSize);
        right2 = setup("/NPC/dad_right2", gp.tileSize, gp.tileSize);

    }

    public void setDialogue() {
        //RANDOM CHUMMERING DIALOGUE SETS
        dialogueText[0][0] = "I'm just tekin' t'dogs out!";
        dialogueText[1][0] = "Keep that sliding door shut, I'm frozzen\nbowlegged!";
        dialogueText[2][0] = "Shall we go over to Morecambe??";
        dialogueText[3][0] = "What d'ya wanna do then?";
        dialogueText[4][0] = "Did ya put that towel down for't shower?";
        dialogueText[5][0] = "Are you comin' up Colne shopping?";
        dialogueText[6][0] = "I'll just wash up again!";
        dialogueText[7][0] = "Have you emptied 't recycling?";
        dialogueText[8][0] = "I'll be taping that thermostat up this\nyear, can't be having that on wi'\nthese prices!";
        dialogueText[9][0] = "Don't be using \"Constant\" on't heater in\ncase you forget to turn it off!";
        dialogueText[10][0] = "Is Pip making love to his basket again?";
        dialogueText[11][0] = "I'm off dahn't Station later...";
        dialogueText[12][0] = "grumble...vaccines...grumble...";
        dialogueText[13][0] = "You're bloody crackers since ya come off\nthem tablets!";
        dialogueText[14][0] = "Bloody governments useless!";
        dialogueText[15][0] = "That Truss won't be any good, we need\nLabour in!!";
        dialogueText[16][0] = "grumble...energy costs...grumble...";
        dialogueText[17][0] = "I'm not going up there doing that all day...";
        dialogueText[18][0] = "Baldilocks has gone on another cruise\nI see...";
        dialogueText[19][0] = "I'm just gonna sit in't garden!";
        dialogueText[20][0] = "Sharon, I were looking at these on\neBay, what you think??";
        dialogueText[21][0] = "Meggy's getting rid of a cement\nmixer, only wants eighty quid for it...";
        dialogueText[22][0] = "You should cover that camera up with\nthis gaffer tape...";
        dialogueText[23][0] = "Bloody Chinese listening in, have\nyou seen this advert...";
        dialogueText[24][0] = "Tell you what, you won't be havin'\nall them holidays after this electric goes up!";
        dialogueText[25][0] = "What did you say to Pete Squeeze\nlast neyt? He seemed a bit off like...";
        dialogueText[26][0] = "That bloody tosser next door needs\nto fix that fence!";
        dialogueText[27][0] = "Need some new strings for this guitar...";
        dialogueText[28][0] = "When's our Leigh over...Not staying\nall month or owt is he?!";
        dialogueText[29][0] = "I can't park my bloody car outside my\nown house f'that t&@t next door!";
        dialogueText[30][0] = "I were looking at Golf's yesterday, but\nthey're running diesels off t'bloody road\naren't they?!";
        dialogueText[31][0] = "Them electric cars are ticking time bombs...";
        dialogueText[32][0] = "Its all about bloody control...";
        dialogueText[33][0] = "I'll tell you what...";
        dialogueText[34][0] = "Were not going to Spain for winter,\nwe can't bloody afford it!";
        dialogueText[35][0] = "Its that bloody Fauchi and Bill Gates!";
        dialogueText[36][0] = "That bloody investment's gone down again!";
        dialogueText[37][0] = "Lardy's int garden...";
        dialogueText[38][0] = "Oh ya not having her round again are\nya, she never shuts up!";
        dialogueText[39][0] = "There's some reyt cheap houses on\nShetland, need a bit o work like...";
        dialogueText[40][0] = "Is't back door shut?";
        dialogueText[41][0] = "Shunta chopped that tree down, no bloody\nprivacy now!";
        dialogueText[42][0] = "Couldn't stop a pig in a ginnel that one!";
        dialogueText[43][0] = "You only want to go over there for a holiday\nin the sun!";
        dialogueText[44][0] = "Is that Christina ever gonna shift\nall her junk out u't shed or what?";
        dialogueText[45][0] = "We'll have to get some blankets cos I'm not\nhaving them radiators on all bloody winter!";
        dialogueText[46][0] = "That telly's never been reyt since\nthat cowboy buggered't satellite dish up\ncuttin' them trees down!";
        dialogueText[47][0] = "I've seen a reyt bloody good guitar\non eBay...";
        dialogueText[48][0] = "There's a draught coming in under\nthat door...";
        dialogueText[49][0] = "Just you watch, petrol and th'electric'll stay\nhigh even after their war excuses finish!";
        dialogueText[50][0] = "Smart TV? I wun't ha' one ginn!";
        dialogueText[51][0] = "She's a face like a bulldog chewin' a wasp!";
        dialogueText[52][0] = "All this Putin...Bet ya owt ya want its\nbloody government and't AmUrricans\ncashin' in!";
        dialogueText[53][0] = "One o't lads at shop says they're gerrin'\nrid of another scaffolding platform...";
        dialogueText[54][0] = "I'll have to put t'bins out...";
        dialogueText[55][0] = "There were some bloody kids looking\nsheepish round't back when I went up...";
        dialogueText[56][0] = "Have yer locked t'back gate?";
        dialogueText[57][0] = "Are ya bringin' t'washing in? Its rainin' out\nthere!";
        dialogueText[58][0] = "I'm just gonna have a run on to that\nshop at Nelson for some bits for't van!";
        dialogueText[59][0] = "Where's old Fleabag??";
        dialogueText[60][0] = "Simpsons is on soon!";

        //MISSION DIALOGUE SETS
        //WEEDING MISSION
        dialogueText[61][0] = "Have you seen those weeds out there?";
        dialogueText[61][1] = "I could really do with getting them dug up but I\nhave to order that horse hair for my violin bow.";
        dialogueText[61][2] = "That's gonna tek all day with this bloody laptop!";
        dialogueText[61][3] = "Can you do it?\nThere's a shovel up int' garage you can use.\nBut don't bend it, or lose it!";

        //BONE THROWN DIALOG
        dialogueText[62][0] ="Watch where you bloody throw that\nwill yer!";


    }

    public void setAction() {

        if (checkEdgeOfMap(this)) {
            turnEntityAround(this);
        } else {
            getRandomDirection();
        }
//        //comment for not throw bone
//        int i = new Random().nextInt(1000) + 1; //odds of throwing a bone
//        if (i > 999 && !projectile.alive && shotAvailableCounter == 30) {
//            projectile.set(worldX, worldY, direction, true, this);
//            gp.projectileList.add(projectile);
//            shotAvailableCounter = 0;
//        // end bone code
//        }
    }

    public void speak() {
        if (gp.player.weedCount > 0) {
            gp.player.missionState = MissionStates.WEEDING_MISSION;
            gp.player.setShovelFlag = true;
        }
        switch(gp.player.missionState) {
            case 1: dialogueSet = 61; break; //weeding mission
            default: dialogueSet = chooseRandomDialogueFromSet(this.name, "NormalChat"); //not in a mission
        }
        //character specific stuff here
        facePlayer();
        startDialogue(this, dialogueSet);
    }
}
