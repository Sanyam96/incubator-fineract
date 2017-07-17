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
package org.apache.fineract.portfolio.client.api;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.fineract.infrastructure.codes.data.CodeValueData;
import org.apache.fineract.infrastructure.dataqueries.data.DatatableData;
import org.apache.fineract.portfolio.address.data.AddressData;
import org.codehaus.jackson.annotate.JsonProperty;
import org.joda.time.LocalDate;

import java.io.Serializable;
import java.util.List;

/**
 * Created by sanyam on 30/6/17.
 */
// Java Bean class for Swagger annotation just for defining the payload
// to be passed to swagger annotation for POST and PUT method
@ApiModel(value = "Client payload swagger")
public class ClientPayloadSwagger implements Serializable {

    private String firstname;
    private String lastname;
    private String fullname;
    private String middlename;
    @ApiModelProperty(required = true, hidden = false)
    private Long officeId;

    private Boolean active;
    private LocalDate activationDate;
    private Boolean isAddressEnabled;
    private AddressData address;
    private String accountNo;
    private String externalId;
    private String mobileNo;

    @ApiModelProperty(required = false, hidden = false)
    private CodeValueData gender;

    @ApiModelProperty(required = false, hidden = false)
    private CodeValueData clientType;

    @ApiModelProperty(required = false, hidden = false)
    private Long staffId;

    private List<DatatableData> datatables;
    private String displayName;
    private LocalDate dateOfBirth;

    public ClientPayloadSwagger(){

    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public LocalDate getActivationDate() {
        return activationDate;
    }

    public void setActivationDate(LocalDate activationDate) {
        this.activationDate = activationDate;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public AddressData getAddress() {
        return address;
    }

    public void setAddress(AddressData address) {
        this.address = address;
    }

    public Boolean getAddressEnabled() {
        return isAddressEnabled;
    }

    public void setAddressEnabled(Boolean addressEnabled) {
        isAddressEnabled = addressEnabled;
    }

    public List<DatatableData> getDatatables() {
        return datatables;
    }

    public void setDatatables(List<DatatableData> datatables) {
        this.datatables = datatables;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public CodeValueData getGender() {
        return gender;
    }

    public void setGender(CodeValueData gender) {
        this.gender = gender;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public String getFirstname() {
        return firstname;
    }



}
