package basicAppium;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class BasicAppium {
    private AppiumDriver driver;
    @BeforeEach
    public void setup() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName","emulator-5554");
        capabilities.setCapability("platformVersion","10");
        capabilities.setCapability("appPackage","com.vrproductiveapps.whendo");
        capabilities.setCapability("appActivity","com.vrproductiveapps.whendo.ui.HomeActivity");
        capabilities.setCapability("platformName","Android");

        driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"),capabilities);

    }
    @AfterEach
    public void cleanup(){
        driver.quit();

    }

    @Test
    public void calculadoraTest() throws InterruptedException {
        //clic en botón +
        driver.findElement(By.id("com.vrproductiveapps.whendo:id/fab")).click();
        //agregar titulo
        driver.findElement(By.id("com.vrproductiveapps.whendo:id/noteTextTitle")).sendKeys("Nota de prueba");
        //agregando nota
        driver.findElement(By.id("com.vrproductiveapps.whendo:id/noteTextNotes")).sendKeys("Esto es una nota de prueba para el taller de automatización de móviles");
        //clic en el botón save
        driver.findElement(By.id("com.vrproductiveapps.whendo:id/saveItem")).click();

        //Verificación de resultado
        Thread.sleep(5000);
        String resultadoActualTituloNota= driver.findElement(By.id("com.vrproductiveapps.whendo:id/home_list_item_text")).getText();
        String resultadoActualContenidoNota = driver.findElement(By.id("com.vrproductiveapps.whendo:id/home_list_item_text2")).getText();
        String resultadoEsperadoTituloNota= "Nota de prueba";
        String resultadoEsperadoContenidoNota = "Esto es una nota de prueba para el taller de automatización de móviles";
        Assertions.assertEquals(resultadoActualTituloNota,resultadoEsperadoTituloNota);
        Assertions.assertEquals(resultadoActualContenidoNota,resultadoEsperadoContenidoNota);
    }
}
