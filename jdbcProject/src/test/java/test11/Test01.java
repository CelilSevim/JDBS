package test11;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;


public class Test01 {
    WebDriver driver;
    WebDriverWait wait;

    String url = "https://brandstore.e-shopland.ch/produkt-kategorie/mens-fashion/";
    By kalpLocator = By.cssSelector("div.yith-wcwl-add-button");


    @BeforeClass
    public void beforeTest(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void test1(){
        driver.get(url);
        wait.until(ExpectedConditions.visibilityOfElementLocated(kalpLocator));
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,300);");

        TakesScreenshot takesScreenshot =((TakesScreenshot) driver);
        File SrcFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File DestFile = new File("test.png");

        try {
            FileUtils.copyFile(SrcFile, DestFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}

/*
Adress        : https://brandstore.e-shopland.ch/
Kullanici     : kursjava@gmail.com
Sifre         : kurs.java.123


*    Ana sayfada bütün resimler tanimli mi?
*    Dogru kullanici ve sifre ile giris yapilabiliyor mu?
*    Menü : Tüm Ürünler
    a.    Tüm ürünlerin resimleri ve fiyat bilgileri var mi
    b.    sol tarafta bulunan kategoriler dropdown calisiyor mu?
    c.    Sag üst tarafta bulunan siralama islemi düzgün calisiyor mu?
    d.    Ürün filtreleme düzgün calisiyor mu?
*    Favorilere ürün eklenip, kaldirilabiliyor mu?
*    Enson tiklanan ürün sol kategoriler altinda bulunan "Son görüntülenenler" kisminda listeleniyor mu?
*    Arama fonksiyonu düzenli calisiyor mu? (Menü kisminda ve sayfanin sol alt kismindaki form)
*    Ürün karsilastirmasi yapilabiliyor mu?
*    Ürünler sepete dogru bilgiler ile eklenebiliyor mu?
*    Sepette gücelleme islemi yapilabiliyor mu?
*    Ürün satin alinabiliyor mu? (Ödemeyi kredi karti secenegi ile yapin, Kayitli Demo bir Kredi karti var)
*    Bir ürün satin alin ve bu ürünün siparisler bölümünde listelendigini onaylayin.


Proje:
    1.    Cucumber ile yapilacak.
    2.    Log kaydi tutulacak (Text ve excel)
    3.    Rapor alinacak
    4.    Scrum uygulanacak
    5.    1 haftalik sprint planlanacak
    6.    Sprint planlama toplantisi yapilacak
    7.    Daily stand-up'lar yapilacak
    8.    Sprint degerlendirme toplantisi yapilacak
 */
