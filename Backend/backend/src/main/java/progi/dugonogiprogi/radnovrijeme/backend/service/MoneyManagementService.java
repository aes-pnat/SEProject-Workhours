package progi.dugonogiprogi.radnovrijeme.backend.service;

import org.springframework.stereotype.Service;
import progi.dugonogiprogi.radnovrijeme.backend.rest.dto.MoneyManagementDTO;

/**
 * Specifies how will moneymanagement site process data and
 * what functionalities this service will provide.
 */

@Service
public interface MoneyManagementService {

    /**
     * Method returns expense information in a form of {@link MoneyManagementDTO}.
     *
     * @param price double value of expected expense from director's input.
     * @return MoneyManagementDTO information about total expense.
     */
    MoneyManagementDTO seeExpense(double price);

    /**
     * Method returns profit information in a form of {@link MoneyManagementDTO}.
     *
     * @param price double value of expected profit from director's input.
     * @return MoneyManagementDTO information about total profit.
     */

    MoneyManagementDTO seeProfit(double price);
}
