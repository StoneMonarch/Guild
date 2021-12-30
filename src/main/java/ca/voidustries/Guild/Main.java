package ca.voidustries.Guild;

import ca.voidustries.Guild.commands.UpdateActivity;
import ca.voidustries.Guild.commands.UserInfoCommand;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Period;
import org.joda.time.PeriodType;

import java.util.Timer;
import java.util.TimerTask;

public class Main {
    public static void main(String[] args) {

        // Get the bots token from the system variable
        String token = System.getenv("DISCORD_BOT_TOKEN");

        // Log the bot into discord using system token (Either Alpha, or Prod)
        DiscordApi api = new DiscordApiBuilder().setToken(token).login().join();

        api.addMessageCreateListener(new UserInfoCommand());
        api.addMessageCreateListener(new UpdateActivity());

        // Print the invite url of your bot
        System.out.println("You can invite the bot by using the following url: " + api.createBotInvite());
    }
}
