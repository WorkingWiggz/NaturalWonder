package com.thekidd.naturalwonder.Developer;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class ShareQuotes {
    private final ArrayList<String> Qoutes = new ArrayList<>();

    ShareQuotes() {
        //Add new Quotes here
        AddtoQuotes("*Insert witty line about the app being good and fun here*:");
        AddtoQuotes("You into D'n'D and tired of writing on paper? Give this a look:");
        AddtoQuotes("Hate ads? Me too, that's why this D&D App doesn't have any:");
        AddtoQuotes("You might be wondering how this App ended Up here, Fun Story...");
        AddtoQuotes("'And that's How we ended up setting a whole city on fire':");
        AddtoQuotes("Want to get up to crazy hijinks's with your friends? Don't want the real world consequences? Play D&D! Oh, and use this app:");
        AddtoQuotes("Your one stop shop for all D&D needs:");
        AddtoQuotes("First Time D&D Player and hate ads? Try this:");
        AddtoQuotes("Give it a lick, it taste like Wonder not Raisins:");
        AddtoQuotes("Let's Go Natural Wonder, Let's Go:");
        AddtoQuotes("Don't know the extensive Lineage of the Hill Dwarf Race? Look it up in here:");
        AddtoQuotes("Don't choose to have multiple apps for your D&D needs, this one does it all... or tries to anyway:");
        AddtoQuotes("Sorry you've Rolled the boring one... No jokes here:");
        AddtoQuotes("'You have become a rock star with your bag pipe playing':");
        AddtoQuotes("Unearth deep lore or at least interesting tidbits about D&D items using this app:");
        AddtoQuotes("Maybe instead of twenty different quotes I could of workshopped something that was actually funny:");
        AddtoQuotes("Did you know that each one of these things is unique, keep sharing to try see them all:");
        AddtoQuotes("You need more D&D App's, I need users... Lets figure out a compromise here:");
        AddtoQuotes("This app is A-mei-zing... I know I hate it too:");
        AddtoQuotes("Please just laugh coming up with 20 of these was rough:");
    }


    void AddtoQuotes(String line) {
        String Link = "https://play.google.com/store/apps/details?id=com.thekidd.naturalwonder&hl=en_US";
        getQoutes().add(line + "\n" + Link);
    }

    public int GetRandomInt(int Low, int High) {
        if (Low >= High) {
            throw new IllegalArgumentException("High end of range must be higher than lower end");
        }
        return ThreadLocalRandom.current().nextInt((High - Low) + 1) + Low;
    }


    public String GetRandomQuote() {
        return getQoutes().get(GetRandomInt(0, getQoutes().size() - 1));
    }

    public ArrayList<String> getQoutes() {
        return Qoutes;
    }

}
