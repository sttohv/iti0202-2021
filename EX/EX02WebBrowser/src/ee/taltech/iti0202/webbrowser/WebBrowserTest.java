package ee.taltech.iti0202.webbrowser;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WebBrowserTest {
    @Test
    /**
     * Public class
     */
    public void solve() {
        WebBrowser test = new WebBrowser();
        List<String> result = new ArrayList<>();
        result.add("google.com");
        result.add("facebook.com");
        result.add("google.com");
        test.setHomePage("neti.ee");
        test.goTo("facebook.com");
        test.back();
        test.back();
        assertEquals(test.getHistory(), result);
    }
}
