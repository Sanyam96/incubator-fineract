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
package org.apache.fineract.accounting.closure.swagger;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import org.joda.time.LocalDate;

/**
 * Created by sanyam on 21/7/17.
 */
@ApiModel(value = "POST GLCLosures Paylaod")
public class POSTglclosuresPayload {


    public Long officeId;
    public LocalDate closingDate;
    public String comments;

    public String locale;

    public String dateFormat;

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    @ApiModelProperty(example = "1")
    public Long getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Long officeId) {
        this.officeId = officeId;
    }

    @ApiModelProperty(example = "06 December 2012")
    public LocalDate getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(LocalDate closingDate) {
        this.closingDate = closingDate;
    }

    @ApiModelProperty(example = "en")
    public String getLocale() {
        return locale;
    }

    @ApiModelProperty(example = "dd MMMM yyyy")
    public String getDateFormat() {
        return dateFormat;
    }

    @ApiModelProperty(example = "The accountants are heading for a carribean vacation")
    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public POSTglclosuresPayload(Long officeId, LocalDate closingDate, String comments, String locale, String dateFormat) {
        this.officeId = officeId;
        this.closingDate = closingDate;
        this.comments = comments;
        this.locale = locale;
        this.dateFormat = dateFormat;
    }
}
