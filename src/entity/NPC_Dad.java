package entity;

import main.GamePanel;

import java.util.Random;

public class NPC_Dad extends Entity {
    public NPC_Dad(GamePanel gp) {
        super(gp);

        direction = "right";
        speed = 1;

        getImage();
        setDialogue();
    }

    public void getImage() {

        up1 = setup("/NPC/dad_up1");
        up2 = setup("/NPC/dad_up2");
        down1 = setup("/NPC/dad_down1");
        down2 = setup("/NPC/dad_down2");
        left1 = setup("/NPC/dad_left1");
        left2 = setup("/NPC/dad_left2");
        right1 = setup("/NPC/dad_right1");
        right2 = setup("/NPC/dad_right2");

    }

    public void setDialogue() {
        dialogues[0] = "I'm just tekin' t'dogs out!";
        dialogues[1] = "Keep that sliding door shut, I'm frozzen\nbowlegged!";
        dialogues[2] = "Shall we go over to Morecambe??";
        dialogues[3] = "What d'ya wanna do then?";
        dialogues[4] = "Did ya put that towel down for't shower?";
        dialogues[5] = "Are you comin' up Colne shopping?";
        dialogues[6] = "I'll just wash up again!";
        dialogues[7] = "Have you emptied 't recycling?";
        dialogues[8] = "I'll be taping that thermostat up this\nyear, can't be having that on wi'\nthese prices!";
        dialogues[9] = "Don't be using \"Constant\" on't heater in\ncase you forget to turn it off!";
        dialogues[10] = "Is Pip making love to his basket again?";
        dialogues[11] = "I'm off dahn't Station later...";
        dialogues[12] = "grumble...vaccines...grumble...";
        dialogues[13] = "You're bloody crackers since ya come off\nthem tablets!";
        dialogues[14] = "Bloody governments useless!";
        dialogues[15] = "That Truss won't be any good, we need\nLabour in!!";
        dialogues[16] = "grumble...energy costs...grumble...";
        dialogues[17] = "I'm not going up there doing that all day...";
        dialogues[18] = "Baldilocks has gone on another cruise\nI see...";
        dialogues[19] = "I'm just gonna sit in't garden!";
        dialogues[20] = "Sharon, I were looking at these on\neBay, what you think??";
        dialogues[21] = "Meggy's getting rid of a cement\nmixer, only wants eighty quid for it...";
        dialogues[22] = "You should cover that camera up with\nthis gaffer tape...";
        dialogues[23] = "Bloody Chinese listening in, have\nyou seen this advert...";
        dialogues[24] = "Tell you what, you won't be havin'\nall them holidays after this electric goes up!";
        dialogues[25] = "What did you say to Pete Squeeze\nlast neyt? He seemed a bit off like...";
        dialogues[26] = "That bloody tosser next door needs\nto fix that fence!";
        dialogues[27] = "Need some new strings for this guitar...";
        dialogues[28] = "When's our Leigh over...Not staying\nall month or owt is he?!";
        dialogues[29] = "I can't park my bloody car outside my\nown house f'that t&@t next door!";
        dialogues[30] = "I were looking at Golf's yesterday, but\nthey're running diesels off t'bloody road\naren't they?!";
        dialogues[31] = "Them electric cars are ticking time bombs...";
        dialogues[32] = "Its all about bloody control...";
        dialogues[33] = "I'll tell you what...";
        dialogues[34] = "Were not going to Spain for winter,\nwe can't bloody afford it!";
        dialogues[35] = "Its that bloody Fauchi and Bill Gates!";
        dialogues[36] = "That bloody investment's gone down again!";
        dialogues[37] = "Lardy's int garden...";
        dialogues[38] = "Oh ya not having her round again are\nya, she never shuts up!";
        dialogues[39] = "There's some reyt cheap houses on\nShetland, need a bit o work like...";
        dialogues[40] = "Is't back door shut?";
        dialogues[41] = "Shunta chopped that tree down, no bloody\nprivacy now!";
        dialogues[42] = "Couldn't stop a pig in a ginnel that one!";
        dialogues[43] = "You only want to go over there for a holiday\nin the sun!";
        dialogues[44] = "Is that Christina every gonna shift all\nher junk out u't shed or what?";
        dialogues[45] = "We'll have to get some blankets cos I'm not\nhaving them radiators on all bloody winter!";
        dialogues[46] = "That telly's never been reyt since\nthat cowboy buggered't satellite dish up\ncuttin' them trees down!";
        dialogues[47] = "I've seen a reyt bloody good guitar\non eBay...";
        dialogues[48] = "There's a draught coming in under\nthat door...";
        dialogues[49] = "Just you watch, petrol and th'electric'll stay\nhigh even after their war excuses finish!";
        dialogues[50] = "Smart TV? I wun't ha' one ginn!";
        dialogues[51] = "She's a face like a bulldog chewin' a wasp!";
        dialogues[52] = "All this Putin...Bet ya owt ya want its\nbloody government and't AmUrricans\ncashin' in!";
        dialogues[53] = "One o't lads at shop says they're gerrin'\nrid of another scaffolding platform...";
        dialogues[54] = "I'll have to put t'bins out...";
        dialogues[55] = "There were some bloody kids looking\nsheepish round't back when I went up...";
        dialogues[56] = "Have yer locked t'back gate?";
        dialogues[57] = "Are ya bringin' t'washing in? Its rainin' out\nthere!";
        dialogues[58] = "I'm just gonna have a run on to that paki shop\nfor some bits for't van!";
        dialogues[59] = "Where's old Fleabag??";
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

    }

    public void speak() {

        //character specific stuff here
        super.speak();
    }
}
