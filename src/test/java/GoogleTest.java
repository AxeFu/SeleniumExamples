import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class GoogleTest {

    @Test
    public void run() {
        WebDriver driver = new ChromeDriver();

        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.get("https://www.avito.ru/nizhniy_novgorod/transport");

        WebElement element = driver.findElement(By.xpath("(//a[text()='Audi'])[1]"));

        String par = element.getAttribute("href");

        System.out.println(par);

        driver.quit();
    }

    @Test
    public void run2() {
        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://devby.io/");

        WebElement element = driver.findElement(By.cssSelector("use[href$=authIcon]"));

        String par = element.getCssValue("display");

        System.out.println(par);

        Actions actions = new Actions(driver);
        //not Work
        actions.keyDown(Keys.LEFT_CONTROL);
        actions.sendKeys("T");
        actions.keyUp(Keys.LEFT_CONTROL);
        actions.build().perform();

        driver.get("https://ya.ru");
    }

    @Test
    public void run3() throws InterruptedException {
        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://pagination.js.org/");
        Thread.sleep(2000);//Это было в ролике, кощунский способ подождать блокируя поток

        List<WebElement> elements = driver.findElements(By.xpath("//div[@class='data-container']/ul/li"));
        List<WebElement> pages = driver.findElements(By.xpath("//div[@class='paginationjs-pages']/ul/li"));

        String text = elements.get(5).getText();
        System.out.println(text);

        pages.get(2).click();
        wait.until(ExpectedConditions.stalenessOf(elements.get(5)));

        elements = driver.findElements(By.xpath("//div[@class='data-container']/ul/li"));

        text = elements.get(5).getText();
        System.out.println(text);
    }

    @Test
    public void run4() {
        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://ya.ru");

        String window1 = driver.getWindowHandle();
        String window2 = null;

        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("window.open()");

        Set<String> windowHandlers = driver.getWindowHandles();
        for (String handler : windowHandlers) {
            if (!window1.equals(handler)) {
                window2 = handler;
                break;
            }
        }
        driver.switchTo().window(window2);
        driver.get("https://google.com");
    }
}
