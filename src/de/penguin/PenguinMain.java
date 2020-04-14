package de.penguin;

import javax.security.auth.login.LoginException;
import de.cmdlistener.CommandListener;
import de.welcomelistener.WelcomeListener;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;

public class PenguinMain {
	public static ShardManager shardMan;

	public static void main(String[] args) {
		try {
			new PenguinMain();
		} catch (LoginException | IllegalArgumentException e) {
			e.printStackTrace();
		}
	}

	public PenguinMain() throws LoginException, IllegalArgumentException {
		DefaultShardManagerBuilder builder = new DefaultShardManagerBuilder();
		builder.setToken("Njc5NzQxNTQ3MDk2NDQwODMy.Xk2Amg.BLQu_CpEKGgPAgfMrc4Vo6ut0ls");
		builder.setStatus(OnlineStatus.DO_NOT_DISTURB);
		builder.setActivity(Activity.playing("WORK IN PROGRESS"));
		builder.addEventListeners(new CommandListener(), new WelcomeListener());
		shardMan = builder.build();
		System.out.println("Bot ist Online.");

	}
}
