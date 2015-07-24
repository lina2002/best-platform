import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class UserInterfaceTest {
    private WebDriver driver;
    private String baseUrl;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        baseUrl = "http://localhost:8080";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testSimpleNavigation() throws Exception {
        driver.get(baseUrl + "/courses");
        driver.findElement(By.cssSelector("h3")).click();
        driver.findElement(By.cssSelector("button.btn-back")).click();
        driver.findElement(By.xpath("//div[@id='coursescontainer']/div[2]/a/h3")).click();
        driver.findElement(By.cssSelector("button.btn-back")).click();
        driver.findElement(By.xpath("//div[@id='coursescontainer']/div[3]/a/h3")).click();
        driver.findElement(By.cssSelector("button.btn-back")).click();
        driver.findElement(By.xpath("//div[@id='coursescontainer']/div[4]/a/h3")).click();
        driver.findElement(By.cssSelector("button.btn-back")).click();
        driver.findElement(By.xpath("//div[@id='coursescontainer']/div[5]/a/h3")).click();
        driver.findElement(By.cssSelector("button.btn-back")).click();
        driver.findElement(By.cssSelector("h3")).click();
        assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*$"));
    }

    @Test
    public void testSignIn() throws Exception {
        driver.get(baseUrl + "/courses");
        driver.findElement(By.cssSelector("input.btn")).click();
        driver.findElement(By.name("username")).clear();
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
        driver.findElement(By.linkText("admin")).click();
        assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*$"));
    }

    @Test
    public void testSignOut() throws Exception {
        driver.get(baseUrl + "/courses");
        driver.findElement(By.cssSelector("input.btn")).click();
        driver.findElement(By.name("username")).clear();
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
        driver.findElement(By.linkText("admin")).click();
        driver.findElement(By.cssSelector("input.btn")).click();
        assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*$"));
    }

    @Test
    public void testCourseEdit() throws Exception {
        driver.get(baseUrl + "/courses");
        driver.findElement(By.cssSelector("input.btn")).click();
        driver.findElement(By.name("username")).clear();
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
        driver.findElement(By.cssSelector("h3")).click();
        driver.findElement(By.cssSelector("button.btn-edit")).click();
        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("name")).sendKeys("Kasza");
        driver.findElement(By.id("description")).clear();
        driver.findElement(By.id("description")).sendKeys("Kasza smaczna i zdrowa.");
        driver.findElement(By.cssSelector("input.btn")).click();
        driver.findElement(By.cssSelector("button.btn-back")).click();
        driver.findElement(By.cssSelector("button.btn-back")).click();
        driver.findElement(By.cssSelector("h3")).click();
        assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*$"));
    }

    @Test
    public void testAddCourse() throws Exception {
        driver.get(baseUrl + "/courses");
        driver.findElement(By.cssSelector("input.btn")).click();
        driver.findElement(By.name("username")).clear();
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
        driver.findElement(By.linkText("admin")).click();
        driver.findElement(By.cssSelector("a")).click();
        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("name")).sendKeys("Pierogi");
        driver.findElement(By.id("description")).clear();
        driver.findElement(By.id("description")).sendKeys("Pierogi to nie kasza, ale takze nie najgorsze.");
        driver.findElement(By.cssSelector("input.btn")).click();
        driver.findElement(By.cssSelector("button.btn-back")).click();
        driver.findElement(By.xpath("//div[@id='coursescontainer']/div[6]/a/h3")).click();
        assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*$"));
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

}
