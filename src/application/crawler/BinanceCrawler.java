package application.crawler;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.json.JSONObject;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
public class BinanceCrawler {
    public static void main(String[] args) {
        // Configure Chrome options
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("user-agent=Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.50 Safari/537.36");

        // Set up the Chrome web driver
        WebDriver driver = new ChromeDriver(options);

        // URL of the web page you want to scrape
        String url = "https://www.binance.com/en/blog/nft";
        driver.get(url);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Scroll logic
        int initialHeight = 0;
        while (true) {
            ((ChromeDriver) driver).executeScript("window.scrollBy(0, 500);");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            int newHeight = Integer.parseInt(((ChromeDriver) driver).executeScript("return window.scrollY").toString());
            if (newHeight == initialHeight) {
                break;
            }
            initialHeight = newHeight;
        }

        // Scrape data
        List<WebElement> postElements = driver.findElements(By.tagName("a"));
        ArrayList<JSONObject> articlesList = new ArrayList<>();

        for (WebElement articleElement : postElements) {
            String href = articleElement.getAttribute("href");
            JSONObject article = new JSONObject();
            article.put("post_title", articleElement.findElement(By.className("carousel-card-title")).getText());
            article.put("post_content", articleElement.findElement(By.className("carousel-card-content")).getText());
            article.put("post_date", articleElement.findElement(By.className("carousel-card-date")).getText());
            article.put("post_image", articleElement.findElement(By.tagName("img")).getAttribute("src"));

            // Additional logic for opening new tabs and scraping additional data
            // ...

            articlesList.add(article);
        }

        // Read existing JSON file
        JSONObject data = new JSONObject();
        try {
            String content = new String(Files.readAllBytes(Paths.get("data.json")));
            data = new JSONObject(content);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Update JSON object
        data.put("binance", articlesList);

        // Write to JSON file
        try (FileWriter file = new FileWriter("data.json")) {
            file.write(data.toString(4));
        } catch (Exception e) {
            e.printStackTrace();
        }

        driver.close();
    }
}
