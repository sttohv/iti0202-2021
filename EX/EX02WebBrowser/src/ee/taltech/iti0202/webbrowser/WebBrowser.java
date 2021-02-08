package ee.taltech.iti0202.webbrowser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Public class
 */
public class WebBrowser {
    private String homePage;
    private List<String> historyList = new ArrayList<>();
    private String current;
    private boolean backCount;
    private List<String> bookmarkList = new ArrayList<>();
    private List<String> back = new ArrayList<>();
    private List<String> forward = new ArrayList<>();


    /**
     * Creates webBrowser object.
     */
    public WebBrowser() {
        homePage = "google.com";
        backCount = false;
        historyList.add(homePage);
        back.add(homePage);
        current = homePage;
    }

    /**
     * Goes to homepage.
     */
    public void homePage() {
        goTo(homePage);
    }

    /**
     * Goes back to previous page.
     */
    public void back() {
        if (back.size() > 1) {
            forward.add(current);
            back.remove(back.size() - 1);
            current = back.get(back.size() - 1);
            historyList.add(current);

        }
    }

    /**
     * Goes forward to next page.
     */
    public void forward() {
        if (forward.size() > 0) {
            back.add(current);
            current = forward.get(forward.size() - 1);
            historyList.add(current);
            forward.remove(forward.size() - 1);
        }
    }

    /**
     * Go to a webpage.
     *
     * @param url url to go to
     */
    public void goTo(String url) {
        if (!url.equals(current)) {
            current = url;
            historyList.add(current);
            back.add(current);
            forward = new ArrayList<>();
        }
    }

    /**
     * Add a webpage as a bookmark.
     */
    public void addAsBookmark() {
        if (!bookmarkList.contains(current)) {
            bookmarkList.add(current);
        }
    }

    /**
     * Remove a bookmark.
     *
     * @param bookmark to remove
     */
    public void removeBookmark(String bookmark) {
        bookmarkList.remove(bookmark);
    }

    /**
     * Get bookmarks.
     *
     * @return return something
     */
    public List<String> getBookmarks() {
        return bookmarkList;
    }

    /**
     * Get bookmarks.
     * @param homePage do something
     */
    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }


    /**
     * Get top 3 visited pages.
     *
     * @return a String that contains top three visited pages separated with a newline "\n"
     */
    public String getTop3VisitedPages() {
        Map<String, Integer> result = new HashMap<>();
        String top3 = "";
        for (String page : historyList
        ) {
            if (!result.containsKey(page)) {
                result.put(page, Collections.frequency(historyList, page));
            }
        }
        List<String> keys = result.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue()
                .reversed()).limit(3).map(Map.Entry::getKey).collect(Collectors.toList());
        for (String key : keys
        ) {
            if (result.get(key) > 1) {
                top3 = top3 + key + " - " + result.get(key) + " visits" + "\n";
            } else {
                top3 = top3 + key + " - " + result.get(key) + " visit" + "\n";
            }
        }
        return top3;
    }


    /**
     * Returns a list of all visited pages.
     * <p>
     * Not to be confused with pages in your back-history.
     * <p>
     * For example, if you visit "facebook.com" and hit back(),
     * then the whole history would be: ["google.com", "facebook.com", "google.com"]
     *
     * @return list of all visited pages
     */
    public List<String> getHistory() {
        return historyList;
    }


    /**
     * Returns the active web page (string).
     *
     * @return active web page
     */
    public String getCurrentUrl() {
        return current;
    }
}
