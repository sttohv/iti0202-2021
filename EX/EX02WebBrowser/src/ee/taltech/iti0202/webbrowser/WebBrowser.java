package ee.taltech.iti0202.webbrowser;

import java.util.*;
import java.util.stream.Collectors;

public class WebBrowser {
    private String homePage;
    private List<String> historyList = new ArrayList<>();
    private String current;
    private int backCount;
    private List<String> bookmarkList= new ArrayList<>();
    private List<String> someList= new ArrayList<>();

    public WebBrowser() {
        homePage = "google.com";
        historyList.add(homePage);
        someList.add(homePage);
        current = homePage;
    }

    /**
     * Goes to homepage.
     */
    public void homePage() {
        //TODO: implement
        if (!current.equals(homePage)) {
            historyList.add(homePage);
            someList.add(homePage);
            current = homePage;
            backCount = 0; //checka üle kas on ikka õiges kohas
        }


    }

    /**
     * Goes back to previous page.
     */
    public void back() {
        //TODO: implement
        if (someList.size() > 1) {
            current = historyList.get(historyList.size() - 2);
            someList.remove(someList.size()-1);
            historyList.add(current);
            backCount++;
        }
    }

    /**
     * Goes forward to next page.
     */
    public void forward() {
        //TODO: implement
        if (backCount > 0) {
            current = historyList.get(historyList.size() - 2);
            historyList.add(current);
            backCount--;
        }
    }

    /**
     * Go to a webpage.
     *
     * @param url url to go to
     */
    public void goTo(String url) {
        //TODO: implement
        current = url;
        historyList.add(current);
        someList.add(current);
        backCount = 0;
    }

    /**
     * Add a webpage as a bookmark.
     */
    public void addAsBookmark() {
        //TODO: implement
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
        //TODO: implement
        bookmarkList.remove(bookmark);
    }

    public List<String> getBookmarks() {
        //TODO: implement
        return bookmarkList;
    }

    public void setHomePage(String homePage) {
        //TODO: implement
        this.homePage = homePage;
    }


    /**
     * Get top 3 visited pages.
     *
     * @return a String that contains top three visited pages separated with a newline "\n"
     */
    public String getTop3VisitedPages() {
        //TODO: implement
        Map<String, Integer> result = new HashMap<>();
        String top3 = "";
        for (String page : historyList
        ) {
            if (!result.containsKey(page)) {
                result.put(page, Collections.frequency(historyList, page));
            }
        }

        List<String> keys = result.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue().reversed()).limit(3).map(Map.Entry::getKey).collect(Collectors.toList());
        for (String key : keys
        ) {
            if(result.get(key)>1) {
                top3 = top3 + key + " - " + result.get(key) + " visits" + "\n";
            }else{
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
        //TODO: implement
        return historyList;
    }


    /**
     * Returns the active web page (string).
     *
     * @return active web page
     */
    public String getCurrentUrl() {
        //TODO: implement
        return current;
    }

    public static void main(String[] args) {

    }
}
