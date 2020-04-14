package de.welcomelistener;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class WelcomeListener extends ListenerAdapter {
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
        Member member = event.getMember();
        TextChannel channel = event.getGuild().getTextChannelById("687788697902383135");
        EmbedBuilder eb = new EmbedBuilder();
        String penguinID = "671331210773004288";
        if ((channel = event.getGuild().getTextChannelById("687788697902383135")) != null)  {

                eb.setTitle("Willkommen!");
                eb.addField("Hallo " + event.getMember().getEffectiveName() + " :wave:", "Herzlich willkommen auf diesem Server! :D", false);
                eb.addField(":rotating_light: Wichtig :rotating_light:", "Bitte lies dir die angepinnten Nachrichten und #server-updates durch!", false);
                eb.addField("Eine Sache noch :penguin:", "Stell dich doch in #vorstellungsrunde vor!", false);

        channel.sendMessage(eb.build()).queue();
        return;
    }else{
            channel = event.getGuild().getDefaultChannel();
            if ((channel = event.getGuild().getDefaultChannel()) != null){
                    eb.setTitle("Willkommen!");
                    eb.addField("Hallo " + event.getMember().getEffectiveName() + " :wave:", "Herzlich willkommen auf diesem Server! :D", false);
                    channel.sendMessage(eb.build()).queue();
            }
        }
}
    }

