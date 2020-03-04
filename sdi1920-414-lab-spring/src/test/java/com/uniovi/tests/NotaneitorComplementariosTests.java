package com.uniovi.tests;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;

import com.uniovi.entities.Professor;
import com.uniovi.repositories.ProfessorRepository;
import com.uniovi.tests.pageobjects.PO_HomeView;
import com.uniovi.tests.pageobjects.PO_PrivateView;
import com.uniovi.tests.pageobjects.PO_Properties;
import com.uniovi.tests.pageobjects.PO_RegisterProfessorView;
import com.uniovi.tests.pageobjects.PO_RegisterView;
import com.uniovi.tests.pageobjects.PO_View;
import com.uniovi.utils.SeleniumUtils;

public class NotaneitorComplementariosTests {

	// En Windows (Debe ser la versión 65.0.1 y desactivar las actualizacioens
		// automáticas)):
		static String PathFirefox65 = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
		static String Geckdriver024 = "C:\\Users\\SARAVG\\Desktop\\SDI\\PL-SDI-Sesión5-material\\geckodriver024win64.exe";
		// En MACOSX (Debe ser la versión 65.0.1 y desactivar las actualizacioens
		// automáticas):
		// static String PathFirefox65 =
		// "/Applications/Firefox.app/Contents/MacOS/firefox-bin";
		// static String Geckdriver024 = "/Users/delacal/selenium/geckodriver024mac";
		// Común a Windows y a MACOSX
		static WebDriver driver = getDriver(PathFirefox65, Geckdriver024);
		static String URL = "http://localhost:8090";

		private ProfessorRepository pr;

		// PR01. Registro de profesores con datos válidos. /
		@Test
		public void PR01() {
			PO_PrivateView.login(driver, "99999988F", "123456");
			//go to register professor
			PO_PrivateView.buscarXPath(driver,  "//li[contains(@id, 'professors-menu')]/a", 0);
			PO_PrivateView.buscarXPath(driver,  "//a[contains(@href, 'professor/add')]", 0);
			PO_RegisterProfessorView.fillForm(driver, "99991290G", "Pedro", "Perez", "ASR");
			PO_View.checkElement(driver, "text", "Profesores");
			PO_View.checkElement(driver, "text", "99991290G");
	
		}
		
		// PR02.Registro de profesores con datos inválidos (nombre y categoría inválidos). /
		@Test
		public void PR02() {
			PO_PrivateView.login(driver, "99999988F", "123456");
			//go to register professor
			PO_PrivateView.buscarXPath(driver,  "//li[contains(@id, 'professors-menu')]/a", 0);
			PO_PrivateView.buscarXPath(driver,  "//a[contains(@href, 'professor/add')]", 0);
			//dni too short
			PO_RegisterProfessorView.fillForm(driver, "9290B", "Pedro", "Perez", "ASR");
			PO_RegisterProfessorView.checkKey(driver, "Error.professor.dnilength", PO_Properties.getSPANISH());
			//dni format wrong
			PO_RegisterProfessorView.fillForm(driver, "999999902", "Pedro", "Perez", "ASR");
			PO_RegisterProfessorView.checkKey(driver, "Error.professor.digit", PO_Properties.getSPANISH());
			PO_RegisterProfessorView.fillForm(driver, "999999AR2", "Pedro", "Perez", "ASR");
			PO_RegisterProfessorView.checkKey(driver, "Error.professor.digit", PO_Properties.getSPANISH());
			//dni repeated
			PO_RegisterProfessorView.fillForm(driver, "99991290G", "Pedro", "Perez", "ASR");
			PO_RegisterProfessorView.checkKey(driver, "Error.professor.unique", PO_Properties.getSPANISH());
		}
		// PR03. Verificar que solo los usuarios autorizados pueden dar de alta un profesor. /
		@Test
		public void PR03() {

			PO_PrivateView.login(driver, "99999990A", "123456");

			PO_PrivateView.buscarXPath(driver,  "//li[contains(@id, 'professors-menu')]/a", 0);
			SeleniumUtils.EsperaCargaPaginaNoTexto(driver, "Añadir profesor", PO_View.getTimeout());
	
			

		}

		public static WebDriver getDriver(String PathFirefox, String Geckdriver) {
			System.setProperty("webdriver.firefox.bin", PathFirefox);
			System.setProperty("webdriver.gecko.driver", Geckdriver);
			WebDriver driver = new FirefoxDriver();
			return driver;
		}

		// Antes de cada prueba se navega al URL home de la aplicaciónn
		@Before
		public void setUp() {
			driver.navigate().to(URL);
		}

		// Después de cada prueba se borran las cookies del navegador
		@After
		public void tearDown() {
			driver.manage().deleteAllCookies();
		}

		// Antes de la primera prueba
		@BeforeClass
		static public void begin() {
		}

		// Al finalizar la última prueba
		@AfterClass
		static public void end() {
			// Cerramos el navegador al finalizar las pruebas
			driver.quit();
		}

}
