package data;

import exception.BrowserNotSupportedException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum Communication {
    FACEBOOK("Facebook", "facebook@gmail.com"),
    VK("VK", "vk.com/onaosipova"),
    OK("OK", "ok.ru/onaosipova"),
    SKYPE("Skype", "onaosipova@gmail.com"),
    VIBER("Viber", "88002000600"),
    TELEGRAM("Telegram", "@onaosipova"),
    WHATSAPP("WhatsApp", "88002000600"),
    HABR("Habr", "onaosipova@gmail.com");

    private final String typeOfCommunication;
    private final String communication;

    public static String getCommunication(String type) {
        return Arrays.stream(Communication.values())
                .filter(val -> val.getTypeOfCommunication().equals(type))
                .findFirst()
                .orElseThrow(() -> new BrowserNotSupportedException(type + " не найден"))
                .getCommunication();
    }
}
