import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FirstTest {

    private final WebDriver driver;
    {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.EAGER); //Only DOM Content load
        driver = new ChromeDriver();
    }
    @Test
    public void seleniumIntroduction() {
        //Windows only
        //System.setProperty("webdriver.chrome.driver", "path");

        driver.get("https://www.selenium.dev/selenium/web/web-form.html");

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500)); //TODO: очень странный способ ожидания загрузки страницы

        WebElement textBox = driver.findElement(By.name("my-text"));
        WebElement submitButton = driver.findElement(By.cssSelector("button")); //TODO: понять по какому принципу проводит поиск

        WebElement myCheck2 = driver.findElement(By.id("my-check-2"));
        Assert.assertFalse(myCheck2.isSelected());
        myCheck2.click();
        boolean checkBoxResult = myCheck2.isSelected();

        textBox.sendKeys("Selenium");
        submitButton.click();

        WebElement message = driver.findElement(By.id("message"));
        String submitMessage = message.getText();

        String title = driver.getTitle();
        driver.quit();
        Assert.assertEquals(title,"Web form - target page");
        Assert.assertEquals(submitMessage,"Received!");
        Assert.assertTrue(checkBoxResult);
    }

    @Test
    public void WaitingStrategies() {
        driver.get("http://localhost:80");

        //implicit
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        //wait if element not found, max waiting equal duration

        //explicit
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofMillis(1500));
        WebElement button = driver.findElement(By.id("adder"));
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            button.click();
            WebElement redBox = wait.until(driver -> driver.findElement(By.id("box" + finalI)));
            //if true, wait is ended or throw TimeoutException;
            Assert.assertEquals("redbox", redBox.getAttribute("class"));
        }

        driver.findElement(By.className("information"));
        driver.findElement(By.xpath("//input[@value='f']"));
        driver.findElement(By.tagName("a"));
        driver.findElement(By.partialLinkText("Official Page"));
        driver.findElement(By.linkText("Selenium Official Page"));
        driver.findElement(By.name("newsletter"));
        driver.findElement(By.id("lname"));
        driver.findElement(By.cssSelector("#fname"));
        driver.findElement(By.className("information"));
    }
}
