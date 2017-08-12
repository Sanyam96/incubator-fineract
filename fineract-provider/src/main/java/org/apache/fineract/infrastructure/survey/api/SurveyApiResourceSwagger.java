package org.apache.fineract.infrastructure.survey.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.fineract.infrastructure.dataqueries.data.DatatableData;
import org.apache.fineract.infrastructure.dataqueries.data.ResultsetColumnHeaderData;

import java.util.List;

/**
 * Created by sanyam on 13/8/17.
 */
final class SurveyApiResourceSwagger {
    private SurveyApiResourceSwagger(){

    }

    @ApiModel(value = "GetSurveyResponse")
    public static final class GetSurveyResponse {
        private GetSurveyResponse(){

        }
        final class GetSurveyResponseDatatableData {
            private GetSurveyResponseDatatableData() {

            }
            @ApiModelProperty(example = "m_client")
            public String applicationTableName;
            @ApiModelProperty(example = "ppi_kenya_2005")
            public String registeredTableName;
            public List<ResultsetColumnHeaderData> columnHeaderData;
        }
        public GetSurveyResponseDatatableData datatableData;
        @ApiModelProperty(example = "false")
        public boolean enabled;
    }

    @ApiModel(value = "PostSurveySurveyNameApptableIdResponse")
    public static final class PostSurveySurveyNameApptableIdResponse {
        private PostSurveySurveyNameApptableIdResponse() {

        }
        @ApiModelProperty(example = "2")
        public Long officeId;
        @ApiModelProperty(example = "87")
        public Long clientId;
        @ApiModelProperty(example = "87")
        public Long resourceId;
    }
}
