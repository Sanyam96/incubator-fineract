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

public final class GLClosuresApiResourceSwagger {
    private GLClosuresApiResourceSwagger() {
        // don't allow to instantiate
    }

    @ApiModel(value = "POST GLCLosures Request")
    public static final class PostGlClosuresRequest {
        private PostGlClosuresRequest() {
            // don't allow to instantiate
        }

        @ApiModelProperty(example = "1")
        public Long officeId;

        @ApiModelProperty(example = "06 December 2012")
        public LocalDate closingDate;

        @ApiModelProperty(example = "The accountants are heading for a carribean vacation")
        public String comments;

        @ApiModelProperty(example = "en")
        public String locale;

        @ApiModelProperty(example = "dd MMMM yyyy")
        public String dateFormat;
    }

    @ApiModel(value = "POST GlClosures response")
    public static final class PostGlClosuresResponse {
        private PostGlClosuresResponse() {
            // don't allow to instantiate
        }

        @ApiModelProperty(example = "1")
        public Long officeId;

        @ApiModelProperty(example = "9")
        public Long resourceId;
    }
}