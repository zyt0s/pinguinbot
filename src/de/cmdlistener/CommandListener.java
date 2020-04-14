package de.cmdlistener;

import java.awt.Color;
import java.math.BigInteger;
import java.time.LocalTime;
import java.util.Random;

import net.dv8tion.jda.api.entities.Message;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import at.mukprojects.giphy4j.Giphy;
import at.mukprojects.giphy4j.entity.search.SearchFeed;
import at.mukprojects.giphy4j.exception.GiphyException;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import de.penguin.PenguinMain;

public class CommandListener extends ListenerAdapter {
	Random random = new Random();
	boolean start = false;
	@SuppressWarnings("finally")
	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		Giphy giphy = new Giphy("FtgCWwMAy8jCEAcfkZ3bOabdGFIzMSWi");
		String message = event.getMessage().getContentDisplay();
		String activity = "";
		EmbedBuilder eb = new EmbedBuilder();
		String question = "";
		String gif = "";
		int cpumove = random.nextInt(3);
		int per = random.nextInt(100);
		int off = random.nextInt(8);

		if (event.isFromType(ChannelType.TEXT)) {
			TextChannel channel = event.getTextChannel();

			if (event.getMember().getId().equalsIgnoreCase("149193519045541888")) {
				if (message.contentEquals("!shutdown")) {
					PenguinMain.shardMan.setStatus(OnlineStatus.OFFLINE);
					PenguinMain.shardMan.shutdown();
				}
			}
			if (message.startsWith("!generator ")) {
				String content = message.substring(11);
				String[] chooser = content.split("-");
				try {
					BigInteger max2 = BigInteger.valueOf(Integer.MAX_VALUE);
					BigInteger max1 = new BigInteger(chooser[1]);
					BigInteger min1 = new BigInteger(chooser[0]);

					if (max1.compareTo(max2) > 0 || min1.compareTo(max2) > 0) {
						eb.setTitle("ERROR");
						eb.addField("Achtung!", "Bitte überschreite nicht das Limit von 2147483647!", false);
						eb.setColor(Color.RED);
						channel.sendMessage(eb.build()).queue();
						return;
					}
					int min = Integer.parseInt(chooser[0]);
					int max = Integer.parseInt(chooser[1]);
					if (max - min == 1) {
						int num = random.nextInt(2);
						switch (num) {
						case 0:
							try {
								eb.setTitle("Generator");
								eb.addField("Zahlen Range: ", min + " bis " + max, false);
								eb.addField("Ergebnis: ", chooser[0], false);
								eb.setColor(Color.GREEN);
								channel.sendMessage(eb.build()).queue();
							} catch (Exception e) {
								eb.setTitle("ERROR");
								eb.addField("Achtung!", "Bitte gib die kleinste Zahl zuerst ein.", false);
								eb.setColor(Color.RED);
								channel.sendMessage(eb.build()).queue();
							}
							break;
						case 1:
							try {
								eb.setTitle("Generator");
								eb.addField("Zahlen Range: ", min + " bis " + max, false);
								eb.addField("Ergebnis: ", chooser[1], false);
								eb.setColor(Color.GREEN);
								channel.sendMessage(eb.build()).queue();
							} catch (Exception e) {
								eb.setTitle("ERROR");
								eb.addField("Achtung!", "Bitte gib die kleinste Zahl zuerst ein.", false);
								eb.setColor(Color.RED);
								channel.sendMessage(eb.build()).queue();
							}

							break;
						}

					} else {
						try {
							int num = random.nextInt((max - min +1)) + min;
							String num1 = Integer.toString(num);
							eb.setTitle("Generator");
							eb.addField("Zahlen Range: ", min + " bis " + max, false);
							eb.addField("Ergebnis: ", num1, false);
							eb.setColor(Color.GREEN);
							channel.sendMessage(eb.build()).queue();
						} catch (Exception e) {
							eb.setTitle("ERROR");
							eb.addField("Achtung!", "Bitte gib die kleinste Zahl zuerst ein.", false);
							eb.setColor(Color.RED);
							channel.sendMessage(eb.build()).queue();
						}
					}
				} catch (Exception e) {
					eb.setTitle("ERROR");
					eb.addField("Achtung!","Bitte benutze nur postive Zahlen!" ,false);
					eb.setColor(Color.RED);
					channel.sendMessage(eb.build()).queue();
				}

			}
			if (message.contentEquals("!rps")){
				start = true;
				eb.setTitle("Rock Paper Scissors!");
				eb.addField("Spielen", "Bitte gib Schere, Stein oder Papier ein!", false);

				channel.sendMessage(eb.build()).queue();

			}else if (message.contentEquals("Papier" ) || message.contentEquals("papier")&& start == true) {
			System.out.println("success");
			switch (cpumove){
				case 0: eb.setTitle("RockPaperScissors");
						eb.addField(":robot: Bot Move: ", "Schere", false);
						eb.addField(":person_raising_hand: Player Move: ", "Papier", false);
						eb.addField("Ergebnis: ", "Papier gegen Schere, du verliest!", false);
						channel.sendMessage(eb.build()).queue();
					break;
				case 1:eb.setTitle("RockPaperScissors");
						eb.addField(":robot: Bot Move: ", "Stein", false);
						eb.addField(":person_raising_hand: Player Move: ", "Papier", false);
						eb.addField("Ergebnis: ", "Papier gegen Stein, du gewinnst!", false);
						channel.sendMessage(eb.build()).queue();
					break;
				case 2: eb.setTitle("RockPaperScissors");
						eb.addField(":robot: Bot Move: ", "Papier", false);
						eb.addField(":person_raising_hand: Player Move: ", "Papier", false);
						eb.addField("Ergebnis: ", "Papier gegen Papier, unentschieden!", false);
						channel.sendMessage(eb.build()).queue();
					break;
			}
		}else if (message.contentEquals("Stein") || message.contentEquals("stein")&& start == true) {
			System.out.println("success");
			switch (cpumove){
				case 0: eb.setTitle("RockPaperScissors");
					eb.addField(":robot: Bot Move: ", "Schere", false);
					eb.addField(":person_raising_hand: Player Move: ", "Stein", false);
					eb.addField("Ergebnis: ", "Stein gegen Schere, du gewinnst!", false);
					channel.sendMessage(eb.build()).queue();
					break;
				case 1: eb.setTitle("RockPaperScissors");
					eb.addField(":robot: Bot Move: ", "Stein", false);
					eb.addField(":person_raising_hand: Player Move: ", "Stein", false);
					eb.addField("Ergebnis: ", "Stein gegen Stein, unentschieden!", false);
					channel.sendMessage(eb.build()).queue();
					break;
				case 2: eb.setTitle("RockPaperScissors");
					eb.addField(":robot: Bot Move: ", "Papier", false);
					eb.addField(":person_raising_hand: Player Move: ", "Stein", false);
					eb.addField("Ergebnis: ", "Stein gegen Papier, du verlierst!", false);
					channel.sendMessage(eb.build()).queue();
					break;
			}
		}else if (message.contentEquals("Schere") || message.contentEquals("schere")&& start == true) {
				System.out.println("success");
				switch (cpumove){
					case 0: eb.setTitle("RockPaperScissors");
							eb.addField(":robot: Bot Move: ", "Schere", false);
							eb.addField(":person_raising_hand: Player Move: ", "Schere", false);
							eb.addField("Ergebnis: ", "Schere gegen Schere, unentschieden!", false);
							channel.sendMessage(eb.build()).queue();
						break;
					case 1: eb.setTitle("RockPaperScissors");
							eb.addField(":robot: Bot Move: ", "Stein", false);
							eb.addField(":person_raising_hand: Player Move: ", "Schere", false);
							eb.addField("Ergebnis: ", "Schere gegen Stein, du verlierst!", false);
							channel.sendMessage(eb.build()).queue();
						break;
					case 2: eb.setTitle("RockPaperScissors");
							eb.addField(":robot: Bot Move: ", "Papier", false);
							eb.addField(":person_raising_hand: Player Move: ", "Schere", false);
							eb.addField("Ergebnis: ", "Schere gegen Papier, du gewinnst!", false);
							channel.sendMessage(eb.build()).queue();
						break;
				}
			}
			if (message.equals("!vote")){
				channel.addReactionById("698916246887989309", "U+1F1E6").queue();
			}
			if (message.equals("!nextlaunch")) {
				try {
					Document doc = Jsoup.connect("https://www.rocketlaunch.live/").userAgent("Mozilla/17.0").execute()
							.bufferUp().parse();
					Element temp = doc.select("div.rlt-vehicle").first();
					Element time = doc.select("div.rlt_time").first();
					System.out.println(time);
					Element day = doc.select("div.rlt_date").first();
					String etime = time.text().toString();
					// if (etime.contains("PM")) {
					long ltime = Long.parseLong(etime.substring(0, 5).replace(":", ""));
					System.out.println(ltime);
					System.out.println(ltime);
					ltime = ltime + 200;
					String ftime = Long.toString(ltime);
					System.out.println(ftime);
					eb.setTitle("NextLaunch :rocket:");
					eb.addField("Rocket: ", temp.getElementsByTag("a").first().text(), false);
					if (ltime < 1000) {
						eb.addField("Time: ", "" + ftime.substring(0, 1) + ":" + ftime.substring(1) + " Uhr!", false);
					} else {
						eb.addField("Time: ", "" + ftime.substring(0, 2) + ":" + ftime.substring(2) + " Uhr!", false);
					}
					/*
					 * }else { //long ltime = Long.parseLong(etime.substring(0,5).replace(":", ""));
					 * //ltime = ltime + 100; //String ftime = Long.toString(ltime);
					 * //eb.setTitle("NextLaunch :rocket:"); //eb.addField("Rocket: " ,
					 * temp.getElementsByTag("a").first().text(),false); //eb.addField("Time: ", ""
					 * + ftime.substring(0,2) + ":" + ftime.substring(2) + " Uhr!",false); }
					 */
					eb.addField("Day: ", (String) day.text(), false);
					eb.setColor(Color.RED);
					channel.sendMessage(eb.build()).queue();

				} finally {
					return;
				}
			}
			if (message.contentEquals("!maxi")) {
				for (int i = 0; i < 5; i++) {
					channel.sendMessage("<@149197701269946368> komm wieder her du Sack").queue();

				}
			}
			if (message.contentEquals("!neele")) {
				for (int i = 0; i < 10; i++) {
					channel.sendMessage("<@419575713163640842> RAUS HIER").queue();

				}
			}
			// Setzen einer Activity
			if (message.startsWith("!activity ")) {
				activity = message.substring(10);
				PenguinMain.shardMan.setActivity(Activity.playing(activity));
			}
			if (event.getMember().getId().equalsIgnoreCase("149193519045541888")
					|| (event.getMember().getId().equalsIgnoreCase("149197701269946368")
							|| event.getMember().getId().equalsIgnoreCase("419575713163640842"))) {
				if (message.startsWith("!ebbuilder ")) {
					String content = message.substring(11);
					String[] ebcontent = content.split("/");
					String description = "";

					eb.setTitle(ebcontent[0]);
					for (int i = 1; i < ebcontent.length; i++) {

						description += ebcontent[i];
						description += "\n";
					}
					eb.setDescription(description);
					channel.sendMessage(eb.build()).queue();
					event.getMessage().delete().queue();

				}
			}
			// Entscheidung zwischen mehreren Möglichkeiten.
			if (message.startsWith("!choose ")) {
				String content = message.substring(8);
				String[] chooser = content.split("/");
				int rng = random.nextInt(chooser.length);

				eb.setTitle("!choose");
				eb.addField("Entscheidung zwischen: " + content, "", false);
				eb.addField("Ich entscheide mich für... ", chooser[rng], false);
				channel.sendMessage(eb.build()).queue();
			}

			// Random Gif Generator / Random Search
			if (message.startsWith("!gif ")) {
				String searchGif = message.substring(5);
				searchGif = searchGif.toLowerCase();

				if (searchGif.contains("penis") || searchGif.contains("vagina") || searchGif.contains("sex")
						|| searchGif.contains("ass") || searchGif.contains("schwanz") || searchGif.contains("dick")
						|| searchGif.contains("pussy") || searchGif.contains("hintern") || searchGif.contains("banane")
						|| searchGif.contains("banana") || searchGif.contains("boobs")
						|| searchGif.contains("boobies")) {

					eb.setTitle("MOMENT MAL");
					eb.addField("Was soll das????", "Du Perversling!", false);
					eb.setColor(Color.RED);
					channel.sendMessage(eb.build()).queue();
					return;
				} else
					gif = message.substring(5);
				SearchFeed feed = null;
				try {

					feed = giphy.search(gif, off);

				} catch (GiphyException e) {
					e.printStackTrace();
				}
				try {
					@SuppressWarnings("unused")
					String giff = feed.getDataList().get(off).getImages().getOriginal().getUrl();
				} catch (Exception e) {
					channel.sendMessage("Kein Gif gefunden.").queue();
				}
				String giff = feed.getDataList().get(off).getImages().getOriginal().getUrl();

				long mid = channel.getIdLong();
				eb.setTitle("Gif Search...");
				eb.addField("Deine Suche: " + gif, "", true);
				eb.setImage(giff);
				eb.setColor(Color.CYAN);
				channel.sendMessage(eb.build()).queue();

				System.out.println(giff);
				System.out.println(mid);

			}
			// Random Münzwurf
			// **Gif hinzufügen?**
			if (message.contentEquals("!rng")) {
				int rng = random.nextInt(2);

				if (rng == 0) {
					channel.sendMessage("Du wirfst Kopf!").queue();
				}

				if (rng == 1) {
					channel.sendMessage("Du wirfst Zahl!").queue();
				}
			}
			// Fragen random beantworten
			// *Mehr Antwortmöglichkeiten*
			if (message.startsWith("!question ")) {
				Random random = new Random();
				int rng = random.nextInt(5);
				question = message.substring(9);
				eb.setThumbnail(
						"https://images-ext-1.discordapp.net/external/ZtNp2sPjdILQf8nJo5MESJqwVPaD0CgGALybNZ-swXI/http/icons.iconarchive.com/icons/graphicloads/android-settings/128/question-icon.png");

				if (rng == 0) {
					eb.setTitle("Question");
					eb.addField("Frage: " + question, "Ja!", true);
					channel.sendMessage(eb.build()).queue();

				}

				if (rng == 1) {
					eb.setTitle("Question");
					eb.addField("Frage: " + question, "Nein!", true);
					channel.sendMessage(eb.build()).queue();
				}

				if (rng == 2) {
					eb.setTitle("Question");
					eb.addField("Frage: " + question, "Frag nochmal!", true);
					channel.sendMessage(eb.build()).queue();

				}

				if (rng == 3) {
					eb.setTitle("Question");
					eb.addField("Frage :" + question, "...", true);
					channel.sendMessage(eb.build()).queue();

				}

				if (rng == 4) {
					eb.setTitle("Question");
					eb.addField("Frage: " + question, "Eventuell.", true);
					channel.sendMessage(eb.build()).queue();

				}
			}
			// Banane
			if (message.contains("Banane")) {
				channel.sendMessage(":banana: " + event.getMember().getAsMention()).queue();
			}
			/*
			 * if (message.contains("!role ")) { String content = message.substring(6);
			 * Object[] chooser = content.split("/"); Role role = (Role)chooser[0];
			 * event.getGuild().addRoleToMember(event.getMember(), role).queue();
			 * 
			 * 
			 * }
			 */
			if (message.startsWith("!hug ")) {
				String gif2 = message.substring(5);
				int offf = random.nextInt(7);

				SearchFeed feed = null;
				try {
					feed = giphy.search("anime hug", offf);

				} catch (GiphyException e) {
					e.printStackTrace();
				}
				String giff = feed.getDataList().get(off).getImages().getOriginal().getUrl();
				eb.setTitle("Hug");
				eb.addField(gif2 + ", du wirst geknuddelt von", event.getMember().getAsMention(), true);
				eb.setImage(giff);
				eb.setColor(Color.CYAN);
				channel.sendMessage(eb.build()).queue();

			}

			// Shippt zwei User.
			// ****Auf zwei limitieren***
			if (message.startsWith("!ship ")) {
				String content = message.substring(6);
				String[] chooser = content.split("/");
				eb.setTitle("Shipping....");
				if (per < 29) {
					eb.setImage("https://media.giphy.com/media/Gfza8p6JjrrEI/giphy.gif");
				}
				if (per >= 30 && per <= 39) {
					eb.setImage("https://media.giphy.com/media/3owzWn24wYQndbQmyY/giphy.gif");
				}

				if (per >= 40 && per <= 49) {
					eb.setImage("https://media.giphy.com/media/1XVVLBev87hm0/giphy.gif");
				}

				if (per >= 50 && per <= 69) {
					eb.setImage("https://media.giphy.com/media/lL2tGRssvLc9V76Za9/giphy.gif");
				}

				if (per >= 70 && per <= 79) {
					eb.setImage("https://media.giphy.com/media/hPyONzUYJhLZS/giphy.gif");
				}

				if (per >= 80 && per <= 89) {
					eb.setImage("https://media.giphy.com/media/jRlP4zbERYW5HoCLvX/giphy.gif");
				}

				if (per >= 90 && per <= 100) {
					eb.setImage("https://media.giphy.com/media/4aiuSiPGsgDQc/giphy.gif");
				}

				eb.addField(chooser[0] + " und " + chooser[1] + " lieben sich zu: ", per + "%", true);
				channel.sendMessage(eb.build()).queue();
			}

			// Banane klein
			if (message.contains("banane")) {
				channel.sendMessage(":banana: " + event.getMember().getAsMention()).queue();
			}

			// Help Command
			if (message.contentEquals("!help")) {
				eb.setTitle("Penguin-Bot!");
				eb.addField("!nextlaunch", "[WIP] Zeigt den nächsten Raketenstart an! :rocket:", false);
				eb.addField("!rps", "Starte eine Runde Schere Stein Papier!", false);
				eb.addField("!ship", "Shippt 2 user miteinander.", false);
				eb.addField("!bigbrain", "Sendet ein Big Brain Gif ab.", false);
				eb.addField("!rng", "Wirft eine zufalls basierte Münze.", false);
				eb.addField("!activity", "Setze den Status vom Bot wie du willst!", false);
				eb.addField("!fbi", "FBI open up!", false);
				eb.addField("!choose", "Syntax : Schreibe zwei Möglichkeiten.", false);
				eb.addField("!gif", "Generiert ein random Gif.", false);
				eb.addField("!time", "Zeigt die aktuelle Uhrzeit.", false);
				eb.addField("!question", "Stelle dem Bot eine Frage!", false);
				eb.addField("!myid", "Zeigt dir deine unique DiscordID.", false);
				eb.addField("!hug", "Umarme jemanden!", false);
				eb.addField("!generator", "Syntax: !generator (kleinste Zahl)-(größte Zahl).", false);
				eb.addField("Banane", ":banana:", false);
				eb.addField("Pinguin", ":penguin:", false);
				channel.sendMessage(eb.build()).queue();

			}

			// BigBrain Gif
			if (message.contains("!bigbrain")) {
				channel.sendMessage(
						"https://tenor.com/view/pewdiepie-big-brain-youtube-pewdiepie-big-brain-pewds-gif-14807319")
						.queue();
			}

			// Zeit anzeigen
			// Maybe Zeitzone hinzufügen?
			if (message.contentEquals("!time")) {
				LocalTime now = LocalTime.now();
				if (now.getMinute() < 10) {
					channel.sendMessage("Es ist: " + now.getHour() + ":" + "0" + now.getMinute() + " Uhr!").queue();

				} else
					channel.sendMessage("Es ist: " + now.getHour() + ":" + now.getMinute() + " Uhr!").queue();
			}

			// Pinguin
			if (message.contains("Pinguin")) {
				channel.sendMessage(":penguin: " + event.getMember().getAsMention()).queue();
			}

			// Fbi
			if (message.contains("!fbi")) {
				channel.sendMessage("https://tenor.com/view/fbi-raid-swat-gif-11500735").queue();
			}

			// Pinguin klein
			if (message.contains("pinguin")) {
				channel.sendMessage(":penguin: " + event.getMember().getAsMention()).queue();
			}

			// Zeigt die Discord ID an
			if (message.contains("!myid")) {
				channel.sendMessage(event.getMember().getId()).queue();
			}
		}
	}

}
