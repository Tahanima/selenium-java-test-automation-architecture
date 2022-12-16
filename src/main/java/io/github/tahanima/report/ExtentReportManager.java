package io.github.tahanima.report;

import static io.github.tahanima.config.ConfigurationManager.configuration;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author tahanima
 */
public final class ExtentReportManager {
  private ExtentReportManager() {}

  public static ExtentReports createReport() {
    String currentDate = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
    String fileName =
        String.format("%sTestReport_%s.html", configuration().baseReportPath(), currentDate);

    ExtentReports extentReport = new ExtentReports();
    ExtentSparkReporter spark = new ExtentSparkReporter(fileName);
    extentReport.attachReporter(spark);
    extentReport.setSystemInfo("Platform", System.getProperty("os.name"));
    extentReport.setSystemInfo("Browser", configuration().browser());

    return extentReport;
  }
}
