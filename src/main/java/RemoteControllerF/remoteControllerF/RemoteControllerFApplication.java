package RemoteControllerF.remoteControllerF;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@SpringBootApplication
public class RemoteControllerFApplication {

	public static void main(String[] args) {
		SpringApplication.run(RemoteControllerFApplication.class, args);
	}

	@Component
	public static class BotRunner implements CommandLineRunner {
		private final BotComponents botComponents;

		@Autowired
		public BotRunner(BotComponents botComponents) {
			this.botComponents = botComponents;
		}

		@Override
		public void run(String... args) throws Exception {
			TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
			telegramBotsApi.registerBot(botComponents);

			while (true) {
				Thread.sleep(1000);
			}
		}
	}
}
