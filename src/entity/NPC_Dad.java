package entity;

import main.GamePanel;
import main.MissionStates;
import object.OBJ_StainRemover;

import java.awt.*;
import java.util.Objects;

public class NPC_Dad extends Entity {
    public NPC_Dad(GamePanel gp) {
        super(gp);

        name = "Dad";
        direction = "right";
        defaultSpeed = 1;
        speed = defaultSpeed;
        type = type_npc;
        goesTransparentWhenHit = true;

        getImage();
        setDialogue();

        solidArea = new Rectangle(8, 16,32,32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        onPath = true;
    }

    public void getImage() {

        image = setup("/NPC/dad_right1", gp.tileSize, gp.tileSize);
        image2 = setup("/NPC/dad_right2", gp.tileSize, gp.tileSize);

        up1Standard = setup("/NPC/dad_up1", gp.tileSize, gp.tileSize);
        up2Standard = setup("/NPC/dad_up2", gp.tileSize, gp.tileSize);
        down1Standard = setup("/NPC/dad_down1", gp.tileSize, gp.tileSize);
        down2Standard = setup("/NPC/dad_down2", gp.tileSize, gp.tileSize);
        left1Standard = setup("/NPC/dad_left1", gp.tileSize, gp.tileSize);
        left2Standard = setup("/NPC/dad_left2", gp.tileSize, gp.tileSize);
        right1Standard = setup("/NPC/dad_right1", gp.tileSize, gp.tileSize);
        right2Standard = setup("/NPC/dad_right2", gp.tileSize, gp.tileSize);

        up1Guitar = setup("/NPC/dad_up1Guitar", gp.tileSize, gp.tileSize);
        up2Guitar = setup("/NPC/dad_up2Guitar", gp.tileSize, gp.tileSize);
        down1Guitar = setup("/NPC/dad_down1Guitar", gp.tileSize, gp.tileSize);
        down2Guitar = setup("/NPC/dad_down2Guitar", gp.tileSize, gp.tileSize);
        left1Guitar = setup("/NPC/dad_left1Guitar", gp.tileSize, gp.tileSize);
        left2Guitar = setup("/NPC/dad_left2Guitar", gp.tileSize, gp.tileSize);
        right1Guitar = setup("/NPC/dad_right1Guitar", gp.tileSize, gp.tileSize);
        right2Guitar = setup("/NPC/dad_right2Guitar", gp.tileSize, gp.tileSize);

        down1 = down1Standard;
        down2 = down2Standard;
        up1 = up1Standard;
        up2 = up2Standard;
        left1 = left1Standard;
        left2 = left2Standard;
        right1 = right1Standard;
        right2 = right2Standard;

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
        //WEEDING
        dialogueText[61][0] = "Have you seen those weeds out there?";
        dialogueText[61][1] = "I could really do with getting them dug up but I\nhave to order that horse hair for my violin bow.";
        dialogueText[61][2] = "That's gonna tek all day with this bloody laptop!";
        dialogueText[61][3] = "Can you do it?\nThere's a shovel up int' garage you can use.\nBut don't bend it, or lose it!";

        //BONE THROWN DIALOG
        dialogueText[62][0] ="Watch where you bloody throw that\nwill yer!";

        //MOP UP SHOWER WATER
        dialogueText[63][0] = "Cheers for feeding the dogs!";
        dialogueText[63][1] = "Bloody hell you stink, like a mix of chicken\nand sweat!";
        dialogueText[63][2] = "Must be from digging them weeds up.";
        dialogueText[63][3] = "Go and get a shower, I've just fettled it so\nit works now and t'water's been on all morning.";
        dialogueText[63][4] = "Don't use it all though, we can't afford\nto keep putting the bloody heating on!";

        //MAGIC BOOK QUIZ
        dialogueText[64][0] = "I wonder of we still have that old red book\nyou know which I mean?";
        dialogueText[64][1] = "It's that magic quiz book where it knows\nyou and asks questions about your family life.\nDo you remember it?";
        dialogueText[64][2] = "Go and see if you can find it int' book hut\nfor me and we'll have a do at it!";

        dialogueText[65][0] = "Oh here it is!  Smells a bit foisty!\nShall we have a do then?";
        dialogueText[65][1] = "I'll ask you a few questions and\nwe'll see what you know!";
        dialogueText[65][2] = "Just choose the best answer!";

        dialogueText[66][0] = "Reyt here we go then! Here's your first question:\n\nWhat do you have to do when you\nfinish in the shower?";

        dialogueText[67][0] = "Question 2:\n\nWhat have I wasted money on and then let it rot?";
        dialogueText[68][0] = "Question 3:\n\nWhat will I do if you try and have a clear out?";
        dialogueText[69][0] = "Question 4:\n\nWhat do I call normal families having a nice day\nout at the sea side?";
        dialogueText[70][0] = "Last Question:\n\nWhat's the likelihood of us ever moving house?";

        dialogueText[71][0] = "That's right!";
        dialogueText[72][0] = "That's wrong!";
        dialogueText[73][0] = "That's it then.\nYour score was " + gp.player.quizScoreCount + "/5";
        dialogueText[73][1] = "So there you go.  Good init!\nYou can have a do next time!\nI want some peace now!";
        dialogueText[73][2] = "Oh and here's that bloody Chinese stain remover off ebay\nAbsolutely ruined that CD it did, throw it in the grey bin!";

        //DESTROY WASP NEST MISSION
        dialogueText[74][0] = "Did you get that money from the cooker then, how much?";
        dialogueText[74][1] = "Well...erm...bloody idiots gave it to that Asian up\nthe drive to give to us, and then they shot off!";
        dialogueText[74][2] = "Oh bloody hell, so did you get it?";
        dialogueText[74][3] = "Did I heck, he scarpered off din't he!";
        dialogueText[74][4] = "Bloody useless.  Anyway there is a wasps nest in\nLeigh's old room.  If you can get in, get rid of it!";

        //CHUCK WASP NEST AWAY MISSION
        dialogueText[75][0] = "What's that smoke smell? I hope you didn't start\na bloody fire up there!";
        dialogueText[75][1] = "But you've got rid of it, yeah?";
        dialogueText[75][2] = "Yeah its sorted, but the remnants are still\nup there.";
        dialogueText[75][3] = "Well get shut of it then!\nGo and pick it up and chuck it in the green bin.";
        dialogueText[75][4] = "I'm not going near that green bin,\nit's full of bloody spiders!";
        dialogueText[75][5] = "There's no more spiders there, I sorted it.\nJust chuck it in there!";
    }

    public void update() {

        super.update();
        if (gp.currentMap == 0 && (gp.player.worldX >= (17 * gp.tileSize) && gp.player.worldX <= (21 * gp.tileSize)) && (gp.player.worldY >= (13 * gp.tileSize) && gp.player.worldY <= (18 * gp.tileSize))) { //if player in living room
            gp.player.inLivingRoom = true;
            speed = defaultSpeed;
        } else if (gp.currentMap != 0) {
            gp.player.inLivingRoom = false;
            speed = defaultSpeed;
        } else {
            gp.player.inLivingRoom = false;
            speed = 2;
        }
    }

    public void setAction(int goalCol, int goalRow) {
        goalCol = 0;
        goalRow = 0;

        if(onPath && !gp.player.inLivingRoom && !gp.player.dadHasGuitar) {
            right2 = image2;
            if (Objects.equals(gp.player.direction, "up")) { //dog chase player but stay one square behind
                goalCol = (gp.player.worldX + gp.player.solidArea.x)/gp.tileSize;
                goalRow = ((gp.player.worldY + gp.player.solidArea.y)/gp.tileSize) + 2;
            } else if (Objects.equals(gp.player.direction, "down")) {
                goalCol = (gp.player.worldX + gp.player.solidArea.x)/gp.tileSize;
                goalRow = ((gp.player.worldY + gp.player.solidArea.y)/gp.tileSize) - 2;
            } else if (Objects.equals(gp.player.direction, "right")) {
                goalCol = ((gp.player.worldX + gp.player.solidArea.x)/gp.tileSize) - 2;
                goalRow = (gp.player.worldY + gp.player.solidArea.y)/gp.tileSize;
            } else if (Objects.equals(gp.player.direction, "left")) {
                goalCol = ((gp.player.worldX + gp.player.solidArea.x)/gp.tileSize) + 2;
                goalRow = (gp.player.worldY + gp.player.solidArea.y)/gp.tileSize;
            }
            searchPath(goalCol, goalRow);
        } else if (onPath && !gp.player.inLivingRoom) {
            gp.player.dadPlayingGuitar = false;
            goalCol = 21;
            goalRow = 18;
            int actCol = worldX/gp.tileSize+1;
            int actRow = worldY/gp.tileSize+1;

            int xDistance = Math.abs(actCol - goalCol);
            int yDistance = Math.abs(actRow - goalRow);
            int tileDistance = ((xDistance*gp.tileSize) + (yDistance*gp.tileSize));

            if (tileDistance > 1) {
                searchPath(goalCol, goalRow);
            } else {
                speed = 2;
                gp.player.dadHasGuitar = false;
                down1 = down1Standard;
                down2 = down2Standard;
                up1 = up1Standard;
                up2 = up2Standard;
                left1 = left1Standard;
                left2 = left2Standard;
                right1 = right1Standard;
                right2 = right2Standard;
            }
        } else if (onPath && gp.player.dadHasGuitar) {
            down1 = down1Guitar;
            down2 = down2Guitar;
            up1 = up1Guitar;
            up2 = up2Guitar;
            left1 = left1Guitar;
            left2 = left2Guitar;
            right1 = right1Guitar;
            right2 = right2Guitar;

            goalCol = 17;
            goalRow = 14;
            int actCol = worldX/gp.tileSize+1;
            int actRow = worldY/gp.tileSize+1;

            int xDistance = Math.abs(actCol - goalCol);
            int yDistance = Math.abs(actRow - goalRow);
            int tileDistance = ((xDistance*gp.tileSize) + (yDistance*gp.tileSize));

            if (tileDistance > 1) {
                searchPath(goalCol, goalRow);
            } else {
                speed = 0;
                right2 = right1Guitar;
                direction = "right";
                gp.player.dadPlayingGuitar = true;
            }
        } else if (onPath) {

            goalCol = 21;
            goalRow = 18;
            int actCol = worldX/gp.tileSize;
            int actRow = worldY/gp.tileSize;

            int xDistance = Math.abs(actCol - goalCol);
            int yDistance = Math.abs(actRow - goalRow);
            int tileDistance = ((xDistance*gp.tileSize) + (yDistance*gp.tileSize));

            if (tileDistance > 1) {
                searchPath(goalCol, goalRow);
            }
        }
        else {
            if (checkEdgeOfMap(this)) {
                turnEntityAround(this);
            } else {
                getRandomDirection();
            }
        }
    }

    public void speak() {
        switch (gp.player.missionToSet) {
            case MissionStates.MOP_UP_THE_SHOWER_WATER -> gp.player.missionState = MissionStates.MOP_UP_THE_SHOWER_WATER;
            case MissionStates.MAGIC_BOOK_QUIZ -> gp.player.missionState = MissionStates.MAGIC_BOOK_QUIZ;
            case MissionStates.DESTROY_WASP_NEST -> {
                gp.player.missionState = MissionStates.DESTROY_WASP_NEST;
                gp.player.waspNestState = 0;
                gp.aSetter.setObjectAfterStart("WaspNest", 1, 17, 14, false);
            }
            case MissionStates.CHUCK_WASP_NEST_IN_BIN -> {
                int mapNum = 1; //select upstairs map
                gp.player.missionState = MissionStates.CHUCK_WASP_NEST_IN_BIN;
                for (int i = 0; i < gp.obj[1].length; i++) {
                    if (gp.obj[mapNum][i] != null && Objects.equals(gp.obj[mapNum][i].name, "WaspNest")) {
                        gp.obj[mapNum][i].type = type_consumable; //make wasp nest collectable
                    }
                }
            }
        }
        if (gp.player.weedCount > 0 && !gp.player.setShovelFlag) {
            gp.player.missionState = MissionStates.WEEDING_MISSION;
            gp.aSetter.setObjectAfterStart("Garden Shovel", gp.currentMap, 45, 8, false);
            gp.player.setShovelFlag = true;
        }
        switch (gp.player.missionState) {
            case MissionStates.WEEDING_MISSION -> dialogueSet = 61;
            case MissionStates.MOP_UP_THE_SHOWER_WATER -> dialogueSet = 63; //mop shower water mission
            case MissionStates.MAGIC_BOOK_QUIZ -> {
                if (gp.player.missionSubstate == 0) {
                    dialogueSet = 64; //magic book quiz beginning of mission
                } else if (gp.player.missionSubstate == 2) {
                    dialogueSet = 65; //start magic book quiz
                    gp.player.missionSubstate = 3;
                } else if (gp.player.missionSubstate >= 3) {
                    switch(gp.player.missionSubstate) {
                        case 3 -> {
                            gp.player.missionSubstate = 4;
                            dialogueSet = 66;
                        }
                        case 4 -> dialogueSet = 66;
                        case 5 -> dialogueSet = 67;
                        case 6 -> dialogueSet = 68;
                        case 7 -> dialogueSet = 69;
                        case 8 -> dialogueSet = 70;
                        case 9 -> dialogueSet = 73;
                    }
                }
            }
            case MissionStates.DESTROY_WASP_NEST -> dialogueSet = 74; //burn wasp nest mission
            case MissionStates.CHUCK_WASP_NEST_IN_BIN -> dialogueSet = 75; //chuck wasp nest in bin mission
            default -> dialogueSet = chooseRandomDialogueFromSet(this.name, "NormalChat"); //not in a mission
        }
        //character specific stuff here
        facePlayer();
        if (gp.player.missionState == MissionStates.MAGIC_BOOK_QUIZ && gp.player.missionSubstate == 9) {
            for (int i = 0; i < gp.player.inventory.size(); i++) {
                if (Objects.equals(gp.player.inventory.get(i).name, "MagicQuizBook")) {
                    gp.player.inventory.remove(i);
                    break;
                }
            }
            gp.player.inventory.add(new OBJ_StainRemover(gp));

            setDialogue();
            gp.ui.addMessage("You received some Dodgy Chinese Stain Remover!");
            gp.misStat.endMissionTasks(MissionStates.MAGIC_BOOK_QUIZ, true);
        }
        startDialogue(this, dialogueSet);
        if (gp.player.missionSubstate >= 4 && gp.player.missionSubstate < 9) {
            gp.quizSubState = gp.dadQuiz;
            gp.gameState = gp.quizState;
        }
    }
}
