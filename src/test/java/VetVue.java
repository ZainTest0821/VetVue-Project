import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class VetVue {

    WebDriver driver;


    @BeforeTest
    public void Launch() {


        WebDriverManager.chromedriver();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://vetvue.celeritasdigital.com/");

    }

    @AfterTest
    void teardown() {
        //driver.quit();

    }
    // Login

    @Test(priority = 1)
    void login() throws InterruptedException {

        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchElementException.class);

        JavascriptExecutor js = (JavascriptExecutor) driver;

        // First scroll to end
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

        // Check Login button functionality

        WebElement loginbutton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*['@type= submit'])[53]")));
        loginbutton.click();

        // Check Forgot button functionality

        WebElement forgotbutton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*['@class=col-md-12 text-end mb-3'])[51]")));
        forgotbutton.click();

        // Enter  wrong login cred to check it is working as expected

        WebElement email1 = wait.until(ExpectedConditions.elementToBeClickable(By.id("email")));
        email1.sendKeys("admin@gmail.com");

        // Click on login to check functionality without password
        loginbutton.click();

        //Now enter password
        WebElement password1 = wait.until(ExpectedConditions.elementToBeClickable(By.id("password")));
        password1.sendKeys("admin12345!");

        //Click on Login button again
        Thread.sleep(2000);
        loginbutton.click();

        //Now enter valid cred

        WebElement email2 = wait.until(ExpectedConditions.elementToBeClickable(By.id("email")));
        email2.sendKeys("admin@gmail.com");

        WebElement password2 = wait.until(ExpectedConditions.elementToBeClickable(By.id("password")));
        password2.sendKeys("admin123!");

        //Click on Login button

        WebElement loginbutton2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*['@type= submit'])[53]")));
        loginbutton2.click();

    }

    @Test(priority = 2)
    void AllCheck() throws InterruptedException {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchElementException.class);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        Actions actions = new Actions(driver);

        // Scroll to end using Action class
        Thread.sleep(2500);
        WebElement end = wait.until(ExpectedConditions.elementToBeClickable(By.tagName("body")));
        actions.moveToElement(end).sendKeys(Keys.END).perform();

        Thread.sleep(2000);

        //Scroll to the top using Action class
        WebElement top = wait.until(ExpectedConditions.elementToBeClickable(By.tagName("body")));
        actions.moveToElement(top).sendKeys(Keys.HOME).perform();

        Thread.sleep(1500);

        // Click on toggle button of random dog entity
        WebElement toogle = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*['@class=card-header d-flex align-items-center justify-content-between collapsed'])[145]")));
        toogle.click();

        // scroll to littile bit down
        js.executeScript("window.scrollTo(" + 100 + "," + 500 + ");");

        Thread.sleep(1500);

        //Again scroll to top
        //Scroll to the top using Action class
        WebElement top1 = wait.until(ExpectedConditions.elementToBeClickable(By.tagName("body")));
        actions.moveToElement(top1).sendKeys(Keys.HOME).perform();



    }


    @Test(priority = 3)
    void date() throws InterruptedException {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchElementException.class);


        // Days toggle button functionality check

        Thread.sleep(2500);

        // Click on day toggle button
        WebElement daytoogle = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='btn dropdown-toggle btn-light']")));
        daytoogle.click();

        // Click on 7 days
        WebElement day7 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='text']")));
        day7.click();

        // Click again on toggle button
        Thread.sleep(3500);
        daytoogle.click();



        // Click on 14 days

        WebElement day14 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@class='text'])[2]")));
        day14.click();

        Thread.sleep(3500);
        //Click again on toggle button
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='btn dropdown-toggle btn-light']")));
        daytoogle.click();

        // Now click on 30 days

        WebElement day30 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@class='text'])[3]")));
        day30.click();


        // Again click on toggle button for selecting 90 days option
        Thread.sleep(3500);
        daytoogle.click();

        // Now select 90 days
        WebElement day90 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@class='text'])[4]")));
        day90.click();

    }

    @Test(priority = 4)
    void create() throws InterruptedException {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchElementException.class);

        // Check create button is working fine

        Thread.sleep(2500);

        WebElement create = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='bi bi-plus-square-fill font-size24px pe-2 pt-1 text-green']")));
        create.click();

        // Check that proper error show when user did not enter any details and click on Upload

        WebElement upload = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@type='submit']")));
        upload.click();

        // Click on cross button to close it

        WebElement close = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@class='text-lightGray font-size24px'])[5]")));
        close.click();

    }

    @Test(priority = 5)
    void Highrisk() throws InterruptedException {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchElementException.class);

        Thread.sleep(1500);

        //Click on High Risk tab button
        WebElement highrisk = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ex1-tab-2']")));
        highrisk.click();

        Thread.sleep(1500);

        //Again click on All
        WebElement all = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ex1-tab-1']")));
        all.click();

    }
    @Test(priority = 6)
    void clickonbell() throws InterruptedException {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchElementException.class);
        Actions actions = new Actions(driver);

        // Here check the functionality of  bell icon on given entries
        WebElement bellicon=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='me-2 text-decoration-none position-relative']")));
        bellicon.click();

        // Scroll to end using Action class
        Thread.sleep(2500);
        WebElement end = wait.until(ExpectedConditions.elementToBeClickable(By.tagName("body")));
        actions.moveToElement(end).sendKeys(Keys.END).perform();

        Thread.sleep(2000);

        //Scroll to the top using Action class
        WebElement top = wait.until(ExpectedConditions.elementToBeClickable(By.tagName("body")));
        actions.moveToElement(top).sendKeys(Keys.HOME).perform();

        Thread.sleep(2000);
    }

    @Test(priority = 7)
    void NotificationSetting() {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchElementException.class);

        // Notification check list
        // Click on Notification setting from Menu

        WebElement notificationsetting = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@style='padding-bottom: 0.6rem']")));
        notificationsetting.click();

        // Click on Device toggle button
        WebElement device = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='filter-option-inner-inner']")));
        device.click();

        // Click on Condition toggle button

        WebElement condition = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@class='filter-option-inner-inner'])[2]")));
        condition.click();

        // Click on Days toggle button
        WebElement days = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@class='filter-option-inner-inner'])[3]")));
        days.click();

        // Click on Range Scenario toggle button
        WebElement Rangescenario = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@class='filter-option-inner-inner'])[4]")));
        Rangescenario.click();

        //Click on Priority toggle button
        WebElement priority = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@class='filter-option-inner-inner'])[5]")));
        priority.click();


    }

    @Test(priority = 8)
    void crud() throws InterruptedException {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchElementException.class);

        // Check that the edit and delete button on value is working as expected

        // Click on edit

        WebElement edit=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='cursor-point']")));
        edit.click();

        // Click on Device dropdown button in edit's dialogbox
        WebElement device=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@class='filter-option-inner-inner'])[9]")));
        device.click();

        // Click on condition in dialogbox
        WebElement condition=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@class='filter-option-inner-inner'])[10]")));
        condition.click();

        // Click on days in dialogbox
        WebElement days=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@class='filter-option-inner-inner'])[11]")));
        days.click();

        // Click on Range scenario in dialogbox
        WebElement rangescenario=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@class='filter-option-inner-inner'])[12]")));
        rangescenario.click();

        // Click on update button in dialgobox
        WebElement update=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@class='btn btn-primary pe-5 ps-5'])[5]")));
        update.click();

        Thread.sleep(1000);

        // Now close the dialog box
       // WebElement close=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@class='bi bi-x-circle'])[6]")));
        //close.click();



        // Now check delete button functionality

        WebElement delete=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@class='cursor-point'])[2]")));
        delete.click();

        // Click on cross button to close delete dialog box
        WebElement cross=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@class='text-lightGray font-size24px'])[8]")));
        cross.click();

    }

    @Test(priority = 9)
    void Notification() throws InterruptedException {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchElementException.class);
        Actions actions = new Actions(driver);

        // Here we check notification screen
        // Click on All notification button from menu bar
        WebElement notificationscreen=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@class='nav-link border-radius-10px  d-flex align-items-center '])[2]")));
        notificationscreen.click();

        // Enter some value in Search bar to check its functionality
        WebElement search=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@type='search']")));
        search.sendKeys("Testing search bar by Mr.Arif");

        Thread.sleep(2000);


        // Now click on priority dropdown button
        WebElement priority=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@type='button']")));
        priority.click();


    }
    @Test(priority = 10)
    void logout (){
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchElementException.class);

        // Now test that our logout functionality is working fine

        //Click on Logout button dropdown

        WebElement logoutdropdown=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='dropdown']")));
        logoutdropdown.click();

        //Now click on Logout button
        WebElement logout=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='dropdown-item']")));
        logout.click();

    }
}