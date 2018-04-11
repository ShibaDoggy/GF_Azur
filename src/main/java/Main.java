import javax.security.auth.login.LoginException;

import GF_DataBase.GF_Doll;
import GF_DataBase.GF_Equip;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.entities.impl.GameImpl;
import net.dv8tion.jda.core.exceptions.RateLimitedException;

public class Main {
	public static void main(String[] args) {
		JDA discord = null;
		
		try {
			discord = new JDABuilder(AccountType.BOT).setToken(BotToken.discordToken).setMaxReconnectDelay(5000)
					.setAutoReconnect(true).buildBlocking();
			discord.getPresence().setGame(new GameImpl("!지휘소", null, Game.GameType.DEFAULT));
		} catch (LoginException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (RateLimitedException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		discord.addEventListener(new Help(),new GF_Doll(),new GF_Equip());
	}
}