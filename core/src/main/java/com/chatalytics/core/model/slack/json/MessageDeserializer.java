package com.chatalytics.core.model.slack.json;

import com.chatalytics.core.model.Message;
import com.chatalytics.core.model.json.JsonChatDeserializer;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;

import org.joda.time.DateTime;

import java.io.IOException;

/**
 * Deserializes slack {@link Message}s
 *
 * @author giannis
 *
 */
public class MessageDeserializer extends JsonChatDeserializer<Message> {

    @Override
    public Message deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException,
            JsonProcessingException {

        ObjectCodec oc = jp.getCodec();
        JsonNode node = oc.readTree(jp);

        String channelId = getAsTextOrNull(node.get("channel"));
        String timestampStr = node.get("ts").asText();
        String[] timestampElementsArr = timestampStr.split("\\.");
        long seconds = Long.parseLong(timestampElementsArr[0]);
        long nanos = Long.parseLong(timestampElementsArr[1]);
        long timeInMillis = seconds * 1000 + nanos / 1000;
        DateTime date = new DateTime(timeInMillis);

        JsonNode subtype = node.get("subtype");
        if (subtype != null) {
            if ("message_changed".equals(subtype.asText())) {
                node = node.get("message");
            } else if ("bot_message".equals(subtype.asText())) {
                JsonNode attachmentNode = node.get("attachments");
                if (attachmentNode != null) {
                    node = attachmentNode;
                }
            }
        }

        String fromName = getAsTextOrNull(node.get("username"));

        JsonNode fromUserIdNode = node.get("user");
        String fromUserId;
        if (fromUserIdNode == null) {
            fromUserId = fromName;
        } else {
            fromUserId = fromUserIdNode.asText();
        }

        String message = getAsTextOrNull(node.get("text"));

        try {
            if (message == null) {
                message = getAsTextOrNull(node.get("pretext"));
            }
        } catch (Exception e) {
            throw new RuntimeException(node.toString(), e);
        }

        return new Message(date, fromName, fromUserId, message, channelId);
    }

}
