
package org.apache.fineract.infrastructure.hooks.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.fineract.infrastructure.hooks.data.Event;
import org.apache.fineract.infrastructure.hooks.data.Field;

import java.util.List;

/**
 * Created by sanyam on 11/8/17.
 */

final class HookApiResourceSwagger {
    private HookApiResourceSwagger() {

    }

    @ApiModel(value = "PostHookRequest")
    public static final class PostHookRequest {
        private PostHookRequest () {

        }

        @ApiModelProperty(example = "Web")
        public String name;
        @ApiModelProperty(example = "true")
        public Boolean isActive;
        @ApiModelProperty(example = "Kremlin")
        public String displayName;
        @ApiModelProperty(example = "1")
        public Long templateId;
        public List<Event> events;
        public List<Field> config;
    }

    @ApiModel(value = "PostHookResponse")
    public static final class PostHookResponse {
        private PostHookResponse() {

        }
        @ApiModelProperty(example = "4")
        public Long resourceId;
    }
}
