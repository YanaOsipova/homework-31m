package data;

import exception.BrowserNotSupportedException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum Driver {
    CHROME("chrome"),
    FIREFOX("firefox"),
    EDGE("edge");

    private final String browser;

    public static Driver getBrowserName(String name) {
        return Arrays.stream(Driver.values())
                .filter(val -> val.getBrowser().equals(name))
                .findFirst()
                .orElseThrow(() -> new BrowserNotSupportedException("Браузер " + name + "не найден"));
    }
}
