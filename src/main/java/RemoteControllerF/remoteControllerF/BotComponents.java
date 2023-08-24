package RemoteControllerF.remoteControllerF;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.awt.*;
import java.io.File;
import java.io.IOException;


@Component
public class BotComponents extends TelegramLongPollingBot {

    @Value("${telegram.bot.username}")
    private String username;
    @Value("${telegram.bot.token}")
    private String token;

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void onUpdateReceived(Update update) {
        SendMessage sendMessage = new SendMessage();
        if (update.hasMessage()) {
            Message message = update.getMessage();
            String stringUser = message.getText();
            Process process;

            if (stringUser.equals("/help")) {
                sendMessage.setText("/off - off your pc");
                sendMessage.setChatId(String.valueOf(message.getChatId()));
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }

            if (stringUser.equals("/off")) {
                try {
                    sendMessage.setText("ok, one second..");
                    sendMessage.setChatId(String.valueOf(message.getChatId()));
                    execute(sendMessage);


                    process = Runtime.getRuntime().exec("shutdown /s /t 1");
                    process.waitFor();
                } catch (IOException | InterruptedException | TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}