package ca.voidustries.Nova;

import ca.voidustries.Nova.commands.UserInfoCommand;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;

public class Main {
    public static void main(String[] args) {

        // Get the bots token from the system variable
        String token = System.getenv("DISCORD_BOT_TOKEN");
        System.out.println(token);

        // Log the bot into discord using system token (Either Alpha, or Prod)
        DiscordApi api = new DiscordApiBuilder().setToken(token).login().join();

        api.addMessageCreateListener(new UserInfoCommand());

        // Print the invite url of your bot
        System.out.println("You can invite the bot by using the following url: " + api.createBotInvite());
    }
}
