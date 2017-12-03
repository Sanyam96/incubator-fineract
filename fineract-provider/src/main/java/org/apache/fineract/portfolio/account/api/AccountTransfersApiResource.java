/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.fineract.portfolio.account.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import org.apache.fineract.commands.domain.CommandWrapper;
import org.apache.fineract.commands.service.CommandWrapperBuilder;
import org.apache.fineract.commands.service.PortfolioCommandSourceWritePlatformService;
import org.apache.fineract.infrastructure.core.api.ApiRequestParameterHelper;
import org.apache.fineract.infrastructure.core.data.CommandProcessingResult;
import org.apache.fineract.infrastructure.core.serialization.ApiRequestJsonSerializationSettings;
import org.apache.fineract.infrastructure.core.serialization.DefaultToApiJsonSerializer;
import org.apache.fineract.infrastructure.core.service.Page;
import org.apache.fineract.infrastructure.core.service.SearchParameters;
import org.apache.fineract.infrastructure.security.service.PlatformSecurityContext;
import org.apache.fineract.portfolio.account.data.AccountTransferData;
import org.apache.fineract.portfolio.account.service.AccountTransfersReadPlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Path("/accounttransfers")
@Component
@Scope("singleton")
@Api(value = "Account transfers api", description = "The class for getting info about account and creating an account")
public class AccountTransfersApiResource {

    private final PlatformSecurityContext context;
    private final DefaultToApiJsonSerializer<AccountTransferData> toApiJsonSerializer;
    private final PortfolioCommandSourceWritePlatformService commandsSourceWritePlatformService;
    private final ApiRequestParameterHelper apiRequestParameterHelper;
    private final AccountTransfersReadPlatformService accountTransfersReadPlatformService;

    @Autowired
    public AccountTransfersApiResource(final PlatformSecurityContext context,
                                       final DefaultToApiJsonSerializer<AccountTransferData> toApiJsonSerializer,
                                       final PortfolioCommandSourceWritePlatformService commandsSourceWritePlatformService,
                                       final ApiRequestParameterHelper apiRequestParameterHelper,
                                       final AccountTransfersReadPlatformService accountTransfersReadPlatformService) {
        this.context = context;
        this.toApiJsonSerializer = toApiJsonSerializer;
        this.commandsSourceWritePlatformService = commandsSourceWritePlatformService;
        this.apiRequestParameterHelper = apiRequestParameterHelper;
        this.accountTransfersReadPlatformService = accountTransfersReadPlatformService;
    }

    @GET
    @Path("template")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    @ApiOperation(value = "Refund a template")
    @ApiResponses({@ApiResponse(code = 200, message = "", response = AccountTransferData.class}))
    public String template(@QueryParam("fromOfficeId") @ApiParam(value="fromOfficeId") final Long fromOfficeId, @QueryParam("fromClientId") @ApiParam(value="fromClientId") final Long fromClientId,
                           @QueryParam("fromAccountId") @ApiParam(value="fromAccountId") final Long fromAccountId, @QueryParam("fromAccountType") @ApiParam(value="fromAccountType") final Integer fromAccountType,
                           @QueryParam("toOfficeId") @ApiParam(value="toOfficeId") final Long toOfficeId, @QueryParam("toClientId") @ApiParam(value="toClientId") final Long toClientId,
                           @QueryParam("toAccountId") @ApiParam(value="toAccountId") final Long toAccountId, @QueryParam("toAccountType") @ApiParam(value="toAccountType") final Integer toAccountType,
                           @Context final UriInfo uriInfo) {

        this.context.authenticatedUser().validateHasReadPermission(AccountTransfersApiConstants.ACCOUNT_TRANSFER_RESOURCE_NAME);

        final AccountTransferData transferData = this.accountTransfersReadPlatformService.retrieveTemplate(fromOfficeId, fromClientId,
                fromAccountId, fromAccountType, toOfficeId, toClientId, toAccountId, toAccountType);

        final ApiRequestJsonSerializationSettings settings = this.apiRequestParameterHelper.process(uriInfo.getQueryParameters());
        return this.toApiJsonSerializer.serialize(settings, transferData, AccountTransfersApiConstants.RESPONSE_DATA_PARAMETERS);
    }

    @POST
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    @ApiOperation(value = "Create an account")
    @ApiResponses({@ApiResponse(code = 200, message = "", response = AccountTransferData.class)})
    public String create(@ApiParam(hidden = true)final String apiRequestBodyAsJson) {

        final CommandWrapper commandRequest = new CommandWrapperBuilder().createAccountTransfer().withJson(apiRequestBodyAsJson).build();

        final CommandProcessingResult result = this.commandsSourceWritePlatformService.logCommandSource(commandRequest);

        return this.toApiJsonSerializer.serialize(result);
    }

    @GET
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    @ApiOperation(value = "List all accounts")
    @ApiResponses({@ApiResponse(code = 200, message = "", response = AccountTransferData.class, responseContainer = "list")})
    public String retrieveAll(@Context final UriInfo uriInfo, @QueryParam("sqlSearch") @ApiParam(value="sqlSearch") final String sqlSearch,
                              @QueryParam("externalId") @ApiParam(value="externalId") final String externalId, @QueryParam("offset") @ApiParam(value="offset") final Integer offset,
                              @QueryParam("limit") @ApiParam(value="limit") final Integer limit, @QueryParam("orderBy") @ApiParam(value="orderBy")final String orderBy,
                              @QueryParam("sortOrder") @ApiParam(value="sortOrder") final String sortOrder,@QueryParam("accountDetailId") @ApiParam(value="accountDetailId") final Long accountDetailId) {

        this.context.authenticatedUser().validateHasReadPermission(AccountTransfersApiConstants.ACCOUNT_TRANSFER_RESOURCE_NAME);

        final SearchParameters searchParameters = SearchParameters.forAccountTransfer(sqlSearch, externalId, offset, limit, orderBy,
                sortOrder);

        final Page<AccountTransferData> transfers = this.accountTransfersReadPlatformService.retrieveAll(searchParameters, accountDetailId);

        final ApiRequestJsonSerializationSettings settings = this.apiRequestParameterHelper.process(uriInfo.getQueryParameters());
        return this.toApiJsonSerializer.serialize(settings, transfers, AccountTransfersApiConstants.RESPONSE_DATA_PARAMETERS);
    }

    @GET
    @Path("{transferId}")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    @ApiOperation(value = "Refund one account")
    @ApiResponses({@ApiResponse(code = 200, message = "", response = AccountTransferData.class)})
    public String retrieveOne(@PathParam("transferId") @ApiParam(value="transferId")final Long transferId, @Context final UriInfo uriInfo) {

        this.context.authenticatedUser().validateHasReadPermission(AccountTransfersApiConstants.ACCOUNT_TRANSFER_RESOURCE_NAME);

        final AccountTransferData transfer = this.accountTransfersReadPlatformService.retrieveOne(transferId);

        final ApiRequestJsonSerializationSettings settings = this.apiRequestParameterHelper.process(uriInfo.getQueryParameters());
        return this.toApiJsonSerializer.serialize(settings, transfer, AccountTransfersApiConstants.RESPONSE_DATA_PARAMETERS);
    }

    @GET
    @Path("templateRefundByTransfer")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    @ApiOperation(value = "Refund template by transfer")
    @ApiResponses({@ApiResponse(code = 200, message = "", response = AccountTransferData.class)})
    public String templateRefundByTransfer(@QueryParam("fromOfficeId") @ApiParam(value="fromOfficeId") final Long fromOfficeId, @QueryParam("fromClientId") @ApiParam(value="fromClientId") final Long fromClientId,
                                           @QueryParam("fromAccountId") @ApiParam(value="fromAccountId") final Long fromAccountId, @QueryParam("fromAccountType") @ApiParam(value="fromAccountType") final Integer fromAccountType,
                                           @QueryParam("toOfficeId") @ApiParam(value="toOfficeId") final Long toOfficeId, @QueryParam("toClientId") @ApiParam(value="toClientId") final Long toClientId,
                                           @QueryParam("toAccountId") @ApiParam(value="toAccountId") final Long toAccountId, @QueryParam("toAccountType") @ApiParam(value="toAccountType") final Integer toAccountType,
                                           @Context final UriInfo uriInfo) {

        this.context.authenticatedUser().validateHasReadPermission(AccountTransfersApiConstants.ACCOUNT_TRANSFER_RESOURCE_NAME);

        final AccountTransferData transferData = this.accountTransfersReadPlatformService.retrieveRefundByTransferTemplate(fromOfficeId, fromClientId,
                fromAccountId, fromAccountType, toOfficeId, toClientId, toAccountId, toAccountType);

        final ApiRequestJsonSerializationSettings settings = this.apiRequestParameterHelper.process(uriInfo.getQueryParameters());
        return this.toApiJsonSerializer.serialize(settings, transferData, AccountTransfersApiConstants.RESPONSE_DATA_PARAMETERS);
    }

    @POST
    @Path("refundByTransfer")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    @ApiOperation(value = "Refund template by transfer (POST version)")
    @ApiResponses({@ApiResponse(code = 200, message = "", response = AccountTransferData.class)})
    public String templateRefundByTransferPost(@ApiParam(hidden = true) final String apiRequestBodyAsJson) {

        final CommandWrapper commandRequest = new CommandWrapperBuilder().refundByTransfer().withJson(apiRequestBodyAsJson).build();

        final CommandProcessingResult result = this.commandsSourceWritePlatformService.logCommandSource(commandRequest);

        return this.toApiJsonSerializer.serialize(result);
    }
}