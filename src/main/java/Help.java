import java.awt.Color;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class Help extends ListenerAdapter {

	public void onMessageReceived(MessageReceivedEvent event) {
		// TextChannel user로 부터 입력받은 채팅 내용을 저장하는 변수 Message 및 Channel 변수
		String msg = event.getMessage().getContent();
		TextChannel channel = event.getTextChannel();
		
		String iconUrl = "https://i.imgur.com/TQK24g1.png";
		String thumbUrl = "https://i.imgur.com/RdceCxg.png";
		
		if(msg.startsWith("!지휘소")){
			EmbedBuilder eb = new EmbedBuilder();
			eb.setColor(Color.WHITE);
			eb.setAuthor("소전항로 지휘소", null, iconUrl);
			eb.setDescription("**소녀전선 명령어: !소전**\n"
					+"**벽람항로 명령어: !벽람**\n");
			eb.setThumbnail(thumbUrl);
			MessageEmbed e = eb.build();
			channel.sendMessage(e).queue();
		}else if(msg.startsWith("!소전")){
			EmbedBuilder eb = new EmbedBuilder();
			eb.setColor(Color.WHITE);
			eb.setAuthor("소녀전선 지휘소", null, iconUrl);
			eb.setDescription("**인형제조시간: !인형 제조시간(20 ~ 810)**\n"
					+"**장비제조시간: !장비 제조시간(5 ~ 540)**\n");
			eb.setImage("https://i.imgur.com/qg0MFZK.png");
			MessageEmbed e = eb.build();
			channel.sendMessage(e).queue();
		}else if(msg.startsWith("!벽람")){
			EmbedBuilder eb = new EmbedBuilder();
			eb.setColor(Color.WHITE);
			eb.setAuthor("벽람항로 지휘소", null, iconUrl);
			eb.setDescription("**벽람항로 지휘소 설치중...**");
			eb.setImage("https://i.imgur.com/oDt2vAm.png");
			MessageEmbed e = eb.build();
			channel.sendMessage(e).queue();
		}
	}
}
