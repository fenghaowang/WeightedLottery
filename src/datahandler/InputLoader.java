package datahandler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Employee;

public class InputLoader
{
    public static List<Employee> rosterReader(String fileName, Logger logger)
    {
        /**
         * This reader only applies to a comma-separated file (.csv)
         *
         * @return A list of employee objects
         */

        List<Employee> employees = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName)))
        {

            String line;

            while ((line = br.readLine()) != null)
            {
                String[] data = line.split(",");

                if (data.length == 2)
                {
                    String employeeName = data[0];
                    LocalDate hireDate = LocalDate.parse(data[1], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    Employee employee = new Employee(employeeName, hireDate);
                    employees.add(employee);

                    logger.log(Level.INFO, "Employee read: " + employeeName + ", " + hireDate);

                }

                else
                {
                    logger.log(Level.WARNING, "Unexpected line format.  Line skipped.");
                }
            }
        }

        catch (IOException e)
        {
            logger.log(Level.SEVERE, "Error reading the input file: " + e);
        }

        logger.log(Level.INFO, employees.size() + " records loaded.");
        return employees;

    }
}
