package GF_DataBase;

import java.awt.Color;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GF_Doll extends ListenerAdapter {

	public Connection connection(String dbFileName) throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
		String url = "jdbc:sqlite:database/gfDB.db";
		Connection conn = DriverManager.getConnection(url);
		return conn;
	}

	public void onMessageReceived(MessageReceivedEvent event) {

		// TextChannel user로 부터 입력받은 채팅 내용을 저장하는 변수 Message 및 Channel 변수
		String msg = event.getMessage().getContent();
		TextChannel channel = event.getTextChannel();

		// 인형조건식으로 알기 위한 변수
		String chat = new String("!인형");

		// 입력받은 Text를 인형시간만 알기 위해 parsing ex)!인형 000 에서 000 부분
		String dollTime = msg.split(" ")[1];

		// EmbedBuilder에서 사용하기 위한 변수
		String name = "인형제조시: " + dollTime;
		String iconUrl = "https://i.imgur.com/RdceCxg.png";
		String thumbUrl = "https://i.imgur.com/TQK24g1.png";
		String UrlProd = null; // DB에서 받아온 Production을 받아서 Emb에서 사용
		String UrlPNG = null; // DB에서 받아온 PNG를 받아서 Emb에서 사용
		String UrlTime = null; // DB에서 받아온 Time 시간 Checking

		// DB관련 code
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection("jdbc:sqlite:database/gfDB.db");
			statement = conn.prepareStatement("select * from Doll where Time=" + dollTime + ";");
			rs = statement.executeQuery();

			UrlProd = rs.getString("Production");
			UrlPNG = rs.getString("PNG");

			// Checking 추가
			UrlTime = rs.getString("Time");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Discord 봇 관련
		if (msg.startsWith(chat) == true) {
			if (UrlTime != null) {
				if (msg.equalsIgnoreCase(chat + " " + dollTime)) {
					EmbedBuilder eb = new EmbedBuilder();
					eb.setColor(Color.YELLOW);
					eb.setAuthor(name, null, iconUrl);
					eb.setThumbnail(thumbUrl);
					eb.setDescription(UrlProd);
					eb.setImage(UrlPNG);
					MessageEmbed e = eb.build();
					channel.sendMessage(e).queue();
				}
			} else {
				EmbedBuilder eb = new EmbedBuilder();
				eb.setColor(Color.YELLOW);
				eb.setAuthor("인형제조오류발생", null, iconUrl);
				eb.setThumbnail(thumbUrl);
				eb.setDescription("**시키깡 인형시간도 모르면서 시키깡인거야?**");
				eb.setImage("https://i.imgur.com/TM7zu7z.png");
				MessageEmbed e = eb.build();
				channel.sendMessage(e).queue();
			}
		}
	}
}
