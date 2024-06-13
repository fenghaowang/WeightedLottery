package launcher;

/**
 * @author fenghaow
 * @version 20240612
 */


import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import datahandler.InputLoader;
import model.Employee;
import utility.Algorithm;




public class Launcher
{
    public static void main(String[] args) throws IOException
    {

        // Global variables
        String PROGRAM_VERSION = "build 20240612"; // Version number
        String LOG_FILE_NAME = "RunLog_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss")) + ".log";
        String inputFileName;


        // Algorithm runtime clocks
        LocalDateTime algorithmRunStartTime; // Variable to store algorithm start time
        LocalDateTime algorithmRunEndTime; // Variable to store algorithm end time


        /**** Initialize logger for logging ****/
        Logger logger = Logger.getLogger("GlobalLogger");

        System.setProperty("java.util.logging.SimpleFormatter.format", "%1$tF %1$tT %4$s %2$s %5$s%6$s%n"); // Set output to be on same line


        // Add file handler
        File logFile = new File(LOG_FILE_NAME);

        if (logFile.exists()) // Remove the old log file if it already exists
        {
            logFile.delete();
        }

        Handler fileHandler = new FileHandler(LOG_FILE_NAME, true);

        logger.addHandler(fileHandler);


        // Set levels to handlers and logger
        fileHandler.setLevel(Level.ALL);
        logger.setLevel(Level.ALL);


        // Set formatter to be simple formatter
        Formatter simpleFormatter = new SimpleFormatter();
        fileHandler.setFormatter(simpleFormatter);


        /**** End of logger initialization ****/


        // Print algorithm starting message
        logger.log(Level.INFO, "Algorithm starting... ");
        logger.log(Level.INFO, "Running version: " + PROGRAM_VERSION);
        algorithmRunStartTime = LocalDateTime.now();
        logger.log(Level.INFO, "Algorithm started at " + algorithmRunStartTime + "\n");


        // Identify the input file
        if (args.length > 0)
        {
            inputFileName = args[0];
        }

        else
        {
            inputFileName = "roster.csv";
        }

        logger.log(Level.INFO, "Input file target name set to: " + inputFileName);


        // Load the input file
        List<Employee> employees = new ArrayList<>();

        try
        {
            employees = InputLoader.rosterReader(inputFileName, logger);
        }

        catch (Exception e)
        {
            logger.log(Level.SEVERE, "Error when loading input file: " + e);
        }


        // ********** Main logic implementation **********
        Algorithm.weightCalculator(employees, logger);
        Algorithm.runLotteryDraw(employees, logger);


        // Print algorithm run message
        logger.log(Level.INFO, "Algorithm completed.");
        algorithmRunEndTime = LocalDateTime.now();
        logger.log(Level.INFO, "Algorithm completed at " + algorithmRunEndTime);
        logger.log(Level.INFO, "Algorithm total run time is: " + algorithmRunStartTime.until(algorithmRunEndTime, ChronoUnit.MINUTES) + " minutes.");


    }


}