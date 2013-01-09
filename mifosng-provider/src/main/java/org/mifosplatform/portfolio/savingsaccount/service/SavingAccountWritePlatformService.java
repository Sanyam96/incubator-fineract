package org.mifosplatform.portfolio.savingsaccount.service;

import org.mifosplatform.infrastructure.core.data.CommandProcessingResult;
import org.mifosplatform.portfolio.savingsaccount.command.SavingAccountCommand;
import org.springframework.security.access.prepost.PreAuthorize;

public interface SavingAccountWritePlatformService {

    @PreAuthorize(value = "hasAnyRole('ALL_FUNCTIONS', 'CREATE_SAVINGSACCOUNT')")
    CommandProcessingResult createSavingAccount(SavingAccountCommand command);

    @PreAuthorize(value = "hasAnyRole('ALL_FUNCTIONS', 'UPDATE_SAVINGSACCOUNT')")
    CommandProcessingResult updateSavingAccount(SavingAccountCommand command);
}