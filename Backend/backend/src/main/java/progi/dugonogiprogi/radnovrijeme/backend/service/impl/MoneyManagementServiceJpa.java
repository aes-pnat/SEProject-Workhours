package progi.dugonogiprogi.radnovrijeme.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import progi.dugonogiprogi.radnovrijeme.backend.dao.*;
import progi.dugonogiprogi.radnovrijeme.backend.domain.*;
import progi.dugonogiprogi.radnovrijeme.backend.rest.dto.CalculateDifferenceDTO;
import progi.dugonogiprogi.radnovrijeme.backend.rest.dto.FinancialInfoDTO;
import progi.dugonogiprogi.radnovrijeme.backend.rest.dto.MoneyManagementDTO;
import progi.dugonogiprogi.radnovrijeme.backend.service.abstractService.MoneyManagementService;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * Provides some functionalities regarding information about expense and profit
 *
 */

@Service
public class MoneyManagementServiceJpa implements MoneyManagementService {

    /**
     * Injection of values of {@link JobRepository} properties
     */

    @Autowired
    private JobRepository jobRepository;

    /**
     * Injection of values of {@link EmployeetaskRepository} properties
     */

    @Autowired
    private EmployeetaskRepository employeetaskRepository;

    /**
     * Injection of values of {@link TaskRepository} properties
     */

    @Autowired
    private TaskRepository taskRepository;

    /**
     * Injection of values of {@link EmployeeRepository} properties
     */

    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * Injection of values of {@link WorkHoursRepository} properties
     */

    @Autowired
    private WorkHoursRepository workHoursRepository;

    @Override
    public MoneyManagementDTO seeExpense(double price) {

        MoneyManagementDTO dto = new MoneyManagementDTO();
        dto.setPrice(this.countExpense());
        dto.setDifference(price - dto.getPrice());

        return dto;
    }



    @Override
    public MoneyManagementDTO seeProfit(double price) {
        MoneyManagementDTO dto = new MoneyManagementDTO();
        double jobPrice = 0;
        for(Job job : jobRepository.findAll()) {
                jobPrice+=job.getPrice();
        }
        dto.setPrice(jobPrice-this.countExpense());
        dto.setDifference(price - dto.getPrice());
        return dto;
    }

    /**
     * Method that counts total expense.
     *
     * @return double value of total expense.
     */
    public double countExpense(){
        double setPrice = 0;
        for(Job job : jobRepository.findAll()) {
            double jobPrice = 0;
            List<Employee> listemployees = new LinkedList<>();
            for (Task t : taskRepository.findAll()) {
                if (t.getIdjob().getId().equals(job.getId())) {
                    Optional<List<Employeetask>> employees = employeetaskRepository.findById_Idtask(t.getId());
                    if (employees.isPresent()) {
                        for (Employeetask et : employees.get()) {
                            listemployees.add(employeeRepository.getById(et.getId().getIdemployee()));
                        }
                    }
                }
            }
            for (Employee employee : listemployees) {
                for (Workhoursinput wh : workHoursRepository.findAll()) {
                    if (wh.getIdemployee().getId().equals(employee.getId())) {
                        jobPrice += wh.getWorkhoursdone();
                    }
                }
            }
            jobPrice = jobPrice*job.getHourprice();
            setPrice = setPrice + jobPrice;
        }
        return setPrice;
    }

    @Override
    public FinancialInfoDTO getFinancialInfo() {
        FinancialInfoDTO financialInfo = new FinancialInfoDTO();

        Double realizedRevenue = getRealizedRevenue();
        financialInfo.setRealizedRevenue(realizedRevenue);

        Double realizedCost = getRealizedCost();
        financialInfo.setRealizedCost(realizedCost);

        Double realizedProfit = realizedRevenue - realizedCost;
        financialInfo.setRealizedProfit(realizedProfit);

        return financialInfo;
    }

    @Override
    public CalculateDifferenceDTO calculateDifference(double expectedRevenue, double expectedCost) {
        CalculateDifferenceDTO calculateDifference = new CalculateDifferenceDTO();

        Double realizedRevenue = getRealizedRevenue();
        Double realizedCost = getRealizedCost();
        Double realizedProfit = realizedRevenue - realizedCost;

        Double revenueDifference = realizedRevenue - expectedRevenue;
        calculateDifference.setRevenueDifference(revenueDifference);

        Double costDifference = realizedCost - expectedCost;
        calculateDifference.setCostDifference(costDifference);

        Double profitDifference = realizedProfit - (expectedRevenue - expectedCost);
        calculateDifference.setProfitDifference(profitDifference);

        return calculateDifference;
    }

    private Double getRealizedRevenue() {
        Double realizedRevenue = (double) 0;
        for(Job job : jobRepository.findAll()) {
            realizedRevenue += job.getPrice();
        }
        return realizedRevenue;
    }

    private Double getRealizedCost() {
        Double realizedCost = (double) 0;
        for(Job job : jobRepository.findAll()) {
            Optional<List<Task>> optionalTasks = taskRepository.findByIdjob_Id(job.getId());
            if(optionalTasks.isPresent()) {
                for(Task task : optionalTasks.get()) {
                    for(Workhoursinput workhoursinput : workHoursRepository.findByIdtask_Id(task.getId())) {
                        realizedCost += job.getHourprice() * workhoursinput.getWorkhoursdone();
                    }
                }
            }
        }
        return realizedCost;
    }
}
