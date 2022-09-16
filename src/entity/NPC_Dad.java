package entity;

import main.GamePanel;
import object.OBJ_DogsBone_NotMagic;

import java.awt.*;
import java.util.Random;

public class NPC_Dad extends Entity {
    public NPC_Dad(GamePanel gp) {
        super(gp);

        direction = "right";
        speed = 1;
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
        randomChummeringDialogues[0] = "I'm just tekin' t'dogs out!";
        randomChummeringDialogues[1] = "Keep that sliding door shut, I'm frozzen\nbowlegged!";
        randomChummeringDialogues[2] = "Shall we go over to Morecambe??";
        randomChummeringDialogues[3] = "What d'ya wanna do then?";
        randomChummeringDialogues[4] = "Did ya put that towel down for't shower?";
        randomChummeringDialogues[5] = "Are you comin' up Colne shopping?";
        randomChummeringDialogues[6] = "I'll just wash up again!";
        randomChummeringDialogues[7] = "Have you emptied 't recycling?";
        randomChummeringDialogues[8] = "I'll be taping that thermostat up this\nyear, can't be having that on wi'\nthese prices!";
        randomChummeringDialogues[9] = "Don't be using \"Constant\" on't heater in\ncase you forget to turn it off!";
        randomChummeringDialogues[10] = "Is Pip making love to his basket again?";
        randomChummeringDialogues[11] = "I'm off dahn't Station later...";
        randomChummeringDialogues[12] = "grumble...vaccines...grumble...";
        randomChummeringDialogues[13] = "You're bloody crackers since ya come off\nthem tablets!";
        randomChummeringDialogues[14] = "Bloody governments useless!";
        randomChummeringDialogues[15] = "That Truss won't be any good, we need\nLabour in!!";
        randomChummeringDialogues[16] = "grumble...energy costs...grumble...";
        randomChummeringDialogues[17] = "I'm not going up there doing that all day...";
        randomChummeringDialogues[18] = "Baldilocks has gone on another cruise\nI see...";
        randomChummeringDialogues[19] = "I'm just gonna sit in't garden!";
        randomChummeringDialogues[20] = "Sharon, I were looking at these on\neBay, what you think??";
        randomChummeringDialogues[21] = "Meggy's getting rid of a cement\nmixer, only wants eighty quid for it...";
        randomChummeringDialogues[22] = "You should cover that camera up with\nthis gaffer tape...";
        randomChummeringDialogues[23] = "Bloody Chinese listening in, have\nyou seen this advert...";
        randomChummeringDialogues[24] = "Tell you what, you won't be havin'\nall them holidays after this electric goes up!";
        randomChummeringDialogues[25] = "What did you say to Pete Squeeze\nlast neyt? He seemed a bit off like...";
        randomChummeringDialogues[26] = "That bloody tosser next door needs\nto fix that fence!";
        randomChummeringDialogues[27] = "Need some new strings for this guitar...";
        randomChummeringDialogues[28] = "When's our Leigh over...Not staying\nall month or owt is he?!";
        randomChummeringDialogues[29] = "I can't park my bloody car outside my\nown house f'that t&@t next door!";
        randomChummeringDialogues[30] = "I were looking at Golf's yesterday, but\nthey're running diesels off t'bloody road\naren't they?!";
        randomChummeringDialogues[31] = "Them electric cars are ticking time bombs...";
        randomChummeringDialogues[32] = "Its all about bloody control...";
        randomChummeringDialogues[33] = "I'll tell you what...";
        randomChummeringDialogues[34] = "Were not going to Spain for winter,\nwe can't bloody afford it!";
        randomChummeringDialogues[35] = "Its that bloody Fauchi and Bill Gates!";
        randomChummeringDialogues[36] = "That bloody investment's gone down again!";
        randomChummeringDialogues[37] = "Lardy's int garden...";
        randomChummeringDialogues[38] = "Oh ya not having her round again are\nya, she never shuts up!";
        randomChummeringDialogues[39] = "There's some reyt cheap houses on\nShetland, need a bit o work like...";
        randomChummeringDialogues[40] = "Is't back door shut?";
        randomChummeringDialogues[41] = "Shunta chopped that tree down, no bloody\nprivacy now!";
        randomChummeringDialogues[42] = "Couldn't stop a pig in a ginnel that one!";
        randomChummeringDialogues[43] = "You only want to go over there for a holiday\nin the sun!";
        randomChummeringDialogues[44] = "Is that Christina every gonna shift all\nher junk out u't shed or what?";
        randomChummeringDialogues[45] = "We'll have to get some blankets cos I'm not\nhaving them radiators on all bloody winter!";
        randomChummeringDialogues[46] = "That telly's never been reyt since\nthat cowboy buggered't satellite dish up\ncuttin' them trees down!";
        randomChummeringDialogues[47] = "I've seen a reyt bloody good guitar\non eBay...";
        randomChummeringDialogues[48] = "There's a draught coming in under\nthat door...";
        randomChummeringDialogues[49] = "Just you watch, petrol and th'electric'll stay\nhigh even after their war excuses finish!";
        randomChummeringDialogues[50] = "Smart TV? I wun't ha' one ginn!";
        randomChummeringDialogues[51] = "She's a face like a bulldog chewin' a wasp!";
        randomChummeringDialogues[52] = "All this Putin...Bet ya owt ya want its\nbloody government and't AmUrricans\ncashin' in!";
        randomChummeringDialogues[53] = "One o't lads at shop says they're gerrin'\nrid of another scaffolding platform...";
        randomChummeringDialogues[54] = "I'll have to put t'bins out...";
        randomChummeringDialogues[55] = "There were some bloody kids looking\nsheepish round't back when I went up...";
        randomChummeringDialogues[56] = "Have yer locked t'back gate?";
        randomChummeringDialogues[57] = "Are ya bringin' t'washing in? Its rainin' out\nthere!";
        randomChummeringDialogues[58] = "I'm just gonna have a run on to that\nshop at Nelson for some bits for't van!";
        randomChummeringDialogues[59] = "Where's old Fleabag??";
        randomChummeringDialogues[60] = "Simpsons is on soon!";
    }

    public void setAction() {

        actionLockCounter ++;

        if (actionLockCounter == 60) {
            Random random = new Random();
            int i = random.nextInt(100) + 1; //pick up a number from 1 to 100

            if (i <= 25) {
                direction = "up";
            }
            if (i > 25 && i <= 50) {
                direction = "down";
            }
            if (i > 50 && i <= 75) {
                direction = "left";
            }
            if (i > 75) {
                direction = "right";
            }
            actionLockCounter = 0;

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

        //character specific stuff here
        super.speak();
    }
}
