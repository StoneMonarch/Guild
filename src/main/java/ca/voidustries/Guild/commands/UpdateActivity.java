package ca.voidustries.Guild.commands;

import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

public class UpdateActivity implements MessageCreateListener {
    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        if (event.getMessageContent().contains("!setActivity")) {
            if (!event.getMessage().getAuthor().isBotOwner()) {
                event.getChannel().sendMessage("You are not allowed to use this command!");
                return;
            }
            String activity = event.getMessageContent().replaceFirst("!setActivity", "");
            activity = activity.trim();
            if (activity.equals("")) {
                event.getChannel().sendMessage("Invalid Input");
            } else {
                event.getApi().updateActivity(activity);
            }
        }
    }
}
