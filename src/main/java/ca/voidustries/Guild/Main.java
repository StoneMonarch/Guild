package ca.voidustries.Guild;

import ca.voidustries.Guild.commands.UpdateActivity;
import ca.voidustries.Guild.commands.UserInfoCommand;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Period;
import org.joda.time.PeriodType;

import java.util.Date;
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

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                DateTime endTime = new DateTime(2021, 3, 11, 0, 0);
                DateTime startTime = new DateTime();
                Period p = new Period(startTime, endTime, PeriodType.yearDayTime());
                String activityString = "Days: " + p.getDays() + ", Hours: " + p.getHours();
                String hex = "";
                for (int i = 0; i < activityString.length(); i++) {
                    char ch = activityString.charAt(i);
                    int in = (int)ch;
                    String part = Integer.toHexString(in);
                    hex += part;
                }

                api.updateActivity(hex);
            }
        }, 0, 3600000);
    }
}
