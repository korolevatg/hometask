import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;


public class homeWebdriver {
    WebDriver driver;
    private final String LOGIN = "ceyogo9446@bitvoo.com";
    private final String PASS;

    {
        PASS = "Ceyogo9446!";
    }

    Logger logger = LogManager.getLogger(homeWebdriver.class);

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        logger.error("error");
        logger.info("info");
        logger.debug("debug");

    }

    @AfterEach
    public void after() {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }

    // 1) Проверка, что в поисковой выдаче первый результат Онлайн‑курсы для профессионалов, дистанционное обучение
    @Test
    public void firstTask() {
        // Создание опций Chrome и включение режима headless
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.setHeadless(true);
        WebDriver driver = new ChromeDriver(options);
        //    Перейти на https://duckduckgo.com/
        driver.get("https://duckduckgo.com/");
        //    В поисковую строку ввести ОТУС
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("ОТУС");
        searchBox.submit();
        //    Проверить что в поисковой выдаче первый результат Онлайн‑курсы для профессионалов, дистанционное обучение
        driver.findElement(By.xpath("//*[@id=\"r1-0\"]"));
        Assertions.assertEquals("Онлайн‑курсы для профессионалов, дистанционное обучение современным профессиям", "Онлайн‑курсы для профессионалов, дистанционное обучение современным профессиям");
        System.out.println("Поиск выполнен успешно!");
    }

    //2) Открытие картинки в модальном окне
    @Test
    public void secondTask() {
        //    Открыть Chrome в режиме киоска
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--kiosk");
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
//    Перейти на https://demo.w3layouts.com/demos_new/template_demo/03-10-2020/photoflash-liberty-demo_Free/685659620/web/index.html?_ga=2.181802926.889871791.1632394818-2083132868.1632394818
        driver.get("https://nos.twnsnd.co/");
//    Нажать на любую картинку
        driver.findElement(By.xpath("//*[starts-with(@src,'https://64.media.tumblr.com/251654ac8af3555621253a03fa40e919/e18bbf2c1d32f115-29/s1280x1920/4cd0e45ddc6bfcddc54afe89f62cf3328fcac2fb.jpg')]")).click();
//    Проверить что картинка открылась в модальном окне
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement modalWindow = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"tumblr\"]")));

        // Проверка, что модальное окно содержит картинку
        WebElement imageElement = modalWindow.findElement(By.tagName("img"));
        if (imageElement != null) {
            System.out.println("Картинка открыта в модальном окне");
        } else {
            System.out.println("Картинка не открыта в модальном окне");
        }

    }

    //3) Вывести в лог все cookie
    @Test
    public void thirdTask() {
        //    Открыть Chrome в режиме полного экрана
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        driver.manage().window().maximize();
//    Перейти на https://otus.ru
        driver.get("https://otus.ru");
//    Авторизоваться под каким-нибудь тестовым пользователем(можно создать нового)
        auth();
//    Вывести в лог все cookie

        System.out.println(driver.manage().getCookies());
    }

    private void auth() {
        driver.findElement(By.cssSelector(".header3__button-sign-in")).click();
        clearAndEnter(By.xpath("//*[@class='new-log-reg__form js-login']/descendant::*[@placeholder='Электронная почта']"), LOGIN);
        clearAndEnter(By.xpath("//*[@class='new-log-reg__form js-login']/descendant::*[@placeholder='Введите пароль']"),
                PASS);
        driver.findElement(By.cssSelector("div.new-input-line_last:nth-child(5) > button:nth-child(1)")).submit();
    }

    private void clearAndEnter(By by, String text) {
        driver.findElement(by).clear();
        driver.findElement(by).sendKeys(text);
    }

}



