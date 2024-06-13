package utility;

import model.Employee;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Algorithm
{


    /**
     *
     * @param employees
     * @param logger
     * @return A list of employee objects with the weight calculated for each entry
     */
    public static void weightCalculator(List<Employee> employees, Logger logger)
    {
        LocalDate today = LocalDate.now(); // Today's date for tenure calculation

        // Calculate the weight using tenure for each employee
        for (Employee employee : employees)
        {
            long tenure = ChronoUnit.DAYS.between(employee.getHireDate(), today);
            employee.setWeight(tenure);
        }

        logger.log(Level.INFO, "Weight calculated for " + employees.size() + " records.");

    }


    /**
     *
     * @param employees A list of employees that already have weights calculated for the lottery
     * @param logger
     * @return An ordered list of employees based on the lottery result
     */

    public static void runLotteryDraw(List<Employee> employees, Logger logger)
    {

        // Calculate initial total weight
        long totalWeight = 0;

        for (Employee employee : employees)
        {
            totalWeight += employee.getWeight();
        }


        // Run through the lottery process
        List<Employee> orderedEmployees = new ArrayList<>();
        List<Employee> remainingEmployees = new ArrayList<>(employees);

        while (!remainingEmployees.isEmpty())
        {
            try
            {
                double randomWeight = Math.random() * totalWeight;
                long cumulativeWeight = 0;
                Employee selectedEmployee = null;

                for (Employee employee : remainingEmployees) {
                    cumulativeWeight += employee.getWeight();
                    if (cumulativeWeight >= randomWeight) {
                        selectedEmployee = employee;
                        break;
                    }
                }

                logger.log(Level.INFO, "The random weight this time is: " + randomWeight);
                logger.log(Level.INFO, "The cumulative weight is: " + cumulativeWeight);
                logger.log(Level.INFO, "The winner this round is: " + selectedEmployee.getEmployeeName());

                orderedEmployees.add(selectedEmployee);
                remainingEmployees.remove(selectedEmployee);
                totalWeight -= selectedEmployee.getWeight();
            }

            catch(Exception e)
            {
                logger.log(Level.WARNING, "Error: " + e);
            }
        }

    }



}
